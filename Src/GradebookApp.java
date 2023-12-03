import java.io.*;
import java.util.*;


public class GradebookApp {
    private static final String DATA_FILE = "Src/data";

    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Menu menu = new Menu();

        // Load data from file at the beginning
        loadFromFile(gradeBook, DATA_FILE);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                menu.display();
                int choice = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choice) {
                        case 1:
                            // Add Student
                            System.out.print("Enter student name: ");
                            String name = scanner.nextLine();
                            gradeBook.addStudent(name);

                            // Prompt to enter a grade for the student
                            System.out.print("Do you want to enter a grade for this student? (Yes = 1. No = 2): ");
                            String enterGradeChoice = scanner.nextLine().toLowerCase();
                            if (enterGradeChoice.equals("1")) {
                                System.out.print("Enter subject: ");
                                String subject = scanner.nextLine();
                                System.out.print("Enter grade: ");
                                int grade = scanner.nextInt();
                                scanner.nextLine();
                                gradeBook.addGrade(name, subject, grade);
                                System.out.print("Press 8 to save for changes to take effect!!!");
                            }
                            break;

                        case 2:
                            // Remove Student
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            gradeBook.removeStudent(name);
                            break;

                        case 3:
                            // Edit Student Information
                            System.out.print("Enter the student name: ");
                            String editName = scanner.nextLine();

                            if (!gradeBook.students.containsKey(editName)) {
                                System.out.println("Student not found.");
                                break;
                            }

                            // Display student's subjects and grades
                            Student editStudent = gradeBook.students.get(editName);
                            System.out.println("Student: " + editName);
                            System.out.println("Subjects and Grades:");
                            for (Map.Entry<String, Integer> entry : editStudent.grades.entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }

                            // Ask what to edit
                            System.out.print("Enter the subject you want to edit (or 'exit' to cancel): ");
                            String editSubject = scanner.nextLine();

                            if (!editSubject.equalsIgnoreCase("exit")) {
                                System.out.print("Enter the new grade for " + editSubject + ": ");
                                int newGrade = scanner.nextInt();
                                scanner.nextLine();  // consume newline

                                try {
                                    gradeBook.editGrade(editName, editSubject, newGrade);
                                    System.out.println("Grade updated successfully.");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;


                        case 4:
                            // Add Grade
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter subject: ");
                            String subject = scanner.nextLine();
                            System.out.print("Enter grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine();  // consume newline
                            gradeBook.addGrade(name, subject, grade);
                            break;

                        case 5:
                            // Get Numerical Grade
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter subject: ");
                            subject = scanner.nextLine();
                            System.out.println("Grade: " + gradeBook.getGrade(name, subject));
                            break;

                        case 6:
                            // Calculate GPA
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.println("GPA: " + gradeBook.getGPA(name));
                            break;

                        case 7:
                            // Get Letter Grade
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.println("Letter Grade: " + gradeBook.getLetterGrade(name));
                            break;

                        case 8:
                            // Save to File
                            saveToFile(gradeBook.students, DATA_FILE);
                            System.out.println("Data saved successfully.");
                            break;

                        case 9:
                            // Show All Student Data
                            gradeBook.students = FileHandler.loadFromFile(DATA_FILE);

                            // Display student information
                            for (String student : gradeBook.studentOrder) {
                                Student currentStudent = gradeBook.students.get(student);
                                System.out.println("Name: " + currentStudent.name);
                                System.out.println("Grades: " + currentStudent.grades);
                                System.out.println("GPA: " + currentStudent.calculateGPA());
                                System.out.println("Letter Grade: " + currentStudent.calculateLetterGrade());
                                System.out.println("------------------------");
                            }
                            break;

                        case 10:
                            // Exit
                            saveToFile(gradeBook.students, DATA_FILE);
                            return;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void saveToFile(HashMap<String, Student> students, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void loadFromFile(GradeBook gradeBook, String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            HashMap<String, Student> loadedStudents = (HashMap<String, Student>) in.readObject();
            if (loadedStudents != null) {
                gradeBook.students = loadedStudents;
                gradeBook.studentOrder = new LinkedList<>(gradeBook.students.keySet());
                Collections.sort(gradeBook.studentOrder);
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exception, for file not existing
        }
    }
}
