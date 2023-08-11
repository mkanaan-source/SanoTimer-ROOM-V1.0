package com.learningjavaandroid.sanotimer_v10.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;
import com.learningjavaandroid.sanotimer_v10.model.Day;

import java.util.List;

@Dao
public interface IrrigationScheduleDao {

    // 01.02.2023 - methods for CRUD operations

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DailySchedule dailySchedule);

    @Query("DELETE FROM irrigation_schedule_table")
    void deleteAll();

    // 26.07.2023 - new method to delete a specific record from the database.
    @Query("DELETE from irrigation_schedule_table where irrigation_schedule_table.id ==:id")
    void delete(long id);

    @Query("SELECT * FROM irrigation_schedule_table")
    LiveData< List<DailySchedule> > getFullSchedule();



    // 04.07.2023 - here is the method to retrieve a specific record from the database.
    @Query("SELECT * FROM irrigation_schedule_table WHERE irrigation_schedule_table.id ==:id")
    LiveData<DailySchedule> getRecord(long id);

    // 02.05.2023 - this is the method we need to pull data from the database for a specific day
    @Query("SELECT * FROM irrigation_schedule_table WHERE irrigation_schedule_table.day = :irrDay")
    LiveData<List<DailySchedule>> getDailyScheduleRecords(Day irrDay);

    // 28.05.2023 - the method to check for duplicate records with the SAME DAY AND START TIME
    @Query("SELECT COUNT(*) FROM irrigation_schedule_table WHERE " +
            "irrigation_schedule_table.day = :irrDay AND irrigation_schedule_table.startTime = :sTime")
    int checkForDuplicateRecords(Day irrDay, String sTime);
/*
    DailySchedule retrieve(int id);

    void update(DailySchedule dailySchedule);


*/


}
