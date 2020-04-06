package com.company.interfaces;

public interface Stack<T> {
    void push(T newItem);
    T pop();
    boolean contains(T item);
    T access(T item);
    int size();
}
