package com.appvisibility.apptravel002.ui.controller;

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
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.appvisibility.apptravel002.ui.service.v05_01_val_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05_1 extends Fragment {
    private Button v05_1_btnConfirmar, v05_1_btnVolver;
    private RecyclerView miRecicler;
    private v05_01_val_Adapter miAdapter;
    private Context mContext;
    static List<Valiente_val> valientes = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public V_05_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05_1.
     */
    // TODO: Rename and change types and number of parameters
    public static V_05_1 newInstance(String param1, String param2) {
        V_05_1 fragment = new V_05_1();
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
        View view = inflater.inflate(R.layout.fragment_v_05_1, container, false);

        this.miRecicler = (RecyclerView) view.findViewById(R.id.v05_1_rcvVal);
        this.miRecicler.setHasFixedSize(true);
        this.miRecicler.setLayoutManager(new LinearLayoutManager(mContext));

        valientesChangeNoListener ("1");
//        valientesChangeListener (1);

        this.miAdapter = new v05_01_val_Adapter(valientes, mContext);
        this.miRecicler.setAdapter(miAdapter);

        v05_1_btnConfirmar = view.findViewById(R.id.v05_1_btnConfirmar);
        v05_1_btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_1_to_nav_v06);
            }
        });

        v05_1_btnVolver = view.findViewById(R.id.v05_1_btnVolver);
        v05_1_btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_1_to_nav_v05);
            }
        });

        return view;
    }

    private void valientesChangeNoListener(String id_eve) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("valiente_val")
            .whereEqualTo("id_eve",id_eve)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    Valiente_val enProceso;
                    if (task.isSuccessful()) {
                        valientes.clear();
                        for (QueryDocumentSnapshot i : task.getResult()) {
//                        Log.d(TAG, i.getId() + " => " + document.getData());
                            enProceso = i.toObject(Valiente_val.class);
                            valientes.add(enProceso);
                        }
                    } else {
//                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
    }

    public void valientesChangeListener( int id_eve) {
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        Query query = fbf.collection("valiente_val").whereEqualTo("id_eve",id_eve);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                Valiente_val enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                valientes.clear();
                for (DocumentSnapshot i : snapshots) {
                    enProceso = i.toObject(Valiente_val.class);
                    valientes.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();
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