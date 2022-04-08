package com.appvisibility.apptravel002.ui.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appvisibility.apptravel002.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05_2 extends Fragment {
    private Button v05_2_btnConfirmar, v05_2_btnVolver;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView v05_2_titulo_eve;
    private TextView v05_2_salidacoordenadastru_eve;
    private TextView v05_2_llegadacoordenadastru_eve;
    private TextView v05_2_descgeneral_eve;

    public V_05_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05_2.
     */
    // TODO: Rename and change types and number of parameters
    public static V_05_2 newInstance(String param1, String param2) {
        V_05_2 fragment = new V_05_2();
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
        View view = inflater.inflate(R.layout.fragment_v_05_2, container, false);

        this.v05_2_titulo_eve = view.findViewById(R.id.v05_2_titulo_eve);
        this.v05_2_salidacoordenadastru_eve = view.findViewById(R.id.v05_2_salidacoordenadastru_eve);
        this.v05_2_llegadacoordenadastru_eve = view.findViewById(R.id.v05_2_llegadacoordenadastru_eve);
        this.v05_2_descgeneral_eve = view.findViewById(R.id.v05_2_descgeneral_eve);

        eventosChangeNoListener("1");

        v05_2_btnConfirmar = view.findViewById(R.id.v05_2_btnConfirmar);
        v05_2_btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v06);
            }
        });

        v05_2_btnVolver = view.findViewById(R.id.v05_2_btnVolver);
        v05_2_btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v05);
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
                        v05_2_titulo_eve.setText(document.getString("titulo_eve"));
                        v05_2_salidacoordenadastru_eve.setText(document.getString("salidacoordenadastru_eve"));
                        v05_2_llegadacoordenadastru_eve.setText(document.getString("llegadacoordenadastru_eve"));
                        v05_2_descgeneral_eve.setText(document.getString("descgeneral_eve"));
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