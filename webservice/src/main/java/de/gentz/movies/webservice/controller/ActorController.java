package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Actor;
import de.gentz.movies.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
@Log4j2
public class ActorController {

    private final ActorRepository actorRepository;

    @GetMapping
    public List<Actor> getGenres() {
        var actors = actorRepository.findAll();
        log.debug("found {} actors", actors.size());
        return actors;
    }
}
