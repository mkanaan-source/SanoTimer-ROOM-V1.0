package com.learningjavaandroid.sanotimer_v10.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.learningjavaandroid.sanotimer_v10.DailyScheduleBottomSheetFragment;
import com.learningjavaandroid.sanotimer_v10.R;
import com.learningjavaandroid.sanotimer_v10.ScheduleProgrammer;
import com.learningjavaandroid.sanotimer_v10.util.IrrigationRoomDatabase;

// 26.04.2023 - we deleted a bunch of stuff since we changed the layout of the
// activity_weekly_schedule.xml file. The backend code now reflects these changes.

public class WeeklySchedule extends AppCompatActivity {

    // 28.04.2023 - back-end instance variable for the radio group in the XML layout.
    private RadioGroup daysRadioGroup;

    private int selectedRadioButtonId;
    private RadioButton selectedRadioButton;
    private Day selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);


        // 28.04.2023 - first match the instance variable with the GUI widget user interacts with.
        daysRadioGroup = findViewById(R.id.days_radioGroup);

        // 28.04.2023 - this is the backend code that gets executed when the user clicks on any
        // of the day radio buttons in the RadioGroup.
        daysRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            selectedRadioButtonId = checkedId;
            selectedRadioButton = findViewById(selectedRadioButtonId);
            if (selectedRadioButton.getId() == R.id.monday_radioButton) {
                selectedDay = Day.MONDAY;
            } else if (selectedRadioButton.getId() == R.id.tuesday_radioButton) {
                selectedDay = Day.TUESDAY;
            } else if (selectedRadioButton.getId() == R.id.wednesday_radioButton) {
                selectedDay = Day.WEDNESDAY;
            } else if (selectedRadioButton.getId() == R.id.thursday_radioButton) {
                selectedDay = Day.THURSDAY;
            } else if (selectedRadioButton.getId() == R.id.friday_radioButton) {
                selectedDay = Day.FRIDAY;
            } else if (selectedRadioButton.getId() == R.id.saturday_radioButton) {
                selectedDay = Day.SATURDAY;
            } else if (selectedRadioButton.getId() == R.id.sunday_radioButton) {
                selectedDay = Day.SUNDAY;
            } else {
                selectedDay = Day.MONDAY;
            }
            Log.d("DAYS_RADIO_GROUP", "onCreate -> Button Clicked: " + selectedDay.toString());
            // Ok...let's switch to the ScheduleProgramer activity.
            Intent switchActivityIntent = new Intent(WeeklySchedule.this,
                    ScheduleProgrammer.class);
            switchActivityIntent.putExtra("daySelected", selectedDay);
            startActivity(switchActivityIntent);


        });



    }




}