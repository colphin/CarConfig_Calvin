package adapter;
import model.*;
import throwable.OptionException;

/**
 * Created by Calvin on 1/21/15.
 */
public interface UpdateAuto {

    public void updateOptionSetName(String OptionSetname, String newName) throws OptionException;
    public void updateOptionPrice(String Optionname, String Option, int newprice) throws OptionException;
}
