package com.works.sharedlibrary.util;

import java.time.ZoneId;
import java.util.Date;

/**
 * @author mali.sahin
 * @since 2019-06-25.
 */
public class DateUtil {

    public static int getYear(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }
}
