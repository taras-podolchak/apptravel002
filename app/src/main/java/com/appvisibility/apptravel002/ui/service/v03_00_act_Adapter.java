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

        holder.txvNombreAct.setText(nombre_act);
        holder.txvActividadTipoAct.setText(actividadtipo_act);
        holder.txvFechaAct.setText(fecha_act);
        holder.txvNivelAct.setText("Nivel: " + nivel_act);
        holder.txvDistanciaAct.setText("Distancia: " + String.valueOf(distancia_act));
        holder.txvDesnivelAct.setText("Desnivel: " + String.valueOf(desnivel_act));
        holder.txvHorasAct.setText("Horas de marcha: " + String.valueOf(horas_act));
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txvNombreAct;
        private TextView txvActividadTipoAct;
        private TextView txvFechaAct;
        private TextView txvNivelAct;
        private TextView txvDistanciaAct;
        private TextView txvDesnivelAct;
        private TextView txvHorasAct;

        public ViewHolder(View v) {
            super(v);

            this.txvDistanciaAct = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this.txvDesnivelAct = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this.txvHorasAct = v.findViewById(R.id.v03_cdv_txv_horas_act);
            this. txvNombreAct = v.findViewById(R.id.v03_cdv_txv_nombre_act);
            this.txvActividadTipoAct = v.findViewById(R.id.v03_cdv_txv_actividadtipo_act);
            this. txvFechaAct = v.findViewById(R.id.v03_cdv_txv_fecha_act);
            this. txvNivelAct = v.findViewById(R.id.v03_cdv_txv_nivel_act);
            this. txvDistanciaAct = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this. txvDesnivelAct = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this. txvHorasAct = v.findViewById(R.id.v03_cdv_txv_horas_act);
        }
    }
}