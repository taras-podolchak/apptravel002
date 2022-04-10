package com.appvisibility.apptravel002.ui.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_06#newInstance} factory method to
 * create an instance of this fragment.
 */

public class V_06 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO:los campos de xml
    private Button v06_activar_aviso, v06_mas_actividades;

    public V_06() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_06.
     */
    // TODO: Rename and change types and number of parameters
    public static V_06 newInstance(String param1, String param2) {
        V_06 fragment = new V_06();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        fragment.setArguments(bundle);
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
        View view = inflater.inflate(R.layout.fragment_v_06, container, false);
        v06_activar_aviso = view.findViewById(R.id.v06_btn_activar_aviso);    //button v01_boton_buscaar_actividades
        v06_activar_aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Se activará aviso", Toast.LENGTH_SHORT).show();
            }
        });
        v06_mas_actividades = view.findViewById(R.id.v06_btn_buscar_mas_actividades);
        v06_mas_actividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v06_to_nav_inicio_v01);
            }
        });
        return view;
    }
}