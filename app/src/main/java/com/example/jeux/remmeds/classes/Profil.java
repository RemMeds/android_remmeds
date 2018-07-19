package com.example.jeux.remmeds.classes;

import android.util.Log;
import android.widget.Toast;

import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.fragments.FragmentAccueil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profil {
    private String lastname;
    private String firstname;
    private String mail;
    private String breakfastHour;
    private String lunchHour;
    private String dinnerHour;
    private String bedHour;
    private String password;

    public Profil() {
        final String userID = MainActivity.getUserID();
        try {
            String profilURL = "http://212.73.217.202:15020/raspberry/get_user/" + userID;
            JSONObject data = MainActivity.getDoInBackground(profilURL);
            JSONArray array;
            array = data.getJSONArray("user");
            for (int i = 0; i < array.length(); i++) {
                this.setLastname(array.getJSONObject(i).getString("lastname"));
                this.setFirstname(array.getJSONObject(i).getString("firstname"));
                this.setMail(array.getJSONObject(i).getString("mail"));
                this.setPassword(array.getJSONObject(i).getString("password"));
                this.setBreakfastHour(array.getJSONObject(i).getString("pref_breakfast"));
                this.setLunchHour(array.getJSONObject(i).getString("pref_lunch"));
                this.setDinnerHour(array.getJSONObject(i).getString("pref_dinner"));
                this.setBedHour(array.getJSONObject(i).getString("pref_bedtime"));
            }
        } catch (JSONException e) {
            Log.e("arrayRecycler", "Exception catched" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("arrayRecyclerProfil", "NULL JSON" + e);
            FragmentAccueil.destroyRecyclerAccueil();
            Toast.makeText(FragmentAccueil.getAccueilContext(), "Erreur de connexion, veuillez rafraîchir", Toast.LENGTH_SHORT).show();
        }
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBreakfastHour() {
        return breakfastHour;
    }

    public void setBreakfastHour(String breakfastHour) {
        this.breakfastHour = breakfastHour;
    }

    public String getLunchHour() {
        return lunchHour;
    }

    public void setLunchHour(String lunchHour) {
        this.lunchHour = lunchHour;
    }

    public String getDinnerHour() {
        return dinnerHour;
    }

    public void setDinnerHour(String dinnerHour) {
        this.dinnerHour = dinnerHour;
    }

    public String getBedHour() {
        return bedHour;
    }

    public void setBedHour(String bedHour) {
        this.bedHour = bedHour;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}