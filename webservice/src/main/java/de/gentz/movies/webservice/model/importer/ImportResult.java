package de.gentz.movies.webservice.model.importer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
@NoArgsConstructor
@Data
public class ImportResult {
    private int totalMovies;
    private int importedMovies;
    private int ignoredMovies;
    private int failedMovies;

    public void increaseImported() {
        importedMovies++;
    }

    public void increaseFailed() {
        failedMovies++;
    }

    public void increaseIgnored() {
        ignoredMovies++;
    }
}
