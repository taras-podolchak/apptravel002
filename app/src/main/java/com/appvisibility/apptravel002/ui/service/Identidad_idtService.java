package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;
import static com.appvisibility.apptravel002.ui.service.Persona_prsService.personaUserU;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.ACV_usuario;
import com.appvisibility.apptravel002.ui.controller.V_05;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Identidad_idtService#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Identidad_idtService extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    public static EditText v01_1_apodo_prs;
    public static EditText v01_1_nombre_prs;
    public static EditText v01_1_apellido1_prs;
    public static EditText v01_1_apellido2_prs;
    public static EditText v01_1_contrasenna_prs;
    public static EditText v01_1_contrasennaConfirmar_prs;
    public static Switch v01_1_recordarcontrasenna_prs;
    public static Spinner v01_1_actividadtipo_prs;
    public static Spinner v01_1_documentotipo_prs;
    public static EditText v01_1_dni_prs;
    public static EditText v01_1_nacionalidad_prs;
    public static EditText v01_1_razonsocial_prs;
    public static EditText v01_1_numerocta_prs;

    // Entities
    private Persona_prs personaUser;
    private Persona_prs identidadEnProceso;
    private String apodo_prs;
    private String nombre_prs;
    private String apellido1_prs;
    private String apellido2_prs;
    private String contrasenna_prs;
    private String contrasennaConfirmar_prs;
    private Boolean recordarcontrasenna_prs;
    private String actividadtipo_prs;
    private String documentotipo_prs;
    private String dni_prs;
    private String nacionalidad_prs;
    private String razonsocial_prs;
    private String numerocta_prs;
    private Boolean validacion;
    private ArrayAdapter<String> arrayAdapter_act;
    private ArrayAdapter<String> arrayAdapter_idt;
    private Boolean datosActualizados;

    public Identidad_idtService() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Identidad_idtService.
     */
    // Rename and change types and number of parameters
    public static Identidad_idtService newInstance(String param1, String param2) {
        Identidad_idtService fragment = new Identidad_idtService();
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
        View view = inflater.inflate(R.layout.fragment_acv_identidad, container, false);
        // Inflate the layout for this fragment

//https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
//Recuperamos los datos del Usuario activo
        MainActivity_val activity = (MainActivity_val) view.getContext();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");

        Bundle bundleIdentidad = new Bundle();

        v01_1_apodo_prs = view.findViewById(R.id.v01_1_etx_apodo_prs);
        v01_1_nombre_prs = view.findViewById(R.id.v01_1_etx_nombre_prs);
        v01_1_apellido1_prs = view.findViewById(R.id.v01_1_etx_apellido1_prs);
        v01_1_apellido2_prs = view.findViewById(R.id.v01_1_etx_apellido2_prs);
        v01_1_contrasenna_prs = view.findViewById(R.id.v01_1_etx_contrasenna_prs);
        v01_1_contrasennaConfirmar_prs = view.findViewById(R.id.v01_1_etx_contrasennaConfirmar_prs);
        v01_1_recordarcontrasenna_prs = view.findViewById(R.id.v01_1_swc_recordarcontrasenna_prs);
        v01_1_actividadtipo_prs = view.findViewById(R.id.v01_1_spn_actividadtipo_prs);
        v01_1_documentotipo_prs = view.findViewById(R.id.v01_1_spn_documentotipo_prs);
        v01_1_dni_prs = view.findViewById(R.id.v01_1_etx_dni_prs);
        v01_1_nacionalidad_prs = view.findViewById(R.id.v01_1_etx_nacionalidad_prs);
        v01_1_razonsocial_prs = view.findViewById(R.id.v01_1_etx_razonsocial_prs);
        v01_1_numerocta_prs = view.findViewById(R.id.v01_1_etx_numerocta_prs);

        recuperarIdentidadActual(view);

        identidadEnProceso = new Persona_prs(apodo_prs, nombre_prs, apellido1_prs, apellido2_prs, contrasenna_prs, recordarcontrasenna_prs, actividadtipo_prs, documentotipo_prs, dni_prs, nacionalidad_prs, razonsocial_prs, numerocta_prs);
        mostrarIdentidadEnProceso(view);
        bundleIdentidad.putSerializable("identidadParaValidacion", identidadEnProceso);

        String [] actividadtipos_prs = view.getResources().getStringArray(R.array.actividadTipos_prs);

        arrayAdapter_act = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, actividadtipos_prs);
        arrayAdapter_act.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String [] documentotipos_prs = view.getResources().getStringArray(R.array.documentoTipos_prs);

        arrayAdapter_idt = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, documentotipos_prs);
        arrayAdapter_idt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AlertDialog.Builder modalFormIdentificación = new AlertDialog.Builder(view.getContext());

        apodo_prs = (apodo_prs == null)? "":apodo_prs;
        nombre_prs = (nombre_prs == null)? "":nombre_prs;
        apellido1_prs = (apellido1_prs == null)? "":apellido1_prs;
        apellido2_prs = (apellido2_prs == null)? "":apellido2_prs;
        contrasenna_prs = (contrasenna_prs == null)? "":contrasenna_prs;
