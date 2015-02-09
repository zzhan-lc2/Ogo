package com.ogomonkey.db.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {
    static final String DATE_PATTERN = "yyyy-MM-dd";

    public static Date toDate(String yyyy_mm_dd) {
        try {
            return DateUtils.parseDate(yyyy_mm_dd, DATE_PATTERN);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
