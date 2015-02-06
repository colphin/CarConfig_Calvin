package Driver;

import model.*;
import throwable.*;
import util.FileIO;

/**
 * Created by Calvin on 1/25/15.
 */
public class CarConfigTest {

    public static void main(String[] args) {
        Automotive auto = null;
        String fileName = "InputTest.txt";
        boolean error;

        do{
            try{
//                System.out.println("Working Directory = " +
//                        System.getProperty("user.dir"));
                auto = FileIO.readFile(fileName);
                FileIO.seralize(auto,"Automotive.ser");
                error = false;
            }catch(MyException e){
                error = true;
                fileName = (String) e.fixException(fileName);
                if (fileName == null) {
                    System.out.println("GoodBye");
                    System.exit(1);
                }
            }
        }while (error);

        try {
            Object obj = FileIO.deseralize("Automotive.ser");
            if (obj instanceof Automotive)
                auto = (Automotive)obj;
        }catch(FileTypeExceptions e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n");
        auto.printOptionsAvailable();

    }


}