//        recordarcontrasenna_prs = (recordarcontrasenna_prs == false)? false:true;

        int indexOfPreviousSelection;
        v01_1_actividadtipo_prs.setAdapter(arrayAdapter_act);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        v01_1_actividadtipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_act.getPosition(personaUser.getActividadtipo_prs());
        v01_1_actividadtipo_prs.setSelection(indexOfPreviousSelection);

        v01_1_documentotipo_prs.setAdapter(arrayAdapter_idt);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        v01_1_documentotipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_idt.getPosition(personaUser.getActividadtipo_prs());
        v01_1_documentotipo_prs.setSelection(indexOfPreviousSelection);

//        actividadtipo_prs = (actividadtipos_prs[indexOfPreviousSelection] == null)? "":actividadtipo_prs;
//        documentotipo_prs = (documentotipos_prs[indexOfPreviousSelection] == null)? "":documentotipo_prs;
        dni_prs = (dni_prs == null)? "":dni_prs;
        nacionalidad_prs = (nacionalidad_prs == null)? "":nacionalidad_prs;
        razonsocial_prs = (razonsocial_prs == null)? "":razonsocial_prs;
        numerocta_prs = (numerocta_prs == null)? "":numerocta_prs;
        identidadEnProceso = new Persona_prs(apodo_prs, nombre_prs, apellido1_prs, apellido2_prs, contrasenna_prs, recordarcontrasenna_prs, actividadtipo_prs, documentotipo_prs, dni_prs, nacionalidad_prs, razonsocial_prs, numerocta_prs);
        mostrarIdentidadEnProceso(view);
        bundleIdentidad.putSerializable("identidadParaValidacion", identidadEnProceso);

        modalFormIdentificación.setIcon(R.drawable.ico_carnetoutline_blue);
        modalFormIdentificación.setTitle("IDENTIDAD");

        if (sesionIniciada == view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
// https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu
            v01_1_razonsocial_prs.setVisibility(View.GONE);
            v01_1_numerocta_prs.setVisibility(View.GONE);
        } else if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_alojamiento)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)) {
//            V_05.v05_recomendacionContacto.setVisibility(View.GONE);
        }

        modalFormIdentificación.setPositiveButton("Confirmar", null);
        modalFormIdentificación.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(view)
                .create();

// https://stackoverflow.com/questions/2620444/how-to-prevent-a-dialog-from-closing-when-a-button-is-clicked
// https://stackoverflow.com/questions/42214856/alertdialog-setonshowlistener-never-called
// Modificamos la funcionalidad del positiveButton para que no salga de la ventana modal sin validar los campos del formulario
        final AlertDialog positiveButtonCambiado = modalFormIdentificación.create();
        positiveButtonCambiado.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = positiveButtonCambiado.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prepararValidacion(view, null);
                        bundleIdentidad.putSerializable("personaUserParaValidacion", personaUser);
                        Validacion_vldService.newInstance(null, null, bundleIdentidad);
