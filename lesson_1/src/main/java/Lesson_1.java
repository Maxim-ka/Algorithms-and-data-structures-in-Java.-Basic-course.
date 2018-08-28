
public class Lesson_1 {


    public static void main(String[] args) {

        System.out.println("Task_1_1\n");
        double basis = 3;
        double degree = 21;
        System.out.printf("%.0f\n", Math.pow(basis, degree));
        System.out.printf("%.0f\n", toExtent(basis, degree));

        System.out.println("Task_1_2\n");
        int[] arr = {1, 4, 8, 6, 9, 5, 1, 5, 3, 6, 5, 4, 1, 8, 2, 2, 1, 4, 3, 6};
        System.out.println(findMinimumElement(arr, 1 , 0));
        System.out.println(findMinimumByDividingInHalf(arr, 0, arr.length - 1));

        System.out.println("Task_1_3\n");
        System.out.printf("%.2f", findArithmeticMean(arr));
    }

    public static double toExtent(double basis, double degree) {
        //  т.к. операция возведения в квадрат быстрее операции умножения за счёт того, что при возведении в квадрат цифры в сомножителе
        // повторяются, в данном алгоритме используется правило X^n = X^((n/2)* 2) = (X^(n/2))^2 = X^(n/2) * X^(n/2), в случае нечетности степени,
        // приводим её к четной X^n = X^(n-1) * X^1
        // поскольку степень постоянно делится пополам возникает логарифмическая зависимость Log2(n) - сколько раз нужно разделить степень на 2
        // что бы получить результат, имеем Log2(n) < n (n - если бы было простое перемножение числа n раз).
        // Т.к. при деление степени пополам может получаться нечетная степень, будет k -количество операций приведения к четной степени,
        // но поскольку k << Log2(n), можно считать сложность алгоритма O(log(n))
        if (degree == 0) return 1;
        while (degree > 1) {
            if (degree % 2 == 0) {
                degree /= 2;
                basis *= basis;
            } else {
                return toExtent(basis, --degree) * basis;
            }
        }
        return basis;
    }

    // в задачах где массивы время к доступу элемента массива по индексу не учитываем т.к. O(1) оно постоянно.
    // в алгоритмах с итерацией, что при поиске минимального числа (будет браться опорный элемент, скажем первый и будет
    // сраниваться с остальными, если текущий элемент меньше опорного, значит текуший становиться опорным и поиск
    // продолжается по массиву, будет n - 1 сравнений, сложность О(n - 1)) или среднеарифмитического значение (так же один
    // проход массиву суммируя все элементы), будет один цикл для прохождения по массиву, следовательно сложность можно
    // считать О(n).
    // Остальный операции не учитываем т.к. они одинаковые для любого количества элементов массива.
    // ниже указаны алгоритмы с рекурсией для тех же задач.

    public static int findMinimumElement(int[] numbers, int startIndex, int supportIndex) {
        //здесь выбираем опорный элемент - первый, нулевой индекс. и начальный элемент с котором начинаем  сравнивать.
        //по скольку нам нужно пройти последовательно весь массив что бы определить минимльный элемент, будет выполнено О(n) - операций
        //если я правильно понял из Бхаргава А. - Грокаем Алгоритмы. Иллюстрированное пособие для программистов и любопытствущих,
        // т.к. используется рекурсия, количество уровней стэка вызовов будет  n. соответсвенно сложность алгоритма О(n * n) = O(n^2)
        if (numbers.length - 1 == startIndex)
            return (numbers[supportIndex] > numbers[numbers.length - 1]) ? numbers[numbers.length - 1] : numbers[supportIndex];
        if (numbers[supportIndex] > numbers[startIndex]) supportIndex = startIndex;
        return findMinimumElement(numbers, ++startIndex, supportIndex);
    }

    public static int findMinimumByDividingInHalf(int[] numbers, int startIndex, int endIndex) {
        //здесь используется деление массива пополам, до базового случая, либо получаем один элемент вслучае нечетного деления или
        //пару соседних элементов, сравнивая которые получаем минимальный элемент.
        //поскольку надо обратится ко всем элементам массива будет выполнено n - операций.
        //но т.к. в данном случае, если я правильно понимаю из Бхаргава А. - Грокаем Алгоритмы. количество уровней стэка вызовов будет Log2(n) т.к
        // постоянно делим массив пополам, аналогия с алгоритмом возведения в степень. тогда сложность алгоритма будет O(n *log(n))
        if (startIndex == endIndex) return numbers[startIndex];
        if (endIndex - startIndex == 1) return (numbers[startIndex] > numbers[endIndex]) ? numbers[endIndex] : numbers[startIndex];
        int middle = (startIndex + endIndex) / 2;
        int left = findMinimumByDividingInHalf(numbers, startIndex, middle);
        int right = findMinimumByDividingInHalf(numbers, middle + 1, endIndex);
        return (left > right) ? right : left;
    }

    public static float findArithmeticMean(int[] numbers) {
        // основная сложность данного алгоритма будет в нахождение суммы всех элементов массива.
        return (float) findSum(numbers, 0, numbers.length - 1) / numbers.length;
    }

    private static int findSum(int[] numbers, int currentIndex) {
        //здесь последовательное сложение элементов начиная с индекса 0, аналогия с  findMinimumElement
        //сложность O(n^2)
        if (currentIndex == numbers.length - 1) return numbers[currentIndex];
        return numbers[currentIndex] + findSum(numbers, ++currentIndex);
    }

    private static int findSum(int[] numbers, int startIndex, int endIndex) {
        //здесь разбиение массива пополам до базовго случая один элемент или сумма двух элементов, аналогия с
        // findMinimumByDividingInHalf, сложность O(n *log(n))
        if (startIndex == endIndex) return numbers[startIndex];
        if (endIndex - startIndex == 1) return numbers[startIndex] + numbers[endIndex];
        int middle = (startIndex + endIndex) / 2;
        return findSum(numbers, startIndex, middle) + findSum(numbers, middle + 1, endIndex);
    }
}
