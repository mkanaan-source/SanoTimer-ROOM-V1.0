package com.learningjavaandroid.sanotimer_v10.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learningjavaandroid.sanotimer_v10.MainActivity;
import com.learningjavaandroid.sanotimer_v10.data.IrrigationScheduleRepository;

import java.util.List;

public class IrrigationViewModel extends AndroidViewModel {

    public static IrrigationScheduleRepository irrigationRepository;
    public final LiveData<List<DailySchedule>> fullSchedule;


    public IrrigationViewModel(@NonNull Application application) {
        super(application);
        irrigationRepository = new IrrigationScheduleRepository(application);
        fullSchedule = irrigationRepository.getFullSchedule();

    }

    public LiveData<List<DailySchedule>> getFullSchedule() { return fullSchedule; }

    public static void insert(DailySchedule dailySchedule) { irrigationRepository.insert(dailySchedule);}



}
