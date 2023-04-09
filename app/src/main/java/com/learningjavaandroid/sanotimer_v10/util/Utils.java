package com.learningjavaandroid.sanotimer_v10.util;

// 03.04.2023 - a general utility class that will be used to house many different types of
// methods that can be used across the board in this app.

import android.text.TextUtils;

public class Utils {

    // 03.04.2023 - the method below takes two int's for the hour and minute and converts it into
    // a string in the form "HH:MM".
    public static String timeFromIntToString(int hour, int minute) {
        return hour + ":" + minute;
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
