package gb.exceptions.lsns;

public class Main {
    public static void main(String[] args) {
//        int[] array = {1,2,4,5,3,6};
//        printMessage(task01(array,4, 3));
//        printMessage(task01(array,7, 3));
//        printMessage(task01(array,4, 8));
//        printMessage(task01(null,4, 3));

//        int[][] matrix = {{1, 0, 1}, {1, 0, 1}, {1, 0, 1}};
//        System.out.println("Сумма элементов массива: " + task02(matrix));

        Integer[] array = {1, 2, null, null, 4, null};
        checkArray(array);

    }

    public static int task01(int[] array, int length, int value) {
        if (array == null) return -3;
        if (array.length < length) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i;
        }
        return -2;
    }

    public static void printMessage(int errorCode) {
        switch (errorCode) {
            case -1:
                System.out.println("Длинна массива меньше заданного");
                break;
            case -2:
                System.out.println("Ничего не найдено.");
                break;
            case -3:
                System.out.println("Массив не инициализирован.");
                break;
            default:
                System.out.println("Индекс элемента: " + errorCode);
        }
    }

    public static int task02(int[][] matrix) {
        int height = matrix.length;
        int sum = 0;
        for (int[] row :
                matrix) {
            if (row.length != height) {
                throw new RuntimeException(String.format("Матрица не квадратная, есть строка длины %d, " +
                        "всего строк %d", row.length, height));
            }
            for (int num :
                    row) {
                if (num != 0 && num != 1) {
                    throw new RuntimeException(String.format("В матрице есть число %d, отличное от 0 и 1", num));
                }
                sum += num;
            }
        }
        return sum;
    }

    public static void checkArray(Integer[] array) {
        StringBuilder nullIndexes = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                nullIndexes.append(i).append(", ");
            }
        }
        if (nullIndexes.length() != 0) {
            throw new RuntimeException("Индексы null'ов: " + nullIndexes.substring(0, nullIndexes.length() - 2));
        }
        System.out.println("null-ов нет");
    }

    public static boolean task05(int[][] matrix){
        int[] num = getCountColumn(matrix);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != num[i]){
                return false;
            }
        }
        return true;
    }
    public static int[] getCountColumn(int[][] array) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int[] row : array) {
            if (row.length > max) {
                max = row.length;
            }
        }
        int[] maxElements = new int[max];
        for (int[] row : array) {
            for (int i = 0; i < row.length; i++) {
                maxElements[i] += 1;
            }
        }
        return maxElements;
    }

}