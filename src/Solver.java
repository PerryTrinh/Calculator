import java.text.DecimalFormat;

public class Solver {
    private String current;
    private String previous;
    private String operator;

    public Solver() {
        current = "0";
        previous = "0";
        operator = "";
    }

    public String compute(String input) {
        if (isNumeric(input)) {
            if (current.equals("0")) {
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
        } else if (operator.equals("")) { //At this point, input is one of the basic operators
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

    //Returns true is string is a numeric string or a decimal point
    private boolean isNumeric(String str) {
        if (str.equals(".")) {
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

    private String applyTrigFunc(String func, String degrees) {
        double numRadians = Math.toRadians(Double.parseDouble(degrees));
        switch(func) {
            case "sin":
                return checkZero(Math.sin(numRadians));
            case "cos":
                return checkZero(Math.cos(numRadians));
            case "tan":
                return checkZero(Math.tan(numRadians));
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