import java.util.Random;

public class Thing {

    private int weight;
    private int jewel;

    public int getWeight() {
        return weight;
    }

    public int getJewel() {
        return jewel;
    }

    public Thing() {
        weight = new Random().nextInt(100) + 1;
        jewel = new Random().nextInt(100);
    }

    @Override
    public String toString() {
        return String.format("weight- %d, jewel- %d;", weight, jewel);
    }
}
