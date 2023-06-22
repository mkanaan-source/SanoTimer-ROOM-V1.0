package com.learningjavaandroid.sanotimer_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.learningjavaandroid.sanotimer_v10.adapter.RecyclerViewAdapter;
import com.learningjavaandroid.sanotimer_v10.adapter.ScheduleItemClickListener;
import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.Day;
import com.learningjavaandroid.sanotimer_v10.model.IrrigationViewModel;

import java.util.List;

public class ScheduleProgrammer extends AppCompatActivity implements ScheduleItemClickListener {

//    // 07.04.2023 - these are the declarations for all the GUI elements.
//    private EditText startTimeEditText;
//    private EditText stopTimeEditText;
//    private FloatingActionButton addStartTimeFab;
//    private FloatingActionButton addStopTimeFab;
//    private Button addScheduleItemButton;

    // 11.04.2023 - instantiate the ViewModel class so we can share data with the associated
    // fragment (SanoTimerTimePickerDialogFragment).
    private IrrigationViewModel irrigationViewModel;

    private static final String TEST_CTRLR = "test_controller";
    private static final int VALVE = 3;
   private Day day;

    // 28.04.2023 - title textview.
    private TextView sch_programmer_textview;

    // 02.05.2023 - object to hold the schedule data for a specific day
    private LiveData<List<DailySchedule>> specificDailySchedule;

    // 13.05.2023 - TextView to display in case there are no schedule items for a given day
    // saved in the database.
    private TextView textView2;

    // 15.05.2023 - new instance variables for the RecyclerView and its associated Adapter class.
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_programmer);


        // 14.04.2023 - now that we have made IrrigationViewModel a singleton get the instance.
        irrigationViewModel = IrrigationViewModel.getViewModel(getApplication());

        // 28.04.2023 - match GUI title textview widget with the appropriate instance variable
        sch_programmer_textview = findViewById(R.id.sch_program_textview);

        textView2 = findViewById(R.id.no_sch_textView);

        // 15.05.2023 - associate the RecyclerView instance variable with its associated GUI widget.
        recyclerView = findViewById(R.id.sch_prog_recyclerView);

        // 15.05.2023 - set RecyclerView to fixed size (for optimal performance).
        recyclerView.setHasFixedSize(true);

        // 15.05.2023 - set the layout manager that the RecyclerView will use. In this case, we
        // will use the LinearLayoutManager (because this is what we used in the GUI layout for
        // the RecyclerView GUI widget; see activity_schedule_programmer.xml).
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        // 28.04.2023 - get the Intent object coming in from WeeklySchedule activity and
        // use it to set the title textview on this activity.
        Bundle extraData = getIntent().getExtras();
        if (extraData != null) {
            day = (Day) extraData.get("daySelected");
            String textData = "View or edit schedule data for " + day.toString();
            sch_programmer_textview.setText(textData);

            // 02.05.2023 - now pull the data out of the database for that specific day
            // and display it on the screen.
            specificDailySchedule = irrigationViewModel.getDailyScheduleRecords(day);

            // 07.06.2023 - temporary code to test for checking duplicate records.
            // Note: duplicate records are defined as records with the same day and start time.
            irrigationViewModel.checkForDuplicateRecords(Day.MONDAY, "07:00");


            if (specificDailySchedule != null) {
                // 12.05.2023 - ok, set up the observer for the LiveData object.
                // let's see if this one helps us get to that daily schedule list.
                specificDailySchedule.observe(this, dailySchedules -> {
                    List<DailySchedule> dailyScheduleList = specificDailySchedule.getValue();
                    if (dailyScheduleList == null) {
                        Log.d("DS", "onCreate: dailyScheduleList is null! :(");
                    } else if (dailyScheduleList.isEmpty()) {
                        //Log.d("DS", "onCreate: no schedule records for " + day.toString());
                        textView2.setVisibility(View.VISIBLE);
                    } else {
                        // 27.05.2023 - we want the list sorted in ascending order by start time.
                        // the code below does the sorting.
                        dailyScheduleList.sort((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));



                        // 15.05.2023 - invoke the RecyclerViewAdapter and associate the
                        // RecyclerView with the RecyclerViewAdapter.
                        recyclerViewAdapter = new RecyclerViewAdapter(dailyScheduleList,
                                this);
                        recyclerView.setAdapter(recyclerViewAdapter);

                        // 25.05.2023 - test code to see how big the list is:
                        Log.d("RV_PROB", "onCreate -> size of list: " +
                                recyclerViewAdapter.getItemCount());

                            for (DailySchedule dailySchedule : dailyScheduleList) {
                                Log.d("DS", "onCreate: " + dailySchedule.toString());
                            }
                    }
                });
            } else {
                Log.d("DS", "onCreate: specificDailySchedule is NULL :(");
            }
        }

        // 12.06.2023 - temp code to test the new implementation of the getFullSchedule() method
        // that runs in the background thread.
        irrigationViewModel.getFullSchedule();


        // 11.04.2023 - instantiate the ViewModel class (or retrieve it if it already exists).
        // Note: this ViewModel has APPLICATION-level scope.
