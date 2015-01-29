package throwable;

import java.util.Scanner;

/**
 * Created by Calvin on 1/25/15.
 */
public class FileNameException extends MyException{
    public FileNameException() {
    }

    public FileNameException(String exceptionMsg){
        super(exceptionMsg);
    }

    @Override
    public void printException(){
        System.out.println(getExceptionMessage());
        System.out.println("File 404");
    }

    @Override
    public String fixException(){
        System.out.println("Trying to fix error");
        System.out.print("Enter new File Name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        return fileName;
    }

    @Override
    public String fixException(String fileName){
        System.out.println(fileName + " is not a file");
        System.out.print("Please enter correct file name (enter exit to quit): ");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        if(line.equalsIgnoreCase("exit")){
            return null;
        }else{
            return line;
        }
    }

}
