package com.learningjavaandroid.sanotimer_v10.adapter;

import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;

// 14.05.2023 - this is the interface class that will handle the click events for all the items
// displayed on the RecyclerView.

public interface ScheduleItemClickListener {

    // 14.05.2023 - and here is the method that will be called when the user clicks on any of the
    // rows displayed on the RecyclerView. Note - this method does not have a concrete implementation
    // since it is part of an INTERFACE class; the concrete implementation will happen at the
    // concrete class (i.e. ACTIVITY) level.
    void onScheduleItemClick(DailySchedule dailySchedule);
}
