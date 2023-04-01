package de.gentz.movies.repository;
import de.gentz.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByNameContainingIgnoreCase(String name);

    Movie getByName(String name);
}