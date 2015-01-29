package throwable;


/**
 * Created by Calvin on 1/25/15.
 */
@SuppressWarnings("serial")
public class FileFormatException extends MyException {

    public FileFormatException(){
    }

    public FileFormatException(String exceptionMsg){
        super(exceptionMsg);
    }

    @Override
    public void printException(){
        System.out.println(getExceptionMessage());
        System.out.println("Input Format is wrong. Edit the you stuff");
    }

    @Override
    public Object fixException(){
        return null;
    }

    public Object fixException(String filename){
        return null;
    }
}
