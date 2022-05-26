package com.appvisibility.apptravel002.ui.controller;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05_2 extends DialogFragment {

    // Campos de xml
    private Button v05_2_adelante, v05_2_atras;
    private TextView v05_2_listaPlazas;
    private String informeCoche = "";

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private Context mContext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public V_05_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05_2.
     */
    // TODO: Rename and change types and number of parameters
    public static V_05_2 newInstance(String param1, String param2) {
        V_05_2 fragment = new V_05_2();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_2, container, false);

        Bundle bundlePersonasEnCoche = getArguments();
        //Recuperamos las Plazas del Coche
        personasEnCoche = bundlePersonasEnCoche.getParcelableArrayList("personaParaV_05_2");

        Bundle bundleEvento = getArguments();
        //Recuperamos el Evento
//        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05_2");
        eventoEnProceso = V_03.eventoEnProceso;
        bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);

        v05_2_listaPlazas = view.findViewById(R.id.v05_2_txv_listaPlazas);

        for (Persona_prs i: personasEnCoche){
            informeCoche += i.getApodo_prs() + "\n";
        }

        v05_2_listaPlazas.setText(informeCoche);

        // Botones
        v05_2_adelante = view.findViewById(R.id.v05_2_btn_confirmar);
        v05_2_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if()
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v03);
            }
        });

        v05_2_atras = view.findViewById(R.id.v05_2_btn_volver);
        v05_2_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v03, bundleEvento);
            }
        });

        return view;
    }//Fin de constructor
}