package com.icboluo.datastructure.stack;

/**
 * @author icboluo
 */
public class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
/*        return switch (operation) {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> 0;
        };*/
        if ("+".equals(operation) || "-".equals(operation)) {
            return 1;
        }
        if ("*".equals(operation) || "/".equals(operation)) {
            return 2;
        }
        return 0;
    }
}
