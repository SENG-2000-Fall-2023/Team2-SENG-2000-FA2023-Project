import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class GradeBook {
    HashMap<String, Student> students;
    LinkedList<String> studentOrder;

    public GradeBook() {
        students = new HashMap<>();
        studentOrder = new LinkedList<>();
    }

    public void editStudent(String oldName, String newName) throws Exception {
        oldName = capitalizeFirstLetter(oldName);
        newName = capitalizeFirstLetter(newName);

        if (!students.containsKey(oldName)) {
            throw new Exception("Student not found");
        }

        if (students.containsKey(newName)) {
            throw new Exception("Another student with the new name already exists");
        }

        Student student = students.remove(oldName);
        student.name = newName;
        students.put(newName, student);

        // Update the studentOrder list
        studentOrder.remove(oldName);
        studentOrder.add(newName);
        Collections.sort(studentOrder);
    }

    public void addStudent(String name) throws Exception {
        name = capitalizeFirstLetter(name);
        if (students.containsKey(name)){
            throw new Exception("Student already exists in gradebook");
        }
        students.put(name, new Student(name));
        studentOrder.add(name);
        Collections.sort(studentOrder);
    }

    public void removeStudent(String name) throws Exception {
        name = capitalizeFirstLetter(name);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        students.remove(name);
        studentOrder.remove(name);
    }

    public String capitalizeFirstLetter(String word) {
        word = word.toLowerCase();
        word = word.substring(0,1).toUpperCase() + word.substring(1);
        return word;
    }

    public void addGrade(String name, String subject, int grade) throws Exception {
        name = capitalizeFirstLetter(name);
        subject = capitalizeFirstLetter(subject);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }

        if (grade < 0 || grade > 100) {
            throw new Exception("Invalid grade entered");
        }

        students.get(name).addGrade(subject, grade);
    }

    public int getGrade(String name, String subject) throws Exception {
        name = capitalizeFirstLetter(name);
        subject = capitalizeFirstLetter(subject);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).getGrade(subject);
    }

    public float getGPA(String name) throws Exception {
        name = capitalizeFirstLetter(name);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).calculateGPA();
    }

    public void editGrade(String name, String subject, int newGrade) throws Exception {
        name = capitalizeFirstLetter(name);
        subject = capitalizeFirstLetter(subject);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }

        if (!students.get(name).grades.containsKey(subject)) {
            throw new Exception("Subject not found");
        }

        if (newGrade < 0 || newGrade > 100) {
            throw new Exception("Invalid grade entered");
        }

        students.get(name).grades.put(subject, newGrade);
    }

    public String getLetterGrade(String name) throws Exception {
        name = capitalizeFirstLetter(name);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).calculateLetterGrade();
    }
}
