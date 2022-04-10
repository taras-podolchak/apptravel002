package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;

import java.util.List;

public class v03_00_act_Adapter extends RecyclerView.Adapter<v03_00_act_Adapter.ViewHolder> {

    private final List<Actividad_act> actividades;
    Context context;

    public v03_00_act_Adapter( List<Actividad_act> actividades, Context context) {
        this.actividades = actividades;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_v_03_act_card = LayoutInflater.from(context).inflate(R.layout.fragment_v_03_act_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view_v_03_act_card);
        return viewHolder;
    }

    @NonNull
    @Override
/**
 * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
 */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre_act = actividades.get(position).getNombre_act();
        String actividadtipo_act = actividades.get(position).getActividadtipo_act();
        String fecha_act = actividades.get(position).getFecha_act();
        String nivel_act = actividades.get(position).getNivel_act();
        String distancia_act = String.valueOf(actividades.get(position).getDistancia_act());
        String desnivel_act = String.valueOf(actividades.get(position).getDesnivel_act());
        String horas_act = String.valueOf(actividades.get(position).getHoras_act());

        holder.v03_nombre_act.setText(nombre_act);
        holder.v03_actividadtipo_act.setText(actividadtipo_act);
        holder.v03_fecha_act.setText(fecha_act);
        holder.v03_nivel_act.setText("Nivel: " + nivel_act);
        holder.v03_distancia_act.setText("Distancia: " + String.valueOf(distancia_act));
        holder.v03_desnivel_act.setText("Desnivel: " + String.valueOf(desnivel_act));
        holder.v03_horas_act.setText("Horas de marcha: " + String.valueOf(horas_act));
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView v03_nombre_act;
        private TextView v03_actividadtipo_act;
        private TextView v03_fecha_act;
        private TextView v03_nivel_act;
        private TextView v03_distancia_act;
        private TextView v03_desnivel_act;
        private TextView v03_horas_act;

        public ViewHolder(View v) {
            super(v);
            this.v03_distancia_act = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this.v03_desnivel_act = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this.v03_horas_act = v.findViewById(R.id.v03_cdv_txv_horas_act);
            this.v03_nombre_act = v.findViewById(R.id.v03_cdv_txv_nombre_act);
            this.v03_actividadtipo_act = v.findViewById(R.id.v03_cdv_txv_actividadtipo_act);
            this.v03_fecha_act = v.findViewById(R.id.v03_cdv_txv_fecha_act);
            this.v03_nivel_act = v.findViewById(R.id.v03_cdv_txv_nivel_act);
        }
    }
}