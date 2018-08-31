import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Lesson_2_Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
                {new Integer[]{10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47}, 5, "[10, 2, 35, 2, 48, 2, 95, 2, 46, 5, 9, 2, 47]", 2, "[10, 35, 48, 95, 46, 5, 9, 47]"},
                {new Integer[]{11, 52, 9, 3, 3, 4, 5, 96, 0 , 65, 3, 3, 69}, 0, "[52, 9, 3, 3, 4, 5, 96, 0, 65, 3, 3, 69]", 3, "[11, 52, 9, 4, 5, 96, 0, 65, 69]"},
                {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1}, 1, "[1, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1]", 1, "[2, 3, 4, 5, 6, 7, 8, 9, 0]"},
                {new Integer[]{4, 4, 4, 16, 4, 82, 33, 4, 4, 4, 4, 75, 36, 98, 4, 52, 4, 3, 4, 10, 4, 4, 4}, 3, "[4, 4, 4, 4, 82, 33, 4, 4, 4, 4, 75, 36, 98, 4, 52, 4, 3, 4, 10, 4, 4, 4]", 4, "[16, 82, 33, 75, 36, 98, 52, 3, 10]"},
                {new Integer[]{91, 4, 2, 73, 2, 2, 2, 2, 4, 15, 96, 2, 2, 2, 2, 7, 89, 2, 57}, 2, "[91, 4, 73, 2, 2, 2, 2, 4, 15, 96, 2, 2, 2, 2, 7, 89, 2, 57]", 2, "[91, 4, 73, 4, 15, 96, 7, 89, 57]"},
                {new Integer[]{14, 82, 73, 94, 75, 8, 76, 76, 76, 76, 76}, 5, "[14, 82, 73, 94, 75, 76, 76, 76, 76, 76]", 76, "[14, 82, 73, 94, 75, 8]"},
                {new Integer[]{6, 2, 6, 4, 6, 1, 6, 2, 6}, 1, "[6, 6, 4, 6, 1, 6, 2, 6]", 6, "[2, 4, 1, 2]"},
                {new Integer[]{1, 1, 1, 1, 1, 0, 0}, 3, "[1, 1, 1, 1, 0, 0]", 1, "[0, 0]"},
        });
    }

    private Integer[] in;
    private int index;
    private String outNoIndex;
    private int value;
    private String outNoValue;

    public Lesson_2_Test(Integer[] in, int index, String outNoIndex, int value, String outNoValue){
        this.in = in;
        this.index = index;
        this.outNoIndex = outNoIndex;
        this.value = value;
        this.outNoValue = outNoValue;
    }

    private MyArray<Integer> myArray;

    @Before
    public void createArray(){
        myArray = new MyArray<>(in);
    }

    @Test
    @Ignore //Тесты запускаются по отдельности
    public void testDeleteIndex(){
        myArray.delete(index);
        Assert.assertEquals(outNoIndex,  myArray.toString());
    }

    @Test
//    @Ignore //Тесты запускаются по отдельности
    public void testDeleteAllValue(){
        myArray.deleteAll(value);
        Assert.assertEquals(outNoValue, myArray.toString());
    }
}
