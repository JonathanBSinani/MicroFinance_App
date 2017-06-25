package com.a11group.microfinanceapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.a11group.microfinanceapp.R;
import com.a11group.microfinanceapp.api.MicrofinanceAPI;
import com.a11group.microfinanceapp.io.ApiAdapter;
import com.a11group.microfinanceapp.io.response.SimulatorResponse;
import com.a11group.microfinanceapp.models.Simulator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "TelaPrincipal";
    private MicrofinanceAPI microfinanceAPI;

    private ListView listSimulator;

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

        listSimulator = (ListView) findViewById(R.id.listSimulator);



        //teste vídeo  - Obtener y registrar datos usando Retrofit y GSON - https://www.youtube.com/watch?v=BboM1MubiYU

        obterDadosSimulacao();

















        //microfinanceAPI = new MicrofinanceAPI(this);



        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceSimulatorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceSimulatorInterface service = retrofit.create(ServiceSimulatorInterface.class);
        Call<catalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<catalog>() {

            @Override
            public void onResponse(Call<catalog> call, Response<catalog> response) {
                if (!response.isSuccessful()) {
                    Log.i("TAG", "Erro: " + response.code());
                }
                else {
                    // requisição retornou com sucesso
                    catalog catalog = response.body();

                    for (Simulator s : catalog.simulator) {
                        Log.i(TAG, String.format("%s: %s",s.birthdate, s.gender));
                        Log.i(TAG, "-----------------------------------------------------------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<catalog> call, Throwable t) {
                Log.e(TAG, "Erro: " + t.getMessage());
            }
        });
*/
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



    private void povoarListSimulacao(ArrayList<Simulator> simulators) {
        ArrayList<String> list = new ArrayList<String>();
        for (Simulator  s: simulators) {
            list.add(s.getBirthdate());
        }

        ArrayAdapter<String> listArrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, list);
        listSimulator.setAdapter(listArrayAdapter);
    }

    private void obterDadosSimulacao() {
        Call<SimulatorResponse> call = ApiAdapter.getApiService().getSimulator();
        call.enqueue(new SimulatorCallback());
    }

    class SimulatorCallback implements Callback<SimulatorResponse> {

        @Override
        public void onResponse(Call<SimulatorResponse> call, Response<SimulatorResponse> response) {
            if (response.isSuccessful()) {
                SimulatorResponse simulatorResponse = response.body();
                povoarListSimulacao(simulatorResponse.getSimulators());
            } else {
                Toast.makeText(getBaseContext(), "Erro no formato de resposta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<SimulatorResponse> call, Throwable t) {
            Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
