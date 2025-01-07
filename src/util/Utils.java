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
}
