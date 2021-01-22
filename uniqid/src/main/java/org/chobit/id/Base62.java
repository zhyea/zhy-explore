package org.chobit.id;

/**
 * @author robin
 */
public final class Base62 {

    private static final char[] DIGITS_CHAR = "56789abcdefghijklmnopqrstuvwxyz01234ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int BASE = DIGITS_CHAR.length;
    private static final int FAST_SIZE = 'z';
    private static final int[] DIGITS_INDEX = new int[FAST_SIZE + 1];

    static {
        for (int i = 0; i < FAST_SIZE; i++) {
            DIGITS_INDEX[i] = -1;
        }
        //
        for (int i = 0; i < BASE; i++) {
            DIGITS_INDEX[DIGITS_CHAR[i]] = i;
        }
    }

    public static long decode(String s) {
        long result = 0L;
        long multiplier = 1;
        for (int pos = s.length() - 1; pos >= 0; pos--) {
            int index = getIndex(s, pos);
            result += index * multiplier;
            multiplier *= BASE;
        }
        return result;
    }

    public static String encode(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number(Base62) must be positive: " + number);
        }
        if (number == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        while (number != 0) {
            builder.append(DIGITS_CHAR[(int) (number % BASE)]);
            number /= BASE;
        }
        return builder.reverse().toString();
    }

    private static int getIndex(String s, int pos) {
        char c = s.charAt(pos);
        if (c > FAST_SIZE) {
            throw new IllegalArgumentException("Unknown character for Base62: " + s);
        }
        int index = DIGITS_INDEX[c];
        if (index == -1) {
            throw new IllegalArgumentException("Unknown character for Base62: " + s);
        }
        return index;
    }


    private Base62() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}

