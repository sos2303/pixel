import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", grade=" + grade + '}';
    }
}

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Добавляем тестовых студентов
        students.add(new Student("Ivan", 20, 4.0));
        students.add(new Student("Maria", 19, 5.0));
        students.add(new Student("Petr", 21, 3.0));

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudentInteractive();
                    break;
                case 2:
                    removeStudentInteractive();
                    break;
                case 3:
                    findStudentInteractive();
                    break;
                case 4:
                    printAllStudents();
                    break;
                case 5:
                    System.out.println("Average grade: " + calculateAverageGrade());
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. Remove student");
        System.out.println("3. Find student");
        System.out.println("4. Show all students");
        System.out.println("5. Calculate average grade");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudentInteractive() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();

        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();

        students.add(new Student(name, age, grade));
        System.out.println("Student added successfully!");
    }

    private static void removeStudentInteractive() {
        System.out.print("Enter student name to remove: ");
        String name = scanner.nextLine();
        removeStudentByName(name);
    }

    private static void findStudentInteractive() {
        System.out.print("Enter student name to find: ");
        String name = scanner.nextLine();
        findStudentByName(name);
    }

    private static void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        System.out.println("\nList of all students:");
        students.forEach(System.out::println);
    }

    public static void removeStudentByName(String name) {
        if (students.removeIf(student -> student.getName().equalsIgnoreCase(name))) {
            System.out.println("Student '" + name + "' removed successfully.");
        } else {
            System.out.println("Student with name '" + name + "' not found.");
        }
    }

    public static void findStudentByName(String name) {
        boolean found = false;
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student found: " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student with name '" + name + "' not found.");
        }
    }

    public static double calculateAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0.0);
    }
}