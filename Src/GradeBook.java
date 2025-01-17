import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class GradeBook {
    HashMap<String, Student> students;
    LinkedList<String> studentOrder;

    public void addNoteToStudent(String name, String note) throws Exception {
        name = capitalizeFirstLetter(name);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        students.get(name).addNote(note);
    }

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
        if (word == null || word.isEmpty()) {
            return word;
        }
        word = word.toLowerCase();
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
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
        students.get(name).GPA = students.get(name).calculateGPA();
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
        return students.get(name).GPA;
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
        students.get(name).GPA = students.get(name).calculateGPA();
    }

    public String getLetterGrade(String name) throws Exception {
        name = capitalizeFirstLetter(name);
        if (!students.containsKey(name)) {
            throw new Exception("Student not found");
        }
        return students.get(name).calculateLetterGrade();
    }

    public void sortByGPA() {
        int i = 0;
        int j = 0;
        //To sort descending order, the largest value must be found unlike traditional selection sort
        int indexLargest = 0;

        List<Student> sortedStudents = new ArrayList<>();

        //Add only values from Students to List
        for (Map.Entry<String, Student> entry : students.entrySet()){
            sortedStudents.add(entry.getValue());
        } 

        for (i = 0; i < sortedStudents.size() - 1; ++i) {

            indexLargest = i;
            for (j = i + 1; j < sortedStudents.size(); ++j) {
                if(sortedStudents.get(j).GPA > sortedStudents.get(indexLargest).GPA) {
                    indexLargest = j;
                }
            }

            Collections.swap(sortedStudents, i, indexLargest);

        }

        studentOrder.clear();
        for (Student studentName: sortedStudents){
            studentOrder.add(studentName.name);
        }
    }
}
