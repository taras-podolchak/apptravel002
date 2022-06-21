package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.modal.A_update_act_modal;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class v03_00_act_Adapter extends RecyclerView.Adapter<v03_00_act_Adapter.ViewHolder> {

    private final List<Actividad_act> actividades;
    private Context context;
    private int accion;
    private int posicionActividad;
    private Bundle bundleActividad = new Bundle();
    private View view_A_add_eve;
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
   // private int idEve = new v02_00_eve_Adapter().getIdEve();

    //act 1
    private EditText a_add_eve_id_act1;
    private EditText a_add_eve_nombre_act1;
    private EditText a_add_eve_actividadtipo_act1;
    private EditText a_add_eve_fecha_act1;
    private EditText a_add_eve_nivel_act1;
    private EditText a_add_eve_distancia_act1;
    private EditText a_add_eve_desnivel_act1;
    private EditText a_add_eve_horas_act1;
    private EditText a_add_eve_wikiloc_act1;

    //act 2
    private EditText a_add_eve_id_act2;
    private EditText a_add_eve_nombre_act2;
    private EditText a_add_eve_actividadtipo_act2;
    private EditText a_add_eve_fecha_act2;
    private EditText a_add_eve_nivel_act2;
    private EditText a_add_eve_distancia_act2;
    private EditText a_add_eve_desnivel_act2;
    private EditText a_add_eve_horas_act2;
    private EditText a_add_eve_wikiloc_act2;

    //act 3
    private EditText a_add_eve_id_act3;
    private EditText a_add_eve_nombre_act3;
    private EditText a_add_eve_actividadtipo_act3;
    private EditText a_add_eve_fecha_act3;
    private EditText a_add_eve_nivel_act3;
    private EditText a_add_eve_distancia_act3;
    private EditText a_add_eve_desnivel_act3;
    private EditText a_add_eve_horas_act3;
    private EditText a_add_eve_wikiloc_act3;

    //act 4
    private EditText a_add_eve_id_act4;
    private EditText a_add_eve_nombre_act4;
    private EditText a_add_eve_actividadtipo_act4;
    private EditText a_add_eve_fecha_act4;
    private EditText a_add_eve_nivel_act4;
    private EditText a_add_eve_distancia_act4;
    private EditText a_add_eve_desnivel_act4;
    private EditText a_add_eve_horas_act4;
    private EditText a_add_eve_wikiloc_act4;

    //act 5
    private EditText a_add_eve_id_act5;
    private EditText a_add_eve_nombre_act5;
    private EditText a_add_eve_actividadtipo_act5;
    private EditText a_add_eve_fecha_act5;
    private EditText a_add_eve_nivel_act5;
    private EditText a_add_eve_distancia_act5;
    private EditText a_add_eve_desnivel_act5;
    private EditText a_add_eve_horas_act5;
    private EditText a_add_eve_wikiloc_act5;


    //accion es: 0 si lo pulsas en V03 y 1 si lo pulsas en A_add_eve
    //view_A_add_eve es: la View de A_add_eve para tener acceso a sus campos
    //posicionActividad es: la posision de evento elegido en A_add_eve
    public v03_00_act_Adapter(List<Actividad_act> actividades, Context context, int accion, @Nullable View view_A_add_eve, int posicionActividad) {
        this.actividades = actividades;
        this.context = context;
        this.accion = accion;
        this.view_A_add_eve = view_A_add_eve;
        this.posicionActividad = posicionActividad;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_v_03_act_card = LayoutInflater.from(context).inflate(R.layout.fragment_v_03_act_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view_v_03_act_card);
        return viewHolder;
    }

    @NonNull
    @Override
/**
 * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
 */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
   /*     if (accion == context.getResources().getInteger(R.integer.accion_rellenar_formulario))
            rellenacionDeLosCamposDeEveEligido();
*/
        Actividad_act actividadEnProceso = new Actividad_act();
        actividadEnProceso.setId_act(actividades.get(position).getId_act());
        actividadEnProceso.setId_eve(actividades.get(position).getId_eve());
        actividadEnProceso.setFecha_act(actividades.get(position).getFecha_act());
        actividadEnProceso.setActividadtipo_act(actividades.get(position).getActividadtipo_act());
        actividadEnProceso.setNombre_act(actividades.get(position).getNombre_act());
        actividadEnProceso.setFoto_act(actividades.get(position).getFoto_act());
        actividadEnProceso.setDescactividad_act(actividades.get(position).getDescactividad_act());
        actividadEnProceso.setNivel_act(actividades.get(position).getNivel_act());
        actividadEnProceso.setSalida_act(actividades.get(position).getSalida_act());
        actividadEnProceso.setSalidacoordenadas_act(actividades.get(position).getSalidacoordenadas_act());
        actividadEnProceso.setLlegada_act(actividades.get(position).getLlegada_act());
        actividadEnProceso.setLlegadacoordenadastru_eve(actividades.get(position).getLlegadacoordenadastru_eve());
        actividadEnProceso.setHoras_act(actividades.get(position).getHoras_act());
        actividadEnProceso.setWikiloc_act(actividades.get(position).getWikiloc_act());
        actividadEnProceso.setDesnivel_act(actividades.get(position).getDesnivel_act());
        actividadEnProceso.setDistancia_act(actividades.get(position).getDistancia_act());

        holder.v03_nombre_act.setText(actividadEnProceso.getNombre_act());
        holder.v03_actividadtipo_act.setText(actividadEnProceso.getActividadtipo_act());
        holder.v03_fecha_act.setText(actividadEnProceso.getFecha_act());
        holder.v03_nivel_act.setText("Nivel: " + actividadEnProceso.getNivel_act());
        holder.v03_distancia_act.setText("Distancia: " + actividadEnProceso.getDistancia_act());
        holder.v03_desnivel_act.setText("Desnivel: " + actividadEnProceso.getDesnivel_act());
        holder.v03_horas_act.setText("Horas de marcha: " + actividadEnProceso.getHoras_act());

        //corto click por el item
        holder.v03_cdv_actividad.setOnClickListener(view -> {
            if (accion == view.getResources().getInteger(R.integer.accion_a_web)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(actividadEnProceso.getWikiloc_act()));
                context.startActivity(intent);
            } else if (accion == view.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
                asignacionDeLosCamposDeAAddEve();
                if (posicionActividad == 1)
                    rellenacionDeLosCamposDeAAddEve_act1(actividadEnProceso);
                else if (posicionActividad == 2)
                    rellenacionDeLosCamposDeAAddEve_act2(actividadEnProceso);
                else if (posicionActividad == 3)
                    rellenacionDeLosCamposDeAAddEve_act3(actividadEnProceso);
                else if (posicionActividad == 4)
                    rellenacionDeLosCamposDeAAddEve_act4(actividadEnProceso);
                else if (posicionActividad == 5)
                    rellenacionDeLosCamposDeAAddEve_act5(actividadEnProceso);
            }
        });

        //largo click por el item
        holder.v03_cdv_actividad.setOnLongClickListener(v1 -> {
            if (sesionIniciada == v1.getResources().getInteger(R.integer.rol_administrador)) {
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                Bundle bundleActividadEnProceso = new Bundle();

                bundleActividadEnProceso.putSerializable("actividadParaRellenarA_update_act_modal", actividadEnProceso);
                DialogFragment a_update_act_modal = new A_update_act_modal();
                a_update_act_modal.setArguments(bundleActividadEnProceso);
                a_update_act_modal.show(manager, "dialog");
            }
            return true;
        });
    }
