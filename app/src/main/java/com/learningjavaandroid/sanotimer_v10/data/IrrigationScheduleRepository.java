package com.learningjavaandroid.sanotimer_v10.data;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.Day;
import com.learningjavaandroid.sanotimer_v10.util.IrrigationRoomDatabase;

import java.util.List;

// 09.02.2023 - Repository class so the UI has access to a "single source of truth" for all
// back-end app data (which comes from the Room database by way of DAO).
public class IrrigationScheduleRepository {
    private IrrigationScheduleDao irrigationScheduleDao;
    private LiveData<List<DailySchedule>> fullSchedule;

    // 07.06.2023 - new interface to run tasks in a background thread and return result to
    // main thread.
    public interface Callback {
        // 07.06.2023 - method that will be called by the Callback on return to the main thread
        // when the checkForDuplicateRecords() method is executed on the background thread.
        void onDuplicateRecordsFound(int countDuplicates);


    }

    // 12.06.2023 - a new interface to handle the results of the getFullSchedule() method.
    public interface Callback_fullSchedule {
        void onGetFullSchedule(LiveData<List<DailySchedule>> fullSchedule);
    }

    // 09.02.2023 - constructor
    public IrrigationScheduleRepository(Application application) {
        IrrigationRoomDatabase irrDb = IrrigationRoomDatabase.getDatabase(application);
        irrigationScheduleDao = irrDb.irrigationScheduleDao();

//        fullSchedule = irrigationScheduleDao.getFullSchedule();


    }

    //public LiveData<List<DailySchedule>> getFullSchedule() { return fullSchedule; }

    // 08.06.2023 - here is an improved implementation of getFullSchedule() that runs in a
    // background thread and returns the result to the main thread.
    public void getFullSchedule(Callback_fullSchedule callback) {
        IrrigationRoomDatabase.irrDbOpExecutor.execute(new Runnable() {
            @Override
            public void run() {
                 fullSchedule = irrigationScheduleDao.getFullSchedule();

                // 07.06.2023 - return result to main thread using the Callback.
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onGetFullSchedule(fullSchedule);
                    }
                });

            }
        });
    }

    public void insert(DailySchedule dailySchedule) {
        IrrigationRoomDatabase.irrDbOpExecutor.execute( () -> {
            irrigationScheduleDao.insert(dailySchedule);
        });
    }

    // 02.05.2023 - the method we need to pull schedule data for a specific day
    public LiveData<List<DailySchedule>> getDailyScheduleRecords(Day day) {
        return irrigationScheduleDao.getDailyScheduleRecords(day);
    }

    // 28.05.2023 - the method to check for duplicate records with the SAME DAY AND START TIME
    public void checkForDuplicateRecords(Day day, String startTime, Callback callback) {
//        return irrigationScheduleDao.checkForDuplicateRecords(day, startTime);
       // 07.06.2023 - run the check for duplicate records in a background thread.
        IrrigationRoomDatabase.irrDbOpExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int duplicateCount = irrigationScheduleDao.checkForDuplicateRecords(day, startTime);

                // 07.06.2023 - return result to main thread using the Callback.
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onDuplicateRecordsFound(duplicateCount);
                    }
                });

            }
        });
    }

}
