package com.appvisibility.apptravel002.ui.valiente.v_02.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act;

import java.util.List;

public class v03_00_act_Adapter extends RecyclerView.Adapter<v03_00_act_Adapter.ViewHolder> {

    private List<Actividad_act> actividades;
    int posicionseleccionada = -1;

    public v03_00_act_Adapter(List<Actividad_act> actividades) {
        this.actividades = actividades;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_v_03_act_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    /**
     * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
     */
    public void onBindViewHolder(ViewHolder holder, int position) {

        String nombre_act = actividades.get(position).getNombre_act();
        String actividadtipo_act = actividades.get(position).getActividadtipo_act();
        String fecha_act = actividades.get(position).getFecha_act();
        String nivel_act = actividades.get(position).getNivel_act();
        int distancia_act = actividades.get(position).getDistancia_act();
        int desnivel_act = actividades.get(position).getDesnivel_act();
        int horas_act = actividades.get(position).getHoras_act();

        holder.txvNombreAct.setText(nombre_act);
        holder.txvActividadTipoAct.setText(actividadtipo_act);
        holder.txvFechaAct.setText(fecha_act);
        holder.txvNivelAct.setText("Nivel: " + nivel_act);
        holder.txvDistanciaAct.setText("Distancia: " + String.valueOf(distancia_act));
        holder.txvDesnivelAct.setText("Desnivel: " + String.valueOf(desnivel_act));
        holder.txvHorasAct.setText("Horas de marcha: " + String.valueOf(horas_act));

        holder.cardAct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                posicionseleccionada = position;
                //Notificamos cambios para que el contenedor se entere y refresque los datos
                // La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
//                notifyDataSetChanged();
//                System.out.println(holder.txtnombre.getText().toString());
            }
        });
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

        private CardView cardAct;

        public ViewHolder(View v) {
            super(v);

            txvNombreAct = v.findViewById(R.id.txvNombreAct);
            txvActividadTipoAct = v.findViewById(R.id.txvActividadTipoAct);
            txvFechaAct = v.findViewById(R.id.txvFechaAct);
            txvNivelAct = v.findViewById(R.id.txvNivelAct);
            txvDistanciaAct = v.findViewById(R.id.txvDistanciaAct);
            txvDesnivelAct = v.findViewById(R.id.txvDesnivelAct);
            txvHorasAct = v.findViewById(R.id.txvHorasAct);

            cardAct = (CardView) v.findViewById(R.id.cardAct);
        }
    }
}