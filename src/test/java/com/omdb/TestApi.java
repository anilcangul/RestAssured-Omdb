package com.omdb;

import org.junit.Test;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class TestApi {

    FindMovie findMovie = new FindMovie();
    String apiKey = System.getProperty("apiKey","49e530b5"); // Api key
    String movieTitle = "Batman: The Dark Knight Returns, Part 1";
    public static String imdbId;


    @Test
    public void Test_001_Batman(){
        String searchWord = "Batman";
        Response searchResponse = findMovie.getSearchResponse(apiKey,searchWord);
        String imdbId =  searchResponse.then().extract().response().path("Search.find{it.Title=='"+movieTitle+"'}.imdbID");
        this.imdbId = imdbId; // aldığımız imdbId değerini class içine tanımlıyoruz.
    }

    @Test
    public void Test_002_Batman_CheckTheRating(){
        Response searchID = findMovie.searchByID(apiKey, imdbId); // Önceki fonk'da aldıgımız id'yi yolluyoruz.
        searchID.then().body("imdbRating",equalTo("8.0")); // imbId ile arattığım filmin puanını 8.0 olacağını idda ediyorum.



    }

}
