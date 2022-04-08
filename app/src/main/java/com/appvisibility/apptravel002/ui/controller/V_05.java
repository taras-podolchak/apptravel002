package com.appvisibility.apptravel002.ui.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

//import java.util.EventListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment {
    private Button v05_btnInscritos, v05_btnDetalles;
    private Button v05_btnConfirmar, v05_btnVolver;
    private Context mContext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView v05_titulo_eve;
    private ImageView v05_foto_eve;
    private TextView v05_fechaidatru_eve;
    private TextView v05_fechavueltatru_eve;
    private TextView v05_transportetipo_eve;
    private TextView v05_nparticipantes_eve;

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
        View view = inflater.inflate(R.layout.fragment_v_05, container, false);

        this.v05_titulo_eve = view.findViewById(R.id.v05_titulo_eve);
        this.v05_foto_eve = view.findViewById(R.id.v05_foto_eve);
        this.v05_fechaidatru_eve = view.findViewById(R.id.v05_fechaidatru_eve);
        this.v05_fechavueltatru_eve = view.findViewById(R.id.v05_fechavueltatru_eve);
        this.v05_transportetipo_eve = view.findViewById(R.id.v05_transportetipo_eve);
        this.v05_nparticipantes_eve = view.findViewById(R.id.v05_nparticipantes_eve);

        eventosChangeNoListener("1");

        v05_btnInscritos = view.findViewById(R.id.v05_btnInscritos);
        v05_btnInscritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v05_1);
            }
        });

        v05_btnDetalles = view.findViewById(R.id.v05_btnDetalles);
        v05_btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v05_2);
            }
        });

        v05_btnConfirmar = view.findViewById(R.id.v05_btnConfirmar);
        v05_btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });

        v05_btnVolver = view.findViewById(R.id.v05_btnVolver);
        v05_btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v03);
            }
        });

        return view;
    }

    public void eventosChangeNoListener(String id_eve){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference docRef = firebaseFirestore.collection("evento_eve").document(id_eve);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        v05_titulo_eve.setText(document.getString("titulo_eve"));
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(v05_foto_eve);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                                // Handle any errors
                            }
                        });
                        //cargamos la info completa
                        v05_fechaidatru_eve.setText(document.getString("fechaidatru_eve"));
                        v05_fechavueltatru_eve.setText(document.getString("fechavueltatru_eve"));
                        v05_transportetipo_eve.setText(document.getString("transportetipo_eve"));
                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}