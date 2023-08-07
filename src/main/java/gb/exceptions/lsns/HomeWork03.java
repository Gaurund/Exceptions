package gb.exceptions.lsns;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeWork03 {
//    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = 0;
        do {
            result = program();
            switch (result) {
                case -10:
                case -9:
                case -8:
                    System.out.println("\nПри вводе ФИО допущена ошибка." +
                            "\nДолжны использоваться только кириллические символы, никаких знаков пунктуации или чисел." +
                            "\nПовторите попытку.\n");
                    break;
                case -7:
                    System.out.println("\nДата рождения введена некорректно." +
                            "\nВвод должен соответствовать паттерну:" +
                            "\nДД.ММ.ГГГГ" +
                            "\nПовторите попытку.\n");
                    break;
                case -6:
                    System.out.println("\nПри вводе телефона допущена ошибка." +
                            "\nТелефон должен быть введён в международном формате (код страны, код населённого пункта, телефонный номер)." +
                            "\nДля разделения можно использовать любые удобные вам символы кроме пробела." +
                            "\nИдеальный вариант: оставить только цифры." +
                            "\nПовторите ввод.\n");
                    break;
                case -5:
                    System.out.println("\nПри вводе пола допущена ошибка." +
                            "\nПол должен быть представлен одной буквой." +
                            "\nДля мужчин -- m." +
                            "\nДля женщин -- f." +
                            "\nПовторите ввод.\n");
                    break;
                case -3:
                    System.out.println("\nСтрока введена некорректно." +
                            "\nДанных слишком много." +
                            "\nПовторите ввод.\n");
                    break;
                case -2:
                    System.out.println("\nВведено недостаточно данных." +
                            "\nПовторите ввод.\n");
                    break;
                case -1:
                    System.out.println("\nСтрока пустая." +
                            "\nПовторите ввод.\n");
                    break;
                case 0:
                    System.out.println("\nСпасибо за внимание! До новых встреч!");
            }
        }
        while (result != 0);
   }

    public static int program() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nПожалуйста, введите ФИО, дату рождения, телефон и пол человека. " +
                "\nВ качества разделителя используйте пробел." +
                "\nДата рождения должна быть в формате ДД.ММ.ГГГГ." +
                "\nПри введении номера телефона не используйте пробел для разделения чисел. Иначе всё пойдёт по бороде." +
                "\nПол должен быть представлен одной латинской буквой. Для мужчин -- m, для женщин -- f.");

        String input = scanner.nextLine().trim();

        try {
            int result = Integer.parseInt(input);
            if (result == 0) return 0;
        } catch (NumberFormatException e) {

        }
        if (input.equals("")) return -1; // Код пустой строки.

        String[] splitInput = input.split("\\p{Space}+");

        if (splitInput.length < 6) return -2; // Код недостаточного ввода
        if (splitInput.length > 6) return -3; // Код избыточного ввода

        String[] resultArray = new String[6];
        for (int i = 0; i < 6; i++) {
            if (splitInput[i].equalsIgnoreCase("f") || splitInput[i].equalsIgnoreCase("m")) {
                resultArray[5] = splitInput[i];
            } else if (splitInput[i].matches("\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+")
                    && splitInput[i].length() != 10) {
                resultArray[4] = splitInput[i].replaceAll("[^0-9]", "");
            } else if (splitInput[i].matches("\\d\\d\\p{Punct}\\d\\d\\p{Punct}\\d\\d\\d\\d")
                    && splitInput[i].length() == 10) {
                resultArray[3] = splitInput[i].replaceAll("[^0-9]", ".");
            } else if (i < 4 && splitInput[i].matches("[А-Яа-яё]+") && splitInput[i + 1].matches("[А-ЯЁа-яё]+") && splitInput[i + 2].matches("[А-ЯЁа-яё]+")) {
                resultArray[0] = splitInput[i];
                resultArray[1] = splitInput[i + 1];
                resultArray[2] = splitInput[i + 2];
                i += 2;
            }
        }

        for (int i = 0; i < 6; i++) {
            if (resultArray[i] == null) {
                return i - 10;
            }
        }

        System.out.println("\nВы ввели следующие данные: " +
                "\n\tФамилия: " + resultArray[0] +
                "\n\tИмя: " + resultArray[1] +
                "\n\tОтчество: " + resultArray[2] +
                "\n\tДата рождения: " + resultArray[3] +
                "\n\tТелефон: " + resultArray[4] +
                "\n\tПол: " + resultArray[5]);

        String filePath = resultArray[0] + ".txt";
        StringBuilder savingText = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            savingText.append("<");
            savingText.append(resultArray[i]);
            savingText.append(">");
        }
        savingText.append("\n");

        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bufferWriter = new BufferedWriter(writer)) {
            bufferWriter.write(savingText.toString());
        } catch (IOException e) {
            e.getMessage();
        }

        System.out.println("\nЕсли вы хотите продолжить введите 1, если хотите закончить введите всё что угодно.");
        try {
            int result = scanner.nextInt();
            if (result == 1) return 1; // Код повторения
        } catch (InputMismatchException e) {

        }
        return 0;
    }
}
