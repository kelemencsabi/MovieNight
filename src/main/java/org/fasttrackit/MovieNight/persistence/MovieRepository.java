package org.fasttrackit.MovieNight.persistence;

import org.fasttrackit.MovieNight.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Page<Movie> findByNameContaining(String partialName, Pageable pageable);
    Page<Movie> findByReleaseYear(Integer releaseYear, Pageable pageable);
    Page<Movie> findByNameContainingAndReleaseYear(String partialName,Integer releaseYear, Pageable pageable);
}
