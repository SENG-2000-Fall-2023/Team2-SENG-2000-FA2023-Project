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
        //check if subject is not already in gradebook
        if(!grades.containsKey(subject)){
        grades.put(subject, new ArrayList<Integer>());
        grades.get(subject).add(grade);
    }
        //subject exists, add grade to list
        else{
           grades.get(subject).add(grade); 
        }
    }

    public ArrayList<Integer> getGrade(String subject) throws Exception {
        if (!grades.containsKey(subject)) {
            throw new Exception("Subject not found");
        }
        return grades.get(subject);
    }

    public float calculateGPA() {
        
        double totalgpa = 0;

        for (ArrayList<Integer> gradelist : grades.values()) {
            int total = 0;
            float subjectavg = 0;
            for (Integer i : gradelist) {
                total+= i;
            }
            subjectavg = total/gradelist.size();
            double subjectgpa = gradepoint(Math.round(subjectavg));
            totalgpa += subjectgpa;            
        }

        return grades.size() > 0 ? (float) totalgpa / grades.size() : 0;
    }

    public double gradepoint(int subjectgrade){
        
        if (subjectgrade >= 93 && subjectgrade <= 100){
            return 4.0;
        }

        else if (subjectgrade >= 90 && subjectgrade <= 92){
            return 3.7;
        }
        
        else if (subjectgrade >= 87 && subjectgrade <= 89){
            return 3.3;
        }
        
        else if (subjectgrade >= 83 && subjectgrade <= 86){
            return 3.0;
        }
        
        else if (subjectgrade >= 80 && subjectgrade <= 82){
            return 2.7;
        }
        
        else if (subjectgrade >= 77 && subjectgrade <= 79){
            return 2.3;
        }
        
        else if (subjectgrade >= 73 && subjectgrade <= 76){
            return 2.0;
        }
        
        else if (subjectgrade >= 70 && subjectgrade <= 72){
            return 1.7;
        }
        
        else if (subjectgrade >= 67 && subjectgrade <= 69){
            return 1.3;
        }
        
        else if (subjectgrade >= 65 && subjectgrade <= 66){
            return 1.0;
        }
        
        else {
            return 0.0;
        }
        
    }

}
