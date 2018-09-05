
public class PriorityQueue<T> extends MyQueue<T>{

    public PriorityQueue(int capacity) {
        super(capacity);
    }

    public void insert(T value){
        if (isEmpty()) myArray.add(++tail, value);
        else {
            if (isFull()) expandQueue();
            organizeInteger((Integer) value);
        }
        items++;
    }

    private void organizeInteger(int value){
        if (myArray.length() == 1) myArray.insert(compare(tail, value), value);
        else findPlace(value);
        if(++tail >= myArray.getCapacity()) tail = -1;
    }

    private void findPlace(int value){
        int left = 0;
        int right = tail;
        int middle;
        while (right - left > 1){
            middle = (left + right) / 2;
            while (value == (int) myArray.get(middle)){
                int index = middle;
                if (middle + 1 < myArray.length()) middle++;
                if (index == middle || value < (int) myArray.get(middle)){
                    myArray.insert(middle, value);
                    return;
                }
            }
            if (value > (int) myArray.get(middle)) left = middle;
            else right = middle;
        }
        myArray.insert(compare(left, right, value), value);
    }

    private int compare(int index, int value ){
        return  (value >= (int) myArray.get(index)) ? ++index : index;
    }

    private int compare(int indexLeft, int indexRight, int value ){
        if (value < (int) myArray.get(indexLeft)) return (indexLeft - 1 < 0) ? indexLeft : --indexLeft;
        return compare(indexRight, value);
    }
}
