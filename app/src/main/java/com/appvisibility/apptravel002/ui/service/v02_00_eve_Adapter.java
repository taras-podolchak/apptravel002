package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.net.Uri;
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
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v02_00_eve_Adapter extends RecyclerView.Adapter<v02_00_eve_Adapter.ViewHolder> {


    private final List<Evento_eve> eventos;
    Context context;

    public v02_00_eve_Adapter(List<Evento_eve> eventos, Context context) {
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
        int id_eve = eventos.get(position).getId_eve();
        String titulo_eve = eventos.get(position).getTitulo_eve();
        String foto_eve = eventos.get(position).getFoto_eve();
        String nivel_eve = eventos.get(position).getNivel_eve();
        int distanciaidatru_eve = eventos.get(position).getDistanciaidatru_eve();
        int distanciavueltatru_eve = eventos.get(position).getDistanciavueltatru_eve();
        String fechaidatru_eve = eventos.get(position).getFechaidatru_eve();
        String fechavueltatru_eve = eventos.get(position).getFechavueltatru_eve();


/*
Descomentar cuando se resuelva el problema de id empezando en 0 (debería empezar en 1) en tablas evento_eve y actividad_act Firestore
        String distancia_act = String.valueOf(actividades.get(id_eve).getDistancia_act());
        String desnivel_act = String.valueOf(actividades.get(id_eve).getDesnivel_act());
        String horas_act = String.valueOf(actividades.get(id_eve).getHoras_act());
*/
        String transportetipo_eve = eventos.get(position).getTransportetipo_eve();
        String nparticipantes_eve = String.valueOf(eventos.get(position).getNparticipantes_eve());

        holder.txvTituloEve.setText(titulo_eve);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        storageRef.child("Eventos/" + foto_eve).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(holder.imvFotoEve);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                // Handle any errors
            }
        });
        holder.txvFechaIdaEve.setText(fechaidatru_eve);
        holder.txvFechaVueltaEve.setText(fechavueltatru_eve);
        holder.txvNivelEve.setText("Nivel: " + nivel_eve);
        holder.txvDistanciaAct_txvDesnivelAct.setText("Ida: " + distanciaidatru_eve + " - Vuelta: " + distanciavueltatru_eve);
        holder.txvTransporteEve.setText(transportetipo_eve);
        holder.txvNParticipantesEve.setText("Participantes: " + nparticipantes_eve);

        holder.cardEve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Evento_eve evento = eventos.get(position);

                Bundle bundle = new Bundle();
                bundle.putInt("evento", position);
                Navigation.findNavController(v).navigate(R.id.nav_v03);


                /*
                 * Notificamos cambios para que el contenedor se entere y refresque los datos
                 * La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
                 */
                notifyDataSetChanged();
            }
        });

/** Lanza la pantalla siguiente con el detalle del objeto seleccionado */
        /*if (posicionSeleccionada == position) {
            Evento_eve evento = eventos.get(posicionSeleccionada);
            Intent i = new Intent(holder.itemView.getContext(), V_03.class);
            i.putExtra("evento", evento);
            holder.itemView.getContext().startActivity(i);
        }*/
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    /**
     * Declara y asigna los campos de la Vista
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txvTituloEve;
        private ImageView imvFotoEve;
        private TextView txvFechaIdaEve;
        private TextView txvFechaVueltaEve;
        private TextView txvNivelEve;
        private TextView txvDistanciaAct_txvDesnivelAct;
        private TextView txvTransporteEve;
        private TextView txvNParticipantesEve;
        private CardView cardEve;

        public ViewHolder(View v) {
            super(v);
            this.txvTituloEve = v.findViewById(R.id.v02_crd_txv_titulo_eve);
//            this.imvFotoEve = v.findViewById(R.id.imvFotoEve);
            this.imvFotoEve = (ImageView) v.findViewById(R.id.v02_crd_imv_foto_eve);
            this.txvFechaIdaEve = v.findViewById(R.id.v02_crd_txv_fechaidatru_eve);
            this.txvFechaVueltaEve = v.findViewById(R.id.v02_crd_txv_fechavueltatru_eve);
            this.txvNivelEve = v.findViewById(R.id.v02_crd_txv_nivel_eve);
            this.txvDistanciaAct_txvDesnivelAct = v.findViewById(R.id.v02_crd_txv_distancia_act_desnivel_act);
            this.txvTransporteEve = v.findViewById(R.id.v02_crd_txv_transportetipo_eve);
            this.txvNParticipantesEve = v.findViewById(R.id.v02_crd_txv_nparticipantes_eve);

            this.cardEve = v.findViewById(R.id.v02_crd_eventos);
        }
    }
}