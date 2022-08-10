package homework;

import java.util.Objects;

public class Customer implements Comparable {
    private final long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }


    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        //result = 31 * result + (name != null ? name.hashCode() : 0);
        //result = 31 * result + (int) (scores ^ (scores >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;

        //if (scores != customer.scores) return false;
        //return Objects.equals(name, customer.name);
        return true;
    }

    public static void main(String[] args) {
        Customer customer = new Customer(1, null, 3);
        Customer customer2 = new Customer(1, null, 3);
//        System.out.println("customer.name == null");
//        System.out.println(customer.getName() == null);
//        System.out.println("customer.name.equals(customer2.name)");
//        String customerName1 = customer.getName();
//        String customerName2 = customer2.getName();
//        System.out.println("customerName1");
//        System.out.println(customerName1);
//        System.out.println("customerName2");
//        System.out.println(customerName2);
//        System.out.println("customerName1.equals(customerName2)");
//        System.out.println(Objects.equals(customerName1, customerName2));
//        //System.out.println(customerName1.equals(customerName2));
//        //System.out.println(customer.getName().equals(customer2.getName()));
//        System.out.println("customer.name.equals(customer2.name)");
//


    }

    @Override
    public int compareTo(Object o) {
        Customer customer = (Customer) o;
        return (int) (this.getScores() - customer.getScores());
    }
}
