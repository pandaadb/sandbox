package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class ParseTestWithDot {

    public static void main(String[] args) {
        
        LocalDateTime t = LocalDateTime.parse("2017.06.04 21:39:16.612", DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS"));
        System.out.println(t);
    }
}
