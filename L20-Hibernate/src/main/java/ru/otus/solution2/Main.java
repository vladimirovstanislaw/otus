package ru.otus.solution2;

import ru.otus.core.HibernateUtils;
import ru.otus.solution2.model.Avatar;
import ru.otus.solution2.model.Course;
import ru.otus.solution2.model.EMail;
import ru.otus.solution2.model.OtusStudent;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.buildSessionFactory(OtusStudent.class, Avatar.class, EMail.class, Course.class);
    }
}
