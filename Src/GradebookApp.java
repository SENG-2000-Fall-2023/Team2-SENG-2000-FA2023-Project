import java.util.*;

public class GradebookApp {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Menu menu = new Menu();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                menu.display();
                int choice = scanner.nextInt();
                scanner.nextLine();  

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter student name: ");
                            String name = scanner.nextLine();
                            gradeBook.addStudent(name);
                            break;
                        case 2:
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            gradeBook.removeStudent(name);
                            break;
                        case 3:
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter subject: ");
                            String subject = scanner.nextLine();
                            System.out.print("Enter grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine();  // consume newline
                            gradeBook.addGrade(name, subject, grade);
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
                            System.out.print("Enter student name: ");
                            name = scanner.nextLine();
                            System.out.println("Letter Grade: " + gradeBook.getLetterGrade(name));
                            break;
                        case 7:
                            FileHandler.saveToFile(gradeBook.students, "students.ser");
                            break;
                        case 8:
                            HashMap<String, Student> loadedStudents = FileHandler.loadFromFile("students.ser");
                            if (loadedStudents != null) {
                                gradeBook.students = loadedStudents;
                            }
                            break;
                        case 9:
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
                            return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
