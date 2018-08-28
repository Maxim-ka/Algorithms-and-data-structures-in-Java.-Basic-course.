import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Lesson_1_Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
                {new int[]{10, 2, 35, 48, 95, 46, 5, 9, 2, 47}, 2, 29.9f, 3, 9, Math.pow(3, 9)},
                {new int[]{11, 52, 93, 34, 5, 96, 0 , 65, 33, 69}, 0, 45.8f, 3, 10, Math.pow(3, 10)},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1, 5.5f, 3, 11, Math.pow(3, 11)},
                {new int[]{16, 82, 33, 44, 75, 36, 98, 52, 3, 10}, 3, 44.9f, 3, 12, Math.pow(3, 12)},
                {new int[]{91, 42, 73, 24, 15, 96, 7, 89, 2, 57}, 2, 49.6f, 3, 15, Math.pow(3, 15)},
                {new int[]{14, 82, 73, 94, 75, 76}, 14, 69f, 3, 16, Math.pow(3, 16)},
                {new int[]{1, 2, 3, 4, 5, 6, 2, 6}, 1, 3.625f, 3, 20, Math.pow(3, 20)},
                {new int[]{1, 1, 1, 1, 1, 0, 0}, 0, 0.714f, 3, 21, Math.pow(3, 21)},
        });
    }

    private int[] in;
    private int minNumber;
    private float arithmeticMean;
    private double basic;
    private double degree;
    private double exponentiation;

    public Lesson_1_Test(int[] in, int minNumber, float arithmeticMean, double basic, double degree, double exponentiation){
        this.in = in;
        this.minNumber = minNumber;
        this.arithmeticMean = arithmeticMean;
        this.basic = basic;
        this.degree = degree;
        this.exponentiation = exponentiation;
    }

    @Test
    public void testToExtent(){
        Assert.assertEquals(exponentiation, Lesson_1.toExtent(basic, degree), 0.001);
    }

    @Test
    public void testFindMinimumElement(){
        Assert.assertEquals(minNumber, Lesson_1.findMinimumElement(in, 1, 0));
    }

    @Test
    public void testFindMinimumByDividingInHalf(){
        Assert.assertEquals(minNumber, Lesson_1.findMinimumByDividingInHalf(in, 0, in.length - 1));
    }

    @Test
    public void testFindArithmeticMean(){
        Assert.assertEquals(arithmeticMean, Lesson_1.findArithmeticMean(in), 0.001);
    }

}
