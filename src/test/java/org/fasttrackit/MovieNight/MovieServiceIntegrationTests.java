package org.fasttrackit.MovieNight;


import org.fasttrackit.MovieNight.domain.Movie;
import org.fasttrackit.MovieNight.exception.ResourceNotFoundException;
import org.fasttrackit.MovieNight.service.MovieService;
import org.fasttrackit.MovieNight.transfer.movie.SaveMovieRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceIntegrationTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testCreateMovie_whenValidRequest_thenReturnCreatedMovie() {
        createMovie();
    }


    @Test(expected = TransactionSystemException.class)
    public void testCreateMovie_whenInvalidRequest_thenThrowTransactionSystemException() {
        SaveMovieRequest request = new SaveMovieRequest();
        movieService.createMovie(request);
    }

    @Test
    public void testGetMovie_whenExistingEntity_thenReturnMovie() {
        Movie createdMovie = createMovie();
        Movie retrievedMovie = movieService.getMovie(createdMovie.getId());
        assertThat(retrievedMovie, notNullValue());
        assertThat(retrievedMovie.getName(), notNullValue());
        assertThat(retrievedMovie.getName(), is(createdMovie.getName()));
        assertThat(retrievedMovie.getReleaseYear(), notNullValue());
        assertThat(retrievedMovie.getReleaseYear(), is(createdMovie.getReleaseYear()));
        assertThat(retrievedMovie.getImagePath(), is(createdMovie.getImagePath()));
        assertThat(retrievedMovie.getDescription(), notNullValue());
        assertThat(retrievedMovie.getDescription(), is(createdMovie.getDescription()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetMovie_whenInexistingEntity_thenThrowResourceNotFoundException() {
        movieService.getMovie(99999999);
    }

    @Test
    public void testUpdateMovie_withValidRequest_thenReturnUpdatedMovie() {
        Movie movie = createMovie();
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName(movie.getName() + " updated");
        request.setImagePath(movie.getImagePath() + " updated");
        request.setReleaseYear(movie.getReleaseYear() + 2);
        request.setDescription(movie.getDescription() + " updated");

        Movie updatedMovie = movieService.updateMovie(movie.getId(), request);
        assertThat(updatedMovie, notNullValue());
        assertThat(updatedMovie.getName(), notNullValue());
        assertThat(updatedMovie.getName(), is(request.getName()));
        assertThat(updatedMovie.getDescription(), notNullValue());
        assertThat(updatedMovie.getDescription(), is(request.getDescription()));
        assertThat(updatedMovie.getReleaseYear(), notNullValue());
        assertThat(updatedMovie.getReleaseYear(), is(request.getReleaseYear()));
        assertThat(updatedMovie.getImagePath(), is(request.getImagePath()));

    }

    @Test(expected = TransactionSystemException.class)
    public void testUpdateMovie_whenInvalidValue_thenThrowException() {
        Movie movie = createMovie();
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName(null);
        request.setImagePath(null);
        request.setReleaseYear(0);
        request.setDescription(null);
        Movie updatedMovie = movieService.updateMovie(movie.getId(), request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteMovie() {
        Movie movie1 = createMovie();
        Movie movie2 = createMovie();
        movieService.deleteMovie(movie1.getId());
        movieService.getMovie(movie1.getId());

    }

    private Movie createMovie() {
        SaveMovieRequest request = new SaveMovieRequest();
        request.setName("The Lord Of The Rings: The Fellowship Of The Ring");
        request.setDescription("coolest movie eva");
        request.setReleaseYear(2001);
        request.setImagePath("folder");

        Movie movie = movieService.createMovie(request);
        assertThat(movie, notNullValue());
        assertThat(movie.getId(), notNullValue());
        assertThat(movie.getId(), greaterThan(0L));
        assertThat(movie.getName(), notNullValue());
        assertThat(movie.getName(), is(request.getName()));
        assertThat(movie.getDescription(), notNullValue());
        assertThat(movie.getDescription(), is(request.getDescription()));
        assertThat(movie.getImagePath(), is(request.getImagePath()));
        assertThat(movie.getReleaseYear(), is(request.getReleaseYear()));

        return movie;
    }
}
