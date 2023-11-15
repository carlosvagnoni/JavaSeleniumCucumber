package com.automatedtests.demoblaze.utils;

import java.util.concurrent.TimeUnit;

/**
 * Utility class for performing assertions and validations on various data types.
 */
public class Expect {
    // Array of supported data types for validation
    private static Class<?>[] dataTypes = {String.class, Integer.class, Float.class, Boolean.class,
            java.util.List.class, java.util.Map.class, java.util.Set.class};

    private static Object value;
    private static Class<?> dataType;

    /**
     * Constructs an Expect object with the provided actual value.
     * Validates if the provided value belongs to the supported data types.
     * @param actualValue The value to be validated.
     * @throws IllegalArgumentException if the data type is not supported.
     */
    public Expect(Object actualValue) {
        boolean isValidType = false;
        for (Class<?> type : dataTypes) {
            if (type.isInstance(actualValue)) {
                isValidType = true;
                break;
            }
        }
        if (!isValidType) {
            throw new IllegalArgumentException("DataType not supported. Try one of: " + java.util.Arrays.toString(dataTypes));
        }
        this.value = actualValue;
        this.dataType = actualValue.getClass();
    }

    public static void toBeEqual(Object expectedValue) {
        if (!dataType.isInstance(expectedValue)) {
            throw new IllegalArgumentException("DataType not supported. Try: " + dataType.getName());
        }

        assert value.equals(expectedValue);
        waitSeconds(1);
    }

    public static void toNotBeEqual(Object expectedValue) {
        if (!dataType.isInstance(expectedValue)) {
            throw new IllegalArgumentException("DataType not supported. Try: " + dataType.getName());
        }

        assert !value.equals(expectedValue);
        waitSeconds(1);
    }

    public static void toContain(String innerValue) {
        if (!String.class.isInstance(value)) {
            throw new IllegalArgumentException("Value type not supported. Use String type");
        }

        assert ((String) value).contains(innerValue);
        waitSeconds(1);
    }

    public static void isTrue() {
        if (!Boolean.class.isInstance(value)) {
            throw new IllegalArgumentException("Value type not supported. Use Boolean type");
        }

        assert (Boolean) value;
        waitSeconds(1);
    }

    public static void isFalse() {
        if (!Boolean.class.isInstance(value)) {
            throw new IllegalArgumentException("Value type not supported. Use Boolean type");
        }

        assert !(Boolean) value;
        waitSeconds(1);
    }

    // Helper method to wait for a specified number of seconds
    private static void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
