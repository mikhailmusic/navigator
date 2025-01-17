package util;

import struct.ArrayList;

public class Utils {
    public static String joinStrings(ArrayList<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder(strings.get(0));
        for (int i = 1; i < strings.size(); i++) {
            result.append("->").append(strings.get(i));
        }

        return result.toString();
    }

    public static <K> int hashCode(K value) {
        int result = 1;

        if (value instanceof String strKey) {
            for (int i = 0; i < strKey.length(); i++) {
                result = 31 * result + strKey.charAt(i);
            }
        } else if (value instanceof Integer) {
            result = (Integer) value;
        } else if (value instanceof Long) {
            result = (int) ((Long) value ^ (Long) value >>> 32);
        } else if (value instanceof Double) {
            long bits = Double.doubleToLongBits((Double) value);
            result = (int) (bits ^ (bits >>> 32));
        } else {
            result = value.hashCode();
        }

        return result;
    }
}
