package com.learningjavaandroid.sanotimer_v10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningjavaandroid.sanotimer_v10.model.IrrigationViewModel;

public class ScheduleProgrammer extends AppCompatActivity {

    // 07.04.2023 - these are the declarations for all the GUI elements.
    private EditText startTimeEditText;
    private EditText stopTimeEditText;
    private FloatingActionButton addStartTimeFab;
    private FloatingActionButton addStopTimeFab;
    private Button addScheduleItemButton;

    // 11.04.2023 - instantiate the ViewModel class so we can share data with the associated
    // fragment (SanoTimerTimePickerDialogFragment).
    private IrrigationViewModel irrigationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_programmer);

        // 07.04.2023 - tie the instance variables to the Views in the layout.
        startTimeEditText = findViewById(R.id.sch_prog_start_time_field);
        stopTimeEditText = findViewById(R.id.sch_prog_stop_time_field);
        addStartTimeFab = findViewById(R.id.sch_prog_add_start_time_fab);
        addStopTimeFab = findViewById(R.id.sch_prog_add_stop_time_fab);
        addScheduleItemButton = findViewById(R.id.sch_prog_add_button);

        // 11.04.2023 - instantiate the ViewModel class (or retrieve it if it already exists).
        // Note: this ViewModel has APPLICATION-level scope.
//        irrigationViewModel = new ViewModelProvider.AndroidViewModelFactory(
//                ScheduleProgrammer.this.getApplication()).create(IrrigationViewModel.class);


        // 14.04.2023 - now that we have made IrrigationViewModel a singleton get the instance.
        irrigationViewModel = IrrigationViewModel.getViewModel(getApplication());

        // 09.04.2023 - the onClickListener() methods for the start and stop time setting.

        addStartTimeFab.setOnClickListener(viewStartTimeFab -> {
            // 12.04.2023 - we are editing the startTime so set 'startTimeSelected' to TRUE
            irrigationViewModel.setStartTimeSelected(true);
            Log.d("SCHEDULE_PROGRAMMER",
                    "onCreate: startTimeSelected =  " + irrigationViewModel.getStartTimeSelected());

            showTimePickerDialog(viewStartTimeFab);
        });

        addStopTimeFab.setOnClickListener(viewStopTimeFab -> {
            // 12.04.2023 - we are editing the startTime so set 'startTimeSelected' to FALSE
            irrigationViewModel.setStartTimeSelected(false);

            showTimePickerDialog(viewStopTimeFab);
        });

        // TODO: 14.04.2023 - add the onClickListener() here to write schedule data
        //  into the database.

        // 12.04.2023 - set up the observe() methods here for the LiveData objects
        // pertaining to the start and stop times defined in the ViewModel.

        irrigationViewModel.getStartTime().observe(this, startTime -> {
            startTimeEditText.setText(startTime);
        });

        irrigationViewModel.getStopTime().observe(this, stopTime -> {
            stopTimeEditText.setText(stopTime);
        });




    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new SanoTimerTimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}