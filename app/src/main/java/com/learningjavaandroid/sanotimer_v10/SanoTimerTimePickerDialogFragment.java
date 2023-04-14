package com.learningjavaandroid.sanotimer_v10;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.IrrigationViewModel;
import com.learningjavaandroid.sanotimer_v10.util.Utils;

import java.util.Calendar;

public class SanoTimerTimePickerDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    // 11.04.2023 - this fragment also needs access to the ViewModel class (IrrigationViewModel)
    private IrrigationViewModel viewModel;


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

        // 11.04.2023 - instantiate IrrigationViewModel (or retrieve instance if it already exists).
//        viewModel = new ViewModelProvider.AndroidViewModelFactory(
//                requireActivity().getApplication()).create(IrrigationViewModel.class);

        // 14.04.2023 - IrrigationViewModel is now a singleton - get the instance.
        viewModel = IrrigationViewModel.getViewModel(requireActivity().getApplication());

        // 13.04.2023 - code for testing only
//        if (viewModel == null) {
//            Log.d("VM_NULL", "onCreateDialog: can't instantiate viewmodel");
//        }

        // 12.04.2023 - first convert the time set by user to String in the form "HH:MM"
        String timeSet = Utils.timeFromIntToString(hourOfDay, minute);

        // 12.04.2023 - check if it is the start or the stop time we are setting and set the
        // appropriate instance variable inside the ViewModel object.
        if(viewModel.getStartTimeSelected()) {
            viewModel.setStartTime(timeSet);
        } else if(!viewModel.getStartTimeSelected()) {
            viewModel.setStopTime(timeSet);
        }
        // 13.04.2023 - another piece of test code
        Log.d("VIEW_MODEL",
                "onTimeSet: startTimeSelected value = " + viewModel.getStartTimeSelected());
    }
}
