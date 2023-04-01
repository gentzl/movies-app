package de.gentz.movies.webservice.service;

import de.gentz.movies.entity.Actor;
import de.gentz.movies.entity.Genre;
import de.gentz.movies.repository.ActorRepository;
import de.gentz.movies.repository.GenreRepository;
import de.gentz.movies.repository.MovieRepository;
import de.gentz.movies.webservice.model.importer.ImportMovie;
import de.gentz.movies.webservice.model.importer.ImportMovieMapper;
import de.gentz.movies.webservice.model.importer.ImportResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for importing movies from an external format.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class MovieImporter {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public ImportResult importMovies(List<ImportMovie> movies) {
        var importResult = new ImportResult();
        importResult.setTotalMovies(movies.size());
        log.info("starting import for '{}' movies", movies.size());
        movies.forEach(movie -> importMovie(importResult, movie));

        log.info("import completed: {}", importResult);
        return importResult;
    }

    private void importMovie(ImportResult importResult, ImportMovie importMovie) {
        try {
            // TODO: multiple movies with same name can exist ... only a workaround for presentation to prevent redundant imports
            var movie = movieRepository.getByName(importMovie.getName());
            var createMovie = false;
            if (movie != null) {
                importResult.increaseIgnored();
                log.info("movie already exists: {}", importMovie);
            } else {
                movie = ImportMovieMapper.map(importMovie);
                createMovie = true;
            }

            if (importMovie.getGenres() != null) {
                var genres = importMovie.getGenres().stream().map(this::getOrCreateGenre).collect(Collectors.toSet());
                movie.getGenres().addAll(genres);
            }
            if (importMovie.getActors() != null) {
                var actors = importMovie.getActors().stream().map(a -> getOrCreateActor(a.getFirstName(), a.getLastName())).collect(Collectors.toSet());
                movie.getActors().addAll(actors);
            }

            movieRepository.save(movie);

            if (createMovie) {
                importResult.increaseImported();
            }

            log.info("successfully imported movie: {}", importMovie);
        } catch (Exception e) {
            log.info(String.format("error importing movie: %s", importMovie), e);
            importResult.increaseFailed();
        }
    }

    private Genre getOrCreateGenre(String genreName) {
        var genre = genreRepository.findByName(genreName);
        if (genre != null) {
            return genre;
        }

        var genreToCreate = Genre.builder()
                .name(genreName)
                .build();
        log.info("creating genre; {}", genreName);
        return genreRepository.save(genreToCreate);
    }

    private Actor getOrCreateActor(String firstname, String lastName) {
        var actor = actorRepository.getByFirstnameAndLastname(firstname, lastName);
        if (actor != null) {
            return actor;
        }

        var actorToCreate = Actor.builder()
                .firstname(firstname)
                .lastname(lastName)
                .build();

        log.info("creating actor; {} {}", firstname, lastName);
        return actorRepository.save(actorToCreate);
    }
}
