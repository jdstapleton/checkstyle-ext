package com.puppycrawl.tools.checkstyle.checks.whitespace.multilinestatement;

import com.google.common.collect.ImmutableList;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public enum InputMultilineStatementEnum {
    /**
     * AAA.
     */
    AAA(ImmutableList.of("Hello")),

    /**
     * BBB.
     */
    BBB(
            ImmutableList.of(
                    "Good bye",
                    "Earth")),

    /**
     * CCC.
     */
    CCC(
            ImmutableList.of(
                    "Hello",
                    "Mars"
            ));

    private final List<String> strings;

    InputMultilineStatementLambdas(List<String> strs) {
        this.strings = strs;
    }
}
