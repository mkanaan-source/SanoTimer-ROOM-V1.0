package com.learningjavaandroid.sanotimer_v10.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.util.IrrigationRoomDatabase;

import java.util.List;

// 09.02.2023 - Repository class so the UI has access to a "single source of truth" for all
// back-end app data (which comes from the Room database by way of DAO).
public class IrrigationScheduleRepository {
    private IrrigationScheduleDao irrigationScheduleDao;
    private LiveData<List<DailySchedule>> fullSchedule;

    // 09.02.2023 - constructor
    public IrrigationScheduleRepository(Application application) {
        IrrigationRoomDatabase irrDb = IrrigationRoomDatabase.getDatabase(application);
        irrigationScheduleDao = irrDb.irrigationScheduleDao();

        fullSchedule = irrigationScheduleDao.getFullSchedule();
    }

    public LiveData<List<DailySchedule>> getFullSchedule() { return fullSchedule; }

    public void insert(DailySchedule dailySchedule) {
        IrrigationRoomDatabase.irrDbWriteExecutor.execute( () -> {
            irrigationScheduleDao.insert(dailySchedule);
        });
    }

}
