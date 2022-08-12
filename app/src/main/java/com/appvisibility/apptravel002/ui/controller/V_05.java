package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;
import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.*;
import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.plazaLibreRenunciarRU;
import static com.appvisibility.apptravel002.ui.service.Persona_prsService.*;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.modal.V_03_2_modal;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private Button v05_adelante, v05_atras;
    private TextView v05_titulo_eve;
    private Switch v05_cocheSiNo;
    private Button v05_ofrecePlazaLibre;
    private Spinner v05_ofertaPlazaLibre;
    private Button v05_solicitaPlazaLibre;
    private TextView v05_asignadaPlazaLibre;
    private TextView v05_alimentacion_prs;
    private Button v05_indicaContacto1;
    private Spinner v05_2_opcionesCargo_prs;
    private AutoCompleteTextView v05_2_muestraContactoElegido;
    private TextView v05_2_muestraContactoElegidoResultado;
    private EditText v05_2_contacto1nombre_prs;
    private EditText v05_2_contacto1apellido1_prs;
    private EditText v05_2_contacto1apellido2_prs;
    private EditText v05_2_contacto1movil_prs;
    private EditText v05_2_contacto1telefono_prs;
    private EditText v05_2_contacto1email_prs;
//    private TextView txtcontactos;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personaOferentes = new ArrayList<>();
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritoOferentes = new ArrayList<>();
    public static Map<Integer, Persona_prs> map_IdIns_Prs = v03_00_prs_Adapter.map_IdIns_Prs;
    private Inscribir_eveprs inscritoOferente;
    private Inscribir_eveprs inscritoOferenteActual;
    private Inscribir_eveprs inscritoEnProceso;
    private int plazasOcupadas = -1;
    private Persona_prs personaUser;
    private int id_prs_enProceso;
    private Boolean solicitudPlazaLibreRealizada = V_03_2_modal.solicitudRealizada;
    private int plazaslibres_eveprs;
    private List alimentaciones_prs = new ArrayList();
    private String alimentacion_prsTitulo;
    private String alimentacion_prsActual;
    private String alimentacion_prsNueva = "";
    private String cargo_prsNueva = "";
    private Persona_prs contacto;
    private Map<Integer, Persona_prs> contactos = new HashMap<>();;
    private String contactoNuevaResultado = "";
    private String contacto1id_prs;
    private String contacto1nombre_prs;
    private String contacto1apellido1_prs;
    private String contacto1apellido2_prs;
    private String contacto1movil_prs;
    private String contacto1telefono_prs;
    private String contacto1email_prs;
    private Boolean datosActualizados;

    private Context mContext;

    // Service
    private v03_00_act_Adapter v03_adapter_act;
    private v03_00_prs_Adapter v03_adapter_prs;

    public V_05() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05.
     */
    // Rename and change types and number of parameters
    public static V_05 newInstance(String param1, String param2) {
        V_05 fragment = new V_05();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05, container, false);

        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
        //Recuperamos los datos del Usuario activo
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");

        //Cargamos el Evento
        Bundle bundleEvento = getArguments();
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05");

        bundleEvento.putSerializable("eventoParaV_05_1", eventoEnProceso);

        v05_titulo_eve = view.findViewById(R.id.v05_txv_titulo_eve);
        v05_cocheSiNo = view.findViewById(R.id.v05_swc_cocheSiNo);
        v05_cocheSiNo.setChecked(true);
        v05_ofrecePlazaLibre = view.findViewById(R.id.v05_btn_ofrecePlazaLibre);
        v05_ofertaPlazaLibre = view.findViewById(R.id.v05_spn_ofertaPlazaLibre);
        v05_solicitaPlazaLibre = view.findViewById(R.id.v05_btn_solicitaPlazaLibre);
        v05_solicitaPlazaLibre.setVisibility(View.GONE);
        v05_asignadaPlazaLibre = view.findViewById(R.id.v05_txv_asignadaPlazaLibre);
        v05_asignadaPlazaLibre.setVisibility(View.GONE);
        v05_alimentacion_prs = view.findViewById(R.id.v05_txv_alimentacion_prs);
        v05_indicaContacto1 = view.findViewById(R.id.v05_btn_indicaContacto1);
        v05_2_muestraContactoElegidoResultado = view.findViewById(R.id.v05_txv_muestraContactoElegidoResultado);
