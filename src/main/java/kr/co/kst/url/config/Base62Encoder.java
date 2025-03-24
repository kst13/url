package kr.co.kst.url.config;

public class Base62Encoder {

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int BASE = 62;


    public static String encode(long number) {
        if (number == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % BASE);
            sb.append(BASE62[remainder]);
            number /= BASE;
        }

        return sb.reverse().toString();
    }

    public static long decode(String base62) {
        long result = 0;
        for (int i = 0; i < base62.length(); i++) {
            result = result * BASE + indexOf(base62.charAt(i));
        }
        return result;
    }


    private static int indexOf(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        if (c >= 'a' && c <= 'z') return c - 'a' + 36;
        throw new IllegalArgumentException("Invalid Base62 character: " + c);
    }
}

