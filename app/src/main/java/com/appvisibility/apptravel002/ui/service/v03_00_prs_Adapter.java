package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprstpr;
import com.appvisibility.apptravel002.ui.entities.Transportepropio_tpr;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class v03_00_prs_Adapter extends RecyclerView.Adapter<v03_00_prs_Adapter.ViewHolder> {

    private List<Persona_prs> personas = new ArrayList<>();
    private Transportepropio_tpr transportepropioEnProceso;
    private Inscribir_eveprstpr inscritoEnProceso;
    private List<Transportepropio_tpr> transportepropios = new ArrayList<>();
    private List<Inscribir_eveprstpr> inscritos  = new ArrayList<>();
    private int id_tpr;
    private int plazaslibres_tpr;
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    FirebaseStorage fbs = FirebaseStorage.getInstance();
    StorageReference str = fbs.getReference();
    Context context;
    int id_eve_bundle;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public v03_00_prs_Adapter(List<Persona_prs> personas, Context context, int id_eve_bundle) {
        this.personas = personas;
        this.context = context;
        this.id_eve_bundle = id_eve_bundle;

        Query query1 = fbf.collection("inscribir_eveprstpr").whereEqualTo("id_eve", id_eve_bundle);
        query1.addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            for (QueryDocumentSnapshot qds : snapshots) {
                inscritoEnProceso = qds.toObject(Inscribir_eveprstpr.class);
                inscritos.add(inscritoEnProceso);
            }
            id_tpr = inscritoEnProceso.getId_tpr();
        });

        Query query2 = fbf.collection("transportepropio_tpr");
        query2.addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            for (QueryDocumentSnapshot qds : snapshots) {
                transportepropioEnProceso = qds.toObject(Transportepropio_tpr.class);
                transportepropios.add(transportepropioEnProceso);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_v_03_prs_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
/**
 * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
 */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id_prs_enProceso = personas.get(position).getId_prs();

// https://stackoverflow.com/questions/70287093/cannot-create-map-from-two-connected-entities-in-java
        Map<Integer, Object> mapIns_Tpr = transportepropios.stream()
            .collect(Collectors.toMap(Transportepropio_tpr::getId_tpr, transportepropio -> transportepropio.getPlazaslibres_tpr()));

        String fotopropia_prs = personas.get(position).getFotopropia_prs();
        String apodo_prs = personas.get(position).getApodo_prs();
        String movil_prs = personas.get(position).getMovil_prs();
        String email_prs = personas.get(position).getEmail_prs();

        for(Inscribir_eveprstpr i: inscritos){
            if (i.getId_prs() == id_prs_enProceso){
                id_tpr = i.getId_tpr();
                plazaslibres_tpr = (int) mapIns_Tpr.get(i.getId_tpr());
            }
        }

        str.child("Valientes/" + fotopropia_prs).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.v03_fotopropia_prs);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                // Handle any errors
            }
        });
        holder.v03_apodo_prs.setText(apodo_prs);
        holder.v03_movil_prs.setText("Tel: "+ movil_prs);
        holder.v03_email_prs.setText(email_prs);

        holder.v03_cdv_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("personaParaC_05", id_prs_enProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada == 0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundle);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundle);
                }
                /*
                 * Notificamos cambios para que el contenedor se entere y refresque los datos
                 * La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
                 */
                notifyDataSetChanged();
            }
        });

        holder.v03_id_tpr.setText("id_tpr: "+ id_tpr);
        holder.v03_plazaslibres_tpr.setText("Plazas libres: "+ plazaslibres_tpr);

        holder.v03_cdv_transportepropio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
//                bundle.putInt("personaParaC_05_tpr", id_prs_enProceso);
                bundle.putInt("personaParaC_05", id_prs_enProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada == 0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundle);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundle);
                }
                /*
                 * Notificamos cambios para que el contenedor se entere y refresque los datos
                 * La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
                 */
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView v03_fotopropia_prs;
        private TextView v03_apodo_prs;
        private TextView v03_movil_prs;
        private TextView v03_email_prs;
        private TextView v03_id_tpr;
        private ImageView v03_ico_coche;
        private TextView v03_plazaslibres_tpr;

        private CardView v03_cdv_persona;
        private CardView v03_cdv_transportepropio;

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_prs = v.findViewById(R.id.v03_cdv_imv_fotopropia_prs);
            this.v03_apodo_prs = v.findViewById(R.id.v03_cdv_txv_apodo_prs);
            this.v03_movil_prs = v.findViewById(R.id.v03_cdv_txv_movil_prs);
            this.v03_email_prs = v.findViewById(R.id.v03_cdv_txv_id_tpr);
            this.v03_id_tpr = v.findViewById(R.id.v03_cdv_txv_email_prs);
            this.v03_ico_coche = v.findViewById(R.id.v03_cdv_imv_ico_coche);
            if (String.valueOf(v03_plazaslibres_tpr).equals("Plazas libres: -1")){
                this.v03_ico_coche.setImageResource(R.drawable.ico_coche_rojo);
            } else {
                this.v03_ico_coche.setImageResource(R.drawable.ico_coche_verde);
            }
            this.v03_plazaslibres_tpr = v.findViewById(R.id.v03_cdv_txv_plazaslibres_tpr);

            this.v03_cdv_persona = (CardView) v.findViewById(R.id.v03_cdv_persona);
            this.v03_cdv_transportepropio = (CardView) v.findViewById(R.id.v03_cdv_persona);
        }
    }
}