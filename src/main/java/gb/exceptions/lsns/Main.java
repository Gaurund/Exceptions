package gb.exceptions.lsns;

public class Main {
    public static void main(String[] args) {
        int[] array = {1,2,4,5,3,6};
        printMessage(task01(array,4, 3));
        printMessage(task01(array,7, 3));
        printMessage(task01(array,4, 8));
        printMessage(task01(null,4, 3));
    }

    public static int task01(int[] array, int length, int value){
        if (array == null) return -3;
        if (array.length < length) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==value) return i;
        }
        return -2;
    }

    public static void printMessage(int errorCode){
        switch (errorCode){
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
}