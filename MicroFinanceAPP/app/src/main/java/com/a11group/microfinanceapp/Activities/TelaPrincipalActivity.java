package com.a11group.microfinanceapp.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.a11group.microfinanceapp.AcessoRest;
import com.a11group.microfinanceapp.HttpConnection;
import com.a11group.microfinanceapp.Model.PrevidenciaModel;
import com.a11group.microfinanceapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TelaPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        AcessoRest ar = new AcessoRest();

        String chamadaWS;
        chamadaWS = "https://lit-stream-32066.herokuapp.com/api/receitas";

        String resultado = ar.chamadaGet(chamadaWS);
        Log.i("JSON", resultado);

        try{
            JSONObject jsonObject = new JSONObject(resultado);
            TextView textView = (TextView) findViewById(R.id.txtcampo);

            textView.setText(jsonObject.getString("descricao"));
        }catch (Exception ex){

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sendJson(View view)
    {

    }

    public void getJson(View view)
    {
        callServer("application/json", "");

    }

    private String generateJson(PrevidenciaModel previdenciaModel){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try{
            jo.put("descricao", previdenciaModel.getDescricao());
            jo.put("tipoReceita", previdenciaModel.getTipoReceita());



        }
        catch (JSONException e){ e.printStackTrace();}
        return (jo.toString());
    }

    private PrevidenciaModel degenerateJson(String data){

        PrevidenciaModel previdenciaModel = new PrevidenciaModel();

        try{
            JSONObject jo = new JSONObject(data);
            //JSONArray ja = new JSONArray();

            previdenciaModel.setDescricao(jo.getString("descricao"));
            previdenciaModel.setTipoReceita(jo.getString("tipoReceita"));

            jo.put("descricao", previdenciaModel.getDescricao());
            jo.put("tipoReceita", previdenciaModel.getTipoReceita());

            //APRESENTAÇÃO
            Log.i("Script", "Descrição: "+ previdenciaModel.getDescricao());
            Log.i("Script", "Tipo de Receita: "+ previdenciaModel.getTipoReceita());

        }
        catch (JSONException e){ e.printStackTrace();}
        return (previdenciaModel);
    }


    private void callServer(final String method, final String data)
    {
        new Thread(){
            public void run()
            {
                String answer = HttpConnection.getSetDataWeb("https://lit-stream-32066.herokuapp.com/api/receitas", method, data);

                Log.i("Script", "ANSWER" + answer);

                if (data.isEmpty()){
                    degenerateJson(answer);
                }
            }
        }.start();
    }
}
