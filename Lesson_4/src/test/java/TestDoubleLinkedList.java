import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestDoubleLinkedList {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
            {new Integer[]{5, 9, 6, 7, 3, 4, 6}, 5, "[5, 9, 6, 7, 3, 6]", new int[]{38, 84, 15, 19}, "[38, 84, 15, 19, 5, 9, 6, 7, 3, 4, 6]", 4, "[5, 9, 6]"},
            {new Integer[]{10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47}, 6, "[10, 2, 35, 2, 48, 2, 95, 2, 46, 5, 9, 2, 47]",  new int[]{58, 79, 101}, "[58, 79, 101, 10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47]", 8, "[10, 2, 35, 2, 48, 2]"},
            {new Integer[]{11, 52, 9, 3, 3, 4, 5, 96, 0, 65, 3, 3, 69}, 10, "[11, 52, 9, 3, 3, 4, 5, 96, 0, 65, 3, 69]",new int[]{411, 2, 5, 6, 9}, "[411, 2, 5, 6, 9, 11, 52, 9, 3, 3, 4, 5, 96, 0, 65, 3, 3, 69]", 6, "[11, 52, 9, 3, 3, 4, 5]"},
            {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1}, 7, "[1, 2, 3, 4, 5, 6, 7, 9, 1, 0, 1, 1, 1]",new int[]{2, 3, 45, 6}, "[2, 3, 45, 6, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1]", 7, "[1, 2, 3, 4, 5, 6, 7]"},
            {new Integer[]{4, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36}, 3, "[4, 4, 4, 4, 82, 33, 4, 4, 4, 4, 75, 36]", new int[]{3, 4, 4, 4, 98}, "[3, 4, 4, 4, 98, 4, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36]", 8, "[4, 4, 4, 16, 4]"},
            {new Integer[]{91, 4, 2, 73, 89, 2, 57}, 1, "[91, 2, 73, 89, 2, 57]", new int[]{2, 2, 2, 2}, "[2, 2, 2, 2, 91, 4, 2, 73, 89, 2, 57]", 6, "[91]"},
            {new Integer[]{14, 82, 73, 94, 75, 8}, 0, "[82, 73, 94, 75, 8]",new int[]{8, 14, 73, 75, 94}, "[8, 14, 73, 75, 94, 14, 82, 73, 94, 75, 8]",3, "[14, 82, 73]"},
            {new Integer[]{6, 2, 6, 4, 6, 1, 6}, 6, "[6, 2, 6, 4, 6, 1]",new int[]{1, 2, 2, 7}, "[1, 2, 2, 7, 6, 2, 6, 4, 6, 1, 6]", 7, "[]"},
            {new Integer[]{1, 1, 1, 1, 1, 0, 0}, 3, "[1, 1, 1, 1, 0, 0]" ,new int[]{5, 8, 1, 1}, "[5, 8, 1, 1, 1, 1, 1, 1, 1, 0, 0]", 6, "[1]"},
            {new Integer[]{6, 5, 4, 3, 2, 1}, 7, "[6, 5, 4, 3, 2, 1]", new int[]{1, 2, 3, 4, 5, 6}, "[1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1]", 0, "[6, 5, 4, 3, 2, 1]"},
            {new Integer[]{1, 2, 3, 4, 5, 6},  9, "[1, 2, 3, 4, 5, 6]",new int[]{1, 2, 3, 4, 5, 6}, "[1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6]", 1, "[1, 2, 3, 4, 5]"},
        });
    }

    private Integer[] in;
    private String out;
    private int removed;
    private int index;
    private int value;
    private int peek;
    private int[] arr;
    private String poll;
    private String delete;

    public TestDoubleLinkedList(Integer[] in, int index, String delete, int[] arr, String out, int removed, String poll){
        this.in = in;
        this.out = out;
        this.index = index;
        this.value = value;
        this.removed = removed;
        this.peek = peek;
        this.arr = arr;
        this.poll = poll;
        this.delete = delete;
    }

    private DoubleLinkedList<Integer> myLinkedList;
    private DoubleLinkedList.MyListIterator myListIterator;

    @Before
    public void create(){
        myLinkedList = new DoubleLinkedList<>();
        for (int i = 0; i <in.length ; i++) {
            myLinkedList.addLast(in[i]);
        }
        myListIterator = myLinkedList.myListIterator();
    }

    @Test
    public void testAddLast(){
        Assert.assertEquals(Arrays.toString(in), myLinkedList.toString());
    }

    @Test
    public void testAddFirst(){
        for (int i = arr.length - 1; i>= 0; i--) {
            myLinkedList.addFirst(arr[i]);
        }
        Assert.assertEquals(out, myLinkedList.toString());
    }

    @Test
    @Ignore
    public void testRemove(){
        for (int i = arr.length - 1; i>= 0; i--) {
            myLinkedList.addFirst(arr[i]);
        }
        for (int i = 0; i < in.length; i++) {
            myLinkedList.remove(in[i]);
        }
        Assert.assertEquals(Arrays.toString(arr), myLinkedList.toString());
    }

    @Test
    public void testPollLast(){
        for (int i = 0; i < removed; i++) {
            myLinkedList.pollLast();
        }
        Assert.assertEquals(poll ,myLinkedList.toString());
    }

    @Test
    public void testDelete(){
        myLinkedList.delete(index);
        Assert.assertEquals(delete, myLinkedList.toString());
    }
}
