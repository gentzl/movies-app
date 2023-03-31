package de.gentz.movies.webservice.controller;

import de.gentz.movies.entity.Movie;
import de.gentz.movies.repository.MovieRepository;
import de.gentz.movies.webservice.mapper.MovieDtoMapper;
import de.gentz.movies.webservice.model.MovieDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Log4j2
public class MovieController {

    private final MovieRepository movieRepository;

    @GetMapping
    public List<MovieDto> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        log.debug("found {} movies", movies.size());
        return movies.stream().map(MovieDtoMapper::mapToDto).collect(Collectors.toList());
    }

    @PostMapping(path = "/find")
    public ResponseEntity findMovies(@RequestBody String searchText) {
        List<Movie> movies = movieRepository.findByNameContainingIgnoreCase(searchText);
        log.debug("found {} movies", movies.size());
        return ResponseEntity.ok(movies.stream().map(MovieDtoMapper::mapToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        var movie = movieRepository.findById(id);
        log.debug("found movie: {}", movie);

        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(MovieDtoMapper.mapToDto(movie.get()));
    }

    @PostMapping
    public ResponseEntity createMovie(@Valid @RequestBody MovieDto movieDto) throws URISyntaxException {
        var movie = MovieDtoMapper.mapTo(movieDto);
        var savedMovie = movieRepository.save(movie);
        log.debug("movieDto saved: {}", movieDto);
        return ResponseEntity.created(new URI("/movies/" + savedMovie.getId())).body(MovieDtoMapper.mapToDto(savedMovie));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable @Valid Integer id, @RequestBody MovieDto movieDto) {
        var movieFromDb = movieRepository.findById(id.longValue()).orElseThrow(RuntimeException::new);
        log.debug("movieDto updated: {}", movieDto);
        var movie = MovieDtoMapper.mapTo(movieDto);
        movieFromDb = movieRepository.save(movie);

        return ResponseEntity.ok(MovieDtoMapper.mapToDto(movieFromDb));
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
