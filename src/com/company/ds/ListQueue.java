package com.company.ds;

import com.company.interfaces.Queue;

import java.util.ArrayList;

public class ListQueue<T> implements Queue<T> {

    private ArrayList<T> data;
    private int front;
    private int end;

    public ListQueue() {
        data = new ArrayList<>();
        this.front = -1;
        this.end = -1;
    }

    public int size() {
        if(this.front == -1 && this.end == -1)
            return 0;
        else
            return end - front + 1;
    }

    public void enQueue(T item) {
        this.data.add(++this.end, item);
        if(this.front == -1)
            this.front++;
    }

    public T deQueue() {
        if(this.front <= this.end)
            return data.get(this.front++);

        throw new IllegalStateException("Queue is empty");
    }

    public boolean contains(T item) {
        if(front <= end) {
            if(data.contains(item))
                return true;
        }

        return false;
    }

    public T access(int position) {
        if(size() == 0 || position > size())
            throw new IllegalArgumentException("Cant access the item on the position " + position);

        int trueIndex = 0;

        for(int i = front; i < end; i++) {
            if(trueIndex == position)
                return data.get(i);

            trueIndex++;
        }

        throw new IllegalArgumentException("Cant access the item on the position " + position);
    }
}
