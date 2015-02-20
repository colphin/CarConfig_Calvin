package Driver;

import adapter.*;
import throwable.*;

public class CarChoiceTest {

    public static void main (String[] args){
        BuildAuto proxAuto = new BuildAuto();
        String fileName = "InputTest.txt";
        boolean error;

        do{
            try {
                proxAuto.BuildAuto(fileName);
                //proxAuto.PrintAuto();
                error = false;
            }catch(MyException e){
                error = true;
                fileName = (String) e.fixException();
            }catch(NullPointerException e){
                System.out.println("FATAL ERROR");
                System.out.println("Working Directory = " +
                        System.getProperty("user.dir"));
                e.printStackTrace();
                break;
            }
        }while (error);

        try {
            proxAuto.setUserOption("Color", "Fort Knox Gold Clearcoat Metallic");
            proxAuto.setUserOption("Transmission", "Automatic");
            proxAuto.setUserOption("Brakes", "Standard");
            proxAuto.setUserOption("Side Impact Air Bags", "Present");
            proxAuto.setUserOption("Power Moonroof", "Present");
            try {
                proxAuto.printFinalConfiguration();
            }catch(FinalConfigException a){
                a.getExceptionMessage();
            }
        } catch (MyException e) {
            e.getExceptionMessage();
        }
    }
}
