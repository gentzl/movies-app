package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Movie;
import de.gentz.movies.repository.MovieRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(path = "/find")
    public ResponseEntity findMovies(@RequestBody String searchText) {
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(searchText);
        log.debug("found {} movies", movies.size());
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        var movie = movieRepository.findById(id);
        log.debug("found movie: {}", movie);

        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(movie.get());
    }

    @PostMapping
    public ResponseEntity createMovie(@Valid @RequestBody Movie movie) throws URISyntaxException {
        var savedMovie = movieRepository.save(movie);
        log.debug("movie saved: {}", movie);
        return ResponseEntity.created(new URI("/movies/" + savedMovie.getId())).body(savedMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
