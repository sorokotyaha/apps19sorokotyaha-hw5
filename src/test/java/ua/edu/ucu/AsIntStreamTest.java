package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author andrii
 */
public class AsIntStreamTest {

    private IntStream intStream;
    private IntStream emptyStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        int[] arr = {};
        intStream = AsIntStream.of(intArr);
        emptyStream = AsIntStream.of(arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamMax() {
        System.out.println("streamMax");
        Integer expResult = 3;
        Integer result = intStream.max();
        assertEquals(expResult, result);

        System.out.println("streamMax for empty stream..");
        emptyStream.max();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamMin() {
        System.out.println("streamMin");
        Integer expResult = -1;
        Integer result = intStream.min();
        assertEquals(expResult, result);

        System.out.println("streamMin for empty stream..");
        emptyStream.min();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamAverage() {
        System.out.println("streamAverage");
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0);

        System.out.println("streamAverage for empty stream..");
        emptyStream.average();

    }

    @Test
    public void testStreamCount() {
        System.out.println("streamCount");
        long expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);

        System.out.println("streamAverage for empty stream..");
        long exp = 0;
        assertEquals(exp, emptyStream.count());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStreamSum() {
        System.out.println("streamSum");
        Integer expResult = 5;
        Integer result = intStream.sum();
        assertEquals(expResult, result);

        System.out.println("streamSum for empty stream..");
        emptyStream.sum();
    }

    @Test
    public void testStreamToArrayEmpty() {
        System.out.println("streamToArrayEmpty");
        int[] expResult = {};
        int[] result = emptyStream.toArray();
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testStreamForEachEmpty() {
        System.out.println("streamForEachEmpty");
        String expResult = "";
        String result = StreamApp.streamForEach(emptyStream);
        assertEquals(expResult, result);

    }

    @Test
    public void testStreamFilter() {
        System.out.println("streamFilter");
        int[] expResult = {0, 1};
        int[] result = intStream.filter(x -> x >= 0).filter(x -> x < 2).toArray();
        assertArrayEquals(expResult, result);

        System.out.println("streamFilterEmpty");
        int[] exp = {};
        int[] res = emptyStream.filter(x -> x >= 0).filter(x -> x < 2).toArray();
        assertArrayEquals(exp, res);

    }

    @Test
    public void testStreamMap() {
        System.out.println("streamMap");
        int[] expResult = {0, 1, 2, 3, 4};
        int[] result = intStream.map(x -> x + 1).toArray();
        assertArrayEquals(expResult, result);

        System.out.println("streamMapEmpty");
        int[] exp = {};
        int[] res = emptyStream.map(x -> x + 1).toArray();
        assertArrayEquals(exp, res);

    }


    @Test
    public void testStreamFlatMap() {
        System.out.println("streamFlatMap");
        int[] expResult = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4};
        int[] result = intStream.flatMap(x -> AsIntStream.of(x, x + 1)).toArray();
        assertArrayEquals(expResult, result);

        System.out.println("streamFlatMapEmpty");
        int[] exp = {};
        int[] res = emptyStream.flatMap(x -> AsIntStream.of(x, x + 1)).toArray();
        assertArrayEquals(exp, res);

    }

    @Test
    public void testStreamReduce() {
        System.out.println("streamReduce");
        int expResult = 0;
        int result = intStream.reduce(1, (x, y) -> x * y);
        assertEquals(expResult, result);

        System.out.println("streamReduceEmpty");
        int exp = 1;
        int res = emptyStream.reduce(1, (x, y) -> x * y);
        assertEquals(exp, res);

    }


}

