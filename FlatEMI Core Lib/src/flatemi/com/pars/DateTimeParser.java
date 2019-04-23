/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatemi.com.pars;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author daan-
 */
public class DateTimeParser {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter LOG_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");

    public static LocalDateTime toDateTime(String q) {
        String num = q.replaceAll("\\D", "");
        if (num.length() != 14) {
            System.out.println("Cannot interpret DateTime for unknown Stringsize @ " + q);
            return null;
        }
        int year = Integer.parseInt(num.substring(0, 4));
        int month = Integer.parseInt(num.substring(4, 6));
        int day = Integer.parseInt(num.substring(6, 8));
        int hour = Integer.parseInt(num.substring(8, 10));
        int minute = Integer.parseInt(num.substring(10, 12));
        int second = Integer.parseInt(num.substring(12));
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public static String fromDateTime(LocalDateTime ldt) {
        return FORMATTER.format(ldt);
    }

    public static String toDateFormat(LocalDate localDate) {
        return DATE_FORMATTER.format(localDate);
    }

    public static LocalDate fromDateFormat(String date) {
        String[] cells = date.split("-");
        int day = Integer.parseInt(cells[0]);
        int month = Integer.parseInt(cells[1]);
        int year = Integer.parseInt(cells[2]);
        return LocalDate.of(year, month, day);
    }

    public static String makeLogStamp() {
        return LOG_FORMATTER.format(LocalDateTime.now());
    }
}
