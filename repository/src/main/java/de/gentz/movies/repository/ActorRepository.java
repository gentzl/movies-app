package de.gentz.movies.repository;

import de.gentz.movies.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor getByFirstnameAndLastname(String firstname, String lastname);

    List<Actor> findAllByOrderByLastnameAsc();
}