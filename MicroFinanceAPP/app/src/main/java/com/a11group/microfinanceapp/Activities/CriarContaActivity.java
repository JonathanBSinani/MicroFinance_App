package com.a11group.microfinanceapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a11group.microfinanceapp.R;

public class CriarContaActivity extends AppCompatActivity {

    private static final String TAG = "CriarConta";

    private EditText txtnome;
    private EditText txtemail;
    private EditText txtsenha;
    private EditText txtconfirm;
    private TextView conta;
    private Button btnInscreve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnInscreve = (Button) findViewById(R.id.btn_salvar_Conta);
        conta = (TextView) findViewById(R.id.login_link);

        btnInscreve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(tela_login);
            }
        });
    }

    public void signup(){
        Log.d(TAG, "Signup");

        if (!validate()){
            onSignupFailed();
            return;
        }

        btnInscreve = (Button) findViewById(R.id.btn_salvar_Conta);
        txtnome = (EditText) findViewById(R.id.txtnome);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtsenha = (EditText) findViewById(R.id.txtsenha);
        txtconfirm = (EditText) findViewById(R.id.txtconfirma_senha);

        btnInscreve.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CriarContaActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.setMessage("Criando Conta...");
        progressDialog.show();

        String nome = txtnome.getText().toString();
        String email = txtemail.getText().toString();
        String senha = txtsenha.getText().toString();
        String confirm_senha = txtconfirm.getText().toString();

        /*
        *
        * aqui vai o código para salvar os dados da criação da conta
        *
        * */


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //chama o onSignupSucess ou o onSignupFailed
                //dependendo do sucesso
                onSignupSucess();
                //onSignupFailed()
                progressDialog.dismiss();
            }
        }, 3000);
    }

    public void onSignupSucess(){
        btnInscreve = (Button) findViewById(R.id.btn_salvar_Conta);

        btnInscreve.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(){
        btnInscreve = (Button) findViewById(R.id.btn_salvar_Conta);

        Toast.makeText(getBaseContext(), "Falha ao Logar", Toast.LENGTH_LONG).show();
        btnInscreve.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        txtnome = (EditText) findViewById(R.id.txtnome);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtsenha = (EditText) findViewById(R.id.txtsenha);
        txtconfirm = (EditText) findViewById(R.id.txtconfirma_senha);

        String nome = txtnome.getText().toString();
        String email = txtemail.getText().toString();
        String senha = txtsenha.getText().toString();
        String confirm_senha = txtconfirm.getText().toString();

        if (nome.isEmpty() || nome.length() < 3){
            txtnome.setError("Pelo menos 3 caracteres");
            valid = false;
        }else{
            txtnome.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("Entrar com endereço de email válido");
            valid = false;
        }else{
            txtemail.setError(null);
        }

        if (senha.isEmpty() || senha.length() < 4 || senha.length() > 10){
            txtsenha.setError("Entre 4 e 10 caracteres alfanuméricos");
            valid = false;
        }else{
            txtsenha.setError(null);
        }

        if (confirm_senha.isEmpty() || !confirm_senha.equals(senha)){
            txtconfirm.setError("A senha diferente da digitada anteriormente");
            valid = false;
        }else{
            txtconfirm.setError(null);
        }

        return valid;
    }

}
