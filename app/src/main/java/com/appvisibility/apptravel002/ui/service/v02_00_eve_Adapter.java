package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Evento_eve_test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v02_00_eve_Adapter extends RecyclerView.Adapter<v02_00_eve_Adapter.ViewHolder> {

    private final List<Evento_eve_test> eventos;
    Context context;

    public v02_00_eve_Adapter(List<Evento_eve_test> eventos, Context context) {
        this.eventos = eventos;
        this.context = context;
    }

    /**
     * Proporciona la forma: Devuelve un ViewHolder con el layout que pretendemos mostrar sobre el RecyclerView para ello utiliza el xml que lo define: lista_datos.xml
     * EOB: Construye la pantalla principal de la que derivarán otras pantallas secundarias
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_v_02_eve_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /**
     * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
     * EOB: Asigna a los campos de la Vista los datos del objeto seleccionado (position) en el Modelo
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id_eve_enProceso = eventos.get(position).getId_eve();
        String titulo_eve = eventos.get(position).getTitulo_eve();
        String foto_eve = eventos.get(position).getFoto_eve();
        String nivel_eve = eventos.get(position).getNivel_eve();
        String estado_eve = eventos.get(position).getEstado_eve();
        String fechaidatru_eve = eventos.get(position).getFechaidatru_eve();
        String fechavueltatru_eve = eventos.get(position).getFechavueltatru_eve();
        String transportetipo_eve = eventos.get(position).getTransportetipo_eve();
        int nparticipantes_eve = eventos.get(position).getNparticipantes_eve();
        Evento_eve eventoEnProceso = new Evento_eve(id_eve_enProceso, titulo_eve, nparticipantes_eve, foto_eve, nivel_eve, estado_eve, transportetipo_eve, fechaidatru_eve, fechavueltatru_eve);

        holder.v02_titulo_eve.setText(titulo_eve);
        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Eventos/" + foto_eve).getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(holder.v02_foto_eve)).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                // Handle any errors
            }
        });
        holder.v02_fechaidatru_eve.setText(fechaidatru_eve);
        holder.v02_fechavueltatru_eve.setText(fechavueltatru_eve);
        holder.v02_nivel_eve.setText("Nivel: " + nivel_eve);
        holder.v02_estado_eve.setText("Estado: " + estado_eve);
        holder.v02_transportetipo_eve.setText(transportetipo_eve);
        holder.v02_nparticipantes_eve.setText("Participantes: " + nparticipantes_eve);

        holder.v02_cdv_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// https://stackoverflow.com/questions/42266436/passing-objects-between-fragments
                Bundle bundleEvento = new Bundle();
                bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);
//                bundleEvento.putInt("eventoParaV_03", id_eve_enProceso);
                Navigation.findNavController(v).navigate(R.id.nav_v03, bundleEvento);

        /*
         * Notificamos cambios para que el contenedor se entere y refresque los datos
         * La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
         */

/** Lanza la pantalla siguiente con el detalle del objeto seleccionado */
/*                if (posicionSeleccionada == position) {
                    Intent intent = new Intent(holder.itemView.getContext(), V_03.class);
                    intent.putExtra("evento", eventoEnProceso);
                    holder.itemView.getContext().startActivity(intent);
                }
 */
//                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    /**
     * Declara y asigna los campos de la Vista
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView v02_titulo_eve;
        private ImageView v02_foto_eve;
        private TextView v02_fechaidatru_eve;
        private TextView v02_fechavueltatru_eve;
        private TextView v02_nivel_eve;
        private TextView v02_estado_eve;
        private TextView v02_transportetipo_eve;
        private TextView v02_nparticipantes_eve;
        private CardView v02_cdv_eventos;

        public ViewHolder(View v) {
            super(v);
            this.v02_titulo_eve = v.findViewById(R.id.v02_crd_txv_titulo_eve);
//            this.imvFotoEve = v.findViewById(R.id.imvFotoEve);
            this.v02_foto_eve = (ImageView) v.findViewById(R.id.v02_crd_imv_foto_eve);
            this.v02_fechaidatru_eve = v.findViewById(R.id.v02_crd_txv_fechaidatru_eve);
            this.v02_fechavueltatru_eve = v.findViewById(R.id.v02_crd_txv_fechavueltatru_eve);
            this.v02_nivel_eve = v.findViewById(R.id.v02_crd_txv_nivel_eve);
            this.v02_estado_eve = v.findViewById(R.id.v02_crd_txv_estado_eve);
            this.v02_transportetipo_eve = v.findViewById(R.id.v02_crd_txv_transportetipo_eve);
            this.v02_nparticipantes_eve = v.findViewById(R.id.v02_crd_txv_nparticipantes_eve);

            this.v02_cdv_eventos = v.findViewById(R.id.v02_cdv_eventos);
        }
    }
}