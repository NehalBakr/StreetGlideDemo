package com.example.nehal.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
    private EditText etCnfPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword= (EditText) findViewById(R.id.etPassword);
        etCnfPassword = (EditText) findViewById(R.id.etCnfPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        }


    public void registerButtonClicked(View view) {
        if (validatePassword()){
            Toast.makeText(Register.this, R.string.not_match, Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog progressDialog = ProgressDialog.show(Register.this, getString(R.string.please_wait2), getString(R.string.processing2), true);
            (firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, R.string.registeration_successful, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Register.this, Login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Register.this, R.string.registeration_failed, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
    public boolean validatePassword() {

        String pwd = etPassword.getText().toString();
        String conPwd = etCnfPassword.getText().toString();

        if (!empty(pwd) && !empty(conPwd) && !pwd.equals(conPwd)) {
            Toast.makeText(Register.this, R.string.error1, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public static boolean empty( final String pwd ) {

        return pwd == null || pwd.trim().isEmpty();
    }
}
