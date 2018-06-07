package ua.lemekh.Util;

import java.util.Objects;

public class StringUtil {
    public static String getNameFromPath(String url) {
        if (Objects.isNull(url) || url.isEmpty()) {
            return null;
        }
        int index = url.lastIndexOf("\\");

        return url.substring(index + 1, url.length());
    }
}
