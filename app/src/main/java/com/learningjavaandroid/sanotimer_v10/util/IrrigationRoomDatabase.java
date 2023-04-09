package com.learningjavaandroid.sanotimer_v10.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.learningjavaandroid.sanotimer_v10.data.IrrigationScheduleDao;
import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 07.02.2023 - this is the class that will model a SINGLE instance of ROOM db for this app.
@Database(entities={DailySchedule.class}, version = 1, exportSchema = false)
public abstract class IrrigationRoomDatabase extends RoomDatabase {

    public abstract IrrigationScheduleDao irrigationScheduleDao();
    public static final int NUMBER_OF_THREADS = 4;

    // 07.02.2023 - this is the part of the code that will make this db object a SINGLETON
    private static volatile IrrigationRoomDatabase INSTANCE;

    public static final ExecutorService irrDbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // 09.02.2023 - the method that will return the single instance of
    // IrrigationRoomDatabase (or create it if it does not exist).
    public static IrrigationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IrrigationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IrrigationRoomDatabase.class, "irrigation_schedule_database")
                            .addCallback(irrRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // 09.02.2023 - method to execute database write operations outside of the main thread.
    public static final RoomDatabase.Callback irrRoomDatabaseCallBack =
            new RoomDatabase.Callback() {

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    irrDbWriteExecutor.execute(()->{
                        IrrigationScheduleDao irrigationScheduleDao
                                = INSTANCE.irrigationScheduleDao();
                        irrigationScheduleDao.deleteAll();

                        // 09.02.2023 - at this point, you can add some test DailySchedule items
                        // the first time the database is created, just so you have something you
                        // can work with......just in case you wish to do so.
                        // 11.02.2023 - well....here it is. You can delete all this later.
//                        DailySchedule dailySchedule = new DailySchedule("ARKA BAHCE",
//                                1, true, 1, 0600, 0630);
//                        irrigationScheduleDao.insert(dailySchedule);
//
//                        dailySchedule = new DailySchedule("ARKA BAHCE",
//                                1, true, 1, 1700, 1730);
//                        irrigationScheduleDao.insert(dailySchedule);
//
//                        dailySchedule = new DailySchedule("ARKA BAHCE",
//                                1, false, 2, 9999, 9999);
//                        irrigationScheduleDao.insert(dailySchedule);
//
//                        dailySchedule = new DailySchedule("ARKA BAHCE",
//                                2, true, 1, 0700, 0730);
//                        irrigationScheduleDao.insert(dailySchedule);
//
//                        dailySchedule = new DailySchedule("ARKA BAHCE",
//                                2, true, 1, 1800, 1830);
//                        irrigationScheduleDao.insert(dailySchedule);
//
//                        dailySchedule = new DailySchedule("ARKA BAHCE",
//                                2, false, 2, 9999, 9999);
//                        irrigationScheduleDao.insert(dailySchedule);

                    });
                }
            };



}
