package com.br.openweatherapi.integrationtest.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReaderProperties {
    private Properties prop = new Properties();

    public ReaderProperties() {
    }

    public void readFile(){
        try {
            prop.load(new FileInputStream("src/test/java/resources/data.properties"));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String getIDD(){
        return this.prop.getProperty("APIKEY");
    }

    public String getIDCity(){
        return this.prop.getProperty("IDCITY");
    }

    public String getCityName(){
        return this.prop.getProperty("CITYNAME");
    }

    public String getZipCode(){
        return this.prop.getProperty("ZIPCODE");
    }

    public String getLatitude(){
        return this.prop.getProperty("LATITUDE");
    }

    public String getLongitude(){
        return this.prop.getProperty("LONGITUDE");
    }

    public String getUnits(){
        return this.prop.getProperty("UNITS");
    }

    public String getIdiom(){
        return this.prop.getProperty("IDIOM");
    }
}