//        txtcontactos = view.findViewById(R.id.txtregistro);

        v05_titulo_eve.setText(eventoEnProceso.getTitulo_eve());

// Switch para mostrar opción Ofrezco Plazas Libres / Necesito Plazas Libres
        v05_cocheSiNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (solicitudPlazaLibreRealizada) {
                        solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                        v05_asignadaPlazaLibre.setText("");
                    } else {
                        plazaslibres_eveprs = 0;
                    }
                    v05_ofrecePlazaLibre.setVisibility(View.VISIBLE);
                    v05_ofertaPlazaLibre.setVisibility(View.VISIBLE);
                    v05_solicitaPlazaLibre.setVisibility(View.GONE);
                    v05_asignadaPlazaLibre.setVisibility(View.GONE);
                    v05_ofertaPlazaLibre.setSelection(0);
                } else {
                    if (inscritoEnProceso().getPlazaslibres_eveprs() >= 0){
                        plazasOcupadas();
                        if (plazasOcupadas > 0){
                            Toast.makeText(getActivity(), "Hay personas con plaza asignada en tu coche", Toast.LENGTH_LONG).show();
                        } else {
                            plazaslibres_eveprs = -1;
                            solicitudPlazaLibreRealizada = plazaLibreAnularRU(solicitudPlazaLibreRealizada, personaUser, plazaslibres_eveprs);
                        }
                    }
                    v05_ofrecePlazaLibre.setVisibility(View.GONE);
                    v05_ofertaPlazaLibre.setVisibility(View.GONE);
                    v05_solicitaPlazaLibre.setVisibility(View.VISIBLE);
                    v05_asignadaPlazaLibre.setVisibility(View.VISIBLE);
                }
            }
        });

// Spinner para indicar el número de Plazas Libres que se ofrecen
        v05_ofrecePlazaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (solicitudPlazaLibreRealizada) {
                    solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                }
            ofrecerPlazasLibres();
            }
        });

// Si hay una Plaza Libre solicitada se muestra
        for (Inscribir_eveprs ins: inscritos){
            if(personaUser.getId_prs() == ins.getId_prs()
                && ins.getId_tpr() != ins.getId_eveprs()){
                int id_tpr = ins.getId_tpr();
                for (Inscribir_eveprs ins2: inscritos){
                    if(id_tpr == ins2.getId_eveprs()){
                        v05_asignadaPlazaLibre.setText(map_IdIns_Prs.get(ins2.getId_prs()).getApodo_prs());
                        solicitudPlazaLibreRealizada = true;
                        inscritoOferenteActual = ins2;
                    }
                }
            }
        }

