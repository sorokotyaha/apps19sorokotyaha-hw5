package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterSI extends DecoratorSI {

    private IntPredicate pr;
    private Integer current;
    private int temp;

    public FilterSI(Iterator<Integer> si, IntPredicate pr) {
        super(si);
        this.pr = pr;
        this.current = this.getNext();

    }

    @Override
    public boolean hasNext() {
        return !(current == null);
    }

    private Integer getNext() {
        if (this.si.hasNext()) {
            int temp = this.si.next();
            while (!this.pr.test(temp)) {
                if (!this.si.hasNext()) {
                    return null;
                }
                temp = this.si.next();
            }
            return temp;
        } else {
            return null;
        }

    }

    @Override
    public Integer next() {
        int next = this.current;
        this.current = this.getNext();
        return next;
    }
}
