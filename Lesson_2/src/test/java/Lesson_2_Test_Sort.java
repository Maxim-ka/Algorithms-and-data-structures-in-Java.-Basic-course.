import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Lesson_2_Test_Sort {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
                {new int[]{10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47}, new int[]{2, 2, 2, 2, 2, 2, 5, 9, 10, 35, 46, 47, 48, 95}},
                {new int[]{11, 52, 9, 3, 3, 4, 5, 96, 0 , 65, 3, 3, 69}, new int[]{0, 3, 3, 3, 3, 4, 5, 9, 11, 52, 65, 69, 96}},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1}, new int[]{0, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}},
                {new int[]{4, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36, 98, 4, 52, 4, 3, 4, 10, 4, 4, 4}, new int[]{3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 10, 16, 33, 36, 52, 75, 82, 98}},
                {new int[]{91, 4, 2, 73, 2, 2, 2, 2, 4, 15, 96, 2, 2, 2, 2, 7, 89, 2, 57}, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 7, 15, 57, 73, 89, 91, 96}},
                {new int[]{14, 82, 73, 94, 75, 8, 76, 76, 76, 76, 76}, new int[]{8, 14, 73, 75, 76, 76, 76, 76, 76, 82, 94}},
                {new int[]{6, 2, 6, 4, 6, 1, 6, 2, 6}, new int[]{1, 2, 2, 4, 6, 6, 6, 6, 6}},
                {new int[]{1, 1, 1, 1, 1, 0, 0}, new int[]{0, 0, 1, 1, 1, 1, 1}},
                {new int[]{6, 5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5, 6}},
        });
    }

    private int[] in;
    private int[] out;

    public Lesson_2_Test_Sort(int[] in, int[] out){
        this.in = in;
        this.out = out;
    }

    @Test
    public void testSortBubble(){
        Assert.assertArrayEquals(out, Lesson_2.sortBubble(in));
    }

    @Test
    public void testSortCount(){
        Assert.assertArrayEquals(out, Lesson_2.sortCount(in));
    }

    @Test
    public void testSortSelect(){
        Assert.assertArrayEquals(out, Lesson_2.sortSelect(in));
    }

    @Test
    public void testSortInsert(){
        Assert.assertArrayEquals(out, Lesson_2.sortInsert(in));
    }
}
