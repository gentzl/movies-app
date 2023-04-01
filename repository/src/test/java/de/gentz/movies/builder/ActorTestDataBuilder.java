package de.gentz.movies.builder;

import de.gentz.movies.entity.Actor;

public class ActorTestDataBuilder {
    private volatile Integer id;
    private final String firstname = "John";

    private final String lastname = "Travolta";

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
