package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iter;

    private AsIntStream(int[] ints) {
        this.iter = new StreamIterator(ints);
    }

    private AsIntStream(Iterator<Integer> iter) {
        this.iter = iter;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }


    @Override
    public Double average() {
        if (!this.iter.hasNext()) {
            throw new IllegalArgumentException();
        }

        int count = 0;
        int summ = 0;
        for (int num : this.getIterable()) {
            summ += num;
            count += 1;
        }

        return (double) summ / count;

    }

    @Override
    public Integer max() {

        if (!this.iter.hasNext()) {
            throw new IllegalArgumentException();
        }
        Integer max = Integer.MIN_VALUE;

//        for (int num: this.getIterable() ) {
//            if (num > max){
//                max = num;
//            }
//        }
//        return max;
        return this.reduce(max, Math::max);
    }

    @Override
    public Integer min() {
        if (!this.iter.hasNext()) {
            throw new IllegalArgumentException();
        }
        Integer min = Integer.MAX_VALUE;

//        for (int num: this.getIterable() ) {
//            if (num < min){
//                min = num;
//            }
//        }
//       return min;

        return this.reduce(min, Math::min);
    }

    @Override
    public long count() {
//        int count = 0;
//        for (int num: this.getIterable() ) {
//            count += 1;
//        }
//        return count;

        return this.reduce(0, (x, y) -> x + 1);

    }

    @Override
    public Integer sum() {
        if (!this.iter.hasNext()) {
            throw new IllegalArgumentException();
        }

//        int summ = 0;
//        for (int num: this.getIterable() ) {
//            summ += num;
//        }
//      return summ;

        return this.reduce(0, Integer::sum);
    }


    private Iterable<Integer> getIterable() {
        Iterator<Integer> it = this.iter;
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return it;
            }
        };
    }


    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterSI(this.iter, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int num : this.getIterable()) {
            action.accept(num);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapSI(this.iter, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapSI(this.iter, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int num : this.getIterable()) {
            result = op.apply(result, num);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int num : this.getIterable()) {
            arr.add(num);
        }
        int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;

    }

    public Iterator<Integer> getIter() {
        return this.iter;
    }


}