/*
        v05_foto_eve = view.findViewById(R.id.v05_imv_foto_eve);
        v05_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v05_1, bundleEvento);
            }
        });
*/

        v05_solicitaPlazaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plazasOcupadas > 0){
                    Toast.makeText(getActivity(), "No puedes solicitar plaza al mismo tiempo que tienes personas con plaza asignada en tu coche", Toast.LENGTH_LONG).show();
                } else {
                    solicitarPlazaLibre(view);
                }
            }
        });

        v05_alimentacion_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicarRestriccionesAlimentarias(view);
            }
        });

        alimentacionR();

        v05_indicaContacto1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                indicarContacto(view);
            }
        });

        // Botones
        v05_adelante = view.findViewById(R.id.v05_btn_confirmar);
        v05_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosActualizados = personaUserU(datosActualizados, personaUser);
                if (datosActualizados) {
                    Toast.makeText(getActivity(), "Se han actualizado tus datos", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "No se han podido actualizar tus datos", Toast.LENGTH_LONG).show();
                }
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });

        v05_atras = view.findViewById(R.id.v05_btn_atras);
        v05_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v02);
            }
        });

        return view;
    }//Fin de constructor

    //Identificamos al inscrito en proceso de Plazas Libres en coche
    private Inscribir_eveprs inscritoEnProceso (){
        for (Inscribir_eveprs ins : inscritos) {
            if (ins.getId_prs() == personaUser.getId_prs()){
                inscritoEnProceso = ins;
            }
        }
        return inscritoEnProceso;
    }

    private void plazasOcupadas (){
        plazasOcupadas = -1;
        // Contamos cuantas plazas ocupadas tiene el inscritoOferente
        if (inscritoEnProceso.getId_tpr() == inscritoEnProceso.getId_eveprs()){
            for (Inscribir_eveprs ins: inscritos){
                if (ins.getId_tpr() == inscritoEnProceso.getId_tpr()){
                    plazasOcupadas++;
                }
            }
        } else {
            plazasOcupadas = 0;
        }
    }

    private void ofrecerPlazasLibres() {
        inscritoEnProceso = inscritoEnProceso();
        plazasOcupadas();

        String [] ofertaPlazasLibres = getResources().getStringArray(R.array.ofertaPlazasLibres);
/*
        ArrayList<String> ofertaPlazasLibres = new ArrayList<>();
        ofertaPlazasLibres.add("VIAJO SOLO");
        ofertaPlazasLibres.add("1 Plaza");
        ofertaPlazasLibres.add("2 Plazas");
        ofertaPlazasLibres.add("3 Plazas");
        ofertaPlazasLibres.add("4 Plazas");
        ofertaPlazasLibres.add("5 Plazas");
        ofertaPlazasLibres.add("6 Plazas");
*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, ofertaPlazasLibres);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_ofertaPlazaLibre.setAdapter(arrayAdapter);
// https://stackoverflow.com/questions/10331854/how-to-get-spinner-selected-item-value-to-string
        final String[] dato = new String[1];
        dato[0] = ofertaPlazasLibres[plazasOcupadas];
//        dato[0] = ofertaPlazasLibres.get(plazasOcupadas);
        v05_ofertaPlazaLibre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plazasOcupadas();

                if (position < plazasOcupadas){
                    position = plazasOcupadas;
                }
                v05_ofertaPlazaLibre.setSelection(position);
                Object item = parent.getItemAtPosition(position);
                if (item.toString().equals("VIAJO SOLO")){
                    dato[0] = "0";
                } else {
                    dato[0] = item.toString().substring(0, item.toString().indexOf(" "));
                }
                if (Integer.parseInt(dato[0]) > plazasOcupadas){
                    plazaLibreOfrecerRU(solicitudPlazaLibreRealizada, personaUser, (Integer.parseInt(dato[0])-plazasOcupadas));
                } else {
                    plazaLibreOfrecerRU(solicitudPlazaLibreRealizada, personaUser, 0);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void solicitarPlazaLibre(View view) {
        String [] descripcionPlazasLibres;

        //Identificamos a los inscritos que ofrecen Plazas Libres en el coche
        inscritoOferentes = inscritos.stream()
            .filter(p->p.getPlazaslibres_eveprs() > 0)
            .collect(Collectors.toList());

        //Identificamos al inscritoSolicitante de Plazas Libres en coche
        inscritoEnProceso = inscritoEnProceso();
        //Recuperamos los datos de las personas que ofrecen Plazas Libres en el coche
        for(Inscribir_eveprs ins: inscritoOferentes){
            if (ins.getPlazaslibres_eveprs() > 0
                    && ins.getId_prs() != personaUser.getId_prs()) {
                id_prs_enProceso = ins.getId_prs();
                personaOferentes.add(map_IdIns_Prs.get(id_prs_enProceso));
            }
            if (ins.getId_prs() == personaUser.getId_prs()){
                v05_asignadaPlazaLibre.setText(map_IdIns_Prs.get(ins.getId_prs()).getApodo_prs());
            }
        }

        //Organizamos el contenido del AlertDialog modal Selección Simple
        descripcionPlazasLibres = new String[personaOferentes.size()];
        for (int i=0; i<personaOferentes.size(); i++){
            for (int j=0; j<inscritoOferentes.size(); j++){
                if (inscritoOferentes.get(j).getId_prs() == personaOferentes.get(i).getId_prs()) {
                    descripcionPlazasLibres[i] = personaOferentes.get(i).getApodo_prs()
                        + " ("
                        + inscritoOferentes.get(j).getPlazaslibres_eveprs()
                        + ") ";
                }
            }
        }

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
// https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
// Valiente: Enviamos a la ventana modal el listado de personasEnCoche correspondiente al id_prs (Persona) seleccionado
//            Bundle bundleInscritoOferentes = new Bundle();
//            bundleInscritoOferentes.putParcelableArrayList("inscritoParaV_05_2", (ArrayList<? extends Parcelable>) inscritoOferentes);
            AlertDialog.Builder modalSimplePlazasLibres = new AlertDialog.Builder(mContext);
            modalSimplePlazasLibres
                .setIcon(R.drawable.ico_coche_azul)
                .setTitle("COCHES CON PLAZAS LIBRES")
                .setItems(descripcionPlazasLibres, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String datoseleccionado = descripcionPlazasLibres[item];
//                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_asignadaPlazaLibre);
                        inscritoOferente = inscritoOferentes.get(item);
                        if (inscritoEnProceso.getId_tpr() == inscritoOferente.getId_tpr()){
                            Toast.makeText(getActivity(), "Ya tienes una plaza solicitada", Toast.LENGTH_LONG).show();
                        } else {
                            if (solicitudPlazaLibreRealizada){
                                solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                                // Debido al uso de onComplete tenemos que alterar algunos valores de los arrayList con el fin de que puedan entrar en los métodos
                                inscritoEnProceso.setPlazaslibres_eveprs(inscritoEnProceso.getPlazaslibres_eveprs() - 1);
                                inscritoEnProceso.setId_tpr(inscritoEnProceso.getId_eveprs());
                                solicitudPlazaLibreRealizada = plazaLibreSolicitarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferente);
                                if (solicitudPlazaLibreRealizada){
                                    inscritoOferenteActual = inscritoOferente;
                                    v05_asignadaPlazaLibre.setText(datoseleccionado.substring(0, datoseleccionado.indexOf("(")));
                                }
                            } else {
                                solicitudPlazaLibreRealizada = plazaLibreSolicitarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferente);
                                if (solicitudPlazaLibreRealizada){
                                    inscritoOferenteActual = inscritoOferente;
                                    v05_asignadaPlazaLibre.setText(datoseleccionado.substring(0, datoseleccionado.indexOf("(")));
                                }
                            }
                        }
                    }
                })
                .create()
                .show();
        }
        personaOferentes.clear();
    }

// Convierte un String separado por guiones en un ArrayList
    private String alimentacionR(){
        alimentacion_prsTitulo = "Restricciones Alimentarias: ";
        alimentacion_prsNueva = "";
// Si el usuario tiene restricciones Alimentarias se muestran
        alimentacion_prsActual = personaUser.getAlimentacion_prs();
// https://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-list
        alimentaciones_prs = Arrays.asList(alimentacion_prsActual.split("\\s*\\s*-\\s*"));
        for (Object ali: alimentaciones_prs){
            alimentacion_prsNueva += ali.toString() + " - ";
            v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                    TextView.BufferType.SPANNABLE);
        }
        return alimentacion_prsNueva;
    }

    private void indicarRestriccionesAlimentarias(View view) {
        String [] restriccionesAlimentarias = getResources().getStringArray(R.array.restriccionesAlimentarias);
        boolean[] opcionesRestriccionesAlimentarias =new boolean[restriccionesAlimentarias.length];

        //Organizamos el contenido del AlertDialog modal Selección Múltiple
        alimentacion_prsNueva = alimentacionR();
        alimentacion_prsNueva = "";
        for (int i=0; i < restriccionesAlimentarias.length; i++){
            restriccionesAlimentarias[i] = restriccionesAlimentarias[i];
            if (alimentaciones_prs.contains(restriccionesAlimentarias[i])){
                opcionesRestriccionesAlimentarias[i] = true;
                alimentacion_prsNueva += restriccionesAlimentarias[i] + " - ";
                v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                        TextView.BufferType.SPANNABLE);
            } else {
                opcionesRestriccionesAlimentarias[i] = false;
            };
        }

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_valiente)) {
            AlertDialog.Builder modalMultipleRestriccionesAlimentarias = new AlertDialog.Builder(mContext);
            modalMultipleRestriccionesAlimentarias
                .setIcon(R.drawable.ico_foodmealplaterestaurant_azul)
                .setTitle("RESTRICCIONES ALIMENTARIAS")
                .setMultiChoiceItems(restriccionesAlimentarias, opcionesRestriccionesAlimentarias, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                        alimentacion_prsNueva  = "";
                        alimentacion_prsActual = "";
                        for (int i = 0; i < opcionesRestriccionesAlimentarias.length; i++) {
                            if (opcionesRestriccionesAlimentarias[i] == true) {
                                alimentacion_prsNueva += restriccionesAlimentarias[i] + " - ";
                                alimentacion_prsActual += restriccionesAlimentarias[i] + " - ";
                            }
                        }
                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personaUser.setAlimentacion_prs(alimentacion_prsActual);
                        v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                                TextView.BufferType.SPANNABLE);
//                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_muestraRestriccionesAlimentarias);
                    }
                })
                .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
        }
    }

// https://www.youtube.com/watch?v=nlqtyfshUkc&ab_channel=CodingDemos
    private void indicarContacto(View view) {

        String [] contactoCargo_prs = getResources().getStringArray(R.array.contactoCargo_prs);
/*
        ArrayList<String> contactoCargo_prs = new ArrayList<>();
        contactoCargo_prs.add("RELACION:");
        contactoCargo_prs.add("Pareja");
        contactoCargo_prs.add("Amistad");
        contactoCargo_prs.add("Familiar");
        contactoCargo_prs.add("Trabajo");
*/
        ArrayAdapter<String> arrayAdapter_prs = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, contactoCargo_prs);
        arrayAdapter_prs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String [] contactoCargo_sum = getResources().getStringArray(R.array.contactoCargo_sum);
