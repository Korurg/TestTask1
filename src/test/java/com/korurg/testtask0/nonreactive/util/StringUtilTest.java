package com.korurg.testtask0.nonreactive.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("TestWithoutContext")
class StringUtilTest {

    @Test
    void containsIgnoreCase_contains() {
        String text = "ABcDE";
        String match = "BCd";

        assertTrue(StringUtil.containsIgnoreCase(text, match));
    }

    @Test
    void containsIgnoreCase_notContains() {
        String text = "ABcsDE";
        String match = "BCd";

        assertFalse(StringUtil.containsIgnoreCase(text, match));
    }

}