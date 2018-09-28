public class MyHashTable{

    private float dutyFactor = 0.7f;
    private MyLinkedList[] hashArr;
    private int capacity;

    public MyHashTable(int capacity) {
        this.capacity = setCapacity(capacity);
        hashArr = new MyLinkedList[this.capacity];
    }

    private int setCapacity(int value){
        if (value % 2 == 0) value += 1;
        for (int i = value;  ; i += 2) {
            if (isSimpleNumber(i)) return i;
        }
    }

    private boolean isSimpleNumber(int value){
        for (int i = 3 ;  i * i <= value; i += 2) {
            if (value % (i * i) == 0) return  false;
        }
        return true;
    }

    private int hashFunc(int key) {
        return key % capacity;
    }

    public void insert(Item item) {
        if (isFullHashArr() || isFullList()) increaseSizeTableHash();
        int key = item.getKey();
        int hashVal = hashFunc(key);
        if (hashArr[hashVal] == null) hashArr[hashVal] = new MyLinkedList();
        hashArr[hashVal].insert(item);
    }

    private void increaseSizeTableHash(){
        capacity = setCapacity(capacity * 2);
        MyLinkedList[] hashArrTemp = new MyLinkedList[capacity];
        for (int i = 0; i < hashArr.length; i++) {
            while (hashArr[i] != null && !hashArr[i].isEmpty()){
                Item item = hashArr[i].getHead();
                int hashNewVal = hashFunc(item.getKey());
                if (hashArrTemp[hashNewVal] == null) hashArrTemp[hashNewVal] = new MyLinkedList();
                hashArrTemp[hashNewVal].insert(hashArr[i].delete(item.getKey()));
            }
        }
        hashArr = hashArrTemp;
    }

    public Item find(int key) {
        int hashVal = hashFunc(key);
        if(hashArr[hashVal] != null) return hashArr[hashVal].find(key);
        return null;
    }

    public Item delete(int key) {
        int hashVal = hashFunc(key);
        if (hashArr[hashVal] != null) return hashArr[hashVal].delete(key);
        return null;
    }

    private boolean isFullHashArr() {
        for (int i = 0; i < hashArr.length; i++) {
            if (hashArr[i] == null) return false;
        }
        return true;
    }

    private boolean isFullList(){
        for (int i = 0; i < hashArr.length; i++) {
            if (hashArr[i] != null && hashArr[i].getSize() > dutyFactor * capacity) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append("-\n");
            sb.append((hashArr[i] == null) ? "*" : hashArr[i].toString());
            sb.append("\n");
        }
        sb.append("-\n");
        return sb.toString();
    }
}
