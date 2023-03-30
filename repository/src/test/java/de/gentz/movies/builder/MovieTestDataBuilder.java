package de.gentz.movies.builder;

import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;

public class MovieTestDataBuilder {
    private static Long id;
    private String name = "Rambo 1";
    private final int ageLimit = 12;
    private final int year = 1986;
    private final String synopsis = "lorem ipsum";

    private Genre genre;

    public MovieTestDataBuilder idNull() {
        id = null;
        return this;
    }

    public MovieTestDataBuilder name(String name) {
        this.name = name;
        return this;
    }


    public MovieTestDataBuilder genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Movie build() {
        if (id != null) {
            id++;
        }

        return Movie.builder()
                .id(id)
                .name(name)
                .ageLimit(ageLimit)
                .year(year)
                .synopsis(synopsis)
                .genre(genre)
                .build();
    }
}
