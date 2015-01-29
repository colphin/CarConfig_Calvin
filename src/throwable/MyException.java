package throwable;

/**
 * Created by Calvin on 1/25/15.
 */
public abstract class MyException extends Exception{
    private String exceptionMessage = "Error: ";

    public MyException(){
        printException();
    }

    public MyException(String exceptionMessage){
        this.setExceptionMessage(exceptionMessage);
        printException();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMsg) {
        this.exceptionMessage = exceptionMsg;
    }

    public abstract void printException();

    public abstract Object fixException();

    public abstract Object fixException(String fileName);

}
