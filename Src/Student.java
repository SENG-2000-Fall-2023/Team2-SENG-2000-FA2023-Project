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
        float total = 0;
        for (int grade : grades.values()) {
            total += convertNumericalGradeToPoints(grade);
        }
        return grades.size() > 0 ? total / grades.size() : 0;
    }

    public String calculateLetterGrade() {
        float gpa = calculateGPA();
        if (gpa >= 4.00) {
            return "A";
        } else if (gpa >= 3.67) {
            return "A-";
        } else if (gpa >= 3.33) {
            return "B+";
        } else if (gpa >= 3.00) {
            return "B";
        } else if (gpa >= 2.67) {
            return "B-";
        } else if (gpa >= 2.33) {
            return "C+";
        } else if (gpa >= 2.00) {
            return "C";
        } else if (gpa >= 1.33) {
            return "D+";
        } else if (gpa >= 1.00) {
            return "D";
        } else {
            return "F";
        }
    }

    private float convertNumericalGradeToPoints(int grade) {
        if (grade >= 93) {
            return 4.00f;
        } else if (grade >= 90) {
            return 3.67f;
        } else if (grade >= 87) {
            return 3.33f;
        } else if (grade >= 83) {
            return 3.00f;
        } else if (grade >= 80) {
            return 2.67f;
        } else if (grade >= 77) {
            return 2.33f;
        } else if (grade >= 73) {
            return 2.00f;
        } else if (grade >= 70) {
            return 1.67f;
        } else if (grade >= 67) {
            return 1.33f;
        } else if (grade >= 63) {
            return 1.00f;
        } else if (grade >= 60) {
            return 0.67f;
        } else {
            return 0.00f;
        }
    }
}