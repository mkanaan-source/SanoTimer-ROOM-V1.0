package com.learningjavaandroid.sanotimer_v10;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.IrrigationViewModel;
import com.learningjavaandroid.sanotimer_v10.util.Utils;

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
//        Log.d("TIME_SET", "onTimeSet: " + " hour set: " + hourOfDay
//                + " minute set: " + minute);
        // 03.04.2023 - test the Utils.timeFromIntToString() method to convert hour and minute
        // into a string of the form "HH:MM".
//        String setTime = Utils.timeFromIntToString(hourOfDay, minute);
//        Log.d("TIME_SET_3", "onTimeSet: " + setTime);

       // 03.04.2023 - now let's test the Utils.timeFromStringToInt() method.
//        int[] hhMM = Utils.timeFromStringToInt(setTime);
//        Log.d("TIME_SET_4", "onTimeSet: hours: " + hhMM[0] + " minutes: " + hhMM[1]);

        // 04.04.2023 - now let's see if we can write something into the database for testing.
//        int stopMinute = minute + 30; // 04.04.2023 - set stop time = start time + 30 mins just for testing.
//        int stopHour = hourOfDay;
//        String startTime = Utils.timeFromIntToString(hourOfDay, minute);
//        String stopTime = Utils.timeFromIntToString(stopHour, stopMinute);

//        DailySchedule dailySchedule = new DailySchedule("ON BAHCE", 3,
//                true, 1, startTime, stopTime);
//        irrigationViewModel.insert(dailySchedule);
    }
}
