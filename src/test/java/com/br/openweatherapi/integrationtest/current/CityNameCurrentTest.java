package com.br.openweatherapi.integrationtest.current;

import com.br.openweatherapi.integrationtest.utils.ReaderProperties;
import com.br.openweatherapi.integrationtest.utils.TestTags;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.br.openweatherapi.integrationtest.utils.Constants.ENDPOINT;
import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Get Current Weather with City Name Parameter")
@Tag(TestTags.REGRESSION)
@Tag(TestTags.CURRENT)
public class CityNameCurrentTest {
    ReaderProperties properties = new ReaderProperties();
    String apiKey;
    String language;
    String units;
    String city;
    String humidity;
    String feels_like;
    String temperature;
    String sunrise;
    String sunset;



    @Test
    @Order(1)
    @DisplayName("Get Current Weather by City Name")
    void getCurrentWeatherCityName() {
        try {
            properties.readFile();
            city = properties.getCityName();
            apiKey = properties.getIDD();
            language = properties.getIdiom();
            units = properties.getUnits();

            Response resposta = given()
                    .filter(new io.qameta.allure.restassured.AllureRestAssured())
                    .baseUri(ENDPOINT)
                    .get("/weather?"+"q="+city+"&appid="+apiKey+"&lang="+language+"&units="+units+"&mode=xml");
            resposta.then()
                    .contentType(ContentType.XML)
                    .statusCode(200)
                    .extract().xmlPath().prettyPrint();

            city = resposta.xmlPath().getNode("current").children().getNode("city").getAttribute("name").toString();
            humidity = resposta.xmlPath().getNode("current").children().getNode("humidity").getAttribute("value").toString();
            feels_like = resposta.xmlPath().getNode("current").children().getNode("feels_like").getAttribute("value").toString();
            temperature = resposta.xmlPath().getNode("current").children().getNode("temperature").getAttribute("value").toString();
            sunrise = resposta.xmlPath().getNode("current").children().getNode("city").children().getNode("sun").getAttribute("rise").toString();
            sunset =  resposta.xmlPath().getNode("current").children().getNode("city").children().getNode("sun").getAttribute("set").toString();

            System.out.println("\n\n=========> Current Weather Conditions <==========");
            System.out.println("City: " + city);
            System.out.println("Humidity: " + humidity);
            System.out.println("Thermal sensation: "+feels_like+"ºC");
            System.out.println("Temperature: "+temperature+"ºC");
            System.out.println("Sun rise: "+sunrise);
            System.out.println("Sun set: "+sunset);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
