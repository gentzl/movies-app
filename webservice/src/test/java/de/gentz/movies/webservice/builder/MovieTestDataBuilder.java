package de.gentz.movies.webservice.builder;

import de.gentz.movies.entity.Actor;
import de.gentz.movies.entity.Director;
import de.gentz.movies.entity.Genre;
import de.gentz.movies.entity.Movie;

import java.util.HashSet;
import java.util.Set;

public class MovieTestDataBuilder {
    private static Integer id = 1;

    private String name = "Rambo 1";
    private final int ageLimit = 12;
    private final int year = 1986;
    private final String synopsis = "lorem ipsum";

    private final Set<Genre> genres = new HashSet<>();

    private final Set<Actor> actors = new HashSet<>();

    private Director director;

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

    public MovieTestDataBuilder actor(Actor actor) {
        actors.add(actor);
        return this;
    }

    public MovieTestDataBuilder director(Director director) {
        this.director = director;
        return this;
    }

    public synchronized Movie build() {
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
                .actors(actors)
                .director(director)
                .build();
    }
}
