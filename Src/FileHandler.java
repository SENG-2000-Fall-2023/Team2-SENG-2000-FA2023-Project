import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void saveToFile(List<Student> students, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Student student : students) {
                out.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Student student = (Student) in.readObject();
                    students.add(student);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static String studentToString(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.name).append(":");
        for (String subject : student.grades.keySet()) {
            sb.append(" ").append(subject).append(" ");
            for (Integer grade : student.grades.get(subject)) {
                sb.append(grade).append(",");
            }
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static Student stringToStudent(String line) {
        String[] parts = line.split(":");
        String name = parts[0].trim();
        Student student = new Student(name);
        if (parts.length > 1) {
            String[] subjects = parts[1].split("\\s+");
            for (int i = 0; i < subjects.length; i += 2) {
                if (i + 1 < subjects.length) {
                    String subject = subjects[i];
                    String[] grades = subjects[i + 1].split(",");
                    for (String grade : grades) {
                        try {
                            int gradeValue = Integer.parseInt(grade);
                            student.addGrade(subject, gradeValue);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid grade format: " + grade);
                        }
                    }
                } else {
                    System.out.println("Missing grades for subject: " + subjects[i]);
                }
            }
        }
        return student;
    }



    public static void appendToFile(List<Student> students, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (Student student : students) {
                writer.println(studentToString(student));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
