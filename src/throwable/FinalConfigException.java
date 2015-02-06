package throwable;

/**
 * Created by Calvin_Yin on 2/5/15.
 */
public class FinalConfigException extends MyException {

    public FinalConfigException(){
    }

    public FinalConfigException(String exceptionMsg){
        super(exceptionMsg);
    }

    @Override
    public void printException(){
        System.out.println(getExceptionMessage());
        System.out.println("Final Configuration is Missing Something");
    }

    @Override
    public Object fixException(){
        return null;
    }

    public Object fixException(String filename){
        return null;
    }





}
