package com.learningjavaandroid.sanotimer_v10.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learningjavaandroid.sanotimer_v10.MainActivity;
import com.learningjavaandroid.sanotimer_v10.data.IrrigationScheduleRepository;
import com.learningjavaandroid.sanotimer_v10.util.Utils;

import java.util.List;

public class IrrigationViewModel extends AndroidViewModel {

    public static IrrigationScheduleRepository irrigationRepository;
    public final LiveData<List<DailySchedule>> fullSchedule;

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
        fullSchedule = irrigationRepository.getFullSchedule();

    }

    public LiveData<List<DailySchedule>> getFullSchedule() { return fullSchedule; }

    public static void insert(DailySchedule dailySchedule) { irrigationRepository.insert(dailySchedule);}

//    public String formatTime(int hour, int minute) {
//        return Utils.timeFromIntToString(hour, minute);
//    }

}
