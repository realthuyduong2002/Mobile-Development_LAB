package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameEdTxt, emailEdTxt, projectEdTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdTxt = findViewById(R.id.nameEdTxt);
        emailEdTxt = findViewById(R.id.emailEdTxt);
        projectEdTxt = findViewById(R.id.projectEdTxt);

        Button viewContactBtn = findViewById(R.id.viewContactBtn);
        viewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển từ MainActivity sang ContactInfo
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);

                // Truyền dữ liệu từ EditTexts sang ContactInfo
                intent.putExtra("nameKey", nameEdTxt.getText().toString());
                intent.putExtra("emailKey", emailEdTxt.getText().toString());
                intent.putExtra("projectKey", projectEdTxt.getText().toString());

                // Khởi chạy ContactInfo Activity
                startActivity(intent);
            }
        });
    }
}
