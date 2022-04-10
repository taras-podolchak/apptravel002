package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_val_Adapter;
import com.google.android.gms.tasks.OnSuccessListener;
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
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO:los campos de xml
    private Button v05_boton_confirmar, v05_boton_volver;
    private TextView v05_titulo_eve;
    private ImageView v05_imageView;
    private TextView v05_textView_info_completa;
    //private Spinner v05_spinner_lista_personas;
    private Switch v05_swich_llevas_el_coche;
    private Spinner v05_spinner_nesesito_coche;
    private Spinner v05_spinner_ofresco_coche;
    private Spinner v05_spinner_tipo_alojamiento;
    private Spinner v05_spinner_restricciones_allimentarias;
   // private Spinner v05_spinner_estado_de_pago;
    private RecyclerView act_Recicler;
    private RecyclerView val_Recicler;

    //TODO:acceso a datos
    FirebaseFirestore bbdd = FirebaseFirestore.getInstance();

    //TODO:entities
    private Evento_eve evento;
    private List<Actividad_act> actividades = new ArrayList<>();
    private List<Valiente_val> valientes = new ArrayList<>();
    private List<Valiente_val> valientesConCoches = new ArrayList<>();
    private Context mContext;

    //TODO:servise
    private v03_00_act_Adapter act_Adapter;
    private v03_00_val_Adapter val_Adapter;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05,
                container, false);


        v05_titulo_eve = view.findViewById(R.id.v05_txv_titulo_eve);
        v05_imageView = view.findViewById(R.id.v05_imv_foto_eve);
        v05_textView_info_completa = view.findViewById(R.id.v05_txv_info_completa);
        v05_swich_llevas_el_coche = (Switch) view.findViewById(R.id.v05_swc_llevas_el_coche);
        v05_spinner_nesesito_coche = view.findViewById(R.id.v05_spn_necesito_coche);
        v05_spinner_ofresco_coche = view.findViewById(R.id.v05_spn_ofrezco_coche);
        v05_spinner_ofresco_coche.setEnabled(false);
        v05_spinner_tipo_alojamiento = view.findViewById(R.id.v05_spn_tipo_alojamiento);
        v05_spinner_restricciones_allimentarias = view.findViewById(R.id.v05_spn_restricciones_allimentarias);

        // TODO: carga de Evento_eve
        eventosChangeListener(1);

        // TODO: carga de Inscritos (valientes)
        this.val_Recicler = (RecyclerView) view.findViewById(R.id.v05_rcv_valientes);
        this.val_Recicler.setHasFixedSize(true);
        this.val_Recicler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

        valientesChangeListener(1);

        this.val_Adapter = new v03_00_val_Adapter(valientes, mContext);
        this.val_Recicler.setAdapter(val_Adapter);

        // TODO: carga de Actividades
        this.act_Recicler = (RecyclerView) view.findViewById(R.id.v05_rcv_actividades);
        this.act_Recicler.setHasFixedSize(true);
        this.act_Recicler.setLayoutManager(new LinearLayoutManager(mContext));

        actividadesChangeListener(1);

        this.act_Adapter = new v03_00_act_Adapter(actividades, mContext);
        this.act_Recicler.setAdapter(act_Adapter);

        // TODO: swich ¿llevas el coche?
        v05_swich_llevas_el_coche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    v05_spinner_ofresco_coche.setEnabled(true);
                    v05_spinner_nesesito_coche.setEnabled(false);
                } else {
                    v05_spinner_nesesito_coche.setEnabled(true);
                    v05_spinner_ofresco_coche.setEnabled(false);
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
        v05_boton_confirmar = view.findViewById(R.id.v05_btn_confirmar);
        v05_boton_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });
        v05_boton_volver = view.findViewById(R.id.v05_btn_volver);
        v05_boton_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v02);
            }
        });
        return view;
    }//fin de constructor



    public void eventosChangeListener(int id_eve) { // bien hecho
        bbdd.collection("evento_eve")
                .whereEqualTo("id_eve", id_eve)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        for (QueryDocumentSnapshot doc : value) {
                            evento = doc.toObject(Evento_eve.class);

                            v05_titulo_eve.setText(evento.getTitulo_eve());
                            //cargamos la imagen
                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference storageRef = storage.getReference();
                            storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Picasso.get().load(uri).into(v05_imageView);
                                }
                            }).addOnFailureListener(exception -> Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show());
                            v05_textView_info_completa.setText(
                                    "Nivel de dificultad: " + evento.getNivel_eve() + "\n"
                                            + "Distancia ida: " + evento.getDistanciaidatru_eve() + " "
                                            + "Distancia vuelta: " + evento.getDistanciavueltatru_eve() + "\n"
                                            + "Fecha ida: " + evento.getFechaidatru_eve() + "\n"
                                            + "Fecha vuelta: " + evento.getFechavueltatru_eve() + "\n"
                                            + "Coordenadas de salida: " + evento.getSalidacoordenadastru_eve() + "\n"
                                            + "Coordenadas de llegada: " + evento.getLlegadacoordenadastru_eve() + "\n"
                                            + evento.getDescgeneral_eve() + " "
                                            + "Precio: " + evento.getPrecio_eve() + "€");
                        }
                        Log.d(TAG, "Current cites in CA: ");
                    }
                });
    }

    public void valientesChangeListener(int noSeQue) {
        bbdd.collection("valiente_val").whereIn("id_val", Arrays.asList(1, 2, 3, 4, 5, 6))
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
                        act_Adapter.notifyDataSetChanged();
                        Log.d(TAG, "Current cites in CA: ");
                    }
                });
    }

    public void actividadesChangeListener(int id_eve) {
        bbdd.collection("actividad_act").whereEqualTo("id_eve", id_eve)
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
                        act_Adapter.notifyDataSetChanged();
                        Log.d(TAG, "Current cites in CA: ");
                    }
                });
    }

    private void necesitoElCocheChangeListener() {
        // bbdd.collection("inscribir_evevalcoltpr").whereEqualTo("id_eve", 1)
        bbdd.collection("valiente_val").whereIn("id_val", Arrays.asList(1, 2, 3, 4, 5, 6))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        valientesConCoches.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            valientesConCoches.add(doc.toObject(Valiente_val.class));
                        }
                        Log.d(TAG, "Current cites in CA: ");
                    }
                });

        ArrayAdapter<Valiente_val> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, valientesConCoches);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_spinner_nesesito_coche.setAdapter(arrayAdapter);
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
        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("Ofrescer asientos:");
        arrayList.add("Ofresco 1 asiento");
        arrayList.add("Ofresco 2 asientos");
        arrayList.add("Ofresco 3 asientos");
        arrayList.add("Ofresco 4 asientos");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_spinner_ofresco_coche.setAdapter(arrayAdapter);
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
        v05_spinner_tipo_alojamiento.setAdapter(arrayAdapter);
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
        v05_spinner_restricciones_allimentarias.setAdapter(arrayAdapter);
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