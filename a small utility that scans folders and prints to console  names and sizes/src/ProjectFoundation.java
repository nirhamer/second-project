import java.io.File;
import java.io.IOException;


public class ProjectFoundation {

    public static void main(String[] args) {
        File currentDir = new File("."); // current directory
        displayDirectoryContents(currentDir);
    }

    public static void displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                String string = "as";
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    displayDirectoryContents(file);
                } else if (file.length()>100) {
                    System.out.println("directory:" + file.getCanonicalPath());
                }
                else if (string.contains("as")) {
                    System.out.println("directory:" + file.getCanonicalPath());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
