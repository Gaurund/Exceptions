package gb.exceptions.lsns;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeWork03 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int result = 0;
        result = program();
        System.out.println(result);

//        LocalDateTime date = LocalDateTime.now();
//        System.out.println(date.getYear());
//        System.out.println(date.getMonthValue());
//        System.out.println(date.getDayOfMonth());

    }

    public static int program() {

        System.out.println("\nПожалуйста, введите ФИО, дату рождения, телефон и пол человека. " +
                "\nВ качества разделителя используйте пробел." +
                "\nДата рождения должна быть в формате dd.mm.yyyy." +
                "\nПри введении номера телефона не используйте пробел для разделения чисел. Иначе всё пойдёт по бороде." +
                "\nПол должен быть представлен одной латинской буквой. Для мужчин -- m, для женщин -- f.");

        String input = scanner.nextLine().trim();

        if (input.equals("")) return -1; // Код пустой строки.

        String[] splitInput = input.split("\\p{Space}+");

        if (splitInput.length < 6) return -2; // Код недостаточного ввода
        if (splitInput.length > 6) return -3; // Код избыточного ввода

        String[] resultArray = new String[6];
        for (int i = 0; i < 6; i++) {
            if (splitInput[i].equalsIgnoreCase("f") || splitInput[i].equalsIgnoreCase("m")) {
                resultArray[5] = splitInput[i];
            }
            else if (splitInput[i].matches("\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+\\p{Punct}?+\\d+")
                    && splitInput[i].length() != 10) {
                resultArray[4] = splitInput[i].replaceAll("[^0-9]", "");
            }
            else if (splitInput[i].matches("\\d\\d\\p{Punct}\\d\\d\\p{Punct}\\d\\d\\d\\d")
                    && splitInput[i].length() == 10) {
                resultArray[3] = splitInput[i];
                //                String[] date = splitInput[i].split("[^0-9]");
//                LocalDate present = LocalDate.now();
//                LocalDate validateDate = new LocalDate();
//                resultArray[3] = date;
            }
            else if (i < 4 && splitInput[i].matches("[а-яА-Я]+") && splitInput[i + 1].matches("[а-яА-Я]+") && splitInput[i + 2].matches("[а-яА-Я]+")) {
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
//        Предложить выйти из программы или вернуться к началу.


        return 0;
    }


}
