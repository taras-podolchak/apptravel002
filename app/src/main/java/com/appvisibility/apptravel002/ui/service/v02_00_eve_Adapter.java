package com.appvisibility.apptravel002.ui.service;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class v02_00_eve_Adapter extends RecyclerView.Adapter<v02_00_eve_Adapter.ViewHolder> {

    private List<Evento_eve> eventos;
    private Context context;
    private int accion;
    private Bundle bundleEvento = new Bundle();
    private View view_A_add_eve;
    private int idEve;

    private EditText a_add_eve_id_eve;
    private EditText a_add_eve_titulo_eve;
    private ImageView a_add_eve_foto_eve;
    private EditText a_add_eve_fechaidatru_eve;
    private EditText a_add_eve_fechavueltatru_eve;
    private EditText a_add_eve_nivel_eve;
    private EditText a_add_eve_salidaidatru_eve;
    private EditText a_add_eve_llegadaidatru_eve;
    private EditText a_add_eve_distanciaidatru_eve;
    private EditText a_add_eve_distanciavueltatru_eve;
    private EditText a_add_eve_llegadacoordenadastru_eve;
    private EditText a_add_eve_salidacoordenadastru_eve;
    private EditText a_add_eve_llegadavueltatru_eve;
    private EditText a_add_eve_precio_eve;
    private EditText a_add_eve_transportetipo_eve;

    // Acceso a datos
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private FirebaseStorage fbs = FirebaseStorage.getInstance();
    private StorageReference str = fbs.getReference();

    public int getIdEve() {
        return idEve;
    }

    public void setIdEve(int idEve) {
        this.idEve = idEve;
    }

    public v02_00_eve_Adapter() {
    }

    //accion es: 0 si lo pulsas en V02 y 1 si lo pulsas en A_add_eve
    //view_A_add_eve es: la View de A_add_eve para tener acceso a sus campos
    public v02_00_eve_Adapter(List<Evento_eve> eventos, Context context, int accion, @Nullable View view_A_add_eve) {
        this.eventos = eventos;
        this.context = context;
        this.accion = accion;
        this.view_A_add_eve = view_A_add_eve;
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

        Evento_eve eveEnProceso = new Evento_eve();
        eveEnProceso.setId_eve(eventos.get(position).getId_eve());
        eveEnProceso.setTitulo_eve(eventos.get(position).getTitulo_eve());
        eveEnProceso.setFoto_eve(eventos.get(position).getFoto_eve());
        eveEnProceso.setFechaidatru_eve(eventos.get(position).getFechaidatru_eve());
        eveEnProceso.setFechavueltatru_eve(eventos.get(position).getFechavueltatru_eve());
        eveEnProceso.setNivel_eve(eventos.get(position).getNivel_eve());
        eveEnProceso.setSalidaidatru_eve(eventos.get(position).getSalidaidatru_eve());
        eveEnProceso.setLlegadaidatru_eve(eventos.get(position).getLlegadaidatru_eve());
        eveEnProceso.setDistanciaidatru_eve(eventos.get(position).getDistanciaidatru_eve());
        eveEnProceso.setDistanciavueltatru_eve(eventos.get(position).getDistanciavueltatru_eve());
        eveEnProceso.setLlegadacoordenadastru_eve(eventos.get(position).getLlegadacoordenadastru_eve());
        eveEnProceso.setSalidacoordenadastru_eve(eventos.get(position).getSalidacoordenadastru_eve());
        eveEnProceso.setLlegadavueltatru_eve(eventos.get(position).getLlegadavueltatru_eve());
        eveEnProceso.setPrecio_eve(eventos.get(position).getPrecio_eve());
        eveEnProceso.setTransportetipo_eve(eventos.get(position).getTransportetipo_eve());
        eveEnProceso.setEstado_eve(eventos.get(position).getEstado_eve());
        eveEnProceso.setPrecio_eve(eventos.get(position).getPrecio_eve());
        eveEnProceso.setDistanciaidatru_eve(eventos.get(position).getDistanciaidatru_eve());
        eveEnProceso.setDistanciavueltatru_eve(eventos.get(position).getDistanciavueltatru_eve());

        holder.v02_titulo_eve.setText(eveEnProceso.getTitulo_eve());
        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Eventos/" + eveEnProceso.getFoto_eve()).getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(holder.v02_foto_eve)).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context.getApplicationContext(), "Error a la hora de cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        holder.v02_fechaidatru_eve.setText(eveEnProceso.getFechaidatru_eve());
        holder.v02_fechavueltatru_eve.setText(eveEnProceso.getFechavueltatru_eve());
        holder.v02_nivel_eve.setText("Nivel: " + eveEnProceso.getNivel_eve());
        if (accion == context.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
            asignacionDeLosCamposDeAAddEve();
            holder.v02_estado_eve.setVisibility(View.VISIBLE);
            if (eveEnProceso.getEstado_eve().equalsIgnoreCase("Confirmado")) {
                holder.v02_estado_eve.setTextColor(context.getResources().getColor(R.color.administradorVerdeColor));
            } else if (eveEnProceso.getEstado_eve().equalsIgnoreCase("Pendiente")) {
                holder.v02_estado_eve.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            } else if (eveEnProceso.getEstado_eve().equalsIgnoreCase("Cancelado")) {
                holder.v02_estado_eve.setTextColor(context.getResources().getColor(R.color.red));
            }
            holder.v02_estado_eve.setText("Estado: " + eveEnProceso.getEstado_eve());
        }
        holder.v02_transportetipo_eve.setText(eveEnProceso.getTransportetipo_eve());
        holder.v02_nparticipantes_eve.setText("Participantes: " + eveEnProceso.getNparticipantes_eve());

        //corto click por el item
        // https://stackoverflow.com/questions/42266436/passing-objects-between-fragments
        holder.v02_cdv_eventos.setOnClickListener(v -> {
            if (accion == v.getResources().getInteger(R.integer.accion_a_v_03)) {
                bundleEvento.putSerializable("eventoParaV_03", eveEnProceso);
                Navigation.findNavController(v).navigate(R.id.nav_v03, bundleEvento);
            } else if (accion == v.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
                idEve = eveEnProceso.getId_eve();
                rellenacionDeLosCamposDeAAddEve(eveEnProceso);
            }
        });

        //largo click por el item
        holder.v02_cdv_eventos.setOnLongClickListener(v1 -> {
            if (accion == v1.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(v1.getContext())
                        .setPositiveButton("Activar evento", (dialog, which) -> {
                            eveEnProceso.setEstado_eve("activado");
                            fbf.collection("evento_eve").document(Integer.toString(eveEnProceso.getId_eve())).set(eveEnProceso);
                            Toast.makeText(v1.getContext(), "Evento activado!", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Desactivar evento", (dialog, which) -> {
                            eveEnProceso.setEstado_eve("desactivado");
                            fbf.collection("evento_eve").document(Integer.toString(eveEnProceso.getId_eve())).set(eveEnProceso);
                            Toast.makeText(v1.getContext(), "Evento desactivado!", Toast.LENGTH_SHORT).show();
                        })
                        .setNeutralButton("Eliminar evento", (dialog, which) -> fbf.collection("evento_eve").document(Integer.toString(eveEnProceso.getId_eve()))
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(v1.getContext(), "Evento eliminado con éxito!", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(v1.getContext(), "Ha ocurrido un error eliminando el Evento", Toast.LENGTH_SHORT).show();
                                }))
                        .setTitle("Confirmar")
                        .setMessage("¿Que deseas hacer con este evento?")
                        .create();
                dialogo.show();
            }
            return true;
        });
    }

    private void asignacionDeLosCamposDeAAddEve() {
        a_add_eve_id_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_eve);
        a_add_eve_titulo_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_titulo_eve);
        a_add_eve_foto_eve = view_A_add_eve.findViewById(R.id.a_add_eve_imv_foto_eve);
        a_add_eve_fechaidatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fechaidatru_eve);
        a_add_eve_fechavueltatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fechavueltatru_eve);
        a_add_eve_nivel_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_eve);
        a_add_eve_salidaidatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_salidaidatru_eve);
        a_add_eve_llegadaidatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_llegadaidatru_eve);
        a_add_eve_distanciaidatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distanciaidatru_eve);
        a_add_eve_distanciavueltatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distanciavueltatru_eve);
        a_add_eve_llegadacoordenadastru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_llegadacoordenadastru_eve);
        a_add_eve_salidacoordenadastru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_salidacoordenadastru_eve);
        a_add_eve_llegadavueltatru_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_llegadavueltatru_eve);
        a_add_eve_precio_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_precio_eve);
        a_add_eve_transportetipo_eve = view_A_add_eve.findViewById(R.id.a_add_eve_etx_transportetipo_eve);
    }

    private void rellenacionDeLosCamposDeAAddEve(Evento_eve eventoEnProceso) {
        a_add_eve_id_eve.setText(String.valueOf(eventoEnProceso.getId_eve()));
        a_add_eve_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
        //cargamos la imagen que ya existe en otro evento
        str.child("Eventos/" + eventoEnProceso.getFoto_eve()).getDownloadUrl()
                .addOnSuccessListener(uri -> Picasso.get().load(uri).into(a_add_eve_foto_eve))
                .addOnFailureListener(exception -> Toast.makeText(view_A_add_eve.getContext(), "GET IMAGE FAILED", Toast.LENGTH_SHORT).show());
        a_add_eve_fechaidatru_eve.setText(eventoEnProceso.getFechaidatru_eve());
        a_add_eve_fechavueltatru_eve.setText(eventoEnProceso.getFechavueltatru_eve());
        a_add_eve_nivel_eve.setText(eventoEnProceso.getNivel_eve());
        a_add_eve_salidaidatru_eve.setText(eventoEnProceso.getSalidaidatru_eve());
        a_add_eve_llegadaidatru_eve.setText(eventoEnProceso.getLlegadaidatru_eve());
        a_add_eve_distanciaidatru_eve.setText(String.valueOf(eventoEnProceso.getDistanciaidatru_eve()));
        a_add_eve_distanciavueltatru_eve.setText(String.valueOf(eventoEnProceso.getDistanciavueltatru_eve()));
        a_add_eve_llegadacoordenadastru_eve.setText(eventoEnProceso.getLlegadacoordenadastru_eve());
        a_add_eve_salidacoordenadastru_eve.setText(eventoEnProceso.getSalidacoordenadastru_eve());
        a_add_eve_llegadavueltatru_eve.setText(eventoEnProceso.getLlegadavueltatru_eve());
        a_add_eve_precio_eve.setText(String.valueOf(eventoEnProceso.getPrecio_eve()));
        a_add_eve_transportetipo_eve.setText(eventoEnProceso.getTransportetipo_eve());
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
            this.v02_foto_eve = v.findViewById(R.id.v02_crd_imv_foto_eve);
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