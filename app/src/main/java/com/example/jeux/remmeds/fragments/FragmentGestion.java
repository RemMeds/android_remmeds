package com.example.jeux.remmeds.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.Compartiment;
import com.example.jeux.remmeds.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentGestion extends Fragment implements View.OnClickListener {


    Button comp1;
    Button comp2;
    Button comp3;
    Button comp4;
    Button comp5;
    Button comp6;
    Button comp7;
    Button comp8;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        final View ges = inflater.inflate(R.layout.fragment_gestion, container, false);

        comp1 = ges.findViewById(R.id.compartiment1_button_layout_pilulier);
        comp2 = ges.findViewById(R.id.compartiment2_button_layout_pilulier);
        comp3 = ges.findViewById(R.id.compartiment3_button_layout_pilulier);
        comp4 = ges.findViewById(R.id.compartiment4_button_layout_pilulier);
        comp5 = ges.findViewById(R.id.compartiment5_button_layout_pilulier);
        comp6 = ges.findViewById(R.id.compartiment6_button_layout_pilulier);
        comp7 = ges.findViewById(R.id.compartiment7_button_layout_pilulier);
        comp8 = ges.findViewById(R.id.compartiment8_button_layout_pilulier);

        comp1.setOnClickListener(this);
        comp2.setOnClickListener(this);
        comp3.setOnClickListener(this);
        comp4.setOnClickListener(this);
        comp5.setOnClickListener(this);
        comp6.setOnClickListener(this);
        comp7.setOnClickListener(this);
        comp8.setOnClickListener(this);

        return ges;
    }

    public void onClick(View v) {
        try {
            JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/compartment/list_com/" + MainActivity.getUserID());
            JSONArray array = data.getJSONArray("compartment");
            switch (v.getId()) {
                case R.id.compartiment1_button_layout_pilulier:
                    putItents(array.getJSONObject(0));
                    break;
                case R.id.compartiment2_button_layout_pilulier:
                    putItents(array.getJSONObject(1));
                    break;
                case R.id.compartiment3_button_layout_pilulier:
                    putItents(array.getJSONObject(2));
                    break;
                case R.id.compartiment4_button_layout_pilulier:
                    putItents(array.getJSONObject(3));
                    break;
                case R.id.compartiment5_button_layout_pilulier:
                    putItents(array.getJSONObject(4));
                    break;
                case R.id.compartiment6_button_layout_pilulier:
                    putItents(array.getJSONObject(5));
                    break;
                case R.id.compartiment7_button_layout_pilulier:
                    putItents(array.getJSONObject(6));
                    break;
                case R.id.compartiment8_button_layout_pilulier:
                    putItents(array.getJSONObject(7));
                default:
                    break;
            }

        } catch (JSONException e) {
            Log.e("JsonErreur", "Onclick.comp" + e);
        } catch (java.lang.NullPointerException e) {
            Log.e("NullJson", "Onclick.comp");
            Toast.makeText(getActivity(), "Erreur de connexion, veuillez re-essayer", Toast.LENGTH_SHORT).show();
        }
    }

    private void putItents(final JSONObject object) {
        Intent i = new Intent(getContext(), Compartiment.class);
        try {
            i.putExtra("duration_number", object.getString("duration_number"));
            i.putExtra("drug_name", object.getString("drug_name"));
            i.putExtra("compartment_num", object.getString("compartment_num"));
            i.putExtra("list_pref", object.getString("list_pref"));
            i.putExtra("perso_hour", object.getString("perso_hour"));
            i.putExtra("duration_text", object.getString("duration_text"));
            i.putExtra("compartment_id", object.getString("compartment_id"));
            i.putExtra("days", object.getString("days"));
            i.putExtra("note", object.getString("note"));
            startActivity(i);
        } catch (JSONException e) {
            Log.e("JSON IS NULL", "putItents");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Gestion des Sections");
    }
}
