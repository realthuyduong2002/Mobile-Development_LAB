package com.example.hw2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.TextAnalyticsService;
import com.example.hw2.model.TextRequest;
import com.example.hw2.model.ClassificationResponse;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView resultText;
    private Button classifyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        resultText = findViewById(R.id.resultText);
        classifyButton = findViewById(R.id.classifyButton);

        classifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToClassify = inputText.getText().toString();
                classifyText(textToClassify);
            }
        });
    }

    private void classifyText(String text) {
        // Tạo Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo service instance
        TextAnalyticsService service = retrofit.create(TextAnalyticsService.class);

        // Gửi yêu cầu và xử lý phản hồi
        Call<ClassificationResponse> call = service.classifyText("YOUR_API_KEY", new TextRequest(text));
        call.enqueue(new Callback<ClassificationResponse>() {
            @Override
            public void onResponse(Call<ClassificationResponse> call, Response<ClassificationResponse> response) {
                if (response.isSuccessful()) {
                    ClassificationResponse classificationResponse = response.body();
                    // Hiển thị kết quả phân loại
                    resultText.setText(classificationResponse.toString());
                } else {
                    // Xử lý lỗi
                    resultText.setText("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ClassificationResponse> call, Throwable t) {
                // Xử lý lỗi
                resultText.setText("Error: " + t.getMessage());
            }
        });
    }
}
