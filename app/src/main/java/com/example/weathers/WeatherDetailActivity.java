package com.example.weathers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.weathers.databinding.ActivityWeatherDetailBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.Executors;

public class WeatherDetailActivity extends AppCompatActivity {

    // ViewBinding for layout access
    private ActivityWeatherDetailBinding binding;

    // OkHttp client handles HTTP requests
    private final OkHttpClient client = new OkHttpClient();

    //     WeatherAPI key
    private static final String API_KEY = "1c35b78720fb434eb8c184427251511";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityWeatherDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get city name passed from MainActivity
        String city = getIntent().getStringExtra("city");
        binding.tvCity.setText(city);

        // Simple back button to return to main screen
        binding.btnBack.setOnClickListener(v -> finish());

        // Run network call in background thread to avoid blocking UI
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                // Build the API request URL
                String url = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city + "&aqi=no";

                // Send GET request to WeatherAPI
                Request request = new Request.Builder().url(url).build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    // Parse the JSON response
                    String body = response.body().string();
                    JSONObject root = new JSONObject(body);
                    JSONObject current = root.getJSONObject("current");
                    JSONObject condition = current.getJSONObject("condition");

                    // Extract weather info from JSON
                    double tempC = current.getDouble("temp_c");
                    double tempF = current.getDouble("temp_f");
                    double feelsLike = current.getDouble("feelslike_c");
                    String conditionText = condition.getString("text");

                    // Update the UI on main thread
                    runOnUiThread(() -> {
                        binding.tvCondition.setText("Condition: " + conditionText);
                        binding.tvTempC.setText(String.format("Temperature (C): %.1f °C", tempC));
                        binding.tvTempF.setText(String.format("Temperature (F): %.1f °F", tempF));
                        binding.tvWindChill.setText(String.format("Feels Like (C): %.1f °C", feelsLike));
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Display error message on failure
                runOnUiThread(() ->
                        binding.tvCondition.setText("Error loading weather. Check API key or network.")
                );
            }
        });
    }
}
