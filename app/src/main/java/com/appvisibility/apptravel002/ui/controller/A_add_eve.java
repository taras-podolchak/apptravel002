package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.google.android.gms.tasks.OnFailureListener;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link A_add_eve#newInstance} factory method to
 * create an instance of this fragment.
 */
public class A_add_eve extends Fragment implements IDAO<Evento_eve, Object, Object> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private RecyclerView v02_recycler_eve;

    // Acceso a datos
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private List<Evento_eve> eventos = new ArrayList<>();
    private List<Actividad_act> actividades = new ArrayList<>();
    private Context mContext;
    private Evento_eve eventoEnProceso;
    private Actividad_act actividadEnProceso;

    // Service
    private v02_00_eve_Adapter v02_adapter_eve;
    private v03_00_act_Adapter v03_adapter_act;
    private RecyclerView v03_recycler_act;


    private EditText a_add_eve_titulo_eve;
    private ImageView a_add_eve_foto_eve;
    private EditText a_add_eve_fechaidatru_eve;
    private EditText a_add_eve_fechavueltatru_eve;
    private EditText a_add_eve_nivel_eve;
    private EditText a_add_eve_salidaidatru_eve;
    private EditText a_add_eve_llegadaidatru_eve;
    private EditText a_add_eve_distanciaidatru_eve;
    private EditText a_add_eve_distanciavueltatru_eve;
    private EditText a_add_eve_llegadacoordenadastru_eve;
    private EditText a_add_eve_salidacoordenadastru_eve;
    private EditText a_add_eve_llegadavueltatru_eve;
    private EditText a_add_eve_precio_eve;
    private EditText a_add_eve_transportetipo_eve;

    private EditText a_add_eve_nombre_act;
    private EditText a_add_eve_actividadtipo_act;
    private EditText a_add_eve_fecha_act;
    private EditText a_add_eve_nivel_act;
    private EditText a_add_eve_distancia_act;
    private EditText a_add_eve_desnivel_act;
    private EditText a_add_eve_horas_act;
    private EditText a_add_eve_wikiloc_act;

    private Button a_add_eve_guardar;
    private Button a_add_eve_limpiar_campos;
    private Button a_add_eve_cancelar;
    private Button a_add_eve_eliminar_act;
    private Button a_add_eve_aniadir_act;

    public A_add_eve() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A_add_eve.
     */
    // TODO: Rename and change types and number of parameters
    public static A_add_eve newInstance(String param1, String param2) {
        A_add_eve fragment = new A_add_eve();
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
            eventoEnProceso = (Evento_eve) getArguments().getSerializable("eventoPara_a_add_eve");
            actividadEnProceso = (Actividad_act) getArguments().getSerializable("actividadPara_a_add_eve");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_add_eve, container, false);


        //Carga de los eventos
        this.v02_recycler_eve = (RecyclerView) view.findViewById(R.id.a_add_eve_rcv_eventos);
        this.v02_recycler_eve.setHasFixedSize(true);
        this.v02_recycler_eve.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v02_adapter_eve = new v02_00_eve_Adapter(eventos, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario));

        Query query1 = fbf.collection("evento_eve").orderBy("id_eve", Query.Direction.ASCENDING);
        tabla1ChangeListener(query1, eventos, Evento_eve.class, v02_adapter_eve);

        this.v02_recycler_eve.setAdapter(v02_adapter_eve);

        a_add_eve_titulo_eve = view.findViewById(R.id.a_add_eve_etx_titulo_eve);
        a_add_eve_foto_eve = view.findViewById(R.id.a_add_eve_imv_foto_eve);
        a_add_eve_fechaidatru_eve = view.findViewById(R.id.a_add_eve_etx_fechaidatru_eve);
        a_add_eve_fechavueltatru_eve = view.findViewById(R.id.a_add_eve_etx_fechavueltatru_eve);
        a_add_eve_nivel_eve = view.findViewById(R.id.a_add_eve_etx_nivel_eve);
        a_add_eve_salidaidatru_eve = view.findViewById(R.id.a_add_eve_etx_salidaidatru_eve);
        a_add_eve_llegadaidatru_eve = view.findViewById(R.id.a_add_eve_etx_llegadaidatru_eve);
        a_add_eve_distanciaidatru_eve = view.findViewById(R.id.a_add_eve_etx_distanciaidatru_eve);
        a_add_eve_distanciavueltatru_eve = view.findViewById(R.id.a_add_eve_etx_distanciavueltatru_eve);
        a_add_eve_llegadacoordenadastru_eve = view.findViewById(R.id.a_add_eve_etx_llegadacoordenadastru_eve);
        a_add_eve_salidacoordenadastru_eve = view.findViewById(R.id.a_add_eve_etx_salidacoordenadastru_eve);
        a_add_eve_llegadavueltatru_eve = view.findViewById(R.id.a_add_eve_etx_llegadavueltatru_eve);
        a_add_eve_precio_eve = view.findViewById(R.id.a_add_eve_etx_precio_eve);
        a_add_eve_transportetipo_eve = view.findViewById(R.id.a_add_eve_etx_transportetipo_eve);


        //Recuperamos el Evento
        if (getArguments() != null) {
           //eventoEnProceso = (Evento_eve) getArguments().getSerializable("eventoPara_a_add_eve");

            //if (eventoEnProceso != null) {
            a_add_eve_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
            FirebaseStorage fbs = FirebaseStorage.getInstance();
            StorageReference str = fbs.getReference();
            str.child("Eventos/" + eventoEnProceso.getFoto_eve()).getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(a_add_eve_foto_eve)).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                }
            });
            a_add_eve_fechaidatru_eve.setText(eventoEnProceso.getFechaidatru_eve());
            a_add_eve_fechavueltatru_eve.setText(eventoEnProceso.getFechavueltatru_eve());
            a_add_eve_nivel_eve.setText(eventoEnProceso.getNivel_eve());
            a_add_eve_salidaidatru_eve.setText(eventoEnProceso.getSalidaidatru_eve());
            a_add_eve_llegadaidatru_eve.setText(eventoEnProceso.getLlegadaidatru_eve());
            a_add_eve_distanciaidatru_eve.setId(eventoEnProceso.getDistanciaidatru_eve());
            a_add_eve_distanciavueltatru_eve.setId(eventoEnProceso.getDistanciavueltatru_eve());
            a_add_eve_llegadacoordenadastru_eve.setText(eventoEnProceso.getLlegadacoordenadastru_eve());
            a_add_eve_salidacoordenadastru_eve.setText(eventoEnProceso.getSalidacoordenadastru_eve());
            a_add_eve_llegadavueltatru_eve.setText(eventoEnProceso.getLlegadavueltatru_eve());
            a_add_eve_precio_eve.setId(eventoEnProceso.getPrecio_eve());
            a_add_eve_transportetipo_eve.setText(eventoEnProceso.getTransportetipo_eve());
            // }
        }


        //carga de los actividades
        this.v03_recycler_act = (RecyclerView) view.findViewById(R.id.a_add_eve_rcv_actividades);
        this.v03_recycler_act.setHasFixedSize(true);
        this.v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario));

        Query query2 = fbf.collection("actividad_act").orderBy("id_act", Query.Direction.ASCENDING);
        tabla2ChangeListener(query2, actividades, Actividad_act.class, v03_adapter_act);
        this.v03_recycler_act.setAdapter(v03_adapter_act);

        this.v03_recycler_act = (RecyclerView) view.findViewById(R.id.a_add_eve_rcv_actividades2);
        this.v03_recycler_act.setHasFixedSize(true);
        this.v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario));
        this.v03_recycler_act.setAdapter(v03_adapter_act);

        a_add_eve_nombre_act = view.findViewById(R.id.a_add_eve_etx_nombre_act);
        a_add_eve_actividadtipo_act = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act);
        a_add_eve_fecha_act = view.findViewById(R.id.a_add_eve_etx_fecha_act);
        a_add_eve_nivel_act = view.findViewById(R.id.a_add_eve_etx_nivel_act);
        a_add_eve_distancia_act = view.findViewById(R.id.a_add_eve_etx_distancia_act);
        a_add_eve_desnivel_act = view.findViewById(R.id.a_add_eve_etx_desnivel_act);
        a_add_eve_horas_act = view.findViewById(R.id.a_add_eve_etx_horas_act);
        a_add_eve_wikiloc_act = view.findViewById(R.id.a_add_eve_etx_wikiloc_act);

        LinearLayout linearLayout2 = view.findViewById(R.id.a_add_eve_ll_añadir_act2);
        linearLayout2.setVisibility(View.GONE);

        //Recuperamos la Actividad
        if (getArguments() != null) {
            //actividadEnProceso = (Actividad_act) getArguments().getSerializable("actividadPara_a_add_eve");

            if (actividadEnProceso != null) {
                a_add_eve_nombre_act.setText(actividadEnProceso.getNombre_act());
                a_add_eve_actividadtipo_act.setText(actividadEnProceso.getActividadtipo_act());
                a_add_eve_fecha_act.setText(actividadEnProceso.getFecha_act());
                a_add_eve_nivel_act.setText(actividadEnProceso.getNivel_act());
                a_add_eve_distancia_act.setId(actividadEnProceso.getDistancia_act());
                a_add_eve_desnivel_act.setId(actividadEnProceso.getDesnivel_act());
                a_add_eve_horas_act.setId(actividadEnProceso.getHoras_act());
                a_add_eve_wikiloc_act.setText(actividadEnProceso.getWikiloc_act());
            }
        }
        //los botones
        a_add_eve_aniadir_act = view.findViewById(R.id.a_add_eve_btn_aniadir_act);
        a_add_eve_eliminar_act = view.findViewById(R.id.a_add_eve_btn_eliminar_act);

        a_add_eve_guardar = view.findViewById(R.id.a_add_eve_btn_guardar);
        a_add_eve_limpiar_campos = view.findViewById(R.id.a_add_eve_btn_limpiar_campos);
        a_add_eve_cancelar = view.findViewById(R.id.a_add_eve_btn_cancelar);

        a_add_eve_aniadir_act.setOnClickListener(view12 -> linearLayout2.setVisibility(View.VISIBLE));
        a_add_eve_eliminar_act.setOnClickListener(view12 -> guardarEvento(view12));

        a_add_eve_guardar.setOnClickListener(view12 -> guardarEvento(view12));
        //a_add_eve_limpiar_campos.setOnClickListener(view13 -> Navigation.findNavController(view13).navigate(R.id.action_nav_v04_to_nav_v04_1));
        a_add_eve_cancelar.setOnClickListener(view13 -> Navigation.findNavController(view13).navigate(R.id.action_nav_a_create_eve_to_nav_v01));

        return view;
    }//Fin de constructor

    private void guardarEvento(View view) {
        Evento_eve evento_push = new Evento_eve();
        evento_push.setId_eve(eventos.size());
        evento_push.setTitulo_eve(a_add_eve_titulo_eve.getText().toString().trim());
        evento_push.setFoto_eve(eventoEnProceso.getFoto_eve());//foto
        evento_push.setFechaidatru_eve(a_add_eve_fechaidatru_eve.getText().toString().trim());
        evento_push.setFechavueltatru_eve(a_add_eve_fechavueltatru_eve.getText().toString().trim());
        evento_push.setNivel_eve(a_add_eve_nivel_eve.getText().toString().trim());
        evento_push.setSalidaidatru_eve(a_add_eve_salidaidatru_eve.getText().toString().trim());
        evento_push.setLlegadaidatru_eve(a_add_eve_llegadaidatru_eve.getText().toString().trim());
        evento_push.setDistanciaidatru_eve(a_add_eve_distanciaidatru_eve.getId());
        evento_push.setDistanciavueltatru_eve(a_add_eve_distanciavueltatru_eve.getId());
        evento_push.setLlegadacoordenadastru_eve(a_add_eve_llegadacoordenadastru_eve.getText().toString().trim());
        evento_push.setSalidacoordenadastru_eve(a_add_eve_salidacoordenadastru_eve.getText().toString().trim());
        evento_push.setLlegadavueltatru_eve(a_add_eve_llegadavueltatru_eve.getText().toString().trim());
        evento_push.setPrecio_eve(a_add_eve_precio_eve.getId());
        evento_push.setTransportetipo_eve(a_add_eve_transportetipo_eve.getText().toString().trim());

        fbf.collection("evento_eve").document(Integer.toString(evento_push.getId_eve()+1)).set(evento_push);
        Toast.makeText(getActivity(), "El evento se ha guardado correctamente", Toast.LENGTH_LONG).show();
        Navigation.findNavController(view).navigate(R.id.action_nav_a_create_eve_to_nav_v01);

    }

    @Override
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
                    enProceso = (T) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();

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
                miAdapter.notifyDataSetChanged();

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }
}