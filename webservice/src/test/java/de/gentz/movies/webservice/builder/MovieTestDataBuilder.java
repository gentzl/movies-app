package de.gentz.movies.webservice.builder;

import de.gentz.movies.entity.Movie;

public class MovieTestDataBuilder {
    private Long id = 1L;
    private String name = "Rambo 1";
    private int ageLimit = 12;
    private int year = 1986;
    private String synopsis = "lorem ipsum";

    public MovieTestDataBuilder idNull() {
        this.id = null;
        return this;
    }

    public MovieTestDataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Movie build() {
        return Movie.builder()
                .id(id != null ? id++ : null)
                .name(name)
                .ageLimit(ageLimit)
                .year(year)
                .synopsis(synopsis)
                .build();
    }
}
