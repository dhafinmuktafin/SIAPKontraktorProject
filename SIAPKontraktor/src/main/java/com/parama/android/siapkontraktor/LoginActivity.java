package com.parama.android.siapkontraktor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends SherlockActivity {

    private static EditText mHostname;
    private static EditText mDatabase;
    private static EditText mUsername;
    private static EditText mPassword;
    private static Button mLogin;

    public static DefaultHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeView();
        setListeners();
    }

    private void setListeners() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login().execute();
            }
        });
    }

    private void initializeView() {
        mHostname = (EditText) findViewById(R.id.hostnameField);
        mDatabase = (EditText) findViewById(R.id.databaseField);
        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);
        mLogin = (Button) findViewById(R.id.loginButton);
        httpClient = new DefaultHttpClient();
    }

    class Login extends AsyncTask<String, String, String> {

        ProgressDialog pDialog = new ProgressDialog(LoginActivity.this);
        JSONObject json;

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Logging In");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String hostname, database, username, password;
            hostname = mHostname.getText().toString();
            database = mDatabase.getText().toString();
            username = mUsername.getText().toString();
            password = mPassword.getText().toString();

            UserFunctions uf = new UserFunctions();
            json = uf.loginUser(hostname, database, username, password, httpClient);
            return null;
        }

        protected void onPostExecute(String arg0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (json.getString("success") != null) {
                            if (Integer.parseInt(json.getString("success")) == 1) {
                                pDialog.dismiss();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                pDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
