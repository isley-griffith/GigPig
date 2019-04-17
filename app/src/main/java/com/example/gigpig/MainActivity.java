package com.example.gigpig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    Button signInButton;
    EditText emailField;
    EditText passwordField;
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        //commenting these 4 lines in makes the user stay logged in even if they exit the app and reopen
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null) {
//            updateUI(currentUser);
//        }
    }

    public void signInButtonOnClick(View view) {
        signInButton = (Button)findViewById(R.id.signin);
        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password);
        final TextView message = (TextView)findViewById(R.id.message);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            message.setText("Failed. Try again.");
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                message.setText("Success!");
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                String exception = ((FirebaseAuthException) task.getException()).getErrorCode();
                                message.setText(exception);
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    public void signUpButtonClicked(View view) {
        Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(i);
    }

    public void updateUI(FirebaseUser currentUser) {
        if(currentUser != null) {
            Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
            startActivity(i);
        }
    }

}
