package homework;


import java.util.*;

public class CustomerService {
    //    private TreeMap<Customer, String> treeMap = new TreeMap<>(new Comparator<Customer>() {
//        @Override
//        public int compare(Customer o1, Customer o2) {
//
//            long compareLong = o1.getScores() - o2.getScores();
//            if (compareLong > 0) {
//                return 1;
//            } else if (compareLong == 0) {
//                return 0;
//            } else {
//                return -1;
//            }
//        }
//    });
    private final NavigableMap<Customer, String> treeMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));



    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallestEntry = treeMap.firstEntry();
        return new AbstractMap.SimpleEntry<>(new Customer(smallestEntry.getKey().getId(), smallestEntry.getKey().getName(), smallestEntry.getKey().getScores()), smallestEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (treeMap.get(customer) == null) {
            Map.Entry<Customer, String> ceilingEntry = treeMap.ceilingEntry(customer);
            if (ceilingEntry != null) {
                return new AbstractMap.SimpleEntry<>(new Customer(ceilingEntry.getKey().getId(), ceilingEntry.getKey().getName(), ceilingEntry.getKey().getScores()), ceilingEntry.getValue());
            } else {
                return null;
            }

        } else {
            Map.Entry<Customer, String> higherEntry = treeMap.higherEntry(customer);
            return new AbstractMap.SimpleEntry<>(new Customer(higherEntry.getKey().getId(), higherEntry.getKey().getName(), higherEntry.getKey().getScores()), higherEntry.getValue());
        }
    }

    public void add(Customer customer, String data) {
        treeMap.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }
}
