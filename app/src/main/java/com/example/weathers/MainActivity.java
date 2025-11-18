
package com.example.weathers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weathers.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // cREATE SAMPLE DATA (Updated to Toronto, Montreal, Barrie) 🇨🇦
        List<Location> sampleLocations = new ArrayList<>();
        sampleLocations.add(new Location("Toronto"));
        sampleLocations.add(new Location("Montreal"));
        sampleLocations.add(new Location("Barrie"));


        LocationAdapter adapter = new LocationAdapter(sampleLocations, location -> {

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("locationName", location.getName());
            startActivity(intent);
        });

        // SET THE ADAPTER to the RecyclerView
        binding.locationsRecyclerView.setAdapter(adapter);
    }
}