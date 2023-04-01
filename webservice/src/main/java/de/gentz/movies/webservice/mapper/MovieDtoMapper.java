package de.gentz.movies.webservice.mapper;

import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;
import de.gentz.movies.webservice.model.MovieDto;

import java.util.stream.Collectors;

public class MovieDtoMapper {
    public static MovieDto mapToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .rating(movie.getRating())
                .year(movie.getYear())
                .ageLimit(movie.getAgeLimit())
                .synopsis(movie.getSynopsis())
                .genreIds(movie.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()))
                .build();
    }

    public static Movie mapTo(MovieDto movie) {
        return Movie.builder()
                .id(movie.getId())
                .name(movie.getName())
                .rating(movie.getRating())
                .year(movie.getYear())
                .ageLimit(movie.getAgeLimit())
                .synopsis(movie.getSynopsis())
                .genres(movie.getGenreIds().stream().map(g -> Genre.builder().id(g).build()).collect(Collectors.toSet()))
                .build();
    }
}
