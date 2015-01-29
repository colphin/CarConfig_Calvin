package throwable;

import java.util.Scanner;

/**
 * Created by Calvin on 1/25/15.
 */
@SuppressWarnings("serial")
public class BlankFileException extends MyException {

    public BlankFileException(){
    }

    public BlankFileException(String exceptionMsg){
        super(exceptionMsg);
    }

    @Override
    public void printException(){
        System.out.println(getExceptionMessage());
        System.out.println("File is blank, you killed it jim.");
    }

    @Override
    public String fixException(){
        System.out.println("Please enter correct file name (enter exit to quit): ");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        if(line.equalsIgnoreCase("exit")){
            return null;
        }else{
            return line;
        }
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
