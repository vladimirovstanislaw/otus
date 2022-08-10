package homework;


import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {
    Deque<Customer> deque = new LinkedList<>();

    public void add(Customer customer) {
        deque.addLast(customer);
    }

    public Customer take() {
        return deque.pollLast();
    }
}
