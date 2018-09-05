public class MyStack<T> {

    private MyDynamicArray<T> myArray;
    private int top;

    public MyStack(int size) {
        myArray = new MyDynamicArray<>(size);
        top = -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void clear(){
        top = -1;
    }

    public void push(T value){
        myArray.append(value);
        top++;
    }

    public Object pop(){
        Object value;
        try {
            value = myArray.get(top--);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Стек пуст");
        }
        myArray.remove();
        return value;
    }

    public Object peek(){
        Object value;
        try {
            value = myArray.get(top);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Стек пуст");
        }
        return value;
    }


}
