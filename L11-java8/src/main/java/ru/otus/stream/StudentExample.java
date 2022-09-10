package ru.otus.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentExample {
    public static void main(String[] args) {
        var alex = Student.builder().name("Alex").avgMark(4.5).build();
        var maria = Student.builder().name("Maria").avgMark(3.5).build();
        var john = Student.builder().name("John").age(12).course(4).avgMark(4.7).build();
        var bob = Student.builder().name("Bob").avgMark(4.8).build();
        var jack = Student.builder().name("Jack").age(23).avgMark(4.5).build();

        var students = List.of(alex, maria, john, bob, jack);
        /*
         * OLD (Java 1.0-1.7) style
         */
        var result = new ArrayList<Student>();
        //напечатать имена топ-студентов 5-го курса с оценкой больше 4, по убыванию
        for (Student student : students) {
            if (student.getAvgMark() > 4 && student.getCourse() == 5 && student.getAge() == 22) {
                result.add(student);
            }
        }
        result.sort((s1, s2) -> Double.compare(s1.getAvgMark(), s2.getAvgMark()));
        for (Student student : result) {
            System.out.println(student);
        }
        /*
         * STREAM (Java1.8+) style
         */
        var stud = students.stream()
                .filter(student -> student.getAvgMark() > 4)
                .filter(student -> student.getCourse() == 5)
                .filter(student -> student.getAge() == 22)
                .sorted(Comparator.comparingDouble(Student::getAvgMark).reversed())
                .map(Student::getName);

       // var studList = stud. (Collectors.toList());
        stud.forEach(System.out::println);
    }
}
