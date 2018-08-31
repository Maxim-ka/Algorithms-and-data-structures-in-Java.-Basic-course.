import java.util.Arrays;

public class Lesson_2 {

    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public static void main(String[] args) {

        System.out.println(Arrays.toString(sortSelect(new int[]{10, 2, 35, 2, 48, 2, 2, 95, 2, 46, 5, 9, 2, 47})));

    }

    private static int find(int value){
        //check for sorting
        int left = 0;
        int right = array.length;
        int middle;
        while (left < right){
            middle = (left + right) >> 1;
            if (array[middle] == value) return middle;
            if (value < array[middle]) right = middle;
            else left = middle + 1;
        }
        return -1;
    }

    public static int[] sortInsert(int[] array){
        for (int i = 1; i < array.length; i++) {   // n - 1
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp){ //при худшем варианте n
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        //сложность О(n * n) = O(n^2)
        return array;
    }

    public static int[] sortSelect(int[] array){
        int flag;
        for (int i = 0; i < array.length; i++) {         // n
            flag = i;
            for (int j = i + 1; j < array.length; j++) { //n - 1
                if (array[flag] > array[j]) flag = j;
            }
            swap(array, i, flag);
        }
        //сложность О(n * n) = O(n^2)
        return array;
    }

    public static int[] sortCount(int[] array){
        //ДЗ
        int min = 0;
        int max = array.length - 1;
        for (int i = 0; i < array.length; i++) { // n
            if (array[i] > array[max]){
                max = i;
                continue;
            }
            if (array[i] < array[min]){
                min = i;
            }
        }
        int[] counter = new int[array[max] + 1];
        for (int i = 0; i < array.length; i++) { // n
            counter[array[i]]++;
        }
        for (int i = 0, j = array[min]; i < array.length; i++, counter[j]--) { //n
            while (counter[j] == 0){  // даже не знаю как здесь прикинуть количество повторений,
                j++;                  //между соседними элементами напр 0 и 100  будет 100 повторений
            }                         //между 100 и 1001 - 901 повторение и т.п., k
            array[i] = j;
        }
        //сложность О(3 * n * k) = O(n * k)
        return array;
    }

    public static int[] sortBubble(int[] array){
        //ДЗ
        boolean sorted;
        int ordered = 0;
        do{
            sorted = false;
            for (int i = ordered; i < array.length - 1 - ordered ; i++) { //наихудший случай, начальный проход n
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    sorted = true;
                }
            }
            if (!sorted) return array;
            sorted = false;
            for (int i = array.length - 1 - ordered; i > ordered ; i--) { //наихудший случай, начальный проход n
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    sorted = true;
                }
            }
            ordered++;
        }while (sorted && ordered < array.length / 2);                    // наихудший случай, сдвигаемся к середине n / 2
        //сложность О(2 * n * (n / 2)) = O (1/2 * 2 * n^2) = O (n^2)
        return array;
    }

    private static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
