package adapter;

import throwable.*;

/**
 * Created by Calvin on 1/21/15.
 */
public interface CreateAuto {

    public void BuildAuto(String fileName) throws BlankFileException,FileFormatException, FileNameException;
    public void PrintAuto();

}
