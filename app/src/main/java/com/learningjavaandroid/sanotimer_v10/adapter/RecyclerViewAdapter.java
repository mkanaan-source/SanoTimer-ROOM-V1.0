package com.learningjavaandroid.sanotimer_v10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learningjavaandroid.sanotimer_v10.R;
import com.learningjavaandroid.sanotimer_v10.model.DailySchedule;

import java.util.List;

// 14.05.2023 - this is the RecyclerView ADAPTER class which will adapt the data received from
// the ROOM database for use with the RecyclerView GUI widget on ScheduleProgrammer activity.
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // 14.05.2023 - first declare our data source which will be displayed on the RecyclerView.
    private final List<DailySchedule> dailyScheduleList;

    // 14.05.2023 - and here we associate a click listener class to the RecyclerView like so....
    private final ScheduleItemClickListener scheduleItemClickListener;

    // 14.05.2023 - and here is our constructor.
    public RecyclerViewAdapter(List<DailySchedule> dailyScheduleList,
                               ScheduleItemClickListener scheduleItemClickListener) {
        this.dailyScheduleList = dailyScheduleList;
        this.scheduleItemClickListener = scheduleItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 14.05.2023 - create a View based on the layout specified in schedule_row.xml
        // which specifies the layout for ONE ROW of the RecyclerView.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_row, parent, false);
        return new ViewHolder(view);
    }

    // 15.05.2023 - this is where we bind the Views (instance variables pertaining to each
    // GUI widget, which were declared on the ViewHolder class definition) with the data.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // 15.05.2023 - first get the DailySchedule object at the location the user is at.
        DailySchedule ds = dailyScheduleList.get(position);

        // 15.05.2023 - now set the value of the GUI widgets based on this DailySchedule object.
        holder.startTimeTextView.setText(ds.getStartTime());
        holder.stopTimeTextView.setText(ds.getStopTime());

    }

    @Override
    public int getItemCount() {
        return dailyScheduleList.size();
    }

    // 15.05.2023 - and this is the actual ViewHolder class for this RecyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView startTimeTextView;
        public TextView stopTimeTextView;

        ScheduleItemClickListener itemClickListener;

        // 15.05.2023 - the ViewHolder constructor.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 14.05.2023 - tie in the instance variables to the respective GUI widgets
            startTimeTextView = itemView.findViewById(R.id.start_time);
            stopTimeTextView = itemView.findViewById(R.id.stop_time);

            this.itemClickListener = scheduleItemClickListener;

            // 14.05.2023 - attach an OnClickListener to the View here in order to respond to
            // click events.
            itemView.setOnClickListener(this);
        }

        // 14.05.2023 - the method below will be called when the user clicks on any of the
        // DailySchedule items on the RecyclerView.
        @Override
        public void onClick(View view) {
            // 14.05.2023 - first get the DailySchedule object at the specific position the user
            // clicked on.
            DailySchedule currScheduleObject = dailyScheduleList.get(getAbsoluteAdapterPosition());

            // 14.05.2023 - find out which View the user clicked on. Remember that every View
            // has an id.
            int id = view.getId();

            // 15.05.2023 - invoke the click listener pertaining to the item that was clicked on.
            itemClickListener.onScheduleItemClick(currScheduleObject);
        }
    }
}
