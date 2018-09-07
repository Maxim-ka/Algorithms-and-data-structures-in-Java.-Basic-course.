import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMyListIterator {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
            {new int[]{5, 9, 6, 7, 3, 4, 6}, 5, "[5, 9, 6, 7, 3, 5, 4, 6, 5]", "[5, 5, 9, 6, 7, 5, 3, 4, 6]",  2, "[9, 6, 7, 4]", "[5, 9, 6, 7, 5, 4, 5]"},
            {new int[]{10, 7, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47}, 6, "[10, 7, 6, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47, 6]", "[6, 10, 6, 7, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47]", -1, "[35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2]", "[6, 6, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 6]"},
            {new int[]{11, 52, 9, 3, 3, 4, 5, 6, 0, 6, 3, 3, 69}, 10, "[11, 52, 9, 3, 3, 4, 5, 6, 10, 0, 6, 10, 3, 3, 69, 10]", "[10, 11, 52, 9, 3, 3, 4, 5, 10, 6, 0, 10, 6, 3, 3, 69]", 7, "[52, 9, 3, 3, 4, 5, 0, 3, 3]", "[10, 52, 9, 3, 3, 4, 5, 10, 0, 10, 3, 3, 10]"},
            {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 6, 6, 1}, 6, "[1, 2, 3, 4, 5, 6, 7, 6, 8, 9, 1, 0, 6, 6, 1, 6]", "[6, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 1, 0, 6, 6, 1]", 4, "[2, 3, 4, 5, 6, 8, 9, 1, 0, 6, 6]", "[6, 2, 3, 4, 5, 6, 6, 8, 9, 1, 0, 6, 6, 6]"},
            {new int[]{91, 4, 2, 3, 89, 2, 57}, 1, "[91, 4, 2, 3, 1, 89, 2, 57, 1]", "[1, 91, 4, 2, 1, 3, 89, 2, 57]", 1, "[4, 2, 89, 2]", "[1, 4, 2, 1, 89, 2, 1]"},
        });
    }

    private int[] in;
    private int add;
    private String outAddNext;
    private String outAddPrev;
    private String outRemoved;
    private int index;
    private String outSet;

    public TestMyListIterator(int[] in, int add, String outAddNext, String outAddPrev, int index ,String outRemoved, String outSet){
        this.in = in;
        this.add = add;
        this.outAddNext = outAddNext;
        this.outAddPrev = outAddPrev;
        this.index = index;
        this.outRemoved = outRemoved;
        this.outSet = outSet;
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
    public void testIteratorHasNext(){
        while (myListIterator.hasNext()){
            int number = (int) myListIterator.next();
            if (number == in.length / 2)  myListIterator.add(add);
        }
        myListIterator.add(add);
        Assert.assertEquals(outAddNext, myLinkedList.toString());
    }

    @Test
    public void testIteratorNext(){
        int[] temp = new int[in.length];
        int i = 0;
        while (myListIterator.hasNext()){
            temp[i++] = (int)myListIterator.next();
        }

        Assert.assertArrayEquals(in, temp);
    }

    @Test
    public void testIteratorHasPrevious(){
        while (myListIterator.hasNext()){
            myListIterator.next();
        }
        while (myListIterator.hasPrevious()){
            int number = (int)myListIterator.previous();
            if (number == in.length / 2) myListIterator.add(add);
        }
        myListIterator.add(add);
        Assert.assertEquals(outAddPrev, myLinkedList.toString());
    }

    @Test
    public void testIteratorPrevious(){
        while (myListIterator.hasNext()){
            myListIterator.next();
        }
        int[] temp = new int[in.length];
        int i = in.length - 1;
        while (myListIterator.hasPrevious()){
            temp[i--] = (int) myListIterator.previous();
        }
        Assert.assertArrayEquals(temp , in);
    }

    @Test
    public void testIteratorPreviousIndex(){
        while (myListIterator.hasNext()){
            myListIterator.next();
        }
        while (myListIterator.hasPrevious()){
            int number = (int)myListIterator.previous();
            if (number == in.length / 2) break;
        }
        Assert.assertEquals(index, myListIterator.previousIndex());
    }

    @Test
    public void testIteratorNextIndex(){
        while (myListIterator.hasNext()){
            myListIterator.next();
        }
        Assert.assertEquals(in.length, myListIterator.nextIndex());
    }

    @Test
    public void testIteratorRemove(){
        while (myListIterator.hasNext()){
            int number = (int) myListIterator.next();
            if (myListIterator.nextIndex() == 1) myListIterator.remove();
            if (number == in.length / 2) myListIterator.remove();
        }
        myListIterator.remove();
        Assert.assertEquals(outRemoved, myLinkedList.toString());
    }

    @Test
    public void testIteratorSet(){
        while (myListIterator.hasNext()){
            int number = (int) myListIterator.next();
            if (myListIterator.nextIndex() == 1)  myListIterator.set(add);
            if (number == in.length / 2) myListIterator.set(add);
        }
        myListIterator.set(add);
        Assert.assertEquals(outSet, myLinkedList.toString());
    }
}
