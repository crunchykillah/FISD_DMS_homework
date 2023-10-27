import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
    private String filename;
    public MyFileWriter(String filename) {
        this.filename = filename;
    }

    public void writeToFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(text);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String findMaxNumberString(String filename) {
        String maxNumberString = "";
        int maxNumber = Integer.MIN_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    int number = Integer.parseInt(parts[1]);
                    if (number > maxNumber) {
                        maxNumber = number;
                        maxNumberString = line;
                    }
                } else {
                    return "Вы единственный игрок в рейтинге!";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxNumberString;
    }
}