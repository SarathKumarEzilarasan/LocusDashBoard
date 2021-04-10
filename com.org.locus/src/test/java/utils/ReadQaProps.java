package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadQaProps {

    public static Properties properties;
    public static ReadQaProps readQaProps;
    public static String token;

    private ReadQaProps() {
        this.properties = new Properties();
        loadFile();
    }

    public static ReadQaProps inst() {
        if (readQaProps == null)
            readQaProps = new ReadQaProps();
        return readQaProps;
    }

    public void loadFile() {
        InputStream input = null;
        try {
            File config = new File("resources/Properties.properties");
            input = new FileInputStream(config);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}