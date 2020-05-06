package com.example.admin.augscan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView passwordreset;
    private EditText passwordresetemail;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.emailSignIn);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.Login);

        passwordreset = findViewById(R.id.forgotpassword);
        passwordresetemail = findViewById(R.id.emailSignIn);
        progressBar = (ProgressBar) findViewById(R.id.progressbars);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        processDialog = new ProgressDialog(this);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }

        });

        passwordreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpasword();
            }
        });
    }

    public void resetpasword(){
        final String resetemail = passwordresetemail.getText().toString();

        if (resetemail.isEmpty()) {
            passwordresetemail.setError("It's empty");
            passwordresetemail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(resetemail)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }




    public void validate(String userEmail, String userPassword){

        processDialog.setMessage("................Please Wait.............");
        processDialog.show();

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    processDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, dashboardActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    processDialog.dismiss();
                }
            }
        });
    }
}
