package server;

import model.AutoGroup;
import model.Automotive;
import util.FileIO;

import java.net.*;
import java.io.*;
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
            while (true) {
                System.out.println("Now Listening");
                String input = null;
                input = (String) ois.readObject();
                //ois.reset();
                System.out.println(input);


                if (input.equals("Upload")){
                    Object obj = ois.readObject();

                    if (obj instanceof Properties){
                        Automotive auto = FileIO.readProperty((Properties)obj);
                        carGroup.addAuto(auto);
                        oos.writeInt(1);
                    }else{
                        System.out.println("Error: Not Properties");
                        oos.writeInt(0);
                    }

                    System.out.println("Recieved: " + obj.toString());
                }


            }
        }catch(Exception e){
            e.getStackTrace();
        }


    }
}