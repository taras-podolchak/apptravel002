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
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.modal.V_03_2_modal;
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

public class v03_00_prs_Adapter extends RecyclerView.Adapter<v03_00_prs_Adapter.ViewHolder> {

    private List<Persona_prs> personasInscritos = new ArrayList<>();
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private List<Inscribir_eveprs> inscritos = new ArrayList<>();
    private int plazaslibres_eveprs;
    private Map<Integer, Persona_prs> map_IdIns_Prs = new HashMap<>();
    private Map<Integer, Inscribir_eveprs> map_Posicion_Inscrito = new HashMap<>();
    private FirebaseStorage fbs = FirebaseStorage.getInstance();
    private StorageReference str = fbs.getReference();
    private Context context;
    private int id_eve_bundle;
    private int id_tpr_enProceso = 0;
    private int id_prs_enProceso;

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
        for (Inscribir_eveprs ins : inscritos) {
            for (Persona_prs prs : personasInscritos) {
                if (ins.getId_prs() == prs.getId_prs()) {
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
        Persona_prs personaEnProceso = new Persona_prs();
        id_prs_enProceso = personasInscritos.get(position).getId_prs();
        personaEnProceso.setId_prs(id_prs_enProceso);
        personaEnProceso.setApodo_prs(personasInscritos.get(position).getApodo_prs());
        personaEnProceso.setContrasenna_prs(personasInscritos.get(position).getContrasenna_prs());
        personaEnProceso.setRecordarcontrasenna_prs(personasInscritos.get(position).getRecordarcontrasenna_prs());
        personaEnProceso.setActividadtipo_prs(personasInscritos.get(position).getActividadtipo_prs());
        personaEnProceso.setDocumentotipo_prs(personasInscritos.get(position).getDocumentotipo_prs());
        personaEnProceso.setDni_prs(personasInscritos.get(position).getDni_prs());
        personaEnProceso.setNombre_prs(personasInscritos.get(position).getNombre_prs());
        personaEnProceso.setApellido1_prs(personasInscritos.get(position).getApellido1_prs());
        personaEnProceso.setApellido2_prs(personasInscritos.get(position).getApellido2_prs());
        personaEnProceso.setRazonsocial_prs(personasInscritos.get(position).getRazonsocial_prs());
        personaEnProceso.setNumerocta_prs(personasInscritos.get(position).getNumerocta_prs());
        personaEnProceso.setDireccion_prs(personasInscritos.get(position).getDireccion_prs());
        personaEnProceso.setLocalidad_prs(personasInscritos.get(position).getLocalidad_prs());
        personaEnProceso.setCpostal_prs(personasInscritos.get(position).getCpostal_prs());
        personaEnProceso.setPais_prs(personasInscritos.get(position).getPais_prs());
        personaEnProceso.setMovil_prs(personasInscritos.get(position).getMovil_prs());
        personaEnProceso.setEmail_prs(personasInscritos.get(position).getEmail_prs());
        personaEnProceso.setTelefono_prs(personasInscritos.get(position).getTelefono_prs());
        personaEnProceso.setWeb_prs(personasInscritos.get(position).getWeb_prs());
        personaEnProceso.setUsuariotipo_prs(personasInscritos.get(position).getUsuariotipo_prs());
        personaEnProceso.setFotopropia_prs(personasInscritos.get(position).getFotopropia_prs());
        personaEnProceso.setFotoorg_prs(personasInscritos.get(position).getFotoorg_prs());
        personaEnProceso.setFederado_prs(personasInscritos.get(position).getFederado_prs());
        personaEnProceso.setFederacionfoto_prs(personasInscritos.get(position).getFederacionfoto_prs());
        personaEnProceso.setSeguro_prs(personasInscritos.get(position).getSeguro_prs());
        personaEnProceso.setSegurocompannia_prs(personasInscritos.get(position).getSegurocompannia_prs());
        personaEnProceso.setSeguropoliza_prs(personasInscritos.get(position).getSeguropoliza_prs());
        personaEnProceso.setFechacaducidadseguro_prs(personasInscritos.get(position).getFechacaducidadseguro_prs());
        personaEnProceso.setFechaalta_prs(personasInscritos.get(position).getFechaalta_prs());
        personaEnProceso.setAntiguedad_prs(personasInscritos.get(position).getAntiguedad_prs());
        personaEnProceso.setFechabaja_prs(personasInscritos.get(position).getFechabaja_prs());
        personaEnProceso.setSolicitabaja_prs(personasInscritos.get(position).getSolicitabaja_prs());
        personaEnProceso.setContacto1Cargo_prs(personasInscritos.get(position).getContacto1Cargo_prs());
        personaEnProceso.setContacto1Nombre_prs(personasInscritos.get(position).getContacto1Nombre_prs());
        personaEnProceso.setContacto1Apellido1_prs(personasInscritos.get(position).getContacto1Apellido1_prs());
        personaEnProceso.setContacto1Apellido2_prs(personasInscritos.get(position).getContacto1Apellido2_prs());
        personaEnProceso.setContacto1Movil_prs(personasInscritos.get(position).getContacto1Movil_prs());
        personaEnProceso.setContacto1Telefono_prs(personasInscritos.get(position).getContacto1Telefono_prs());
        personaEnProceso.setContacto1Email_prs(personasInscritos.get(position).getContacto1Email_prs());
        personaEnProceso.setContacto2Cargo_prs(personasInscritos.get(position).getContacto2Cargo_prs());
        personaEnProceso.setContacto2Nombre_prs(personasInscritos.get(position).getContacto2Nombre_prs());
        personaEnProceso.setContacto2Apellido1_prs(personasInscritos.get(position).getContacto2Apellido1_prs());
        personaEnProceso.setContacto2Apellido2_prs(personasInscritos.get(position).getContacto2Apellido2_prs());
        personaEnProceso.setContacto2Movil_prs(personasInscritos.get(position).getContacto2Movil_prs());
        personaEnProceso.setContacto2Telefono_prs(personasInscritos.get(position).getContacto2Telefono_prs());
        personaEnProceso.setContacto2Email_prs(personasInscritos.get(position).getContacto2Email_prs());
        personaEnProceso.setPreferencia_prs(personasInscritos.get(position).getPreferencia_prs());
        personaEnProceso.setFiabilidadpre_prs(personasInscritos.get(position).getFiabilidadpre_prs());
        personaEnProceso.setValoracionorgpre_prs(personasInscritos.get(position).getValoracionorgpre_prs());
        personaEnProceso.setAntiguedadpre_prs(personasInscritos.get(position).getAntiguedadpre_prs());
        personaEnProceso.setVolumencomprapre_prs(personasInscritos.get(position).getVolumencomprapre_prs());
        personaEnProceso.setCochepre_prs(personasInscritos.get(position).getCochepre_prs());
        personaEnProceso.setNrelacionespre_prs(personasInscritos.get(position).getNrelacionespre_prs());
        personaEnProceso.setCoche_prs(personasInscritos.get(position).getCoche_prs());
        personaEnProceso.setNps01_prs(personasInscritos.get(position).getNps01_prs());
        personaEnProceso.setNps01Fecha_prs(personasInscritos.get(position).getNps01Fecha_prs());
        personaEnProceso.setNps02_prs(personasInscritos.get(position).getNps02_prs());
        personaEnProceso.setNps02Fecha_prs(personasInscritos.get(position).getNps02Fecha_prs());
        personaEnProceso.setNps03_prs(personasInscritos.get(position).getNps03_prs());
        personaEnProceso.setNps03Fecha_prs(personasInscritos.get(position).getNps03Fecha_prs());
        personaEnProceso.setCondicioneslegales_prs(personasInscritos.get(position).getCondicioneslegales_prs());

        str.child("Valientes/" + personaEnProceso.getFotopropia_prs()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.v03_fotopropia_prs);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_SHORT).show();
                // Handle any errors
            }
        });
        holder.v03_apodo_prs.setText(personaEnProceso.getApodo_prs());
