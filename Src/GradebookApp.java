import java.util.Scanner;

public class GradebookApp {
    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Add grade");
            System.out.println("4. Get grade");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
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
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
