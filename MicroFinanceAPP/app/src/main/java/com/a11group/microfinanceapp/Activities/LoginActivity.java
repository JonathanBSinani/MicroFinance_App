package com.a11group.microfinanceapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a11group.microfinanceapp.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;

    /*DECLARAÇÕES DE OBJETOS/*
     */

    private EditText txtemail;
    private EditText txtsenha;
    private Button btnlogin;
    private TextView linkInscrever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnlogin = (Button) findViewById(R.id.btn_login);
        linkInscrever = (TextView) findViewById(R.id.cria_conta_link);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        linkInscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_criaConta = new Intent(LoginActivity.this, CriarContaActivity.class);
                startActivity(tela_criaConta);
            }
        });

    }

    //função login
    public void login(){
        Log.d(TAG, "Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        txtemail = (EditText) findViewById(R.id.edt_email);
        txtsenha = (EditText) findViewById(R.id.edt_password);
        btnlogin = (Button) findViewById(R.id.btn_login);

        //desabilitando botão login
        btnlogin.setEnabled(false);

        //criando o ProgressDialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = txtemail.getText().toString();
        String password = txtsenha.getText().toString();

          /*
        *
        * aqui vai o código para verificar os dados de Login
        *
        * */


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //chama onLoginSucess ou a função onLoginFailed
                        onLoginSucess();
                        //função onLoginFailed
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){
                //acaba finalizando a atividade por padrão e vai acaba logando automaticamente
                finish();
            }
        }
    }

    @Override
    public void onBackPressed(){
        //desabilita o voltar ao MainActivity
        moveTaskToBack(true);
    }

    //função Sucesso no Login
    public void onLoginSucess(){
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnlogin.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, TelaPrincipalActivity.class);
        startActivity(intent);
//        finish();
    }

    //função Falha no Login
    public void onLoginFailed(){
        btnlogin = (Button) findViewById(R.id.btn_login);
        Toast.makeText(getBaseContext(), "Falha ao Logar", Toast.LENGTH_LONG).show();
        btnlogin.setEnabled(true);
    }

    //validação dos campos email e senha da tela Login
    public boolean validate(){
        boolean valid = true;

        txtemail = (EditText) findViewById(R.id.edt_email);
        txtsenha = (EditText) findViewById(R.id.edt_password);

        String email = txtemail.getText().toString();
        String senha = txtsenha.getText().toString();

        //validação de email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("Entre com endereço de email válido");
            valid = false;
        }else{
            txtemail.setError(null);
        }

        //validação de senha
        if (senha.isEmpty() || senha.length() < 4 || senha.length() > 10){
            txtsenha.setError("Entre 4 e 10 caracteres alfanuméricos");
            valid = false;
        }else{
            txtsenha.setError(null);
        }

        return valid;
    }

}
