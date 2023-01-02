import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadTextFile {
    
    private static ObjectInputStream input;
    private static ArrayList<Student> list;
    
    public static void openFile(){
        try{
            input = new ObjectInputStream(Files.newInputStream(Paths.get("Students.ser")));
        }
        catch(IOException ioException){
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }

    public static void readRecords(){
        list = new ArrayList<>();
        try{
            while (true){
                    Student record = (Student) input.readObject();
                    list.add(record);
            }
        }catch (EOFException endOfFileException){
            System.out.printf("%nNo more records%n");

        }catch (ClassNotFoundException classNotFoundException){
            System.err.println("Invalid object type. Terminating.");

        }catch (IOException ioException){
            System.err.println("Error reading from file. Terminating.");

        }
    }

    public static void closeFile(){
        try{
            if (input != null)
                input.close();
                
        }catch (IOException ioException){
            System.err.println("Error closing file. Terminating.");
        }
    }

    public static ArrayList<Student> getArrayList(){
        return list;
    }
}