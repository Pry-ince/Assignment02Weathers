package com.example.weathers;
public class WeatherResponse {
    public Location location;
    public Current current;

    public class Location {
        public String name;
        public String region;
        public String country;
    }

    public class Current {
        public double temp_c;
        public double temp_f;
        public Condition condition;
        public double windchill_c;
    }

    public class Condition {
        public String text;
        public String icon;
    }
}