import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void removeStudent(String name) throws StudentNotFoundException {
        if (!students.containsKey(name)) {
            throw new StudentNotFoundException("Student not found");
        }
        students.remove(name);
        studentOrder.remove(name);
    }

    public void addGrade(String name, String subject, int grade) throws StudentNotFoundException {
        if (!students.containsKey(name)) {
            throw new StudentNotFoundException("Student not found");
        }
        students.get(name).addGrade(subject, grade);
    }

    public ArrayList<Integer> getGrade(String name, String subject) throws StudentNotFoundException {
        if (!students.containsKey(name)) {
            throw new StudentNotFoundException("Student not found");
        }
        return students.get(name).getGrade(subject);
    }

    public float getGPA(String name) throws StudentNotFoundException {
        if (!students.containsKey(name)) {
            throw new StudentNotFoundException("Student not found");
        }
        return students.get(name).calculateGPA();
    }

    public void showAllStudentsAndGrades() {
        sortStudents();
        for (String name : studentOrder) {
            System.out.println(name + ":");
            Student student = students.get(name);
            for (String subject : student.grades.keySet()) {
                System.out.println("  " + subject + " " + student.getGrade(subject));
            }
            System.out.println();
        }
    }

    public void sortStudents () {

        Collections.sort(studentOrder, new Comparator<String>() {
            //Sorts lowercased names before Uppercased ones
            @Override
            public int compare(String o1, String o2) {
                return Collator.getInstance().compare(o1, o2);
            }
        });

    }

    public class StudentNotFoundException extends Exception {
        public StudentNotFoundException(String message) {
            super(message);
        }
    }
}
