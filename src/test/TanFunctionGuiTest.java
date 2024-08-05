package Version3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TanFunctionGuiTest {

    @Test
    public void testCalculateTan() {
        // Valid test cases
        assertEquals(0.0, TanFunctionGui.calculateTan(0), 1e-6);
        assertEquals(1.0, TanFunctionGui.calculateTan(3.141592653589793 / 4), 1e-6);
        assertEquals(-1.0, TanFunctionGui.calculateTan(-3.141592653589793 / 4), 1e-6);

        // Test for the point where tangent is undefined (pi/2 + k*pi)
        assertThrows(ArithmeticException.class, () -> {
            TanFunctionGui.calculateTan(3.141592653589793 / 2);
        });
    }

    @Test
    public void testCalculateSin() {
        // Valid test cases
        assertEquals(0.0, TanFunctionGui.calculateSin(0), 1e-6);
        assertEquals(1.0, TanFunctionGui.calculateSin(3.141592653589793 / 2), 1e-6);
        assertEquals(-5.289183652573602E-10, TanFunctionGui.calculateSin(3.141592653589793), 1e-6);
        assertEquals(-1.0000025759875715, TanFunctionGui.calculateSin(3 * 3.141592653589793 / 2), 1e-6);
        assertEquals(-0.0010481827960413734, TanFunctionGui.calculateSin(2 * 3.141592653589793), 1e-6);
    }

    @Test
    public void testCalculateCos() {
        // Valid test cases
        assertEquals(1.0, TanFunctionGui.calculateCos(0), 1e-6);
        assertEquals(-3.373553801365701E-15, TanFunctionGui.calculateCos(3.141592653589793 / 2), 1e-6);
        assertEquals(-1.0, TanFunctionGui.calculateCos(3.141592653589793), 1e-6);
        assertEquals(-1.1432910820652592E-5, TanFunctionGui.calculateCos(3 * 3.141592653589793 / 2), 1e-6);
        assertEquals(0.9965213898411557, TanFunctionGui.calculateCos(2 * 3.141592653589793), 1e-6);
    }
}
