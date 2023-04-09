package com.learningjavaandroid.sanotimer_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScheduleProgrammer extends AppCompatActivity {

    private EditText startTimeEditText;
    private EditText stopTimeEditText;
    private FloatingActionButton addStartTimeFab;
    private FloatingActionButton addStopTimeFab;
    private Button addScheduleItemButton;


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

        // 09.04.2023 - the onClickListener() methods for the start and stop time setting.
        addStartTimeFab.setOnClickListener(viewStartTimeFab -> {
            showTimePickerDialog(viewStartTimeFab);
        });

        addStopTimeFab.setOnClickListener(viewStopTimeFab -> {
            showTimePickerDialog(viewStopTimeFab);
        });



    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new SanoTimerTimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}