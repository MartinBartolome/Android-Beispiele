package ch.ffhs.counter;

import org.junit.Test;

import static org.junit.Assert.*;

public class CounterTest {
    @Test
    public void up() throws Exception {
        Counter counter = Counter.getInstance();
        counter.reset();
        assertEquals(counter.getValue(), counter.getMin());
        counter.up();
        assertEquals(counter.getValue(), 1);
    }

    @Test
    public void down() throws Exception {

    }

    @Test
    public void reset() throws Exception {
        Counter counter = Counter.getInstance();
        counter.reset();
        assertEquals(counter.getValue(), counter.getMin());
    }

    @Test
    public void set() throws Exception {

    }

}