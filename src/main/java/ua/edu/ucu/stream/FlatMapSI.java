package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntToIntStreamFunction;

import java.util.Iterator;

public class FlatMapSI extends DecoratorSI {
    private StreamIterator current;

    private IntToIntStreamFunction isf;

    public FlatMapSI(Iterator<Integer> si, IntToIntStreamFunction isf) {
        super(si);
        this.isf = isf;
        //this.current = new StreamIterator(new int[] {});
        if (this.si.hasNext()) {
            this.current = (StreamIterator) ((AsIntStream) this.isf.applyAsIntStream(this.si.next())).getIter();
        } else {
            this.current = new StreamIterator(new int[]{});
        }

    }


    @Override
    public boolean hasNext() {
        return this.si.hasNext() || this.current.hasNext();
    }


    @Override
    public Integer next() {
        if (!this.current.hasNext()) {
            this.current = (StreamIterator) ((AsIntStream) this.isf.applyAsIntStream(this.si.next())).getIter();
        }
        return this.current.next();

    }


}
