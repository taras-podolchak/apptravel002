package com.appvisibility.apptravel002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.appvisibility.apptravel002.ui.controller.V_01;
import com.appvisibility.apptravel002.ui.controller.V_02;
import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.controller.V_04;
import com.appvisibility.apptravel002.ui.controller.V_05;
import com.appvisibility.apptravel002.ui.controller.V_06;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.appvisibility.apptravel002.databinding.ActivityMainAdmBinding;

public class MainActivity_adm extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainAdmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int num = intent.getIntExtra("abrirEnMainActivity_adm", 0);
        int id_eve_bundle = intent.getIntExtra("eventoParaV_03", 0);

        Bundle result = new Bundle();
        result.putInt("eventoParaV_04", id_eve_bundle);

        if (num == 1) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_01()).commit();
        }
        if (num == 2) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_02()).commit();
        }
        if (num == 3) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_03()).commit();
        }
        if (num == 4) {
            V_04 v_04 = new V_04();
            v_04.setArguments(result);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, v_04).commit();

            /*getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,( new V_04().setArguments(result))).commit();*/
        }
        if (num == 5) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_05()).commit();
        }
        if (num == 6) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_06()).commit();
        } else {
            Toast.makeText(this, "Bundle llega vacio", Toast.LENGTH_LONG).show();
        }

        binding = ActivityMainAdmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityAdm.toolbar);
        binding.appBarMainActivityAdm.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_v01_inicio, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_adm);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_adm, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_adm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}