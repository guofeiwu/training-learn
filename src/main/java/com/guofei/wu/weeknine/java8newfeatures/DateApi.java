package com.guofei.wu.weeknine.java8newfeatures;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/**
 * Java 8 data api
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/3
 * @since 2018/8/3
 */
public class DateApi {
    @Test
    public void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        print(clock.getZone());
        print(clock.millis());
//        print(clock.instant());
        ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
        print(clock.withZone(zoneId));

        print("==========================");

        Instant instant = clock.instant();
        print(Date.from(instant).getTime());
    }


    @Test
    public void zoneIdsTest() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        // Asia/Hong_kong
        // Asia/Shanghai
        for (String z :
                zoneIds) {
//            print(z);
        }

        ZoneId hongKong = ZoneId.of("Asia/Hong_Kong");
        print(hongKong.getRules());

        ZoneId shangHai = ZoneId.of("Asia/Shanghai");
        print(shangHai.getRules());

        print(shangHai.getId());


        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        ZoneId zoneId = ZoneOffset.ofOffset("UTC", zoneOffset);

        print(zoneId.getId());
        print(zoneId.normalized().getId());
    }


    @Test
    public void localTimeTest() {
        LocalTime localTime = LocalTime.now();
        String str = localTime.getHour() + ":" + localTime.getMinute() + ":" + (localTime.getSecond() < 10 ? "0" + localTime.getSecond() : localTime.getSecond());
        print(str);

        localTime = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        LocalDateTime localDateTime = localTime.atDate(LocalDate.now());
        print(localDateTime.getDayOfYear());


        print("=======================");
        ZoneId hongKong = ZoneId.of("Asia/Hong_Kong");
        ZoneId shangHai = ZoneId.of("Asia/Shanghai");


        LocalTime hk = LocalTime.now(hongKong);
        LocalTime sh = LocalTime.now(shangHai);


        print(hk.isAfter(sh));
        long hoursBetween = ChronoUnit.HOURS.between(hk, sh);
        print(hoursBetween);
        print("=========================");

        // DateTimeParseException
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG)
//                .withLocale(Locale.CHINA);
//
//        LocalTime leetTime = LocalTime.parse("January 12, 1952", dateTimeFormatter);
//        print(leetTime);

        LocalTime late = LocalTime.of(23, 59, 59);
        print(late);

    }


    @Test
    public void localDateTest() {
        LocalDate date = LocalDate.now();
        print(date);
        LocalDate tomorrow = date.plusDays(1);
        print(tomorrow);

//        DateTimeFormatter germanFormatter =
//                DateTimeFormatter
//                        .ofLocalizedDate(FormatStyle.SHORT)
//                        .withLocale(Locale.CHINA);
//
//        LocalDate xmas = LocalDate.parse("3:30pm", germanFormatter);
//        System.out.println(xmas);


        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("11 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);
    }


    private void print(Object o) {
        System.out.println("value: " + o);
    }


}
