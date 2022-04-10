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


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        storageRef.child("Valientes/val0" + (++position) + "a.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
        holder.txvNombreVal_txvApellido1Val.setText(nombre_val+" "+apellido1_val);
        holder.txvMovilVal.setText("Tel: "+movil_val);
        holder.txvCocheVal.setText("Coche: " +coche_val);
        holder.txvEmailVal.setText("Email: "+email_val);

    }

    @Override
    public int getItemCount() {
        return valientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imvFotopropiaVal;
        private TextView txvApodoVal;
        private TextView txvNombreVal_txvApellido1Val;

        private TextView txvDireccionVal;
        private TextView txvCpostalVal;
        private TextView txvLocalidadVal;
        private TextView txvMovilVal;
        private TextView txvCocheVal;
        private TextView txvEmailVal;

        private CardView cardVal;

        public ViewHolder(View v) {
            super(v);

            imvFotopropiaVal = v.findViewById(R.id.v03_cdv_imv_fotopropia_val);
            txvNombreVal_txvApellido1Val = v.findViewById(R.id.v03_cdv_txv_nombre_val_apellido1_val);
            txvMovilVal = v.findViewById(R.id.v03_cdv_txv_movil_val);
            txvCocheVal = v.findViewById(R.id.v03_cdv_txv_coche_val);
            txvEmailVal = v.findViewById(R.id.v03_cdv_txv_email_val);

            cardVal = (CardView) v.findViewById(R.id.v03_cdv_valiente);
        }
    }
}