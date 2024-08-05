package Version1;

import java.util.Scanner;

public class TanFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an angle in radians: ");

        if (scanner.hasNextDouble()) {
            double angle = scanner.nextDouble();
            try {
                double result = calculateTan(angle);
                System.out.println("The tangent of " + angle + " is: " + result);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid input. Please enter a real number.");
        }

        scanner.close();
    }

    public static double calculateTan(double x) {
        double piOver2 = Math.PI / 2;
        double remainder = Math.abs(x % Math.PI);
        if (Math.abs(remainder - piOver2) < 1e-4) {
            throw new ArithmeticException("Tangent is undefined for this input.");
        }
        return Math.tan(x);
    }
}
