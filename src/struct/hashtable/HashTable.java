package struct.hashtable;

import struct.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements Iterable<KeyValue<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75d;
    private LinkedList<KeyValue<K, V>>[] slots;
    private int count;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.count = 0;
    }

    public void add(K key, V value) {
        growIfNeeded();
        int slotNumber = findSlotNumber(key);

        if (slots[slotNumber] == null) {
            slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<K, V> kv : slots[slotNumber]) {
            if (kv.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }

        slots[slotNumber].add(new KeyValue<>(key, value, count));
        count++;
    }

    private int findSlotNumber(K key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void growIfNeeded() {
        if ((double) (this.size() + 1) / this.slots.length > LOAD_FACTOR) {
            this.grow();
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = slots.length * 2;
        LinkedList<KeyValue<K, V>>[] newTable = new LinkedList[newCapacity];

        for (LinkedList<KeyValue<K, V>> list : slots) {
            if (list != null) {
                for (KeyValue<K, V> entry : list) {
                    int newSlot = Math.abs(entry.getKey().hashCode()) % newCapacity;
                    if (newTable[newSlot] == null) {
                        newTable[newSlot] = new LinkedList<>();
                    }
                    newTable[newSlot].add(entry);
                }
            }
        }
        this.slots = newTable;
    }

    public int size() {
        return this.count;
    }

    public int capacity() {
        return this.slots.length;
    }

    public boolean addOrReplace(K key, V value) {
        growIfNeeded();
        int slotNumber = findSlotNumber(key);

        if (slots[slotNumber] == null) {
            slots[slotNumber] = new LinkedList<>();
        }

        for (KeyValue<K, V> kv : slots[slotNumber]) {
            if (kv.getKey().equals(key)) {
                kv.setValue(value);
                return false;
            }
        }

        slots[slotNumber].add(new KeyValue<>(key, value, count));
        count++;
        return true;
    }

    public V get(K key) {
        KeyValue<K, V> kv = this.find(key);
        if (kv == null) {
            return null;
        }
        return kv.getValue();
    }

    public KeyValue<K, V> find(K key) {
        int slotNumber = findSlotNumber(key);
        if (slots[slotNumber] != null) {
            for (KeyValue<K, V> kv : slots[slotNumber]) {
                if (kv.getKey().equals(key)) {
                    return kv;
                }
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return find(key) != null;
    }

    public boolean containsValue(V value) {
        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kv : slot) {
                    if (kv.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getOrder(K key) {
        return find(key).getOrder();
    }

    public boolean remove(K key) {
        int slotNumber = findSlotNumber(key);
        if (slots[slotNumber] != null) {
            Iterator<KeyValue<K, V>> iterator = slots[slotNumber].iterator();
            while (iterator.hasNext()) {
                KeyValue<K, V> kv = iterator.next();
                if (kv.getKey().equals(key)) {
                    iterator.remove();
                    count--;
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; i++) {
            this.slots[i] = null;
        }
        this.count = 0;
    }

    public Iterable<K> keys() {
        LinkedList<K> keysList = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kv : slot) {
                    keysList.add(kv.getKey());
                }
            }
        }
        return keysList;
    }

    public Iterable<V> values() {
        LinkedList<V> valuesList = new LinkedList<>();
        for (LinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kv : slot) {
                    valuesList.add(kv.getValue());
                }
            }
        }
        return valuesList;
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<KeyValue<K, V>> {
        private Iterator<KeyValue<K, V>> currentIterator;
        private int currentSlot = 0;

        @Override
        public boolean hasNext() {
            if (currentIterator != null && currentIterator.hasNext()) {
                return true;
            }

            while (currentSlot < slots.length) {
                if (slots[currentSlot] != null) {
                    currentIterator = slots[currentSlot].iterator();
                    if (currentIterator.hasNext()) {
                        currentSlot++;
                        return true;
                    }
                }
                currentSlot++;
            }

            return false;
        }

        @Override
        public KeyValue<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the hash table");
            }
            return currentIterator.next();
        }

    }
}
