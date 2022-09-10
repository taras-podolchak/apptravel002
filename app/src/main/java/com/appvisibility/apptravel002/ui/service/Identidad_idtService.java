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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.ACV_usuario;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

import java.util.Arrays;
import java.util.List;

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

        v01_1_nacionalidad_prs.setVisibility(View.GONE);

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

        modalFormIdentificación.setIcon(R.drawable.ico_carnetoutline_blue);
        modalFormIdentificación.setTitle("IDENTIDAD");

        int indexOfPreviousSelection;
        v01_1_actividadtipo_prs.setAdapter(arrayAdapter_act);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        v01_1_actividadtipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_act.getPosition(identidadEnProceso.getActividadtipo_prs());
        v01_1_actividadtipo_prs.setSelection(indexOfPreviousSelection);

        v01_1_documentotipo_prs.setAdapter(arrayAdapter_idt);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        v01_1_documentotipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_idt.getPosition(identidadEnProceso.getDocumentotipo_prs());
        v01_1_documentotipo_prs.setSelection(indexOfPreviousSelection);

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
                        bundleIdentidad.putSerializable("identidadParaValidacion", identidadEnProceso);
                        Validacion_vldService.newInstance(null, null, bundleIdentidad);
                        validacion = secuenciaDeValidacion(view);
                        mostrarIdentidadEnProceso(view);

