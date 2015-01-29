package Driver;

import adapter.*;
import throwable.*;

/**
 * Created by Calvin on 1/26/15.
 */
public class ProxyAutoTest {

    public static void main(String[] args) {
        BuildAuto proxAuto = new BuildAuto();
        String fileName = "InputTest.txt";
        boolean error;

        //System.out.println("START");

        do{
            try {
                //System.out.println("0");
                proxAuto.BuildAuto(fileName);
                //System.out.println("1");;

                proxAuto.PrintAuto();

                //System.out.println("END");

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

        boolean error2;
        String optionSetName = "Brakes";
        String newOptionSetName = "NEW AND IMPORVED BRAKES";
        do {
            try {
                proxAuto.updateOptionSetName(optionSetName, newOptionSetName);
                error2 = false;
                proxAuto.PrintAuto();
            } catch (OptionException e) {
                System.out.println(e.getMessage());
                error2 = true;
                newOptionSetName = (String) e.fixException(fileName);
                if (fileName == null) {
                    System.out.println("GoodBye");
                    System.exit(1);
                }
            }
        }while (error2);

        boolean error3;
        String optionName = "dummy_test";   //Dummy test
//        String optionName = "Standard";     //Actual one
        do {
            try {
                proxAuto.updateOptionPrice(newOptionSetName, optionName, 9999);
                error3 = false;
                proxAuto.PrintAuto();
            } catch (OptionException e) {
                error3 = true;
                optionName = (String) e.fixException(optionName);
                if (optionName == null) {
                    System.out.println("GoodBye");
                    System.exit(1);
                }
            }
        }while (error3);
    }

}
