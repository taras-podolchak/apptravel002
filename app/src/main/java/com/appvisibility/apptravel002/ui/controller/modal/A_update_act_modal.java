package com.appvisibility.apptravel002.ui.controller.modal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link A_update_act_modal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class A_update_act_modal extends DialogFragment {

    private EditText a_update_act_modal_nombre_act;
    private EditText a_update_act_modal_actividadtipo_act;
    private EditText a_update_act_modal_fecha_act;
    private EditText a_update_act_modal_nivel_act;
    private EditText a_update_act_modal_distancia_act;
    private EditText a_update_act_modal_desnivel_act;
    private EditText a_update_act_modal_horas_act;
    private EditText a_update_act_modal_wikiloc_act;
    private Button a_update_act_modal_eliminar;
    private Button a_update_act_modal_limpiar_campos;
    private Button a_update_act_modal_guardar;
    private Button a_update_act_modal_quitar_de_evento;


    // Acceso a datos
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Actividad_act actividadEnProceso;
    private Actividad_act actividad_push;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public A_update_act_modal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A_update_act_modal.
     */
    // TODO: Rename and change types and number of parameters
    public static A_update_act_modal newInstance(String param1, String param2) {
        A_update_act_modal fragment = new A_update_act_modal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actividadEnProceso = (Actividad_act) getArguments().getSerializable("actividadParaAUpdateActModal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_update_act_modal, container, false);
        asignacionDeLosCamposDeAAddEve(view);
        rellenacionDeLosCamposDeAAddEve_act(actividadEnProceso);

        // Actividad_act actividad_push = recuperacionAct();


        a_update_act_modal_limpiar_campos.setOnClickListener(view12 -> limpiarAct());
        a_update_act_modal_guardar.setOnClickListener(view12 -> {
            guardarAct();
            A_update_act_modal.this.getDialog().cancel();
        });
        a_update_act_modal_eliminar.setOnClickListener(view12 -> {
            eliminarAct(view12);
            A_update_act_modal.this.getDialog().cancel();
        });
        a_update_act_modal_quitar_de_evento.setOnClickListener(view12 -> {
            guardarAct();
            A_update_act_modal.this.getDialog().cancel();
        });

        return view;
    }

    private void guardarAct() {
        actividad_push = recuperacionAct();
        actividad_push.setId_eve(0);
        fbf.collection("actividad_act").document(Integer.toString(actividadEnProceso.getId_act())).set(actividad_push);
        Toast.makeText(getActivity(), "La actividad se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void eliminarAct(View v) {
        fbf.collection("actividad_act").document(Integer.toString(actividadEnProceso.getId_act()))
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(v.getContext(), "Actividad eliminado con éxito!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(v.getContext(), "Ha ocurrido un error eliminando la actividad", Toast.LENGTH_SHORT).show();
                });
    }

    private void asignacionDeLosCamposDeAAddEve(View view) {
        a_update_act_modal_nombre_act = view.findViewById(R.id.a_update_act_modal_etx_nombre_act);
        a_update_act_modal_actividadtipo_act = view.findViewById(R.id.a_update_act_modal_etx_actividadtipo_act);
        a_update_act_modal_fecha_act = view.findViewById(R.id.a_update_act_modal_etx_fecha_act);
        a_update_act_modal_nivel_act = view.findViewById(R.id.a_update_act_modal_etx_nivel_act);
        a_update_act_modal_distancia_act = view.findViewById(R.id.a_update_act_modal_etx_distancia_act);
        a_update_act_modal_desnivel_act = view.findViewById(R.id.a_update_act_modal_etx_desnivel_act);
        a_update_act_modal_horas_act = view.findViewById(R.id.a_update_act_modal_etx_horas_act);
        a_update_act_modal_wikiloc_act = view.findViewById(R.id.a_update_act_modal_etx_wikiloc_act);
        a_update_act_modal_eliminar = view.findViewById(R.id.a_update_act_modal_btn_eliminar);
        a_update_act_modal_limpiar_campos = view.findViewById(R.id.a_update_act_modal_btn_limpiar_campos);
        a_update_act_modal_guardar = view.findViewById(R.id.a_update_act_modal_btn_guardar);
        a_update_act_modal_quitar_de_evento = view.findViewById(R.id.a_update_act_modal_btn_quitar_de_evento);
    }

    private void rellenacionDeLosCamposDeAAddEve_act(Actividad_act actividadEnProceso) {
        a_update_act_modal_nombre_act.setText(actividadEnProceso.getNombre_act());
        a_update_act_modal_actividadtipo_act.setText(actividadEnProceso.getActividadtipo_act());
        a_update_act_modal_fecha_act.setText(actividadEnProceso.getFecha_act());
        a_update_act_modal_nivel_act.setText(actividadEnProceso.getNivel_act());
        a_update_act_modal_distancia_act.setId(actividadEnProceso.getDistancia_act());
        a_update_act_modal_desnivel_act.setId(actividadEnProceso.getDesnivel_act());
        a_update_act_modal_horas_act.setId(actividadEnProceso.getHoras_act());
        a_update_act_modal_wikiloc_act.setText(actividadEnProceso.getWikiloc_act());
    }

    private Actividad_act recuperacionAct() {
        Actividad_act act = new Actividad_act();
        act.setNombre_act(a_update_act_modal_nombre_act.getText().toString().trim());
        act.setActividadtipo_act(a_update_act_modal_actividadtipo_act.getText().toString().trim());
        act.setFecha_act(a_update_act_modal_fecha_act.getText().toString().trim());
        act.setNivel_act(a_update_act_modal_nivel_act.getText().toString().trim());
        act.setDistancia_act(a_update_act_modal_distancia_act.getId());
        act.setDesnivel_act(a_update_act_modal_desnivel_act.getId());
        act.setHoras_act(a_update_act_modal_horas_act.getId());
        act.setWikiloc_act(a_update_act_modal_wikiloc_act.getText().toString().trim());
        return act;
    }

    private void limpiarAct() {
        a_update_act_modal_nombre_act.getText().clear();
        a_update_act_modal_actividadtipo_act.getText().clear();
        a_update_act_modal_fecha_act.getText().clear();
        a_update_act_modal_nivel_act.getText().clear();
        a_update_act_modal_distancia_act.getText().clear();
        a_update_act_modal_desnivel_act.getText().clear();
        a_update_act_modal_horas_act.getText().clear();
        a_update_act_modal_wikiloc_act.getText().clear();
    }
}