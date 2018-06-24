package com.example.jeux.remmeds.classes;

import com.example.jeux.remmeds.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PriseAdapter extends RecyclerView.Adapter<PriseAdapter.MyViewHolder>{

    private List<Prise> priseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nomMedicament;
        private ImageView compartiment;
        private TextView heurePrise;

        public MyViewHolder(View view) {
            super(view);
            nomMedicament = view.findViewById(R.id.acceuil_recycler_nomMedicament);
            compartiment = view.findViewById(R.id.acceuil_recycler_compartiment);
            heurePrise = view.findViewById(R.id.acceuil_recycler_heurePrise);
        }
    }

    public PriseAdapter(List<Prise> priseList) {
        this.priseList = priseList;
    }
    //Creation de l'adapter qui permettera de construire la recyclerview
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //initialisation du view holder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prise_liste_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Prise prise= priseList.get(position);

        holder.nomMedicament.setText(prise.getNomMedicament());
        holder.compartiment.setImageResource(prise.getCompartiment());
        holder.heurePrise.setText(prise.getHeurePrise());
    }

    @Override
    public int getItemCount() {
        return priseList.size();
    }
}
