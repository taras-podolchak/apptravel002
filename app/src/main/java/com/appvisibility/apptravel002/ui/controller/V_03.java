package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.os.Bundle;

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
import com.appvisibility.apptravel002.ui.entities.Evento_eve_test;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_val_Adapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    private TextView v03_txv_iscritos_eve;
    private RecyclerView v03_recycler_act;
    private RecyclerView v03_recycler_val;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Evento_eve_test evento_eve_test;
    private List<Valiente_val> valientes = new ArrayList<>();
    private List<Actividad_act> actividades = new ArrayList<>();
    private Context mContext;

    //TODO:servise
    private v03_00_val_Adapter v03_adapter_val;
    private v03_00_act_Adapter v03_adapter_act;
    int posicion;

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

        posicion = getArguments().getInt("eventoParaV_03");

        Bundle result = new Bundle();
        result.putInt("eventoParaV_04", posicion);
        result.putInt("eventoParaV_05", posicion);
        result.putInt("eventoParaV_05_1", posicion);

        this.v03_titulo_eve = view.findViewById(R.id.v03_txv_titulo_eve);
        this.v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        this.v03_fechaidatru_eve = view.findViewById(R.id.v03_txv_fechaidatru_eve);
        this.v03_fechavueltatru_eve = view.findViewById(R.id.v03_txv_fechavueltatru_eve);
        this.v03_estado_eve = view.findViewById(R.id.v03_txv_estado_eve);
        this.v03_nparticipantes_eve = view.findViewById(R.id.v02_crd_txv_nparticipantes_eve);
        this.v03_txv_iscritos_eve = view.findViewById(R.id.v03_txv_iscritos_eve);
        this.v03_recycler_val = view.findViewById(R.id.v03_rcv_valientes);
        this.v03_recycler_act = view.findViewById(R.id.v03_rcv_actividades);

        dataV_03ChangeListener(posicion);

        // TODO: los botones
      /*  v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        v03_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05_1, result);
            }
        });*/

        v03_me_interesa = view.findViewById(R.id.v03_btn_me_interesa);
        v03_me_interesa.setOnClickListener(view12 -> {
            if (!sesionIniciada) {
                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v04, result);
            } else {
                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v05, result);
            }
        });
        v03_volver = view.findViewById(R.id.v03_btn_volver);
        v03_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v03_to_nav_v02));
        return view;
    }//fin de constructor

    public void dataV_03ChangeListener(int posicion) {//prueba
        fbf.collection("evento_eve_test")
                .whereEqualTo("id_eve", posicion)
                .addSnapshotListener((value, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    for (QueryDocumentSnapshot doc : value) {
                        evento_eve_test = doc.toObject(Evento_eve_test.class);
                    }
                    //cargamos el evento
                    v03_titulo_eve.setText(evento_eve_test.getTitulo_eve());
                    FirebaseStorage fbs = FirebaseStorage.getInstance();
                    StorageReference str = fbs.getReference();
                    str.child("Eventos/" + evento_eve_test.getFoto_eve()).getDownloadUrl().addOnSuccessListener(uri ->
                            Picasso.get().load(uri).into(v03_foto_eve)).addOnFailureListener(exception ->
                            Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_LONG).show());
                    v03_fechaidatru_eve.setText(evento_eve_test.getFechaidatru_eve());
                    v03_fechavueltatru_eve.setText(evento_eve_test.getFechavueltatru_eve());
                    v03_estado_eve.setText("Estado: " + evento_eve_test.getEstado_eve());

                    //cargamos los inscritos
                    if (sesionIniciada) {
                    v03_recycler_val.setHasFixedSize(true);
                    v03_recycler_val.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                    valientes = evento_eve_test.getListValiente();
                    v03_adapter_val = new v03_00_val_Adapter(valientes, mContext);
                    v03_recycler_val.setAdapter(v03_adapter_val);
                    } else {
                        v03_txv_iscritos_eve.setVisibility(View.INVISIBLE);
                    }

                    //cargamos los actividades
                    v03_recycler_act.setHasFixedSize(true);
                    v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));
                    actividades = evento_eve_test.getListActividad();
                    v03_adapter_act = new v03_00_act_Adapter(actividades, mContext);
                    v03_recycler_act.setAdapter(v03_adapter_act);

                });
    }
}