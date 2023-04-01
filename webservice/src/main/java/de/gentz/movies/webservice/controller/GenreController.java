package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Genre;
import de.gentz.movies.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@Log4j2
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping
    public List<Genre> getGenres() {
        var genres = genreRepository.findAllByOrderByNameAsc();
        log.debug("found {} genres", genres.size());
        return genres;
    }
}
