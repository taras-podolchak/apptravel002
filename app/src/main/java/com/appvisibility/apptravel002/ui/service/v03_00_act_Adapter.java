package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
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

    //act 1
    private EditText a_add_eve_nombre_act1;
    private EditText a_add_eve_actividadtipo_act1;
    private EditText a_add_eve_fecha_act1;
    private EditText a_add_eve_nivel_act1;
    private EditText a_add_eve_distancia_act1;
    private EditText a_add_eve_desnivel_act1;
    private EditText a_add_eve_horas_act1;
    private EditText a_add_eve_wikiloc_act1;

    //act 2
    private EditText a_add_eve_nombre_act2;
    private EditText a_add_eve_actividadtipo_act2;
    private EditText a_add_eve_fecha_act2;
    private EditText a_add_eve_nivel_act2;
    private EditText a_add_eve_distancia_act2;
    private EditText a_add_eve_desnivel_act2;
    private EditText a_add_eve_horas_act2;
    private EditText a_add_eve_wikiloc_act2;

    //act 3
    private EditText a_add_eve_nombre_act3;
    private EditText a_add_eve_actividadtipo_act3;
    private EditText a_add_eve_fecha_act3;
    private EditText a_add_eve_nivel_act3;
    private EditText a_add_eve_distancia_act3;
    private EditText a_add_eve_desnivel_act3;
    private EditText a_add_eve_horas_act3;
    private EditText a_add_eve_wikiloc_act3;

    //act 4
    private EditText a_add_eve_nombre_act4;
    private EditText a_add_eve_actividadtipo_act4;
    private EditText a_add_eve_fecha_act4;
    private EditText a_add_eve_nivel_act4;
    private EditText a_add_eve_distancia_act4;
    private EditText a_add_eve_desnivel_act4;
    private EditText a_add_eve_horas_act4;
    private EditText a_add_eve_wikiloc_act4;

    //act 5
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
        String nombre_act = actividades.get(position).getNombre_act();
        String actividadtipo_act = actividades.get(position).getActividadtipo_act();
        String fecha_act = actividades.get(position).getFecha_act();
        String nivel_act = actividades.get(position).getNivel_act();
        int distancia_act = actividades.get(position).getDistancia_act();
        int desnivel_act = actividades.get(position).getDesnivel_act();
        int horas_act = actividades.get(position).getHoras_act();
        String wikiloc_act = actividades.get(position).getWikiloc_act();

        Actividad_act actividadEnProceso = new Actividad_act(nombre_act, actividadtipo_act, fecha_act, nivel_act, distancia_act, desnivel_act, horas_act, wikiloc_act);

        holder.v03_nombre_act.setText(nombre_act);
        holder.v03_actividadtipo_act.setText(actividadtipo_act);
        holder.v03_fecha_act.setText(fecha_act);
        holder.v03_nivel_act.setText("Nivel: " + nivel_act);
        holder.v03_distancia_act.setText("Distancia: " + distancia_act);
        holder.v03_desnivel_act.setText("Desnivel: " + desnivel_act);
        holder.v03_horas_act.setText("Horas de marcha: " + horas_act);

        //corto click por el item
        holder.v03_cdv_actividad.setOnClickListener(view -> {
            if (accion == view.getResources().getInteger(R.integer.accion_a_web)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(wikiloc_act));
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
            if (accion == v1.getResources().getInteger(R.integer.accion_rellenar_formulario)) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(v1.getContext())
                        .setPositiveButton("Activar actividad", (dialog, which) -> {
                            actividadEnProceso.setDescactividad_act("activado");
                            fbf.collection("actividad_act").document(Integer.toString(actividadEnProceso.getId_act())).set(actividadEnProceso);
                            Toast.makeText(v1.getContext(), "Actividad activado!", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Desactivar actividad", (dialog, which) -> {
                            actividadEnProceso.setDescactividad_act("desactivado");
                            fbf.collection("actividad_act").document(Integer.toString(actividadEnProceso.getId_act())).set(actividadEnProceso);
                            Toast.makeText(v1.getContext(), "Actividad desactivado!", Toast.LENGTH_SHORT).show();
                        })
                        .setNeutralButton("Eliminar actividad", (dialog, which) -> fbf.collection("actividad_act").document(Integer.toString(actividadEnProceso.getId_act()))
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(v1.getContext(), "Actividad eliminado con éxito!", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(v1.getContext(), "Ha ocurrido un error eliminando la actividad", Toast.LENGTH_SHORT).show();
                                }))
                        .setTitle("Confirmar")
                        .setMessage("¿Que deseas hacer con esta actividad?")
                        .create();
                dialogo.show();
            }
            return true;
        });
    }


    private void asignacionDeLosCamposDeAAddEve() {
        //actividad act 1
        a_add_eve_nombre_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act1);
        a_add_eve_actividadtipo_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act1);
        a_add_eve_fecha_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act1);
        a_add_eve_nivel_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act1);
        a_add_eve_distancia_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act1);
        a_add_eve_desnivel_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act1);
        a_add_eve_horas_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act1);
        a_add_eve_wikiloc_act1 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act1);

        //actividad act 2
        a_add_eve_nombre_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act2);
        a_add_eve_actividadtipo_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act2);
        a_add_eve_fecha_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act2);
        a_add_eve_nivel_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act2);
        a_add_eve_distancia_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act2);
        a_add_eve_desnivel_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act2);
        a_add_eve_horas_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act2);
        a_add_eve_wikiloc_act2 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act2);

        //actividad act 3
        a_add_eve_nombre_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act3);
        a_add_eve_actividadtipo_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act3);
        a_add_eve_fecha_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act3);
        a_add_eve_nivel_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act3);
        a_add_eve_distancia_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act3);
        a_add_eve_desnivel_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act3);
        a_add_eve_horas_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act3);
        a_add_eve_wikiloc_act3 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act3);;

        //actividad act 4
        a_add_eve_nombre_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nombre_act4);
        a_add_eve_actividadtipo_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_actividadtipo_act4);
        a_add_eve_fecha_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_fecha_act4);
        a_add_eve_nivel_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_nivel_act4);
        a_add_eve_distancia_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_distancia_act4);
        a_add_eve_desnivel_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_desnivel_act4);
        a_add_eve_horas_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_horas_act4);
        a_add_eve_wikiloc_act4 = view_A_add_eve.findViewById(R.id.a_add_eve_etx_wikiloc_act4);

        //actividad act 5
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
        a_add_eve_nombre_act1.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act1.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act1.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act1.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act1.setId(actividadEnProceso.getDistancia_act());
        a_add_eve_desnivel_act1.setId(actividadEnProceso.getDesnivel_act());
        a_add_eve_horas_act1.setId(actividadEnProceso.getHoras_act());
        a_add_eve_wikiloc_act1.setText(actividadEnProceso.getWikiloc_act());
    }
    private void rellenacionDeLosCamposDeAAddEve_act2(Actividad_act actividadEnProceso) {
        a_add_eve_nombre_act2.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act2.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act2.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act2.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act2.setId(actividadEnProceso.getDistancia_act());
        a_add_eve_desnivel_act2.setId(actividadEnProceso.getDesnivel_act());
        a_add_eve_horas_act2.setId(actividadEnProceso.getHoras_act());
        a_add_eve_wikiloc_act2.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act3(Actividad_act actividadEnProceso) {
        a_add_eve_nombre_act3.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act3.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act3.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act3.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act3.setId(actividadEnProceso.getDistancia_act());
        a_add_eve_desnivel_act3.setId(actividadEnProceso.getDesnivel_act());
        a_add_eve_horas_act3.setId(actividadEnProceso.getHoras_act());
        a_add_eve_wikiloc_act3.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act4(Actividad_act actividadEnProceso) {
        a_add_eve_nombre_act4.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act4.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act4.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act4.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act4.setId(actividadEnProceso.getDistancia_act());
        a_add_eve_desnivel_act4.setId(actividadEnProceso.getDesnivel_act());
        a_add_eve_horas_act4.setId(actividadEnProceso.getHoras_act());
        a_add_eve_wikiloc_act4.setText(actividadEnProceso.getWikiloc_act());
    }

    private void rellenacionDeLosCamposDeAAddEve_act5(Actividad_act actividadEnProceso) {
        a_add_eve_nombre_act5.setText(actividadEnProceso.getNombre_act());
        a_add_eve_actividadtipo_act5.setText(actividadEnProceso.getActividadtipo_act());
        a_add_eve_fecha_act5.setText(actividadEnProceso.getFecha_act());
        a_add_eve_nivel_act5.setText(actividadEnProceso.getNivel_act());
        a_add_eve_distancia_act5.setId(actividadEnProceso.getDistancia_act());
        a_add_eve_desnivel_act5.setId(actividadEnProceso.getDesnivel_act());
        a_add_eve_horas_act5.setId(actividadEnProceso.getHoras_act());
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

        public ViewHolder(View v) {
            super(v);
            this.v03_distancia_act = v.findViewById(R.id.v03_cdv_txv_distancia_act);
            this.v03_desnivel_act = v.findViewById(R.id.v03_cdv_txv_desnivel_act);
            this.v03_horas_act = v.findViewById(R.id.v03_cdv_txv_horas_act);
            this.v03_nombre_act = v.findViewById(R.id.v03_cdv_txv_nombre_act);
            this.v03_actividadtipo_act = v.findViewById(R.id.v03_cdv_txv_actividadtipo_act);
            this.v03_fecha_act = v.findViewById(R.id.v03_cdv_txv_fecha_act);
            this.v03_nivel_act = v.findViewById(R.id.v03_cdv_txv_nivel_act);

            this.v03_cdv_actividad = v.findViewById(R.id.v03_cdv_actividad);
        }
    }
}