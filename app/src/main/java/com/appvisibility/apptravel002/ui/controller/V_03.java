package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Evento_eve_test;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprstpr;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_03 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO:los campos de xml
    private Button v03_me_interesa, v03_volver;
    private TextView v03_titulo_eve;
    private ImageView v03_foto_eve;
    private TextView v03_fechaidatru_eve;
    private TextView v03_fechavueltatru_eve;
    private TextView v03_estado_eve;
    private TextView v03_nparticipantes_eve;
    private TextView v03_inscritos_eve;
    private RecyclerView v03_recycler_prs;
    private RecyclerView v03_recycler_act;
    //private RecyclerView v03_recycler_prs;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Evento_eve_test evento_eve_test;
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personas = new ArrayList<>();
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Inscribir_eveprstpr> inscritos = new ArrayList<>();
    private List<Persona_prs> personasFiltrados = new ArrayList<>();
    private Context mContext;

    //TODO:servise
    private v03_00_prs_Adapter v03_adapter_prs;
    private v03_00_act_Adapter v03_adapter_act;
    int id_eve_bundle;

    public V_03() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_03.
     */
    // TODO: Rename and change types and number of parameters
    public static V_03 newInstance(String param1, String param2) {
        V_03 fragment = new V_03();
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
        View view = inflater.inflate(R.layout.fragment_v_03, container, false);

        /*ActionBar supportActionBar= ((AppCompatActivity) getActivity()).getSupportActionBar();
        supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#864d2d")));*/

        id_eve_bundle = getArguments().getInt("eventoParaV_03");

        Bundle result = new Bundle();
        result.putInt("eventoParaV_04", id_eve_bundle);
        result.putInt("eventoParaV_05", id_eve_bundle);
        result.putInt("eventoParaV_05_1", id_eve_bundle);

        this.v03_titulo_eve = view.findViewById(R.id.v03_txv_titulo_eve);
        this.v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        this.v03_fechaidatru_eve = view.findViewById(R.id.v03_txv_fechaidatru_eve);
        this.v03_fechavueltatru_eve = view.findViewById(R.id.v03_txv_fechavueltatru_eve);
        this.v03_estado_eve = view.findViewById(R.id.v03_txv_estado_eve);

        this.v03_inscritos_eve = view.findViewById(R.id.v03_txv_inscritos_eve);

        this.v03_recycler_prs = (RecyclerView) view.findViewById(R.id.v03_rcv_personas);
        this.v03_recycler_prs.setHasFixedSize(true);
        this.v03_recycler_prs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

        this.v03_recycler_act = (RecyclerView) view.findViewById(R.id.v03_rcv_actividades);
        this.v03_recycler_act.setHasFixedSize(true);
        this.v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));

        Query query1 = fbf.collection("inscribir_eveprstpr").whereEqualTo("id_eve", id_eve_bundle);
        query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    inscritos.clear();
                    Inscribir_eveprstpr resultado;
                    QuerySnapshot document = task.getResult();
                    for (QueryDocumentSnapshot qds : document) {
                        resultado = qds.toObject(Inscribir_eveprstpr.class);
                        inscritos.add(resultado);
                        Log.d(TAG, "DocumentSnapshot data: " + qds.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
//                v03_adapter_prs = new v03_00_prs_Adapter((List<Inscribir_eveprstpr>) lista, mContext);
//                v03_recycler_prs.setAdapter(v03_adapter_prs);
            }
        });

        Query query2 = fbf.collection("persona_prs").orderBy("id_prs", Query.Direction.ASCENDING);
        query2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    personas.clear();
                    Persona_prs resultado;
                    QuerySnapshot document = task.getResult();
                    for (QueryDocumentSnapshot qds : document) {
                        resultado = qds.toObject(Persona_prs.class);
                        personas.add(resultado);
                        Log.d(TAG, "DocumentSnapshot data: " + qds.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
// https://stackoverflow.com/questions/36246998/stream-filter-of-1-list-based-on-another-list
                personasFiltrados = personas.stream()
                        .filter(e -> inscritos.stream().map(Inscribir_eveprstpr::getId_prs).anyMatch(id -> id.equals(e.getId_prs())))
                        .collect(Collectors.toList());
                v03_adapter_prs = new v03_00_prs_Adapter(personasFiltrados, mContext, id_eve_bundle);
                v03_recycler_prs.setAdapter(v03_adapter_prs);
            }
        });

/*
        Query query3 = fbf.collection("transportepropio_tpr").orderBy("id_tpr", Query.Direction.ASCENDING);
//        tabla3ChangeListener (query3, transportepropios, Transportepropio_tpr.class, v03_adapter_tpr);
        Hilo tabla3ChangeListener = new Hilo (query3, transportepropios, Transportepropio_tpr.class, v03_adapter_tpr);
        tabla3ChangeListener.run();
        Thread hilo3 = new Thread(tabla3ChangeListener);
        hilo3.start();
*/

