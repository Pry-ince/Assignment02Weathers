package com.example.weathers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weathers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // You can now use binding to access UI elements, e.g.,
        // binding.textView.setText("Hello Weather!");
    }
}
