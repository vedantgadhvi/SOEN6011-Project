# Tangent Calculator

This project is a Java-based application that provides a graphical user interface (GUI) to calculate the tangent of an angle given in radians. The application, designed for scientific computing purposes, demonstrates the implementation of the `tan(x)` function from scratch, avoiding the use of built-in Java libraries for trigonometric calculations.

## Features

- **Graphical User Interface (GUI):**
  The application uses Java Swing to create a user-friendly interface where users can input an angle in radians and receive the tangent value.

- **Custom Trigonometric Functions:**
  The calculation of the tangent function leverages Taylor series expansions to compute the sine and cosine values. This ensures that the implementation does not rely on the standard `Math.tan` function, showcasing an in-depth understanding of mathematical function implementation.

- **Error Handling:**
  The application includes robust error handling to manage invalid inputs and cases where the tangent function is undefined, providing clear and helpful error messages to the user.

- **Code Quality and Versioning:**
  The project adheres to the Google Java Style Guide to maintain high code quality. Semantic versioning is used to manage and document changes, with the current version being `3.0`.

## Directory Structure

- `src/`
  - `test/`
    - `TanFunctionGuiTest.java`
  - `version1/`
    - `TanFunction.java`
  - `version2/`
    - `TanFunctionGUI.java`
  - `version3/`
    - `TanFunctionGui.java`
- `README.md`

## Usage

1. **Compile the Code:**
   javac TanFunctionGui.java
2. **Run the Application:**
   java TanFunctionGui
3. **Input an Angle:**
   Enter an angle in radians in the provided text field and click "Calculate" to see the tangent value.
   
## Author

- **Vedant Gadhavi**

## Description

This project not only serves as a practical tool for calculating the tangent of an angle but also as an educational example of implementing complex mathematical functions in Java, developing a GUI application, and adhering to best coding practices.

## Versions

- **3**

