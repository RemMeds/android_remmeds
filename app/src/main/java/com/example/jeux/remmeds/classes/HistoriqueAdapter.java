package com.example.jeux.remmeds.classes;

import com.example.jeux.remmeds.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.MyViewHolder> {
    private List<Prise> priseList;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nom;
        private TextView heurePrise;
        private TextView datePrise;
        private ImageView image;
        private TextView plageHorraire;


        private MyViewHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.historique_recycler_nomMedicament);
            heurePrise = view.findViewById(R.id.historique_recycler_heurePrise);
            datePrise = view.findViewById(R.id.historique_recycler_datePrise);
            image = view.findViewById(R.id.historique_recycler_compartiment);
            plageHorraire = view.findViewById(R.id.historique_plage_horraire);
        }

        @Override
        public void onClick(View v) {
            //Overided by recycler
        }
    }

    public HistoriqueAdapter(List<Prise> priseList) {
        this.priseList = priseList;
    }

    //Creation de l'adapter qui permettera de construire la recyclerview
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //initialisation du view holder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historique_prise_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Prise prise = priseList.get(position);
        holder.nom.setText(prise.getNommedicament());
        holder.heurePrise.setText(prise.getHeurePrise());
        holder.datePrise.setText(prise.getDatePrise());
        holder.plageHorraire.setText(prise.getPlageHorraire());
        holder.image.setImageResource(prise.getCompartiment());

        if (prise.getIsTaken().equals("1")){
            holder.itemView.setBackgroundResource(R.color.vert_transparent);
        }else{
            holder.itemView.setBackgroundResource(R.color.rouge_transparent);
        }

    }

    @Override
    public int getItemCount() {
        return priseList.size();
    }
}
