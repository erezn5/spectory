package com.spectory.linux.file.manager.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public  class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void lsCommand(String folderName){
        File file = new File(folderName);
        printArrayOfFiles(Objects.requireNonNull(file.listFiles()));
    }

    private static void printArrayOfFiles(File[] listOfFiles) {
        for (File child : listOfFiles) {
            if (child.isDirectory()) {
                logger.info(child.getName() + "/");
            } else {
                logger.info(child.getName());
            }
        }
    }

    public static void mkdirCommand(String folderName) throws IOException {
        createDirectory(folderName);
    }

    public static void mkfileCommand(String fileName) throws IOException {
        createFile(fileName);
    }

    private static void createFile(String fileName) throws IOException{
        Path path = Paths.get(fileName);
        if(!Files.exists(path)) {
            Files.createFile(path);
            logger.info("File Created successfully");
        }else{
            logger.info("File already exists");
        }
    }

    private static void createDirectory(String folderName) throws IOException {
        Path path= Paths.get(folderName);
        if(!Files.exists(path)) {
            Files.createDirectory(path);
            logger.info("Directory Created");
        }else{
            logger.info("Directory already exists");
        }

    }

    public static String cdCommand(String args) {
        File dir = new File(args);
        if(dir.isDirectory()){
            return dir.getAbsolutePath();
        }else{
            logger.info(args + " is not a directory");
        }
        return "no such folder";
    }

    public static boolean isFolderExist(String folder){
        Path path = Paths.get(folder);
        return Files.exists(path);
    }

    public static int folderSize(String path) {
        return new File(path).list().length;
    }

    public static void deleteDirectoryRecursion(String folder) throws IOException {
        FileUtils.deleteDirectory(new File(folder));
        logger.info("Folder deleted successfully");
    }

    public static boolean deleteFile(String filePath){
        File file = new File(filePath);
        return file.delete();
    }

    public static String extractExtension(String folderName) {
        return folderName.split(" ")[1];
    }
}

