package com.example.weathers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailView extends ViewModel {

    private final MutableLiveData<WeatherData> weatherLiveData = new MutableLiveData<>();
    private WeatherService weatherService;

    public DetailView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);
    }

    public LiveData<WeatherData> getWeatherLiveData() {
        return weatherLiveData;
    }

    public void fetchWeatherForLocation(String location) {
        String apiKey = "YOUR_API_KEY"; // Replace with your weatherapi.com API key

        Call<WeatherResponse> call = weatherService.getCurrentWeather(apiKey, location);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse body = response.body();
                    WeatherData data = new WeatherData(
                            body.location.name,
                            body.current.temp_c,
                            body.current.temp_f,
                            body.current.condition.text,
                            body.current.windchill_c
                    );
                    weatherLiveData.postValue(data);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle error, optionally post error state
            }
        });
    }

    public static class WeatherData {
        public final String locationName;
        public final double tempCelsius;
        public final double tempFahrenheit;
        public final String conditionText;
        public final double windChill;

        public WeatherData(String locationName, double tempCelsius, double tempFahrenheit, String conditionText, double windChill) {
            this.locationName = locationName;
            this.tempCelsius = tempCelsius;
            this.tempFahrenheit = tempFahrenheit;
            this.conditionText = conditionText;
            this.windChill = windChill;
        }
    }
}
