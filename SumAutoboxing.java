import java.util.ArrayList;
import java.util.Scanner;

public class SumAutoboxing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Enter integers separated by spaces or press Enter to input one per line.\nType 'done' when finished:");

        while (true) {
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("done")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                if (p.equalsIgnoreCase("done")) {
                    break;
                }
                try {
                    // parse string to primitive int, autobox into Integer when adding
                    int val = Integer.parseInt(p);
                    list.add(val); // autoboxing from int to Integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer: '" + p + "' - skipping.");
                }
            }
        }

        // sum using unboxing in enhanced for-loop
        int sum = 0;
        for (Integer num : list) {
            if (num != null) {
                sum += num; // unboxing Integer to int
            }
        }

        System.out.println("Numbers entered: " + list);
        System.out.println("Sum: " + sum);
        sc.close();
    }
}
