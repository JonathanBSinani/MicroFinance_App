package com.a11group.microfinanceapp.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a11group.microfinanceapp.api.ApiUtils;
import com.a11group.microfinanceapp.models.Simulator;
import com.a11group.microfinanceapp.R;
import com.a11group.microfinanceapp.trait.RequestInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewSimulationActivity extends AppCompatActivity {

    private static final String TAG = "NewSimulationActivity";
    private RequestInterface mAPIService;

    private TextView txtResultadoSimualacao;
    private EditText edtDataNascimento;
    private EditText edtIdadeAposentadoria;
    private EditText edtValorMensal;

    private Button btnSimular;

    private Spinner spnSexo;
    private Spinner spnRecebBeneficio;
    private List<String> sexos = new ArrayList<String>();
    private List<String> recBenificios = new ArrayList<String>();
    private String sexo;
    private String recBen;

    private CardView cvRecultado;

    private String data;

    DatePickerDialog datePickerDialog;

    private int anoCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //adicionando Sexos ao Spinner no Layout
        sexos.add("male");
        sexos.add("female");

        recBenificios.add("10");
        recBenificios.add("20");
        recBenificios.add("30");
        recBenificios.add("40");


        //identificar os campos
        txtResultadoSimualacao = (TextView) findViewById(R.id.resultadoSimulacao);
        edtDataNascimento = (EditText) findViewById(R.id.edtDataNascimento);
        edtIdadeAposentadoria = (EditText) findViewById(R.id.edtIdadeAposentadoria);
        edtValorMensal = (EditText) findViewById(R.id.edtValorMensal);
        btnSimular = (Button) findViewById(R.id.btnSimular);

        cvRecultado = (CardView) findViewById(R.id.card_view_resultado);

        cvRecultado.setVisibility(View.INVISIBLE);

        //identificar o spinner
        spnSexo = (Spinner) findViewById(R.id.spnSexo);
        spnRecebBeneficio = (Spinner) findViewById(R.id.spnRecebimentoBeneficio);

        //criar arrayAdapter
        //arrayAdapter de Sexos
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sexos);
        ArrayAdapter<String> spinnerSexoArrayAdapter = arrayAdapter;
        spinnerSexoArrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spnSexo.setAdapter(spinnerSexoArrayAdapter);

        //capturando o Sexo no Spinner
        spnSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //pega sexo pela posição
                sexo  = adapterView.getItemAtPosition(i).toString();
                if (sexo.equals("male")) {
                    edtIdadeAposentadoria.setText("65");
                } else {
                    edtIdadeAposentadoria.setText("60");
                }
                //Toast.makeText(NewSimulationActivity.this, "Sexo Selecionado: " + sexo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //arrayAdapter de Recebimento de benefícios
        ArrayAdapter<String> integerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, recBenificios);
        ArrayAdapter<String> spinnerRecBenArrayAdapter = integerArrayAdapter;
        spinnerRecBenArrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spnRecebBeneficio.setAdapter(spinnerRecBenArrayAdapter);
        //capturando o Recebimento do beneficio
        spnRecebBeneficio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //pega recebimento pela posição
                recBen = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(NewSimulationActivity.this, "Selecionado: " + recBen, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewSimulationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edtDataNascimento.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                                data = year + "-" + monthOfYear + "-" + dayOfMonth;
                                anoCorrente = year;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        mAPIService = ApiUtils.getAPIService();

        btnSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String dataNasc = edtDataNascimento.getText().toString().trim();
                String dataNasc = data;
                String gender = sexo;
                Integer idadeAposentadoria = Integer.parseInt(edtIdadeAposentadoria.getText().toString().trim());
                Integer AnosRecebimento = Integer.parseInt(recBen);
                double ValorMensal = Double.parseDouble(edtValorMensal.getText().toString().trim());
                //implementar validação
                if (verificaCampos()){
                    sendPostSimulator(dataNasc, gender, idadeAposentadoria, AnosRecebimento, ValorMensal);
                    double resultado;
                    resultado = calculoPrevidencia(ValorMensal, AnosRecebimento, idadeAposentadoria);
                    cvRecultado.setVisibility(View.VISIBLE);
                    txtResultadoSimualacao.setText("R$ " + resultado);
                }

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendPostSimulator(String dataNasc, String gender, Integer idadeAposentadoria, Integer AnosRecebimento, Double ValorMensal) {
        mAPIService.savePost(dataNasc,gender,idadeAposentadoria,AnosRecebimento, ValorMensal).enqueue(new Callback<Simulator>() {
            @Override
            public void onResponse(Call<Simulator> call, Response<Simulator> response) {
                if(response.isSuccessful()) {
                    response.body().toString();
                    Toast.makeText(NewSimulationActivity.this, "Simulação realizada com sucesso!! ", Toast.LENGTH_LONG).show();
                    //Log.i(TAG, "post submitted to API." + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<Simulator> call, Throwable t) {
                Toast.makeText(NewSimulationActivity.this, "Ocorreu algum problema!! Tente novamente...", Toast.LENGTH_LONG).show();
                //Log.e(TAG, "Unable to submit post to API.");

            }
        });

    }

    public boolean verificaCampos(){
        boolean valid = true;

        edtDataNascimento = (EditText) findViewById(R.id.edtDataNascimento);
        edtIdadeAposentadoria = (EditText) findViewById(R.id.edtIdadeAposentadoria);
        edtValorMensal = (EditText) findViewById(R.id.edtValorMensal);

        String dataNascimento = edtDataNascimento.getText().toString();
        String idadeAposentadoria = edtIdadeAposentadoria.getText().toString();
        String valorMensal = edtValorMensal.getText().toString();

        //validação de Data de Nascimento
        if (dataNascimento.isEmpty()) {
            edtDataNascimento.setError("Data de Nascimento é obrigatório!!");
            edtDataNascimento.setFocusable(true);
            edtDataNascimento.requestFocus();
            valid = false;
        } else {
            edtDataNascimento.setError(null);
        }

        //validação de Idade da Aposentadoria
        if (idadeAposentadoria.isEmpty() || idadeAposentadoria.length() > 2) {
            edtIdadeAposentadoria.setError("Idade da Aposentadoria é obrigatório!! verifica a idade (menor que 100)");
            edtIdadeAposentadoria.setFocusable(true);
            edtIdadeAposentadoria.requestFocus();
            valid = false;
        } else {
            edtIdadeAposentadoria.setError(null);
        }

        //validação de Valor Mensal
        if (valorMensal.isEmpty()) {
            edtValorMensal.setError("Valor Pretendido é obrigatório!!");
            valid = false;
        } else {
            edtValorMensal.setError(null);
        }

        return valid;
    }

    public double calculoPrevidencia(double mMoney, Integer mAnosRecebimento, Integer mAnosRetirada) {
        Calendar c = Calendar.getInstance();
        Integer mYearCurrent = c.get(Calendar.YEAR);
        Integer idade = 0;
        double mMontlyContribution = 0.00, mAccumulatedValue = 0.00;

        idade = mYearCurrent - anoCorrente;

        mAccumulatedValue = mMoney * (mAnosRecebimento * 12);

        mMontlyContribution = mAccumulatedValue / ((mAnosRetirada - idade) * 12);

        Toast.makeText(NewSimulationActivity.this, String.format("Ano de nascimento: " + anoCorrente +"\n"
                +"idade: "+ idade + "\n"
                + "Valor acumulado: " + mAccumulatedValue + "\n"
                + "Valor montante: " + Double.valueOf(String.format(Locale.US, "%.2f", mMontlyContribution))), Toast.LENGTH_LONG).show();

        return Double.valueOf(String.format(Locale.US, "%.2f", mMontlyContribution));
    }

}