/*
    private void rellenacionDeLosCamposDeEveEligido() {
        for (Actividad_act act : actividades) {
            if (act.getId_eve() == idEve && idEve != 0)
                rellenacionDeLosCamposDeAAddEve_act1(act);
        }
    }
*/
    private void asignacionDeLosCamposDeAAddEve() {
        //actividad act 1
        a_add_eve_id_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_act1);
        a_add_eve_nombre_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act1);
        a_add_eve_actividadtipo_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act1);
        a_add_eve_fecha_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act1);
        a_add_eve_nivel_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act1);
        a_add_eve_distancia_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act1);
        a_add_eve_desnivel_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act1);
        a_add_eve_horas_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act1);
        a_add_eve_wikiloc_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act1);

        //actividad act 2
        a_add_eve_id_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_act2);
        a_add_eve_nombre_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act2);
        a_add_eve_actividadtipo_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act2);
        a_add_eve_fecha_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act2);
        a_add_eve_nivel_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act2);
        a_add_eve_distancia_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act2);
        a_add_eve_desnivel_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act2);
        a_add_eve_horas_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act2);
        a_add_eve_wikiloc_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act2);

        //actividad act 3
        a_add_eve_id_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_act3);
        a_add_eve_nombre_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act3);
        a_add_eve_actividadtipo_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act3);
        a_add_eve_fecha_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act3);
        a_add_eve_nivel_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act3);
        a_add_eve_distancia_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act3);
        a_add_eve_desnivel_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act3);
        a_add_eve_horas_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act3);
        a_add_eve_wikiloc_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act3);
        ;

        //actividad act 4
        a_add_eve_id_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_act4);
        a_add_eve_nombre_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act4);
        a_add_eve_actividadtipo_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act4);
        a_add_eve_fecha_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act4);
        a_add_eve_nivel_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act4);
        a_add_eve_distancia_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act4);
        a_add_eve_desnivel_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act4);
        a_add_eve_horas_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act4);
        a_add_eve_wikiloc_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act4);

        //actividad act 5
        a_add_eve_id_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_id_act5);
        a_add_eve_nombre_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act5);
        a_add_eve_actividadtipo_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act5);
        a_add_eve_fecha_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act5);
        a_add_eve_nivel_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act5);
        a_add_eve_distancia_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act5);
        a_add_eve_desnivel_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act5);
        a_add_eve_horas_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act5);
        a_add_eve_wikiloc_act5 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act5);
    }

    private void rellenacionDeLosCamposDeAAddEve_act1(Actividad_act actividadEnProceso) {
        a_add_eve_id_act1.setText(String.valueOf(actividadEnProceso.getId_act()));
        a_add_eve_nombre_act1.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act1.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act1.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act1.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act1.setText(String.valueOf(actividadEnProceso.getDistancia_act()));
        a_add_eve_desnivel_act1.setText(String.valueOf(actividadEnProceso.getDesnivel_act()));
        a_add_eve_horas_act1.setText(String.valueOf(actividadEnProceso.getHoras_act()));
        a_add_eve_wikiloc_act1.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act2(Actividad_act actividadEnProceso) {
        a_add_eve_id_act2.setText(String.valueOf(actividadEnProceso.getId_act()));
        a_add_eve_nombre_act2.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act2.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act2.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act2.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act2.setText(String.valueOf(actividadEnProceso.getDistancia_act()));
        a_add_eve_desnivel_act2.setText(String.valueOf(actividadEnProceso.getDesnivel_act()));
        a_add_eve_horas_act2.setText(String.valueOf(actividadEnProceso.getHoras_act()));
        a_add_eve_wikiloc_act2.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act3(Actividad_act actividadEnProceso) {
        a_add_eve_id_act3.setText(String.valueOf(actividadEnProceso.getId_act()));
        a_add_eve_nombre_act3.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act3.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act3.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act3.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act3.setText(String.valueOf(actividadEnProceso.getDistancia_act()));
        a_add_eve_desnivel_act3.setText(String.valueOf(actividadEnProceso.getDesnivel_act()));
        a_add_eve_horas_act3.setText(String.valueOf(actividadEnProceso.getHoras_act()));
        a_add_eve_wikiloc_act3.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act4(Actividad_act actividadEnProceso) {
        a_add_eve_id_act4.setText(String.valueOf(actividadEnProceso.getId_act()));
        a_add_eve_nombre_act4.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act4.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act4.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act4.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act4.setText(String.valueOf(actividadEnProceso.getDistancia_act()));
        a_add_eve_desnivel_act4.setText(String.valueOf(actividadEnProceso.getDesnivel_act()));
        a_add_eve_horas_act4.setText(String.valueOf(actividadEnProceso.getHoras_act()));
        a_add_eve_wikiloc_act4.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act5(Actividad_act actividadEnProceso) {
        a_add_eve_id_act5.setText(String.valueOf(actividadEnProceso.getId_act()));
        a_add_eve_nombre_act5.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act5.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act5.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act5.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act5.setText(String.valueOf(actividadEnProceso.getDistancia_act()));
        a_add_eve_desnivel_act5.setText(String.valueOf(actividadEnProceso.getDesnivel_act()));
        a_add_eve_horas_act5.setText(String.valueOf(actividadEnProceso.getHoras_act()));
        a_add_eve_wikiloc_act5.setText(actividadEnProceso.getWikiloc_act());
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView v03_nombre_act;
        private TextView v03_actividadtipo_act;
        private TextView v03_fecha_act;
        private TextView v03_nivel_act;
        private TextView v03_distancia_act;
        private TextView v03_desnivel_act;
        private TextView v03_horas_act;
        private CardView v03_cdv_actividad;
        private LifecycleOwner lifecycleOwner = this.lifecycleOwner;

        public ViewHolder(View v) {
            super(v);
            this.v03_nombre_act = v.findViewById(R.id.v03_cdv_txv_nombre_act);
            this.v03_actividadtipo_act = v.findViewById(R.id.v03_cdv_txv_actividadtipo_act);
            this.v03_fecha_act = v.findViewById(R.id.v03_cdv_txv_fecha_act);
            this.v03_nivel_act = v.findViewById(R.id.v03_cdv_txv_nivel_act);
            this.v03_distancia_act = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this.v03_desnivel_act = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this.v03_horas_act = v.findViewById(R.id.v03_cdv_txv_horas_act);
            this.v03_cdv_actividad = v.findViewById(R.id.v03_cdv_actividad);
        }
    }
}