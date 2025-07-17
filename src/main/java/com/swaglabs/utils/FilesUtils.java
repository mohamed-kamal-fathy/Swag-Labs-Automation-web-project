package com.swaglabs.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {
    private FilesUtils(){
    }


    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] filesList = folder.listFiles();
        if (filesList == null || filesList.length == 0)
        {
            LogsUtils.warn("No Files Found in the Directory: "+ folderPath);
            return null;
        }
        File latestFile = filesList[0];
        for (File file : filesList){
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }



    public static void deleteFiles(File dirPath){
        if(dirPath==null||!dirPath.exists())
        {
            LogsUtils.warn("Directory does not exist: "+ dirPath);
         }

        File[] filesList = dirPath.listFiles();
        if (filesList == null)
        {
            LogsUtils.warn("Directory is empty: "+ dirPath);
        }

        for (File file : filesList){
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {

                try {
                    Files.delete(file.toPath());
                }
                catch (IOException e) {
                    LogsUtils.error("Failed to delete file: "+ file.getAbsolutePath());

                }
            }
        }
    }
    public static void cleanDirectory(File file){
        try {
            FileUtils.cleanDirectory(file);
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }
    }
}
