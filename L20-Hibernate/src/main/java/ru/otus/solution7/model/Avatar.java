package ru.otus.solution7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToOne(mappedBy = "avatar", cascade = CascadeType.ALL)
    private OtusStudent student;
}
