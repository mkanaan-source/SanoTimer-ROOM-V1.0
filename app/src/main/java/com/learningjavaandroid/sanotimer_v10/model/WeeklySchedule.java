package com.learningjavaandroid.sanotimer_v10.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.learningjavaandroid.sanotimer_v10.DailyScheduleBottomSheetFragment;
import com.learningjavaandroid.sanotimer_v10.R;

// 28.03.2023 - remember: for now, our ONLY objective is to tie one of the day buttons to the
// bottom sheet fragment (specfied by DailyScheduleBottomSheetFragment class).
public class WeeklySchedule extends AppCompatActivity {

    private Button weeklyScheduleMondayButton;
    private DailyScheduleBottomSheetFragment dailyScheduleBottomSheetFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        weeklyScheduleMondayButton = findViewById(R.id.weekly_sch_monday_button);

        // 28.03.2023 - instantiate DailyScheduleBottomSheetFragment and specify how it will
        // behave in user interactions.
        dailyScheduleBottomSheetFragment = new DailyScheduleBottomSheetFragment();
        ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);

        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior =
                BottomSheetBehavior.from(constraintLayout);

        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        // 28.03.2023 - set up an onclick listener for the button
        weeklyScheduleMondayButton.setOnClickListener(v -> {
            // we clicked on the button. show the bottom sheet.
            showDailyScheduleBottomSheet();
        });
    }

    // 28.03.2023 - this is the method to actually display the bottom sheet fragment.
    private void showDailyScheduleBottomSheet() {
        dailyScheduleBottomSheetFragment.show(getSupportFragmentManager(),
                dailyScheduleBottomSheetFragment.getTag());
    }





}