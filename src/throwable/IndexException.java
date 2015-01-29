package throwable;

/**
 * Created by Calvin on 1/25/15.
 */
public class IndexException extends MyException {
    public IndexException(){

    }

    public IndexException(String msg){
        super(msg);
    }

    @Override
    public void printException() {
        System.out.println(getExceptionMessage());
        System.out.println("The specified OptionSet/Option does not exist");

    }

    @Override
    public Object fixException() {
        return null;
    }

    @Override
    public String fixException(String fileName){
        return null;
    }
}