/*
        ArrayList<String> contactoCargo_sum = new ArrayList<>();
        contactoCargo_sum.add("CARGO:");
        contactoCargo_sum.add("Gerencia");
        contactoCargo_sum.add("Reservas");
        contactoCargo_sum.add("Facturación");
*/
        ArrayAdapter<String> arrayAdapter_sum = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, contactoCargo_sum);
        arrayAdapter_sum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        permitirAcceso();

//        Uri bbdd = ContactsContract.Data.CONTENT_URI;
        Uri bbdd = ContactsContract.Contacts.CONTENT_URI;
        String projection[] = new String[] {
//                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Contacts._ID,
//                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,
//                ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,
        };
        String selectionClause = null;
        String selectionArgs[] = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;
//        String sortOrder = null;
        Cursor cursor = crearCursor(bbdd, projection, selectionClause, selectionArgs, sortOrder);

// https://stackoverflow.com/questions/4301064/how-to-get-the-first-name-and-last-name-from-android-contacts
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String contacto1id_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
//            String contacto1id_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID));
//            String contacto1id_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID));
            String contacto1nombre_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
//            String contacto1nombre_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
//            String contacto1apellido1_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
            contacto = new Persona_prs(Integer.parseInt(contacto1id_prs), contacto1nombre_prs, contacto1apellido1_prs, contacto1apellido2_prs, contacto1movil_prs, contacto1telefono_prs, contacto1email_prs);
            contactos.put(Integer.parseInt(contacto1id_prs), contacto);
            cursor.moveToNext();
        }
        cursor.close();

        List<String> contactosDescripcion = new ArrayList<>();

        Iterator<Integer> it1 = contactos.keySet().iterator();
        while (it1.hasNext()) {
            Integer key = it1.next();
            contactosDescripcion.add(
                    contactos.get(key).getId_prs() + " - " +
                    contactos.get(key).getContacto1Nombre_prs()
            );
        }

        ArrayAdapter<String> arrayAdapter_contacto = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, contactosDescripcion);
        arrayAdapter_contacto.setDropDownViewResource(android.R.layout.simple_list_item_1);

        AlertDialog.Builder modalFormContacto = new AlertDialog.Builder(mContext);
        View view_prs = getLayoutInflater().inflate(R.layout.fragment_v_05_2_modal, null);
        v05_2_opcionesCargo_prs = view_prs.findViewById(R.id.v05_2_spn_ContactoCargo);
        v05_2_muestraContactoElegido = view_prs.findViewById(R.id.v05_2_act_muestraContactoElegido);
        v05_2_contacto1nombre_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoNombre);
        v05_2_contacto1apellido1_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoApellido1);
        v05_2_contacto1apellido2_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoApellido2);
        v05_2_contacto1movil_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoMovil);
        v05_2_contacto1telefono_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoTelefono);
        v05_2_contacto1email_prs = view_prs.findViewById(R.id.v05_2_etx_ContactoEmail);

        v05_2_muestraContactoElegido.setAdapter(arrayAdapter_contacto);

