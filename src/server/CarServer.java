package server;

import java.net.*;
import java.io.*;
import java.util.Properties;

import model.*;
import throwable.FileTypeExceptions;
import util.FileIO;

/**
 * Created by Calvin_Yin on 3/12/15.
 */
public class CarServer{

    private ServerSocket serverSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int port = 4444;


    public CarServer(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Listening to Port:" + port);
        }catch(IOException a){
            System.out.println("Error: could not listen to port: "+ port);
        }
    }

    public void runTheDamnServer(){
        DefaultServerSocket defaultServerSocket = null;
        System.out.println("Server is now running.");
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            defaultServerSocket = new DefaultServerSocket(clientSocket);
            defaultServerSocket.run();
        }
    }

    public static void main(String[] args) {
        CarServer asdf = new CarServer(4444);
        asdf.runTheDamnServer();
    }


}
