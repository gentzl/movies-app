package de.gentz.movies.webservice.model.importer;

import de.gentz.movies.entity.Movie;

public class ImportMovieMapper {
    public static Movie map(ImportMovie movie) {
        return Movie.builder()
                .name(movie.getName())
                .name(movie.getName())
                .rating(movie.getRating())
                .year(movie.getYear())
                .ageLimit(movie.getAgeLimit())
                .synopsis(movie.getSynopsis())
                .build();
    }
}
