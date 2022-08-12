package homework;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {
    Deque<Customer> deque = new ArrayDeque<Customer>();

    public void add(Customer customer) {
        deque.push(customer);
    }

    public Customer take() {
        return deque.pop();
    }

}
