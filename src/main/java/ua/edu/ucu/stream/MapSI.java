package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapSI extends DecoratorSI {

    private IntUnaryOperator uo;

    public MapSI(Iterator<Integer> si, IntUnaryOperator uo) {
        super(si);
        this.uo = uo;

    }

    @Override
    public boolean hasNext() {
        return this.si.hasNext();
    }


    @Override
    public Integer next() {
        return this.uo.apply(this.si.next());
    }
}

