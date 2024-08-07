package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * This class provides a GUI to calculate the tangent of an angle in radians.
 *
 * @version 2.1.3
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
    SwingUtilities.invokeLater(TanFunctionGui::createAndShowGui);
  }

  /**
   * Creates and displays the GUI.
   */
  private static void createAndShowGui() {
    JFrame frame = new JFrame("Tangent Calculator");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    frame.add(panel);
    placeComponents(panel);

    frame.setVisible(true);
  }

  /**
   * Places the components on the panel.
   *
   * @param panel The panel to add components to.
   */
  private static void placeComponents(JPanel panel) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10, 10, 10, 10);
    constraints.fill = GridBagConstraints.HORIZONTAL;

    JLabel userLabel = new JLabel("Enter an angle in radians:");
    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(userLabel, constraints);
    setAccessibility(userLabel, "Angle Input Label",
        "Label prompting the user to enter an angle in radians");

    JTextField angleText = new JTextField(10);
    constraints.gridx = 1;
    panel.add(angleText, constraints);
    setAccessibility(angleText, "Angle Input Field",
        "Input field for entering an angle in radians");

    final JButton calculateButton = new JButton("Calculate");
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    panel.add(calculateButton, constraints);
    setAccessibility(calculateButton, "Calculate Button",
        "Button to calculate the tangent of the entered angle");

    JTextArea resultArea = new JTextArea(2, 20);
    constraints.gridy = 2;
    resultArea.setLineWrap(true);
    resultArea.setWrapStyleWord(true);
    resultArea.setEditable(false);
    panel.add(resultArea, constraints);
    setAccessibility(resultArea, "Result Area",
        "Text area displaying the result or error messages");

    calculateButton.addActionListener(e -> {
      String angleStr = angleText.getText();
      try {
        double angle = Double.parseDouble(angleStr);
        double result = calculateTan(angle);
        resultArea.setText("The tangent of " + angle + " is: " + formatResult(result));
      } catch (NumberFormatException ex) {
        resultArea.setText("Invalid input. Please enter a real number.");
      } catch (ArithmeticException ex) {
        resultArea.setText(ex.getMessage());
      }
    });
  }

  /**
   * Sets accessibility information for a component.
   *
   * @param component   The component to set accessibility information for.
   * @param name        The accessible name.
   * @param description The accessible description.
   */
  private static void setAccessibility(JComponent component, String name, String description) {
    AccessibleContext context = component.getAccessibleContext();
    context.setAccessibleName(name);
    context.setAccessibleDescription(description);
    component.setToolTipText(description);
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

  /**
   * Formats the result to a readable string.
   *
   * @param result The result to format.
   * @return The formatted string.
   */
  private static String formatResult(double result) {
    DecimalFormat df = new DecimalFormat("#.######");
    return df.format(result);
  }
}
