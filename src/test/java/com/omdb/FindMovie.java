package com.omdb;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class FindMovie {

    public FindMovie() {
        baseURI = "http://www.omdbapi.com/"; // Base url'i bu şekilde tanımlıyoruz.
    }
    public Response getSearchResponse(String apiKey, String searchWord) {

        Response searchResponse =
                given()
                        .param("apikey", apiKey)
                        .param("s", searchWord)
                        .when().get()
                        .then().log().all()
                        .contentType(ContentType.JSON)
                        .statusCode(200) //status code 200 olmalı
                        .extract()
                        .response();
        return searchResponse;
    }

    public Response searchByID(String apiKey, String imdbId) {

        Response searchId =
                given()
                    .param("apikey", apiKey)
                    .param("i",imdbId) // ID'si alinan film icin arama yapiyoruz
                    .when().get()  //methodumuzun türünü belirtiyoruz (method içine yazdığımız değer baseURI'in sonuna eklenir)
                    .then().log().all()
                    .statusCode(200) //statuscode 200 dönmelidir
                    .body("imdbRating", not(emptyOrNullString()))
                    .extract()
                    .response();
        return searchId; //Dönen response'u Test fonksiyonumuza Response olarak döndürüyoruz

            }

}


