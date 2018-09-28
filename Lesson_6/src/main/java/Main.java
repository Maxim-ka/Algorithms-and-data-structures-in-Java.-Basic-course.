import java.util.Random;

public class Main {

    private static final int NUMBER_OF_TREES = 20;
    private static final int AMOUNT_OF_ELEMENTS = (int) Math.pow(2, 6);

    public static void main(String[] args) {
        MyTree[] myTrees = new MyTree[NUMBER_OF_TREES];
        Random random = new Random();
        for (int i = 0; i < myTrees.length; i++) {
            myTrees[i] = new MyTree<>();
            for (int j = 0; j < AMOUNT_OF_ELEMENTS ; j++) {
                int number = random.nextInt(201) - 100;
                myTrees[i].insert(number, number);
            }
        }
        for (int i = 0; i < myTrees.length; i++) {
            myTrees[i].showTree();
            System.out.println();
        }
        System.out.printf("Несбалансированных деревьев %d процентов",  analyze(myTrees));

    }

    private static int analyze(MyTree[] myTrees){
        int count = 0;
        for (int i = 0; i < myTrees.length; i++) {
            if (!myTrees[i].checkBalance()) count++;
        }
        return count * 100 / myTrees.length;
    }
}
