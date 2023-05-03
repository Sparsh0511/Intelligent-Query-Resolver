package com.example.smartqueryresolver;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.google.gson.JsonObject;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("ALL")
public interface DallEApi {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "Authorization: Bearer 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"
    })
    @FormUrlEncoded
    @POST("https://api.openai.com/v1/images/generations")
    Call<DallEResponse> generateImage(
            @Field("prompt") String prompt,
            @Field("num_images") int num,
            @Field("size") int size,
            @Field("response_format") int responseFormat,
            @Field("noise_removal") boolean noiseRemoval,
            @Field("return_bytes") boolean returnBytes
    );


    class DallEResponse {
        @SerializedName("data")
        public List<JsonObject> data;
    }
}
