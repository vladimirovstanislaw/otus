package homework;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    Deque<Customer> deque = new LinkedList<>();

    public void add(Customer customer) {
        deque.add(customer);
    }

    public Customer take() {
        return deque.getLast(); // это "заглушка, чтобы скомилировать"
    }
}
