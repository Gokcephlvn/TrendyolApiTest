package apiHelper;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.util.List;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.MovieModel;

public class OmdbApiHelper {
	
	  String baseURI = System.getProperty("baseURI", "http://www.omdbapi.com/");
	  String apiKey = System.getProperty("apiKey", "884fdc32");
	  String movieTitle = System.getProperty("movieTitle", "Harry Potter and the Sorcerer's Stone");
      String searchMovie = System.getProperty("searchMovie", "harry potter");
    

	    public String getMovieId() {
	        RestAssured.baseURI = baseURI;

	        String imdbId = null;

	        try {
	            Response responseMovie = getMovie();

	            JsonPath path = responseMovie.jsonPath();
	            List<MovieModel> movies = path.getList("Search", MovieModel.class);

	            for (MovieModel movie : movies)
	                if (movie.getTitle().equals(movieTitle)) {
	                    imdbId = movie.getImdbID();
	                    System.out.println("\nEXTRACTED ID: " + imdbId);
	                    break;
	                }

	            return imdbId;
	        } catch (Exception ex) {
	        	 System.out.println("HATA! " + ex.getMessage());
	        	   return null;
	        }
	     
	    }

	    public Response getMovieById( String imdbId) {
	        try {
	           return given()
	                    .param("apikey", apiKey)
	                    .param("i", imdbId)
	                    .when()
	                    .get();
	        } catch (Exception ex) {
	            System.out.println("HATA! " + ex.getMessage());
	            return null;
	        }
	    }

	    private Response getMovie() {
	        try {
	            return given()
	                    .param("apikey", apiKey)
	                    .param("s", searchMovie)
	                    .when()
	                    .get()
	                    .then()
	                    .log()
	                    .all()
	                    .contentType(ContentType.JSON)
	                    .statusCode(200)
	                    .body("Search.Title", not(emptyOrNullString())).and()
	                    .body("Search.Year", not(emptyOrNullString())).and()
	                    .body("Search.Released", not(emptyOrNullString()))
	                    .extract()
	                    .response();

	        } catch (Exception ex) {
	            System.out.println("HATA! " + ex.getMessage());
	            return null;
	        }

	    }

}
