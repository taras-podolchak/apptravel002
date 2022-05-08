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
import com.appvisibility.apptravel002.ui.entities.Inscribir_evevalcoltpr;
import com.appvisibility.apptravel002.ui.entities.Transportepropio_tpr;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
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

public class v03_00_val_Adapter extends RecyclerView.Adapter<v03_00_val_Adapter.ViewHolder> {

    private List<Valiente_val> valientes = new ArrayList<>();
    private Transportepropio_tpr transportepropioEnProceso;
    private Inscribir_evevalcoltpr inscritoEnProceso;
    private List<Transportepropio_tpr> transportepropios = new ArrayList<>();
    private List<Inscribir_evevalcoltpr> inscritos  = new ArrayList<>();
    private int id_tpr;
    private int plazaslibres_tpr;
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    FirebaseStorage fbs = FirebaseStorage.getInstance();
    StorageReference str = fbs.getReference();
    Context context;
    int id_eve_bundle;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public v03_00_val_Adapter(List<Valiente_val> valientes, Context context, int id_eve_bundle) {
        this.valientes = valientes;
        this.context = context;
        this.id_eve_bundle = id_eve_bundle;

        Query query1 = fbf.collection("inscribir_evevalcoltpr").whereEqualTo("id_eve", id_eve_bundle);
        query1.addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            for (QueryDocumentSnapshot qds : snapshots) {
                inscritoEnProceso = qds.toObject(Inscribir_evevalcoltpr.class);
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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_v_03_val_card, parent, false);
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
        int id_val_enProceso = valientes.get(position).getId_val();

// https://stackoverflow.com/questions/70287093/cannot-create-map-from-two-connected-entities-in-java
        Map<Integer, Object> mapIns_Tpr = transportepropios.stream()
            .collect(Collectors.toMap(Transportepropio_tpr::getId_tpr, transportepropio -> transportepropio.getPlazaslibres_tpr()));

        String fotopropia_val = valientes.get(position).getFotopropia_val();
        String apodo_val = valientes.get(position).getApodo_val();
        String movil_val = valientes.get(position).getMovil_val();
        String email_val = valientes.get(position).getEmail_val();

        for(Inscribir_evevalcoltpr i: inscritos){
            if (i.getId_val() == id_val_enProceso){
                id_tpr = i.getId_tpr();
                plazaslibres_tpr = (int) mapIns_Tpr.get(i.getId_tpr());
            }
        }

        str.child("Valientes/" + fotopropia_val).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.v03_fotopropia_val);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                // Handle any errors
            }
        });
        holder.v03_apodo_val.setText(apodo_val);
        holder.v03_movil_val.setText("Tel: "+ movil_val);
        holder.v03_email_val.setText(email_val);

        holder.v03_cdv_valiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("valienteParaC_05", id_val_enProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada==1) {
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
                bundle.putInt("valienteParaC_05_tpr", id_val_enProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada==1) {
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
        return valientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView v03_fotopropia_val;
        private TextView v03_apodo_val;
        private TextView v03_movil_val;
        private TextView v03_coche_val;
        private TextView v03_email_val;
        private TextView v03_id_tpr;
        private ImageView v03_ico_coche;
        private TextView v03_plazaslibres_tpr;

        private CardView v03_cdv_valiente;
        private CardView v03_cdv_transportepropio;

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_val = v.findViewById(R.id.v03_cdv_imv_fotopropia_val);
            this.v03_apodo_val = v.findViewById(R.id.v03_cdv_txv_apodo_val);
            this.v03_movil_val = v.findViewById(R.id.v03_cdv_txv_movil_val);
            this.v03_email_val = v.findViewById(R.id.v03_cdv_txv_email_val);
            this.v03_id_tpr = v.findViewById(R.id.v03_cdv_txv_id_tpr);
            this.v03_ico_coche = v.findViewById(R.id.v03_cdv_imv_ico_coche);
            if (String.valueOf(v03_plazaslibres_tpr).equals("Plazas libres: -1")){
                this.v03_ico_coche.setImageResource(R.drawable.ico_coche_rojo);
            } else {
                this.v03_ico_coche.setImageResource(R.drawable.ico_coche_verde);
            }
            this.v03_plazaslibres_tpr = v.findViewById(R.id.v03_cdv_txv_plazaslibres_tpr);

            this.v03_cdv_valiente = (CardView) v.findViewById(R.id.v03_cdv_valiente);
            this.v03_cdv_transportepropio = (CardView) v.findViewById(R.id.v03_cdv_valiente);
        }
    }
}