//        irrigationViewModel = new ViewModelProvider.AndroidViewModelFactory(
//                ScheduleProgrammer.this.getApplication()).create(IrrigationViewModel.class);




        // 09.04.2023 - the onClickListener() methods for the start and stop time setting.

//        addStartTimeFab.setOnClickListener(viewStartTimeFab -> {
//            // 12.04.2023 - we are editing the startTime so set 'startTimeSelected' to TRUE
//            irrigationViewModel.setStartTimeSelected(true);
//            Log.d("SCHEDULE_PROGRAMMER",
//                    "onCreate: startTimeSelected =  " + irrigationViewModel.getStartTimeSelected());
//
//            showTimePickerDialog(viewStartTimeFab);
//        });

//        addStopTimeFab.setOnClickListener(viewStopTimeFab -> {
//            // 12.04.2023 - we are editing the startTime so set 'startTimeSelected' to FALSE
//            irrigationViewModel.setStartTimeSelected(false);
//
//            showTimePickerDialog(viewStopTimeFab);
//        });

        // 12.04.2023 - set up the observe() methods here for the LiveData objects
        // pertaining to the start and stop times defined in the ViewModel.

//        irrigationViewModel.getStartTime().observe(this, startTime -> {
//            startTimeEditText.setText(startTime);
//        });
//
//        irrigationViewModel.getStopTime().observe(this, stopTime -> {
//            stopTimeEditText.setText(stopTime);
//        });

        // 18.04.2023 - OK.....now write the data to the database.
//        addScheduleItemButton.setOnClickListener(v -> {
//
//            String timeStart = startTimeEditText.getText().toString().trim();
//            String timeStop = stopTimeEditText.getText().toString().trim();
//
//            // TODO: 24.04.2023 - we come up with a DailySchedule object with some amount of
//            // TODO: hardcoded data so we could test the data being written into the database.
//            // TODO: Normally, all fields of the DailySchedule should be set based on info provided by user.
//
//            DailySchedule dailySchedule = new DailySchedule(TEST_CTRLR, VALVE, day, timeStart, timeStop);
//            IrrigationViewModel.insert(dailySchedule);
//
//        });




    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new SanoTimerTimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // 15.05.2023 - this is the concrete implementation of the code that gets executed when
    // the user clicks on any of the rows in the RecyclerView.
    @Override
    public void onScheduleItemClick(DailySchedule dailySchedule) {
        // TODO: 15.05.2023 - normally, this is the point where we will switch to a new Activity
        // TODO: (that we haven't designed yet) which will allow us to either update the start and
        // TODO: stop times pertaining to that piece of schedule data (or even delete it altogether).
        // TODO: But for now, we will simply print the contents of the DailySchedule object as a
        // TODO: log.d() output.
        Log.d("RVIEW_TEST", "onScheduleItemClick -> start time: "
                + dailySchedule.getStartTime() + " stop time: " + dailySchedule.getStopTime());

    }
}