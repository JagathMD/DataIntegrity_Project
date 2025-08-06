import java.io.*;
import java.util.List;

public class FileHandler {
    // Method to write multi-line content to a file
    public static void writeToFile(String filePath, List<String> content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : content) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    // Method to read multi-line content from a file
    public static String readFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString().trim();
    }
}


 