public class MyDeque<T> extends MyQueue<T>{


    public MyDeque(int capacity) {
        super(capacity);
    }

    public void insertTail(T value){
        insert(value);
    }

    public void insertHead(T value){
        if (isFull()) myArray = expandQueue();
        head = (isEmpty()) ? head + (++tail) : --head;
        if(head < 0) head = myArray.getCapacity() - 1;
        myArray.add(head, value);
        items++;
    }

    public Object removeTail(){
        if (isEmpty()) throw new RuntimeException("Очередь пуста");
        Object temp = myArray.get(tail--);
        if (tail < 0) tail = myArray.length() - 1;
        items--;
        return temp;
    }

    public Object removeHead(){
       return remove();
    }
}
