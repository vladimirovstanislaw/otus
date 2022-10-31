package ru.otus.crm.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Client")
@Table(name = "Client")
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "client",
            cascade = CascadeType.ALL

    )
    private List<Phone> phones = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {

        this.id = id;
        this.name = name;
        this.address = address;
        for (Phone ph : phones) {
            //ph.setClient(this);
            this.phones.add(new Phone(ph.getId(), ph.getNumber(), returnThis()));
        }

    }

    public Client returnThis() {
        return this;
    }

    @Override
    public Client clone() {
        Client client = new Client();
        client.setId(this.id);
        client.setName(this.name);
        client.setAddress(new Address(this.address.getId(), this.address.getStreet()));
        List<Phone> newListOfPhones = new ArrayList<Phone>();
        this.phones.stream().forEach(v -> {
            v.setClient(client);
            newListOfPhones.add(v);
        });
        client.setPhones(newListOfPhones);
        return client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setClient(this);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setClient(null);
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        return id != null && id.equals(((Client) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
