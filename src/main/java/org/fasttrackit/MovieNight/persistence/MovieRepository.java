package org.fasttrackit.MovieNight.persistence;

import org.fasttrackit.MovieNight.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
