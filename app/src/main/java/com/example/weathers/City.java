package com.example.weathers;

public class City {
    private final String name;
    private final String query; // what we send to the API later

    public City(String name, String query) {
        this.name = name;
        this.query = query;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }
}