//        holder.v03_movil_prs.setText("Tel: " + personaEnProceso.getMovil_prs());

        // Identificamos el número de Plazas Libres disponibles del Inscrito en proceso
        for (Inscribir_eveprs ins : inscritos) {
            if (ins.getId_prs() == id_prs_enProceso) {
                plazaslibres_eveprs = ins.getPlazaslibres_eveprs();
                // Mapeamos cada Posición del RecycledView con el id_prs asignado
                map_Posicion_Inscrito.put(position, ins);
            }
        }

        // Cargamos en el RecycledView el mensaje correspondiente al número de Plazas Libres disponible en cada Inscrito
        if (plazaslibres_eveprs == -1) {
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_rojo);
            holder.v03_plazaslibres_eveprs.setText("Sin plaza asignada");
        } else if (plazaslibres_eveprs == 0) {
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_naranja);
            holder.v03_plazaslibres_eveprs.setText("Con plaza asignada");
        } else {
            holder.v03_ico_coche.setImageResource(R.drawable.ico_coche_verde);
            holder.v03_plazaslibres_eveprs.setText("Plazas libres: " + plazaslibres_eveprs);
        }

        holder.v03_cdv_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos el id_tpr (coche) en proceso correspondiente a la posición seleccionada del Recycled
                for (Inscribir_eveprs ins : inscritos) {
                    if (ins.getId_prs() == map_Posicion_Inscrito.get(position).getId_prs()) {
                        id_tpr_enProceso = ins.getId_tpr();
                    }
                }
                // Si el id_tpr (coche) en proceso aún dispone de plazas libres, cargamos en el listado de personasEnCoche las personas que tiene ese id_tpr
                for (Inscribir_eveprs ins : inscritos) {
                    if (ins.getId_tpr() == id_tpr_enProceso
                            && ins.getPlazaslibres_eveprs() >= 0) {
                        id_prs_enProceso = ins.getId_prs();
                        personasEnCoche.add(map_IdIns_Prs.get(id_prs_enProceso));
                    }
                }

                if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                        && map_Posicion_Inscrito.get(position).getPlazaslibres_eveprs() >= 0) {
                    // https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
                    // Valiente: Enviamos a la ventana modal el listado de personasEnCoche correspondiente al id_prs (Persona) seleccionado
                    Bundle bundlePersonasEnCoche = new Bundle();
                    bundlePersonasEnCoche.putParcelableArrayList("personaParaV_03_2", (ArrayList<? extends Parcelable>) personasEnCoche);
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    DialogFragment v_03_2_modal = new V_03_2_modal();
                    v_03_2_modal.setArguments(bundlePersonasEnCoche);
                    v_03_2_modal.show(fragmentManager, "dialog");
                } else if (sesionIniciada ==  view.getResources().getInteger(R.integer.rol_valiente)
                        && map_Posicion_Inscrito.get(position).getPlazaslibres_eveprs() < 0) {
                    Toast.makeText((context.getApplicationContext()), "Inscrito sin Plaza de Transporte", Toast.LENGTH_LONG).show();
                } else if (sesionIniciada > view.getResources().getInteger(R.integer.rol_valiente) ) {
                    // Colaboradores: Enviamos la ficha completa de la personaEnProceso
                    Bundle bundlePersona = new Bundle();
                    bundlePersona.putSerializable("personaParaC_05", personaEnProceso);
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

        public ViewHolder(View v) {
            super(v);
            this.v03_fotopropia_prs = v.findViewById(R.id.v03_cdv_imv_fotopropia_prs);
            this.v03_apodo_prs = v.findViewById(R.id.v03_cdv_txv_apodo_prs);
            this.v03_movil_prs = v.findViewById(R.id.v03_cdv_txv_movil_prs);
            this.v03_ico_coche = v.findViewById(R.id.v03_cdv_imv_ico_coche);
            this.v03_plazaslibres_eveprs = v.findViewById(R.id.v03_cdv_txv_plazaslibres_eveprs);
            this.v03_cdv_persona = v.findViewById(R.id.v03_cdv_persona);
        }
    }
}