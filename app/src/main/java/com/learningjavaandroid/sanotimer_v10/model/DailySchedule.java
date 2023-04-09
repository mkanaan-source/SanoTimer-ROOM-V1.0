package com.learningjavaandroid.sanotimer_v10.model;

// 01.02.2023 - a model class that will model a SINGLE piece of irrigation schedule record,
// i.e. a SINGLE row of the table in the database.

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "irrigation_schedule_table")
public class DailySchedule {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "controllerId")
    private String controllerId;

    @ColumnInfo(name = "valveId")
    private int valveId;

    @ColumnInfo(name = "willWork")
    private Boolean willWork;

    // TODO - 12.02.2023 - might be a better idea to specify days as ENUM data type.
    @ColumnInfo(name = "Day")
    private int day;

    // 04.04.2023 - this is now a string of the form "HH:MM".
    @ColumnInfo(name = "startTime")
    private String startTime;

    // 04.04.2023 - this is now a string of the form "HH:MM".
    @ColumnInfo(name = "stopTime")
    private String stopTime;

    // 01.02.2023 - constructors
    public DailySchedule() {
    }

    public DailySchedule(@NonNull String controllerId, int valveId,
                         @NonNull Boolean willWork, int day, String startTime, String stopTime) {
        this.controllerId = controllerId;
        this.valveId = valveId;
        this.willWork = willWork;
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

    public Boolean getWillWork() {
        return willWork;
    }

    public void setWillWork(@NonNull Boolean willWork) {
        this.willWork = willWork;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
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
}

