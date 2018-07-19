package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jeux.remmeds.fragments.FragmentGestion;
import com.example.jeux.remmeds.fragments.FragmentRepertoire;
import com.example.jeux.remmeds.fragments.FragmentAccueil;
import com.example.jeux.remmeds.fragments.FragmentHistorique;
import com.example.jeux.remmeds.fragments.FragmentProfil;
import com.example.jeux.remmeds.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String userID;

    public static String getUserID() {
        return userID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent authenticationActivity = getIntent();
        userID = authenticationActivity.getStringExtra("userID");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Affiche l'accueil en premier écran
        displayFragment(R.id.fragment_accueil);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public static JSONObject getDoInBackground(String targetURL) {
        //Permet d'initier la connexion à l'api pour des requetes GET
        // targetURL : Url du chemin de l'API
        //Retourne un JSON

        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(targetURL);
            urlConn = url.openConnection();
            urlConn.setConnectTimeout(3 * 1000);
            urlConn.setReadTimeout(5 * 1000);
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return new JSONObject(stringBuilder.toString());
        } catch (Exception ex) {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
    }

    public static void postDoInBackground(String targetURL) {
        //Permet d'initier la connexion à l'api pour des requetes POST
        // targetURL : Url du chemin de l'API
        int timeout = 5000;
        URL url = null;
        HttpURLConnection connexion = null;
        try {
            // Crée les connexions
            url = new URL(targetURL);
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("POST");
            connexion.setRequestProperty("Content-Type",
                    "application/json");
            connexion.setRequestProperty("Content-Language", "fr-FR");
            connexion.setUseCaches(false);
            connexion.setDoInput(true);
            connexion.setDoOutput(true);
            connexion.setConnectTimeout(timeout);
            connexion.setReadTimeout(timeout);
            InputStream is = connexion.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
        } catch (Exception e) {
            Log.e("doPost", "Exception catched :" + e);
        } finally {
            if (connexion != null) {
                connexion.disconnect();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, About.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displayFragment(item.getItemId());
        //make this method blank
        return true;
    }

    private void displayFragment(int itemId) {
        //creation du fragment
        Fragment fragment;
        //initialisation du fragment selectionné
        switch (itemId) {
            case R.id.nav_accueil:
                fragment = new FragmentAccueil();
                break;
            case R.id.nav_historique:
                fragment = new FragmentHistorique();
                break;
            case R.id.nav_profil:
                fragment = new FragmentProfil();
                break;
            case R.id.nav_repertoire:
                fragment = new FragmentRepertoire();
                break;
            case R.id.nav_gestion:
                fragment = new FragmentGestion();
                break;
            case R.id.nav_disconnect:
                new AlertDialog.Builder(this)
                        .setTitle("Déconnexion")
                        .setMessage("\nÊtes-vous sûr de vouloir vous déconnecter ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Non", null)
                        .show();
                fragment = new FragmentAccueil();
                break;
            default:
                fragment = new FragmentAccueil();
        }

        //Remplacement du fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
