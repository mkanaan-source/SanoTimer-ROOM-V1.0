package com.learningjavaandroid.sanotimer_v10.model;


import java.util.List;

// 23.03.2023 - just a dummy comment here to test out the integration of Android Studio with GitHub.

// 01.02.2023 - a class that models an irrigation control device.
// 01.02.2023 - TODO: want to implement this as a singleton.
public class Controller {
    private String controllerId; // 01.02.2023 - name of the controller.
    private int noChannels; // 01.02.2023 - number of channels, i.e. no. valves controlled by device
    private Boolean blueToothConnected; // 01.02.2023 - Bluetooth connection status.

    public static class Valve {
        private int valveId;
        private Boolean on; // is the valve on or not?

        // 01.02.2023 - constructors
        public Valve() {
        }

        public Valve(int valveId, Boolean on) {
            this.valveId = valveId;
            this.on = on;
        }

        // 01.02.2023 - getters and setters2
        public int getValveId() {
            return valveId;
        }

        public void setValveId(int valveId) {
            this.valveId = valveId;
        }

        public Boolean getOn() {
            return on;
        }

        public void setOn(Boolean on) {
            this.on = on;
        }
    }

    private List<Valve> valveList;


    // 01.02.2023 - Constructors
    public Controller() {
    }

    public Controller(String controllerId, int noChannels, Boolean blueToothConnected,
                      List<Valve> valveList) {
        this.controllerId = controllerId;
        this.noChannels = noChannels;
        this.blueToothConnected = blueToothConnected;
        this.valveList = valveList;
    }

    // 01.02.2023 - Getters and setters.
    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public int getNoChannels() {
        return noChannels;
    }

    public void setNoChannels(int noChannels) {
        this.noChannels = noChannels;
    }

    public Boolean getBlueToothConnected() {
        return blueToothConnected;
    }

    public void setBlueToothConnected(Boolean blueToothConnected) {
        this.blueToothConnected = blueToothConnected;
    }

    public List<Valve> getValveList() {
        return valveList;
    }

    public void setValveList(List<Valve> valveList) {
        this.valveList = valveList;
    }



}
