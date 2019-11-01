package org.fasttrackit.MovieNight.web;

import org.fasttrackit.MovieNight.domain.Movie;
import org.fasttrackit.MovieNight.persistence.MovieRepository;
import org.fasttrackit.MovieNight.service.MovieService;
import org.fasttrackit.MovieNight.transfer.movie.GetMoviesRequest;
import org.fasttrackit.MovieNight.transfer.movie.SaveMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid SaveMovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody SaveMovieRequest request) {
        Movie movie = movieService.updateMovie(id, request);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Movie> deleteMovie(long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id) {
        Movie movie = movieService.getMovie(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> getMovies(GetMoviesRequest request, Pageable pageable) {
        Page<Movie> movies = movieService.getMovies(request, pageable);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
