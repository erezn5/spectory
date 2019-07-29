package com.spectory.linux.file.manager.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvConf {

    private static Properties prop;
    private static File APP_PROPERTIES_FILE = new File("src/main/resources/app/app.properties");

    private EnvConf(){}

    static {
        load();
    }

    private static void load(){

        try(InputStream input = new FileInputStream(APP_PROPERTIES_FILE.getAbsoluteFile())){
            prop = new Properties();
            prop.load(input);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
