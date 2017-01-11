import java.text.DecimalFormat;

public class Solver {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Evaluates operator, then operands, then applies them
     * @param operator +, -, *, /
     * @param operand1 Numerical string
     * @param operand2 Numerical string
     * @return String answer of expression
     */
    public static String evaluate(String operator, String operand1, String operand2) {
        double op1 = Double.parseDouble(operand1);
        double op2 = Double.parseDouble(operand2);
        switch(operator) {
            case "+":
                return checkZero(op1+op2);
            case "-":
                return checkZero(op1-op2);
            case "*":
                return checkZero(op1*op2);
            case "/":
                return checkZero(op1/op2);
            default:
                return "0";
        }
    }

    private static String checkZero(double num) {
        DecimalFormat rounder = new DecimalFormat("0.########");
        String rounded = rounder.format(num);
        if((Math.round(num) + "").equals(rounded)) {
            return rounded;
        } else {
            return num + "";
        }
    }
}