package adapter;
import model.*;
import throwable.*;
import util.*;



/**
 * Created by Calvin on 1/21/15.
 */
public abstract class proxyAutomobile{


    private static Automotive a1;

    public proxyAutomobile(){
        a1 = new Automotive();
    }

    public void BuildAuto(String fileName) throws BlankFileException, FileFormatException, FileNameException{
        //System.out.println("1-0");
        FileIO fIO = new util.FileIO();
        //System.out.println("1-1");
        a1 = fIO.readFile(fileName);
        //System.out.println("1-2");
    }

    public void PrintAuto(){
        //System.out.println("test");
        System.out.println(a1.toString());
    }

    public void updateOptionSetName(String OptionSetname, String newName) throws OptionException {
        if(a1.hasOptionSet(OptionSetname))
            a1.setOptionSetName(OptionSetname, newName);
        else
            throw new OptionException();
    }

    public void updateOptionPrice(String OptionName, String Option, int newPrice) throws OptionException{

        if(a1.hasOptionSet(OptionName)) {
            if (a1.getOptionSet(OptionName).hasOption(Option)){
                a1.getOptionSet(OptionName).setOptionPrice(Option, newPrice);
            }else
                throw new OptionException();
        }else
            throw new OptionException();
    }

}
