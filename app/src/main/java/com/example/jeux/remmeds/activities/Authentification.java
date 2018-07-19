package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jeux.remmeds.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Authentification extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        Button connexionButton = findViewById(R.id.connexion_button_layout_authentification);
        Button sInscrireButton = findViewById(R.id.sinscrire_button_layout_authentification);
        Button problemeConnexionButton = findViewById(R.id.problemeconnexion_button_layout_authentification);
        connexionButton.setOnClickListener(this);
        sInscrireButton.setOnClickListener(this);
        problemeConnexionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connexion_button_layout_authentification:
                EditText user = findViewById(R.id.mail_edittext_layout_authentification);
                EditText password = findViewById(R.id.password_edittext_layout_authentification);
                AsyncHttpClient client = new AsyncHttpClient();
                String myUser = user.getText().toString();
                String myPassword = password.getText().toString();
                if (!myUser.isEmpty() && !myPassword.isEmpty()) {
                    String myURL = "http://212.73.217.202:15020/user/check_account/" + myUser + "&" + myPassword;
                    client.get(myURL, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] header, JSONObject response) {
                            try {
                                String connection = response.getString("connection");
                                String userID = response.getString("user_id");
                                if (connection.equals("true")) {
                                    Intent mainActivity = new Intent(Authentification.this, MainActivity.class);
                                    mainActivity.putExtra("userID", userID);
                                    startActivity(mainActivity);
                                } else {
                                    alertDialogAuthentication();
                                }
                            } catch (JSONException e) {
                                Log.i("Get Response Error", ": "+e);
                            }
                        }
                    });
                } else {
                    alertDialogAuthentication();
                }
                break;
            case R.id.sinscrire_button_layout_authentification:
                Intent signUpActivity = new Intent(Authentification.this, Inscription.class);
                startActivity(signUpActivity);
                break;
            case R.id.problemeconnexion_button_layout_authentification:
                alertDialogConnectionError();
                break;
            default:
                Log.i("Dafault switch", "Any button catched");
        }
    }

    private void alertDialogAuthentication() {
        //Display an alert dialog for credential error
        AlertDialog.Builder builder = new AlertDialog.Builder(Authentification.this);
        builder.setCancelable(true);
        builder.setTitle("Erreur d'authentification");
        builder.setMessage("\nVotre compte / mot de passe est erroné.\n\nVeuillez cliquer sur Ok et réessayer.");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void alertDialogConnectionError() {
        //Display an alert dialog for credential error
        AlertDialog.Builder builder = new AlertDialog.Builder(Authentification.this);
        builder.setCancelable(true);
        builder.setTitle("Problème de connexion ?");
        builder.setMessage("Cliquez sur le bouton ci-joint pour envoyer un mail au support " +
                "de notre application ou via notre site web.\n(Rubrique: Nous contacter)");
        builder.setNegativeButton("Envoyer mail", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mailto = "mailto:remmeds@outlook.fr" +
                        "?subject=" + Uri.encode("Problème de connexion Application RemMeds");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Visiter site web", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String websiteAddress = "http://212.73.217.202:10080/"; //NOSONAR
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(websiteAddress));
                startActivity(viewIntent);
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter l'application ?")
                .setMessage("\nÊtes-vous sûr de vouloir quitter l'application ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Non", null)
                .show();
    }
}
