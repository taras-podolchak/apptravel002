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
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_val_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Button v03_boton_me_interesaa, v03_boton_volver;
    private TextView titulo_eve;
    private ImageView foto_eve;
    private TextView fechaidatru_eve;
    private TextView fechavueltatru_eve;
    private TextView transportetipo_eve;
    private TextView nparticipantes_eve;
    private RecyclerView act_Recicler;
    private RecyclerView val_Recicler;

    //TODO:acceso a datos


    //TODO:entities
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Valiente_val> valientes = new ArrayList<>();
    private Context mContext;

    //TODO:servise
    private v03_00_act_Adapter act_Adapter;
    private v03_00_val_Adapter val_Adapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_03,
                container, false);

        Bundle bundle = getArguments();
        //this.evento = (Evento_eve) bundle.getSerializable("evento");
        //int posicion = (int) bundle.getInt("evento");
        // posicion++;
        this.titulo_eve = view.findViewById(R.id.txvTituloEve);
        this.foto_eve = view.findViewById(R.id.imvFotoEve);
        this.fechaidatru_eve = view.findViewById(R.id.txvFechaIdaTruEve);
        this.fechavueltatru_eve = view.findViewById(R.id.txvFechaVueltaTruEve);
        this.transportetipo_eve = view.findViewById(R.id.txvTransporteTipoEve);
        this.nparticipantes_eve = view.findViewById(R.id.txvNParticipantesEve);

       /* this.titulo_eve.setText(this.evento.getTitulo_eve());
        Picasso.get().load(evento.getFoto_eve()).into(foto_eve);
//        this.foto_eve.setImageResource(this.evento.getFoto_eve());
        this.fechaidatru_eve.setText(this.evento.getFechaidatru_eve());
        this.fechavueltatru_eve.setText(this.evento.getFechavueltatru_eve());
        this.transportetipo_eve.setText(this.evento.getTransportetipo_eve());
//        this.nparticipantes_eve.setText(this.evento.getNparticipantes_eve());*/

        //valientes


        this.val_Recicler = (RecyclerView) view.findViewById(R.id.v03_00_val);
        this.val_Recicler.setHasFixedSize(true);
        this.val_Recicler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

        valientesChangeListener(1);

        this.val_Adapter = new v03_00_val_Adapter(valientes, mContext);
        this.val_Recicler.setAdapter(val_Adapter);

        //eventos y actividades

        this.act_Recicler = (RecyclerView) view.findViewById(R.id.v03_00_act);
        this.act_Recicler.setHasFixedSize(true);
        this.act_Recicler.setLayoutManager(new LinearLayoutManager(mContext));

        //eventosChangeNoListener();
        eventosChangeNoListener("1");
        actividadesChangeListener(1);

        this.act_Adapter = new v03_00_act_Adapter(actividades, mContext);
        this.act_Recicler.setAdapter(act_Adapter);

        v03_boton_me_interesaa = view.findViewById(R.id.v03_boton_me_interesa);
        v03_boton_me_interesaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sesionIniciada) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v04);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05);
                }
            }
        });
        v03_boton_volver = view.findViewById(R.id.v03_boton_volver);
        v03_boton_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v03_to_nav_v02));//lamda.. java8+
        return view;
    }//fin de constructor

    public void valientesChangeListener(int id_eve) {
        FirebaseFirestore bbdd = FirebaseFirestore.getInstance();
        Query query = bbdd.collection("valiente_val").whereIn("id_val", Arrays.asList(1, 2, 3, 4, 5, 6));
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                Actividad_act enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                valientes.clear();
                for (DocumentSnapshot i : snapshots) {
                    valientes.add(i.toObject(Valiente_val.class));
                }
                act_Adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

   /* public void eventosChangeNoListener() {//para eliminar
        FirebaseFirestore bbdd = FirebaseFirestore.getInstance();
        bbdd.collectionGroup("coleccion_para_eliminar").whereEqualTo("evento_eve_refer", "evento_eve/1").whereEqualTo("id_eve", 1).get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<Evento_eve> doc = Collections.singletonList((Evento_eve) queryDocumentSnapshots.getDocuments());
                        Evento_eve document = doc.get(1);
                        titulo_eve.setText(document.getTitulo_eve());
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(foto_eve);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                                // Handle any errors
                            }
                        });
                        //cargamos la info completa
                        fechaidatru_eve.setText(document.getFechaidatru_eve());
                        fechavueltatru_eve.setText(document.getFechavueltatru_eve());
                        transportetipo_eve.setText(document.getTransportetipo_eve());
//                      this.nparticipantes_eve.setText(this.evento.getNparticipantes_eve());


                    }

                });

    }*/

    public void eventosChangeNoListener(String id_eve) {
        FirebaseFirestore bbdd = FirebaseFirestore.getInstance();
        DocumentReference docRef = bbdd.collection("evento_eve").document(id_eve);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        //cargamos el titulo
                        titulo_eve.setText(document.getString("titulo_eve"));
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(foto_eve);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                                // Handle any errors
                            }
                        });
                        //cargamos la info completa
                        fechaidatru_eve.setText(document.getString("fechaidatru_eve"));
                        fechavueltatru_eve.setText(document.getString("fechavueltatru_eve"));
                        transportetipo_eve.setText(document.getString("transportetipo_eve"));
//                      this.nparticipantes_eve.setText(this.evento.getNparticipantes_eve());

                        //aqui se cargara mas cosas
                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void actividadesChangeListener(int id_eve) {
        FirebaseFirestore bbdd = FirebaseFirestore.getInstance();
        Query query = bbdd.collection("actividad_act").whereEqualTo("id_eve", id_eve);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                Actividad_act enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                actividades.clear();
                for (DocumentSnapshot i : snapshots) {
                    enProceso = i.toObject(Actividad_act.class);
                    actividades.add(enProceso);
                }
                act_Adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}