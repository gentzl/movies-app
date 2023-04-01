package de.gentz.movies.webservice.builder;

import de.gentz.movies.entity.Genre;

public class GenreTestDataBuilder {
    private volatile Integer id;
    private String name = "Action";

    public GenreTestDataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Genre build() {
        if (id != null) {
            id++;
        }

        return Genre.builder()
                .id(id)
                .name(name)
                .build();
    }
}
