package ru.otus.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "OtusStudent")
@NoArgsConstructor
@AllArgsConstructor
public class OtusStudent {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "student")//���������, ��� ���������� ����� �������� Avatar � ����� "student"
    // @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;
    @ManyToMany
    @JoinTable(name = "studentsUndCourses", joinColumns = @JoinColumn(name = "student_id")//�� ������� ��������
            , inverseJoinColumns = @JoinColumn(name = "course_id"))//�� ������� �����
    private List<Course> courses;
}
