package de.gentz.movies.repository;

import de.gentz.movies.builder.ActorTestDataBuilder;
import de.gentz.movies.entity.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoConfiguration
@ContextConfiguration(classes = {ActorRepository.class})
@DataJpaTest
@EntityScan("de.gentz.movies.entity")
public class ActorRepositoryTest {

    public static final Actor ACTOR = new ActorTestDataBuilder().build();

    @Autowired
    ActorRepository actorRepository;

    @Test
    public void findByName_Found() {
        actorRepository.save(ACTOR);
        var foundActor = actorRepository.getByFirstnameAndLastname(ACTOR.getFirstname(), ACTOR.getLastname());
        assertNotNull(foundActor);
        assertEquals(ACTOR.getFirstname(), foundActor.getFirstname());
        assertEquals(ACTOR.getLastname(), foundActor.getLastname());
    }
}