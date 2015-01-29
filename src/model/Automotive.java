package model;

import java.io.Serializable;
import java.util.*;
import model.OptionSet.Option;
import throwable.*;


/**
 * Created by Calvin on 1/25/15.
 */
public class Automotive implements Serializable{

    private String make;
    private String model;
    private ArrayList<OptionSet> configOpt;
    private int basePrice;
    private int finalPrice;

    public Automotive(){
        this.configOpt = new ArrayList<OptionSet>();
    }

    public Automotive(String make, String Model){
        this.make = make;
        this.model = model;
        this.configOpt = new ArrayList<OptionSet>();

    }

    public Automotive(String make, String model, int basePrice){
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        this.configOpt = new ArrayList<OptionSet>();

    }

    public Automotive (String make, String model, ArrayList<OptionSet> configOpt, int basePrice){
        this.make = make;
        this.model = model;
        this.configOpt = configOpt;
        this.basePrice = basePrice;
        this.configOpt = new ArrayList<OptionSet>();

    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setConfigOpt(ArrayList<OptionSet> configOpt) {
        this.configOpt = configOpt;
    }

    public boolean hasOptionSet(String name){
        int i = findOptionSet(name);
        if (i != -1)
            return true;
        else
            return false;
    }

    private int findOptionSet(String name) {
        for (OptionSet opt : configOpt) {
            if (opt.getName().equalsIgnoreCase(name)) {
                return configOpt.indexOf(opt);
            }
        }
        return -1;
    }

    public OptionSet getOptionSet(String name) throws OptionException {
        int i = findOptionSet(name);
        if (i != -1)
            return configOpt.get(i);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    public void addOptionSet(String name){
        configOpt.add(new OptionSet(name));
    }

    public void addOptionSet(String name, int count){
        configOpt.add(new OptionSet(name, count));
    }

    public Option getOption(String optionSetName, String optionName) throws OptionException {
        if (this.hasOptionSet(optionSetName))
            return this.getOptionSet(optionSetName).getOption(optionName);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    public void addOption(String setName, String name, int price) throws OptionException {
        if (this.hasOptionSet(setName)) {
            this.getOptionSet(setName).addOption(name, price);
        } else {
            throw new OptionException("Error: Invalid Option Set");
        }
    }

    public void setOption(String optionSetName, String option, int index, int price) throws OptionException, IndexException {
        if (this.hasOptionSet(optionSetName)){
            try{
                this.getOptionSet(optionSetName).setOption(index, option, price);
            }catch(OptionException e){
                throw new IndexException("Error: Invalid Index");
            }
        }else
            throw new OptionException("Error: Invalid Option Set");
    }

//    public void setOptionChoice(String optionSetName, String optionName) throws OptionException{
//        if(this.hasOptionSet(optionSetName)){
//            if (this.getOptionSet(optionSetName).hasOption(optionName))
//                this.getOptionSet(optionSetName).setOptionChoice(optionName);
//            else
//                throw new OptionException("Error: Invalid Option Name");
//        }else {
//            throw new OptionException("Error: Invalid Option Set Name");
//        }
//    }

    public void setOptionName(String oldName, String newName)throws OptionException{
        if (this.hasOptionSet(oldName))
            this.getOptionSet(oldName).setOptionName(oldName, newName);
        else
            throw new OptionException("Error Invalid Option Set");
    }

    public void setOptionPrice(String optionSetName, String optionName, int price) throws OptionException{
        if(this.hasOptionSet(optionSetName)){
            if (this.getOptionSet(optionSetName).hasOption(optionName))
                this.getOptionSet(optionSetName).getOption(optionName).setPrice(price);
            else
                throw new OptionException("Error: Invalid Option Name");
        }else {
            throw new OptionException("Error: Invalid Option Set Name");
        }
    }

    public void setOptionSetName(String oldName, String newName) throws OptionException{
        if (this.hasOptionSet(oldName)){
            this.getOptionSet(oldName).setName(newName);
        }else
            throw new OptionException("Error: Invalid OptionSet");
    }

    public String getOptionChoice(String optionSetName) throws OptionException {
        if (this.hasOptionSet(optionSetName)) {
            return this.getOptionSet(optionSetName).getOptionChoice();
        } else {
            throw new OptionException("Error: Invalid Option Set");
        }
    }

    public int getOptionPrice(String optionSetName, String optionName) throws OptionException {
        if (this.hasOptionSet(optionSetName))
            return this.getOptionSet(optionSetName).getOptionPrice(optionName);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    public ListIterator<String> getOptionSetNameIter(){
        ArrayList<String> optionSetName = new ArrayList<String>();
        for (OptionSet i: configOpt)
            optionSetName.add(i.getName());
        return optionSetName.listIterator();
    }

    public ListIterator<String> getOptionNameIter(String optionSetName) throws OptionException {
        if (this.hasOptionSet(optionSetName))
            return this.getOptionSet(optionSetName).getOptionNamesItr();
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    public void printFinalConfiguration(){
        System.out.println("**************************************\n"+ "Current Car Configuration for " + make + " " + model + "\n"+"Base Price: $" + basePrice + "\n" + "Configuration Options: \n");
        for (OptionSet i: configOpt){
            System.out.println(i.toString());
        }
        System.out.println("***************************\n");
    }

    public void printOptionsAvailable() {
        System.out.println("**************************************\n"+ "Current Car Configuration for " + make + " " + model + "\n" + "Base Price: $" + basePrice + "\n\n" + "_Configuration Options_");
        for (OptionSet i : configOpt) {
            System.out.println(i.toString());
        }
        System.out.println("**************************************\n");
    }

    public void removeOptionSet(String name) throws OptionException {
        int i  = this.findOptionSet(name);
        if (i != -1)
            this.configOpt.remove(i);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    public void calcFinalPrice(){
        finalPrice  = basePrice;
        for (OptionSet optSet: configOpt){
            try{
                finalPrice += optSet.getOptionPrice(optSet.getOptionChoice());
            }catch(OptionException e){
                e.fixException();
            }
        }
    }

    public String toString() {
        StringBuffer output = new StringBuffer();
        String newline = "\n";

        output.append(newline + "Make:" + make + newline);
        output.append("Model:" + model + newline);
        output.append("Base Price:$" + basePrice + newline);
        output.append("\nConfiguration Options:" + newline);
        for (OptionSet optSet : configOpt) {
            output.append(optSet.toString() + newline);
        }
        return output.toString();
    }
}
