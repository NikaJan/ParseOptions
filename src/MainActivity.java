import org.apache.commons.cli.*;

import java.io.*;

public class MainActivity {
    public static void main(String[] args) throws Exception{
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(false);
        options.addOption(output);

        Option remove = new Option("rm", "remove", true, "remove file");
        input.setRequired(false);
        options.addOption(remove);

        Option move = new Option("mv", "move", true, "move file");
        input.setRequired(false);
        options.addOption(move);

        Option createFile = new Option("f", "createFile", true, "create file");
        input.setRequired(false);
        options.addOption(createFile);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
            return;
        }
        if(args.length == 0) {
            System.out.println("No options found!");
            return;
        }

        if(args[0].equals("-i")) {
            int num;
            FileInputStream fileIn;
            FileOutputStream fileOut;
            System.out.println("Option is i");
            try {
                try {
                    fileIn = new FileInputStream(args[1]);
                } catch (FileNotFoundException e) {
                    System.out.println("Input File Not Found.");
                    return;
                }
                try {
                    fileOut = new FileOutputStream(args[3]);
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

        if(args[0].equals("-rm")) {
            System.out.println("Option is RM");

            try {
                File file = new File(args[1]);
                file.delete();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException" + e);
                return;
            }
        }

        if(args[0].equals("-mv")) {
            System.out.println("Option is MV");
            int num;
            FileInputStream fileIn;
            FileOutputStream fileOut;

            try {
                try {
                    fileIn = new FileInputStream(args[1]);
                } catch (FileNotFoundException e) {
                    System.out.println("Input File Not Found.");
                    return;
                }
                try {
                    fileOut = new FileOutputStream(args[3]);
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

        try {
            File file = new File(args[1]);
            file.delete();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException" + e);
            return;
        }

        if(args[0].equals("-f")) {
            System.out.println("Option is F");

            try {
                File file = new File(args[1]);
                file.createNewFile();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException" + e);
                return;
            }
        }

        }
    }
