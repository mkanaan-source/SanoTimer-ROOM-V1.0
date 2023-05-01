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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.Day;
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

    private static final String TEST_CTRLR = "test_controller";
    private static final int VALVE = 3;
    private static final Day day = Day.MONDAY;

    // 28.04.2023 - title textview.
    private TextView sch_programmer_textview;


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

        // 28.04.2023 - match GUI title textview widget with the appropriate instance variable
        sch_programmer_textview = findViewById(R.id.sch_program_textview);

        // 28.04.2023 - get the Intent object coming in from WeeklySchedule activity and
        // use it to set the title textview on this activity.
        Bundle extraData = getIntent().getExtras();
        if (extraData != null) {
            Day day = (Day) extraData.get("daySelected");
            String textData = "View or edit schedule data for " + day.toString();
            sch_programmer_textview.setText(textData);
        }


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

        // 12.04.2023 - set up the observe() methods here for the LiveData objects
        // pertaining to the start and stop times defined in the ViewModel.

        irrigationViewModel.getStartTime().observe(this, startTime -> {
            startTimeEditText.setText(startTime);
        });

        irrigationViewModel.getStopTime().observe(this, stopTime -> {
            stopTimeEditText.setText(stopTime);
        });

        // 18.04.2023 - OK.....now write the data to the database.
        addScheduleItemButton.setOnClickListener(v -> {

            String timeStart = startTimeEditText.getText().toString().trim();
            String timeStop = stopTimeEditText.getText().toString().trim();

            // TODO: 24.04.2023 - we come up with a DailySchedule object with some amount of
            // TODO: hardcoded data so we could test the data being written into the database.
            // TODO: Normally, all fields of the DailySchedule should be set based on info provided by user.

            DailySchedule dailySchedule = new DailySchedule(TEST_CTRLR, VALVE, day, timeStart, timeStop);
            IrrigationViewModel.insert(dailySchedule);

        });




    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new SanoTimerTimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}