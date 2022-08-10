package homework;


import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    private TreeMap<Customer, String> treeMap = new TreeMap<>();


    public Map.Entry<Customer, String> getSmallest() {
        Object[] keys = treeMap.keySet().toArray();
        return new AbstractMap.SimpleEntry<>(new Customer(((Customer) keys[0]).getId(), ((Customer) keys[0]).getName(), ((Customer) keys[0]).getScores()), treeMap.get(keys[0]));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (treeMap.get(customer) == null) {
            Map.Entry<Customer, String> ceilingEntry = treeMap.ceilingEntry(customer);
            if (ceilingEntry != null) {
                return new AbstractMap.SimpleEntry<>(new Customer(treeMap.ceilingEntry(customer).getKey().getId(), treeMap.ceilingEntry(customer).getKey().getName(), treeMap.ceilingEntry(customer).getKey().getScores()), treeMap.ceilingEntry(customer).getValue());
            } else {
                return null;
            }

        } else {
            int indexOfCustomer = -1;

            Object[] keys = treeMap.keySet().toArray();
            Object[] values = treeMap.values().toArray();
            Map.Entry<Customer, String> ceilingEntry = treeMap.ceilingEntry(customer);
            Customer ceilingKey = treeMap.ceilingKey(customer);
            for (int i = 0; i < keys.length; i++) {
                if (customer.equals(keys[i])) {
                    indexOfCustomer = i;
                }
            }

            for (int i = 0; i < keys.length; i++) {
                System.out.println(keys[i]);
                System.out.println(values[i]);
            }
            return new AbstractMap.SimpleEntry<>(new Customer(((Customer) keys[indexOfCustomer + 1]).getId(), ((Customer) keys[indexOfCustomer + 1]).getName(), ((Customer) keys[indexOfCustomer + 1]).getScores()), (String) values[indexOfCustomer + 1]);

        }
    }

    public void add(Customer customer, String data) {
        treeMap.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }
}
