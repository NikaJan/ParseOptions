import org.apache.commons.cli.*;

import java.io.*;

public class MainActivity {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        Option copy = new Option("cp", "input", true, "copy file");
        copy.setRequired(false);
        options.addOption(copy);

        Option remove = new Option("rm", "remove", true, "remove file");
        remove.setRequired(false);
        options.addOption(remove);

        Option move = new Option("mv", "move", true, "move file");
        move.setRequired(false);
        options.addOption(move);

        Option create = new Option("f", "createFile", true, "create file");
        create.setRequired(false);
        options.addOption(create);

        if (args.length == 0) {
            System.out.println("No options found!");
            return;
        }

        String option = args[0];

        switch (option){
            case "-cp":
                copyFile(args[1], args[2]);
                break;
            case "-rm":
                deleteFile(args[1]);
                break;
            case "-mv":
                copyFile(args[1], args[2]);
                deleteFile(args[1]);
                break;
            case "-f":
                createFile(args[1]);
                break;
        }
    }

    public static void copyFile (String s, String s2) throws IOException {
        int num;
        FileInputStream fileIn;
        FileOutputStream fileOut;

        try {
            try {
                fileIn = new FileInputStream(s);
            } catch (FileNotFoundException e) {
                System.out.println("Input File Not Found.");
                return;
            }
            try {
                fileOut = new FileOutputStream(s2);
            } catch (FileNotFoundException e) {
                System.out.println("Error Opening Output File.");
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect argument use:java CopyFile Source Destination");
            return;
        }
        try {
            do {
                num = fileIn.read();
                if (num != -1) {
                    fileOut.write(num);
                }
            }
            while (num != -1);
        } catch (IOException e) {
            System.out.println("File Error: Could not copy file.");
        }

        fileIn.close();
        fileOut.close();
    }


    public static void createFile (String s){
        try {
            File file = new File(s);
            file.createNewFile();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException" + e);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteFile (String s){
        try {
            File file = new File(s);
            file.delete();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException" + e);
            return;
        }
    }


    }
