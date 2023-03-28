package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Movie;
import de.gentz.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Log4j2
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        log.debug("found {} movies", movies.size());
        return movies;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        var movie = movieRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("found movie: {}", movie);
        return movie;
    }

    @PostMapping
    public ResponseEntity createMovie(@RequestBody Movie movie) throws URISyntaxException {
        var savedMovie = movieRepository.save(movie);
        log.debug("movie saved: {}", movie);
        return ResponseEntity.created(new URI("/movies/" + savedMovie.getId())).body(savedMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        var movieFromDb = movieRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("movie updated: {}", movie);
        updateMovie(movie, movieFromDb);
        movieFromDb = movieRepository.save(movie);

        return ResponseEntity.ok(movieFromDb);
    }

    private static void updateMovie(Movie movie, Movie movieFromDb) {
        movieFromDb.setName(movie.getName());
        movieFromDb.setYear(movie.getYear());
        movieFromDb.setRating(movie.getRating());
        movieFromDb.setSynopsis(movie.getSynopsis());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        log.debug("movie with id='{}' deleted", id);
        return ResponseEntity.ok().build();
    }
}
