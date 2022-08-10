package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private TreeMap<Customer, String> treeMap = new TreeMap<>();


    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        //rebuildMap();
        Set<Customer> set = treeMap.keySet();
        return new java.util.AbstractMap.SimpleEntry<Customer, String>((Customer) set.toArray()[0], treeMap.get(set.toArray()[0]));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
//        rebuildMap();
        if (treeMap.get(customer) == null) {
            return treeMap.ceilingEntry(customer);
        } else {
            int indexOfCustomer = -1;

            Object[] keys = treeMap.keySet().toArray();
            Object[] values = treeMap.values().toArray();
            Map.Entry<Customer, String> ceilingEntry = treeMap.ceilingEntry(customer);
            Customer ceilingKey = treeMap.ceilingKey(customer);
            for (int i = 0; i < keys.length; i++) {
                if (customer.equals((Customer) keys[i])) {
                    indexOfCustomer = i;
                }
            }

            for (int i = 0; i < keys.length; i++) {
                System.out.println(keys[i]);
                System.out.println(values[i]);
            }
            return new java.util.AbstractMap.SimpleEntry<Customer, String>((Customer) keys[indexOfCustomer + 1], (String) values[indexOfCustomer + 1]);
//            int indexOfMiddle = 0;
//            List<Object> set = List.of(treeMap.keySet().toArray());
//
//
//            for (int i = 0; i < set.size(); i++) {
//                System.out.println(((Customer) set.get(i)));
//
//            }
//
//
//            for (int i = 0; i < set.size(); i++) {
//                if (((Customer) set.get(i)).getScores() > customer.getScores()) {
//                    indexOfMiddle = i;
//                    break;
//                }
//            }
//            return new java.util.AbstractMap.SimpleEntry<>((Customer) set.toArray()[indexOfMiddle], treeMap.get(set.toArray()[indexOfMiddle]));
        }
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }

    private synchronized void rebuildMap() {
        TreeMap<Customer, String> resortedMap = new TreeMap<>();
        for (Map.Entry<Customer, String> entry : treeMap.entrySet()) {
            resortedMap.put(entry.getKey(), entry.getValue());
        }
        treeMap = resortedMap;
    }
}
