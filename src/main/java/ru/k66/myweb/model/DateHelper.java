package ru.k66.myweb.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ikydp on 16.02.2017.
 */
public class DateHelper {

    private static final int[] TIME_FIELDS =
            {
                    Calendar.HOUR_OF_DAY,
                    Calendar.HOUR,
                    Calendar.AM_PM,
                    Calendar.MINUTE,
                    Calendar.SECOND,
                    Calendar.MILLISECOND
            };

    public static Date getDate() {

            GregorianCalendar gDate = new GregorianCalendar();

            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();

       return  date;
    }

    public static void main(String[] args) {
        System.out.print(getDate());
    }
}
