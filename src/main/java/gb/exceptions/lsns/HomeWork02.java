package gb.exceptions.lsns;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class HomeWork02 {

    public static void main(String[] args) {
        System.out.println("\nВыполнение первого задания:");
        task_01();

        System.out.println("\nВыполнение четвёртого задания:");
        task_04();
    }

    //    Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
//    и возвращает введенное значение.
//    Ввод текста вместо числа не должно приводить к падению приложения,
//    вместо этого, необходимо повторно запросить у пользователя ввод данных.
    public static void task_01() {
        System.out.println("\nВведите дробное число: ");
        try {
            Scanner scanner = new Scanner(System.in);
            float number = scanner.nextFloat();
            System.out.println("\nВы ввели: " + number);
        } catch (NoSuchElementException e) {
            System.out.println("\nНельзя вводить ничего кроме чисел! Желательно дробных.\n");
            task_01();
        }
    }

    //    Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
//    Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
    public static void task_04() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите что угодно, только не пустую строку!");
        String text = scanner.nextLine();
        if (Objects.equals(text, "")) {
            throw new RuntimeException("\nНельзя вводить пустые строки!");
        }
        System.out.println("\nВы ввели: " + text + "\nСпасибо. До свидания.");
    }
}