// https://stackoverflow.com/questions/4819813/how-to-get-text-from-autocompletetextview
        v05_2_muestraContactoElegido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                v05_2_muestraContactoElegido.setSelection(position);
                Object item = parent.getItemAtPosition(position);
                contactoNuevaResultado = (String) item;
//                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), contactoNuevaResultado, Toast.LENGTH_LONG).show();
                List<String> contactoNuevaArray = Arrays.asList(contactoNuevaResultado.split("\\s*\\s*-\\s*"));
                for (int i=0; i <= contactoNuevaArray.size(); i++){
                    switch (i) {
                        case 0: {contacto1id_prs = contactoNuevaArray.get(i); break;}
                        case 1: {v05_2_contacto1nombre_prs.setText(contactoNuevaArray.get(i)); break;}
                        default: {contactoNuevaResultado = " ";}
                    }
                }

                Uri bbdd = ContactsContract.Data.CONTENT_URI;
                String projection[] = null;
                String selectionClause = ContactsContract.Data.CONTACT_ID+ " = ?";
//                String selectionClause = ContactsContract.Contacts._ID+ " = ?";
                String selectionArgs[] = new String[]{contacto1id_prs};
                String sortOrder = null;
                Cursor cursor = crearCursor(bbdd, projection, selectionClause, selectionArgs, sortOrder);

                if (cursor.moveToFirst()) {
                    contacto1nombre_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME_ALTERNATIVE));
                    contactoNuevaArray = Arrays.asList(contacto1nombre_prs.split("\\s*,\\s*"));
                    for (int i=0; i <= contactoNuevaArray.size(); i++){
                        switch (i) {
                            case 0: {contacto1apellido1_prs = contactoNuevaArray.get(i); break;}
                            case 1: {contacto1nombre_prs = contactoNuevaArray.get(i); break;}
                            default: {contactoNuevaResultado = " ";}
                        }
                    }
                    contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Nombre_prs(contacto1nombre_prs);
                    v05_2_contacto1nombre_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Nombre_prs());
