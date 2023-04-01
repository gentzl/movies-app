package de.gentz.movies.webservice.mapper;

import de.gentz.movies.entity.Actor;
import de.gentz.movies.entity.Director;
import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;
import de.gentz.movies.webservice.model.MovieDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

// TODO: test
public class MovieDtoMapper {
    public static MovieDto mapToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .rating(movie.getRating())
                .year(movie.getYear())
                .ageLimit(movie.getAgeLimit())
                .synopsis(movie.getSynopsis())
                .genreIds(movie.getGenres() != null ? movie.getGenres().stream().map(Genre::getId).collect(Collectors.toList()) : new ArrayList<>())
                .actorIds(movie.getActors() != null ? movie.getActors().stream().map(Actor::getId).collect(Collectors.toList()) : new ArrayList<>())
                .directorId(movie.getDirector() != null ? movie.getDirector().getId() : null)
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
                .genres(movie.getGenreIds() != null ? movie.getGenreIds().stream().map(g -> Genre.builder().id(g).build()).collect(Collectors.toSet()) : new HashSet<>())
                .actors(movie.getActorIds() != null ? movie.getActorIds().stream().map(a -> Actor.builder().id(a).build()).collect(Collectors.toSet()) : new HashSet<>())
                .director(movie.getDirectorId() != null ? Director.builder().id(movie.getDirectorId()).build() : null)
                .build();
    }
}
