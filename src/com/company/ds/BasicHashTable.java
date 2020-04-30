package com.company.ds;

public class BasicHashTable<K, V> {
    private HashEntry[] data;
    private int capacity;
    private int size;

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        this.data = new HashEntry[this.capacity];
        this.size = 0;
    }

    public V get(K key) {
        int hash = calculateHash(key);

        if(data[hash] == null) {
            return null;
        } else {
            return (V)data[hash].getValue();
        }
    }

    public void put(K key, V value) {
        int hash = calculateHash(key);

        data[hash] = new HashEntry<K, V>(key, value);
        size++;
    }

    public V delete(K key) {
        V value = get(key);

        if(value != null) {
            int hash = calculateHash(key);
            data[hash] = null;
            size--;
            hash = (hash + 1) % this.capacity;

            while(data[hash] != null) {
                HashEntry he = data[hash];
                data[hash] = null;
                put((K)he.getKey(), (V)he.getValue());
                size--;
                hash = (hash + 1) % this.capacity;
            }
        }

        return value;
    }

    public boolean hasKey(K key) {
        int hash = calculateHash(key);

        if(data[hash] == null) {
            return false;
        } else {
            if(data[hash].getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasValue(V value) {
        for(int i = 0; i < this.capacity; i++) {
            HashEntry entry = data[i];

            if(entry != null && entry.getValue().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    private int calculateHash(K key) {
        int hash = key.hashCode() % this.capacity;
        while(data[hash] != null && data[hash].getKey().equals(key)) {
            hash = (hash + 1) % this.capacity;
        }

        return hash;
    }

    private class HashEntry<K, V> {
        private K key;
        private V value;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
