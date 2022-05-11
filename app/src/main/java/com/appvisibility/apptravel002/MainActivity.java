package com.appvisibility.apptravel002;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.appvisibility.apptravel002.databinding.ActivityMainBinding;
import com.appvisibility.apptravel002.ui.controller.V_02;
import com.appvisibility.apptravel002.ui.entities.Persona_per_test;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private Persona_per_test persona;
    public static int sesionIniciada = 0;
    static final int ASK_QUESTION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{"developeremail@gmail.com"});  //developer 's email
            Email.putExtra(Intent.EXTRA_SUBJECT,
                    "Add your Subject"); // Email 's Subject
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "");  //Email 's Greeting text
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
        });

        DrawerLayout drawer = binding.drawerLayout.findViewById(R.id.drawer_layout);
        NavigationView navigationView = binding.navView.findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_v01_inicio, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView mNavigationView = findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


    }   //fin de constructor

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //TODO para tener la cesion abierta
    @Override
    protected void onStart() {
        super.onStart();
        if (fba.getCurrentUser() != null) {
            //obtenemos la Uid del registro de la bbdd FirebaseAuth
            FirebaseUser user = fba.getCurrentUser();
            String uid = user.getUid();

            //buscamos en FirebaseFirestore el documento con esa Uid
            DocumentReference docRef = fbf.collection("persona_per_test").document(uid);
            docRef.get().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    DocumentSnapshot document = task1.getResult();
                    if (document.exists()) {
                        //recuperamos la persona
                        persona = (Persona_per_test) document.toObject(Persona_per_test.class);
                        if (persona.getUsuariotipo_per() == 1) {
                            sesionIniciada = persona.getUsuariotipo_per();
                        }
                        if (persona.getUsuariotipo_per() == 2) {
                            sesionIniciada = persona.getUsuariotipo_per();
                            Intent intent = new Intent(this, MainActivity_prs.class);
                            intent.putExtra("abrirEnMainActivity_col", 1);
                            intent.putExtra("eventoParaV_04", 1);
                            startActivity(intent);
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task1.getException());
                }
            });
        }
    }

    //TODO el menuImems onClicListener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.colaborador) {
            if (sesionIniciada == 0) {
                Intent intent = new Intent(this, MainActivity_prs.class);
                intent.putExtra("abrirEnMainActivity_col", 4);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 2) {
                getApplication().setTheme(R.style.Theme_Apptravel002_col);
                Fragment v_02 = new V_02();
                show(v_02);
            } else {
                Toast.makeText(MainActivity.this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.administrador) {
            if (sesionIniciada == 0) {
                Intent intent = new Intent(this, MainActivity_adm.class);
                intent.putExtra("abrirEnMainActivity_adm", 4);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 3) {
                getApplication().setTheme(R.style.Theme_Apptravel002_adm);
                Fragment v_02 = new V_02();
                show(v_02);
            } else {
                Toast.makeText(MainActivity.this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.cerrar_session) {
            getApplication().setTheme(R.style.Theme_Apptravel002);
            Toast.makeText(getApplicationContext(), "Cerramos la sesion", Toast.LENGTH_SHORT).show();
            fba.signOut();
            sesionIniciada = 0;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void show(Fragment fragment) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

}