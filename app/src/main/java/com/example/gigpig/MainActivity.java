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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    Button signInButton;
    EditText usernameField;
    EditText passwordField;
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.password);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

        Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
        startActivity(i);

        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null) {
//            updateUI(currentUser);
//        }
    }

    public void signInButtonOnClick(View view) {
        signInButton = (Button)findViewById(R.id.signin);
        usernameField = (EditText)findViewById(R.id.username);
        passwordField = (EditText)findViewById(R.id.password);
        final TextView message = (TextView)findViewById(R.id.message);
        String usernameText = usernameField.getText().toString();
        String passwordText = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(usernameText, passwordText)
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
                            message.setText("Failed. Try Again.");
                        }
                    }
                });
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
