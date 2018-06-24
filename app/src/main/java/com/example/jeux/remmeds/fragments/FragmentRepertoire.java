package com.example.jeux.remmeds.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeux.remmeds.activities.Ajoutcontact;
import com.example.jeux.remmeds.classes.Contact;
import com.example.jeux.remmeds.classes.ContactAdapter;
import com.example.jeux.remmeds.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class FragmentRepertoire extends Fragment {
    private static List<Contact> contactList = new ArrayList<>();
    private static ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        RecyclerView mRecyclerView;
        Button addButton;
        final View rep = inflater.inflate(R.layout.fragment_repertoire, container, false);

        mRecyclerView = rep.findViewById(R.id.rep_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ContactAdapter(contactList);

        addButton = rep.findViewById(R.id.ajouter_button_layout_repertoire);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Ajoutcontact.class);
                startActivity(i);
            }
        });

        try {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        } catch (Exception e) {
            Log.e("mRecyclerView.addItem","exception",e);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return rep;

    }

    public static void addItem(String nom, String prenom, String adresse, String numero){
        Contact contact = new Contact(nom,prenom,adresse,numero);
        contactList.add(contact);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        try {
            getActivity().setTitle("Repertoire");
        } catch (Exception e) {
            Log.e("getActivity.setTitle","exception",e);
        }
    }
}