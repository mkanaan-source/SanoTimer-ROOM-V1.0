package com.learningjavaandroid.sanotimer_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.IrrigationViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IrrigationViewModel irrigationViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        irrigationViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication()).create(IrrigationViewModel.class);

//        irrigationViewModel.getFullSchedule().observe(MainActivity.this, dailySchedules -> {
//                // TODO: 10.02.2023 - this is the code where we can add the code to update
//                // TODO: the UI when the data coming from the database changes.
//                // 11.02.2023 - for now, just add a few lines to log the data.
//            StringBuilder builder = new StringBuilder();
//            for (DailySchedule dailySchedule : dailySchedules) {
//
//                builder.append(" - ").append(dailySchedule.getControllerId())
//                        .append(" ").append(dailySchedule.getValveId());
//
//                Log.d("ST_TEST", "onCreate: " + dailySchedule.getControllerId());
//                Log.d("ST_TEST", "onCreate: " + dailySchedule.getValveId());
//
//            }
//            textView.setText(builder.toString());
//
//        });
    }
}