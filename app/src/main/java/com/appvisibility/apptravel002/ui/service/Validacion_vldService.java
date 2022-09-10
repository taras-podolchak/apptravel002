package com.appvisibility.apptravel002.ui.service;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

// https://regexr.com/346hf
// https://www.w3schools.com/java/java_regex.asp
    public static Boolean validarApodo() {
        if (identidadEnProceso.getApodo_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getApodo_prs() == null ||
                !identidadEnProceso.getApodo_prs().matches("^([0-9]*[a-z]*[A-Z]*){8,10}$")) {
            return false;
        } else {
            return true;
        }
    }
// https://es.stackoverflow.com/questions/224929/c%C3%B3mo-validar-un-nombre-con-expresiones-regulares
    public static Boolean validarNombre() {
        if (identidadEnProceso.getNombre_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getNombre_prs() == null ||
                !identidadEnProceso.getNombre_prs().matches("^(([A-ZÑÇ'-ÄËÏÖÜÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙ]*[a-zñç'äëïöüáéíóúáéíóúâêîôûàèìòù]*)*\\s*)*$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarApellido1() {
        if (identidadEnProceso.getApellido1_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getApellido1_prs() == null ||
                !identidadEnProceso.getApellido1_prs().matches("^(([A-ZÑÇ'-ÄËÏÖÜÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙ]*[a-zñç'äëïöüáéíóúáéíóúâêîôûàèìòù]*)*\\s*)*$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarApellido2() {
        if (identidadEnProceso.getApellido2_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getApellido2_prs() == null ||
                !identidadEnProceso.getApellido2_prs().matches("^(([A-ZÑÇ'-ÄËÏÖÜÁÉÍÓÚÂÊÎÔÛÀÈÌÒÙ]*[a-zñç'äëïöüáéíóúáéíóúâêîôûàèìòù]*)*\\s*)*$")) {
            return false;
        } else {
            return true;
        }
    }
// https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
    public static Boolean validarContrasenna() {
        if (identidadEnProceso.getContrasenna_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getContrasenna_prs() == null ||
                !identidadEnProceso.getContrasenna_prs().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")) {
            return false;
        } else {
            return true;
        }
    }
// https://es.stackoverflow.com/questions/5535/sabeis-alguna-expresi%C3%B3n-regular-para-validar-el-nie
    public static Boolean validarDni() {
        if (identidadEnProceso.getDni_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getDni_prs() == null ||
                !identidadEnProceso.getDni_prs().matches("^[0-9]{8}[A-Z]$")) {
            return false;
        } else {
//            String let = identidadEnProceso.getDni_prs().substring(3, 4);
            String numero = identidadEnProceso.getDni_prs().substring(0, identidadEnProceso.getDni_prs().length()-1);
            numero = numero.replace('X', (char) 0);
            numero = numero.replace('Y', (char) 1);
            numero = numero.replace('Z', (char) 2);
            String letraActual = identidadEnProceso.getDni_prs().substring(identidadEnProceso.getDni_prs().length()-1);
            int digito = Integer.parseInt(numero) % 23;
            String letra = "TRWAGMYFPDXBNJZSQVHLCKET";
            letra = letra.substring(digito, digito+1);
            if (!letra.equalsIgnoreCase(letraActual)) {
                return false;
            } else {
                return true;
            }
        }
    }
// https://es.stackoverflow.com/questions/5535/sabeis-alguna-expresi%C3%B3n-regular-para-validar-el-nie
// https://trellat.es/funcion-para-validar-dni-o-nie-en-javascript/
    public static Boolean validarNieNif() {
        if (identidadEnProceso.getDni_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getDni_prs() == null ||
                !identidadEnProceso.getDni_prs().matches("^[XYZ][0-9]{7}[A-Z]$")) {
            return false;
        } else {
            String numero = identidadEnProceso.getDni_prs().substring(0, identidadEnProceso.getDni_prs().length()-1);
            numero = numero.replace('X', (char) 0);
            numero = numero.replace('Y', (char) 1);
            numero = numero.replace('Z', (char) 2);
            String letraActual = identidadEnProceso.getDni_prs().substring(identidadEnProceso.getDni_prs().length()-1, identidadEnProceso.getDni_prs().length());
            int digito = Integer.parseInt(numero) % 23;
            String letra = "TRWAGMYFPDXBNJZSQVHLCKET";
            letra = letra.substring(digito, digito+1);
            if (!letra.equalsIgnoreCase(letraActual)) {
                return false;
            } else {
                return true;
            }
        }
    }
// https://stackoverflow.com/questions/40647728/regex-for-passport-number
// https://trellat.es/funcion-para-validar-dni-o-nie-en-javascript/
    public static Boolean validarPasaporte() {
        if (identidadEnProceso.getDni_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getDni_prs() == null ||
                !identidadEnProceso.getDni_prs().matches("^(?!^0+$)[a-zA-Z0-9]{3,20}$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarNacionalidad() {
        if (identidadEnProceso.getNacionalidad_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getNacionalidad_prs() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarRazonsocial() {
        if (identidadEnProceso.getDni_prs().equalsIgnoreCase("") ||
                identidadEnProceso.getDni_prs() == null) {
            return false;
        } else {
            return true;
        }
    }
// https://stackoverflow.com/questions/44656264/iban-regex-design
// https://www.programacionenjava.com/blog/2008/03/26/javabasico/algoritmo-de-verificacion-de-cuenta-de-banco-valida/
// https://stackoverflow.com/questions/20375585/how-to-check-an-iban-number-using-the-apache-ibancheckdigit
// https://commons.apache.org/proper/commons-validator/
// https://commons.apache.org/proper/commons-validator/jacoco/org.apache.commons.validator.routines.checkdigit/IBANCheckDigit.java.html
    public static Boolean validarNumerocta() {
        if (!identidadEnProceso.getNumerocta_prs().matches("^[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){5}(?:[ ]?[0-9]{1,2})?$")) {
            IBANCheckDigit iBANCheckDigit = new IBANCheckDigit();
            if (iBANCheckDigit.isValid(identidadEnProceso.getNumerocta_prs())) {
                return true;
            } else {
                return identidadEnProceso.getNumerocta_prs().isEmpty();
            }
        } else {
            return false;
        }
    }

    public static Boolean validarCargoContacto() {
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

    public static Boolean validarApellido1Contacto() {
        if (contactoEnProceso.getApellido1_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getApellido1_cnt() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarApellido2Contacto() {
        if (contactoEnProceso.getApellido2_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getApellido2_cnt() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarMovilContacto() {
// Regex Teléfono Movil: Prefijo de país seguido de un 6 más 8-10 dígitos
        if (contactoEnProceso.getMovil_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getMovil_cnt() == null ||
                !contactoEnProceso.getMovil_cnt().matches("^([+]*(\\s)*(\\d{2,3}))*\\s*[6](?:\\s*\\d){8,10}$")) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validarTelefonoContacto() {
        if (!contactoEnProceso.getTelefono_cnt().matches("^[+]*(\\d{10,14})*$")) {
            return false;
        } else {
            return true;
        }
    }
//  http://w3.unpocodetodo.info/utiles/regex-ejemplos.php?type=email
    public static Boolean validarEmailContacto() {
        if (contactoEnProceso.getEmail_cnt().equalsIgnoreCase("") ||
                contactoEnProceso.getEmail_cnt() == null ||
                !contactoEnProceso.getEmail_cnt().matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$")) {
            return false;
        } else {
            return true;
        }
    }
}