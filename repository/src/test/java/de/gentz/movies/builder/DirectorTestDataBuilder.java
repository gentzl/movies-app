package de.gentz.movies.builder;

import de.gentz.movies.entity.Director;

public class DirectorTestDataBuilder {
    private static Integer id;
    private final String firstname = "Steven";

    private final String lastname = "Spielberg";

    public synchronized Director build() {
        if (id != null) {
            id++;
        }

        return Director.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }
}
