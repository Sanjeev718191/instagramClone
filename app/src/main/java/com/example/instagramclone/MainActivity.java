package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signupModeActive = true;
    TextView loginTextView;
    EditText username;
    EditText password;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginTextView){
            Button signupButton = findViewById(R.id.button);
            if(signupModeActive){
                signupModeActive = false;
                signupButton.setText("Login");
                loginTextView.setText("or Signup");
            } else {
                signupModeActive = true;
                signupButton.setText("Signup");
                loginTextView.setText("or Login");
            }
        }
        if(v.getId() == R.id.logoImageView || v.getId() == R.id.backgroundLayour){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
            signupClicked(v);
        }
        return false;
    }

    public void signupClicked(View view){

        if(username.getText().toString().matches("") || password.getText().toString().matches("")){
            Toast.makeText(this, "A user name and a password are required", Toast.LENGTH_SHORT).show();
        } else {
            if(signupModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Success", "Signup In Successfully");
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Success", "Logged In Successfully");
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                // if defined
                .clientKey("WawPNtdfhX4u")
                .server("http:ec2-18-183-178-249.ap-northeast-1.compute.amazonaws.com/parse/")
                .build()
        );
        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


        loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);

        username = findViewById(R.id.editTextPersonName);
        password = findViewById(R.id.editTextPassword);
        ImageView logoImageView = findViewById(R.id.logoImageView);
        ConstraintLayout backgroundLayout = findViewById(R.id.backgroundLayour);
        logoImageView.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);

        password.setOnKeyListener(this);

        if(ParseUser.getCurrentUser().getUsername() != null){
            showUserList();
        }
    }
}