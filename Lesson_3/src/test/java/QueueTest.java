import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QueueTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
            {new Integer[]{5, 9, 6, 7, 3, 4, 6}, 4, 10, "[5, 9, 6, 7, 10, 3, 4, 6]", 5, 9, new int[]{3, 4, 5, 6, 6, 7, 9}},
            {new Integer[]{10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47}, 2, 44, "[10, 2, 44, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47]", 10, 2, new int[]{2, 2, 2, 2, 2, 2, 5, 9, 10, 35, 46, 47, 48, 95}},
            {new Integer[]{11, 52, 9, 3, 3, 4, 5, 96, 0 , 65, 3, 3, 69}, 6, 0,"[11, 52, 9, 3, 3, 4, 0, 5, 96, 0, 65, 3, 3, 69]", 11, 52, new int[]{0, 3, 3, 3, 3, 4, 5, 9, 11, 52, 65, 69, 96}},
            {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1}, 9, -1, "[1, 2, 3, 4, 5, 6, 7, 8, 9, -1, 1, 0, 1, 1, 1]", 1, 2, new int[]{0, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}},
            {new Integer[]{4, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36, 98, 4, 52, 4, 3, 4, 10, 4, 4, 4}, 1, 2, "[4, 2, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36, 98, 4, 52, 4, 3, 4, 10, 4, 4, 4]", 4, 4, new int[]{3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 10, 16, 33, 36, 52, 75, 82, 98}},
            {new Integer[]{91, 4, 2, 73, 2, 2, 2, 2, 4, 15, 96, 2, 2, 2, 2, 7, 89, 2, 57}, 8, 11, "[91, 4, 2, 73, 2, 2, 2, 2, 11, 4, 15, 96, 2, 2, 2, 2, 7, 89, 2, 57]", 91, 4, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 7, 15, 57, 73, 89, 91, 96}},
            {new Integer[]{14, 82, 73, 94, 75, 8, 76, 76, 76, 76, 76}, 5, 0, "[14, 82, 73, 94, 75, 0, 8, 76, 76, 76, 76, 76]", 14, 82, new int[]{8, 14, 73, 75, 76, 76, 76, 76, 76, 82, 94}},
            {new Integer[]{6, 2, 6, 4, 6, 1, 6, 2, 6}, 1, 9,"[6, 9, 2, 6, 4, 6, 1, 6, 2, 6]", 6, 2, new int[]{1, 2, 2, 4, 6, 6, 6, 6, 6}},
            {new Integer[]{1, 1, 1, 1, 1, 0, 0}, 5, 5, "[1, 1, 1, 1, 1, 5, 0, 0]", 1, 1, new int[]{0, 0, 1, 1, 1, 1, 1}},
            {new Integer[]{6, 5, 4, 3, 2, 1}, 5, 8,"[6, 5, 4, 3, 2, 8, 1]", 6, 5, new int[]{1, 2, 3, 4, 5, 6}},
            {new Integer[]{1, 2, 3, 4, 5, 6}, 0, 0,"[0, 1, 2, 3, 4, 5, 6]", 1, 2, new int[]{1, 2, 3, 4, 5, 6}},
        });
    }

    private Integer[] in;
    private String out;
    private int removed;
    private int index;
    private int value;
    private int peek;
    private int[] arr;

    public QueueTest(Integer[] in, int index, int value, String out, int removed, int peek, int[] arr){
        this.in = in;
        this.out = out;
        this.index = index;
        this.value = value;
        this.removed = removed;
        this.peek = peek;
        this.arr = arr;
    }

    private int size = 3;
    private MyQueue<Integer> myQueue;
    private MyDynamicArray<Integer> myArray;
    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void create(){
        myQueue = new MyQueue<>(size);
        for (int i = 0; i <in.length ; i++) {
            myQueue.insert(in[i]);
        }
        myArray = new MyDynamicArray<>(in);
        priorityQueue = new PriorityQueue<>(size);
    }

    @Test
    public void testPriorityQueue(){
        for (int i = 0; i < in.length; i++) {
            priorityQueue.insert(in[i]);
        }
        int[]temp = new int[in.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = (int) priorityQueue.remove();
        }
        Assert.assertArrayEquals(arr, temp);
    }

    @Test
    public void testInsert(){
        Assert.assertEquals(Arrays.toString(in), myQueue.toString());
    }

    @Test
    public void testRemove(){
        Assert.assertEquals(removed, myQueue.remove());
    }

    @Test
    public void testPeek(){
        myQueue.remove();
        Assert.assertEquals(peek, myQueue.peek());
    }

    @Test
    public void testInsertArray(){
        myArray.insert(index, value);
        Assert.assertEquals(out, myArray.toString());
    }
}
