import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private String id;
    private String name;
    private String designation;
    private double salary;

    public Employee(String id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        try {
            double sal = Double.parseDouble(parts[3]);
            return new Employee(parts[0], parts[1], parts[2], sal);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

public class EmployeeManagement {
    private static final String FILE = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Management Menu");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addEmployee(sc);
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    System.out.println("Exiting application.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter employee ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter employee name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter designation: ");
        String des = sc.nextLine().trim();
        System.out.print("Enter salary: ");
        double sal = 0.0;
        try {
            sal = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary input, setting to 0.0");
        }

        Employee emp = new Employee(id, name, des, sal);

        try (FileWriter fw = new FileWriter(FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(emp.toString());
            bw.newLine();
            System.out.println("Employee added.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void displayEmployees() {
        File f = new File(FILE);
        if (!f.exists()) {
            System.out.println("No employee records found.");
            return;
        }

        List<Employee> list = new ArrayList<>();
        try (FileReader fr = new FileReader(FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                Employee e = Employee.fromString(line);
                if (e != null) list.add(e);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        if (list.isEmpty()) {
            System.out.println("No employee records to display.");
            return;
        }

        System.out.println("\nEmployees:");
        for (Employee e : list) {
            String[] fields = e.toString().split(",");
            System.out.println("ID: " + fields[0] + ", Name: " + fields[1] + ", Designation: " + fields[2] + ", Salary: " + fields[3]);
        }
    }
}
