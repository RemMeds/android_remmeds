package com.example.jeux.remmeds.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.activities.MainActivity;
import com.example.jeux.remmeds.classes.Prise;
import com.example.jeux.remmeds.classes.PriseAdapter;
import com.example.jeux.remmeds.classes.Profil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FragmentAccueil extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();
    private static PriseAdapter mAdapter = new PriseAdapter(priseListe);
    private static Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = this.getContext();
        RecyclerView mRecyclerView;
        final View acc = inflater.inflate(R.layout.fragment_accueil, container, false);

        mRecyclerView = acc.findViewById(R.id.prisesjour_recyclerView_layout_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem", "exception", e);
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return acc;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Accueil");
        if (priseListe.isEmpty()) {
            JSONObject data = MainActivity.getDoInBackground("http://212.73.217.202:15020/compartment/list_com/" + MainActivity.getUserID());
            try {
                final Profil p = new Profil();
                JSONArray compartmentArray = data.getJSONArray("compartment");
                generatePrises(compartmentArray, p);
                refreshRecyclerAccueil();
            } catch (java.lang.NullPointerException e) {
                Log.e("NullJson", "Accueil" + e);
            } catch (org.json.JSONException e) {
                Log.e("JsonException", "catched " + e);
                Toast.makeText(getActivity(), "Erreur de connexion, veuillez re-essayer", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private static void checkHistory(Prise prise) {
//        JSONObject objectHistory;
//        JSONArray arrayHistorique = null;
//        Profil p = new Profil();
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//            Date currentDay = dateFormat.parse(dateFormat.format(new Date()));
//            Date endTimeRef = dateFormat.parse(prise.getHeurePrise());
//            arrayHistorique = historiqueData.getJSONArray("historic");
//            for (int i = 0; i < arrayHistorique.length(); i++) {
//                objectHistory = arrayHistorique.getJSONObject(i);
//                if (prise.getNommedicament().equals(objectHistory.getString("drug_name")) && dateFormat.parse(dateFormat.format(new Date())).equals(currentDay)) {
//                    if (p.getBreakfastHour().equals(objectHistory.getString("time_slot"))) {
//                        prise.setIsTaken("1");
//                    } else if (p.getLunchHour().equals(objectHistory.getString("time_slot"))) {
//                        prise.setIsTaken("1");
//                    } else if (p.getDinnerHour().equals(objectHistory.getString("time_slot"))) {
//                        prise.setIsTaken("1");
//                    } else if (p.getBedHour().equals(objectHistory.getString("time_slot"))) {
//                        prise.setIsTaken("1");
//                    }
//
//                }
//            }
//        } catch (JSONException e) {
//            Log.e("JSON EXCEPTION CATCHED", "AccueilCheck" + e);
//        } catch (ParseException e) {
//            Log.e("Exception catched", "AccueilCheck" + e);
//        }
//    }


    private void generatePrises(JSONArray compartement, Profil profil) {
        JSONObject objectCompartement;

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
// full name form of the day
        String dayname = new SimpleDateFormat("EEEE", Locale.FRANCE).format(date.getTime());
        dayname = dayname.substring(0, 1).toUpperCase() + dayname.substring(1);
        for (int i = 0; i < 8; i++) {
            try {
                objectCompartement = compartement.getJSONObject(i);
                String nommedicament = objectCompartement.getString("drug_name");
                String dayperso = objectCompartement.getString("days");
                String listpref = objectCompartement.getString("list_pref");
                String heureperso = objectCompartement.getString("perso_hour");
                int compnum = Integer.parseInt(objectCompartement.getString("compartment_num"));
                int comp[] = {R.drawable.comp1, R.drawable.comp2, R.drawable.comp3, R.drawable.comp4, R.drawable.comp5, R.drawable.comp6, R.drawable.comp7, R.drawable.comp8};

                String[] days = dayperso.split(",");
                int check = 0;
                int j = 0;
                while (j < days.length && check < 1) {
                    if (days[j].equals(dayname)) {
                        check = 2;
                        String[] prefs = listpref.split(",");
                        for (String pref : prefs) {
                            switch (pref) {
                                case "Breakfast":
                                    Prise prise0 = new Prise(nommedicament, comp[compnum - 1], profil.getBreakfastHour(), "0");
                                    addPrise(prise0);
                                    break;
                                case "Lunch":
                                    Prise prise1 = new Prise(nommedicament, comp[compnum - 1], profil.getLunchHour(), "0");
                                    addPrise(prise1);
                                    break;
                                case "Dinner":
                                    Prise prise2 = new Prise(nommedicament, comp[compnum - 1], profil.getDinnerHour(), "0");
                                    addPrise(prise2);
                                    break;
                                case "Bedtime":
                                    Prise prise3 = new Prise(nommedicament, comp[compnum - 1], profil.getBedHour(), "0");
                                    addPrise(prise3);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    j++;
                }
                if (!heureperso.equals("") && !heureperso.equals("0")) {
                    Prise prise4 = new Prise(nommedicament, comp[compnum - 1], heureperso, "0");
                    addPrise(prise4);
                }
                sortPriseListe();
            } catch (JSONException e) {
                Log.e("JSon Null ?", "generatePrise " + e);
            }
        }
    }

    public static void refreshRecyclerAccueil() {
        mAdapter.notifyDataSetChanged();
    }

    public static void sortPriseListe() {
        Collections.sort(priseListe, new Comparator<Prise>() {
            public int compare(Prise obj1, Prise obj2) {
                // ## Ascending order
                return obj1.getHeurePrise().compareToIgnoreCase(obj2.getHeurePrise()); // To compare string values
                // return Integer.valueOf(obj1.empId).compareTo(obj2.empId); // To compare integer values
                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
            }
        });
    }

    public static void addPrise(Prise prise) {
        priseListe.add(prise);
    }

    public static Context getAccueilContext() {
        return mContext;
    }

    public static void destroyRecyclerAccueil() {
        if (!priseListe.isEmpty()) {
            priseListe.clear();
            refreshRecyclerAccueil();
        }
    }

    private static Date addMinutes(int minutes, Date beforeTime) {
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        long curTimeInMs = beforeTime.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }
}