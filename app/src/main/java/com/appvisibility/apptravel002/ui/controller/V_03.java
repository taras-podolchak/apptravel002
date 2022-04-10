package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_val_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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
 * Use the {@link V_03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_03 extends Fragment {

    //private static V_03 instance;
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
    private TextView v03_transportetipo_eve;
    private TextView v03_nparticipantes_eve;
    private RecyclerView v03_recycler_act;
    private RecyclerView v03_recycler_val;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Evento_eve evento;
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Valiente_val> valientes = new ArrayList<>();
    private Context mContext;

    //TODO:servise
    private v02_00_eve_Adapter adapter_eve;
    private v03_00_val_Adapter v03_adapter_val;
    private v03_00_act_Adapter adapter_act;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_03, container, false);

        Bundle bundle = getArguments();
        //this.evento = (Evento_eve) bundle.getSerializable("evento");
        //int posicion = (int) bundle.getInt("evento");
        // posicion++;

        this.v03_titulo_eve = view.findViewById(R.id.v03_txv_titulo_eve);
        this.v03_foto_eve = view.findViewById(R.id.v03_imv_foto_eve);
        this.v03_fechaidatru_eve = view.findViewById(R.id.v03_txv_fechaidatru_eve);
        this.v03_fechavueltatru_eve = view.findViewById(R.id.v03_txv_fechavueltatru_eve);
        this.v03_transportetipo_eve = view.findViewById(R.id.v03_txv_transportetipo_eve);
        this.v03_nparticipantes_eve = view.findViewById(R.id.v02_crd_txv_nparticipantes_eve);

        // TODO: carga de Evento_eve
        eventosChangeListener(1);

        // TODO: carga de Inscritos (valientes)
        this.v03_recycler_val = (RecyclerView) view.findViewById(R.id.v03_rcv_valientes);
        this.v03_recycler_val.setHasFixedSize(true);
        this.v03_recycler_val.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

        valientesChangeListener(1);

        this.v03_adapter_val = new v03_00_val_Adapter(valientes, mContext);
        this.v03_recycler_val.setAdapter(v03_adapter_val);

        // TODO: carga de Actividades
        this.v03_recycler_act = (RecyclerView) view.findViewById(R.id.v03_rcv_actividades);
        this.v03_recycler_act.setHasFixedSize(true);
        this.v03_recycler_act.setLayoutManager(new LinearLayoutManager(mContext));

        actividadesChangeListener(1);

        this.adapter_act = new v03_00_act_Adapter(actividades, mContext);
        this.v03_recycler_act.setAdapter(adapter_act);

        // TODO: los botones
        v03_me_interesa = view.findViewById(R.id.v03_btn_me_interesa);
        v03_me_interesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sesionIniciada) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v04);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05);
                }
            }
        });
        v03_volver = view.findViewById(R.id.v03_btn_volver);
        v03_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v03_to_nav_v02));//lamda.. java8+
        return view;
    }//fin de constructor

    public void eventosChangeListener(int id_eve) { // bien hecho
        fbf.collection("evento_eve")
            .whereEqualTo("id_eve", id_eve)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value,
                                    @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    for (QueryDocumentSnapshot doc : value) {
                        evento = doc.toObject(Evento_eve.class);

                        v03_titulo_eve.setText(evento.getTitulo_eve());
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(v03_foto_eve);
                            }
                        }).addOnFailureListener(exception -> Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show());
                        v03_fechaidatru_eve.setText(evento.getFechaidatru_eve());
                        v03_fechavueltatru_eve.setText(evento.getFechavueltatru_eve());
                        v03_transportetipo_eve.setText(evento.getTransportetipo_eve());
                    }
                    Log.d(TAG, "Current cites in CA: ");
                }
            });
    }

    public void valientesChangeListener(int noSeQue) {
        fbf.collection("valiente_val").whereIn("id_val", Arrays.asList(1, 2, 3, 4, 5, 6))
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value,
                                    @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    valientes.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        valientes.add(doc.toObject(Valiente_val.class));
                    }
                    adapter_act.notifyDataSetChanged();
                    Log.d(TAG, "Current cites in CA: ");
                }
            });
    }

    public void valientesChangeListener() {//prueba
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        DocumentReference drf = fbf.collection("evento_eve_test").document("1");
        drf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    valientes = (List<Valiente_val>) document.get("array_inscritos");
                    //Evento_eve even = group.get(0);
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void actividadesChangeListener(int id_eve) {
        fbf.collection("actividad_act").whereEqualTo("id_eve", id_eve)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value,
                                    @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }
                    actividades.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        actividades.add(doc.toObject((Actividad_act.class)));
                    }
                    adapter_act.notifyDataSetChanged();
                    Log.d(TAG, "Current cites in CA: ");
                }
            });
    }
}