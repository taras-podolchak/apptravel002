package com.appvisibility.apptravel002.ui.service;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Contacto_cnt;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

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
//    private Contacto_cnt mParam1;
    private String mParam1;
    private String mTituloListaCargo_cnt;

    // Entities
    private static Contacto_cnt contactoEnProceso;
    private static Persona_prs identidadEnProceso;

    public Validacion_vldService() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tituloListaCargo_cnt Parameter 2.
     * @return A new instance of fragment Validacion_vldService.
     */
    // TODO: Rename and change types and number of parameters
    public static Validacion_vldService newInstance(Bundle bundleContacto, String tituloListaCargo_cnt, Bundle bundleIdentidad) {
        Validacion_vldService fragment = new Validacion_vldService();
        if (bundleContacto != null) {
            contactoEnProceso = (Contacto_cnt) bundleContacto.getSerializable("contactoParaValidacion");
        }
        tituloListaCargo_cnt = tituloListaCargo_cnt;
        if (bundleIdentidad != null) {
            identidadEnProceso = (Persona_prs)  bundleIdentidad.getSerializable("identidadParaValidacion");
        }
/*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, tituloListaCargo_cnt);
        fragment.setArguments(args);
 */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mTituloListaCargo_cnt = getArguments().getString(ARG_PARAM2);
        }
    }//Fin de constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_2_modal_cnt, container, false);
        // Inflate the layout for this fragment
        return view;
    }
// TODO
    public static Boolean validarApodo() {
        if (identidadEnProceso.getApodo_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getApodo_prs() == null) {
            return false;
        } else {
            return true;
        }
    }
// TODO
    public static Boolean validarNombre() {
        if (identidadEnProceso.getNombre_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getNombre_prs() == null) {
            return false;
        } else {
            return true;
        }
    }

// TODO
    public static Boolean validarContrasenna() {
        if (identidadEnProceso.getContrasenna_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getContrasenna_prs() == null) {
            return false;
        } else {
            return true;
        }
    }
// TODO
    public static Boolean validarDni() {
        if (identidadEnProceso.getDni_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getDni_prs() == null) {
            return false;
        } else {
            return true;
        }
    }
// TODO
    public static Boolean validarNumerocta() {
        if (identidadEnProceso.getNumerocta_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getNumerocta_prs() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarCargo() {
        if (contactoEnProceso.getCargo_cnt().equalsIgnoreCase(tituloListaCargo_cnt)) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarNombreContacto() {
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
// Regex Teléfono Movil: Prefijo de país seguido de un 6 más 8-10 dígitos
        if (contactoEnProceso.getMovil_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getMovil_cnt() == null ||
                !contactoEnProceso.getMovil_cnt().matches("^([+]*(\\s)*(\\d{2,3}))*\\s*[6](?:\\s*\\d){8,10}$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarTelefono() {
        if (!contactoEnProceso.getTelefono_cnt().matches("^[+]*(\\d{10,14})*$")) {
            return false;
        } else {
            return true;
        }
    }
//  http://w3.unpocodetodo.info/utiles/regex-ejemplos.php?type=email
    public static Boolean validarEmail() {
        if (contactoEnProceso.getEmail_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getEmail_cnt() == null ||
                !contactoEnProceso.getEmail_cnt().matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$")) {
            return false;
        } else {
            return true;
        }
    }
}