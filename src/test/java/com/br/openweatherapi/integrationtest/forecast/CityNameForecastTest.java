package com.br.openweatherapi.integrationtest.forecast;

import com.br.openweatherapi.integrationtest.utils.ReaderProperties;
import com.br.openweatherapi.integrationtest.utils.TestTags;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.time.Instant;
import java.util.Date;

import static com.br.openweatherapi.integrationtest.utils.Constants.ENDPOINT;
import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Get Forecast Weather with City Name Parameter")
@Tag(TestTags.REGRESSION)
@Tag(TestTags.FORECAST)
public class CityNameForecastTest {
    ReaderProperties properties = new ReaderProperties();
    String apiKey;
    String language;
    String units;
    String latitude;
    String longitude;
    String date;
    String min_temperature;
    String max_temperature;
    String weatherconditions;
    Integer timestamp;



    @Test
    @Order(1)
    @DisplayName("Get Forecast Weather by City Name")
    void getForecastWeatherCityName() {
        try {
            properties.readFile();
            longitude = properties.getLongitude();
            latitude = properties.getLatitude();
            apiKey = properties.getIDD();
            language = properties.getIdiom();
            units = properties.getUnits();

            Response resposta = given()
                    .filter(new AllureRestAssured())
                    .baseUri(ENDPOINT)
                    .contentType(ContentType.JSON)
                    .header("Content-Type", "application/json")
                    .get("/onecall?"+"lat="+latitude+"&lon="+longitude+"&exclude=current,minutely,hourly,alerts"+"&appid="+apiKey+"&lang="+language+"&units="+units);
            resposta.then()
                    .statusCode(200)
                    .extract().response().getBody().jsonPath().prettyPrint();

            JsonPath path = resposta.jsonPath();

            timestamp = path.get("daily[7].dt");
            min_temperature = path.get("daily[7].temp.min").toString();
            max_temperature = path.get("daily[7].temp.max").toString();
            weatherconditions = path.get("daily[7].weather.description").toString();

            date = this.convertTime(timestamp);


            System.out.println("=========> Weather forecast for the sixth day <==========");
            System.out.println("Date: " + date);
            System.out.println("Minimum temperature: "+min_temperature);
            System.out.println("Maximum temperature: "+max_temperature);
            System.out.println("Weather: "+weatherconditions);


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private String convertTime(Integer timestamp){
        Instant instant = Instant.ofEpochSecond(timestamp.longValue());
        Date date = Date.from(instant);
        return date.toString();
    }

}
