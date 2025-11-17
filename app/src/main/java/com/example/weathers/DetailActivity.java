package com.example.weathers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.weathers.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private DetailView viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String locationName = getIntent().getStringExtra("locationName");

        viewModel = new ViewModelProvider(this).get(DetailView.class);

        // Add observer to LiveData
        viewModel.getWeatherLiveData().observe(this, new Observer<DetailView.WeatherData>() {
            @Override
            public void onChanged(DetailView.WeatherData weatherData) {
                if (weatherData != null) {
                    binding.locationNameTextView.setText(weatherData.locationName);
                    binding.temperatureCTextView.setText(weatherData.tempCelsius + " °C");
                    binding.temperatureFTextView.setText(weatherData.tempFahrenheit + " °F");
                    binding.conditionTextView.setText(weatherData.conditionText);
                    binding.windChillTextView.setText("Wind Chill: " + weatherData.windChill);
                }
            }
        });

        // Fetch weather data for the given location (simulate API call)
        viewModel.fetchWeatherForLocation(locationName);

        binding.backButton.setOnClickListener(v -> finish());
    }
}
