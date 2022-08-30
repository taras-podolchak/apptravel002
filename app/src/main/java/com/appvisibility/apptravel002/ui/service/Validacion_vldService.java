package com.appvisibility.apptravel002.ui.service;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Contacto_cnt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Validacion_vldService#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Validacion_vldService extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String tituloListaCargo_cnt;

    // TODO: Rename and change types of parameters
    private Contacto_cnt mParam1;
    private String mParam2;

    // Entities
    private static Contacto_cnt contactoEnProceso;

    public Validacion_vldService() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2 Parameter 2.
     * @return A new instance of fragment Validacion_vldService.
     */
    // TODO: Rename and change types and number of parameters
    public static Validacion_vldService newInstance(Bundle bundleContacto, String param2) {
        Validacion_vldService fragment = new Validacion_vldService();
        contactoEnProceso = (Contacto_cnt) bundleContacto.getSerializable("contactoParaValidacion");
        tituloListaCargo_cnt = param2;
/*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
 */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Contacto_cnt) getArguments().getSerializable(String.valueOf(ARG_PARAM1));
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }//Fin de constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_2_modal_cnt, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    public static Boolean validarCargo() {
        if (contactoEnProceso.getCargo_cnt().equalsIgnoreCase(tituloListaCargo_cnt)) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarNombre() {
        if (contactoEnProceso.getNombre_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getNombre_cnt() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarApellido1() {
        if (contactoEnProceso.getApellido1_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getApellido1_cnt() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarApellido2() {
        if (contactoEnProceso.getApellido2_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getApellido2_cnt() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarMovil() {
        if (contactoEnProceso.getMovil_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getMovil_cnt() == null ||
                !contactoEnProceso.getMovil_cnt().matches("^[+]*(\\d{2,3})*[6]\\d{8,10}$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarTelefono() {
        if (!contactoEnProceso.getTelefono_cnt().matches("^[+]*(\\d{11,14})*$")) {
            return false;
        } else {
            return true;
        }
    }
//  http://w3.unpocodetodo.info/utiles/regex-ejemplos.php?type=email
    public static Boolean validarEmail() {
        if (contactoEnProceso.getEmail_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getEmail_cnt() == null ||
                !contactoEnProceso.getEmail_cnt().matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")) {
            return false;
        } else {
            return true;
        }
    }
}