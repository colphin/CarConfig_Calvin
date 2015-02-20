package Driver;

import adapter.*;
import model.AutoGroup;
import throwable.*;

/**
 * Created by Calvin on 1/26/15.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        BuildAuto proxAuto = new BuildAuto();
        AutoGroup autoGroup = new AutoGroup();
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

        System.out.println("NOW TESTING LINKED HASH MAP");

        autoGroup.addAuto(proxAuto.getAutomotive());
        System.out.println(autoGroup.getAutoHashMap().get(proxAuto.getAutoName()).toString());
        autoGroup.removeAuto(proxAuto.getAutoName());


    }

}
