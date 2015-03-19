package scale;

import model.*;
import throwable.*;
import adapter.proxyAutomobile;

import java.util.Scanner;
import java.util.Iterator;

/**
 * Created by Calvin_Yin on 2/23/15.
 */
public class EditOptions extends proxyAutomobile implements Runnable {

    static AutoGroup carGroup;
    boolean running = true;

    public EditOptions(AutoGroup carGroup){
        super();
        EditOptions.carGroup = carGroup;
    }

    public synchronized String readFromConsole(String query) throws MyException{
//        String threadIndication = "\n" + Thread.currentThread().getName() + "\n";
        System.out.print(query);
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }

    public synchronized boolean carInMap(String name){
        Object value = carGroup.getAutoHashMap().get(name);
        return value != null;
    }

    public void terminate(){
        running = false;
    }

    @Override
    public synchronized void run() {
        while (running) {
            String threadIndication = "\n" + Thread.currentThread().getName() + "\n";
            System.out.println(threadIndication);
            Iterator dummy = carGroup.getKeyIterator();
            System.out.println("____List of Cars____");
            while (dummy.hasNext()) {
                System.out.println(dummy.next());
            }
            System.out.println("––––––––––––––––––––");
            try {
                String tempName = readFromConsole("Enter Name of Car you want to Edit(Type Stop to end Program)\nSelection: ");
                Automotive carToFuckUp;
                if (tempName.compareToIgnoreCase("stop")==0){
                    running = false;
                    break;
                }
                if (carInMap(tempName)) {
                    carToFuckUp = carGroup.getAuto(tempName);
                } else {
                    throw new NullPointerException();
                }
                boolean notValidEntry = true;
                boolean notEnd = true;
                while (notValidEntry && notEnd) {
                    int editSelection = Integer.parseInt(readFromConsole("Do you want to edit:\n 1.Automotive\n 2.OptionSet\n 3.Option\n 4.Exit\nSelection: "));
                    switch (editSelection) {
                        case 1:     //edit Automotive
                            notValidEntry = false;
                            String oldName = carToFuckUp.getName();
                            System.out.println("Current car name: " + oldName);
                            carToFuckUp.setName(readFromConsole("Enter New Car Name: "));
                            carGroup.addAuto(carToFuckUp);
                            carGroup.removeAuto(oldName);
                            System.out.println("Name set to: " + carToFuckUp.getName());
                            break;
                        case 2:     //edit OptionSet
                            carToFuckUp.printOptionSetAvailable();
                            String userChoice = readFromConsole("Enter Option Choice to edit: ");
                            String newOptionSetName = readFromConsole("New OptionSet Name: ");
                            carToFuckUp.getOptionSet(userChoice).setName(newOptionSetName);
                            System.out.println("Name set to: " + carToFuckUp.getOptionSet(newOptionSetName).getName());
                            notValidEntry = false;
                            break;
                        case 3:     //edit Option
                            notValidEntry = false;
                            //todo: add shit you need still not working
                            carToFuckUp.printOptionSetAvailable();
                            String userChoice1 = readFromConsole("Enter OptionSet Choice to edit: ");
                            carToFuckUp.printOptionsAvailable(carToFuckUp.getOptionSet(userChoice1));
                            String userChoice2 = readFromConsole("Enter Option Choice to edit: ");
                            String userChoice3 = readFromConsole("Enter new Option Name: ");
                            System.out.println(userChoice1 + " - " + userChoice2 + " - " + userChoice3  );
                            System.out.println(carToFuckUp.getOptionSet(userChoice1).getOption(userChoice3));
                            System.out.println("end");
                            break;
                        case 4:
                            System.out.println("GoodBye");
                            running = false;
                            notEnd = false;
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("\nERROR: " + e.toString() + "\n");
            }
        }
        String currentThread = "\n" + Thread.currentThread().getName();
        System.out.println(currentThread + ": END");
        running = true;
    }
}
