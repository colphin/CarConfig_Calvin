package adapter;
import model.*;
import throwable.*;
import util.*;

import java.util.LinkedHashMap;
import java.util.Properties;


/**
 * Created by Calvin on 1/21/15.
 */
public abstract class proxyAutomobile{

    //TODO: Create a Linked Hash Map of Automotives, AutoMap class should be in model package

    private static Automotive a1;
    private static AutoGroup autoHashMap;
    //private static LinkedHashMap<String, Automotive> autoHashMap = new LinkedHashMap<String, Automotive>();

    public proxyAutomobile(){
        a1 = new Automotive();
        autoHashMap = new AutoGroup();
    }

    public void BuildAuto(String fileName) throws BlankFileException, FileFormatException, FileNameException{
        //System.out.println("1-0");
        FileIO fIO = new util.FileIO();
        //System.out.println("1-1");
        a1 = fIO.readFile(fileName);
        //System.out.println("1-2");
        autoHashMap.addAuto(a1);
    }

    public void PrintAuto(String name){
        //System.out.println("test");
        System.out.println(autoHashMap.getAutoHashMap().get(name).toString());
    }

    public void PrintAuto(){
        System.out.println(a1.toString());
    }


    public void updateOptionSetName(String OptionSetname, String newName) throws OptionException {
        if(a1.hasOptionSet(OptionSetname))
            a1.setOptionSetName(OptionSetname, newName);
        else
            throw new OptionException();
    }

    public void updateOptionSetName(String carName, String OptionSetname, String newName) throws OptionException {
        if(autoHashMap.getAutoHashMap().get(carName).hasOptionSet(OptionSetname))
            autoHashMap.getAutoHashMap().get(carName).setOptionSetName(OptionSetname, newName);
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

    public void updateOptionPrice(String carName, String OptionName, String Option, int newPrice) throws OptionException{
        if(autoHashMap.getAutoHashMap().get(carName).hasOptionSet(OptionName)) {
            if (autoHashMap.getAutoHashMap().get(carName).getOptionSet(OptionName).hasOption(Option)){
                autoHashMap.getAutoHashMap().get(carName).getOptionSet(OptionName).setOptionPrice(Option, newPrice);
            }else
                throw new OptionException();
        }else
            throw new OptionException();
    }
    
    public void setUserOption(String s1, String s2) throws OptionException{
    	a1.setUserOption(s1, s2);
    }

    public void setUserOption(String carName,String s1, String s2) throws OptionException{
        autoHashMap.getAutoHashMap().get(carName).setUserOption(s1, s2);
    }

    public String getUserOption(String s1) throws OptionException{
    	return a1.getUserOption(s1);
    }

    public String getUserOption(String carName, String s1) throws OptionException{
        return autoHashMap.getAutoHashMap().get(carName).getUserOption(s1);
    }

    public Automotive getAutomotive(){
        return a1;
    }

    public String getAutoName(){
        return a1.getName();
    }

    
    public void printFinalConfiguration() throws FinalConfigException{
    	a1.printFinalConfiguration();
    }

    public void printFinalConfiguration(String carName) throws FinalConfigException{
        autoHashMap.getAutoHashMap().get(carName).printFinalConfiguration();
    }

    public void buildAutoFromPropertyObj(Properties propertyObj)throws Exception{
        Automotive newAuto = null;
        newAuto = FileIO.readProperty(propertyObj);

        if (newAuto != null){
            autoHashMap.addAuto(newAuto);
        }
    }

}
