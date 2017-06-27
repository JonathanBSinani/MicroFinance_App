package com.a11group.microfinanceapp.api;

import com.a11group.microfinanceapp.trait.RetrofitClient;
import com.a11group.microfinanceapp.trait.RequestInterface;

/**
 * Created by Jonathan on 26/06/2017.
 */

public class ApiUtils {
    private ApiUtils() {

    }

    public static final String BASE_URL = "http://backend-fatec.herokuapp.com/api/";

    public static RequestInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(RequestInterface.class);
    }
}
