package driver;

import adapter.BuildAuto;
import model.AutoGroup;
import throwable.MyException;
import scale.*;

/**
 * Created by Calvin_Yin on 2/23/15.
 */
public class MultiThreadingTest {

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

        autoGroup.addAuto(proxAuto.getAutomotive());
        EditOptions bigmama = new EditOptions(autoGroup);
        EditOptions biddaddy = new EditOptions(autoGroup);
        Thread t1 = new Thread(bigmama);
        Thread t2 = new Thread(biddaddy);
        try {
            t1.start();
            t1.join();
            t2.start();
        }catch(InterruptedException e){
            System.out.println("Error: "+ e.toString());
        }

    }

}
