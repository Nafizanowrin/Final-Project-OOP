import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

public class CreateTextFile {
    private static ObjectOutputStream studentData;
    
    public static void openFile(){
        try{
            studentData = new ObjectOutputStream(Files.newOutputStream(Paths.get("Students.ser")));
            
        }catch(IOException ioException){
            JOptionPane.showMessageDialog(null, "Error Opening file!!");
            System.exit(1);
        }
    }

    public static void addRecords(String name, String id, String department, String batch, String section){
        try{
            Student student = new Student(name, id, department, batch, section);

            studentData.writeObject(student);
                    
        }catch(NoSuchElementException elementException){
            System.err.println("Invalid input. Please try again.");
        }catch(IOException ioException){
            System.err.println("Error writing to file. Terminating.");
        }
    }

    public static void closeFile(){
        try{
            if (studentData != null)
                studentData.close();
        }catch (IOException ioException){
            System.err.println("Error closing file. Terminating.");
        }
    }
}