//                    contacto1apellido1_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DATA2));
                    contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Apellido1_prs(contacto1apellido1_prs);
                    v05_2_contacto1apellido1_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Apellido1_prs());
//                    contacto1apellido2_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME));
//                    contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Apellido2_prs(contacto1apellido2_prs);
//                    v05_2_contacto1apellido2_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Apellido2_prs());
                }
                cursor.close();

                Uri bbdd2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String projection2[] = new String[] {
                        ContactsContract.CommonDataKinds.Phone.DATA,
                };
                String selectionClause2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?";
//                String selectionClause = ContactsContract.Contacts._ID+ " = ?";
                String selectionArgs2[] = new String[]{contacto1id_prs};
                String sortOrder2 = null;
                Cursor cursor2 = crearCursor(bbdd2, projection2, selectionClause2, selectionArgs2, sortOrder2);

                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    contacto1telefono_prs = contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Telefono_prs();
                    contacto1movil_prs = contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Movil_prs();
                    if (contacto1telefono_prs == null) {
                        contacto1telefono_prs = cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "");
                        contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Telefono_prs(contacto1telefono_prs);
                        v05_2_contacto1telefono_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Telefono_prs());
                    } else {
                        if (!(cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "")).equalsIgnoreCase(contacto1telefono_prs.replaceAll(" ", "")));{
                            if (contacto1movil_prs == null) {
                                contacto1movil_prs = cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "");
                                contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Movil_prs(contacto1movil_prs);
                                v05_2_contacto1movil_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Movil_prs());
                            }
                        }
                    }
                    cursor2.moveToNext();
                }
                cursor2.close();

                Uri bbdd3 = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
