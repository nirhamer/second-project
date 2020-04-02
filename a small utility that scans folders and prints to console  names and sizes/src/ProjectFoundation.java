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
                if (file.isDirectory()) {
                    displayDirectoryContents(file);
                }
                else

                if ( file.getName().toLowerCase().contains("as") || file.length() > 100) System.out.println(file.getCanonicalPath());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
