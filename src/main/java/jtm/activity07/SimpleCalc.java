package jtm.activity07;

import org.junit.Assert;

import com.mysql.cj.exceptions.AssertionFailedException;

public class SimpleCalc {

    public static int add(int a, int b) throws SimpleCalcException {
    	validateInput(a, b);
        return validateOutput(a, b, "+");
    }

    public static int subtract(int a, int b) throws SimpleCalcException {
    	validateInput(a, b);
        return validateOutput(a, b, "-");
    }

    public static int multiply(int a, int b) throws SimpleCalcException {
    	validateInput(a, b);
        return validateOutput(a, b, "*");
    }

    public static int divide(int a, int b) throws SimpleCalcException {
    	validateInput(a, b);
        return validateOutput(a, b, "/");
    }

    private static void validateInput(int a, int b) throws SimpleCalcException {
    	try {
			String aPart = "";
			if (a < -10) {
				aPart = String.format("a: %d is below -10", a);
			} else if (a > 10) {
				aPart = String.format("a: %d is above 10", a);
			}

			String bPart = "";
			if (b < -10) {
				bPart = String.format("b: %d is below -10", b);
			} else if (b > 10) {
				bPart = String.format("b: %d is above 10", b);
			}

			if (!aPart.isEmpty() && !bPart.isEmpty()) {
				bPart = " and " + bPart;
			}

			if (!aPart.isEmpty() || !bPart.isEmpty()) {
				Assert.fail("input value " + aPart + bPart);
			}
		} catch (AssertionError e) {
			throw new SimpleCalcException("Assertion error", e);
		}
    }

    private static int validateOutput(int a, int b, String operation) throws SimpleCalcException
    {
		int result = -999;
		
		switch (operation) {
			case "+": {
				result = a + b;
			} break;
			case "-": {
				result = a - b;
			} break;
			case "*": {
				result = a * b;
			} break;
			case "/": {
				try {
					result = a / b;
				} catch (ArithmeticException e) {
					throw new SimpleCalcException("division by zero", e);
				}
			} break;
			default: {
				throw new SimpleCalcException("unknown opertaion");
			}
		}

		String start = String.format(
			"output value %d %s %d = ", 
			a, operation, b
		);
		
		String right = ""; 
		if (result < -10) {
			right = result + " is below -10";
		} else if (result > 10) {
			right = result + " is above 10";
		}

		if (!right.isEmpty()) {
			throw new SimpleCalcException(start + right);
		}
		
		return result;
    }
}
