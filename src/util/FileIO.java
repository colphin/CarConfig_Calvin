package util;

import java.io.*;
import java.util.*;
import model.*;
import throwable.*;


/**
 * Created by Calvin on 1/25/15.
 */
public class FileIO {

    public static Automotive readFile (String fileName) throws BlankFileException, FileFormatException, FileNameException {
        Automotive a1 = new Automotive();
        ArrayList<OptionSet> optSetList = new ArrayList<OptionSet>();
        Scanner scan = null;
        boolean isBlank = true;

        try{
            FileReader file = new FileReader(fileName);

            checkFileType(fileName);

            BufferedReader read = new BufferedReader(file);
            scan = new Scanner(read);
            ArrayList<String> lines = new ArrayList<String>();

            while(scan.hasNextLine()){
                isBlank = false;
                String line = scan.nextLine();

                if(line.contains(":"))
                    lines.add(line.trim());

            }

            if (isBlank == true){
                throw new BlankFileException("Blank File");
            }else if(lines.size() == 0){
                throw new FileFormatException("Invalid File Format");
            }

            //Getting the Basic Information.
            a1.setMake(lines.get(0).split(":")[1].trim());
            a1.setModel(lines.get(1).split(":")[1].trim());
            a1.setBasePrice(Integer.parseInt(lines.get(2).split(":")[1].trim()));

            ListIterator<String> line = lines.listIterator(3);


            while(line.hasNext()){
                //OptionSet optset = new OptionSet(line.next().split(":")[0].trim());
                String tempOptSetName = line.next().split(":")[0].trim();
                a1.addOptionSet(tempOptSetName);
                while(line.hasNext()){
                    String nextLine = line.next();
                    if(!nextLine.endsWith(":") || nextLine == null){
                        a1.addOption(tempOptSetName, nextLine.split(":")[0].trim(), Integer.parseInt(nextLine.split(":")[1].trim()));
                    }else{
                        line.previous();
                        break;
                    }
                }
//                System.out.println("TEST: " + a1.getOptionSet(tempOptSetName).toString());

            }


            /*
            while(line.hasNext()){
                OptionSet optset = new OptionSet(line.next().split(":")[0].trim());
                ArrayList<OptionSet.Option> optList = new ArrayList<OptionSet.Option>();
                while(line.hasNext()){
                    String nextLine = line.next();
                    if(!nextLine.endsWith(":")){
                        OptionSet.Option o = optset.new Option( nextLine.split(":")[0].trim(), Integer.parseInt(nextLine.split(":")[1].trim()));
                        optList.add(o);
                    }else{
                        line.previous();
                        break;
                    }
                }
                optset.setOptions(optList);
                optSetList.add(optset);
            }
            */

        }catch(FileNotFoundException e){
            throw new FileNameException(e.getMessage());
        }catch(FileFormatException e){
            throw new FileFormatException(e.getMessage());
        }catch(BlankFileException e){
            throw new BlankFileException(e.getMessage());
        } catch (FileTypeExceptions e) {
            e.printStackTrace();
        } catch(OptionException e){
            e.getExceptionMessage();
        }


        scan.close();
        //a1.setConfigOpt(optSetList);
        return a1;
    }

    public static void checkFileType(String fileName) throws FileTypeExceptions {
        String extn = fileName.substring(fileName.indexOf(".")+1);
        if (!extn.equalsIgnoreCase("txt")) {
            throw new FileTypeExceptions("Invalid File Type");
        }
    }

    public static void seralize(Object obj, String destination){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destination));
            out.writeObject(obj);
            out.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static Object deseralize(String source) throws FileTypeExceptions{
        Object obj = null;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(source));
            obj = in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    return obj;
    }
}
