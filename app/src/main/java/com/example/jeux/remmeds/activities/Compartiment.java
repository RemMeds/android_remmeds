package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.fragments.FragmentAccueil;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Compartiment extends AppCompatActivity implements View.OnClickListener {

    private Switch swiheureperso;
    private Switch swifrequenceperso;
    private ToggleButton toglundi;
    private ToggleButton togmardi;
    private ToggleButton togmercredi;
    private ToggleButton togjeudi;
    private ToggleButton togvendredi;
    private ToggleButton togsamedi;
    private ToggleButton togdimanche;
    private TextView texnommedic;
    private EditText edinbrheure;
    private EditText nbrduree;
    private EditText texnotes;
    private Switch swibreakfast;
    private Switch swidejeuner;
    private Switch swidiner;
    private Switch swicoucher;
    private Spinner typeduree;

    private String compid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button enregistrer;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartiment);
        swibreakfast = findViewById(R.id.ptitdej_switch_layout_compartiment);
        swidejeuner = findViewById(R.id.dejeuner_switch_layout_compartiment);
        swidiner = findViewById(R.id.diner_switch_layout_compartiment);
        swicoucher = findViewById(R.id.coucher_switch_layout_compartiment);
        swiheureperso = findViewById(R.id.heureperso_switch_layout_compartiment);
        swifrequenceperso = findViewById(R.id.frequenceperso_switch_layout_compartiment);

        enregistrer = findViewById(R.id.enregistrer_button_layout_compartiment);
        typeduree = findViewById(R.id.typenombre_spinner_layout_compartiment);
        texnotes = findViewById(R.id.note_editText_layout_compartiment);
        nbrduree = findViewById(R.id.nombre_editText_layout_compartiment);
        texnommedic = findViewById(R.id.nom_editText_layout_compartiment);
        edinbrheure = findViewById(R.id.heureperso_editText_layout_compartiment);

        toglundi = findViewById(R.id.lundi_toggleButton_layout_compartiment);
        togmardi = findViewById(R.id.mardi_toggleButton_layout_compartiment);
        togmercredi = findViewById(R.id.mercredi_toggleButton_layout_compartiment);
        togjeudi = findViewById(R.id.jeudi_toggleButton_layout_compartiment);
        togvendredi = findViewById(R.id.vendredi_toggleButton_layout_compartiment);
        togsamedi = findViewById(R.id.samedi_toggleButton_layout_compartiment);
        togdimanche = findViewById(R.id.dimanche_toggleButton_layout_compartiment);

        getFormattedHour(edinbrheure);

        swibreakfast.setOnClickListener(this);
        swidejeuner.setOnClickListener(this);
        swidiner.setOnClickListener(this);
        swicoucher.setOnClickListener(this);
        swiheureperso.setOnClickListener(this);
        swifrequenceperso.setOnClickListener(this);
        enregistrer.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String dayIntent = getIntent().getExtras().getString("days");
            String listePrefIntent = getIntent().getExtras().getString("list_pref");
            String durationTextIntent = getIntent().getExtras().getString("duration_text");
            String persoHourIntent = getIntent().getExtras().getString("perso_hour");
            String durationNumberIntent = getIntent().getExtras().getString("duration_number");
            String drugNameIntent = getIntent().getExtras().getString("drug_name");
            String noteIntent = getIntent().getExtras().getString("note");
            compid = getIntent().getExtras().getString("compartment_id");
            try {
                if (!dayIntent.equals("") && !dayIntent.equals("0")) {
                    setUpDaysPref(dayIntent);
                }
                if (!listePrefIntent.equals("") && !listePrefIntent.equals("0")) {
                    setUpTimePref(listePrefIntent);
                }
                if (!durationTextIntent.equals("") && !durationTextIntent.equals("0")) {
                    setUpDureePref(durationTextIntent);
                }
                if (!persoHourIntent.equals("") && !persoHourIntent.equals("0")) {
                    setUpHeurePerso(persoHourIntent);
                }
                if (!durationNumberIntent.equals("") && !durationNumberIntent.equals("0")) {
                    nbrduree.setText(durationNumberIntent);
                }
                if (!drugNameIntent.equals("") && !drugNameIntent.equals("0")) {
                    texnommedic.setText(drugNameIntent);
                }
                if (!noteIntent.equals("") && !noteIntent.equals("0")) {
                    texnotes.setText(noteIntent);
                }
            } catch (java.lang.NullPointerException e) {
                Log.e("Null Intents", "Compartiments" + e);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ptitdej_switch_layout_compartiment:
                break;
            case R.id.dejeuner_switch_layout_compartiment:
                break;
            case R.id.diner_switch_layout_compartiment:
                break;
            case R.id.coucher_switch_layout_compartiment:
                break;
            case R.id.heureperso_switch_layout_compartiment:
                optionsheureperso();
                break;
            case R.id.frequenceperso_switch_layout_compartiment:
                optionsfrequenceperso();
                break;
            case R.id.enregistrer_button_layout_compartiment:
                if(texnommedic.getText().toString().equals("")){
                    alertDialogDrugNameError();
                }
                else{
                    saveChanges();
                    FragmentAccueil.destroyRecyclerAccueil();
                    onBackPressed();
                }
                break;
            default:
                break;
        }
    }

    private void alertDialogDrugNameError() {
        //Display an alert dialog for credential error
        AlertDialog.Builder builder = new AlertDialog.Builder(Compartiment.this);
        builder.setCancelable(false);
        builder.setTitle("Aucun nom de médicament");
        builder.setMessage("Cliquez sur le bouton retour pour annuler vos modifications " +
                "ou sur le bouton continuer");
        builder.setNegativeButton("retour", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onBackPressed();
            }
        });

        builder.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void saveChanges() {
        MainActivity.postDoInBackground("http://212.73.217.202:15020/compartment/update_com/" + compid + "&" + saveDrugName() + "&" + saveNotes()+ "&" + saveDurationNumb() + "&" + saveDurationText() + "&0&" + saveHeurePerso(swiheureperso) + "&0&" + saveDayPerso() + "&" + saveListPref());
        Toast.makeText(Compartiment.this, "Sauvegardé", Toast.LENGTH_SHORT).show();
    }

    private String saveHeurePerso(Switch heureperso) {
        if (heureperso.isChecked()) {
            return edinbrheure.getText().toString();
        } else {
            return "0";
        }
    }

    private String saveDrugName() {
        if (texnommedic.getText().toString().equals("")) {
            return "0";
        } else {
            return texnommedic.getText().toString();
        }
    }

    private String saveNotes() {
        if (texnotes.getText().toString().equals("")) {
            return "0";
        } else {
            return texnotes.getText().toString();
        }
    }

    private String saveDurationNumb() {
        if (nbrduree.getText().toString().equals("")) {
            return "0";
        } else {
            return nbrduree.getText().toString();
        }
    }

    private String saveDurationText() {
        if (typeduree.getSelectedItem().toString().equals("")) {
            return "0";
        } else {
            return typeduree.getSelectedItem().toString();
        }
    }

    private String saveListPref() {
        String urlpref = "";
        if (swibreakfast.isChecked()) {
            urlpref += "Breakfast,";
        }
        if (swidejeuner.isChecked()) {
            urlpref += "Lunch,";
        }
        if (swidiner.isChecked()) {
            urlpref += "Dinner,";
        }
        if (swicoucher.isChecked()) {
            urlpref += "Bedtime,";
        }
        if (urlpref.equals("")) {
            return "0";
        } else {
            urlpref = urlpref.substring(0, urlpref.length() - 1);
            return urlpref;
        }
    }

    private String saveDayPerso() {
        if (swifrequenceperso.isChecked()) {
            String urldays = "";
            if (toglundi.isChecked()) {
                urldays += "Lundi,";
            }
            if (togmardi.isChecked()) {
                urldays += "Mardi,";
            }
            if (togmercredi.isChecked()) {
                urldays += "Mercredi,";
            }
            if (togjeudi.isChecked()) {
                urldays += "Jeudi,";
            }
            if (togvendredi.isChecked()) {
                urldays += "Vendredi,";
            }
            if (togsamedi.isChecked()) {
                urldays += "Samedi,";
            }
            if (togdimanche.isChecked()) {
                urldays += "Dimanche,";
            }
            if (urldays.equals("")) {
                return "0";
            } else {
                urldays = urldays.substring(0, urldays.length() - 1);
                return urldays;
            }
        } else {
            return "0";
        }
    }

    private void setUpDureePref(String dureePref) {
        String[] items = dureePref.split(",");
        for (String item : items) {
            switch (item) {
                case "Jours":
                    typeduree.setSelection(0);
                    break;
                case "Semaines":
                    typeduree.setSelection(1);
                    break;
                case "Mois":
                    typeduree.setSelection(2);
                    break;
                case "Années":
                    typeduree.setSelection(3);
                    break;
                default:
                    break;
            }

        }
    }

    private void setUpHeurePerso(String heureperso) {
        swiheureperso.setChecked(true);
        optionsheureperso();
        edinbrheure.setText(heureperso);
    }

    private void setUpTimePref(String timePref) {
        String[] items = timePref.split(",");
        for (String item : items) {
            switch (item) {
                case "Breakfast":
                    swibreakfast.setChecked(true);
                    break;
                case "Lunch":
                    swidejeuner.setChecked(true);
                    break;
                case "Dinner":
                    swidiner.setChecked(true);
                    break;
                case "Bedtime":
                    swicoucher.setChecked(true);
                    break;
                default:
                    break;
            }
        }


    }

    private void setUpDaysPref(String daysPref) {
        String[] items = daysPref.split(",");
        swifrequenceperso.setChecked(true);
        optionsfrequenceperso();
        for (String item : items) {
            switch (item) {
                case "Lundi":
                    toglundi.setChecked(true);
                    break;
                case "Mardi":
                    togmardi.setChecked(true);
                    break;
                case "Mercredi":
                    togmercredi.setChecked(true);
                    break;
                case "Jeudi":
                    togjeudi.setChecked(true);
                    break;
                case "Vendredi":
                    togvendredi.setChecked(true);
                    break;
                case "Samedi":
                    togsamedi.setChecked(true);
                    break;
                case "Dimanche":
                    togdimanche.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    private void getFormattedHour(final EditText whichField) {
        //Launch a time picker instead of keyboard on clock field
        whichField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar myCurrentTime = Calendar.getInstance();
                int hour = myCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = myCurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Compartiment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        DecimalFormat addZero = new DecimalFormat("00");
                        whichField.setText(addZero.format(selectedHour) + ":" + addZero.format(selectedMinute)); //NOSONAR
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Sélectionner heure");
                mTimePicker.show();

            }
        });
    }

    private void optionsfrequenceperso() {
        if (swifrequenceperso.isChecked()) {
            toglundi.setVisibility(View.VISIBLE);
            togmardi.setVisibility(View.VISIBLE);
            togmercredi.setVisibility(View.VISIBLE);
            togjeudi.setVisibility(View.VISIBLE);
            togvendredi.setVisibility(View.VISIBLE);
            togsamedi.setVisibility(View.VISIBLE);
            togdimanche.setVisibility(View.VISIBLE);
        } else {
            toglundi.setVisibility(View.GONE);
            togmardi.setVisibility(View.GONE);
            togmercredi.setVisibility(View.GONE);
            togjeudi.setVisibility(View.GONE);
            togvendredi.setVisibility(View.GONE);
            togsamedi.setVisibility(View.GONE);
            togdimanche.setVisibility(View.GONE);
        }
    }

    private void optionsheureperso() {
        if (swiheureperso.isChecked()) {
            edinbrheure.setVisibility(View.VISIBLE);
        } else {
            edinbrheure.setVisibility(View.GONE);
        }
    }
}