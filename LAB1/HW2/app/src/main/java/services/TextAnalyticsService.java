package services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Header;
import com.example.hw2.model.TextRequest;
import com.example.hw2.model.ClassificationResponse;

public interface TextAnalyticsService {
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @POST("sentiment")
    Call<ClassificationResponse> classifyText(
            @Header("Ocp-Apim-Subscription-Key") String apiKey,
            @Body TextRequest body
    );
}
