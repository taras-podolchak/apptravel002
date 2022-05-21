package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

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
//        int id_prs_enProceso = personas.get(position).getId_prs();

        int	id_prs_enProceso = personas.get(position).getId_prs();
        String  apodo_prs = personas.get(position).getApodo_prs();
        String	dni_prs = personas.get(position).getDni_prs();
        String	nombre_prs = personas.get(position).getNombre_prs();
        String	apellido1_prs = personas.get(position).getApellido1_prs();
        String	apellido2_prs = personas.get(position).getApellido2_prs();
        String	direccion_prs = personas.get(position).getDireccion_prs();
        String	localidad_prs = personas.get(position).getLocalidad_prs();
        String	cpostal_prs = personas.get(position).getCpostal_prs();
        String  movil_prs = personas.get(position).getMovil_prs();
        String  email_prs = personas.get(position).getEmail_prs();
        String  fotopropia_prs = personas.get(position).getFotopropia_prs();
        String	fechaalta_prs = personas.get(position).getFechaalta_prs();
        String	fechabaja_prs = personas.get(position).getFechabaja_prs();
        String	contacto1cargo_prs = personas.get(position).getContacto1Cargo_prs();
        String	contacto1movil_prs = personas.get(position).getContacto1Movil_prs();
        String	contacto2cargo_prs = personas.get(position).getContacto2Cargo_prs();
        String	contacto2movil_prs = personas.get(position).getContacto2Movil_prs();
        int	fiabilidadpre_prs = personas.get(position).getFiabilidadpre_prs();
        int	valoracionorgpre_prs = personas.get(position).getValoracionorgpre_prs();
        int	antiguedadpre_prs = personas.get(position).getAntiguedadpre_prs();
        int	volumencomprapre_prs = personas.get(position).getVolumencomprapre_prs();
        int	cochepre_prs = personas.get(position).getCochepre_prs();
        int	nrelacionespre_prs = personas.get(position).getNrelacionespre_prs();
        String	coche_prs = personas.get(position).getCoche_prs();
        int	nps01_prs = personas.get(position).getNps01_prs();
        String	nps01fecha_prs = personas.get(position).getNps01Fecha_prs();
        int	nps02_prs = personas.get(position).getNps02_prs();
        String	nps02fecha_prs = personas.get(position).getNps02Fecha_prs();
        int	nps03_prs = personas.get(position).getNps03_prs();
        String	nps03fecha_prs = personas.get(position).getNps03Fecha_prs();
        boolean	condicioneslegales_prs = personas.get(position).getCondicioneslegales_prs();

        Persona_prs personaEnProceso = new Persona_prs(id_prs_enProceso, apodo_prs, dni_prs, nombre_prs, apellido1_prs, apellido2_prs, direccion_prs, localidad_prs, cpostal_prs, movil_prs, email_prs, fotopropia_prs, fechaalta_prs, fechabaja_prs, contacto1cargo_prs, contacto1movil_prs, contacto2cargo_prs, contacto2movil_prs, fiabilidadpre_prs, valoracionorgpre_prs, antiguedadpre_prs, volumencomprapre_prs, cochepre_prs, nrelacionespre_prs, coche_prs, nps01_prs, nps01fecha_prs, nps02_prs, nps02fecha_prs, nps03_prs, nps03fecha_prs, condicioneslegales_prs);

// https://stackoverflow.com/questions/70287093/cannot-create-map-from-two-connected-entities-in-java
        Map<Integer, Object> mapIns_Tpr = transportepropios.stream()
                .collect(Collectors.toMap(Transportepropio_tpr::getId_tpr, transportepropio -> transportepropio.getPlazaslibres_tpr()));

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
                Bundle bundlePersona = new Bundle();
                bundlePersona.putSerializable("personaParaC_05", personaEnProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada == 0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
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
//                Bundle bundleTransportepropio = new Bundle();
//                bundle.putInt("personaParaC_05_tpr", id_prs_enProceso);
//                bundleTransportepropio.putInt("personaParaC_05", id_prs_enProceso);
                Bundle bundlePersona = new Bundle();
                bundlePersona.putSerializable("personaParaC_05", personaEnProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada == 0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
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