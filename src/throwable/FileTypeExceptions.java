package throwable;

import java.util.*;

/**
 * Created by Calvin on 1/25/15.
 */
public class FileTypeExceptions extends MyException {

    public FileTypeExceptions(){
    }

    public FileTypeExceptions(String exceptionMsg){
        super(exceptionMsg);
    }

    @Override
    public void printException(){
        System.out.println(getExceptionMessage());
        System.out.println("not a .txt file.");
    }

    @Override
    public String fixException(){
        System.out.println("Enter File Name");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        return fileName;
    }

    @Override
    public String fixException(String fileName){
        System.out.println(fileName + " is not a file");
        System.out.println("Please enter correct file name (enter exit to quit): ");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        if(line.equalsIgnoreCase("exit")){
            return null;
        }else{
            return line;
        }
    }

}
