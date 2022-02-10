package com.appvisibility.apptravel002.ui.valiente.v_02.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Evento_eve;
import com.squareup.picasso.Picasso;
import java.util.List;

public class v02_00_eve_Adapter extends RecyclerView.Adapter<v02_00_eve_Adapter.ViewHolder>{

    private final List<Evento_eve> eventos;
    private final List<Actividad_act> actividades;
    private int posicionSeleccionada = -1;

    public v02_00_eve_Adapter(List<Evento_eve> eventos, List<Actividad_act> actividades) {
        this.eventos = eventos;
        this.actividades = actividades;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_v_02_eve_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
//        ViewHolder viewHolder = new v02_00_eve_ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int id_eve = eventos.get(position).getId_eve();
        String titulo_eve = eventos.get(position).getTitulo_eve();
        String foto_eve = eventos.get(position).getFoto_eve();
//        int foto_eve = eventos.get(position).getFoto_eve();
        String fechaidatru_eve = eventos.get(position).getFechaidatru_eve();
        String fechavueltatru_eve = eventos.get(position).getFechavueltatru_eve();
        String nivel_eve = eventos.get(position).getNivel_eve();
        String distancia_act = String.valueOf(actividades.get(id_eve).getDistancia_act());
        String desnivel_act = String.valueOf(actividades.get(id_eve).getDesnivel_act());
        String horas_act = String.valueOf(actividades.get(id_eve).getHoras_act());
        String transportetipo_eve = eventos.get(position).getTransportetipo_eve();
        String nparticipantes_eve = String.valueOf(eventos.get(position).getNparticipantes_eve());

        holder.txvTituloEve.setText(titulo_eve);
//        holder.imvFotoEve.setImageResource(foto_eve);
        Picasso.get().load(foto_eve).into(holder.imvFotoEve);
        holder.txvFechaIdaEve.setText(fechaidatru_eve);
        holder.txvFechaVueltaEve.setText(fechavueltatru_eve);
        holder.txvNivelEve.setText("Nivel: " +nivel_eve);
        holder.txvDistanciaAct.setText("Distancia: " +distancia_act);
        holder.txvDesnivelAct.setText("Desnivel: " +desnivel_act);
        holder.txvHorasAct.setText("Horas de marcha: " +horas_act);
        holder.txvTransporteEve.setText(transportetipo_eve);
        holder.txvNParticipantesEve.setText("Participantes: " +nparticipantes_eve);

        holder.cardEve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                posicionSeleccionada = position;
                notifyDataSetChanged();
                Evento_eve evento = eventos.get(posicionSeleccionada);

                Bundle result = new Bundle();
                result.putSerializable("evento", evento);
                Navigation.findNavController(v).navigate(R.id.nav_v03,result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvTituloEve;
        private ImageView imvFotoEve;
        private TextView txvFechaIdaEve;
        private TextView txvFechaVueltaEve;
        private TextView txvNivelEve;
        private TextView txvDistanciaAct;
        private TextView txvDesnivelAct;
        private TextView txvHorasAct;
        private TextView txvTransporteEve;
        private TextView txvNParticipantesEve;
        private CardView cardEve;

        public ViewHolder(View v) {
            super(v);
            this.txvTituloEve = v.findViewById(R.id.txvTituloEve);
//            this.imvFotoEve = v.findViewById(R.id.imvFotoEve);
            this.imvFotoEve = (ImageView) v.findViewById(R.id.imvFotoEve);
            this.txvFechaIdaEve = v.findViewById(R.id.txvFechaIdaTruEve);
            this.txvFechaVueltaEve = v.findViewById(R.id.txvFechaVueltaTruEve);
            this.txvNivelEve = v.findViewById(R.id.txvNivelEve);
            this.txvDistanciaAct = v.findViewById(R.id.txvDistanciaAct);
            this.txvDesnivelAct = v.findViewById(R.id.txvDesnivelAct);
            this.txvHorasAct = v.findViewById(R.id.txvHorasAct);
            this.txvTransporteEve = v.findViewById(R.id.txvTransporteTipoEve);
            this.txvNParticipantesEve = v.findViewById(R.id.txvNParticipantesEve);
            this.cardEve = v.findViewById(R.id.cardEve);
        }
    }
}