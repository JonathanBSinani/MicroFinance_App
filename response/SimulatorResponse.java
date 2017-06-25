package com.a11group.microfinanceapp.io.response;

import com.a11group.microfinanceapp.models.Simulator;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Jonathan on 25/06/2017.
 */

public class SimulatorResponse {
    @SerializedName("")
    private ArrayList<Simulator> simulators;

    public ArrayList<Simulator> getSimulators() {
        return simulators;
    }

    public void setSimulators(ArrayList<Simulator> simulators) {
        this.simulators = simulators;
    }
}
