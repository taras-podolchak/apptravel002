package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v03_00_val_Adapter extends RecyclerView.Adapter<v03_00_val_Adapter.ViewHolder> {

    private final List<Valiente_val> valientes;
    Context context;

    public v03_00_val_Adapter(List<Valiente_val> valientes, Context context) {
        this.valientes = valientes;
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
        String nombre_val = valientes.get(position).getNombre_val();
        String apellido1_val = valientes.get(position).getApellido1_val();
        String movil_val = valientes.get(position).getMovil_val();
        String coche_val = valientes.get(position).getCoche_val();
        String email_val = valientes.get(position).getEmail_val();

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
        holder.v03_nombre_val_apellido1_val.setText(nombre_val+" "+apellido1_val);
        holder.v03_movil_val.setText("Tel: "+movil_val);
        holder.v03_coche_val.setText("Coche: " +coche_val);
        holder.v03_email_val.setText("Email: "+email_val);
    }

    @Override
    public int getItemCount() {
        return valientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView v03_fotopropia_val;
        private TextView v03_nombre_val_apellido1_val;
        private TextView v03_movil_val;
        private TextView v03_coche_val;
        private TextView v03_email_val;

        private CardView v03_cdv_valiente;

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_val = v.findViewById(R.id.v03_cdv_imv_fotopropia_val);
            this.v03_nombre_val_apellido1_val = v.findViewById(R.id.v03_cdv_txv_nombre_val_apellido1_val);
            this.v03_movil_val = v.findViewById(R.id.v03_cdv_txv_movil_val);
            this.v03_coche_val = v.findViewById(R.id.v03_cdv_txv_coche_val);
            this.v03_email_val = v.findViewById(R.id.v03_cdv_txv_email_val);

            this.v03_cdv_valiente = (CardView) v.findViewById(R.id.v03_cdv_valiente);
        }
    }
}