package com.learningjavaandroid.sanotimer_v10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DailyScheduleBottomSheetFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {

    private TextView bottomSheetTextView; // 28.03.2023 - this is just the placeholder textView we put on the fragment.

    // 27.03.2023 - need to have this empty constructor in place
    public DailyScheduleBottomSheetFragment() {
    }

    // 28.03.2023 - what happens when this view is created? The method below determines that.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.daily_schedule_bottom_sheet, container, false);

        // 28.03.2023 - initialize the textView instance variable on this view (fragment, actually).
        bottomSheetTextView = view.findViewById(R.id.bottom_sheet_textview);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
