package ru.otus.cachehw;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    //Надо реализовать эти методы
    //обычные/weak/soft/soft+phantom референсы на listener-ы
    private final Map<K, V> cache = new WeakHashMap<>();

    private Set<HwListener<K, V>> listeners = new HashSet<>();

    @Override
    public void put(K key, V value) {

        notifyListeners(key, value, "put");
        cache.put(key, value);
    }

    public void notifyListeners(K key, V value, String action) {
        for (HwListener<K, V> listener : listeners) {
            try {
                listener.notify(key, value, "action");//
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public void remove(K key) {
        notifyListeners(key, null, "remove");
        cache.remove(key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