// https://stackoverflow.com/questions/42397915/how-to-pass-string-from-one-fragment-to-another-in-android
// Permite mostrar en la pantalla padre los datos filtrados del formulario
                        ACV_usuario.newInstance(null, null);
                        ACV_usuario.acv_usuario_apodo_prs.setText(identidadEnProceso.getApodo_prs());
                        ACV_usuario.acv_usuario_nombre_prs.setText(identidadEnProceso.getNombre_prs());
                        ACV_usuario.acv_usuario_apellido1_prs.setText(identidadEnProceso.getApellido1_prs());
                        ACV_usuario.acv_usuario_apellido2_prs.setText(identidadEnProceso.getApellido2_prs());
                        ACV_usuario.acv_usuario_actividadtipo_prs.setText(identidadEnProceso.getActividadtipo_prs());
                        ACV_usuario.acv_usuario_documentotipo_prs_lbl.setText(identidadEnProceso.getDocumentotipo_prs());
                        ACV_usuario.acv_usuario_dni_prs.setText(identidadEnProceso.getDni_prs());
                        ACV_usuario.acv_usuario_razonsocial_prs.setText(identidadEnProceso.getRazonsocial_prs());
                        ACV_usuario.acv_usuario_numerocta_prs.setText(identidadEnProceso.getNumerocta_prs());

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
/*
                        ACV_usuario.newInstance(null, null);
                        ACV_usuario.acv_usuario_txv_direccion_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_localidad_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_cpostal_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_pais_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_movil_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_email_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_telefono_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_web_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_numerocta_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_txv_alimentacion_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_swc_federado_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_swc_seguro_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_segurocompannia_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_seguropoliza_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_fechacaducidadseguro_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_txv_fechaalta_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_antiguedad_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_swc_condicioneslegales_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_swc_solicitabaja_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_fechabaja_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_txv_preferencia_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_fiabilidadpre_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_valoracionorgpre_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_antiguedadpre_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_volumencomprapre_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_cochepre_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nrelacionespre_prs.setText(identidadEnProceso.getNumerocta_prs());

                        ACV_usuario.acv_usuario_txv_nps01fecha_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nps01_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nps02fecha_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nps02_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nps03fecha_prs.setText(identidadEnProceso.getNumerocta_prs());
                        ACV_usuario.acv_usuario_txv_nps03_prs.setText(identidadEnProceso.getNumerocta_prs());
 */
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
        if (identidadEnProceso.isRecordarcontrasenna_prs()) {
            personaUser.setRecordarcontrasenna_prs(true);
        } else {
            personaUser.setRecordarcontrasenna_prs(false);
        }
        int indexOfPreviousSelection = v01_1_actividadtipo_prs.getSelectedItemPosition();
        personaUser.setActividadtipo_prs(arrayAdapter_act.getItem(indexOfPreviousSelection));
        indexOfPreviousSelection = v01_1_documentotipo_prs.getSelectedItemPosition();
        personaUser.setDocumentotipo_prs(arrayAdapter_idt.getItem(indexOfPreviousSelection));
        personaUser.setDni_prs(identidadEnProceso.getDni_prs().replaceAll("[\\s()-]", ""));
        personaUser.setNacionalidad_prs(identidadEnProceso.getNacionalidad_prs());
        personaUser.setRazonsocial_prs(identidadEnProceso.getRazonsocial_prs());
        personaUser.setNumerocta_prs(identidadEnProceso.getNumerocta_prs().replaceAll("[\\s()-]", ""));
    }

    public void prepararValidacion(View view, String innecesarioAqui){
        identidadEnProceso.setApodo_prs(v01_1_apodo_prs.getText().toString());
        identidadEnProceso.setNombre_prs(limpiarApellido(view, v01_1_nombre_prs.getText().toString()));
        identidadEnProceso.setApellido1_prs(limpiarApellido(view, v01_1_apellido1_prs.getText().toString()));
        identidadEnProceso.setApellido2_prs(limpiarApellido(view, v01_1_apellido2_prs.getText().toString()));
        identidadEnProceso.setContrasenna_prs(v01_1_contrasenna_prs.getText().toString());
        if (v01_1_recordarcontrasenna_prs.isChecked()) {
            identidadEnProceso.setRecordarcontrasenna_prs(true);
        } else {
            identidadEnProceso.setRecordarcontrasenna_prs(false);
        }
        int indexOfPreviousSelection = v01_1_actividadtipo_prs.getSelectedItemPosition();
        identidadEnProceso.setActividadtipo_prs(arrayAdapter_act.getItem(indexOfPreviousSelection));
        indexOfPreviousSelection = v01_1_documentotipo_prs.getSelectedItemPosition();
        identidadEnProceso.setDocumentotipo_prs(arrayAdapter_idt.getItem(indexOfPreviousSelection));
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
        if (identidadEnProceso.isRecordarcontrasenna_prs()) {
            v01_1_recordarcontrasenna_prs.setChecked(true);
        } else {
            v01_1_recordarcontrasenna_prs.setChecked(false);
        }
        v01_1_dni_prs.setText(identidadEnProceso.getDni_prs());
        v01_1_nacionalidad_prs.setText(identidadEnProceso.getNacionalidad_prs());
        v01_1_razonsocial_prs.setText(identidadEnProceso.getRazonsocial_prs());
        v01_1_numerocta_prs.setText(identidadEnProceso.getNumerocta_prs());
    }

    public String limpiarApellido(View view, String cadena) {
        List<String> prefijosApellidos = Arrays.asList(view.getResources().getStringArray(R.array.prefijosApellidos));
        String apellidoLimpio = cadena.trim();
        apellidoLimpio = apellidoLimpio.toLowerCase();
        apellidoLimpio = apellidoLimpio.replaceAll("-", " ### ");
        apellidoLimpio = apellidoLimpio.replaceAll("'", " €€€ ");
        List<String> apellidoArray = Arrays.asList(apellidoLimpio.split("[\\s()/]"));
        apellidoLimpio = "";
        for (int i=0; i <= apellidoArray.size()-1; i++){
            if (apellidoArray.get(i).matches("^[\\s.()/]$")) {
                apellidoArray.remove(i);
            }
            if (!prefijosApellidos.contains(apellidoArray.get(i))
                    && !apellidoArray.get(i).contains("€€€")
                    && !apellidoArray.get(i).contains("###")
                    && !apellidoArray.get(i).equalsIgnoreCase("")
            ) {
                apellidoLimpio += String.valueOf(apellidoArray.get(i).charAt(0)).toUpperCase() + apellidoArray.get(i).substring(1) + " ";
            } else if (apellidoArray.get(i).contains("###")) {
                apellidoLimpio += "-";
            } else if (apellidoArray.get(i).contains("€€€")) {
                apellidoLimpio += "'";
            } else if (prefijosApellidos.contains(apellidoArray.get(i))) {
                apellidoLimpio += String.valueOf(apellidoArray.get(i)) + " ";
            } else {
                apellidoLimpio += "";
            }
        }
        apellidoLimpio = apellidoLimpio.replaceAll(" -", "-");
        apellidoLimpio = apellidoLimpio.replaceAll(" '", "'");
        apellidoLimpio = apellidoLimpio.trim();
        return apellidoLimpio;
    }

    public Boolean secuenciaDeValidacion(View view) {
        Boolean validacion = false;
// https://www.android--code.com/2015/08/android-edittext-border-color_20.html
// https://stackoverflow.com/questions/34075131/how-to-set-a-button-border-color-programmatically-in-android
// You can create a layout for this. in your code
        if (!Validacion_vldService.validarApodo()) {
            v01_1_apodo_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            v01_1_apodo_prs.setBackgroundResource(0);
            validacion = true;
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
        if (!Validacion_vldService.validarApellido2()) {
            v01_1_apellido2_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            v01_1_apellido2_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }
        if (!Validacion_vldService.validarContrasenna()) {
            v01_1_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
            Toast.makeText(view.getContext(), "La contraseña debe contener al menos 8 caracteres incluyendo números, mayúsculas, minúsculas y caracteres especiales", Toast.LENGTH_LONG).show();
        } else {
            v01_1_contrasenna_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }
        contrasennaConfirmar_prs = (String.valueOf(v01_1_contrasennaConfirmar_prs.getText()) == null)? "" : String.valueOf(v01_1_contrasennaConfirmar_prs.getText());
        if (!contrasenna_prs.equals(contrasennaConfirmar_prs)) {
            v01_1_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            v01_1_contrasennaConfirmar_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            v01_1_contrasenna_prs.setBackgroundResource(0);
            v01_1_contrasennaConfirmar_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }

        int indexOfPreviousSelection = v01_1_documentotipo_prs.getSelectedItemPosition();
        switch (identidadEnProceso.getDocumentotipo_prs()) {
            case ("DNI"): {
                if (!Validacion_vldService.validarDni()) {
                    v01_1_dni_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                    validacion = false;
                } else {
                    v01_1_dni_prs.setBackgroundResource(0);
                    validacion = !validacion? false: true;
                }
                break;
            }
            case ("NIE"): case ("NIF"): {
                if (!Validacion_vldService.validarNieNif()) {
                    v01_1_dni_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                    validacion = false;
                } else {
                    v01_1_nacionalidad_prs.setVisibility(View.VISIBLE);
                    v01_1_nacionalidad_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                    if (!Validacion_vldService.validarNacionalidad()) {
                        v01_1_nacionalidad_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                        validacion = false;
                    } else {
                        v01_1_nacionalidad_prs.setBackgroundResource(0);
                        validacion = !validacion? false: true;
                    }
                }
                break;
            }
            default: {
                if (!Validacion_vldService.validarPasaporte()) {
                    v01_1_dni_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                    validacion = false;
                } else {
                    v01_1_nacionalidad_prs.setVisibility(View.VISIBLE);
                    v01_1_nacionalidad_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                    if (!Validacion_vldService.validarNacionalidad()) {
                        v01_1_nacionalidad_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
                        validacion = false;
                    } else {
                        v01_1_nacionalidad_prs.setBackgroundResource(0);
                        validacion = !validacion? false: true;
                    }
                }
                break;
            }
        }
        if (!Validacion_vldService.validarNumerocta()) {
            v01_1_numerocta_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            v01_1_numerocta_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }
        return validacion;
    }
}