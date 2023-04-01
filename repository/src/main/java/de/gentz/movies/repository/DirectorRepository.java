package de.gentz.movies.repository;

import de.gentz.movies.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Director getByFirstnameAndLastname(String firstname, String lastname);

    List<Director> findAllByOrderByLastname();
}