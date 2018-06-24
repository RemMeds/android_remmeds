package com.example.jeux.remmeds.fragments;

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
import android.widget.ImageView;


import com.example.jeux.remmeds.R;
import com.example.jeux.remmeds.classes.Prise;
import com.example.jeux.remmeds.classes.PriseAdapter;

import java.util.ArrayList;
import java.util.List;


public class FragmentAccueil extends Fragment {
    private static List<Prise> priseListe = new ArrayList<>();
    private static PriseAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        final View acc = inflater.inflate(R.layout.fragment_accueil, container, false);

        mRecyclerView = acc.findViewById(R.id.prisesjour_recyclerView_layout_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PriseAdapter(priseListe);

        Prise a = new Prise("Heure du coucher",R.drawable.ic_bedtime,"20h30");
        Prise b = new Prise("Doliprane",R.drawable.ic_pillbox,"15h00");
        Prise c = new Prise("Kebab",R.drawable.ic_lunch,"12h30");
        Prise d = new Prise("Smecta",R.drawable.ic_pillbox,"9h00");
        Prise u = new Prise("Smecta",R.drawable.ic_pillbox,"9h00");
        Prise f = new Prise("Smecta",R.drawable.ic_pillbox,"9h00");
        Prise g = new Prise("Smecta",R.drawable.ic_pillbox,"9h00");


        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem","exception",e);
        }

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        priseListe.add(a);
        priseListe.add(b);
        priseListe.add(c);
        priseListe.add(d);
        priseListe.add(u);
        priseListe.add(f);
        priseListe.add(g);
        mAdapter.notifyDataSetChanged();

        return acc;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Accueil");
    }
}