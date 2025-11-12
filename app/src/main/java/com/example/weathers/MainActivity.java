package com.example.weathers;

import com.example.weathers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//This is an new weathers app
        // You can now access UI elements directly through binding, e.g.
        // binding.textView.setText("Hello Weather!");
    }
}
