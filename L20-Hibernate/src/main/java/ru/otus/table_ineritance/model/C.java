package ru.otus.table_ineritance.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "C")
@DiscriminatorValue("CLeaf")
public class C extends A{
    private String c;

    public C(long id, String a, String c) {
        super(id, a);
        this.c = c;
    }

    @Override
    public String toString() {
        return "C{" +
                "id=" + id +
                ", a='" + a + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
