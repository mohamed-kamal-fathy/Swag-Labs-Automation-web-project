package utilities.dataReader;

import utilities.logs.LogsUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FilesUtils{
    private  FilesUtils() {super();}


    public static File getLatestFile(String folderPath) {
        if (folderPath == null || folderPath.trim().isEmpty()) {return null;}
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {return null;}
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {return null;}

        File latestFile = files[0];
        for (File file : files) {
            if (file.isFile() && file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }

        return latestFile;
    }
    public static void deleteFiles(File dirPath) {
        // Check if directory is null or doesn't exist
        if (dirPath == null || !dirPath.exists()) {
            LogsUtil.warn("Directory does not exist: " + dirPath);
            return;
        }

        // Get list of files in the directory
        File[] filesList = dirPath.listFiles();
        if (filesList == null) {
            LogsUtil.warn("Failed to list files in: " + dirPath);
            return;
        }

        // Process each file/directory
        for (File file : filesList) {
            if (file.isDirectory()) {
                // Recursively delete subdirectory
                deleteFiles(file);

                // After deleting contents, try to delete the directory itself
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtil.error("Failed to delete directory: " + file);
                }
            } else {
                // Delete file
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtil.error("Failed to delete file: " + file);
                }
            }
        }
    }

    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> generateUniqueNumber(int numberOfProductNeeded, int totalNumberOfProducts) {
        Set<Integer> generateNumbers = new HashSet<>();
        while (generateNumbers.size() < numberOfProductNeeded) {
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generateNumbers.add(randomNumber);
        }
        return generateNumbers;
    }
}
