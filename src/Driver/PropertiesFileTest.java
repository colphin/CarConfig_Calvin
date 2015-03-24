package driver;

import adapter.BuildAuto;
import model.Automotive;
import util.*;
import throwable.*;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Calvin_Yin on 3/22/15.
 */
public class PropertiesFileTest {

    private static Automotive proxAuto = new Automotive();

    public static void main(String[] args) {
        PropertiesFileTest asdf = new PropertiesFileTest("FordFocusZTW.properties");
        System.out.println(proxAuto.toString());
    }

    public PropertiesFileTest(String fileName){
        try {

            File file = new File("FordFocusZTW.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

//            Enumeration enuKeys = properties.keys();
//            while (enuKeys.hasMoreElements()) {
//                String key = (String) enuKeys.nextElement();
//                String value = properties.getProperty(key);
//                System.out.println(key + ": " + value);
//            }

            proxAuto = FileIO.readProperty(properties);

        }catch (IOException e){
            System.out.println("SON OF A MOTHER FUCKER WHAT IN THE WORLD IS WORKING?");
        }catch (Exception e){
            System.out.println("SHIT SOMETHING ISN'T WORKING AGAIN");
        }
    }

}
