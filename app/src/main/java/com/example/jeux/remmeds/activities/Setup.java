package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.jeux.remmeds.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        final EditText field_prenom = findViewById(R.id.prenom_edittext_layout_setup);
        final EditText field_nom = findViewById(R.id.nom_edittext_layout_setup);
        final EditText field_breakfast = findViewById(R.id.breakfast_editText_layout_setup);
        final EditText field_lunch = findViewById(R.id.lunch_edittext_layout_setup);
        final EditText field_dinner = findViewById(R.id.dinner_edittext_layout_setup);
        final EditText field_bedtime = findViewById(R.id.bedtime_edittext_layout_setup);
        get_formatted_hour(field_breakfast);
        get_formatted_hour(field_lunch);
        get_formatted_hour(field_dinner);
        get_formatted_hour(field_bedtime);
        Button valider_button = findViewById(R.id.enregistrer_button_layout_setup);
        valider_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(field_prenom.getText()) && !TextUtils.isEmpty(field_nom.getText()) &&
                        !TextUtils.isEmpty(field_breakfast.getText()) && !TextUtils.isEmpty(field_lunch.getText()) &&
                        !TextUtils.isEmpty(field_dinner.getText()) && !TextUtils.isEmpty(field_bedtime.getText())) {
                    //ATM  do nothing, Future add values in DB 
                    finish();
                } else {
                    alert_dialog_missing_field_error();
                }
            }
        });
    }


    private void get_formatted_hour(final EditText which_field) {
        //Launch a time picker instead of keyboard on clock field
        which_field.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar my_current_time = Calendar.getInstance();
                int hour = my_current_time.get(Calendar.HOUR_OF_DAY);
                int minute = my_current_time.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Setup.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        DecimalFormat add_zero = new DecimalFormat("00");
                        which_field.setText(add_zero.format(selectedHour) + ":" + add_zero.format(selectedMinute)); //NOSONAR
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Sélectionner heure");
                mTimePicker.show();

            }
        });
    }


    private void alert_dialog_missing_field_error() {
        //Display an alert dialog for missing field
        AlertDialog.Builder builder = new AlertDialog.Builder(Setup.this);

        builder.setCancelable(true);
        builder.setTitle("Problème");
        builder.setMessage("\nVeuillez renseigner tous les champs avant de valider");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Annuler inscription ?")
                .setMessage("\nÊtes-vous sûr de vouloir annuler l'inscription ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }
}
