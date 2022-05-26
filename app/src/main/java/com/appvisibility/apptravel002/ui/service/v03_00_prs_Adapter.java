package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class v03_00_prs_Adapter extends RecyclerView.Adapter<v03_00_prs_Adapter.ViewHolder> {

    private List<Persona_prs> personasInscritos = new ArrayList<>();
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private List<Inscribir_eveprs> inscritos  = new ArrayList<>();
    private int plazaslibres_eveprs;
    private Map<Integer, Persona_prs> map_IdIns_Prs = new HashMap<>();
    private Map<Integer, Inscribir_eveprs> map_Posicion_Inscrito = new HashMap<>();
    FirebaseStorage fbs = FirebaseStorage.getInstance();
    StorageReference str = fbs.getReference();
    Context context;
    int id_eve_bundle;
    int id_tpr_enProceso = 0;
    int id_prs_enProceso;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public v03_00_prs_Adapter(List<Persona_prs> personasInscritos, List<Inscribir_eveprs> inscritos, Context context, int id_eve_bundle) {
        this.personasInscritos = personasInscritos;
        this.inscritos = inscritos;
        this.context = context;
        this.id_eve_bundle = id_eve_bundle;

/*
https://stackoverflow.com/questions/70287093/cannot-create-map-from-two-connected-entities-in-java
        Map <Integer, Object> map_IdTpr_PlazasLibres = new HashMap<>();
        map_IdTpr_PlazasLibres = transportepropios.stream()
                .collect(Collectors.toMap(Transportepropio_tpr::getId_tpr, pl -> pl.getPlazaslibres_tpr()));
*/
// Mapeamos los Inscritos con el objeto Persona correspondiente
        for(Inscribir_eveprs ins: inscritos){
            for(Persona_prs prs: personasInscritos){
                if (ins.getId_prs() == prs.getId_prs()){
                    map_IdIns_Prs.put(ins.getId_prs(), prs);
                }
            }
        }
    }//Fin de constructor

    public v03_00_prs_Adapter() {
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
        id_prs_enProceso = personasInscritos.get(position).getId_prs();
        String  apodo_prs = personasInscritos.get(position).getApodo_prs();
        String  dni_prs = personasInscritos.get(position).getDni_prs();
        String  nombre_prs = personasInscritos.get(position).getNombre_prs();
        String  apellido1_prs = personasInscritos.get(position).getApellido1_prs();
        String  apellido2_prs = personasInscritos.get(position).getApellido2_prs();
        String  direccion_prs = personasInscritos.get(position).getDireccion_prs();
        String  localidad_prs = personasInscritos.get(position).getLocalidad_prs();
        String  cpostal_prs = personasInscritos.get(position).getCpostal_prs();
        String  movil_prs = personasInscritos.get(position).getMovil_prs();
        String  email_prs = personasInscritos.get(position).getEmail_prs();
        String  fotopropia_prs = personasInscritos.get(position).getFotopropia_prs();
        String  fechaalta_prs = personasInscritos.get(position).getFechaalta_prs();
        String  fechabaja_prs = personasInscritos.get(position).getFechabaja_prs();
        String  contacto1cargo_prs = personasInscritos.get(position).getContacto1Cargo_prs();
        String  contacto1movil_prs = personasInscritos.get(position).getContacto1Movil_prs();
        String  contacto2cargo_prs = personasInscritos.get(position).getContacto2Cargo_prs();
        String  contacto2movil_prs = personasInscritos.get(position).getContacto2Movil_prs();
        int fiabilidadpre_prs = personasInscritos.get(position).getFiabilidadpre_prs();
        int valoracionorgpre_prs = personasInscritos.get(position).getValoracionorgpre_prs();
        int antiguedadpre_prs = personasInscritos.get(position).getAntiguedadpre_prs();
        int volumencomprapre_prs = personasInscritos.get(position).getVolumencomprapre_prs();
        int cochepre_prs = personasInscritos.get(position).getCochepre_prs();
        int nrelacionespre_prs = personasInscritos.get(position).getNrelacionespre_prs();
        String  coche_prs = personasInscritos.get(position).getCoche_prs();
        int nps01_prs = personasInscritos.get(position).getNps01_prs();
        String  nps01fecha_prs = personasInscritos.get(position).getNps01Fecha_prs();
        int nps02_prs = personasInscritos.get(position).getNps02_prs();
        String  nps02fecha_prs = personasInscritos.get(position).getNps02Fecha_prs();
        int nps03_prs = personasInscritos.get(position).getNps03_prs();
        String  nps03fecha_prs = personasInscritos.get(position).getNps03Fecha_prs();
        boolean condicioneslegales_prs = personasInscritos.get(position).getCondicioneslegales_prs();

        Persona_prs personaEnProceso = new Persona_prs(id_prs_enProceso, apodo_prs, dni_prs, nombre_prs, apellido1_prs, apellido2_prs, direccion_prs, localidad_prs, cpostal_prs, movil_prs, email_prs, fotopropia_prs, fechaalta_prs, fechabaja_prs, contacto1cargo_prs, contacto1movil_prs, contacto2cargo_prs, contacto2movil_prs, fiabilidadpre_prs, valoracionorgpre_prs, antiguedadpre_prs, volumencomprapre_prs, cochepre_prs, nrelacionespre_prs, coche_prs, nps01_prs, nps01fecha_prs, nps02_prs, nps02fecha_prs, nps03_prs, nps03fecha_prs, condicioneslegales_prs);

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

        holder.v03_cdv_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Bundle bundlePersona = new Bundle();
                bundlePersona.putSerializable("personaParaC_05", personaEnProceso);
                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if (sesionIniciada == 0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_c05, bundlePersona);
                }
                notifyDataSetChanged();
*/
            }
        });

        // Identificamos el número de Plazas Libres disponibles del Inscrito en proceso
        for (Inscribir_eveprs ins: inscritos) {
            if (ins.getId_prs() == id_prs_enProceso){
                plazaslibres_eveprs = ins.getPlazaslibres_eveprs();
                // Mapeamos cada Posición del RecycledView con el id_prs asignado
                map_Posicion_Inscrito.put(position, ins);
            }
        }

        // Cargamos en el RecycledView el mensaje correspondiente al número de Plazas Libres disponible en cada Inscrito
        if (plazaslibres_eveprs == -1){
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_rojo);
            holder.v03_plazaslibres_eveprs.setText("Sin plaza asignada");
        } else if (plazaslibres_eveprs == 0) {
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_naranja);
            holder.v03_plazaslibres_eveprs.setText("Con plaza asignada");
        } else {
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_verde);
            holder.v03_plazaslibres_eveprs.setText("Plazas libres: " + plazaslibres_eveprs);
        }

        holder.v03_cdv_transportepropio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el id_tpr correspondiente a la posición seleccionada del Recycled
                for (Inscribir_eveprs ins: inscritos) {
                    if (ins.getId_prs() == map_Posicion_Inscrito.get(position).getId_prs()){
                        id_tpr_enProceso = ins.getId_tpr();
                    }
                }
                // Si la persona en proceso aún dispone de plazas libres, cargamos en el listado de personasEnCoche las personas que tiene id_tpr correspondiente a la posición seleccionada del Recycled
                for (Inscribir_eveprs ins: inscritos) {
                    if (ins.getId_tpr() == id_tpr_enProceso && ins.getPlazaslibres_eveprs()>=0){
//                    if (ins.getId_tpr() == id_tpr_enProceso){
                        id_prs_enProceso = ins.getId_prs();
                        personasEnCoche.add(map_IdIns_Prs.get(id_prs_enProceso));
                    }
                }

                // Colaboradores: Enviamos la ficha completa de la personaEnProceso
                Bundle bundlePersona = new Bundle();
                bundlePersona.putSerializable("personaParaC_05", personaEnProceso);
                // https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
                // Valiente: Enviamos a la ventana modal el listado de personasEnCoche correspondiente al id_prs seleccionado
                Bundle bundlePersonasEnCoche = new Bundle();
                bundlePersonasEnCoche.putParcelableArrayList("personaParaV_05_2", (ArrayList<? extends Parcelable>) personasEnCoche);

                // TODO EOB: Sustituir condicion del if por "roll == colaborador"
                if ((sesionIniciada == 0 || sesionIniciada == 3) && map_Posicion_Inscrito.get(position).getPlazaslibres_eveprs()>=0) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05_2, bundlePersonasEnCoche);
                } else if (sesionIniciada == 0 && map_Posicion_Inscrito.get(position).getPlazaslibres_eveprs()<0) {
                    Toast.makeText((context.getApplicationContext()), "Inscrito sin Plaza de Transporte", Toast.LENGTH_LONG).show();
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
        return personasInscritos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView v03_fotopropia_prs;
        private TextView v03_apodo_prs;
        private TextView v03_movil_prs;
        private ImageView v03_ico_coche;
        private TextView v03_plazaslibres_eveprs;
        private CardView v03_cdv_persona;
        private CardView v03_cdv_transportepropio;

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_prs = v.findViewById(R.id.v03_cdv_imv_fotopropia_prs);
            this.v03_apodo_prs = v.findViewById(R.id.v03_cdv_txv_apodo_prs);
            this.v03_movil_prs = v.findViewById(R.id.v03_cdv_txv_movil_prs);
            this.v03_ico_coche = v.findViewById(R.id.v03_cdv_imv_ico_coche);
            this.v03_plazaslibres_eveprs = v.findViewById(R.id.v03_cdv_txv_plazaslibres_eveprs);
            this.v03_cdv_persona = (CardView) v.findViewById(R.id.v03_cdv_persona);
            this.v03_cdv_transportepropio = (CardView) v.findViewById(R.id.v03_cdv_persona);
        }
    }
}