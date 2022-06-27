package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
// public class V_05 extends Fragment implements IDAO<Persona_prs, Object, Object> {
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
//    private int id_eve_enProceso;
    private TextView v05_titulo_eve;
//    private ImageView v05_foto_eve;
//    private TextView v05_info_completa;
//    private Spinner v05_lista_personas;
    private Switch v05_cocheSiNo;
    private Button v05_solicitaPlazaLibre;
    private TextView v05_asignadaPlazaLibre;
//    private Spinner v05_solicitaPlazaLibre;
    private Spinner v05_ofertaPlazaLibre;
    private Spinner v05_tipo_alojamiento;
    private Button v05_indicaRestriccionesAlimentarias;
    private TextView v05_muestraRestriccionesAlimentarias;
//    private Spinner v05_restricciones_alimentarias;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
//    private Evento_eve evento;
    private Evento_eve eventoEnProceso;
//    private List<Persona_prs> personas = new ArrayList<>();
    private List<Persona_prs> personaOferentes = new ArrayList<>();
//    private List<Persona_prs> inscritos = new ArrayList<>();
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritoOferentes = new ArrayList<>();
    public static Map<Integer, Persona_prs> map_IdIns_Prs = v03_00_prs_Adapter.map_IdIns_Prs;
    private Persona_prs personaUser;
    private int id_prs_enProceso;

//    private Map<Integer, Persona_prs> map_IdIns_Prs = v03_00_prs_Adapter.map_IdIns_Prs;
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
//        v05_foto_eve = view.findViewById(R.id.v05_imv_foto_eve);
//        v05_info_completa = view.findViewById(R.id.v05_txv_info_completa);
        v05_cocheSiNo = view.findViewById(R.id.v05_swc_cocheSiNo);
        v05_solicitaPlazaLibre = view.findViewById(R.id.v05_btn_solicitaPlazaLibre);
        v05_asignadaPlazaLibre = view.findViewById(R.id.v05_txv_asignadaPlazaLibre);
        v05_ofertaPlazaLibre = view.findViewById(R.id.v05_spn_ofertaPlazaLibre);
        v05_ofertaPlazaLibre.setEnabled(false);
        v05_tipo_alojamiento = view.findViewById(R.id.v05_spn_tipo_alojamiento);
        v05_indicaRestriccionesAlimentarias = view.findViewById(R.id.v05_btn_indicaRestriccionesAlimentarias);
        v05_muestraRestriccionesAlimentarias = view.findViewById(R.id.v05_txv_muestraRestriccionesAlimentarias);

        //Cargamos el Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
//        List<Evento_eve> eventos = new ArrayList<>();
//        v02_00_eve_Adapter v02_adapter_eve = null;

        v05_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
        //Cargamos la imagen
/*
        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Eventos/" + eventoEnProceso.getFoto_eve()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(v05_foto_eve);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_SHORT).show();
                // Handle any errors
            }
        });
*/
/*
        v05_info_completa.setText(
                "Nivel de dificultad: " + eventoEnProceso.getNivel_eve() + "\n"
                        + "Distancia ida: " + eventoEnProceso.getDistanciaidatru_eve() + " "
                        + "Distancia vuelta: " + eventoEnProceso.getDistanciavueltatru_eve() + "\n"
                        + "Fecha ida: " + eventoEnProceso.getFechaidatru_eve() + "\n"
                        + "Fecha vuelta: " + eventoEnProceso.getFechavueltatru_eve() + "\n"
                        // + "Coordenadas de salida: " + evento.getSalidacoordenadastru_eve() + "\n"
                        // + "Coordenadas de llegada: " + evento.getLlegadacoordenadastru_eve() + "\n"
                        //  + evento.getDescgeneral_eve() + " "
                        + "Precio: " + eventoEnProceso.getPrecio_eve() + "€");
*/
        // TODO: carga de Inscritos (personas)
        /*this.v05_recycler_prs = (RecyclerView) view.findViewById(R.id.v05_rcv_personas);
        this.v05_recycler_prs.setHasFixedSize(true);
        this.v05_recycler_prs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
*/
/*
        Query query1 = fbf.collection("persona_prs").whereIn("id_prs", Arrays.asList(1, 2, 3, 4, 5, 6));
        tabla1ChangeListener(query1, personas, Persona_prs.class, null);
*/

