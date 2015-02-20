package model;

import util.FileIO;
import throwable.*;

import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.ArrayList;

/**
 * Created by Calvin_Yin on 2/9/15.
 */
public class AutoGroup {

    //TODO add the usual functions for Automotive class (MAIN: add and remove)

    private LinkedHashMap<String, Automotive> autoHashMap = new LinkedHashMap<String, Automotive>();

    public AutoGroup() {
    }

    public AutoGroup(LinkedHashMap<String, Automotive> autoHashMap) {
        this.autoHashMap = autoHashMap;
    }

    public LinkedHashMap<String, Automotive> getAutoHashMap() {
        return autoHashMap;
    }

    public void addAuto(String fileName){
        try {
            autoHashMap.put(FileIO.readFile(fileName).getName(),FileIO.readFile(fileName));
        } catch(MyException e){
            e.fixException();
        }
    }

    public void addAuto(Automotive a){
        autoHashMap.put(a.getName(), a);
    }

    public void removeAuto(String carName){
        autoHashMap.remove(carName);
    }


    public ListIterator<String> getKeyIterator(){
        ArrayList<String> dummy = new ArrayList<String>();
        for (String key: autoHashMap.keySet()){
            dummy.add(key);
        }
        return dummy.listIterator();
    }

    public ListIterator<Automotive> getValueIterator(){
        ArrayList<Automotive> dummy = new ArrayList<Automotive>();
        for (Automotive value: autoHashMap.values()){
            dummy.add(value);
        }
        return dummy.listIterator();
    }

}
