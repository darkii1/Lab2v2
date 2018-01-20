import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void getSize() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(10);
        test.add(5);
        test.add(2);
        test.add(3);
        test.add(3);
        Assert.assertEquals(4,test.getSize());
    }

    @Test
    public void isEmpty() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(10);
        Assert.assertTrue(test.isEmpty());
        test.add(5);
        test.add(2);
        Assert.assertFalse(test.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        MyArrayList<String> test = new MyArrayList<String>(10);
        test.add("Alice");
        test.add("Doggy");
        Assert.assertTrue(test.contains("Alice"));
        Assert.assertFalse(test.contains("Potty"));
    }

    @Test
    public void indexOf() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(10);
        test.add(5);
        test.add(2);
        test.add(3);
        test.add(3);
        Assert.assertEquals(0,test.indexOf(5));
        Assert.assertEquals(2,test.indexOf(3));
    }

    @Test
    public void get() throws Exception {
        MyArrayList<String> test = new MyArrayList<String>(10);
        test.add("Alice");
        test.add("Doggy");
        Assert.assertEquals("Alice",test.get(0));
    }

    @Test
    public void add() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(2);
        test.add(5);
        test.add(2);
        test.add(3);
        Assert.assertEquals(3,test.getSize());
        test.add(0,6);
        test.add(3,4);
        Assert.assertEquals(6,(int)test.get(0));
        Assert.assertEquals(4,(int)test.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWithException1() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(2);
        test.add(5,5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWithException2() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(2);
        test.add(-4,5);
    }

    @Test
    public void remove() throws Exception {
        MyArrayList<Integer> test = new MyArrayList<Integer>(3);
        test.add(5);
        test.add(3);
        test.add(2);
        test.remove(1);
        Assert.assertEquals(2,(int)test.get(1));
    }

    @Test
    public void remove2() throws Exception {
        MyArrayList<String> test = new MyArrayList<String>(3);
        test.add("5");
        test.add("3");
        test.add("2");
        test.add("3");
        test.remove("3");
        Assert.assertEquals("2",test.get(1));
        Assert.assertEquals(2,test.getSize());
    }





    @Test
    public void forEachTest() throws Exception {
        MyArrayList<String> test = new MyArrayList<String>(3);
        test.add("5");
        test.add("3");
        test.add("2");
        for (String element : test) {
            System.out.println(element);
        }
    }

    @Test
    public void CopyArrayTest() throws Exception {
        MyArrayList<String> test = new MyArrayList<String>(3);
        test.add("5");
        test.add("3");
        test.add("2");
        MyArrayList<String> test2 = test;
        Assert.assertEquals("5", test2.get(0));
        Assert.assertEquals("3", test2.get(1));
        Assert.assertEquals("2", test2.get(2));
    }
}