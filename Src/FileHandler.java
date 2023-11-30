import java.io.*;
import java.util.HashMap;

public class FileHandler {
    public static void saveToFile(HashMap<String, Student> students, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(students);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static HashMap<String, Student> loadFromFile(String fileName) {
        HashMap<String, Student> students = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            students = (HashMap<String, Student>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Student class not found");
            c.printStackTrace();
        }
        return students;
    }
}