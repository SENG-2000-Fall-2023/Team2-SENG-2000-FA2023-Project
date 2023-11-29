import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Student implements Serializable {
    String name;
    HashMap<String, ArrayList<Integer>> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, Integer grade) {
        // check if subject is not already in gradebook
        if (!grades.containsKey(subject)) {
            grades.put(subject, new ArrayList<Integer>());
        }
        // subject exists, add grade to list
        grades.get(subject).add(grade);
    }

    public ArrayList<Integer> getGrade(String subject) {
        return grades.getOrDefault(subject, new ArrayList<>());
    }

    public float calculateGPA() {
        double totalGPA = 0;
        int subjectCount = 0;

        for (ArrayList<Integer> gradeList : grades.values()) {
            if (!gradeList.isEmpty()) {
                int total = 0;
                for (Integer i : gradeList) {
                    total += i;
                }
                float subjectAvg = (float) total / gradeList.size();
                double subjectGPA = gradepoint(Math.round(subjectAvg));
                totalGPA += subjectGPA;
                subjectCount++;
            }
        }

        return subjectCount > 0 ? (float) totalGPA / subjectCount : 0;
    }

    private double gradepoint(int subjectGrade) {
        if (subjectGrade >= 93 && subjectGrade <= 100) {
            return 4.0;
        } else if (subjectGrade >= 90 && subjectGrade <= 92) {
            return 3.7;
        } else if (subjectGrade >= 87 && subjectGrade <= 89) {
            return 3.3;
        } else if (subjectGrade >= 83 && subjectGrade <= 86) {
            return 3.0;
        } else if (subjectGrade >= 80 && subjectGrade <= 82) {
            return 2.7;
        } else if (subjectGrade >= 77 && subjectGrade <= 79) {
            return 2.3;
        } else if (subjectGrade >= 73 && subjectGrade <= 76) {
            return 2.0;
        } else if (subjectGrade >= 70 && subjectGrade <= 72) {
            return 1.7;
        } else if (subjectGrade >= 67 && subjectGrade <= 69) {
            return 1.3;
        } else if (subjectGrade >= 65 && subjectGrade <= 66) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
