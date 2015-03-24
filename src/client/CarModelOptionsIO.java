package client;

import adapter.BuildAuto;
import adapter.CreateAuto;
import model.Automotive;
import throwable.MyException;
import util.FileIO;

import java.io.*;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.*;


/**
 * Created by Calvin_Yin on 2/28/15.
 */
public class CarModelOptionsIO {

    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    private Socket socket;

    public CarModelOptionsIO(Socket sock) {
        this.socket = sock;
    }

    public static void main(String[] args) {
        try {
            //System.out.println("0");
            Socket temp_sock = new Socket(InetAddress.getLocalHost(),4444);
            //System.out.println("1");
            CarModelOptionsIO asdf = new CarModelOptionsIO(temp_sock);
            //System.out.println("2");
            asdf.run();
            //System.out.println("3");
        }catch(Exception e) {
            System.out.println("ERROR: something broke, bug hunting time");
            e.printStackTrace();
        }
    }

    public void run() {
        if (openConnection()) {
            while (true) {
                handleSession();
            }
        }
        closeSession();

    }

    public boolean openConnection() {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            System.out.println("Error: could not connect to server");
            return false;
        }

    }

    public void closeSession() {
        try {
            oos.close();
            ois.close();
        } catch (IOException e) {
            System.out.println("Error Closing Connection");
        }
    }

    public String readFromConsole(String query) throws MyException {
        System.out.print(query);
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }

    public void handleSession() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n");
            System.out.println("Enter What you want to do");
            System.out.println("1. Upload Car Properties");
            System.out.println("2. Edit Car Options");
            System.out.println("3. EXIT");

            int choice = 0;
            boolean isNotInt = true;

            while(isNotInt) {
                try {
                    choice = Integer.parseInt(in.readLine());
                    isNotInt = false;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
            }

            switch (choice) {
                case 1: {
                    System.out.println("Upload Selected");
                    oos.flush();
                    oos.writeObject("Upload");
                    oos.flush();
                    String fileName = null;
                    fileName = readFromConsole("What is the name of the file you want to upload: ");
                    //String fileName = "FordFocusZTW.properties";

                    File file = new File(fileName);
                    FileInputStream fileInput = new FileInputStream(file);
                    Properties properties = new Properties();
                    properties.load(fileInput);
                    fileInput.close();

                    oos.writeObject(properties);
                    System.out.println(fileName + " is selected.");

                    String fromServer = null;

                    BufferedReader bfIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while ((fromServer = bfIn.readLine()) != null) {
                        if (fromServer.equals("Received")) {
                            System.out.println("File Transfer Success");
                            break;
                        }
                    }



//                    int response = ois.readInt();
//                    System.out.println(response);
//
//                    if (response == 1)
//                        System.out.println("Successful File Transfer");
//                    if (response == 0)
//                        System.out.println("Error: File Transfer");

                    //socket.shutdownOutput();

                    break;
                }
                case 2: {
                    oos.writeObject("Edit");
                    oos.flush();

                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    ois.skip(Long.MAX_VALUE);

                    ArrayList<String> cars = (ArrayList<String>) ois.readObject();
                    System.out.print("Cars available: ");
                    System.out.println(cars);s

                    String editCar = readFromConsole("Car you want to Edit: ");
                    String editCar_temp = editCar;

                    break;
                }

                case 3: {
                    oos.writeObject("END Client");
                    oos.flush();
                    System.out.println("Closing Session");

                    closeSession();
                    System.out.println("Session closed");
                    System.out.println("System Exit");
                    System.exit(1);
                    break;
                }
                default:{
                    System.out.println("Error: Invalid Entry");
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
        } catch (MyException e) {
            System.out.println("Error: Invalid name");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Could not read object");
        }

    }
}






















//    public void handleSession(){
//        //todo: small menu sends the data to the server side
//        try{
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Enter What you want to do");
//            System.out.println("1. Upload Car Properties");
//            System.out.println("2. Edit Car Optoins");
//
//            int choice= Integer.parseInt(in.readLine());
//
//            switch(choice){
//                case 1: {
//                    oos.writeObject("Upload");
//                    oos.flush();
//                    String fileName = readFromConsole("What is the name of the file you want to upload: ");
//
//                    File file = new File(fileName);
//
//                    bos = new BufferedOutputStream(oos, 1024);
//                    fis = new FileInputStream(file);
//
//                    byte[] bit = new byte[1024];
//                    int bitcount = 1024;
//                    int i = 0;
//
//                    while ((i = fis.read(bit, 0, 1024)) != -1) {
//                        bitcount += 1024;
//                        bos.write(bit, 0, i);
//                        bos.flush();
//                    }
//
//                    socket.shutdownOutput();
//                    System.out.println("Byte sent: " + bitcount);
//
//                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                    ois.skip(Long.MAX_VALUE);
//
//                    String confirmation = (String) ois.readObject();
//                    System.out.println("Server Confirmation " + confirmation);
//                }
//                case 2: {
//                    oos.writeObject("Edit");
//                    oos.flush();
//
//                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                    ois.skip(Long.MAX_VALUE);
//
//                    ArrayList<String> cars = (ArrayList<String>) ois.readObject();
//                    System.out.print("Cars available: ");
//                    System.out.println(cars);
//
//                    String editCar = readFromConsole("Car you want to Edit: ");
//                    String editCar_temp = editCar;
//
//                    BuildAuto auto = new
//                }
//                default:
//
//            }
//
//
//        }catch(IOException e){
//            System.out.println("Error");
//        }catch(MyException e){
//            System.out.println("Error: Invalid name");
//        }catch(ClassNotFoundException e){
//            System.out.println("Error: Could not read object");
//        }
//    }



























//    public CarModelOptionsIO() throws IOException{
//        ois = new ObjectInputStream(sock.getInputStream());
//        oos = new ObjectOutputStream(sock.getOutputStream());
//    }
//
//    public void openConnection({
//
//    })
//
//
//    public Automotive makeAutoFromPropertiesFile(){
//        InputStream input = null;
//        Properties prop = new Properties();
//        Automotive car = null;
//
//        try{
//            input = new FileInputStream("FordFocusZTW.properties");
//            prop.load(input);
//            car = FileIO.readProperty(prop);
//        } catch (IOException e){
//            e.printStackTrace();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return car;
//    }
//
//    public void writeCarToServer() throws IOException{
//        Automotive car = makeAutoFromPropertiesFile();
//        oos.writeObject(car);
//    }
//
//    public boolean checkSuccesfulCartoServer() {
//        boolean isConnected = true;
//        do{
//            try{
//                Object temp = ois.readObject();
//                if (temp.equals(null))
//                    return false;
//                else
//                    return true;
//            }catch(ClassNotFoundException e){
//                isConnected = false;
//                System.out.println("Programming error: "+ e.getMessage());
//            }catch(IOException e){
//                isConnected = false;
//                System.out.println("Server Shutdown");
//                System.exit(0);
//            }
//        }while(isConnected);
//        return false;
//    }

