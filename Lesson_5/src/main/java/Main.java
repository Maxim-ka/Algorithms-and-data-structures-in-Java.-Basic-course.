public class Main {

    public static void main(String[] args) {
        Thing[] things = new Thing[5];
        for (int i = 0; i < things.length; i++) {
            things[i] = new Thing();
        }
        for (int i = 0; i < things.length; i++) {
            System.out.println(i + 1 + " " + things[i].toString());
        }
        Backpack backpack = new Backpack(100);
        System.out.println("--------------");
        backpack.toTake(0, things);
        System.out.println("--------------");
        System.out.println(backpack.toString());

    }
}