//        personasChangeListener(1);

 /*       this.v05_adapter_prs = new v03_00_prs_Adapter(personas, mContext);
        this.v05_recycler_prs.setAdapter(v03_adapter_prs);

        // TODO: carga de Actividades
        this.v05_recycler_act = (RecyclerView) view.findViewById(R.id.v05_rcv_actividades);
        this.v05_recycler_act.setHasFixedSize(true);
        this.v05_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));

        Query query3 = fbf.collection("actividad_act").whereEqualTo("id_eve", 1);
        tabla3ChangeListener (query3, actividades, Actividad_act.class, v03_adapter_act);

        this.v05_adapter_act = new v03_00_act_Adapter(actividades, mContext);
        this.v05_recycler_act.setAdapter(v03_adapter_act);*/

        // TODO: swich ¿llevas el coche?
        v05_cocheSiNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    v05_ofertaPlazaLibre.setEnabled(true);
                    v05_solicitaPlazaLibre.setEnabled(false);
                    v05_asignadaPlazaLibre.setEnabled(false);
                } else {
                    v05_ofertaPlazaLibre.setEnabled(false);
                    v05_solicitaPlazaLibre.setEnabled(true);
                    v05_asignadaPlazaLibre.setEnabled(true);
                }
            }
        });

        // TODO: spinner ofresco coche
        ofrecerPlazasLibres();

        // TODO: spinner tipo alojamiento
        tipoAlojamientoChangeListener();

        // Botones
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
                solicitarPlazaLibre(view);
            }
        });

        v05_indicaRestriccionesAlimentarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicarRestriccionesAlimentarias(view);
            }
        });

        v05_adelante = view.findViewById(R.id.v05_btn_confirmar);
        v05_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void solicitarPlazaLibre(View view) {
        String [] descripcionPlazasLibres;

        //Identificamos a los inscritos que ofrecen Plazas Libres en el coche
        inscritoOferentes = inscritos.stream()
            .filter(p->p.getPlazaslibres_eveprs() > 0)
            .collect(Collectors.toList());

        //Recuperamos los datos de las personas que ofrecen Plazas Libres en el coche
        for(Inscribir_eveprs ins: inscritoOferentes){
            if (ins.getPlazaslibres_eveprs() > 0
                    && ins.getId_prs() != personaUser.getId_prs()) {
                id_prs_enProceso = ins.getId_prs();
                personaOferentes.add(map_IdIns_Prs.get(id_prs_enProceso));
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

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_valiente)) {
// https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
// Valiente: Enviamos a la ventana modal el listado de personasEnCoche correspondiente al id_prs (Persona) seleccionado
//            Bundle bundleInscritoOferentes = new Bundle();
//            bundleInscritoOferentes.putParcelableArrayList("inscritoParaV_05_2", (ArrayList<? extends Parcelable>) inscritoOferentes);
            AlertDialog.Builder modalSimplePlazasLibres = new AlertDialog.Builder(mContext);
            modalSimplePlazasLibres
                .setIcon(R.drawable.ico_coche_naranja)
                .setTitle("COCHES CON PLAZAS LIBRES")
                .setItems(descripcionPlazasLibres, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String datoseleccionado = descripcionPlazasLibres[item];
                        v05_asignadaPlazaLibre.setText(datoseleccionado);
                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_asignadaPlazaLibre);
                    }
                })
                .create()
                .show();
        }
        personaOferentes.clear();
    }

    private void ofrecerPlazasLibres() {
        ArrayList<String> ofertaPlazasLibres = new ArrayList<>();
        ofertaPlazasLibres.add("Ofrezcer plazas:");
        ofertaPlazasLibres.add("Ofrezco 1 plaza");
        ofertaPlazasLibres.add("Ofrezco 2 plazas");
        ofertaPlazasLibres.add("Ofrezco 3 plazas");
        ofertaPlazasLibres.add("Ofrezco 4 plazas");
        ofertaPlazasLibres.add("Ofrezco 5 plazas");
        ofertaPlazasLibres.add("Ofrezco 6 plazas");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, ofertaPlazasLibres);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_ofertaPlazaLibre.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void tipoAlojamientoChangeListener() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tipo de alojamiento:");
        arrayList.add("Alojamiento");
        arrayList.add("Treking");
        arrayList.add("Autocar");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_tipo_alojamiento.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void indicarRestriccionesAlimentarias(View view) {
        String [] descripcionRestriccionesAlimentarias;

        ArrayList<String> restriccionesAlimentarias = new ArrayList<>();
        restriccionesAlimentarias.add("Altramúz");
        restriccionesAlimentarias.add("Cacahuete");
        restriccionesAlimentarias.add("Crustáceos");
        restriccionesAlimentarias.add("Glúten");
        restriccionesAlimentarias.add("Huevos");
        restriccionesAlimentarias.add("Lacteos");
        restriccionesAlimentarias.add("Molúscos");
        restriccionesAlimentarias.add("Mostaza");
        restriccionesAlimentarias.add("Pescado");
        restriccionesAlimentarias.add("Sésamo");
        restriccionesAlimentarias.add("Soja");
        restriccionesAlimentarias.add("Sulfitos");
        restriccionesAlimentarias.add("Vegano");
        restriccionesAlimentarias.add("Vegetariano");

        boolean[] opcionesRestriccionesAlimentarias =new boolean[restriccionesAlimentarias.size()];

        //Organizamos el contenido del AlertDialog modal Selección Múltiple
        descripcionRestriccionesAlimentarias = new String[restriccionesAlimentarias.size()];
        for (int i=0; i<restriccionesAlimentarias.size(); i++){
            descripcionRestriccionesAlimentarias[i] = restriccionesAlimentarias.get(i);
            opcionesRestriccionesAlimentarias[i] = false;
        }

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_valiente)) {
            AlertDialog.Builder modalMultipleRestriccionesAlimentarias = new AlertDialog.Builder(mContext);
            modalMultipleRestriccionesAlimentarias
                .setIcon(R.drawable.ico_coche_naranja)
                .setTitle("RESTRICCIONES ALIMENTARIAS")
                .setMultiChoiceItems(descripcionRestriccionesAlimentarias, opcionesRestriccionesAlimentarias, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                        String seleccionados="";
                        for (int i = 0; i < opcionesRestriccionesAlimentarias.length; i++)
                        {
                            if (opcionesRestriccionesAlimentarias[i] == true)
                            {
                                seleccionados += descripcionRestriccionesAlimentarias[i] + "\n";
                            }
                        }
                        v05_muestraRestriccionesAlimentarias.setText(seleccionados);
                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_muestraRestriccionesAlimentarias);
                    }
                })
                .create()
                .show();
        }
    }

/*
    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Toast.makeText(mContext, "Error en Firestore", Toast.LENGTH_SHORT).show();
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (T) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }

 */
//                miAdapter.notifyDataSetChanged();
/*
                ArrayAdapter<Persona_prs> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, personas);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                v05_lista_personas.setAdapter(arrayAdapter);
 */
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/
/*
            }
        });
    }

    @Override
    public <S> void tabla2ChangeListener(Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter) {
    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {
    }
*/
}