public class Main {

    public static void main(String[] args) {

        MyHashTable ht = new MyHashTable (5);
        for (int i = 0; i < 4; i ++) {
            ht.insert (new Item (i * 5));
        }
//        System.out.println (ht);
//        ht.delete (25);
        System.out.println (ht);
        ht.insert (new Item (75));
        ht.insert (new Item (105));
        ht.insert (new Item (205));
        ht.insert (new Item (305));
        System.out.println("-------------\n");
        System.out.println (ht);
    }
}
