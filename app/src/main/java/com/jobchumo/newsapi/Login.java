package com.jobchumo.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    protected EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emaili);
        pass = findViewById(R.id.passw);
    }

    public void Log_in(View view) {


        String emailAddress = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Empty Fields \nPlease Enter All Your Details", Toast.LENGTH_SHORT).show();
        }
        else {
            signIn();
        }
    }

    private void signIn() {
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Logging You In...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        progressDialog.hide();
        startActivity(new Intent(Login.this, MainActivity.class));
    }
}