import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;

public class GradebookApp {
    private static boolean saveChanges = false; // Flag to track changes

    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        loadPreviousData(gradeBook); // Load data at the beginning

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Add grade");
            System.out.println("4. Get grade");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Show all students and grades");
            System.out.println("7. Save and Exit");
            System.out.println("8. DO NOT SAVE and Exit");
            System.out.println("9. Clear All Data");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        gradeBook.addStudent(name);
                        saveChanges = true;
                        System.out.println("Student added successfully.");

                        // Ask if the user wants to add a grade
                        System.out.print("Do you want to add a grade for this student? (1 for Yes, 2 for No): ");
                        int addGradeChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (addGradeChoice == 1) {
                            System.out.print("Enter subject: ");
                            String subject = scanner.nextLine();
                            System.out.print("Enter grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine();
                            gradeBook.addGrade(name, subject, grade);
                            saveChanges = true;
                            System.out.println("Grade added successfully.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter student name: ");
                        name = scanner.nextLine();
                        try {
                            gradeBook.removeStudent(name);
                            saveChanges = true;
                            System.out.println("Student removed successfully.");
                        } catch (GradeBook.StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter student name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter subject: ");
                        String subject = scanner.nextLine();
                        System.out.print("Enter grade: ");
                        int grade = scanner.nextInt();
                        scanner.nextLine();
                        gradeBook.addGrade(name, subject, grade);
                        saveChanges = true;
                        System.out.println("Grade added successfully.");
                        break;
                    case 4:
                        System.out.print("Enter student name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter subject: ");
                        subject = scanner.nextLine();
                        System.out.println("Grade: " + gradeBook.getGrade(name, subject));
                        break;
                    case 5:
                        System.out.print("Enter student name: ");
                        name = scanner.nextLine();
                        System.out.println("GPA: " + gradeBook.getGPA(name));
                        break;
                    case 6:
                        gradeBook.showAllStudentsAndGrades();
                        break;
                    case 7:
                        saveData(gradeBook); // Save data before exiting
                        System.out.println("Changes saved successfully. Exiting...");
                        return;
                    case 8:
                        saveChanges = false;
                        System.out.println("Exiting without saving changes.");
                        return;
                    case 9:
                        clearAllData(gradeBook);
                        System.out.println("All data cleared successfully.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadPreviousData(GradeBook gradeBook) {
        String filePath = "src" + File.separator + "data";
        List<Student> students = FileHandler.loadFromFile(filePath);
        if (students != null) {
            for (Student student : students) {
                gradeBook.addStudent(student.name);
                for (Map.Entry<String, ArrayList<Integer>> entry : student.grades.entrySet()) {
                    for (Integer grade : entry.getValue()) {
                        try {
                            gradeBook.addGrade(student.name, entry.getKey(), grade);
                        } catch (GradeBook.StudentNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    private static void saveData(GradeBook gradeBook) {
        if (saveChanges) {
            String filePath = "src" + File.separator + "data";
            try {
                FileHandler.saveToFile(new ArrayList<>(gradeBook.students.values()), filePath);
                System.out.println("Changes saved successfully.");
            } catch (Exception e) {
                System.out.println("Error saving changes: " + e.getMessage());
            }
            saveChanges = false;
        }
    }

    private static void clearAllData(GradeBook gradeBook) {
        gradeBook.students.clear();
        String filePath = "src" + File.separator + "data";
        try {
            FileHandler.saveToFile(new ArrayList<>(), filePath);
            System.out.println("All data cleared successfully.");
        } catch (Exception e) {
            System.out.println("Error clearing data: " + e.getMessage());
        }
    }
}
