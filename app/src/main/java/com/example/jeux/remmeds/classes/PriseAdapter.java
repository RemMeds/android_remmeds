package com.example.jeux.remmeds.classes;

import com.example.jeux.remmeds.R;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PriseAdapter extends RecyclerView.Adapter<PriseAdapter.MyViewHolder>{

    private List<Prise> priseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nomMedicament;
        private ImageView compartiment;
        private TextView heurePrise;

        public MyViewHolder(View view) {
            super(view);
            nomMedicament = view.findViewById(R.id.accueil_recycler_nomMedicament);
            compartiment = view.findViewById(R.id.accueil_recycler_compartiment);
            heurePrise = view.findViewById(R.id.accueil_recycler_heurePrise);
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
        holder.nomMedicament.setText(prise.getNommedicament());
        holder.compartiment.setImageResource(prise.getCompartiment());
        holder.heurePrise.setText(prise.getHeurePrise());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date EndTimeRef = dateFormat.parse(prise.getHeurePrise());
            Date EndTimeGreen = addMinutes(30,EndTimeRef);
            Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));

            if (CurrentTime.before(EndTimeGreen) && CurrentTime.after(EndTimeRef)){
                holder.itemView.setBackgroundColor(Color.YELLOW);
            }
            else if(CurrentTime.after(EndTimeGreen)){
                holder.itemView.setBackgroundColor(Color.RED);
            }

        } catch (ParseException e) {
            Log.e("Parse exception","catched"+e);
        }
    }

    private static Date addMinutes(int minutes, Date beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        long curTimeInMs = beforeTime.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }

    @Override
    public int getItemCount() {
        return priseList.size();
    }
}
