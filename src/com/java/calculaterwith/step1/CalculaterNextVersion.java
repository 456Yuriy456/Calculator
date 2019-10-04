package com.java.calculaterwith.step1;

//This is new version of Calculator
public class CalculaterNextVersion {
    public static void main(String[] args) {
        String formula = "2*3+(4*3+6-2*8)-1";

//        formula = lookFor(formula, '(');

        formula = performOperation(formula, '*');
        System.out.println("Result *: " + formula);
        formula = performOperation(formula, '/');
        System.out.println("Result /: " + formula);
        formula = performOperation(formula, '+');
        System.out.println("Result +: " + formula);
        formula = performOperation(formula, '-');
        System.out.println("Final: " + formula);
    }

    private static boolean isDigit(char c) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (char digit : digits) {
            if (c == digit)
                return true;
        }

        return false;
    }

    private static int findFirstDigitIndex(String formulaToSearch, int startFromIndex) {
        int firstDigitIndex = startFromIndex - 1;

        while (firstDigitIndex > 0 && isDigit(formulaToSearch.charAt(firstDigitIndex - 1)))
            firstDigitIndex--;

        return firstDigitIndex;
    }

    private static int findLastDigitIndex(String formulaToSearch, int startFromIndex) {
        int rightNumberLastDigit = startFromIndex + 1;

        while (rightNumberLastDigit < formulaToSearch.length() - 1 && isDigit(formulaToSearch.charAt(rightNumberLastDigit + 1)))
            rightNumberLastDigit++;

        return rightNumberLastDigit;
    }

    private static String performOperation(String formula, char operation) {
        int operationCharIndex;

        while ((operationCharIndex = formula.indexOf(operation)) != -1) {
            int leftNumberFirstDigitIndex = findFirstDigitIndex(formula, operationCharIndex);
            int rightNumberLastDigitIndex = findLastDigitIndex(formula, operationCharIndex);

            int leftNumber = Integer.parseInt(formula.substring(leftNumberFirstDigitIndex, operationCharIndex));
            int rightNumber = Integer.parseInt(formula.substring(operationCharIndex + 1, rightNumberLastDigitIndex + 1));

            int result = 0;
            switch (operation) {
                case '*':
                    result = leftNumber * rightNumber;
                    break;
                case '/':
                    result = leftNumber / rightNumber;
                    break;
                case '+':
                    result = leftNumber + rightNumber;
                    break;
                case '-':
                    result = leftNumber - rightNumber;
                    break;
            }

            formula = formula.replace(formula.substring(leftNumberFirstDigitIndex, rightNumberLastDigitIndex + 1), String.valueOf(result));
        }

        return formula;
    }

    
    private static String lookFor(String formula, char sign) {

        int indexFirst, indexLast;
        indexLast = (indexFirst = formula.indexOf(sign));
        indexLast++;
        char charAt = formula.charAt(indexLast);
        while (true) {
            if (charAt != ')') {
                indexLast++;
                charAt = formula.charAt(indexLast);
                continue;
            } else {
                indexLast++;
                break;
            }
        }
        String formulaOld = formula.substring(indexFirst, indexLast);
        String formulaNew = formula.substring(++indexFirst, --indexLast);

        formulaNew = performOperation(formulaNew, '*');
        System.out.println("Formula in side of formula, result *: " + formulaNew);
        formulaNew = performOperation(formulaNew, '/');
        System.out.println("Formula in side of formula, result /: " + formulaNew);
        formulaNew = performOperation(formulaNew, '+');
        System.out.println("Formula in side of formula, result +: " + formulaNew);
        formulaNew = performOperation(formulaNew, '-');
        System.out.println("Formula in side of formula, result -: " + formulaNew);

        formula = formula.replace(formulaOld, formulaNew);
        return formula;
    }
}