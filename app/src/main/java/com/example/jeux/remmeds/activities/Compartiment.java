package com.example.jeux.remmeds.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.jeux.remmeds.R;

public class Compartiment extends AppCompatActivity {

    Switch swi_breakfast, swi_dejeuner, swi_diner, swi_coucher, swi_heureperso, swi_frequenceperso;
    ToggleButton tog_lundi, tog_mardi, tog_mercredi, tog_jeudi, tog_vendredi, tog_samedi, tog_dimanche;
    TextView tex_toutesles, tex_apartirde;
    EditText edi_nbrheure, edi_apartirde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartiment);
        swi_breakfast = (Switch) findViewById(R.id.ptitdej_switch_layout_compartiment);
        swi_breakfast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_breakfast.isChecked()) {
                    choix_heure();
                } else {
                    Toast.makeText(Compartiment.this, "ptitdej désactivé", Toast.LENGTH_LONG).show();
                }
            }
        });
        swi_dejeuner = (Switch) findViewById(R.id.dejeuner_switch_layout_compartiment);
        swi_dejeuner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_dejeuner.isChecked()) {
                    choix_heure();
                } else {
                    Toast.makeText(Compartiment.this, "dejeuner désactivé", Toast.LENGTH_LONG).show();
                }
            }
        });
        swi_diner = (Switch) findViewById(R.id.diner_switch_layout_compartiment);
        swi_diner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_diner.isChecked()) {
                    choix_heure();
                } else {
                    Toast.makeText(Compartiment.this, "Diner désactivé", Toast.LENGTH_LONG).show();
                }
            }
        });
        swi_coucher = (Switch) findViewById(R.id.coucher_switch_layout_compartiment);
        swi_coucher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_coucher.isChecked()) {
                    Toast.makeText(Compartiment.this, "Coucher activé", Toast.LENGTH_LONG).show();
                    //TODO
                } else {
                    Toast.makeText(Compartiment.this, "Coucher désactivé", Toast.LENGTH_LONG).show();
                    //TODO
                }
            }
        });
        swi_heureperso = (Switch) findViewById(R.id.heureperso_switch_layout_compartiment);
        tex_apartirde = (TextView) findViewById(R.id.fintexte_TextView_layout_compartiment);
        tex_toutesles = (TextView) findViewById(R.id.debuttexte_textview_layout_commpartiment);
        edi_nbrheure = (EditText) findViewById(R.id.nombreheure_editText_layout_compartiment);
        edi_apartirde = (EditText) findViewById(R.id.heuredepart_editText_layout_compartiment);
        swi_heureperso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_heureperso.isChecked()) {
                    tex_apartirde.setVisibility(View.VISIBLE);
                    tex_toutesles.setVisibility(View.VISIBLE);
                    edi_nbrheure.setVisibility(View.VISIBLE);
                    edi_apartirde.setVisibility(View.VISIBLE);
                } else {
                    tex_apartirde.setVisibility(View.GONE);
                    tex_toutesles.setVisibility(View.GONE);
                    edi_nbrheure.setVisibility(View.GONE);
                    edi_apartirde.setVisibility(View.GONE);
                }
            }
        });
        swi_frequenceperso = (Switch) findViewById(R.id.frequenceperso_switch_layout_compartiment);
        tog_lundi = (ToggleButton) findViewById(R.id.lundi_toggleButton_layout_compartiment);
        tog_mardi = (ToggleButton) findViewById(R.id.mardi_toggleButton_layout_compartiment);
        tog_mercredi = (ToggleButton) findViewById(R.id.mercredi_toggleButton_layout_compartiment);
        tog_jeudi = (ToggleButton) findViewById(R.id.jeudi_toggleButton_layout_compartiment);
        tog_vendredi = (ToggleButton) findViewById(R.id.vendredi_toggleButton_layout_compartiment);
        tog_samedi = (ToggleButton) findViewById(R.id.samedi_toggleButton_layout_compartiment);
        tog_dimanche = (ToggleButton) findViewById(R.id.dimanche_toggleButton_layout_compartiment);
        swi_frequenceperso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (swi_frequenceperso.isChecked()) {
                    tog_lundi.setVisibility(View.VISIBLE);
                    tog_mardi.setVisibility(View.VISIBLE);
                    tog_mercredi.setVisibility(View.VISIBLE);
                    tog_jeudi.setVisibility(View.VISIBLE);
                    tog_vendredi.setVisibility(View.VISIBLE);
                    tog_samedi.setVisibility(View.VISIBLE);
                    tog_dimanche.setVisibility(View.VISIBLE);
                } else {
                    tog_lundi.setVisibility(View.GONE);
                    tog_mardi.setVisibility(View.GONE);
                    tog_mercredi.setVisibility(View.GONE);
                    tog_jeudi.setVisibility(View.GONE);
                    tog_vendredi.setVisibility(View.GONE);
                    tog_samedi.setVisibility(View.GONE);
                    tog_dimanche.setVisibility(View.GONE);
                }
            }
        });
    }


    private void choix_heure() {
        final CharSequence[] items = {" Avant "," Pendant "," Après "};
        // arraylist pour l'item sélectionné
        final ArrayList<Integer> selectedItems=new ArrayList<Integer>();
        new AlertDialog.Builder(this)
                .setTitle("A quel moment du repas ?")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // ajoute valeur dans selectedItems
                            selectedItems.add(indexSelected);
                        } else if (selectedItems.contains(indexSelected)) {
                            // si valeur déjà dans array, supprimer
                            selectedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Annuler", null)
                .show();
    }
}