package com.example.smartqueryresolver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ImageGenerate extends AppCompatActivity {

    final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    ImageView imageView;
    EditText promptEdtText;
    Button generate;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_generate);

        imageView = findViewById(R.id.imageView);
        promptEdtText = findViewById(R.id.promptEdtText);
        generate = findViewById(R.id.generate);
        progressBar = findViewById(R.id.progressBar);

        generate.setOnClickListener(view -> {
            promptEdtText.setSelection(0);
            callAPI(promptEdtText.getText().toString());
        });
    }

    void callAPI(String prompt) {

        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("prompt", prompt);
            jsonBody.put("n", 1);
            jsonBody.put("size", "1024x1024");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/images/generations")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                progressBar.setVisibility(View.GONE);
                showFailureToast(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        String url = jsonArray.getJSONObject(0).getString("url");
                        runOnUiThread(() -> {
                            Picasso picasso = Picasso.get();
                            picasso.load(url).into(imageView);
                            progressBar.setVisibility(View.GONE);
                        });
                    } catch (JSONException e ) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void showFailureToast(IOException e) {
        Toast.makeText(this, "An Error Occurred" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}