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

    @ColumnInfo(name = "startTime")
    private int startTime;

    @ColumnInfo(name = "stopTime")
    private int stopTime;

    // 01.02.2023 - constructors
    public DailySchedule() {
    }

    public DailySchedule(@NonNull String controllerId, int valveId,
                         @NonNull Boolean willWork, int day, int startTime, int stopTime) {
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }
}

