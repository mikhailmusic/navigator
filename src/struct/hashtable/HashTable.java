package struct.hashtable;


import java.util.Iterator;

public class HashTable<T> implements Iterable<KeyValue<Integer, T>> {
    private final HashTableKV<Integer, T> integerTHashTable = new HashTableKV<>();

    @Override
    public Iterator<KeyValue<Integer, T>> iterator() {
        return integerTHashTable.iterator();
    }

    public void add(T value) {
        integerTHashTable.add(value.hashCode(), value);
    }

    public Integer getOrder(Integer hashCode) {
        return integerTHashTable.find(hashCode).getOrder();
    }

    public boolean addOrReplace(T value) {
        return integerTHashTable.addOrReplace(value.hashCode(), value);
    }

    public T get(Integer hashCode) {
        return integerTHashTable.get(hashCode);
    }

    public KeyValue<Integer, T> find(Integer hashCode) {
        return integerTHashTable.find(hashCode);
    }

    public boolean remove(Integer hashCode) {
        return integerTHashTable.remove(hashCode);
    }

    public void clear() {
        integerTHashTable.clear();
    }

    public Iterable<T> values() {
        return integerTHashTable.values();
    }

    public boolean containsKey(Integer hashCode) {
        return integerTHashTable.containsKey(hashCode);
    }

    public boolean contains(T value) {
        for(T r : integerTHashTable.values()) {
            if (value.equals(r)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return integerTHashTable.size();
    }

    public int capacity() {
        return integerTHashTable.capacity();
    }
}
