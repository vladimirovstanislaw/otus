package ru.otus.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="EMail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMail {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name="email")
    private String email;
}
