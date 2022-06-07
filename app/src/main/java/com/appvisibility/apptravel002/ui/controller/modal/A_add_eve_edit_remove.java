package com.appvisibility.apptravel002.ui.controller.modal;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appvisibility.apptravel002.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link A_add_eve_edit_remove#newInstance} factory method to
 * create an instance of this fragment.
 */
public class A_add_eve_edit_remove extends DialogFragment {

    private Button a_add_eve_edit_remove_modal_cancelar, a_add_eve_edit_remove_modal_editar, a_add_eve_edit_remove_modal_eliminar;
    private boolean edit_eve;

    public boolean isEdit_eve() {
        return edit_eve;
    }

    public void setEdit_eve(boolean edit_eve) {
        this.edit_eve = edit_eve;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public A_add_eve_edit_remove() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment A_add_eve_edit_remove.
     */
    // TODO: Rename and change types and number of parameters
    public static A_add_eve_edit_remove newInstance(String param1, String param2) {
        A_add_eve_edit_remove fragment = new A_add_eve_edit_remove();
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
        View view = inflater.inflate(R.layout.fragment_a_add_eve_edit_remove, container, false);

        a_add_eve_edit_remove_modal_cancelar = view.findViewById(R.id.btn_a_add_eve_edit_remove_modal_cancelar);
        a_add_eve_edit_remove_modal_editar = view.findViewById(R.id.btn_a_add_eve_edit_remove_modal_editar);
        a_add_eve_edit_remove_modal_eliminar = view.findViewById(R.id.btn_a_add_eve_edit_remove_modal_eliminar);

        a_add_eve_edit_remove_modal_cancelar.setOnClickListener(view12 -> A_add_eve_edit_remove.this.getDialog().cancel());
        a_add_eve_edit_remove_modal_editar.setOnClickListener(view12 -> edit_eve = true);
        a_add_eve_edit_remove_modal_eliminar.setOnClickListener(view12 -> edit_eve = false);

        return view;
    }
}