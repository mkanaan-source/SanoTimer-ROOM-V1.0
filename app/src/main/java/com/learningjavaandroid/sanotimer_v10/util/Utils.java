package com.learningjavaandroid.sanotimer_v10.util;

// 03.04.2023 - a general utility class that will be used to house many different types of
// methods that can be used across the board in this app.

import android.text.TextUtils;

public class Utils {

    // 03.04.2023 - the method below takes two int's for the hour and minute and converts it into
    // a string in the form "HH:MM".
    // 14.04.2023 - made a few changes on this method so that when the user enters a time like
    // 09:00 or 09:05, the resulting string output will actually be set to something like '09:00' and
    // not something non-intuitive like '9:0' or '9:5' :).

    public static String timeFromIntToString(int hour, int minute) {
        String hourAsString;
        String minuteAsString;
        if ((hour >= 0) && (hour <= 9)) {
            hourAsString = "0" + hour;
        } else {
            hourAsString = String.valueOf(hour);
        }

        if ((minute >= 0) && (minute <= 9)) {
            minuteAsString = "0" + minute;
        } else {
            minuteAsString = String.valueOf(minute);
        }

        return hourAsString + ":" + minuteAsString;
    }

    // 03.04.2023 - the method below takes a string for the time (in the form "HH:MM") and
    // parses it and returns an array of two int's (one for the hour and one for the minute).
    public static int[] timeFromStringToInt(String timeAsString) {

        int[] hourAndMinute = new int[2];

        // 03.04.2023 - here we are setting up a SimpleStringSplitter object with ':' for the delimiter.
        TextUtils.SimpleStringSplitter stringSplitter =
                new TextUtils.SimpleStringSplitter(':');

        // 03.04.2023 - here we are setting the string that we want the splitter to split.
        stringSplitter.setString(timeAsString);

        // 03.04.2023 - now do the actual split.
        hourAndMinute[0] = Integer.parseInt(stringSplitter.next());
        hourAndMinute[1] = Integer.parseInt(stringSplitter.next());

        return hourAndMinute;

    }

}
