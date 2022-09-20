package com.appvisibility.apptravel002.ui.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.interfaces.IDAO;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    private FirebaseStorage fbs = FirebaseStorage.getInstance();
    private StorageReference str = fbs.getReference();

    // Entities
    private List<Evento_eve> eventos = new ArrayList<>();
    private List<Actividad_act> actividades = new ArrayList<>();
    // private Evento_eve eventoEnProceso;
    // private Actividad_act actividadEnProceso;
    private int id_new_eve;
    private int id_new_act;
    private Uri imageUri;
    private boolean subirNewFoto = false;

    private Context mContext;

    // Service
    private v02_00_eve_Adapter v02_adapter_eve;
    private v03_00_act_Adapter v03_adapter_act1;
    private RecyclerView v03_recycler_act1;
    private v03_00_act_Adapter v03_adapter_act2;
    private RecyclerView v03_recycler_act2;
    private v03_00_act_Adapter v03_adapter_act3;
    private RecyclerView v03_recycler_act3;
    private v03_00_act_Adapter v03_adapter_act4;
    private RecyclerView v03_recycler_act4;
    private v03_00_act_Adapter v03_adapter_act5;
    private RecyclerView v03_recycler_act5;

    //eve
    private EditText a_add_eve_id_eve;
    private EditText a_add_eve_titulo_eve;
    private ImageView a_add_eve_foto_eve;
    private EditText a_add_eve_foto_ruta_eve;
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
    private Button a_add_eve_guardar;
    private Button a_add_eve_limpiar_campos;
    private Button a_add_eve_cancelar;

    //act 1
    private EditText a_add_eve_id_act1;
    private LinearLayout a_add_eve_añadir_act1;
    private EditText a_add_eve_nombre_act1;
    private EditText a_add_eve_actividadtipo_act1;
    private EditText a_add_eve_fecha_act1;
    private EditText a_add_eve_nivel_act1;
    private EditText a_add_eve_distancia_act1;
    private EditText a_add_eve_desnivel_act1;
    private EditText a_add_eve_horas_act1;
    private EditText a_add_eve_wikiloc_act1;
    private Button a_add_eve_eliminar_act1;
    private Button a_add_eve_aniadir_act1;

    //act 2
    private EditText a_add_eve_id_act2;
    private LinearLayout a_add_eve_añadir_act2;
    private EditText a_add_eve_nombre_act2;
    private EditText a_add_eve_actividadtipo_act2;
    private EditText a_add_eve_fecha_act2;
    private EditText a_add_eve_nivel_act2;
    private EditText a_add_eve_distancia_act2;
    private EditText a_add_eve_desnivel_act2;
    private EditText a_add_eve_horas_act2;
    private EditText a_add_eve_wikiloc_act2;
    private Button a_add_eve_eliminar_act2;
    private Button a_add_eve_aniadir_act2;

    //act 3
    private EditText a_add_eve_id_act3;
    private LinearLayout a_add_eve_añadir_act3;
    private EditText a_add_eve_nombre_act3;
    private EditText a_add_eve_actividadtipo_act3;
    private EditText a_add_eve_fecha_act3;
    private EditText a_add_eve_nivel_act3;
    private EditText a_add_eve_distancia_act3;
    private EditText a_add_eve_desnivel_act3;
    private EditText a_add_eve_horas_act3;
    private EditText a_add_eve_wikiloc_act3;
    private Button a_add_eve_eliminar_act3;
    private Button a_add_eve_aniadir_act3;

    //act 4
    private EditText a_add_eve_id_act4;
    private LinearLayout a_add_eve_añadir_act4;
    private EditText a_add_eve_nombre_act4;
    private EditText a_add_eve_actividadtipo_act4;
    private EditText a_add_eve_fecha_act4;
    private EditText a_add_eve_nivel_act4;
    private EditText a_add_eve_distancia_act4;
    private EditText a_add_eve_desnivel_act4;
    private EditText a_add_eve_horas_act4;
    private EditText a_add_eve_wikiloc_act4;
    private Button a_add_eve_eliminar_act4;
    private Button a_add_eve_aniadir_act4;

    //act 5
    private EditText a_add_eve_id_act5;
    private LinearLayout a_add_eve_añadir_act5;
    private EditText a_add_eve_nombre_act5;
    private EditText a_add_eve_actividadtipo_act5;
    private EditText a_add_eve_fecha_act5;
    private EditText a_add_eve_nivel_act5;
    private EditText a_add_eve_distancia_act5;
    private EditText a_add_eve_desnivel_act5;
    private EditText a_add_eve_horas_act5;
    private EditText a_add_eve_wikiloc_act5;
    private Button a_add_eve_eliminar_act5;
    private Button a_add_eve_aniadir_act5;


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

        asignacionDeLosCamposDeAAddEve(view);

        //RecyclerView de los eventos
        //Referenciamos los views
        this.v02_recycler_eve = view.findViewById(R.id.a_add_eve_rcv_eventos);
        this.v02_recycler_eve.setHasFixedSize(true);
        this.v02_recycler_eve.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v02_adapter_eve = new v02_00_eve_Adapter(eventos, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view);

        Query query1 = fbf.collection("evento_eve").orderBy("id_eve", Query.Direction.ASCENDING);
        tabla1ChangeListener(query1, eventos, Evento_eve.class, v02_adapter_eve);

        this.v02_recycler_eve.setAdapter(v02_adapter_eve);

        //RecyclerView de la actividad1
        this.v03_recycler_act1 = view.findViewById(R.id.a_add_eve_rcv_actividad1);
        this.v03_recycler_act1.setHasFixedSize(true);
        this.v03_recycler_act1.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act1 = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view, 1);
        Query query2 = fbf.collection("actividad_act").orderBy("id_act", Query.Direction.ASCENDING);
        tabla2ChangeListener(query2, actividades, Actividad_act.class, v03_adapter_act1);
        this.v03_recycler_act1.setAdapter(v03_adapter_act1);

        //RecyclerView de la actividad2
        this.v03_recycler_act2 = view.findViewById(R.id.a_add_eve_rcv_actividad2);
        this.v03_recycler_act2.setHasFixedSize(true);
        this.v03_recycler_act2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act2 = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view, 2);
        this.v03_recycler_act2.setAdapter(v03_adapter_act2);

        //RecyclerView de la actividad3
        this.v03_recycler_act3 = view.findViewById(R.id.a_add_eve_rcv_actividad3);
        this.v03_recycler_act3.setHasFixedSize(true);
        this.v03_recycler_act3.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act3 = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view, 3);
        this.v03_recycler_act3.setAdapter(v03_adapter_act3);

        //RecyclerView de la actividad4
        this.v03_recycler_act4 = view.findViewById(R.id.a_add_eve_rcv_actividad4);
        this.v03_recycler_act4.setHasFixedSize(true);
        this.v03_recycler_act4.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act4 = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view, 4);
        this.v03_recycler_act4.setAdapter(v03_adapter_act4);

        //RecyclerView de la actividad5
        this.v03_recycler_act5 = view.findViewById(R.id.a_add_eve_rcv_actividad5);
        this.v03_recycler_act5.setHasFixedSize(true);
        this.v03_recycler_act5.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        this.v03_adapter_act5 = new v03_00_act_Adapter(actividades, mContext, getResources().getInteger(R.integer.accion_rellenar_formulario), view, 5);
        this.v03_recycler_act5.setAdapter(v03_adapter_act5);

        //cargar la imagen nueva (tiene 3 etapas(1.1, 1.2, 1.3)
        // 1.2 https://developer.android.com/training/basics/intents/result
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            imageUri = uri;
            a_add_eve_foto_eve.setImageURI(imageUri);
            //1.3
            subirNewFoto = true;
        });

        //click por la imagen
        // 1.1 https://firebase.google.com/docs/storage/android/upload-files?hl=es-419
        a_add_eve_foto_eve.setOnClickListener(View1 -> {
            mGetContent.launch("image/*");
        });


        //los botones del evento
        a_add_eve_guardar.setOnClickListener(view1 -> {
            if (comprobacionDeLosCamposObligatorios())
                modalConfirmacionUpdateOrCreate(view1);
        });
        a_add_eve_limpiar_campos.setOnClickListener(view13 -> limpiarEve());
        a_add_eve_cancelar.setOnClickListener(view13 -> Navigation.findNavController(view13).navigate(R.id.action_nav_a_create_eve_to_nav_v01));


        //los botones de la actividad1
        a_add_eve_aniadir_act1.setOnClickListener(view12 -> a_add_eve_añadir_act2.setVisibility(View.VISIBLE));
        a_add_eve_eliminar_act1.setOnClickListener(view12 -> limpiarAct1());

        //los botones de la actividad2
        a_add_eve_aniadir_act2.setOnClickListener(view12 -> a_add_eve_añadir_act3.setVisibility(View.VISIBLE));
        a_add_eve_eliminar_act2.setOnClickListener(view12 -> {
            limpiarAct2();
            a_add_eve_añadir_act2.setVisibility(View.GONE);
        });

        //los botones de la actividad3
        a_add_eve_aniadir_act3.setOnClickListener(view12 -> a_add_eve_añadir_act4.setVisibility(View.VISIBLE));
        a_add_eve_eliminar_act3.setOnClickListener(view12 -> {
            limpiarAct3();
            a_add_eve_añadir_act3.setVisibility(View.GONE);
        });

        //los botones de la actividad4
        a_add_eve_aniadir_act4.setOnClickListener(view12 -> a_add_eve_añadir_act5.setVisibility(View.VISIBLE));
        a_add_eve_eliminar_act4.setOnClickListener(view12 -> {
            limpiarAct4();
            a_add_eve_añadir_act4.setVisibility(View.GONE);
        });

        //los botones de la actividad5
        a_add_eve_aniadir_act5.setOnClickListener(view12 -> Toast.makeText(getActivity(), "No se puede crear mas actividades", Toast.LENGTH_SHORT).show());
        a_add_eve_eliminar_act5.setOnClickListener(view12 -> {
            limpiarAct5();
            a_add_eve_añadir_act5.setVisibility(View.GONE);
        });

        return view;
    }//Fin de constructor

    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Toast.makeText(mContext, "Error en Firestore", Toast.LENGTH_SHORT).show();
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (T) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();
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
                    Toast.makeText(mContext, "Error en Firestore", Toast.LENGTH_SHORT).show();
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (S) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }

    private void changeNewImage(View view) {

        final ProgressDialog pd = new ProgressDialog(mContext);
        pd.setTitle("Cargando la imagen...");
        pd.show();
        str.child("Eventos/" + imageUri.getLastPathSegment()).putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    pd.dismiss();
                    Snackbar.make(view, "La imagen se ha cargado con exito!", Snackbar.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Error a cargar la imagen!", Toast.LENGTH_SHORT).show();
                });
    }


    private boolean comprobacionDeLosCamposObligatorios() {
        if (a_add_eve_titulo_eve.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Falta el titulo!", Toast.LENGTH_SHORT).show();
        } else {
            if (a_add_eve_distanciaidatru_eve.getText().toString().length() == 0 ||
                    a_add_eve_distanciavueltatru_eve.getText().toString().length() == 0 ||
                    a_add_eve_precio_eve.getText().toString().length() == 0) {
                a_add_eve_distanciaidatru_eve.setError("Campo obligatorio");
                a_add_eve_distanciavueltatru_eve.setError("Campo obligatorio");
                a_add_eve_precio_eve.setError("Campo obligatorio");
            } else {
                if (a_add_eve_nombre_act1.getText().toString().length() == 0) {
                    return true;
                } else {
                    if (a_add_eve_nombre_act1.getText().toString().length() == 0 ||
                            a_add_eve_distancia_act1.getText().toString().length() == 0 ||
                            a_add_eve_desnivel_act1.getText().toString().length() == 0 ||
                            a_add_eve_horas_act1.getText().toString().length() == 0) {
                        a_add_eve_nombre_act1.setError("Campo obligatorio");
                        a_add_eve_distancia_act1.setError("Campo obligatorio");
                        a_add_eve_desnivel_act1.setError("Campo obligatorio");
                        a_add_eve_horas_act1.setError("Campo obligatorio");
                    } else {
                        if (a_add_eve_nombre_act2.getText().toString().length() == 0) {
                            return true;
                        } else {
                            if (a_add_eve_nombre_act2.getText().toString().length() == 0 ||
                                    a_add_eve_distancia_act2.getText().toString().length() == 0 ||
                                    a_add_eve_desnivel_act2.getText().toString().length() == 0 ||
                                    a_add_eve_horas_act2.getText().toString().length() == 0) {
                                a_add_eve_nombre_act2.setError("Campo obligatorio");
                                a_add_eve_distancia_act2.setError("Campo obligatorio");
                                a_add_eve_desnivel_act2.setError("Campo obligatorio");
                                a_add_eve_horas_act2.setError("Campo obligatorio");
                            } else {
                                if (a_add_eve_nombre_act3.getText().toString().length() == 0) {
                                    return true;
                                } else {
                                    if (a_add_eve_nombre_act3.getText().toString().length() == 0 ||
                                            a_add_eve_distancia_act3.getText().toString().length() == 0 ||
                                            a_add_eve_desnivel_act3.getText().toString().length() == 0 ||
                                            a_add_eve_horas_act3.getText().toString().length() == 0) {
                                        a_add_eve_nombre_act3.setError("Campo obligatorio");
                                        a_add_eve_distancia_act3.setError("Campo obligatorio");
                                        a_add_eve_desnivel_act3.setError("Campo obligatorio");
                                        a_add_eve_horas_act3.setError("Campo obligatorio");
                                    } else {
                                        if (a_add_eve_nombre_act4.getText().toString().length() == 0) {
                                            return true;
                                        } else {
                                            if (a_add_eve_nombre_act4.getText().toString().length() == 0 ||
                                                    a_add_eve_distancia_act4.getText().toString().length() == 0 ||
                                                    a_add_eve_desnivel_act4.getText().toString().length() == 0 ||
                                                    a_add_eve_horas_act4.getText().toString().length() == 0) {
                                                a_add_eve_nombre_act4.setError("Campo obligatorio");
                                                a_add_eve_distancia_act4.setError("Campo obligatorio");
                                                a_add_eve_desnivel_act4.setError("Campo obligatorio");
                                                a_add_eve_horas_act4.setError("Campo obligatorio");
                                            } else {
                                                if (a_add_eve_nombre_act5.getText().toString().length() == 0) {
                                                    return true;
                                                } else {
                                                    if (a_add_eve_nombre_act5.getText().toString().length() == 0 ||
                                                            a_add_eve_distancia_act5.getText().toString().length() == 0 ||
                                                            a_add_eve_desnivel_act5.getText().toString().length() == 0 ||
                                                            a_add_eve_horas_act5.getText().toString().length() == 0) {
                                                        a_add_eve_nombre_act5.setError("Campo obligatorio");
                                                        a_add_eve_distancia_act5.setError("Campo obligatorio");
                                                        a_add_eve_desnivel_act5.setError("Campo obligatorio");
                                                        a_add_eve_horas_act5.setError("Campo obligatorio");
                                                    } else {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void modalConfirmacionUpdateOrCreate(View view) {
        AlertDialog dialogo = new AlertDialog
                .Builder(view.getContext())
                .setPositiveButton("Actualizar", (dialog, which) -> guardarEve(view, true))
                .setNegativeButton("Crear", (dialog, which) -> guardarEve(view, false))
                .setTitle("Confirmar")
                .setMessage("¿Que deseas hacer con este evento?")
                .create();
        dialogo.show();
    }

    private void guardarEve(View view, boolean update_create) {

        //sacamos el mayor id_eve del arrlist de eventos
        for (Evento_eve eve : eventos) {
            // añadir mas controles para rellenar huecos del arrlist
            if (eve.getId_eve() > this.id_new_eve) {
                this.id_new_eve = eve.getId_eve();
            }
            this.id_new_eve++;
        }
        //sacamos el mayor id_act del arrlist de actividades
        for (Actividad_act act : actividades) {
            // añadir mas controles para rellenar huecos del arrlist
            if (act.getId_act() > this.id_new_act) {
                this.id_new_act = act.getId_act();
            }
            // this.id_new_act++;
        }

        Evento_eve evento_push = recuperacionEve();
        //actualizar
        if (update_create) {
            guardar_acts(Integer.parseInt(a_add_eve_id_eve.getText().toString().trim()));
            fbf.collection("evento_eve").document(String.valueOf(Integer.parseInt(a_add_eve_id_eve.getText().toString().trim()))).set(evento_push);
            Toast.makeText(getActivity(), "El evento se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
        } //crear
        else {
            if (imageUri != null)
                evento_push.setFoto_eve(imageUri.getLastPathSegment());
            evento_push.setId_eve(id_new_eve);
            guardar_acts(id_new_eve);
            fbf.collection("evento_eve").document(Integer.toString(id_new_eve)).set(evento_push);
            Toast.makeText(getActivity(), "El evento se ha creado correctamente", Toast.LENGTH_SHORT).show();
        }
        if (subirNewFoto)
            changeNewImage(view);
        Navigation.findNavController(view).navigate(R.id.action_nav_a_create_eve_to_nav_v01);
    }

    private void guardar_acts(int id_eve) {
        if (!a_add_eve_nombre_act1.getText().toString().trim().isEmpty()) {
            Actividad_act act1 = recuperacionAct1();
            act1.setId_eve(id_eve);
            boolean existAct1 = false;
            for (Actividad_act act : actividades) {
                if (act1.equals(act)) {
                    existAct1 = true;
                }
            }
            if (!existAct1)
                act1.setId_act(++id_new_act);
            fbf.collection("actividad_act").document(Integer.toString(act1.getId_act())).set(act1);
            Toast.makeText(getActivity(), "act1 save", Toast.LENGTH_SHORT).show();

            if (!a_add_eve_nombre_act2.getText().toString().trim().isEmpty()) {
                Actividad_act act2 = recuperacionAct2();
                act2.setId_eve(id_eve);
                boolean existAct2 = false;
                for (Actividad_act act : actividades) {
                    if (act2.equals(act)) {
                        existAct2 = true;
                    }
                }
                if (!existAct2)
                    act2.setId_act(++id_new_act);
                fbf.collection("actividad_act").document(Integer.toString(act2.getId_act())).set(act2);
                Toast.makeText(getActivity(), "act2 save", Toast.LENGTH_SHORT).show();

                if (!a_add_eve_nombre_act3.getText().toString().trim().isEmpty()) {
                    Actividad_act act3 = recuperacionAct3();
                    act3.setId_eve(id_eve);
                    boolean existAct3 = false;
                    for (Actividad_act act : actividades) {
                        if (act3.equals(act)) {
                            existAct3 = true;
                        }
                    }
                    if (!existAct3)
                        act3.setId_act(++id_new_act);
                    fbf.collection("actividad_act").document(Integer.toString(act3.getId_act())).set(act3);
                    Toast.makeText(getActivity(), "act3 save", Toast.LENGTH_SHORT).show();

                    if (!a_add_eve_nombre_act4.getText().toString().trim().isEmpty()) {
                        Actividad_act act4 = recuperacionAct4();
                        act4.setId_eve(id_eve);
                        boolean existAct4 = false;
                        for (Actividad_act act : actividades) {
                            if (act4.equals(act)) {
                                existAct4 = true;
                            }
                        }
                        if (!existAct4)
                            act4.setId_act(++id_new_act);
                        fbf.collection("actividad_act").document(Integer.toString(act4.getId_act())).set(act4);
                        Toast.makeText(getActivity(), "act4 save", Toast.LENGTH_SHORT).show();

                        if (!a_add_eve_nombre_act5.getText().toString().trim().isEmpty()) {
                            Actividad_act act5 = recuperacionAct5();
                            act5.setId_eve(id_eve);
                            boolean existAct5 = false;
                            for (Actividad_act act : actividades) {
                                if (act5.equals(act)) {
                                    existAct5 = true;
                                }
                            }
                            if (!existAct5)
                                act5.setId_act(++id_new_act);
                            fbf.collection("actividad_act").document(Integer.toString(act5.getId_act())).set(act5);
                            Toast.makeText(getActivity(), "act5 save", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    private void asignacionDeLosCamposDeAAddEve(View view) {
        //evento eve
        a_add_eve_id_eve = view.findViewById(R.id.a_add_eve_etx_id_eve);
        a_add_eve_titulo_eve = view.findViewById(R.id.a_add_eve_etx_titulo_eve);
        a_add_eve_foto_eve = view.findViewById(R.id.a_add_eve_imv_foto_eve);
        a_add_eve_foto_ruta_eve = view.findViewById(R.id.a_add_eve_imv_foto_ruta_eve);
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
        a_add_eve_guardar = view.findViewById(R.id.a_add_eve_btn_guardar);
        a_add_eve_limpiar_campos = view.findViewById(R.id.a_add_eve_btn_limpiar_campos);
        a_add_eve_cancelar = view.findViewById(R.id.a_add_eve_btn_cancelar);

        //actividad act 1
        a_add_eve_id_act1 = view.findViewById(R.id.a_add_eve_etx_id_act1);
        a_add_eve_añadir_act1 = view.findViewById(R.id.a_add_eve_ll_añadir_act1);
        a_add_eve_nombre_act1 = view.findViewById(R.id.a_add_eve_etx_nombre_act1);
        a_add_eve_actividadtipo_act1 = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act1);
        a_add_eve_fecha_act1 = view.findViewById(R.id.a_add_eve_etx_fecha_act1);
        a_add_eve_nivel_act1 = view.findViewById(R.id.a_add_eve_etx_nivel_act1);
        a_add_eve_distancia_act1 = view.findViewById(R.id.a_add_eve_etx_distancia_act1);
        a_add_eve_desnivel_act1 = view.findViewById(R.id.a_add_eve_etx_desnivel_act1);
        a_add_eve_horas_act1 = view.findViewById(R.id.a_add_eve_etx_horas_act1);
        a_add_eve_wikiloc_act1 = view.findViewById(R.id.a_add_eve_etx_wikiloc_act1);
        a_add_eve_aniadir_act1 = view.findViewById(R.id.a_add_eve_btn_aniadir_act1);
        a_add_eve_eliminar_act1 = view.findViewById(R.id.a_add_eve_btn_eliminar_act1);

        //actividad act 2
        a_add_eve_id_act2 = view.findViewById(R.id.a_add_eve_etx_id_act2);
        a_add_eve_añadir_act2 = view.findViewById(R.id.a_add_eve_ll_añadir_act2);
        a_add_eve_nombre_act2 = view.findViewById(R.id.a_add_eve_etx_nombre_act2);
        a_add_eve_actividadtipo_act2 = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act2);
        a_add_eve_fecha_act2 = view.findViewById(R.id.a_add_eve_etx_fecha_act2);
        a_add_eve_nivel_act2 = view.findViewById(R.id.a_add_eve_etx_nivel_act2);
        a_add_eve_distancia_act2 = view.findViewById(R.id.a_add_eve_etx_distancia_act2);
        a_add_eve_desnivel_act2 = view.findViewById(R.id.a_add_eve_etx_desnivel_act2);
        a_add_eve_horas_act2 = view.findViewById(R.id.a_add_eve_etx_horas_act2);
        a_add_eve_wikiloc_act2 = view.findViewById(R.id.a_add_eve_etx_wikiloc_act2);
        a_add_eve_aniadir_act2 = view.findViewById(R.id.a_add_eve_btn_aniadir_act2);
        a_add_eve_eliminar_act2 = view.findViewById(R.id.a_add_eve_btn_eliminar_act2);

        //actividad act 3
        a_add_eve_id_act3 = view.findViewById(R.id.a_add_eve_etx_id_act3);
        a_add_eve_añadir_act3 = view.findViewById(R.id.a_add_eve_ll_añadir_act3);
        a_add_eve_nombre_act3 = view.findViewById(R.id.a_add_eve_etx_nombre_act3);
        a_add_eve_actividadtipo_act3 = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act3);
        a_add_eve_fecha_act3 = view.findViewById(R.id.a_add_eve_etx_fecha_act3);
        a_add_eve_nivel_act3 = view.findViewById(R.id.a_add_eve_etx_nivel_act3);
        a_add_eve_distancia_act3 = view.findViewById(R.id.a_add_eve_etx_distancia_act3);
        a_add_eve_desnivel_act3 = view.findViewById(R.id.a_add_eve_etx_desnivel_act3);
        a_add_eve_horas_act3 = view.findViewById(R.id.a_add_eve_etx_horas_act3);
        a_add_eve_wikiloc_act3 = view.findViewById(R.id.a_add_eve_etx_wikiloc_act3);
        a_add_eve_aniadir_act3 = view.findViewById(R.id.a_add_eve_btn_aniadir_act3);
        a_add_eve_eliminar_act3 = view.findViewById(R.id.a_add_eve_btn_eliminar_act3);

        //actividad act 4
        a_add_eve_id_act4 = view.findViewById(R.id.a_add_eve_etx_id_act4);
        a_add_eve_añadir_act4 = view.findViewById(R.id.a_add_eve_ll_añadir_act4);
        a_add_eve_nombre_act4 = view.findViewById(R.id.a_add_eve_etx_nombre_act4);
        a_add_eve_actividadtipo_act4 = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act4);
        a_add_eve_fecha_act4 = view.findViewById(R.id.a_add_eve_etx_fecha_act4);
        a_add_eve_nivel_act4 = view.findViewById(R.id.a_add_eve_etx_nivel_act4);
        a_add_eve_distancia_act4 = view.findViewById(R.id.a_add_eve_etx_distancia_act4);
        a_add_eve_desnivel_act4 = view.findViewById(R.id.a_add_eve_etx_desnivel_act4);
        a_add_eve_horas_act4 = view.findViewById(R.id.a_add_eve_etx_horas_act4);
        a_add_eve_wikiloc_act4 = view.findViewById(R.id.a_add_eve_etx_wikiloc_act4);
        a_add_eve_aniadir_act4 = view.findViewById(R.id.a_add_eve_btn_aniadir_act4);
        a_add_eve_eliminar_act4 = view.findViewById(R.id.a_add_eve_btn_eliminar_act4);

        //actividad act 5
        a_add_eve_id_act5 = view.findViewById(R.id.a_add_eve_etx_id_act5);
        a_add_eve_añadir_act5 = view.findViewById(R.id.a_add_eve_ll_añadir_act5);
        a_add_eve_nombre_act5 = view.findViewById(R.id.a_add_eve_etx_nombre_act5);
        a_add_eve_actividadtipo_act5 = view.findViewById(R.id.a_add_eve_etx_actividadtipo_act5);
        a_add_eve_fecha_act5 = view.findViewById(R.id.a_add_eve_etx_fecha_act5);
        a_add_eve_nivel_act5 = view.findViewById(R.id.a_add_eve_etx_nivel_act5);
        a_add_eve_distancia_act5 = view.findViewById(R.id.a_add_eve_etx_distancia_act5);
        a_add_eve_desnivel_act5 = view.findViewById(R.id.a_add_eve_etx_desnivel_act5);
        a_add_eve_horas_act5 = view.findViewById(R.id.a_add_eve_etx_horas_act5);
        a_add_eve_wikiloc_act5 = view.findViewById(R.id.a_add_eve_etx_wikiloc_act5);
        a_add_eve_aniadir_act5 = view.findViewById(R.id.a_add_eve_btn_aniadir_act5);
        a_add_eve_eliminar_act5 = view.findViewById(R.id.a_add_eve_btn_eliminar_act5);
    }

    private Evento_eve recuperacionEve() {
        Evento_eve eve = new Evento_eve();
        eve.setId_eve(Integer.parseInt(a_add_eve_id_eve.getText().toString().trim()));
        eve.setTitulo_eve(a_add_eve_titulo_eve.getText().toString().trim());
        eve.setFoto_eve(a_add_eve_foto_ruta_eve.getText().toString().trim());
        eve.setFechaidatru_eve(a_add_eve_fechaidatru_eve.getText().toString().trim());
        eve.setFechavueltatru_eve(a_add_eve_fechavueltatru_eve.getText().toString().trim());
        eve.setNivel_eve(a_add_eve_nivel_eve.getText().toString().trim());
        eve.setSalidaidatru_eve(a_add_eve_salidaidatru_eve.getText().toString().trim());
        eve.setLlegadaidatru_eve(a_add_eve_llegadaidatru_eve.getText().toString().trim());
        eve.setDistanciaidatru_eve(Integer.parseInt(a_add_eve_distanciaidatru_eve.getText().toString().trim()));
        eve.setDistanciavueltatru_eve(Integer.parseInt(a_add_eve_distanciavueltatru_eve.getText().toString().trim()));
        eve.setLlegadacoordenadastru_eve(a_add_eve_llegadacoordenadastru_eve.getText().toString().trim());
        eve.setSalidacoordenadastru_eve(a_add_eve_salidacoordenadastru_eve.getText().toString().trim());
        eve.setLlegadavueltatru_eve(a_add_eve_llegadavueltatru_eve.getText().toString().trim());
        eve.setPrecio_eve(Integer.parseInt(a_add_eve_precio_eve.getText().toString().trim()));
        eve.setTransportetipo_eve(a_add_eve_transportetipo_eve.getText().toString().trim());
        eve.setEstado_eve("Pendiente");
        return eve;
    }

    private void limpiarEve() {
        a_add_eve_titulo_eve.getText().clear();
        a_add_eve_foto_eve.setImageResource(R.drawable.ic_menu_gallery);
        a_add_eve_foto_ruta_eve.getText().clear();
        ;
        a_add_eve_fechaidatru_eve.getText().clear();
        a_add_eve_fechavueltatru_eve.getText().clear();
        a_add_eve_nivel_eve.getText().clear();
        a_add_eve_salidaidatru_eve.getText().clear();
        a_add_eve_llegadaidatru_eve.getText().clear();
        a_add_eve_distanciaidatru_eve.getText().clear();
        a_add_eve_distanciavueltatru_eve.getText().clear();
        a_add_eve_llegadacoordenadastru_eve.getText().clear();
        a_add_eve_salidacoordenadastru_eve.getText().clear();
        a_add_eve_llegadavueltatru_eve.getText().clear();
        a_add_eve_precio_eve.getText().clear();
        a_add_eve_transportetipo_eve.getText().clear();
    }


    private Actividad_act recuperacionAct1() {
        Actividad_act act1 = new Actividad_act();
        act1.setId_act(Integer.parseInt(a_add_eve_id_act1.getText().toString().trim()));
        act1.setNombre_act(a_add_eve_nombre_act1.getText().toString().trim());
        act1.setActividadtipo_act(a_add_eve_actividadtipo_act1.getText().toString().trim());
        act1.setFecha_act(a_add_eve_fecha_act1.getText().toString().trim());
        act1.setNivel_act(a_add_eve_nivel_act1.getText().toString().trim());
        act1.setDistancia_act(Integer.parseInt(a_add_eve_distancia_act1.getText().toString().trim()));
        act1.setDesnivel_act(Integer.parseInt(a_add_eve_desnivel_act1.getText().toString().trim()));
        act1.setHoras_act(Integer.parseInt(a_add_eve_horas_act1.getText().toString().trim()));
        act1.setWikiloc_act(a_add_eve_wikiloc_act1.getText().toString().trim());
        return act1;
    }

    private Actividad_act recuperacionAct2() {
        Actividad_act act2 = new Actividad_act();
        act2.setId_act(Integer.parseInt(a_add_eve_id_act2.getText().toString().trim()));
        act2.setNombre_act(a_add_eve_nombre_act2.getText().toString().trim());
        act2.setActividadtipo_act(a_add_eve_actividadtipo_act2.getText().toString().trim());
        act2.setFecha_act(a_add_eve_fecha_act2.getText().toString().trim());
        act2.setNivel_act(a_add_eve_nivel_act2.getText().toString().trim());
        act2.setDistancia_act(Integer.parseInt(a_add_eve_distancia_act2.getText().toString().trim()));
        act2.setDesnivel_act(Integer.parseInt(a_add_eve_desnivel_act2.getText().toString().trim()));
        act2.setHoras_act(Integer.parseInt(a_add_eve_horas_act2.getText().toString().trim()));
        act2.setWikiloc_act(a_add_eve_wikiloc_act2.getText().toString().trim());
        return act2;
    }

    private Actividad_act recuperacionAct3() {
        Actividad_act act3 = new Actividad_act();
        act3.setId_act(Integer.parseInt(a_add_eve_id_act3.getText().toString().trim()));
        act3.setNombre_act(a_add_eve_nombre_act3.getText().toString().trim());
        act3.setActividadtipo_act(a_add_eve_actividadtipo_act3.getText().toString().trim());
        act3.setFecha_act(a_add_eve_fecha_act3.getText().toString().trim());
        act3.setNivel_act(a_add_eve_nivel_act3.getText().toString().trim());
        act3.setDistancia_act(Integer.parseInt(a_add_eve_distancia_act3.getText().toString().trim()));
        act3.setDesnivel_act(Integer.parseInt(a_add_eve_desnivel_act3.getText().toString().trim()));
        act3.setHoras_act(Integer.parseInt(a_add_eve_horas_act3.getText().toString().trim()));
        act3.setWikiloc_act(a_add_eve_wikiloc_act3.getText().toString().trim());
        return act3;
    }

    private Actividad_act recuperacionAct4() {
        Actividad_act act4 = new Actividad_act();
        act4.setId_act(Integer.parseInt(a_add_eve_id_act4.getText().toString().trim()));
        act4.setNombre_act(a_add_eve_nombre_act4.getText().toString().trim());
        act4.setActividadtipo_act(a_add_eve_actividadtipo_act4.getText().toString().trim());
        act4.setFecha_act(a_add_eve_fecha_act4.getText().toString().trim());
        act4.setNivel_act(a_add_eve_nivel_act4.getText().toString().trim());
        act4.setDistancia_act(Integer.parseInt(a_add_eve_distancia_act4.getText().toString().trim()));
        act4.setDesnivel_act(Integer.parseInt(a_add_eve_desnivel_act4.getText().toString().trim()));
        act4.setHoras_act(Integer.parseInt(a_add_eve_horas_act4.getText().toString().trim()));
        act4.setWikiloc_act(a_add_eve_wikiloc_act4.getText().toString().trim());
        return act4;
    }

    private Actividad_act recuperacionAct5() {
        Actividad_act act5 = new Actividad_act();
        act5.setId_act(Integer.parseInt(a_add_eve_id_act5.getText().toString().trim()));
        act5.setNombre_act(a_add_eve_nombre_act5.getText().toString().trim());
        act5.setActividadtipo_act(a_add_eve_actividadtipo_act5.getText().toString().trim());
        act5.setFecha_act(a_add_eve_fecha_act5.getText().toString().trim());
        act5.setNivel_act(a_add_eve_nivel_act5.getText().toString().trim());
        act5.setDistancia_act(Integer.parseInt(a_add_eve_distancia_act5.getText().toString().trim()));
        act5.setDesnivel_act(Integer.parseInt(a_add_eve_desnivel_act5.getText().toString().trim()));
        act5.setHoras_act(Integer.parseInt(a_add_eve_horas_act5.getText().toString().trim()));
        act5.setWikiloc_act(a_add_eve_wikiloc_act5.getText().toString().trim());
        return act5;
    }


    private void limpiarAct1() {
        a_add_eve_nombre_act1.getText().clear();
        a_add_eve_nombre_act1.getText().clear();
        a_add_eve_actividadtipo_act1.getText().clear();
        a_add_eve_fecha_act1.getText().clear();
        a_add_eve_nivel_act1.getText().clear();
        a_add_eve_distancia_act1.getText().clear();
        a_add_eve_desnivel_act1.getText().clear();
        a_add_eve_horas_act1.getText().clear();
        a_add_eve_wikiloc_act1.getText().clear();
    }

    private void limpiarAct2() {
        a_add_eve_nombre_act2.getText().clear();
        a_add_eve_actividadtipo_act2.getText().clear();
        a_add_eve_fecha_act2.getText().clear();
        a_add_eve_nivel_act2.getText().clear();
        a_add_eve_distancia_act2.getText().clear();
        a_add_eve_desnivel_act2.getText().clear();
        a_add_eve_horas_act2.getText().clear();
        a_add_eve_wikiloc_act2.getText().clear();
    }

    private void limpiarAct3() {
        a_add_eve_nombre_act3.getText().clear();
        a_add_eve_actividadtipo_act3.getText().clear();
        a_add_eve_fecha_act3.getText().clear();
        a_add_eve_nivel_act3.getText().clear();
        a_add_eve_distancia_act3.getText().clear();
        a_add_eve_desnivel_act3.getText().clear();
        a_add_eve_horas_act3.getText().clear();
        a_add_eve_wikiloc_act3.getText().clear();
    }

    private void limpiarAct4() {
        a_add_eve_nombre_act4.getText().clear();
        a_add_eve_actividadtipo_act4.getText().clear();
        a_add_eve_fecha_act4.getText().clear();
        a_add_eve_nivel_act4.getText().clear();
        a_add_eve_distancia_act4.getText().clear();
        a_add_eve_desnivel_act4.getText().clear();
        a_add_eve_horas_act4.getText().clear();
        a_add_eve_wikiloc_act4.getText().clear();
    }

    private void limpiarAct5() {
        a_add_eve_nombre_act5.getText().clear();
        a_add_eve_actividadtipo_act5.getText().clear();
        a_add_eve_fecha_act5.getText().clear();
        a_add_eve_nivel_act5.getText().clear();
        a_add_eve_distancia_act5.getText().clear();
        a_add_eve_desnivel_act5.getText().clear();
        a_add_eve_horas_act5.getText().clear();
        a_add_eve_wikiloc_act5.getText().clear();
    }
}