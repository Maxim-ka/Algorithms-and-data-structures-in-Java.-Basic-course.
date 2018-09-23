import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestBackpack {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList (new Object[][]{
            {new Thing[]{new Thing(54, 1), new Thing(29, 38), new Thing(30, 70), new Thing(33, 22), new Thing(41, 42)}, new int[]{42, 70, 38}},
            {new Thing[]{new Thing(81, 4), new Thing(9, 31), new Thing(20, 27), new Thing(52, 83), new Thing(29, 49)}, new int[]{49, 83, 31}},
            {new Thing[]{new Thing(71, 67), new Thing(90, 80), new Thing(8, 46), new Thing(10, 32), new Thing(90, 10)}, new int[]{32, 46, 67}},
            {new Thing[]{new Thing(34, 18), new Thing(78, 56), new Thing(26, 82), new Thing(77, 29), new Thing(58, 10)}, new int[]{82, 18}},
            {new Thing[]{new Thing(69, 0), new Thing(32, 61), new Thing(76, 26), new Thing(37, 2), new Thing(96, 79)}, new int[]{79}},
        });
    }

    private Thing[] in;
    private int[] outCost;

    public TestBackpack(Thing[] in, int[] outCost){
        this.in = in;
        this.outCost = outCost;
    }

    private int carryingCapacity = 100;
    private Backpack backpack;

    @Before
    public void create(){
        backpack = new Backpack(carryingCapacity);
    }

    @Test
    public void testToTake(){
        backpack.toTake(0, in);
        Assert.assertArrayEquals(outCost, backpack.getCostOfBackpack());
    }
}
