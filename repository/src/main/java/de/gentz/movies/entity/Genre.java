package de.gentz.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "genres")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Name cannot be null")
    @Length(min = 1, max = 100)
    @Column(unique = true)
    private String name;
}
