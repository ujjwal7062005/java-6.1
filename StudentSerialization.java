import java.io.*;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentID;
    private String name;
    private double grade;

    public Student(String studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{ID='" + studentID + "', name='" + name + "', grade=" + grade + "}";
    }
}

public class StudentSerialization {
    private static final String FILE = "student.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter student grade (numeric): ");
        double grade = 0.0;
        try {
            grade = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade input, setting grade to 0.0");
        }

        Student s = new Student(id, name, grade);

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(s);
            System.out.println("Student object serialized to file: " + FILE);
        } catch (IOException e) {
            System.out.println("Error serializing object: " + e.getMessage());
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof Student) {
                Student ds = (Student) obj;
                System.out.println("Deserialized student: " + ds);
            } else {
                System.out.println("Deserialized object is not a Student.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing object: " + e.getMessage());
            e.printStackTrace();
        }

        sc.close();
    }
}
