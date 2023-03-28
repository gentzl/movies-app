package de.gentz.movies.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "movies")
@Data
@ToString
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Name cannot be null")
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

    private String synopsis;

    /*Movies

    Genres

    Actors

            Directors*/
}
