package com.a11group.microfinanceapp.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.a11group.microfinanceapp.model.Simulator;

import java.util.List;

/**
 * Created by Jonathan on 17/06/2017.
 */

public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Simulator>>{

    ProgressDialog dialog;

    @Override
    protected List<Simulator> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
