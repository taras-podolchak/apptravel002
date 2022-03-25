package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act;
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Evento_eve;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class v02_00_eve_Adapter extends RecyclerView.Adapter<v02_00_eve_Adapter.ViewHolder> {

    //private final List<Evento_eve> eventos;
   // private final List<Actividad_act> actividades;
    private int posicionSeleccionada /*= -1*/;
    private FirebaseFirestore firebaseFirestore;

    public v02_00_eve_Adapter(/*List<Evento_eve> eventos, *//*List<Actividad_act> actividades*/) {
        /* this.eventos = eventos;*/
        /*this.actividades = actividades;*/
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

        public ViewHolder(View view) {
            super(view);
            this.txvTituloEve = view.findViewById(R.id.txvTituloEve);
//            this.imvFotoEve = v.findViewById(R.id.imvFotoEve);
            this.imvFotoEve = (ImageView) view.findViewById(R.id.imvFotoEve);
            this.txvFechaIdaEve = view.findViewById(R.id.txvFechaIdaTruEve);
            this.txvFechaVueltaEve = view.findViewById(R.id.txvFechaVueltaTruEve);
            this.txvNivelEve = view.findViewById(R.id.txvNivelEve);
            this.txvDistanciaAct = view.findViewById(R.id.txvDistanciaAct);
            this.txvDesnivelAct = view.findViewById(R.id.txvDesnivelAct);
            this.txvHorasAct = view.findViewById(R.id.txvHorasAct);
            this.txvTransporteEve = view.findViewById(R.id.txvTransporteTipoEve);
            this.txvNParticipantesEve = view.findViewById(R.id.txvNParticipantesEve);
            this.cardEve = view.findViewById(R.id.cardEve);
        }
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
      /*  int id_eve = eventos.get(position).getId_eve();
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
        holder.txvNivelEve.setText("Nivel: " + nivel_eve);
        holder.txvDistanciaAct.setText("Distancia: " + distancia_act);
        holder.txvDesnivelAct.setText("Desnivel: " + desnivel_act);
        holder.txvHorasAct.setText("Horas de marcha: " + horas_act);
        holder.txvTransporteEve.setText(transportetipo_eve);
        holder.txvNParticipantesEve.setText("Participantes: " + nparticipantes_eve);*/

        String  posicionEventoString=Integer.toString(position);

        firebaseFirestore = FirebaseFirestore.getInstance();

        DocumentReference docRef = firebaseFirestore.collection("evento_eve").document(posicionEventoString);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        //cargamos el titulo
                        holder.txvTituloEve.setText(document.getString("titulo_eve"));
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/"+position+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(holder.imvFotoEve);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                //Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                                // Handle any errors
                            }
                        });

                        holder.txvFechaIdaEve.setText("Fecha ida: " +document.getString("fechaidatru_eve"));
                        holder.txvFechaVueltaEve.setText("Fecha vuelta: " +document.getString("fechavueltatru_eve"));
                        holder.txvNivelEve.setText("Nivel: " +document.getString( "nivel_eve"));
                        holder.txvDistanciaAct.setText("Distancia: " +document.getString( "distanciaidatru_eve"));
                        holder.txvTransporteEve.setText(document.getString("transportetipo_eve"));

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        holder.cardEve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                posicionSeleccionada = position;
                notifyDataSetChanged();
               // Evento_eve evento = eventos.get(posicionSeleccionada);

                Bundle result = new Bundle();
                //cambio el evento por la position
                result.putSerializable("evento", /*evento*/ position);
                Navigation.findNavController(v).navigate(R.id.nav_v03, result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6/*ventos.size()*/;
    }


}