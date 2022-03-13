package com.appvisibility.apptravel002.ui.valiente.v_03;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.valiente.v_02.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act_data;
import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Evento_eve;
import com.squareup.picasso.Picasso;

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
    private Button v03_boton_me_interesaa, v03_boton_volver;
    private Evento_eve evento;
    private TextView titulo_eve;
    private ImageView foto_eve;
    private TextView fechaidatru_eve;
    private TextView fechavueltatru_eve;
    private TextView transportetipo_eve;
    private TextView nparticipantes_eve;
    private Context mContext;
    private RecyclerView miSubRecicler;
    private v03_00_act_Adapter miSubAdapter;


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
        this.evento = (Evento_eve) bundle.getSerializable("evento");

        this.titulo_eve = view.findViewById(R.id.txvTituloEve);
        this.foto_eve = view.findViewById(R.id.imvFotoEve);
        this.fechaidatru_eve = view.findViewById(R.id.txvFechaIdaTruEve);
        this.fechavueltatru_eve = view.findViewById(R.id.txvFechaVueltaTruEve);
        this.transportetipo_eve = view.findViewById(R.id.txvTransporteTipoEve);
        this.nparticipantes_eve = view.findViewById(R.id.txvNParticipantesEve);

        this.titulo_eve.setText(this.evento.getTitulo_eve());
        Picasso.get().load(evento.getFoto_eve()).into(foto_eve);
//        this.foto_eve.setImageResource(this.evento.getFoto_eve());
        this.fechaidatru_eve.setText(this.evento.getFechaidatru_eve());
        this.fechavueltatru_eve.setText(this.evento.getFechavueltatru_eve());
        this.transportetipo_eve.setText(this.evento.getTransportetipo_eve());
//        this.nparticipantes_eve.setText(this.evento.getNparticipantes_eve());

// Creación del recicler de Actividad dentro del recicler Evento
        this.miSubRecicler = (RecyclerView) view.findViewById(R.id.v03_00_act);
        this.miSubRecicler.setHasFixedSize(true);
        this.miSubRecicler.setLayoutManager(new LinearLayoutManager(mContext));

//        this.miSubAdapter = new v03_00_act_Adapter(Actividad_act_data.actividades);
        this.miSubAdapter = new v03_00_act_Adapter(Actividad_act_data.actividadesExtracto(evento.getId_eve()));
        this.miSubRecicler.setAdapter(miSubAdapter);


        v03_boton_me_interesaa = view.findViewById(R.id.v03_boton_me_interesa);
        v03_boton_me_interesaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sesionIniciada){
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v04);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_nav_v03_to_nav_v05);
                }

            }
        });
        v03_boton_volver = view.findViewById(R.id.v03_boton_volver);
        v03_boton_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v03_to_nav_v02));//lamda.. java8+
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}