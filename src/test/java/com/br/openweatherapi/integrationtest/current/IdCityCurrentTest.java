package com.br.openweatherapi.integrationtest.current;

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
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Get Current Weather with IDCity Parameter")
@Tag(TestTags.REGRESSION)
@Tag(TestTags.CURRENT)
public class IdCityCurrentTest {
    ReaderProperties properties = new ReaderProperties();
    String idCity;
    String apiKey;
    String language;
    String units;
    String city;
    String humidity;
    String feels_like;
    String temperature;
    String sunrise;
    String sunset;
    Integer timestamp_sunrise;
    Integer timestamp_sunset;



    @Test
    @Order(1)
    @DisplayName("Get Current Weather by ID City")
    void getCurrentWeatherIdCity() {
        try {
            properties.readFile();
            idCity = properties.getIDCity();
            apiKey = properties.getIDD();
            language = properties.getIdiom();
            units = properties.getUnits();

            Response resposta = given()
                    .filter(new AllureRestAssured())
                    .baseUri(ENDPOINT)
                    .contentType(ContentType.JSON)
                    .header("Content-Type", "application/json")
                    .get("/weather?"+"id="+idCity+"&appid="+apiKey+"&lang="+language+"&units="+units);
            resposta.then()
                    .statusCode(200)
                    .body("name", equalToIgnoringCase("Recife"))
                    .extract().response().getBody().jsonPath().prettyPrint();

            JsonPath path = resposta.jsonPath();
            city = path.get("name");
            humidity = path.get("main.humidity").toString();
            feels_like = path.get("main.feels_like").toString();
            temperature = path.get("main.temp").toString();
            timestamp_sunrise = path.get("sys.sunrise");
            timestamp_sunset = path.get("sys.sunset");

            sunrise = this.convertTime(timestamp_sunrise);
            sunset = this.convertTime(timestamp_sunset);

            System.out.println("=========> Current Weather Conditions <==========");
            System.out.println("City: " + city);
            System.out.println("Humidity: " + humidity);
            System.out.println("Thermal sensation: "+feels_like);
            System.out.println("Temperature: "+temperature);
            System.out.println("Sun rise: "+sunrise);
            System.out.println("Sun set: "+sunset);

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
