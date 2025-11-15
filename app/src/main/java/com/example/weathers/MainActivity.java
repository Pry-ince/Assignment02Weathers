package com.example.weathers;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.weathers.databinding.ActivityMainBinding;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<City> cities = Arrays.asList(
                new City("Toronto", "Toronto"),
                new City("Montreal", "Montreal"),
                new City("Barrie", "Barrie")
        );

        CityAdapter adapter = new CityAdapter(cities, city -> {
            Intent intent = new Intent(MainActivity.this, WeatherDetailActivity.class);
            intent.putExtra("city", city.getQuery());
            startActivity(intent);
        });

        binding.rvCities.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCities.setAdapter(adapter);
    }
}