//                Uri bbdd3 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//                Uri bbdd = ContactsContract.Contacts.CONTENT_URI;
                String projection3[] = null;
                String selectionClause3 = ContactsContract.CommonDataKinds.Email.CONTACT_ID+ " = ?";
//                String selectionClause = ContactsContract.Contacts._ID+ " = ?";
                String selectionArgs3[] = new String[]{contacto1id_prs};
                String sortOrder3 = null;
                Cursor cursor3 = crearCursor(bbdd3, projection3, selectionClause3, selectionArgs3, sortOrder3);

//                if (cursor3.moveToFirst()) {
                cursor3.moveToFirst();
                while (!cursor3.isAfterLast()) {
                    contacto1email_prs = contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Email_prs();
                    if (contacto1email_prs == null) {
                        contacto1email_prs = cursor3.getString(cursor3.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA1)).replaceAll(" ", "");
                        contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Email_prs(contacto1email_prs);
                        v05_2_contacto1email_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Email_prs());
                    }
                    cursor3.moveToNext();
                }
                cursor3.close();

// https://stackoverflow.com/questions/8805937/retrieving-phone-number-from-contact-id-android
/*
                Uri bbdd3 = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
//                Uri bbdd3 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//                Uri bbdd = ContactsContract.Contacts.CONTENT_URI;
                String projection3[] = null;
                String selectionClause3 = ContactsContract.CommonDataKinds.Email.CONTACT_ID+ " = ?";
//                String selectionClause = ContactsContract.Contacts._ID+ " = ?";
                String selectionArgs3[] = new String[]{contacto1id_prs};
                String sortOrder3 = null;
                Cursor cursor3 = crearCursor(bbdd3, projection3, selectionClause3, selectionArgs3, sortOrder3);

//                if (cursor3.moveToFirst()) {
                cursor3.moveToFirst();
                while (!cursor3.isAfterLast()) {
                    for(int i=0; i< cursor3.getColumnCount(); i++){
//                        Toast.makeText(getActivity(), cursor3.getColumnName(i) + ": " + cursor3.getString(i), Toast.LENGTH_LONG).show();
                        Log.i("CONTACTSTAG17", cursor3.getColumnName(i) + ": " + cursor3.getString(i));
                    }
                    cursor3.moveToNext();
                }
                cursor3.close();
*/
/*
                Uri bbdd = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//              Uri bbdd = ContactsContract.Data.CONTENT_URI,
                String projection[] = new String[] {
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
//                        ContactsContract.Data._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Email.DISPLAY_NAME,
                };
                String selectionClause = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?";
                String selectionArgs[] = new String[]{contacto1id_prs};
                String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
                Cursor cursor = crearCursor(bbdd, projection, selectionClause, selectionArgs, sortOrder);

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    if (cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)).equalsIgnoreCase(contacto1id_prs)) {
                        contacto1id_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        contacto1nombre_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        contacto1movil_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contacto1telefono_prs = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if (!contacto1movil_prs.equalsIgnoreCase("0")) {
                            contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Movil_prs(contacto1movil_prs);
                            v05_2_contacto1movil_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Movil_prs());
                        }
                        if (!contacto1telefono_prs.equalsIgnoreCase("0")) {
                            contactos.get(Integer.parseInt(contacto1id_prs)).setContacto1Telefono_prs(contacto1telefono_prs);
                            v05_2_contacto1telefono_prs.setText(contactos.get(Integer.parseInt(contacto1id_prs)).getContacto1Telefono_prs());
                        }
                        Toast.makeText(getActivity(), contacto1id_prs+" * "+contacto1nombre_prs+" * "+contacto1movil_prs, Toast.LENGTH_LONG).show();
                    }
                    cursor.moveToNext();
                }
                cursor.close();
 */

                if (contacto1nombre_prs != ""){
                    v05_2_muestraContactoElegido.setVisibility(View.GONE);
                }
