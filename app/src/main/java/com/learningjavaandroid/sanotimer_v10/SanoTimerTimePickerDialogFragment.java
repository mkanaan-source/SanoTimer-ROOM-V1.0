package com.learningjavaandroid.sanotimer_v10;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class SanoTimerTimePickerDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        // 30.03.2023 - I BELIEVE this is where we will add the code to save the time to the database.
        Log.d("TIME_SET", "onTimeSet: " + " hour set: " + hourOfDay
                + " minute set: " + minute);
        // 31.03.2023 - more test code....to format hour and day as string in the form "HH:MM".
        String setTime = hourOfDay + ":" + minute;
        // 31.03.2023 - ok....now let's see if we can unpack the string to get hour and minute
        // 01.04.2023 - first set the string splitter object with : as the delimiter
        // TODO - 01.04.2023 - move this code to a simple utils method.
        TextUtils.SimpleStringSplitter stringSplitter =
                new TextUtils.SimpleStringSplitter(':');
        // 01.04.2023 - set the text to split.
        stringSplitter.setString(setTime);

        // 01.04.2023 - now split!
        // TODO 01.04.2023 - move this code to a simple utils method.
        int testHour = Integer.parseInt(stringSplitter.next());
        int testMinute = Integer.parseInt(stringSplitter.next());
        Log.d("TIME_SET_2", "onTimeSet: " + " testHour: " + testHour
                + " testMinute: " + testMinute);
    }
}
