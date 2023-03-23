package com.learningjavaandroid.sanotimer_v10.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;

import java.util.List;

@Dao
public interface IrrigationScheduleDao {

    // 01.02.2023 - methods for CRUD operations

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DailySchedule dailySchedule);

    @Query("DELETE FROM irrigation_schedule_table")
    void deleteAll();

    @Query("SELECT * FROM irrigation_schedule_table")
    LiveData< List<DailySchedule> > getFullSchedule();

/*
TODO 01.02.2023 - the next few methods are the rest of the CRUD methods we need to implement.
 */
 
/*
    DailySchedule retrieve(int id);

    void update(DailySchedule dailySchedule);

    void delete(DailySchedule dailySchedule);
*/


}
