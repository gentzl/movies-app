package de.gentz.movies.webservice.builder;

import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;

import java.util.HashSet;
import java.util.Set;

public class MovieTestDataBuilder {
    private volatile Integer id = 1;

    private String name = "Rambo 1";
    private final int ageLimit = 12;
    private final int year = 1986;
    private final String synopsis = "lorem ipsum";

    private final Set<Genre> genres = new HashSet<>();

    public MovieTestDataBuilder idNull() {
        id = null;
        return this;
    }

    public MovieTestDataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MovieTestDataBuilder genre(Genre genre) {
        genres.add(genre);
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
                .genres(genres)
                .build();
    }
}
