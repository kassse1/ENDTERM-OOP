package University;

import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookApplication {
    private ArrayList<Student> students;
    private int nextId;
    private Scanner scanner;

    public PhonebookApplication() {
        students = new ArrayList<>();
        nextId = 0;
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;

        do {
            System.out.println("====================");
            System.out.println("Hello! This is phonebook!");
            System.out.println("1. To print all");
            System.out.println("2. To add");
            System.out.println("3. To search");
            System.out.println("4. To delete");
            System.out.println("5. To quit");
            System.out.print("[in]");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    printAllEntries();
                    break;
                case 2:
                    addNewEntry();
                    break;
                case 3:
                    searchEntry();
                    break;
                case 4:
                    deleteEntry();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void printAllEntries() {
        System.out.println("====================");
        for (Student student : students) {
            System.out.println("(id " + student.getId() + ") " +
                    student.getName() + "; " +
                    student.getAge() + " years old; " +
                    student.getPhone());
        }
        System.out.println("====================");
    }

    private void addNewEntry() {
        System.out.println("adding new entry");
        System.out.print("input student's name\n[in]");
        String name = scanner.nextLine();

        System.out.print("input student's age (0 ... 150)\n[in]");
        int age = scanner.nextInt();

        // Check age to be in a reasonable range
        if (age < 0 || age > 150) {
            System.out.println("Invalid age. Please enter a valid age.");
            return;
        }

        System.out.print("input student's phone (less than 13 characters)\n[in]");
        String phone = scanner.next();

        if (phone.length() > 13) {
            System.out.println("Invalid phone number. Please enter a valid phone number.");
            return;
        }

        Student newStudent = new Student(nextId++, name, age, phone);
        students.add(newStudent);
    }

    private void searchEntry() {
        System.out.print("input student's name to search\n[in]");
        String searchName = scanner.nextLine();

        boolean found = false;

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Student found:");
                System.out.println("(id " + student.getId() + ") " +
                        student.getName() + "; " +
                        student.getAge() + " years old; " +
                        student.getPhone());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private void deleteEntry() {
        System.out.print("input student's id to delete\n[in]");
        int deleteId = scanner.nextInt();

        boolean found = false;

        for (Student student : students) {
            if (student.getId() == deleteId) {
                students.remove(student);
                System.out.println("Student deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}
