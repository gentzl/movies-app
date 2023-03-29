package de.gentz.movies.repository;

import de.gentz.movies.builder.MovieTestDataBuilder;
import de.gentz.movies.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnableAutoConfiguration
@ContextConfiguration(classes = MovieRepository.class)
@DataJpaTest
@EntityScan("de.gentz.movies.entity")
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    Movie rambo1Movie = new MovieTestDataBuilder().name("Rambo 1").build();
    Movie rambo2Movie = new MovieTestDataBuilder().name("Rambo 2").build();
    Movie titanicMovie = new MovieTestDataBuilder().name("Titanic").build();

    @Test
    public void findByNameContaining_Found() {
        movieRepository.save(rambo1Movie);
        movieRepository.save(rambo2Movie);
        movieRepository.save(titanicMovie);
        var foundMovies = movieRepository.findByNameContainingIgnoreCase("ram");
        assertEquals(2, foundMovies.size());
        assertEquals("Rambo 1", foundMovies.get(0).getName());
        assertEquals("Rambo 2", foundMovies.get(1).getName());
    }

}