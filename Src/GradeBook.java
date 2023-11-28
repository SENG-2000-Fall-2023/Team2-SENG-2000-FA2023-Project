import java.util.HashMap;
import java.util.LinkedList;

public class GradeBook {
    HashMap<String, Student> students;
    LinkedList<String> studentOrder;

    public GradeBook() {
        students = new HashMap<>();
        studentOrder = new LinkedList<>();
    }

    public void addStudent(String name) {
        students.put(name, new Student(name));
        studentOrder.add(name);
    }

    public void removeStudent(String name) throws Exception {
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        students.remove(name);
        studentOrder.remove(name);
    }

    public void addGrade(String name, String subject, int grade) throws Exception {
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        students.get(name).addGrade(subject, grade);
    }

    public int getGrade(String name, String subject) throws Exception {
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).getGrade(subject);
    }

    public float getGPA(String name) throws Exception {
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).calculateGPA();
    }
}
