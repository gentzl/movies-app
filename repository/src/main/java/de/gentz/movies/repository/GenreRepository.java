package de.gentz.movies.repository;

import de.gentz.movies.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);

    List<Genre> findAllByOrderByNameAsc();
}