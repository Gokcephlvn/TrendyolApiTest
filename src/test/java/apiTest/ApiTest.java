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

		MovieModel movie = response.getBody().as(MovieModel.class);

		assertThat("Tests the status code ", Keywords.StatusCode, is(equalTo(response.getStatusCode())));

		assertThat("Tests the title of the movie", Keywords.movieTitle, is(equalTo(movie.getTitle())));

		assertThat("Tests the release year of the movie", Keywords.year, is(equalTo(movie.getYear())));

		assertThat("Tests the movie's release date", Keywords.relased, is(equalTo(movie.getReleased())));

	}

}
