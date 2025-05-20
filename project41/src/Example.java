import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class Example {

    public static void main(String[] args) {

        try {
            ZipFile zipFile = new ZipFile("example.zip");
            zipFile.setPassword("good");
            zipFile.extractAll("contents");
            System.out.println("Successfully cracked!");
        } catch (ZipException ze) {
            System.out.println("Incorrect password :(");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
