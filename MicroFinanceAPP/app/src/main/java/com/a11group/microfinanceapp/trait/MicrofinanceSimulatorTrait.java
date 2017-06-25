package com.a11group.microfinanceapp.trait;

import com.a11group.microfinanceapp.api.Login;
import com.a11group.microfinanceapp.model.Simulator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MicrofinanceSimulatorTrait {

    @POST("simulator")
    Call<Simulator.Result> simulator(@Body Simulator.Create simulator);

    //Just for Test
    @GET("login.php")
    Call<Login> login();
}