//                Toast.makeText(getActivity(), ((Persona_prs) item).toStringContacto1(), Toast.LENGTH_LONG).show();
            }
        });

        modalFormContacto.setIcon(R.drawable.ico_contact_book);
        modalFormContacto.setTitle("PERSONA DE CONTACTO");

        if (sesionIniciada == view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
            v05_2_opcionesCargo_prs.setAdapter(arrayAdapter_prs);
// https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu
            v05_2_contacto1apellido2_prs.setVisibility(View.GONE);
            v05_2_contacto1telefono_prs.setVisibility(View.GONE);
            v05_2_contacto1email_prs.setVisibility(View.GONE);
            modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cargo_prsNueva = v05_2_opcionesCargo_prs.getSelectedItem().toString();
                        if (!cargo_prsNueva.equalsIgnoreCase("RELACION:")) {
                            personaUser.setContacto1Cargo_prs(cargo_prsNueva);
// https://www.youtube.com/watch?v=JB3ETK5mh3c&ab_channel=CodinginFlow
// autocomplete form with contact android youtube
                            personaUser.setContacto1Nombre_prs(contactoNuevaResultado);
                        } else {
                            cargo_prsNueva = "";
                            personaUser.setContacto1Cargo_prs(cargo_prsNueva);
                        }
                        Toast.makeText(getActivity(), "*"+contactoNuevaResultado+"*", Toast.LENGTH_LONG).show();
                    }
                });
        } else if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_alojamiento)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)){
            v05_2_opcionesCargo_prs.setAdapter(arrayAdapter_sum);
            modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cargo_prsNueva = v05_2_opcionesCargo_prs.getSelectedItem().toString();
                    if (!cargo_prsNueva.equalsIgnoreCase("CARGO:")) {
                        personaUser.setContacto1Cargo_prs(cargo_prsNueva);
                    } else {
                        cargo_prsNueva = "";
                        personaUser.setContacto1Cargo_prs(cargo_prsNueva);
                    }
                    v05_2_muestraContactoElegidoResultado.setText(contactoNuevaResultado);
                }
            });
        }

        modalFormContacto.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .setView(view_prs)
            .create()
            .show();
    }

    public void permitirAcceso (){
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_CONTACTS}, 1);}}}

    private Cursor crearCursor(Uri bbdd, String projection[], String selectionClause, String selectionArgs[], String sortOrder) {
// https://stackoverflow.com/questions/24108998/getcontentresolver-is-not-working
        Cursor cursor = mContext.getContentResolver().query(
                bbdd,
                projection,
                selectionClause,
                selectionArgs,
                sortOrder,
                null
        );
        return cursor;
    }
}