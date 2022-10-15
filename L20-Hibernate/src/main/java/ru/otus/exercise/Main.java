package ru.otus.exercise;

import ru.otus.core.HibernateUtils;
import ru.otus.exercise.model.Avatar;
import ru.otus.exercise.model.Course;
import ru.otus.exercise.model.EMail;
import ru.otus.exercise.model.OtusStudent;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class);
    }
}
