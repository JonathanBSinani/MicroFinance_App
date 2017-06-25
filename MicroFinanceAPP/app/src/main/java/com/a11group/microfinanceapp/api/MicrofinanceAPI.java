package com.a11group.microfinanceapp.api;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.a11group.microfinanceapp.R;
import com.a11group.microfinanceapp.model.Login;
import com.a11group.microfinanceapp.model.Simulator;
import com.a11group.microfinanceapp.trait.MicrofinanceSimulatorTrait;

public class MicrofinanceAPI {

    private String BASE_URL;
    private Retrofit retrofit;

    public MicrofinanceAPI(Context context) {

        this.BASE_URL = context.getString(R.string.base_url);
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
    }

    public Call<Simulator.Result> simulator(Simulator.Create simulator) {

        return retrofit.create(MicrofinanceSimulatorTrait.class).simulator(simulator);
    }

    //Just for Test
    public Call<Login> login() {

        return retrofit.create(MicrofinanceSimulatorTrait.class).login();
    }
}