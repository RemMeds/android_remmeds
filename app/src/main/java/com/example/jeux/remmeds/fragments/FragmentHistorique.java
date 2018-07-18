package com.example.jeux.remmeds.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.HistoriqueAdapter;
import com.example.jeux.remmeds.classes.Prise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentHistorique extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();
    private static HistoriqueAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        final View acc = inflater.inflate(R.layout.fragment_historique, container, false);
        RecyclerView mRecyclerView;
        mRecyclerView = acc.findViewById(R.id.evenements_recyclerview_layout_historique);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HistoriqueAdapter(priseListe);
        mRecyclerView.setAdapter(mAdapter);

        try {
            JSONObject historiqueData = MainActivity.getDoInBackground("http://212.73.217.202:15020/historic/list_historic/" + MainActivity.getUserID());
            JSONArray array = historiqueData.getJSONArray("historic");
            refreshRecyclerHistorique();
            if (priseListe.isEmpty()) {
                generateHistoric(array);
                mAdapter.notifyDataSetChanged();
            }
        } catch (java.lang.NullPointerException e) {
            Log.e("NullJson", "Accueil" + e);
        } catch (org.json.JSONException e) {
            Log.e("JsonException", "catched " + e);
        }

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (
                Exception e) {
            Log.e("mRecyclerView.addDeco", "exception", e);
        }

        return acc;
    }

    public static void refreshRecyclerHistorique() {
        priseListe.clear();
        mAdapter.notifyDataSetChanged();
    }


    private void generateHistoric(JSONArray array) {
        String drugName;
        String day;
        String hour;
        String numComp;
        String isTaken;
        String plageHorraire;
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                drugName = object.getString("drug_name");
                day = object.getString("day");
                hour = object.getString("hour");
                numComp = object.getString("num_comp");
                isTaken = object.getString("respected");
                plageHorraire = object.getString("time_slot");
                Prise prise = new Prise(drugName, getPicture(numComp), hour, day, plageHorraire, isTaken);
                priseListe.add(prise);
            } catch (JSONException e) {
                Log.e("Exception Catched", "Fragment Historique " + e);
            } catch (java.lang.NullPointerException e) {
                Log.e("JSON Null", "Fragment Historique " + e);
            }
        }
        sortPriseListe();
    }

    public static void sortPriseListe() {
        Collections.sort(priseListe, new Comparator<Prise>() {
            public int compare(Prise obj1, Prise obj2) {
                // ## Ascending order
                return obj1.getDatePrise().compareToIgnoreCase(obj2.getDatePrise()); // To compare string values
                // return Integer.valueOf(obj1.empId).compareTo(obj2.empId); // To compare integer values
                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
            }
        });
    }

    private int getPicture(String numComp) {
        int compPicture;
        switch (numComp) {
            case "1":
                compPicture = R.drawable.comp1;
                return compPicture;
            case "2":
                compPicture = R.drawable.comp2;
                return compPicture;
            case "3":
                compPicture = R.drawable.comp3;
                return compPicture;
            case "4":
                compPicture = R.drawable.comp4;
                return compPicture;
            case "5":
                compPicture = R.drawable.comp5;
                return compPicture;
            case "6":
                compPicture = R.drawable.comp6;
                return compPicture;
            case "7":
                compPicture = R.drawable.comp7;
                return compPicture;
            case "8":
                compPicture = R.drawable.comp8;
                return compPicture;
            default:
                compPicture = R.drawable.comp8;
                return compPicture;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Historique");

    }
}