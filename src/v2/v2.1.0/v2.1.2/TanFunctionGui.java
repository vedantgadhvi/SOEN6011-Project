package Version3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class provides a GUI to calculate the tangent of an angle in radians.
 *
 * @version 2.1.2
 * @author Vedant Gadhavi
 */
public class TanFunctionGui {

    private static final double PI = 3.141592653589793;

    /**
     * Main method to create the GUI and set it visible.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tangent Calculator");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null); // Center the frame

            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(new GridBagLayout());

            frame.add(panel);
            placeComponents(panel);

            frame.setVisible(true);
        });
    }

    /**
     * Places the components on the panel.
     *
     * @param panel The panel to add components to.
     */
    private static void placeComponents(JPanel panel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Enter an angle in radians:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(userLabel, constraints);

        JTextField angleText = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(angleText, constraints);

        JButton calculateButton = new JButton("Calculate");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(calculateButton, constraints);

        JTextArea resultArea = new JTextArea(3, 30);
        resultArea.setWrapStyleWord(true);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);

        calculateButton.addActionListener(e -> {
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
        });
    }

    /**
     * Calculates the tangent of an angle using Taylor series for sine and cosine.
     *
     * @param x The angle in radians.
     * @return The tangent of the angle.
     * @throws ArithmeticException If the tangent is undefined for the input.
     */
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

    /**
     * Calculates the sine of an angle using Taylor series expansion.
     *
     * @param x The angle in radians.
     * @return The sine of the angle.
     */
    public static double calculateSin(double x) {
        double term = x;
        double sum = term;
        for (int n = 1; n < 10; n++) {
            term *= -x * x / (2 * n * (2 * n + 1));
            sum += term;
        }
        return sum;
    }

    /**
     * Calculates the cosine of an angle using Taylor series expansion.
     *
     * @param x The angle in radians.
     * @return The cosine of the angle.
     */
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
