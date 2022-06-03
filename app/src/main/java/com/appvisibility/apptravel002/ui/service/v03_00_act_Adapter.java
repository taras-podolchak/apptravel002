package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.A_add_eve;
import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;

import java.util.List;

public class v03_00_act_Adapter extends RecyclerView.Adapter<v03_00_act_Adapter.ViewHolder> {

    private final List<Actividad_act> actividades;
    private Context context;
    private int accion;
    private Bundle bundleActividad = new Bundle();

    //accion es: 0 si lo pulsas en V03 y 1 si lo pulsas en A_add_eve
    public v03_00_act_Adapter(List<Actividad_act> actividades, Context context, int accion) {
        this.actividades = actividades;
        this.context = context;
        this.accion = accion;

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
        int distancia_act = actividades.get(position).getDistancia_act();
        int desnivel_act = actividades.get(position).getDesnivel_act();
        int horas_act = actividades.get(position).getHoras_act();
        String wikiloc_act = actividades.get(position).getWikiloc_act();

        Actividad_act actividadEnProceso = new Actividad_act(nombre_act, actividadtipo_act, fecha_act, nivel_act, distancia_act, desnivel_act, horas_act, wikiloc_act);

        holder.v03_nombre_act.setText(nombre_act);
        holder.v03_actividadtipo_act.setText(actividadtipo_act);
        holder.v03_fecha_act.setText(fecha_act);
        holder.v03_nivel_act.setText("Nivel: " + nivel_act);
        holder.v03_distancia_act.setText("Distancia: " + distancia_act);
        holder.v03_desnivel_act.setText("Desnivel: " + desnivel_act);
        holder.v03_horas_act.setText("Horas de marcha: " + horas_act);
        holder.v03_cdv_actividad.setOnClickListener(view -> {
            if (accion == view.getResources().getInteger(R.integer.accion_a_web)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(wikiloc_act));
                context.startActivity(intent);
            } else if (accion == view.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
                bundleActividad.putSerializable("eventoPara_a_add_eve", new Evento_eve());
                bundleActividad.putSerializable("actividadPara_a_add_eve", actividadEnProceso);
                Navigation.findNavController(view).navigate(R.id.nav_a_create_eve, bundleActividad);
            }
        });
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
        private CardView v03_cdv_actividad;

        public ViewHolder(View v) {
            super(v);
            this.v03_distancia_act = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this.v03_desnivel_act = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this.v03_horas_act = v.findViewById(R.id.v03_cdv_txv_horas_act);
            this.v03_nombre_act = v.findViewById(R.id.v03_cdv_txv_nombre_act);
            this.v03_actividadtipo_act = v.findViewById(R.id.v03_cdv_txv_actividadtipo_act);
            this.v03_fecha_act = v.findViewById(R.id.v03_cdv_txv_fecha_act);
            this.v03_nivel_act = v.findViewById(R.id.v03_cdv_txv_nivel_act);

            this.v03_cdv_actividad = v.findViewById(R.id.v03_cdv_actividad);
        }
    }
}