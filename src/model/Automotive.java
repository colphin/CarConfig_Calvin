package model;

import java.io.Serializable;
import java.util.*;
import model.OptionSet.Option;
import throwable.*;


/**
 * CS35B
 *
 * @author Calvin Yin
 */

public class Automotive implements Serializable{

    private String make;
    private String model;
    private String name;
    private ArrayList<OptionSet> configOpt;
    private int basePrice;
    private int finalPrice;

    /**
     * empty Constructor
     */
    public Automotive(){
        this.configOpt = new ArrayList<OptionSet>();
    }

    /**
     * Constructor
     * @param make
     * @param Model
     */
    public Automotive(String make, String Model){
        this.make = make;
        this.model = Model;
        this.configOpt = new ArrayList<OptionSet>();
        setName();
    }

    /**
     * Constructor
     * @param make
     * @param model
     * @param basePrice
     */
    public Automotive(String make, String model, int basePrice){
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        this.configOpt = new ArrayList<OptionSet>();
        setName();

    }

    /**
     * Constructor
     * @param make
     * @param model
     * @param configOpt
     * @param basePrice
     */
    public Automotive (String make, String model, ArrayList<OptionSet> configOpt, int basePrice){
        this.make = make;
        this.model = model;
        this.configOpt = configOpt;
        this.basePrice = basePrice;
        this.configOpt = new ArrayList<OptionSet>();
        setName();

    }


    public void setName(){
        this.name = getMake() + " " + getModel();
    }

    public String getName(){
        return name;
    }

    /**
     * The get make for the car
     * @return attribute make
     */
    public String getMake() {
        return make;
    }

    /**
     * the get model for the car
     * @return attribute model
     */

    public String getModel() {
        return model;
    }
    /**
     * The get Base price for the car
     * @return attribute basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * The setter method for Make
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
        setName();
    }

    /**
     * The setter method for Model
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
        setName();
    }

    /**
     * The setter method for basePrice
     * @param basePrice
     */
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * The setter method for the final Config Option
     * @param configOpt
     */
    public void setConfigOpt(ArrayList<OptionSet> configOpt) {
        this.configOpt = configOpt;
    }

    /**
     * The checker method for OptionSet
     * @param name
     * @return
     */
    public boolean hasOptionSet(String name){
        int i = findOptionSet(name);
        if (i != -1)
            return true;
        else
            return false;
    }

    /**
     * The finder method for Option set
     * @param name
     * @return index of the Option set else return -1
     */
    public int findOptionSet(String name) {
        for (OptionSet opt : configOpt) {
            if (opt.getName().equalsIgnoreCase(name)) {
                return configOpt.indexOf(opt);
            }
        }
        return -1;
    }

