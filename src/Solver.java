import java.text.DecimalFormat;

public class Solver {
    private String current;
    private String previous;
    private String operator;
    private String radDeg;

    public Solver() {
        current = "0";
        previous = "0";
        operator = "";
        radDeg = "Deg";
    }

    public void switchRadDeg() {
        if (this.radDeg.equals("Rad")) {
            this.radDeg = "Deg";
        } else {
            this.radDeg = "Rad";
        }
    }

    public String compute(String input) {
        if (isNumeric(input)) {
            if (input.equals("pi")) {
                current = Double.toString(Math.PI);
            } else if (current.equals("0")) {
                current = input;
            } else {
                current += input;
            }
            return current;
        } else if (input.equals("C")) {
            current = "0";
            previous = "0";
            operator = "";
            return current;
        } else if (isTrigFunc(input)){
            previous = "0";
            current = applyTrigFunc(input, current);
            return current;
        } else if (input.equals("1/x")) {
            return applyInverse();
        } else if (input.equals("x^2")) {
            return applyExp(2);
        } else if (input.equals("sqrt")) {
            return applyExp(0);
        } else if (input.equals("x!")) {
            return applyFactorial();
        } else { //At this point, input is one of the basic operators (+, -, /, *)
            return computeBasicOperators(input);
        }
    }

    private String computeBasicOperators(String input) {
        if (operator.equals("")) { //At this point, input is one of the basic operators
            previous = current;
            operator = input;
            current = "0";
            return previous;
        } else if (input.equals("=")) {
            current = evaluate(operator, previous, current);
            operator = "";
            previous = "0";
            return current;
        } else { //there is already a pending operation
            previous = evaluate(operator, previous, current);
            operator = input;
            current = "0";
            return previous;
        }
    }

    private String applyInverse() {
        if (current.equals("0")) {
            return "Error: Divide by 0";
        }
        current = Double.toString(1/Double.parseDouble(current));
        return current;
    }

    private String applyExp(int exp) {
        if (exp == 2) {
            current = Double.toString(Math.pow(Double.parseDouble(current), 2));
        } else {
            current = Double.toString(Math.sqrt(Double.parseDouble(current)));
        }
        return current;
    }

    private String applyFactorial() {
        try {
            int currentInt = Integer.parseInt(current);

            int result = 1;
            for (int i = 1; i <= currentInt; i++) {
                result *= i;
            }

            return Integer.toString(result);
        } catch (NumberFormatException e) {
            return "Cannot (yet) perform factorials on decimals";
        }
    }

    //Returns true is string is a numeric string, a decimal point, or pi
    private boolean isNumeric(String str) {
        if (str.equals(".") || str.equals("pi")) {
            return true;
        }
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
    private String evaluate(String operator, String operand1, String operand2) {
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

    private boolean isTrigFunc(String str) {
        return str.equals("sin") || str.equals("cos") || str.equals("tan");
    }

    private String applyTrigFunc(String func, String value) {
        double numValue = Double.parseDouble(value);

        //Need to change value to radians because Math.sin/cos/tan only takes in radians
        if (this.radDeg.equals("Deg")) {
            numValue = Math.toRadians(numValue);
        }

        switch(func) {
            case "sin":
                return checkZero(Math.sin(numValue));
            case "cos":
                return checkZero(Math.cos(numValue));
            case "tan":
                return checkZero(Math.tan(numValue));
            default:
                return "0";
        }
    }

    private String checkZero(double num) {
        DecimalFormat rounder = new DecimalFormat("0.######");
        String rounded = rounder.format(num);
        if((Math.round(num) + "").equals(rounded)) {
            return rounded;
        } else {
            return num + "";
        }
    }
}