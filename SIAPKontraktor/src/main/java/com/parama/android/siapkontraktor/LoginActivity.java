package com.parama.android.siapkontraktor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;

public class LoginActivity extends SherlockActivity {

    private static EditText mHostname;
    private static EditText mDatabase;
    private static EditText mUsername;
    private static EditText mPassword;
    private static Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeView();
    }

    private void initializeView() {
        mHostname = (EditText) findViewById(R.id.hostnameField);
        mDatabase = (EditText) findViewById(R.id.databaseField);
        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);
        mLogin = (Button) findViewById(R.id.loginButton);
    }
}
