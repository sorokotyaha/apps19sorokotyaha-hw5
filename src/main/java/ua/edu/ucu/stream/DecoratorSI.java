package ua.edu.ucu.stream;

import java.util.Iterator;

abstract class DecoratorSI implements Iterator<Integer> {
    protected Iterator<Integer> si;

    public DecoratorSI(Iterator<Integer> si){
        this.si = si;
    }
}
