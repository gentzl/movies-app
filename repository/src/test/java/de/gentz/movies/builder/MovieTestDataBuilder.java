package de.gentz.movies.builder;

import de.gentz.movies.entity.Movie;

public class MovieTestDataBuilder {
    private static Long id = 1L;
    private String name = "Rambo 1";
    private int ageLimit = 12;
    private int year = 1986;
    private String synopsis = "lorem ipsum";

    public MovieTestDataBuilder idNull() {
        id = null;
        return this;
    }

    public MovieTestDataBuilder name(String name) {
        this.name = name;
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
                .build();
    }
}
