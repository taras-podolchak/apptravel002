package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

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
import com.appvisibility.apptravel002.ui.entities.Persona_per_test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v03_00_val_Adapter extends RecyclerView.Adapter<v03_00_val_Adapter.ViewHolder> {

    private final List<Persona_per_test> personas;
    Context context;

    public v03_00_val_Adapter(List<Persona_per_test> personas, Context context) {
        this.personas = personas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_v_03_act_card = LayoutInflater.from(context).inflate(R.layout.fragment_v_03_val_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view_v_03_act_card);
        return viewHolder;
    }

    @NonNull
    @Override
/**
 * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
 */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id_val_enProceso = personas.get(position).getId_per();
        String nombre_val = personas.get(position).getNombre_per();
        String apellido1_val = personas.get(position).getApellido1_per();
        String movil_val = personas.get(position).getMovil_per();
        String coche_val = personas.get(position).getCoche_per();
        String email_val = personas.get(position).getEmail_per();
        int plazas_libres_val = personas.get(position).getPlazaslibres_per();

        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Valientes/val0" + (++position) + "a.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        holder.v03_nombre_val_apellido1_val.setText(nombre_val + " " + apellido1_val);
        holder.v03_movil_val.setText("Tel: " + movil_val);
        holder.v03_coche_val.setText("Coche: " + coche_val);
        holder.v03_email_val.setText("Email: " + email_val);
        holder.v03_plazas_libres_val.setText("Plazas libres: " + plazas_libres_val);

//        int finalPosition = position;
        holder.v03_cdv_valiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("valienteParaC_05", id_val_enProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada==0) { //0 sesion cerrada / 1 valiente / 2 colaborador / 3 administrador
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
        private ImageView v03_fotopropia_val;
        private TextView v03_nombre_val_apellido1_val;
        private TextView v03_movil_val;
        private TextView v03_coche_val;
        private TextView v03_email_val;
        private TextView v03_plazas_libres_val;

        private CardView v03_cdv_valiente;

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_val = v.findViewById(R.id.v03_cdv_imv_fotopropia_val);
            this.v03_nombre_val_apellido1_val = v.findViewById(R.id.v03_cdv_txv_nombre_val_apellido1_val);
            this.v03_movil_val = v.findViewById(R.id.v03_cdv_txv_movil_val);
            this.v03_coche_val = v.findViewById(R.id.v03_cdv_txv_coche_val);
            this.v03_email_val = v.findViewById(R.id.v03_cdv_txv_email_val);
            this.v03_plazas_libres_val = v.findViewById(R.id.v03_cdv_txv_plazas_libres_val);

            this.v03_cdv_valiente = (CardView) v.findViewById(R.id.v03_cdv_valiente);
        }
    }
}