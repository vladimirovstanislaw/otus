package ru.otus.solution3;

import ru.otus.core.HibernateUtils;
import ru.otus.solution3.model.Avatar;
import ru.otus.solution3.model.Course;
import ru.otus.solution3.model.EMail;
import ru.otus.solution3.model.OtusStudent;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class);
    }
}
