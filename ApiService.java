package com.a11group.microfinanceapp.io;

import com.a11group.microfinanceapp.io.response.SimulatorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jonathan on 25/06/2017.
 */

public interface ApiService {

    @GET("simulator")
    Call<SimulatorResponse> getSimulator();
}
