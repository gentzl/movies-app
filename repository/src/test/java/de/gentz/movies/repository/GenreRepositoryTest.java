package de.gentz.movies.repository;

import de.gentz.movies.builder.GenreTestDataBuilder;
import de.gentz.movies.entity.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@EnableAutoConfiguration
@ContextConfiguration(classes = {GenreRepository.class})
@DataJpaTest
@EntityScan("de.gentz.movies.entity")
public class GenreRepositoryTest {

    public static final Genre GENRE_ACTION = new GenreTestDataBuilder().name("DOCUMENTATION").build();

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void findByName_Found() {
        genreRepository.save(GENRE_ACTION);
        var genre = genreRepository.findByName(GENRE_ACTION.getName());

        assertEquals(GENRE_ACTION.getName(), genre.getName());
    }

    @Test
    public void findByName_NotFound() {
        var genre = genreRepository.findByName("you can't find me");

        assertNull(genre);
    }
}