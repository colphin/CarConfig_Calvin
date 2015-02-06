package model;

import throwable.OptionException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Calvin on 1/25/15.
 */
public class OptionSet implements Serializable{

    private String name;
    private ArrayList<Option> options;
    private String userOption;

    //CONSTRUCTORS
    public OptionSet() {
        this.options = new ArrayList<Option>();
    }

    public OptionSet(String name) {
        this.name = name;
        this.options = new ArrayList<Option>();
    }

    public OptionSet(String name, int size) {
        this.name = name;
        options = new ArrayList<Option>(size);
        this.options = new ArrayList<Option>();
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public String getUserOption() {
        return userOption;
    }

    public void setUserOption(String s) {
        this.userOption = s;
    }

    public void addOption(String name, int price){
        options.add(new Option(name, price));
    }

    public Option getOption(String Name) throws OptionException {
        int i = findOption(name);
        if (i == -1){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return options.get(i);
        }
        else
            throw new OptionException("Invalid OptionSet/Option");
    }

    public void setOption(int i, String name, int price){
        Option opt = new Option(name, price);
        options.set(i,opt);
    }

    private int findOption(String name) {
        for (Option opt : options) {
            if (opt.name.equalsIgnoreCase(name)) {
                return options.indexOf(opt);
            }
        }
        return -1;
    }

    public  boolean hasOption(String name){
        int i = findOption(name);
        if (i != -1)
            return true;
        else
            return false;
    }

    public void setOptionName(String oldName, String newName) throws OptionException {
        if (this.hasOption(oldName)){
            this.getOption(oldName).setName(newName);
        }else{
            throw new OptionException("Error: Invalid Entry");
        }
    }

    public int getOptionPrice(String name) throws OptionException {
        int i = findOption(name);
        if (i != -1)
            return options.get(i).getPrice();
        else
            throw new OptionException("Error: Invalid Entry");
    }

    public void setOptionPrice(String name, int price) throws OptionException {
        int i = findOption(name);
        if (i != -1)
            options.get(i).setPrice(price);
        else
            throw new OptionException("Error: Invalid Entry");
    }

    public void removeOption(String name) throws OptionException {
        int i = this.findOption(name);
        if (i != -1)
            this.options.remove(i);
        else
            throw new OptionException("Error: Invalid Entry");
    }

    @Override
    public String toString() {
        return "OptionSet{" +
                "name='" + name + '\'' +
                ", options=" + options +
                '}';
    }

    //INNER CLASS OPTION
    protected class Option implements Serializable{

        private String name;
        private int price;

        public Option(){
        }

        public Option(String name, int price){
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Option{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

}
