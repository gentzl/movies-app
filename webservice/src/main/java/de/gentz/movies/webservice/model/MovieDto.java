package de.gentz.movies.webservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer id;

    @NotNull(message = "Name cannot be null")
    @Length(min = 1, max = 100)
    private String name;

    @Min(value = 1900, message = "Year should not be less than 1900")
    @Max(value = 2023, message = "Year should not be greater than 2023")
    private int year;

    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int ageLimit;

    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private int rating;

    @Length(max = 500)
    private String synopsis;

    @NotNull(message = "Genre ids cannot be null")
    @NotEmpty(message = "Genre must not be empty ")
    private List<Integer> genreIds;

    private List<Integer> actorIds;

    @NotNull(message = "Director id cannot be null")
    private Integer directorId;
}
