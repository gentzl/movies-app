/*
package de.gentz.movies.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.gentz.movies.entity.Movie;
import de.gentz.movies.repository.MovieRepository;
import de.gentz.movies.webservice.builder.MovieTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {
    @MockBean
    MovieRepository movieRepository;

    @Autowired
    private MockMvc mvc;

    Movie movie1 = new MovieTestDataBuilder().name("Rambo 1").build();

    Movie movie2 = new MovieTestDataBuilder().name("Rambo 2").build();

    @Test
    public void getAllMovies_FoundTwo() throws Exception {
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        mvc.perform(MockMvcRequestBuilders
                        .get("/movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].ageLimit").isNotEmpty())
                .andExpect(jsonPath("$[0].rating").isNotEmpty())
                .andExpect(jsonPath("$[0].year").isNotEmpty())
                .andExpect(jsonPath("$[0].synopsis").isNotEmpty());
    }

    @Test
    public void getMovie_Found() throws Exception {
        when(movieRepository.findById(movie1.getId())).thenReturn(Optional.of(movie1));

        mvc.perform(MockMvcRequestBuilders
                        .get("/movies/" + movie1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.ageLimit").isNotEmpty())
                .andExpect(jsonPath("$.rating").isNotEmpty())
                .andExpect(jsonPath("$.year").isNotEmpty())
                .andExpect(jsonPath("$.synopsis").isNotEmpty());
    }

    @Test
    public void getMovie_NotFound() throws Exception {
        when(movieRepository.findById(any())).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .get("/movies/-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createMovie_Valid() throws Exception {
        var movieName = "Titanic";
        var movieToCreate = new MovieTestDataBuilder().idNull().name(movieName).build();
        var createdMovie = new MovieTestDataBuilder().idNull().name(movieName).build();

        when(movieRepository.save(any())).thenReturn(createdMovie);

        mvc.perform(MockMvcRequestBuilders
                        .post("/movies")
                        .content(asJsonString(movieToCreate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        verify(movieRepository).save(any());
    }

    // TODO: test more methods

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}*/
