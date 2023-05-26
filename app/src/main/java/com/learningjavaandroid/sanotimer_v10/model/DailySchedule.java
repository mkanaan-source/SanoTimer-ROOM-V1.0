package com.learningjavaandroid.sanotimer_v10.model;

// 01.02.2023 - a model class that will model a SINGLE piece of irrigation schedule record,
// i.e. a SINGLE row of the table in the database.

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "irrigation_schedule_table")
public class DailySchedule {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String controllerId;

    private int valveId;

    // 24.04.2023 - now we specify days as ENUM data type.
    private Day day;

    // 04.04.2023 - this is now a string of the form "HH:MM".
    private String startTime;

    // 04.04.2023 - this is now a string of the form "HH:MM".
    private String stopTime;

    // 01.02.2023 - constructors
    @Ignore
    public DailySchedule() {
    }

    public DailySchedule(@NonNull String controllerId, int valveId,
                         Day day, String startTime, String stopTime) {
        this.controllerId = controllerId;
        this.valveId = valveId;
        this.day  = day;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    // 01.02.2023 - getter and setter methods.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(@NonNull String controllerId) {
        this.controllerId = controllerId;
    }

    public int getValveId() {
        return valveId;
    }

    public void setValveId(int valveId) {
        this.valveId = valveId;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    // 02.05.2023 - this is an override method so we can see a text dump of the contents of a
    // DailySchedule object on the console.....just for testing purposes.
    @Override
    public String toString() {
        return "DailySchedule{" +
                "id=" + id +
                ", controllerId='" + controllerId + '\'' +
                ", valveId=" + valveId +
                ", day=" + day +
                ", startTime='" + startTime + '\'' +
                ", stopTime='" + stopTime + '\'' +
                '}';
    }
}

