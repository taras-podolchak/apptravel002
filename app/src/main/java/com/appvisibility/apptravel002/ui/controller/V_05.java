package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Alojamiento_alo;
import com.appvisibility.apptravel002.ui.entities.Evento_eve_test;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment implements IDAO<Evento_eve_test, Persona_prs, Actividad_act> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO:los campos de xml
    private Button v05_confirmar, v05_volver;
    private TextView v05_titulo_eve;
    private ImageView v05_foto_eve;
    private TextView v05_info_completa;
    private Spinner v05_lista_personas;
    private Switch v05_llevas_coche;
    private Spinner v05_necesito_coche;
    private Spinner v05_ofrezco_coche;
    private Spinner v05_tipo_alojamiento;
    private Spinner v05_restricciones_alimentarias;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Evento_eve_test evento;
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Persona_prs> personas = new ArrayList<>();
    private List<Persona_prs> personasConCoches = new ArrayList<>();
    private List<Alojamiento_alo> listAlojamiento = new ArrayList<>();
    private Context mContext;

    //TODO:servise
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
    // TODO: Rename and change types and number of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05, container, false);
        int id_eve_bundle = getArguments().getInt("eventoParaV_05");

        Bundle result = new Bundle();
        result.putInt("eventoParaV_05_1", id_eve_bundle);

        v05_titulo_eve = view.findViewById(R.id.v05_txv_titulo_eve);
        v05_foto_eve = view.findViewById(R.id.v05_imv_foto_eve);
        v05_info_completa = view.findViewById(R.id.v05_txv_info_completa);
        v05_llevas_coche = view.findViewById(R.id.v05_swc_llevas_el_coche);
        v05_lista_personas = view.findViewById(R.id.v05_spn_lista_personas);
        v05_necesito_coche = view.findViewById(R.id.v05_spn_necesito_coche);
        v05_ofrezco_coche = view.findViewById(R.id.v05_spn_ofrezco_coche);
        v05_ofrezco_coche.setEnabled(false);
        v05_tipo_alojamiento = view.findViewById(R.id.v05_spn_tipo_alojamiento);
        v05_restricciones_alimentarias = view.findViewById(R.id.v05_spn_restricciones_allimentarias);

        // TODO: carga de Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
        List<Evento_eve_test> eventos_list = new ArrayList<>();
        v02_00_eve_Adapter v02_adapter_eve = null;

        Query query1 = fbf.collection("evento_eve_test").whereEqualTo("id_eve", id_eve_bundle);
        tabla1ChangeListener(query1, eventos_list, Evento_eve_test.class, v02_adapter_eve);

        // TODO: carga de Inscritos (personas)
        /*this.v05_recycler_prs = (RecyclerView) view.findViewById(R.id.v05_rcv_personas);
        this.v05_recycler_prs.setHasFixedSize(true);
        this.v05_recycler_prs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
*/
        Query query2 = fbf.collection("persona_prs").whereIn("id_prs", Arrays.asList(1, 2, 3, 4, 5, 6));
        tabla2ChangeListener(query2, personas, Persona_prs.class, v03_adapter_prs);
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
        v05_llevas_coche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    v05_ofrezco_coche.setEnabled(true);
                    v05_necesito_coche.setEnabled(false);
                } else {
                    v05_necesito_coche.setEnabled(true);
                    v05_ofrezco_coche.setEnabled(false);
                }
            }
        });

        // TODO: spinner nesesito coche
        necesitoElCocheChangeListener();

        // TODO: spinner ofresco coche
        ofrescoElCocheChangeListener();

        // TODO: spinner tipo alojamiento
        tipoAlojamientoChangeListener();

        // TODO: spinner restricciones allimentarias
        restriccionesAllimentariasChangeListener();


        // TODO: los botones
        v05_foto_eve = view.findViewById(R.id.v05_imv_foto_eve);
        v05_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v05_1, result);
            }
        });

        v05_confirmar = view.findViewById(R.id.v05_btn_confirmar);
        v05_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });

        v05_volver = view.findViewById(R.id.v05_btn_volver);
        v05_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v02);
            }
        });

        return view;
    }//fin de constructor


    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    evento = (Evento_eve_test) qds.toObject(tipoObjeto);
//                    lista.add(enProceso);
//                miAdapter.notifyDataSetChanged();

                    v05_titulo_eve.setText(evento.getTitulo_eve());
                    //cargamos la imagen
                    FirebaseStorage fbs = FirebaseStorage.getInstance();
                    StorageReference str = fbs.getReference();
                    str.child("Eventos/" + evento.getFoto_eve()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(v05_foto_eve);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                            // Handle any errors
                        }
                    });
                    v05_info_completa.setText(
                            "Nivel de dificultad: " + evento.getNivel_eve() + "\n"
                                    + "Distancia ida: " + evento.getDistanciaidatru_eve() + " "
                                    + "Distancia vuelta: " + evento.getDistanciavueltatru_eve() + "\n"
                                    + "Fecha ida: " + evento.getFechaidatru_eve() + "\n"
                                    + "Fecha vuelta: " + evento.getFechavueltatru_eve() + "\n"
                                    // + "Coordenadas de salida: " + evento.getSalidacoordenadastru_eve() + "\n"
                                    // + "Coordenadas de llegada: " + evento.getLlegadacoordenadastru_eve() + "\n"
                                    //  + evento.getDescgeneral_eve() + " "
                                    + "Precio: " + evento.getPrecio_eve() + "€");
                }

//                if (pdg.isShowing()){
//                    pdg.dismiss();
//                }

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public <S> void tabla2ChangeListener(Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                S enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (S) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
//                miAdapter.notifyDataSetChanged();
                ArrayAdapter<Persona_prs> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, personas);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                v05_lista_personas.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

//                if (pdg.isShowing()){
//                    pdg.dismiss();
//                }

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public <O> void tabla3ChangeListener(Query query, List<O> lista, Class<O> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                O enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (O) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();

//                if (pdg.isShowing()){
//                    pdg.dismiss();
//                }

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void necesitoElCocheChangeListener() {
        // fbf.collection("inscribir_eveprstpr").whereEqualTo("id_eve", 1)
        fbf.collection("persona_prs").whereIn("id_prs", Arrays.asList(1, 2, 3, 4, 5, 6))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        personasConCoches.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            personasConCoches.add(doc.toObject(Persona_prs.class));
                        }
                        Log.d(TAG, "Current cites in CA: ");
                    }
                });

        ArrayAdapter<Persona_prs> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, personasConCoches);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_necesito_coche.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void ofrescoElCocheChangeListener() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Ofrescer asientos:");
        arrayList.add("Ofresco 1 asiento");
        arrayList.add("Ofresco 2 asientos");
        arrayList.add("Ofresco 3 asientos");
        arrayList.add("Ofresco 4 asientos");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_ofrezco_coche.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
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
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void restriccionesAllimentariasChangeListener() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Restricciones alimentarias:");
        arrayList.add("Altramúz");
        arrayList.add("Cacahuete");
        arrayList.add("Crustáceos");
        arrayList.add("Glúten");
        arrayList.add("Huevos");
        arrayList.add("Lacteos");
        arrayList.add("Molúscos");
        arrayList.add("Mostaza");
        arrayList.add("Pescado");
        arrayList.add("Sésamo");
        arrayList.add("Soja");
        arrayList.add("Sulfitos");
        arrayList.add("Vegano");
        arrayList.add("Vegetariano");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_restricciones_alimentarias.setAdapter(arrayAdapter);
        /*v05_spinner_lista_personas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }
}