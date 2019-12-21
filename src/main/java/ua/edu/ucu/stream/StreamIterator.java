package ua.edu.ucu.stream;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private int[] ints;
    private int curr = 0;

    public StreamIterator(int[] ints) {
        this.ints = ints;
    }

    @Override
    public boolean hasNext() {
        return this.curr < this.ints.length;
    }

    @Override
    public Integer next() {
        return this.ints[curr++];
    }

}
