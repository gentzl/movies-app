package de.gentz.movies.webservice.builder;

import de.gentz.movies.entity.Director;

public class DirectorTestDataBuilder {
    private Integer id;

    public Director build() {
        if (id != null) {
            id++;
        }

        return Director.builder()
                .id(id)
                .firstname("Steven")
                .lastname("Spielberg")
                .build();
    }
}
