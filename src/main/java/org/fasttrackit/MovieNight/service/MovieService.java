package org.fasttrackit.MovieNight.service;

import org.fasttrackit.MovieNight.domain.Movie;
import org.fasttrackit.MovieNight.exception.ResourceNotFoundException;
import org.fasttrackit.MovieNight.persistence.MovieRepository;
import org.fasttrackit.MovieNight.transfer.movie.GetMoviesRequest;
import org.fasttrackit.MovieNight.transfer.movie.SaveMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovie(long id) {
        LOGGER.info("Retrieving movie {}", id);
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

    public Page<Movie> getMovies(GetMoviesRequest request, Pageable pageable) {
        LOGGER.info("Retrieving movies {}", request);
        if (request != null && request.partialName != null && request.releaseYear != null) {
            return movieRepository.findByNameContainingAndReleaseYear(request.partialName, request.releaseYear, pageable);
        } else if (request != null && request.partialName != null) {
            return movieRepository.findByNameContaining(request.partialName, pageable);
        } else if (request != null && request.releaseYear != null) {
            return movieRepository.findByReleaseYear(request.releaseYear, pageable);
        } else return movieRepository.findAll(pageable);
    }

    public Movie createMovie(SaveMovieRequest request) {
        LOGGER.info("Creating movie: {}", request);
        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setImagePath(request.getImagePath());

        return movieRepository.save(movie);
    }

    public Movie updateMovie(long id, SaveMovieRequest request) {
        LOGGER.info("Updating movie {}: {}", id, request);
        Movie movie = getMovie(id);
        BeanUtils.copyProperties(request, movie);
        return movieRepository.save(movie);
    }

    public void deleteMovie(long id) {
        LOGGER.info("Deleting movie {}", id);
        movieRepository.deleteById(id);
    }

}