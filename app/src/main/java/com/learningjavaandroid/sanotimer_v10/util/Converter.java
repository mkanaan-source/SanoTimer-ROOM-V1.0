package com.learningjavaandroid.sanotimer_v10.util;

import android.util.Log;

import androidx.room.TypeConverter;

import com.learningjavaandroid.sanotimer_v10.model.Day;

// 24.04.2023 - a class to handle type conversions to and from custom-made classes (in our case
// mainly the 'Day' class) to and from ROOM-compatible classes.
public class Converter {

    @TypeConverter
    public static String fromDay(Day day) {

        // 24.04.2023 - note that the name() method comes with ANY ENUM class. Its main
        // function is to return the string representation of the ENUM object.
        return day == null ? null : day.name();
    }

    // 24.04.2023 - a type converter method to convert from the string representation of a day
    // enum (MONDAY, TUESDAY, WEDNESDAY etc. ) to an actual Day Enum object.
    @TypeConverter
    public static Day toDay(String day) {
        // 24.04.2023 - NOTE: the valueOf() method is another one that comes with the enum package.
        // It just returns the string representation of the enum (in our case, MONDAY, TUESDAY etc.)
        // as an ENUM data type defined in the Day class.
        return day == null ? null : Day.valueOf(day);
    }
}
