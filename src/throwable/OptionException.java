package throwable;

import java.util.Scanner;

/**
 * Created by Calvin on 1/25/15.
 */
public class OptionException extends MyException{

    public OptionException() {
    }

    public OptionException(String msg) {
        super(msg);
    }

    @Override
    public void printException() {
        System.out.print(getExceptionMessage());
        System.out.println("The specified OptionSet/Option does not exist");

    }

    @Override
    public Object fixException() {
        System.out.println("Trying to fix error");
        System.out.print("Enter new Option(Set) Name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        return fileName;
    }

    @Override
    public String fixException(String optionName){
        System.out.println(optionName + " is not a Option");
        System.out.print("Please enter correct Option(Set) name (enter exit to quit): ");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        if(line.equalsIgnoreCase("exit")){
            return null;
        }else{
            return line;
        }
    }

}
