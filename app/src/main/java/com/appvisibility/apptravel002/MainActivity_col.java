package com.appvisibility.apptravel002;

import static com.appvisibility.apptravel002.MainActivity.ASK_QUESTION_REQUEST;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.appvisibility.apptravel002.databinding.ActivityMainColBinding;
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

public class MainActivity_col extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainColBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //Bundle bundle = new Bundle();
        int num = intent.getIntExtra("abrirEnMainActivity_col", 0);
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
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new V_04()).commit();
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


        binding = ActivityMainColBinding.inflate(

                getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityCol.toolbar);
        binding.appBarMainActivityCol.fab.setOnClickListener(new View.OnClickListener() {
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
                R.id.nav_v01, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .

                        build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_col);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_col, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_col);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Comprobamos si el resultado es cancelado
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_LONG).show();
            } else {
                // En caso de ser OK
                //inicializar con un fragmento
                V_04 taskFragment = new V_04();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.nav_v04, taskFragment);
                transaction.commit();
            }
        }*/
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
                V_04 taskFragment = new V_04();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.nav_v04, taskFragment);
                transaction.commit();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }*/
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 1
        if (requestCode == ASK_QUESTION_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                V_04 taskFragment = new V_04();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.nav_v04, taskFragment);
                transaction.commit();
            }
        }
    }*/

    /*public void openSomeActivityForResult() {
        Intent intent = new Intent(this, SomeActivity.class);
        activityLauncher.launch(intent, result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // There are no request codes
                Intent data = result.getData();
                doSomeOperations();
            }
        });
    }*/
}

