package org.fasttrackit.MovieNight.persistence;

import org.fasttrackit.MovieNight.domain.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;


public interface ActorRepository extends JpaRepository<Actor,Long> {
    Page<Actor> findByFirstNameContainingOrLastNameContaining(@NotNull String partialFirstName, @NotNull String partialLastName, Pageable pageable);
    Page<Actor> findByFirstNameContainingOrLastNameContainingAndAge(@NotNull String partialFirstName, @NotNull String partialLastName, @NotNull Integer age, Pageable pageable);
}
