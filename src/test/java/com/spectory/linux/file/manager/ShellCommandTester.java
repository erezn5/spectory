package com.spectory.linux.file.manager;

import com.spectory.linux.file.manager.setup.EnvConf;
import com.spectory.linux.file.manager.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ShellCommandTester {

    private static final Logger logger = LoggerFactory.getLogger(ShellCommandTester.class);
    private static String mkdir = EnvConf.getProperty("mkdir.test.command");
    private static String mkfile = EnvConf.getProperty("mkfile.test.command");
    private static String ls = EnvConf.getProperty("ls.test.command");
    private static String cd = EnvConf.getProperty("cd.test.command");
    static String folderName;

    @Test(priority = 10)
    public void testMkdirCommand() throws IOException {
        System.out.print("shell>>");
        System.out.println(mkdir);
        folderName = fileExtractor(mkdir);
        FileUtil.mkdirCommand(folderName);
        Assert.assertTrue(FileUtil.isFolderExist(folderName), "Folder was not created as expected");
        logger.info("Folder was created successfully");
    }

    private String fileExtractor(String folderName) {
        return FileUtil.extractExtension(folderName);
    }

    @Test(priority = 20)
    public void testMkFileCommand()throws IOException {
        System.out.print("shell>>");
        System.out.println(mkfile);
        String fileName = fileExtractor(mkfile);
        FileUtil.mkfileCommand(fileName);
        Assert.assertTrue(FileUtil.isFolderExist(fileName), "File was not created as expected");
        logger.info("File was created successfully");

    }

    @Test(priority = 30)
    public void testCdCommand(){
        System.out.print("shell>>");
        System.out.println(cd);
        String folderName = fileExtractor(cd);
        FileUtil.cdCommand(folderName);
        FileUtil.lsCommand(folderName);
        Assert.assertEquals(FileUtil.folderSize(folderName),3);


    }

    @Test(priority = 40)
    public void testLsCommand(){
        System.out.print("shell>>");
        System.out.println(ls);
        String folderName = fileExtractor(cd);
        Assert.assertEquals(FileUtil.folderSize(folderName),3, "ls was not performed as expected");

    }

    @AfterClass()
    public static void tearDown() throws IOException {
        logger.info("Deleting all folders and files...");
        FileUtil.deleteDirectoryRecursion(folderName);
    }


}
