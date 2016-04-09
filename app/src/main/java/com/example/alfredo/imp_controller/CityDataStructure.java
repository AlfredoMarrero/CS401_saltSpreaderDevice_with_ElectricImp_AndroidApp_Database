package com.example.alfredo.imp_controller;

/**
 * Created by alfred on 3/13/16.
 */

public class CityDataStructure {

    private String cityName;
    private String countryName;
    private String cityID;

    // constructor
    public CityDataStructure(String cityId, String city, String country ) {
        this.cityName = city;
        this.countryName = country;
        this.cityID = cityId;
    }

    // getter
    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String name) {
        this.cityName = name;
    }

    public String getCountry()
    {
        return countryName;
    }
    // setter

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityId) {
        this.cityID = cityId;
    }

    public void setCountryName(String code)
    {
        this.countryName = code;
    }
}
