import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
    String name;
    HashMap<String, Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int grade) {
        grades.put(subject, grade);
    }

    public int getGrade(String subject) throws Exception {
        if (!grades.containsKey(subject)) {
            throw new Exception("Subject not found");
        }
        return grades.get(subject);
    }

    public float calculateGPA() {
        int total = 0;
        for (int grade : grades.values()) {
            total += grade;
        }
        return grades.size() > 0 ? (float) total / grades.size() : 0;
    }
}
