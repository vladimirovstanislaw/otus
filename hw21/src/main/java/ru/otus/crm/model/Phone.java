package ru.otus.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

//    @ManyToOne
//    // @Column(name = "client_id")
//    @SQLInsert(sql = "INSERT INTO Phone (number,client_id) values (?,?)")
//
//    private Client client;

    //отлично работает без ссылки на Client, но есть апдейты
//    public Phone(Long id, String number) {
//        this.id = id;
//        this.number = number;
//    }


}
