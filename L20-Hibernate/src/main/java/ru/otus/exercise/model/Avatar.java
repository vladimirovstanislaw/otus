package ru.otus.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@Table(name="AvatarJoka")
@AllArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name="photoUrl")
    private String photoUrl;

    @OneToOne
    private OtusStudent student;
}
