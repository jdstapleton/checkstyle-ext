package com.puppycrawl.tools.checkstyle.checks.whitespace.returnseparation;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class InputReturnSeparationValidStyle {
    private static final Random random = new Random(0xdeadbeef);

    public static void main(String[] args) {
        System.out.println(getMsg(parseInt(args[0])));
    }

    private static String getMsg(int x) {
        if (x / 2 == 4) {
            String msg = "The number is 8.";

            return msg;
        } else {
            String msg = "The number may not be even.";

            return msg;
        }
    }

    private static String multiLineReturn() {
        int x = 0;

        return "this is " +
                "A multi-line " +
                Integer.toHexString(7 * 15) +
                " string.";
    }

    private static String singleStatement() {
        return "";
    }

    private static String singleStatementInIf() {
        if (random.nextBoolean()) {
            return "";
        } else {
            return "";
        }
    }

    public static String singleLineStatement() {
        return "XYZ";
    }

    public static String anotherSingleLineStatement() { int x = 0; return "XYZ"; }

    public static String returnWithComments() {
        boolean coin = random.nextBoolean();
        long die = random.nextLong() % 6;

        if (coin) {
            String result = "heads";
            /*
             * Its valid to have a multi line statement
             * prior to the return statement */
            return "The coin result was " + result;
        } else if (die == 5) {
            String result = Long.toString(die + 1);
            /* multi-line comment on a single line is also valid */
            return "You rolled a " + result;
        } else {
            String result = "Lost";
            // single line comments are valid as well
            return result + " the coin toss and the die roll.";
        }
    }
}