// https://www.android--code.com/2015/08/android-edittext-border-color_20.html
// https://stackoverflow.com/questions/34075131/how-to-set-a-button-border-color-programmatically-in-android
// You can create a layout for this. in your code
                        if (!Validacion_vldService.validarApodo()) {
                            v01_1_apodo_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_apodo_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
                        if (!Validacion_vldService.validarNombre()) {
                            v01_1_nombre_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_nombre_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
                        if (!Validacion_vldService.validarApellido1()) {
                            v01_1_apellido1_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_apellido1_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
/*
                            if (!Validacion_vldService.validarApellido2()) {
                                v01_1_apellido2_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                                validacion = false;
                            } else {
                                v01_1_apellido2_prs.setBackgroundResource(0);
                                validacion = !validacion? false: true;
                            }
*/
                        if (!Validacion_vldService.validarContrasenna()) {
                            v01_1_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_contrasenna_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
                        if (!Validacion_vldService.validarDni()) {
                            v01_1_dni_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_dni_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
                        if (!Validacion_vldService.validarNumerocta()) {
                            v01_1_numerocta_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                            validacion = false;
                        } else {
                            v01_1_numerocta_prs.setBackgroundResource(0);
                            validacion = !validacion? false: true;
                        }
                        if (!validacion){
                            Toast.makeText(view.getContext(), "La identificación no se puede guardar, datos incorrectos", Toast.LENGTH_LONG).show();
                        } else {
                            validacion = false;
                            volcarIdentidad(view, null);
                            datosActualizados = personaUserU(datosActualizados, personaUser);
                            if (datosActualizados){
                                Toast.makeText(view.getContext(), "La identidad se ha guardado satisfactoriamente", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(view.getContext(), "La identidad no se han podido guardar", Toast.LENGTH_LONG).show();
                            }
                            positiveButtonCambiado.dismiss();
                        }

// https://stackoverflow.com/questions/42397915/how-to-pass-string-from-one-fragment-to-another-in-android
// Permite mostrar en la pantalla padre el resultado de la selección de un contacto de la agenda
                        ACV_usuario.newInstance(null, null);
                        ACV_usuario.acv_usuario_nombre_prs.setText(identidadEnProceso.getNombre_prs());

//                        V_05.newInstance(contactoElegidoResultado, null);
//                        V_05.v05_2_muestraContacto1Elegido.setText(contactoElegidoResultado);
//                        alertDialog.dismiss();
                    }
                });
            }
        });
        positiveButtonCambiado.show();

        return view;
    }//Fin de constructor

    public void volcarIdentidad(View view, String innecesarioAqui) {
        personaUser.setApodo_prs(identidadEnProceso.getApodo_prs());
        personaUser.setNombre_prs(identidadEnProceso.getNombre_prs());
        personaUser.setApellido1_prs(identidadEnProceso.getApellido1_prs());
        personaUser.setApellido2_prs(identidadEnProceso.getApellido2_prs());
        personaUser.setContrasenna_prs(identidadEnProceso.getContrasenna_prs());
        personaUser.setRecordarcontrasenna_prs(identidadEnProceso.isRecordarcontrasenna_prs());
        personaUser.setActividadtipo_prs(identidadEnProceso.getActividadtipo_prs());
        personaUser.setDocumentotipo_prs(identidadEnProceso.getDocumentotipo_prs());
        personaUser.setDni_prs(identidadEnProceso.getDni_prs());
        personaUser.setNacionalidad_prs(identidadEnProceso.getNacionalidad_prs());
        personaUser.setRazonsocial_prs(identidadEnProceso.getRazonsocial_prs());
        personaUser.setNumerocta_prs(identidadEnProceso.getNumerocta_prs());
//        contacto1ElegidoResultado = identidadEnProceso.getNombre_prs() + " " + identidadEnProceso.getApellido1_prs();
    }

    public void prepararValidacion(View view, String innecesarioAqui){
        int indexOfPreviousSelection;
        identidadEnProceso.setApodo_prs(v01_1_apodo_prs.getText().toString());
        identidadEnProceso.setNombre_prs(v01_1_nombre_prs.getText().toString());
        identidadEnProceso.setApellido1_prs(v01_1_apellido1_prs.getText().toString());
        identidadEnProceso.setApellido2_prs(v01_1_apellido2_prs.getText().toString());
        identidadEnProceso.setContrasenna_prs(v01_1_contrasenna_prs.getText().toString());
//        identidadEnProceso.setRecordarcontrasenna_prs(v01_1_recordarcontrasenna_prs.setChecked(true));
        indexOfPreviousSelection = v01_1_actividadtipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = v01_1_documentotipo_prs.getSelectedItemPosition();
        identidadEnProceso.setDni_prs(v01_1_dni_prs.getText().toString());
        identidadEnProceso.setNacionalidad_prs(v01_1_nacionalidad_prs.getText().toString());
        identidadEnProceso.setRazonsocial_prs(v01_1_razonsocial_prs.getText().toString());
        identidadEnProceso.setNumerocta_prs(v01_1_numerocta_prs.getText().toString());
    }

    public void recuperarIdentidadActual(View view) {
        apodo_prs = (personaUser.getApodo_prs() == null)? "" : personaUser.getApodo_prs();
        nombre_prs = (personaUser.getNombre_prs() == null)? "" : personaUser.getNombre_prs();
        apellido1_prs = (personaUser.getApellido1_prs() == null)? "" : personaUser.getApellido1_prs();
        apellido2_prs = (personaUser.getApellido2_prs() == null)? "" : personaUser.getApellido2_prs();
        contrasenna_prs = (personaUser.getContrasenna_prs() == null)? "" : personaUser.getContrasenna_prs();
        recordarcontrasenna_prs = personaUser.isRecordarcontrasenna_prs();
        actividadtipo_prs = (personaUser.getActividadtipo_prs() == null)? "" : personaUser.getActividadtipo_prs();
        documentotipo_prs = (personaUser.getDocumentotipo_prs() == null)? "" : personaUser.getDocumentotipo_prs();
        dni_prs = (personaUser.getDni_prs() == null)? "" : personaUser.getDni_prs();
        nacionalidad_prs = (personaUser.getNacionalidad_prs() == null)? "" : personaUser.getNacionalidad_prs();
        razonsocial_prs = (personaUser.getRazonsocial_prs() == null)? "" : personaUser.getRazonsocial_prs();
        numerocta_prs = (personaUser.getContacto1Email_prs() == null)? "" : personaUser.getNumerocta_prs();
    }

    public void mostrarIdentidadEnProceso (View view) {
        v01_1_apodo_prs.setText(identidadEnProceso.getApodo_prs());
        v01_1_nombre_prs.setText(identidadEnProceso.getNombre_prs());
        v01_1_apellido1_prs.setText(identidadEnProceso.getApellido1_prs());
        v01_1_apellido2_prs.setText(identidadEnProceso.getApellido2_prs());
        v01_1_contrasenna_prs.setText(identidadEnProceso.getContrasenna_prs());
//        v01_1_actividadtipo_prs.setText(identidadEnProceso.getActividadtipo_prs());
 //       v01_1_documentotipo_prs.setText(identidadEnProceso.getDocumentotipo_prs());
        v01_1_dni_prs.setText(identidadEnProceso.getDni_prs());
        v01_1_nacionalidad_prs.setText(identidadEnProceso.getNacionalidad_prs());
        v01_1_razonsocial_prs.setText(identidadEnProceso.getRazonsocial_prs());
        v01_1_numerocta_prs.setText(identidadEnProceso.getNumerocta_prs());
    }

}