//        dataV_03ChangeListener(id_eve_bundle);
        Query query3 = fbf.collection("evento_eve").whereEqualTo("id_eve", id_eve_bundle);
        dataV_03ChangeListener(query3);

        Query query4 = fbf.collection("actividad_act").whereEqualTo("id_eve", id_eve_bundle);
        Document2ChangeListener(query4, actividades, Actividad_act.class);

        // TODO: los botones
        v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        v03_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05_1, result);
            }
        });

        v03_me_interesa = view.findViewById(R.id.v03_btn_me_interesa);
        v03_me_interesa.setOnClickListener(view12 -> {
            if (sesionIniciada == 0) {
                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v04, result);
            } else {
                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v05, result);
            }
        });
        v03_volver = view.findViewById(R.id.v03_btn_volver);
        v03_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v03_to_nav_v02));

//        filtro();
        v03_recycler_prs.setAdapter(v03_adapter_prs);

        return view;
    }//fin de constructor


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filtro (){
// https://stackoverflow.com/questions/36246998/stream-filter-of-1-list-based-on-another-list
        personasFiltrados = personas.stream()
            .filter(e -> inscritos.stream().map(Inscribir_eveprstpr::getId_prs).anyMatch(id -> id.equals(e.getId_prs())))
            .collect(Collectors.toList());
    }

    public void dataV_03ChangeListener(Query query4) {//prueba
        query4.addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            for (QueryDocumentSnapshot qds : snapshots) {
                eventoEnProceso = qds.toObject(Evento_eve.class);
            }
            //cargamos el evento

            v03_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
            FirebaseStorage fbs = FirebaseStorage.getInstance();
            StorageReference str = fbs.getReference();
            str.child("Eventos/" + eventoEnProceso.getFoto_eve()).getDownloadUrl().addOnSuccessListener(uri ->
                    Picasso.get().load(uri).into(v03_foto_eve)).addOnFailureListener(exception ->
                    Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_LONG).show());
            v03_fechaidatru_eve.setText(eventoEnProceso.getFechaidatru_eve());
            v03_fechavueltatru_eve.setText(eventoEnProceso.getFechavueltatru_eve());
            v03_estado_eve.setText("Estado: " + eventoEnProceso.getEstado_eve());

            //cargamos los inscritos
/*
            if (sesionIniciada) {
                v03_recycler_prs.setHasFixedSize(true);
                v03_recycler_prs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                personas = evento_eve_test.getListPersona();
                v03_adapter_prs = new v03_00_prs_Adapter(personas, mContext);
                v03_recycler_prs.setAdapter(v03_adapter_prs);
            } else {
                v03_inscritos_eve.setVisibility(View.INVISIBLE);
            }
            //cargamos los actividades
*/
/*
            v03_recycler_act.setHasFixedSize(true);
            v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));
//            actividades = evento_eve_test.getListActividad();
*/
        });
    }

    // https://stackoverflow.com/questions/63202621/how-to-query-firestore-and-get-specific-documents-in-android
/*
    private <N> void Document1ChangeListener(DocumentReference docRef, N resultado, Class<N> tipoObjeto) {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                N resultado;
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        resultado = (N) document.toObject(tipoObjeto);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
*/
    private <M> void Document2ChangeListener(Query query, List<M> lista, Class<M> tipoObjeto) {
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    lista.clear();
                    M resultado;
                    QuerySnapshot document = task.getResult();
                    for (QueryDocumentSnapshot qds : document) {
                        resultado = (M) qds.toObject(tipoObjeto);
                        lista.add(resultado);
                        Log.d(TAG, "DocumentSnapshot data: " + qds.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                v03_adapter_act = new v03_00_act_Adapter((List<Actividad_act>) lista, mContext);
                v03_recycler_act.setAdapter(v03_adapter_act);
            }
        });
    }
/*
    private <K> void Document3ChangeListener(Query query, List<K> lista, Class<K> tipoObjeto) {
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    lista.clear();
                    K resultado;
                    QuerySnapshot document = task.getResult();
                    for (QueryDocumentSnapshot qds : document) {
                        resultado = (K) qds.toObject(tipoObjeto);
                        lista.add(resultado);
                        Log.d(TAG, "DocumentSnapshot data: " + qds.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
//                v03_adapter_prs = new v03_00_prs_Adapter((List<Inscribir_eveprstpr>) lista, mContext);
                v03_recycler_prs.setAdapter(v03_adapter_prs);
            }
        });
    }

    private <L> void Document4ChangeListener(Query query, List<L> lista, Class<L> tipoObjeto) {
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    lista.clear();
                    L resultado;
                    QuerySnapshot document = task.getResult();
                    for (QueryDocumentSnapshot qds : document) {
                        resultado = (L) qds.toObject(tipoObjeto);
                        lista.add(resultado);
                        Log.d(TAG, "DocumentSnapshot data: " + qds.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
//                v03_adapter_prs = new v03_00_prs_Adapter((List<Persona_prs>) lista, mContext);
                v03_recycler_prs.setAdapter(v03_adapter_prs);
            }
        });
    }
*/
}