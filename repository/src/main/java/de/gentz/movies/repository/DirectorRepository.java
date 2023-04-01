package de.gentz.movies.repository;

import de.gentz.movies.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Actor, Long> {
}