import org.apache.commons.cli.*;

import javax.imageio.IIOException;
import java.io.*;

public class MainActivity {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            optionsNumberErr();
            return;
        }

        String option = args[0];

        switch (option) {
            case "-cp":
                if (args.length < 3) {
                    optionsNumberErr();
                    return;
                }
                File source = new File(args[1]);
                File target = new File(args[2]);
                copy(source, target);
                break;
            case "-rm":
                if (args.length < 2) {
                    optionsNumberErr();
                    return;
                }
                delete(args[1]);
                break;
            case "-mv":
                if (args.length < 3) {
                    optionsNumberErr();
                    return;
                }
                File mSource = new File(args[1]);
                File mTarget = new File(args[2]);
                copy(mSource, mTarget);
                delete(args[1]);
                break;
            case "-touch":
                if (args.length < 2) {
                    optionsNumberErr();
                    return;
                }
                createFile(args[1]);
                break;
            case "-f":
                if (args.length < 2) {
                    optionsNumberErr();
                    return;
                }
                createFile(args[1]);
                break;
            case "-mkdir":
                if (args.length < 2) {
                    optionsNumberErr();
                    return;
                }
                createDir(args[1]);
                break;
            case "-ls":
                if (args.length < 2) {
                    optionsNumberErr();
                    return;
                }
                File mSourceList = new File(args[1]);
                printFileList(mSourceList);
                break;
            default:
                optionsNumberErr();
                break;
        }
    }


    public static void optionsNumberErr() {
        Options options = new Options();
        HelpFormatter formatter = new HelpFormatter();

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

        Option mkdir = new Option("mkdir", "createDir", true, "create directory");
        mkdir.setRequired(false);
        options.addOption(mkdir);

        Option ls = new Option("ls", "printFileList", true, "print file list");
        ls.setRequired(false);
        options.addOption(ls);

        System.out.println("Not enough or wrong options!");
        formatter.printHelp("ParseOptions", options);
    }


    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }


        public static void copyDirectory(File source, File target) throws IOException {
            if (!target.exists()) {
                target.mkdir();
            }

            for (String f : source.list()) {
                copy(new File(source, f), new File(target, f));
            }
        }

        public static void copyFile(File source, File target) throws IOException {
            try {
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target);

                byte[] buf = new byte[1024];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            } catch (IIOException e) {
                System.err.println("IIOException" + e);
                e.printStackTrace();
                return;
            }
        }


    public static void createFile(String s) throws IOException{
        File file = new File(s);

            try {
                file.createNewFile();
            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException" + e);
                return;
            } catch (IOException e) {
                System.err.println("IIOException" + e);
                e.printStackTrace();
                return;
            }
    }

    public static void createDir(String s) {
        try {
            File file = new File(s);
            file.mkdirs();
        } catch (NullPointerException e) {
            System.err.println("NullPointerException" + e);
            return;
        }
    }

    public static void delete(String s) {
        File file = new File(s);
        if (file.exists()) {
            if (file.isFile()) {
                try {
                    file.delete();
                } catch (NullPointerException e) {
                    System.err.println("NullPointerException" + e);
                    return;
                }
            } else if (file.isDirectory()) {
                if (file.exists()) {
                    do {
                        deleteDir(file);
                    } while (file.exists());
                }
            }
        } else{
            System.out.println("File or folder does not exist!");
        }
    }

    public static void deleteDir(File file) {

        if (file.isDirectory()) {
            String fileList[] = file.list();
            if (fileList.length == 0) {
                file.delete();
            } else {
                int size = fileList.length;
                for (int i = 0; i < size; i++) {
                    String fileName = fileList[i];
                    String fullPath = file.getPath() + "/" + fileName;
                    File fileOrFolder = new File(fullPath);
                    deleteDir(fileOrFolder);
                }
            }
        } else {
            file.delete();
        }
    }
    public static void printFileList(File file) throws IOException{
        File[] filesList = file.listFiles();
        for (File f : filesList) {
            if (f.isDirectory())
                System.out.println(f.getName());
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
    }
}