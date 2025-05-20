import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class Example {

    public static void main(String[] args) {

        try {
            ZipFile zipFile = new ZipFile("protected3.zip");
            zipFile.setPassword("may");
            zipFile.extractAll("contents");
            System.out.println("Successfully cracked!");
        } catch (ZipException ze) {
            System.out.println("Incorrect password :(");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
