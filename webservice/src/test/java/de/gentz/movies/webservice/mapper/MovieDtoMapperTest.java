package de.gentz.movies.webservice.mapper;

import de.gentz.movies.entity.Actor;
import de.gentz.movies.entity.Director;
import de.gentz.movies.entity.Genre;
import de.gentz.movies.webservice.builder.MovieTestDataBuilder;
import de.gentz.movies.webservice.model.MovieDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieDtoMapperTest {

    @Test
    void mapToDto() {
        var movie = new MovieTestDataBuilder()
                .genre(Genre.builder().id(23).build())
                .actor(Actor.builder().id(303).build())
                .director(Director.builder().id(42).build())
                .build();
        var dto = MovieDtoMapper.mapToDto(movie);
        assertNotNull(dto.getId());
        assertNotNull(dto.getName());
        assertTrue(dto.getYear() > 0);
        assertTrue(dto.getAgeLimit() > 0);
        assertNotNull(dto.getSynopsis());
        assertTrue(dto.getGenreIds().size() > 0);
        assertTrue((dto.getActorIds().size() > 0));
        assertNotNull(dto.getDirectorId());
    }

    @Test
    void mapTo() {
        var dto = MovieDto.builder()
                .id(1)
                .name("name")
                .rating(2)
                .ageLimit(3)
                .year(2000)
                .synopsis("synopsis")
                .genreIds(Arrays.asList(23, 4))
                .actorIds(Arrays.asList(1, 2))
                .directorId(200)
                .build();

        var movie = MovieDtoMapper.mapTo(dto);
        assertNotNull(movie.getId());
        assertNotNull(movie.getName());
        assertTrue(movie.getRating() > 0);
        assertTrue(movie.getYear() > 0);
        assertTrue(movie.getAgeLimit() > 0);
        assertTrue(movie.getGenres().size() > 0);
        assertNotNull(movie.getDirector());
    }
}