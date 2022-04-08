package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v05_01_val_Adapter extends RecyclerView.Adapter<v05_01_val_Adapter.ViewHolder> {

    private List<Valiente_val> valientes;
    Context context;

    public v05_01_val_Adapter(List<Valiente_val> valientes, Context mContext) {
        this.valientes = valientes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_v_05_val_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @NonNull
    @Override
/**
 * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
 */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int id_val = valientes.get(position).getId_val();
        String apodo_val = valientes.get(position).getApodo_val();
        String nombre_val = valientes.get(position).getNombre_val();
        String apellido1_val = valientes.get(position).getApellido1_val();
        String movil_val = valientes.get(position).getMovil_val();
        String coche_val = valientes.get(position).getCoche_val();
        String email_val = valientes.get(position).getEmail_val();
        String fotopropia_val = valientes.get(position).getFotopropia_val();

        holder.txvApodoVal.setText(apodo_val);
        holder.txvNombreVal.setText(nombre_val);
        holder.txvApellido1Val.setText(apellido1_val);
        holder.txvMovilVal.setText(movil_val);
        holder.txvCocheVal.setText(coche_val);
        holder.txvEmailVal.setText(email_val);
//        Picasso.get().load(fotopropia_val).into(holder.imvFotopropiaVal);
//        holder.imvEvento2.setImageResource(holder.imvEvento2);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        storageRef.child("Valientes/"+fotopropia_val).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.imvFotopropiaVal);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                // Handle any errors
            }
        });
    }

    @Override
    public int getItemCount() {
        return valientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imvFotopropiaVal;
        private TextView txvApodoVal;
        private TextView txvNombreVal;
        private TextView txvApellido1Val;
        private TextView txvMovilVal;
        private TextView txvCocheVal;
        private TextView txvEmailVal;

        public ViewHolder(View v) {
            super(v);

            this.imvFotopropiaVal = v.findViewById(R.id.imvFotopropiaVal);
            this.txvApodoVal = v.findViewById(R.id.txvApodoVal);
            this.txvNombreVal = v.findViewById(R.id.txvNombreVal);
            this.txvApellido1Val = v.findViewById(R.id.txvApellido1Val);
            this.txvMovilVal = v.findViewById(R.id.txvMovilVal);
            this.txvCocheVal = v.findViewById(R.id.txvCocheVal);
            this.txvEmailVal = v.findViewById(R.id.txvEmailVal);
        }
    }

}