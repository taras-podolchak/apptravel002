package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprstpr;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_03 extends Fragment implements IDAO<Actividad_act, Inscribir_eveprstpr, Persona_prs> {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private Button v03_adelante, v03_atras;
    private int id_eve_enProceso;
    private TextView v03_titulo_eve;
    private ImageView v03_foto_eve;
    private TextView v03_fechaidatru_eve;
    private TextView v03_fechavueltatru_eve;
    private TextView v03_estado_eve;
    private RecyclerView v03_recycler_prs;
    private RecyclerView v03_recycler_act;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personas = new ArrayList<>();
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Actividad_act> actividadesFiltrados = new ArrayList<>();
    private List<Inscribir_eveprstpr> inscritos = new ArrayList<>();
    private List<Persona_prs> personasFiltrados = new ArrayList<>();
    private Context mContext;

    // Service
    private v03_00_prs_Adapter v03_adapter_prs;
    private v03_00_act_Adapter v03_adapter_act;

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
    // Rename and change types and number of parameters
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_03, container, false);

        Bundle bundleEvento = getArguments();
        //Recuperamos el Evento
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_03");

        this.id_eve_enProceso = eventoEnProceso.getId_eve();
        bundleEvento.putSerializable("eventoParaV_04", eventoEnProceso);
        bundleEvento.putSerializable("eventoParaV_05", eventoEnProceso);
        bundleEvento.putSerializable("eventoParaV_05_1", eventoEnProceso);

        this.v03_titulo_eve = view.findViewById(R.id.v03_txv_titulo_eve);
        this.v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        this.v03_fechaidatru_eve = view.findViewById(R.id.v03_txv_fechaidatru_eve);
        this.v03_fechavueltatru_eve = view.findViewById(R.id.v03_txv_fechavueltatru_eve);
        this.v03_estado_eve = view.findViewById(R.id.v03_txv_estado_eve);

        this.v03_recycler_act = (RecyclerView) view.findViewById(R.id.v03_rcv_actividades);
        this.v03_recycler_act.setHasFixedSize(true);
        this.v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));

        this.v03_recycler_prs = (RecyclerView) view.findViewById(R.id.v03_rcv_personas);
        this.v03_recycler_prs.setHasFixedSize(true);
        this.v03_recycler_prs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

        //Cargamos el Evento
        v03_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Eventos/" + eventoEnProceso.getFoto_eve()).getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(v03_foto_eve)).addOnFailureListener(exception ->
                Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_LONG).show());
        v03_fechaidatru_eve.setText(eventoEnProceso.getFechaidatru_eve());
        v03_fechavueltatru_eve.setText(eventoEnProceso.getFechavueltatru_eve());
        v03_estado_eve.setText("Estado: " + eventoEnProceso.getEstado_eve());

        //Cargamos los Actividades del Evento
        Query query1 = fbf.collection("actividad_act").orderBy("id_eve", Query.Direction.ASCENDING);
        tabla1ChangeListener(query1, actividades, Actividad_act.class, v03_adapter_act);

        //Cargamos todos los Inscritos
        Query query2 = fbf.collection("inscribir_eveprstpr").whereEqualTo("id_eve", id_eve_enProceso);
        tabla2ChangeListener(query2, inscritos, Inscribir_eveprstpr.class, null);

        //Cargamos todas las Personas
        Query query3 = fbf.collection("persona_prs").orderBy("id_prs", Query.Direction.DESCENDING);
        tabla3ChangeListener(query3, personas, Persona_prs.class, null);

        // Botones
        v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        v03_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05_1, bundleEvento);
            }
        });

        v03_adelante = view.findViewById(R.id.v03_btn_me_interesa);
        v03_adelante.setOnClickListener(viewAdelante -> {
            if (sesionIniciada == 0) {
                Navigation.findNavController(viewAdelante).navigate(R.id.action_nav_v03_to_nav_v04, bundleEvento);
            } else {
                Navigation.findNavController(viewAdelante).navigate(R.id.action_nav_v03_to_nav_v05, bundleEvento);
            }
        });

        v03_atras = view.findViewById(R.id.v03_btn_volver);
        v03_atras.setOnClickListener(viewAtras ->
                Navigation.findNavController(viewAtras).navigate(R.id.action_nav_v03_to_nav_v02));

        v03_recycler_prs.setAdapter(v03_adapter_prs);

        return view;
    }//Fin de constructor

    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (T) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
//                miAdapter.notifyDataSetChanged();
                // Filtramos las Actividades del Evento en proceso
                actividadesFiltrados = actividades.stream()
                    .filter(p->p.getId_eve() == id_eve_enProceso)
                    .collect(Collectors.toList());
                //Cargamos los Actividades del Evento
                v03_adapter_act = new v03_00_act_Adapter(actividadesFiltrados, mContext);
                v03_recycler_act.setAdapter(v03_adapter_act);

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
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

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                R enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (R) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
//                miAdapter.notifyDataSetChanged();
                // Mapeamos las Personas que están Inscritas al Evento en proceso
                // https://stackoverflow.com/questions/36246998/stream-filter-of-1-list-based-on-another-list
                personasFiltrados = personas.stream()
                    .filter(e -> inscritos.stream().map(Inscribir_eveprstpr::getId_prs).anyMatch(id -> id.equals(e.getId_prs())))
                    .collect(Collectors.toList());
                //Cargamos los datos de las Personas Inscritas al Evento
                v03_adapter_prs = new v03_00_prs_Adapter(personasFiltrados, inscritos, mContext, id_eve_enProceso);
                v03_recycler_prs.setAdapter(v03_adapter_prs);

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}