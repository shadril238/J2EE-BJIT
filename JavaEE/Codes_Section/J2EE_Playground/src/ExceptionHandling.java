import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExceptionHandling {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream sourceStream = null;
        FileOutputStream targetStream = null;

        try {
            sourceStream = new FileInputStream("file.txt");
            targetStream = new FileOutputStream("file2.txt");

            int temp;
            while((temp = sourceStream.read()) != -1){
                targetStream.write((byte)temp);
            }
        }finally {
            if(sourceStream != null){
                sourceStream.close();
            }
            if(targetStream != null){
                targetStream.close();
            }
        }
    }
}
