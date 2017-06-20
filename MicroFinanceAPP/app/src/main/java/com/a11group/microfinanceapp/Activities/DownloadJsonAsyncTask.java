package com.a11group.microfinanceapp.Activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.a11group.microfinanceapp.Model.PrevidenciaModel;

import java.util.List;

/**
 * Created by Jonathan on 17/06/2017.
 */

public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<PrevidenciaModel>>{

    ProgressDialog dialog;

    @Override
    protected List<PrevidenciaModel> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
