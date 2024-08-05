package Version2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TanFunctionGUI {

    private static final double PI = 3.141592653589793;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tangent Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Enter an angle in radians:");
        userLabel.setBounds(10, 20, 200, 25);
        panel.add(userLabel);

        JTextField angleText = new JTextField(20);
        angleText.setBounds(220, 20, 165, 25);
        panel.add(angleText);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 80, 150, 25);
        panel.add(calculateButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 110, 365, 50);
        resultArea.setEditable(false);
        panel.add(resultArea);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String angleStr = angleText.getText();
                try {
                    double angle = Double.parseDouble(angleStr);
                    double result = calculateTan(angle);
                    resultArea.setText("The tangent of " + angle + " is: " + result);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input. Please enter a real number.");
                } catch (ArithmeticException ex) {
                    resultArea.setText(ex.getMessage());
                }
            }
        });
    }

    public static double calculateTan(double x) {
        double piOver2 = PI / 2;
        double remainder = Math.abs(x % PI);
        if (Math.abs(remainder - piOver2) < 1e-4) {
            throw new ArithmeticException("Tangent is undefined for this input.");
        }
        double sinX = calculateSin(x);
        double cosX = calculateCos(x);
        return sinX / cosX;
    }

    public static double calculateSin(double x) {
        double term = x;
        double sum = term;
        for (int n = 1; n < 10; n++) {
            term *= -x * x / (2 * n * (2 * n + 1));
            sum += term;
        }
        return sum;
    }

    public static double calculateCos(double x) {
        double term = 1.0;
        double sum = term;
        for (int n = 1; n < 10; n++) {
            term *= -x * x / (2 * n * (2 * n - 1));
            sum += term;
        }
        return sum;
    }
}
