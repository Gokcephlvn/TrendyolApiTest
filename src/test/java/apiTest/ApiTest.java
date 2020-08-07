package apiTest;


import io.restassured.response.Response;
import models.MovieModel;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


import apiHelper.OmdbApiHelper;
import constants.Keywords;





public class ApiTest {
    @Test
    public void searchMovie() {
    	OmdbApiHelper search = new OmdbApiHelper();
 
      
        String imdbId = search.getMovieId();
          
        Response response = search.getMovieById(imdbId);

        MovieModel movie=response.getBody().as(MovieModel.class);
      
        assertThat("ben status test ediyorum",Keywords.StatusCode,is(equalTo(response.getStatusCode())));
       
        assertThat("ben title test ediyorum",Keywords.movieTitle,is(equalTo(movie.getTitle())));
        
        assertThat("ben title test ediyorum",Keywords.year,is(equalTo(movie.getYear())));
        
        assertThat("ben title test ediyorum",Keywords.relased,is(equalTo(movie.getRelased())));
        
        
    }


}
