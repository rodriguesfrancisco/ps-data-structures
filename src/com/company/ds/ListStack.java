package com.company.ds;

import com.company.interfaces.Stack;

import java.util.ArrayList;

public class ListStack<T> implements Stack<T> {
    private ArrayList<T> data;
    private int stackPointer;

    public ListStack() {
        data = new ArrayList<>();
        stackPointer = 0;
    }

    public void push(T newItem) {
        data.add(newItem);
        stackPointer++;
    }

    public T pop() {
        if(stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }
        return data.get(--stackPointer);
    }

    public boolean contains(T item) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).equals(item))
                return true;
        }

        return false;
    }

    public T access(T item) {
        while(stackPointer > 0) {
            T tmpItem = pop();
            if(tmpItem.equals(item))
                return tmpItem;
        }

        throw new IllegalArgumentException("Could not find the item on the Stack");
    }

    public int size() {
        return stackPointer;
    }
}
