package de.gentz.movies.builder;

import de.gentz.movies.entity.Actor;

public class DirectorTestDataBuilder {
    private volatile Integer id;
    private final String firstname = "Steven";

    private final String lastname = "Spielberg";

    public Actor build() {
        if (id != null) {
            id++;
        }

        return Actor.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }
}
