package com.learningjavaandroid.sanotimer_v10.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.learningjavaandroid.sanotimer_v10.MainActivity;
import com.learningjavaandroid.sanotimer_v10.data.IrrigationScheduleRepository;
import com.learningjavaandroid.sanotimer_v10.util.SanoTimerLifecycleOwner;
import com.learningjavaandroid.sanotimer_v10.util.Utils;

import java.util.List;
import java.util.Objects;

public class IrrigationViewModel extends AndroidViewModel {

    public static IrrigationScheduleRepository irrigationRepository;
    //public final LiveData<List<DailySchedule>> fullSchedule;

    // 14.04.2023 - code to make IrrigationViewModel a SINGLETON
    private static volatile IrrigationViewModel INSTANCE;

    // 12.04.2023 - declare the MutableLiveData holders for the data that the UI widgets
    // (in this case the EditText's on the ScheduleProgrammer activity) need access to.
    private MutableLiveData<String> startTime = new MutableLiveData<String>();
    private MutableLiveData<String> stopTime = new MutableLiveData<String>();

    // 12.04.2023 - Boolean to answer a basic question: are we editing the start time or the stop time?
    private Boolean startTimeSelected;

    // 11.04.2023 - these are the items that will be coming in from SanoTimerTimePickerDialogFragment.
//    private int hour;
//    private int minute;
    // 12.04.2023 - ....and here are the get() and the set() methods for the MutableLiveData objects above.
    public void setStartTime(String start) {
        startTime.setValue(start);
    }

    public void setStopTime(String stop) {
        stopTime.setValue(stop);
    }

    public LiveData<String> getStartTime() {
        return startTime;
    }

    public LiveData<String> getStopTime() {
        return stopTime;
    }

    // 12.04.2023 - here are the get() and set() methods for 'startTimeSelected' Boolean variable.
    public Boolean getStartTimeSelected() {
        return startTimeSelected;
    }

    public void setStartTimeSelected(Boolean flag) {
        startTimeSelected = flag;
    }

    // 14.04.2023 - New constructor code to implement IrrigationViewModel as a singleton
    public static IrrigationViewModel getViewModel(@NonNull Application application) {

        if (INSTANCE == null) {
            synchronized (IrrigationViewModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new IrrigationViewModel(application);
                }
            }
        }
        return INSTANCE;
    }

    // 12.04.2023 - and here is our beautiful ViewModel constructor :)
    public IrrigationViewModel(@NonNull Application application) {
        super(application);
        irrigationRepository = new IrrigationScheduleRepository(application);
        //fullSchedule = irrigationRepository.getFullSchedule();

    }

    //public LiveData<List<DailySchedule>> getFullSchedule() { return fullSchedule; }

    public static void insert(DailySchedule dailySchedule) {
        irrigationRepository.insert(dailySchedule);
    }

    // 02.05.2023 - the method we need to pull schedule data for a specific day
    public LiveData<List<DailySchedule>> getDailyScheduleRecords(Day day) {
        return irrigationRepository.getDailyScheduleRecords(day);
    }

    // 28.05.2023 - the method to check for duplicate records with the SAME DAY AND START TIME
    public void checkForDuplicateRecords(Day day, String startTime) {

        // 07.06.2023 - the reason why we have the Callback object here as a parameter is
        // because we want to run this task in a background thread but return the result to the
        // main thread.
        irrigationRepository.checkForDuplicateRecords(day, startTime,
                new IrrigationScheduleRepository.Callback() {
                    @Override
                    public void onDuplicateRecordsFound(int countDuplicates) {
                        // TODO: 07.06.2023 - for now we will add just a log.d here for testing.
                        // TODO: But normally, this is where we would update UI components etc.
                        Log.d("DUP_REC", "onDuplicateRecordsFound -> # of duplicates: "
                                + countDuplicates);
                    }
                });
    }

    // 19.06.2023 - trying to fix the crash issue due to the new implementation of getFullSchedule
    // that is designed to run in a background thread and return results to the main thread.
    // To solve this, we need to come up with our own LifecycleOwner object.
    SanoTimerLifecycleOwner lifecycleOwner = new SanoTimerLifecycleOwner();

    // 12.06.2023 - the method that will get the full schedule from the database
    public void getFullSchedule() {
        irrigationRepository.getFullSchedule(new IrrigationScheduleRepository.Callback_fullSchedule() {

            // 12.06.2023 - this is the concrete implementation of the method that gets executed
            // after the getFullSchedule() method gets executed in a background thread. FOR NOW,
            // the implementation just includes log.d messages for testing.
            @Override
            public void onGetFullSchedule(LiveData<List<DailySchedule>> fullSchedule) {
                // 13.06.2023 - set up an observer for the LiveData object first.
                // 19.06.2023 - now pass the 'lifecycleOwner' object to the 'observe' method
                // to fix the crash issue.
                fullSchedule.observe(lifecycleOwner, dailySchedules -> {
                    List<DailySchedule> dailyScheduleList = fullSchedule.getValue();
                    if (dailyScheduleList != null) {
                        for (DailySchedule dailySchedule : dailyScheduleList) {
                            Log.d("FULL_SCHEDULE", "onGetFullSchedule: "
                                    + dailySchedule.getDay() + " " + dailySchedule.getStartTime()
                                    + " " + dailySchedule.getStopTime());
                        }
                    } else {
                        Log.d("FULL_SCHEDULE", "onGetFullSchedule: dailySchedule is NULL! :(");
                    }

                });
            }
        });
    }

    // 04.07.2023 - the ViewModel-level implementation of the getRecord() method that will get
    // a specific record from the database.
    public void getRecord(long id) {
        irrigationRepository.getRecord(id, new IrrigationScheduleRepository.Callback_getRecord() {
            @Override
            public void onGetRecord(LiveData<DailySchedule> scheduleRecord) {
                scheduleRecord.observe(lifecycleOwner, dailySchedule -> {
                    // 05.07.2023 - note: this is the code that gets executed when a SINGLE
                    // record is pulled from the database. FOR NOW, we just put in a log.d for testing.
                    Log.d("SCHEDULE_RECORD", "onGetRecord: "
                            + Objects.requireNonNull(scheduleRecord.getValue()).toString());
                });
            }
        });
    }

    // 26.07.2023 - the ViewModel-level implementation of the delete() method to delete a specific
    // record from the database.
    // 02.08.2023 - change the definition of this method to explicitly pass in a DailySchedule object.
    public void delete(DailySchedule dailySchedule) { irrigationRepository.delete(dailySchedule); }



}
