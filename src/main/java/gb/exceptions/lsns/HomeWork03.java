package gb.exceptions.lsns;

import java.util.Scanner;

public class HomeWork03 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = 0;
        result = program();
        System.out.println(result);

    }

    public static void test() {
        String[] array = {"12345", "+79231", "+7(950)123456", "+8(950)546-1213", "8-950-546-12-13", "12.12.2023"};
        for (String s : array) {
            if (s.matches("\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+") && s.length() != 10)
                System.out.println(s);
        }
    }

    public static int program() {

        //        Предупредить как правильно вводить данные.
        System.out.println("\nПожалуйста, введите ФИО, дату рождения, телефон и пол человека. " +
                "\nВ качества разделителя используйте пробел." +
                "\nДата рождения должна быть в формате dd.mm.yyyy." +
                "\nПри введении номера телефона не используйте пробел для разделения чисел. Иначе всё пойдёт по бороде." +
                "\nПол должен быть представлен одной латинской буквой. Для мужчин -- m, для женщин -- f.");

        //                Передать ввод в строку.
        String input = scanner.nextLine();

        //        Убедиться что она не пустая. Если пустая, вернуться в пункт 1. Иначе идти дальше.
        if (input.trim().equals("")) return -1;

        //                Разбить её в массив строк.
        String[] splitInput = input.trim().split("\\p{Space}+");

        //        Проверить величину массива. Если он не равен 6, выдать сообщение о некорректном вводе и перейти к пункту 1. Иначе идём дальше.
        if (splitInput.length != 6) return -2;

        //        Создать временный массив в который будет сохраняться результат.
        String[] resultArray = new String[6];

        for (int i = 0; i < 6; i++) {
//                Проверить указание пола, если да, записать в 5 индекс
            if (splitInput[i].equalsIgnoreCase("f") || splitInput[i].equalsIgnoreCase("m")) {
                resultArray[5] = splitInput[i];

                System.out.println("Пол: " + resultArray[5]);
            }
//        Проверить телефон, если да, записать в 4 индекс. Тут должен быть сложный парсер из разных форматов в число.
            else if (splitInput[i].matches("\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+")
                    && splitInput[i].length() != 10) {
                resultArray[4] = splitInput[i];

                System.out.println("Телефон: " + resultArray[4]);
            }
//        Проверить дату, если да, записать в 3 индекс. Тут должен быть сложный парсер в дату.
            else if (splitInput[i].matches("\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+")
                    && splitInput[i].length() == 10) {
                resultArray[3] = splitInput[i];

                System.out.println("Дата: " + resultArray[3]);
            }
//                Проверить строку на фамилию. Если да, то записать её и два последующих элемента в индексы 0,1 и 2
            else if (i < 4 && splitInput[i].matches("[а-яА-Я]+") && splitInput[i + 1].matches("[а-яА-Я]+") && splitInput[i + 2].matches("[а-яА-Я]+")) {
                resultArray[0] = splitInput[i];
                resultArray[1] = splitInput[i + 1];
                resultArray[2] = splitInput[i + 2];
                i += 2;

                System.out.println("Фамилия: " + resultArray[0]);
                System.out.println("Имя: " + resultArray[1]);
                System.out.println("Отчество: " + resultArray[2]);
            }
        }
//        Если все элементы кончились проверить не пустые ли индексы 0, 1 и 2. Если да, выдать ошибку и перейти к пункту 1. Если индексы содержат строки, то идти дальше.
//        Посмотреть существует ли файл с таким названием? Если нет, создать. Если да, открыть.
//                Дописать в файл строку и перевод строки.
//        Предложить выйти из программы или вернуться к началу.


        return 0;
    }
}
