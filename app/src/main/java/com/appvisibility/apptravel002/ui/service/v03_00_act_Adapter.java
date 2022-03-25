package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class v03_00_act_Adapter extends RecyclerView.Adapter<v03_00_act_Adapter.ViewHolder> {

    //private List<Actividad_act> actividades;
    int posicionseleccionada /*= -1*/;
    private FirebaseFirestore firebaseFirestore;
    private int posicionEventoInt;

    /*public v03_00_act_Adapter(List<Actividad_act> actividades) {
        this.actividades = actividades;
    }*/
    public v03_00_act_Adapter(int posicionEventoInt) {
        this.posicionEventoInt = posicionEventoInt;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txvNombreAct;
        private TextView txvActividadTipoAct;
        private TextView txvFechaAct;
        private TextView txvNivelAct;
        private TextView txvDistanciaAct;
        private TextView txvDesnivelAct;
        private TextView txvHorasAct;

        private CardView cardAct;

        public ViewHolder(View view) {
            super(view);

            txvNombreAct = view.findViewById(R.id.txvNombreAct);
            txvActividadTipoAct = view.findViewById(R.id.txvActividadTipoAct);
            txvFechaAct = view.findViewById(R.id.txvFechaAct);
            txvNivelAct = view.findViewById(R.id.txvNivelAct);
            txvDistanciaAct = view.findViewById(R.id.txvDistanciaAct);
            txvDesnivelAct = view.findViewById(R.id.txvDesnivelAct);
            txvHorasAct = view.findViewById(R.id.txvHorasAct);

            cardAct = (CardView) view.findViewById(R.id.cardAct);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_v_03_act_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    /**
     * Proporciona los datos: Se encarga de establecer los objetos en el ViewHolder y la posición.
     */
    public void onBindViewHolder(ViewHolder holder, int position) {

      /*  String nombre_act = actividades.get(position).getNombre_act();
        String actividadtipo_act = actividades.get(position).getActividadtipo_act();
        String fecha_act = actividades.get(position).getFecha_act();
        String nivel_act = actividades.get(position).getNivel_act();
        int distancia_act = actividades.get(position).getDistancia_act();
        int desnivel_act = actividades.get(position).getDesnivel_act();
        int horas_act = actividades.get(position).getHoras_act();

        holder.txvNombreAct.setText(nombre_act);
        holder.txvActividadTipoAct.setText(actividadtipo_act);
        holder.txvFechaAct.setText(fecha_act);
        holder.txvNivelAct.setText("Nivel: " + nivel_act);
        holder.txvDistanciaAct.setText("Distancia: " + String.valueOf(distancia_act));
        holder.txvDesnivelAct.setText("Desnivel: " + String.valueOf(desnivel_act));
        holder.txvHorasAct.setText("Horas de marcha: " + String.valueOf(horas_act));*/

        String posicionEventoString = Integer.toString(posicionEventoInt+1);

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("actividad_act")
                //aqui tengo el filtro
                .whereEqualTo("id_eve",  posicionEventoString)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                holder.txvNombreAct.setText(document.getString("nombre_act"));
                                holder.txvActividadTipoAct.setText(document.getString("actividadtipo_act"));
                                holder.txvFechaAct.setText(document.getString("fecha_act"));
                                holder.txvNivelAct.setText("Nivel: " + document.getString("nivel_act"));
                                holder.txvDistanciaAct.setText("Distancia: " + document.getString("distancia_act"));
                                holder.txvDesnivelAct.setText("Desnivel: " + document.getString("desnivel_act"));
                                holder.txvHorasAct.setText("Horas de marcha: " + document.getString("horas_act"));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        holder.cardAct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                posicionseleccionada = position;
                //Notificamos cambios para que el contenedor se entere y refresque los datos
                // La siguiente instruccion está a la escucha de posible cambios y los refresca. ATENCION: consume mucha memoria porque está permanentemente a la escucha
//                notifyDataSetChanged();
//                System.out.println(holder.txtnombre.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4/*actividades.size()*/;
    }


}