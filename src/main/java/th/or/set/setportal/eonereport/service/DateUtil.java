package th.or.set.setportal.eonereport.service;

import java.util.Calendar;
import java.util.Date;

import static th.or.set.setportal.eonereport.config.Constants.SLASH;

public class DateUtil {

    public static Calendar getCurrentDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    public static Calendar calcCalendarAsOf(Integer period, Integer quarter, String fiscalEnd) {
        if (quarter == 6 || quarter == 62) {
            //Half year, Half year for financial institution -> Set quarter = 2
            quarter = 2;
        }
        if (quarter == 9) {
            //Annual -> Set quarter = 4
            quarter = 4;
        }

        //29/2
        fiscalEnd = checkLeapYear(fiscalEnd, period);
        String[] fiscalEndList = fiscalEnd.split(SLASH);
        int month = Integer.parseInt(fiscalEndList[1]) - 1;
        int date = Integer.parseInt(fiscalEndList[0]);

        Calendar calendar = getCurrentDate();
        calendar.set(Calendar.YEAR, period);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, date);

        return addMonth(calendar, (4 - quarter) * -3);
    }

    public static Date calcDateAsOf(Integer period, Integer quarter, String fiscalEnd) {
        return calcCalendarAsOf(period, quarter, fiscalEnd).getTime();
    }

    public static Calendar addMonth(Calendar calendar, Integer month) {
        Calendar tmp = (Calendar) calendar.clone();
        if (isEndOfMonth(tmp)) {
            tmp.add(Calendar.MONTH, month);
            tmp.set(Calendar.DATE, tmp.getActualMaximum(Calendar.DATE));
        } else {
            int d = tmp.get(Calendar.DATE);
            int m = tmp.get(Calendar.MONTH) + month;
            int y = tmp.get(Calendar.YEAR);
            tmp.set(y, m, d);
        }
        return tmp;
    }

    public static boolean isEndOfMonth(Calendar date) {
        return (date.get(Calendar.DATE) == date.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    public static String checkLeapYear(String fiscalEnd, int year) {
        if (fiscalEnd != null && !fiscalEnd.isEmpty()) {
            Boolean isEndOfFeburary = isEndOfFeburary(fiscalEnd);
            if (isEndOfFeburary) {
                if (DateUtil.isLeapYear(year)) {
                    return "29/2";
                } else {
                    return "28/2";
                }
            } else {
                return fiscalEnd;
            }
        } else {
            return "31/12";
        }
    }

    public static Boolean isEndOfFeburary(String fiscalEnd) {
        return fiscalEnd.equals("28/2") || fiscalEnd.equals("29/2") || fiscalEnd.equals("28/02") || fiscalEnd.equals("29/02");
    }

    public static boolean isLeapYear(int year) {
//        assert year >= 1583; // not valid before this date.
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

}
