package com.example.jeux.remmeds.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

import com.example.jeux.remmeds.R;

public class Compartiment extends AppCompatActivity {

    Switch swi_breakfast, swi_dejeuner, swi_diner, swi_coucher, swi_heureperso, swi_frequenceperso;
    Button btn_enregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartiment);
        // Initialisation des éléments
        swi_breakfast = (Switch) findViewById(R.id.ptitdej_switch_layout_compartiment);
        swi_dejeuner = (Switch) findViewById(R.id.dejeuner_switch_layout_compartiment);
        swi_diner = (Switch) findViewById(R.id.diner_switch_layout_compartiment);
        swi_coucher = (Switch) findViewById(R.id.coucher_switch_layout_compartiment);
        swi_heureperso = (Switch) findViewById(R.id.heureperso_switch_layout_compartiment);
        swi_frequenceperso = (Switch) findViewById(R.id.frequenceperso_switch_layout_compartiment);
        btn_enregistrer = (Button) findViewById(R.id.enregistrer_button_layout_compartiment);
        btn_enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statusSwitch1, statusSwitch2;
                if (swi_breakfast.isChecked())
                    statusSwitch1 = swi_breakfast.getTextOn().toString();
                else
                    statusSwitch1 = swi_breakfast.getTextOff().toString();
                if (swi_dejeuner.isChecked())
                    statusSwitch2 = swi_dejeuner.getTextOn().toString();
                else
                    statusSwitch2 = swi_dejeuner.getTextOff().toString();
                if (swi_diner.isChecked())
                    statusSwitch2 = swi_diner.getTextOn().toString();
                else
                    statusSwitch2 = swi_diner.getTextOff().toString();
                if (swi_coucher.isChecked())
                    statusSwitch2 = swi_coucher.getTextOn().toString();
                else
                    statusSwitch2 = swi_coucher.getTextOff().toString();
                if (swi_heureperso.isChecked())
                    statusSwitch2 = swi_heureperso.getTextOn().toString();
                else
                    statusSwitch2 = swi_heureperso.getTextOff().toString();
                if (swi_frequenceperso.isChecked())
                    statusSwitch2 = swi_frequenceperso.getTextOn().toString();
                else
                    statusSwitch2 = swi_frequenceperso.getTextOff().toString();
                Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1 + "\n" + "Switch2 :" + statusSwitch2, Toast.LENGTH_LONG).show(); // display the current state for switch's
            }
        });

    }
}
