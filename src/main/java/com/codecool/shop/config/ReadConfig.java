package com.codecool.shop.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
    private Properties property;
    String fileName;

    public ReadConfig(String fileName) {
        this.fileName = fileName;
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProperties() throws IOException {
        InputStream inputStream = null;

        try {
            property = new Properties();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            System.out.println(classloader.getClass());
            inputStream = classloader.getResourceAsStream(fileName);

            if (inputStream != null) {
                property.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public Properties getProperty() {
        return property;
    }
}
