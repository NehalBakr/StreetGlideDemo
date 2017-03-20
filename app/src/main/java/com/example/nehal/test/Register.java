package com.example.nehal.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        EditText etCnfPassword = (EditText) findViewById(R.id.etCnfPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        String pwd = etPassword.getText().toString();
        String conPwd = etCnfPassword.getText().toString();

        if (!pwd.equals(" ") && !conPwd.equals(" ") && !pwd.equals(conPwd)){

            Toast.makeText(Register.this,"Password not match", Toast.LENGTH_SHORT).show();

        }

        }


    public void bRegister_click(View view) {
        final ProgressDialog progressDialog = ProgressDialog.show(Register.this, "Please wait...", "Processing... ", true);
        (firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registeration is Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                } else {
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
