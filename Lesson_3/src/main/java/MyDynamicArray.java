
public class MyDynamicArray<T>  {

    private Object[] arr;
    private int size;
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public MyDynamicArray(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        arr = new Object[capacity];
    }

    @SafeVarargs
    public MyDynamicArray(T... arg ) {
        size = arg.length;
        capacity = arg.length;
        arr = arg;
    }

    public int length(){
        return size;
    }

    public Object get(int index){
        if (size == 0) throw new RuntimeException("Массив пуст");
        if (index < 0 || index >= size) throw  new ArrayIndexOutOfBoundsException("Выход за пределы массива");
        return arr[index];
    }


    public void set(int index, int value){
        if (index < 0 || index >= size) throw  new ArrayIndexOutOfBoundsException("Выход за пределы массива");
        arr[index] = value;
    }

    public void add(int index, Object object){
        T value = (T) object;
        //необходимый метод для заполнения очереди
        if (index < 0 || index >= capacity) throw  new ArrayIndexOutOfBoundsException("Выход за пределы массива");
        arr[index] = value;
        size++;
    }

    public void insert(int index, Object object){
        T value = (T) object;
        // метод здесь не нужен?, написал просто посмотреть
        if (index < 0 || index > size) throw  new ArrayIndexOutOfBoundsException("Выход за пределы массива");
        if (size >= capacity){
            Object[] temp = arr;
            arr = new Object[capacity *= 2]; // увеличить на любую величину
            System.arraycopy(temp, 0, arr, 0, index);
            System.arraycopy(temp, index, arr, index + 1, size - index);
        }else{
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }
        arr[index] = value;
        size++;
    }

    public void append(T value){
        if (size >= arr.length - 1){
            Object[] temp = arr;
            arr = new Object[temp.length * 2];
            System.arraycopy(temp, 0, arr, 0, size);
        }
        arr[size++] = value;
    }

    public boolean remove(){
        if (size == 0) return false;
        size--;
        return true;
    }

    public boolean delete(int index){
        if (size == 0) return false;
        if (index < 0 || index >= size) throw  new ArrayIndexOutOfBoundsException("Выход за пределы массива");
        while (index < size - 1){
            arr[index] = arr[++index];
        }
        size--;
        return true;
    }

    public boolean deleteAll(){
        if (size == 0) return false;
        size = 0;
        return true;
    }

    public boolean deleteAll(T value){
        int count = 0;
        for (int i = 0; i + count < size; i++) {
            if (count != 0) arr[i] = arr[i + count];
            while (arr[i] == value || arr[i].equals(value)){
                ++count;
                if (i + count >= size) break;
                arr[i] = arr[i + count];
            }
        }
        size -= count;
        return count != 0;
    }

    public boolean isInArray(T value){
        for (int i = 0; i < size; i++) {
            if (arr[i] == value || arr[i].equals(value)) return true;
        }
        return false;
    }

    //k << n = k * 2^n;
    //k >> n = k / 2^n;


    @Override
    public String toString() {
        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(arr[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