    /**
     * The getter method for Option Set
     * @param name
     * @return The option set
     * @throws OptionException
     */
    public OptionSet getOptionSet(String name) throws OptionException {
        int i = findOptionSet(name);
        if (i != -1)
            return configOpt.get(i);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    /**
     * Adds an optionSet to configOpt
     * @param name
     */
    public void addOptionSet(String name){
        configOpt.add(new OptionSet(name));
    }

    /**
     * Adds an Option Set to Config Opt
     * @param name
     * @param count
     */
    public void addOptionSet(String name, int count){
        configOpt.add(new OptionSet(name, count));
    }

    /**
     * Getter for option
     * @param optionSetName
     * @param optionName
     * @return option if there exists one in listed Option set.
     * @throws OptionException
     */
    public Option getOption(String optionSetName, String optionName) throws OptionException {
        if (this.hasOptionSet(optionSetName))
            return this.getOptionSet(optionSetName).getOption(optionName);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    /**
     * Adds Option to option set if there is a option set
     * @param setName
     * @param name
     * @param price
     * @throws OptionException
     */
    public void addOption(String setName, String name, int price) throws OptionException {
        if (this.hasOptionSet(setName)) {
            this.getOptionSet(setName).addOption(name, price);
        } else {
            throw new OptionException("Error: Invalid Option Set");
        }
    }

    /**
     * Sets Option in option set if there is one
     * @param optionSetName
     * @param option
     * @param index
     * @param price
     * @throws OptionException
     * @throws IndexException
     */
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

    /**
     * Sets the user's option choice
     * @param optionSetName
     * @param optionName
     * @throws OptionException
     */
    public void setUserOption(String optionSetName, String optionName) throws OptionException{
        if(this.hasOptionSet(optionSetName)){
            if (this.getOptionSet(optionSetName).hasOption(optionName))
                this.getOptionSet(optionSetName).setUserOption(optionName);
            else
                throw new OptionException("Error: Invalid Option Name");
        }else {
            throw new OptionException("Error: Invalid Option Set Name");
        }
    }

    /**
     * Changes Option name
     * @param oldName
     * @param newName
     * @throws OptionException
     */
    public void setOptionName(String oldName, String newName)throws OptionException{
        if (this.hasOptionSet(oldName))
            this.getOptionSet(oldName).setOptionName(oldName, newName);
        else
            throw new OptionException("Error Invalid Option Set");
    }

    /**
     * Sets price of the Option in Option Set
     * @param optionSetName
     * @param optionName
     * @param price
     * @throws OptionException
     */
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

    /**
     * Sets Optionset name
     * @param oldName
     * @param newName
     * @throws OptionException
     */
    public void setOptionSetName(String oldName, String newName) throws OptionException{
        if (this.hasOptionSet(oldName)){
            this.getOptionSet(oldName).setName(newName);
        }else
            throw new OptionException("Error: Invalid OptionSet");
    }

    /**
     * Gets User's Option of the OptionSet
     * @param optionSetName
     * @return
     * @throws OptionException
     */
    public String getUserOption(String optionSetName) throws OptionException {
        if (this.hasOptionSet(optionSetName)) {
            return this.getOptionSet(optionSetName).getUserOption();
        } else {
            throw new OptionException("Error: Invalid Option Set");
        }
    }

    /**
     * Gets the Price of the Option in OptionSet
     * @param optionSetName
     * @param optionName
     * @return Price of Option
     * @throws OptionException
     */
    public int getOptionPrice(String optionSetName, String optionName) throws OptionException {
        if (this.hasOptionSet(optionSetName))
            return this.getOptionSet(optionSetName).getOptionPrice(optionName);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    /**
     * Prints final configuration of car
     */
    public void printFinalConfiguration(){
        System.out.println("**************************************\n"+ "Current Car Configuration for " + make + " " + model + "\n"+"Base Price: $" + basePrice + "\n" + "Configuration Options: \n");
        for (OptionSet i: configOpt){
            try{
                if (i.getUserOption() == null){
                    System.out.println("**************************************\n");
                    throw new FinalConfigException();
                }else {
                    System.out.println("___"+i.getName()+"___");
                    System.out.println(i.getUserOption() + ": " + i.getOptionPrice(i.getUserOption()));
                    System.out.println();
                }
            }catch(FinalConfigException e){
                e.getExceptionMessage();
                System.exit(1);
            }catch(OptionException f){
                f.fixException();
            }
        }
        calculateFinalPrice();
        System.out.println("\nFinal Price: $" + finalPrice);
        System.out.println("***************************\n");
    }

    /**
     * Prints possible options of the car
     */
    public void printOptionsAvailable() {
        System.out.println("**************************************\n"+ "Current Car Configuration for " + make + " " + model + "\n" + "Base Price: $" + basePrice + "\n\n" + "_Configuration Options_");
        for (OptionSet i : configOpt) {
            System.out.println(i.toString());
        }
        System.out.println("**************************************\n");
    }

    /**
     * Removes Option Set
     * @param name
     * @throws OptionException
     */
    public void removeOptionSet(String name) throws OptionException {
        int i  = this.findOptionSet(name);
        if (i != -1)
            this.configOpt.remove(i);
        else
            throw new OptionException("Error: Invalid Option Set");
    }

    /**
     * sets the final price doesn't return anything
     */
    public void calculateFinalPrice(){
        finalPrice  = basePrice;
        for (OptionSet optSet: configOpt){
            try{
                finalPrice += optSet.getOptionPrice(optSet.getUserOption());
            }catch(OptionException e){
                e.fixException();
            }
        }
    }

    /**
     * getter for Final Price
     * @return finalPrice
     */
    public int getFinalPrice(){
        return this.finalPrice;
    }

    /**
     * To String
     * @return the car and everything on it
     */
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
