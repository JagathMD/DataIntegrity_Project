import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String originalFilePath = "original.txt";  // Store the path of the original file

        try {
            // Phase 1: Create a text file and write multi-line messages
            System.out.println("Phase 1: Enter the content to store in the text file (type 'END' to finish):");
            List<String> messages = new ArrayList<>();
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) break; // Stop on 'END'
                messages.add(line);
            }
            FileHandler.writeToFile(originalFilePath, messages);
            System.out.println("Message stored in 'original.txt'.");

            // Phase 2: Read the text file and generate SHA-256 hash
            String originalContent = FileHandler.readFromFile(originalFilePath);
            String hash = HashGenerator.generateSHA256Hash(originalContent);
            System.out.println("Phase 2: Hash generated using SHA-256.");

            // Phase 3: Store the hash in another text file
            FileHandler.writeToFile("hash.txt", List.of(hash));
            System.out.println("Phase 3: Hash stored in 'hash.txt'.");

            // Phase 4: Ask the user if they want to check the integrity
            System.out.println("Phase 4: Do you want to check the integrity of the file? (yes/no):");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("yes")) {
                // Proceed with integrity check using the stored path
                String newHash = HashGenerator.generateSHA256Hash(FileHandler.readFromFile(originalFilePath));
                String storedHash = FileHandler.readFromFile("hash.txt");
                if (IntegrityVerifier.verifyIntegrity(storedHash, newHash)) {
                    System.out.println("Integrity Verified! The file has not been tampered with.");
                } else {
                    System.out.println("Integrity Check Failed! The file has been altered.");
                }
            } else {
                System.out.println("Integrity check aborted. Exiting program.");
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        scanner.close();
    }
}


