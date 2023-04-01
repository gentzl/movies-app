package de.gentz.movies.builder;

import de.gentz.movies.entity.Genre;

public class GenreTestDataBuilder {
    private static Integer id;
    private String name = "Action";

    public GenreTestDataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public synchronized Genre build() {
        if (id != null) {
            id++;
        }

        return Genre.builder()
                .id(id)
                .name(name)
                .build();
    }
}
