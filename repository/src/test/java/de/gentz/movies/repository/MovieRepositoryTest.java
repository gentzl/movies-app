package de.gentz.movies.repository;

import de.gentz.movies.builder.GenreTestDataBuilder;
import de.gentz.movies.builder.MovieTestDataBuilder;
import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableAutoConfiguration
@ContextConfiguration(classes = {MovieRepository.class, GenreRepository.class})
@DataJpaTest
@EntityScan("de.gentz.movies.entity")
public class MovieRepositoryTest {
    public static final Genre GENRE_ACTION = new GenreTestDataBuilder().name("Action").build();
    public static final Genre GENRE_DRAMA = new GenreTestDataBuilder().name("Drama").build();

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    Movie rambo1Movie = new MovieTestDataBuilder().genre(GENRE_ACTION).name("Rambo 1").build();
    Movie rambo2Movie = new MovieTestDataBuilder().genre(GENRE_ACTION).name("Rambo 2").build();
    Movie terminatorMovie = new MovieTestDataBuilder().genre(GENRE_ACTION).name("Terminator").build();

    @Test
    public void findByNameContaining_Found() {
        saveMovieWithAllReferences(rambo1Movie);
        saveMovieWithAllReferences(rambo2Movie);
        saveMovieWithAllReferences(terminatorMovie);
        var foundMovies = movieRepository.findByNameContainingIgnoreCase("amb");

        assertEquals(2, foundMovies.size());
        assertEquals("Rambo 1", foundMovies.get(0).getName());
        assertEquals("Rambo 2", foundMovies.get(1).getName());
    }

    @Test
    public void getReferenceById() {
        Movie titanicMovie = new MovieTestDataBuilder()
                .name("Titanic")
                .genre(GENRE_DRAMA)
                .build();
        saveMovieWithAllReferences(titanicMovie);

        titanicMovie = movieRepository.getReferenceById(titanicMovie.getId());

        assertEquals("Titanic", titanicMovie.getName());
        assertEquals("Drama", titanicMovie.getGenres().iterator().next().getName());
    }

    private synchronized void saveMovieWithAllReferences(Movie movie) {
        movie.getGenres().forEach(this::saveGenre);
        movieRepository.save(movie);
    }

    private void saveGenre(Genre genre) {
        if (genreRepository.findByName(genre.getName()) == null) {
            genreRepository.save(genre);
        }
    }

}