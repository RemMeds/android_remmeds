package com.example.jeux.remmeds.classes;

import com.example.jeux.remmeds.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private List<Contact> contactList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nom;
        private TextView prenom;
        private TextView adresse;
        private TextView numero;

        public MyViewHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.rep_recycler_nom);
            prenom = view.findViewById(R.id.rep_recycler_prenom);
            adresse = view.findViewById(R.id.rep_recycler_adresse);
            numero = view.findViewById(R.id.rep_recycler_numero);
        }
    }

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }
    //Creation de l'adapter qui permettera de construire la recyclerview
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //initialisation du view holder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.nom.setText(contact.getNom());
        holder.prenom.setText(contact.getPrenom());
        holder.adresse.setText(contact.getAdresse());
        holder.numero.setText(contact.getNumero());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
