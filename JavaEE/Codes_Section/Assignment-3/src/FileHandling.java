import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
public class FileHandling {
    public static void main(String[] args) throws IOException {
        String directoryPath = "D:\\Assignment-3";
        String fileName = "Shadril_Hassan_Shifat_00-30135.txt";

        FileHandling.createDirectory(directoryPath);
        FileHandling.createFile(fileName, directoryPath);
        FileHandling.getFilePath(fileName, directoryPath);
        FileHandling.writeToFile(fileName, directoryPath);

        String readFile = directoryPath + "\\" + fileName;
        String writeFile = directoryPath + "\\writeFile.txt";
        FileHandling.readWriteToFile(readFile, writeFile);
    }

    // Create a new directory at your laptop/PC through JAVA code.
    private static void createDirectory(String directoryPath){
        try{
            File directory = new File(directoryPath);
            if(!directory.exists()){
                if (directory.mkdirs()){
                    System.out.println("Directory created successfully...");
                }
                else{
                    System.out.println("Directory can't be created...");
                }
            }
            else{
                System.out.println("Directory already exist...");
            }
        } catch (Exception e){
            System.out.println("An error occurred while handling files." + e.getMessage());
            e.printStackTrace();
        }
    }
    // Create a new file with your name and ID.
    private static void createFile(String fileName, String fileDirectory){
        try{
            String newFile = fileDirectory + "\\" + fileName;
            File file = new File(newFile);
            if(!file.exists()){
                if (file.createNewFile()){
                    System.out.println("File created successfully...");
                }
                else {
                    System.out.println("File can't be created...");
                }
            }
            else {
                System.out.println("File already exist...");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while handling files.");
            e.printStackTrace();
        }
    }
    // Get file path through Java coding and print at console.
    private static void getFilePath(String fileName, String fileDirectory){
        String filePath = fileDirectory + "\\" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File path: " + file.getAbsolutePath());
        } else {
            System.out.println("File does not exist.");
        }
    }
    // Write something on that newly created file
    private static void writeToFile(String fileName, String fileDirectory) throws IOException{
        String filePath = fileDirectory + "\\" + fileName;
        String content = "Hello, I am Shadril Hassan Shifat.";
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(filePath);
            fileWriter.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred while handling files.");
            e.printStackTrace();
        }
        finally {
            if(fileWriter != null)
                fileWriter.close();
        }
    }
    // Read from the first file then write to a new Output file.
    private static void readWriteToFile(String readFile, String writeFile) throws FileNotFoundException, IOException {
        FileInputStream sourceStream = null;
        FileOutputStream targetStream = null;
        try {
            sourceStream = new FileInputStream(readFile);
            targetStream = new FileOutputStream(writeFile);

            int temp;
            while ((temp = sourceStream.read())!= -1) {
                targetStream.write((byte) temp);
            }
        }
        finally {
            if (sourceStream != null)
                sourceStream.close();
            if (targetStream != null)
                targetStream.close();
        }
    }
}
