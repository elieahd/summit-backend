package utils;

import java.util.Random;

public class Randomizer {

    private static final Random RANDOM = new Random();

    private static final int MAX_INTEGER = 100;
    private static final int MIN_INTEGER = 1;

    private static final int STRING_LENGTH = 12;
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789";

    private Randomizer() {
        // utility classes shouldn't be initialized
    }

    public static String randomString() {
        final StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    public static Integer randomInteger() {
        return RANDOM.nextInt(MAX_INTEGER - MIN_INTEGER) + MIN_INTEGER;
    }

    public static <T extends Enum<T>> T random(Class<T> enumClass) {
        final T[] values = enumClass.getEnumConstants();
        return values[RANDOM.nextInt(values.length)];
    }

}
