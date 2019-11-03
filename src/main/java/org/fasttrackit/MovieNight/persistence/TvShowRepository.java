package org.fasttrackit.MovieNight.persistence;

import org.fasttrackit.MovieNight.domain.TvShow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository<TvShow ,Long> {
    Page<TvShow> findByNameContaining(String partialName, Pageable pageable);
    Page<TvShow> findByReleaseYear(Integer releaseYear, Pageable pageable);
    Page<TvShow> findByNameContainingAndReleaseYear(String partialName,Integer releaseYear, Pageable pageable);
}
