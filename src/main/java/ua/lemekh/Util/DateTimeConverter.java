package ua.lemekh.Util;

import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    public static final DateTimeFormatter FORMATTER_FOR_SAVE = DateTimeFormatter.ofPattern("dd-MM-YYYY");
}
