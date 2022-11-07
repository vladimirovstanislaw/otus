package ru.otus.cachehw;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    //Надо реализовать эти методы
    //обычные/weak/soft/soft+phantom референсы на listener-ы
    private Map<K, V> cache = new WeakHashMap<>();

    private Set<HwListener<K, V>> listeners = new HashSet<>();

    @Override
    public void put(K key, V value) {

        for (HwListener<K, V> listener : listeners) {
            listener.notify(key, value, "action");//
        }
        cache.put(key, value);
    }

    @Override
    public void remove(K key) {
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
