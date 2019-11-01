package org.fasttrackit.MovieNight.web;

import org.fasttrackit.MovieNight.domain.Actor;
import org.fasttrackit.MovieNight.persistence.ActorRepository;
import org.fasttrackit.MovieNight.service.ActorService;
import org.fasttrackit.MovieNight.transfer.actor.GetActorsRequest;
import org.fasttrackit.MovieNight.transfer.actor.SaveActorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;
    private final ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorService actorService, ActorRepository actorRepository) {
        this.actorService = actorService;
        this.actorRepository = actorRepository;
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody @Valid SaveActorRequest request) {
        Actor actor = actorService.createActor(request);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") long id, @RequestBody SaveActorRequest request) {
        Actor actor = actorService.updateActor(id, request);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Actor> deleteActor(long id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable("id") long id) {
        Actor actor = actorService.getActor(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Actor>> getActors(GetActorsRequest request, Pageable pageable) {
        Page<Actor> actors = actorService.getActors(request, pageable);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
}
