package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntConsumer;

import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;

public class Main {
    public static void main(String[] args) {

        System.out.println(AsIntStream.of(-1, 0, 1, 2, 3).max());

        System.out.println(AsIntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(10, (x, y) -> x * y));
        assertArrayEquals(AsIntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});


        for (int i : new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new FilterSI(new FilterSI(new StreamIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                        x -> x > 3),
                        x -> x < 7);
            }
        }) {
            System.out.println(i);
        }

        AsIntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).flatMap(x -> AsIntStream.of(1, 2, 3)).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });


    }


}
