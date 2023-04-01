package de.gentz.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "actors")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue
    private Integer id;

    @NotNull(message = "Firstname cannot be null")
    @Length(min = 1, max = 100)
    private String firstname;

    @NotNull(message = "Lastname cannot be null")
    @Length(min = 1, max = 100)
    private String lastname;
}
