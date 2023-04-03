package de.gentz.movies.repository;

import de.gentz.movies.builder.DirectorTestDataBuilder;
import de.gentz.movies.entity.Director;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
@ContextConfiguration(classes = {DirectorRepository.class})
@DataJpaTest
@EntityScan("de.gentz.movies.entity")
public class DirectorRepositoryTest {

    public static final Director DIRECTOR = new DirectorTestDataBuilder().build();

    @Autowired
    DirectorRepository directorRepository;

    @Test
    public void findByName_Found() {
        directorRepository.save(DIRECTOR);
        var foundActor = directorRepository.getByFirstnameAndLastname(DIRECTOR.getFirstname(), DIRECTOR.getLastname());
        assertNotNull(foundActor);
        assertEquals(DIRECTOR.getFirstname(), foundActor.getFirstname());
        assertEquals(DIRECTOR.getLastname(), foundActor.getLastname());
    }

    @Test
    public void findByName_NotFound() {
        directorRepository.save(DIRECTOR);
        var foundActor = directorRepository.getByFirstnameAndLastname("Non existing firstname", DIRECTOR.getLastname());
        assertNull(foundActor);
    }
}