package org.fasttrackit.MovieNight.service;

import org.fasttrackit.MovieNight.domain.Actor;
import org.fasttrackit.MovieNight.exception.ResourceNotFoundException;
import org.fasttrackit.MovieNight.persistence.ActorRepository;
import org.fasttrackit.MovieNight.transfer.actor.GetActorsRequest;
import org.fasttrackit.MovieNight.transfer.actor.SaveActorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ActorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    public Actor getActor(long id) {
        LOGGER.info("Retrieving actor {}", id);
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
    }

    public Page<Actor> getActors(GetActorsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving actors {}", request);
        if (request != null && request.age != null && (request.partialFirstName != null || request.partialLastName !=null)){
            return actorRepository.findByFirstNameContainingOrLastNameContainingAndAge(request.partialFirstName ,request.partialLastName , request.age, pageable);
        }else if(request != null && (request.partialFirstName != null || request.partialLastName !=null)){
            return  actorRepository.findByFirstNameContainingOrLastNameContaining(request.partialFirstName, request.partialLastName,pageable);
        }else return actorRepository.findAll(pageable);
    }

    public Actor createActor(SaveActorRequest request) {
        LOGGER.info("Creating actor: {}", request);
        Actor actor = new Actor();
        actor.setFirstName(request.getFirstName());
        actor.setLastName(request.getLastName());
        actor.setAge(request.getAge());

        return actorRepository.save(actor);
    }

    public Actor updateActor(long id, SaveActorRequest request) {
        LOGGER.info("Updating actor {}: {}", id, request);
        Actor actor = getActor(id);
        BeanUtils.copyProperties(request, actor);
        return actorRepository.save(actor);
    }

    public void deleteActor(long id) {
        LOGGER.info("Deleting actor {}", id);
        actorRepository.deleteById(id);
    }
}
