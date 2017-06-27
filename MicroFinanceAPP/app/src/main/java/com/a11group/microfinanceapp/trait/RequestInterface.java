package com.a11group.microfinanceapp.trait;

import com.a11group.microfinanceapp.models.Simulator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Jonathan on 25/06/2017.
 */

public interface RequestInterface {
    @GET("simulator")
    Call<List<Simulator>> getJSON();

    @POST("simulator")
    @FormUrlEncoded
    Call<Simulator> savePost(@Field("birthdate") String birthdate,
                             @Field("gender") String gender,
                             @Field("retired") int retired,
                             @Field("year") int year,
                             @Field("money") double money
                         /*@Field("accumulatedValue") String accumulatedValue,
                         @Field("mothlyContribution") String monthlyContribution*/);


    @POST("simulator")
    @FormUrlEncoded
    Call<Simulator> savePostBody(@Body Simulator post);
}
