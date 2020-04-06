package com.company.ds;

public class BasicQueue<T> {
    private T[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(1000);
    }

    public BasicQueue(int size) {
        data = (T[]) new Object[size];
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
        this.data[++this.end] = item;
        if(this.front == -1)
            this.front++;
    }

    public T deQueue() {
        if(this.front <= this.end)
            return data[this.front++];

        throw new IllegalStateException("Queue is empty!");
    }

    public boolean contains(T item) {
        if(front <= end) {
            for(int i = front; i < end; i++) {
                if(data[i].equals(item)) {
                    return true;
                }
            }

            return false;
        }

        throw new IllegalArgumentException("This queue dont contain the item: " + item);
    }

    public T access(int position) {
        if(size() == 0 || position > size())
            throw new IllegalArgumentException("Cant access the item on the position " + position);

        int trueIndex = 0;

        for(int i = front; i < end; i++) {
            if(trueIndex == position)
                return data[i];

            trueIndex++;
        }

        throw new IllegalArgumentException("Cant access the item on the position " + position);
    }
}
