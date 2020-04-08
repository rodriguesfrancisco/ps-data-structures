package com.company.interfaces;

public interface Queue<T> {
    int size();
    void enQueue(T item);
    T deQueue();
    boolean contains(T iten);
    T access(int position);
}
