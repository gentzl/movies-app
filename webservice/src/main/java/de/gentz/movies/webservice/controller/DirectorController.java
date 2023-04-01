package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Director;
import de.gentz.movies.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
@Log4j2
public class DirectorController {

    private final DirectorRepository directorRepository;

    @GetMapping
    public List<Director> getDirectors() {
        var directors = directorRepository.findAllByOrderByLastname();
        log.debug("found {} directors", directors.size());
        return directors;
    }
}
