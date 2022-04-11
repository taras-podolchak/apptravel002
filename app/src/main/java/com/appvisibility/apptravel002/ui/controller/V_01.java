package com.appvisibility.apptravel002.ui.controller;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;


public class V_01 extends Fragment implements RadioButton.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO:los campos de xml
    private Button v01_buscar_actividades;

    //TODO:servise
    private int resultado;

    public V_01() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_v_01, container, false);

        ((RadioButton) view.findViewById(R.id.v01_rbt_excursiones_un_dia)).setOnCheckedChangeListener(this);
        ((RadioButton) view.findViewById(R.id.v01_rbt_aventuras_largas)).setOnCheckedChangeListener(this);
        ((RadioButton) view.findViewById(R.id.v01_rbt_fin_semana)).setOnCheckedChangeListener(this);

        v01_buscar_actividades = view.findViewById(R.id.v01_btn_buscar_actividades);
        v01_buscar_actividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (resultado == 0) {
                    Toast.makeText(getActivity(), "Por favor elige la actividad", Toast.LENGTH_SHORT).show();
                }
                if (resultado == 1) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_inicio_v01_to_nav_v02);
                    Toast.makeText(getActivity(), "Excursiones de un día", Toast.LENGTH_SHORT).show();
                }
                if (resultado == 2) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_inicio_v01_to_nav_v02);
                    Toast.makeText(getActivity(), "Fin de semana", Toast.LENGTH_SHORT).show();
                }
                if (resultado == 3) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_inicio_v01_to_nav_v02);
                    Toast.makeText(getActivity(), "Aventuras mas largas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Check which radio button was clicked
        switch (buttonView.getId()) {
            case R.id.v01_rbt_excursiones_un_dia:
                if (isChecked)
                    Toast.makeText(getActivity(), "v01_radioButton_exurciones_de_un_dia", Toast.LENGTH_SHORT).show();
                resultado = 1;
                break;
            case R.id.v01_rbt_fin_semana:
                if (isChecked)
                    Toast.makeText(getActivity(), "v01_radioButton_fin_de_semana", Toast.LENGTH_SHORT).show();
                resultado = 2;
                break;
            case R.id.v01_rbt_aventuras_largas:
                if (isChecked)
                    Toast.makeText(getActivity(), "v01_radioButton_aventuras_mas_largas", Toast.LENGTH_SHORT).show();
                resultado = 3;
                break;
        }
    }
}