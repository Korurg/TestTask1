package com.korurg.testtask0.nonreactive.util;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean containsIgnoreCase(String text, String match) {
        return text.toLowerCase().contains(match.toLowerCase());
    }

}
