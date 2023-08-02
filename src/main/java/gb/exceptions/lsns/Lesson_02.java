package gb.exceptions.lsns;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lesson_02 {
    public static void main(String[] args) {
        String path = "D:/Dev/JAVA/Exceptions/src/main/names.txt";
        writeToFile(modify(getNames(path)), path);
    }

    public static List<String[]> getNames(String path) {
        List<String[]> names = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.split("="));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    public static List<String[]> modify(List<String[]> names) {
        for (String[] row : names) {
            if (!isValid(row[1]) && (!row[1].equals("?"))) {
                throw new RuntimeException("Некорректные данные: " + row[0] + "-->" + row[1]);
            }
            row[1] = String.valueOf(row[0].length());
        }
        return names;
    }

    public static void writeToFile(List<String[]> names, String path) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(path));
            for (String[] row :
                    names) {
                pw.println(row[0] + "=" + row[1]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) pw.close();
        }
    }

    public static boolean isValid(String testString) {
        try {
            Integer.parseInt(testString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
