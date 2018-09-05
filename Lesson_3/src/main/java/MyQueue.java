public class MyQueue<T> {

    protected MyDynamicArray<T> myArray;
    protected int head;
    protected int tail;
    protected int items;

    public MyQueue(int capacity) {
        this.myArray = new MyDynamicArray<>(capacity);
        head = 0;
        tail = -1;
        items = 0;
    }

    public boolean isEmpty(){
        return items == 0;
    }

    public boolean isFull(){
        return items == myArray.getCapacity();
    }

    public int size(){
        return items;
    }

    public void insert(T value){
        if (isFull()) myArray = expandQueue();
        myArray.add(++tail, value);
        if(tail >= myArray.getCapacity()) tail = -1;
        items++;
    }

    protected MyDynamicArray<T> expandQueue(){
        MyDynamicArray<T> myDynamicArray = new MyDynamicArray<>(myArray.getCapacity() * 2);
        if(tail >= head){
            for (int i = 0; i < myArray.length(); i++) {
                myDynamicArray.add(i, myArray.get(i));
            }
        }else{
            int j = myDynamicArray.getCapacity() - myArray.getCapacity();
            for (int i = 0; i < tail + 1; i++) {
                myDynamicArray.add(i, myArray.get(i));
            }
            for (int i = myArray.getCapacity() - 1; i >= head; i--){
                myDynamicArray.add(i + j, myArray.get(i));
            }
            head = myDynamicArray.getCapacity() - 1 - head;
        }
        return myDynamicArray;
    }

    public Object remove(){
        if (isEmpty()) throw new RuntimeException("Очередь пуста");
        Object temp = myArray.get(head++);
        if (head == myArray.getCapacity()) head %= myArray.getCapacity();
        items--;
        return temp;
    }

    public Object peek(){
        if (isEmpty()) throw new RuntimeException("Очередь пуста");
        return myArray.get(head);
    }

    @Override
    public String toString() {
        if (tail >= head) return myArray.toString();
        if (myArray == null) return "null";
        int iMax = myArray.length() - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = head; i <= iMax; i++) {
            b.append(String.valueOf(myArray.get(i)));
            b.append(", ");
        }
        for (int i = 0;  ; i++) {
            b.append(String.valueOf(myArray.get(i)));
            if (i == tail + 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
