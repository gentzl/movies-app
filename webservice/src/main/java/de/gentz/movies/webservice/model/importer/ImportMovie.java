package de.gentz.movies.webservice.model.importer;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an external movie format.
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportMovie {

    private Integer id;

    private String name;

    private int year;

    @ToString.Exclude
    private int ageLimit;

    @ToString.Exclude

    private int rating;

    @ToString.Exclude
    private String synopsis;

    @ToString.Exclude
    private Set<String> genres = new HashSet<>();
    @ToString.Exclude
    private Set<Person> actors = new HashSet<>();

    private Person director;
}
