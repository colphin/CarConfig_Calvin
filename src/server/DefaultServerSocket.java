package server;

import model.AutoGroup;
import model.Automotive;
import throwable.FileFormatException;
import util.FileIO;

import java.net.*;
import java.io.*;
import java.util.ListIterator;
import java.util.Properties;

/**
 * Created by Calvin_Yin on 3/16/15.
 */
public class DefaultServerSocket extends Thread {

    private Socket sock;

    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    AutoGroup carGroup = new AutoGroup();


    //TODO: run, openConnection, closeConnection, handleSession

    public DefaultServerSocket(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try {
            System.out.println("default server socket started");
            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

            while (true) {
                System.out.println("\nNow Listening");
                String input = null;
                input = (String) ois.readObject();
                //ois.reset();
                System.out.println(input);

                //******
                //Uplaod choice
                //*****
                if (input.equals("Upload")){
                    Object obj = ois.readObject();

                    try{
                        Automotive auto = FileIO.readProperty((Properties)obj);
                        carGroup.addAuto(auto);
                        System.out.print("CarGroup: ");
                        ListIterator itr = carGroup.getKeyIterator();
                        while (itr.hasNext())
                            System.out.print(itr.next() + ", ");
                        System.out.println();
                    }catch(Exception e){
                        System.out.println("Error: Not Properties");
                    }

                    System.out.println("Received");
                    out.println("Received");
                }


                //******
                //Edit choice
                //*****
                if (input.equals("Edit")){
                    ListIterator itr = carGroup.getKeyIterator();
                    oos.writeObject(itr);
                }



                //******
                //Exit choice
                //*****
                if (input.equals("END Client")){
                    out.println("Bye");
                }


            }
        }catch(Exception e){
            System.out.println("");
        }


    }
}