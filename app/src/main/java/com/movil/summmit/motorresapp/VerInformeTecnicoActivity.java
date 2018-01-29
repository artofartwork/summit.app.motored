package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class VerInformeTecnicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_informe_tecnico);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Detalle Informe");
        getMenuInflater().inflate(R.menu.menu_detail_informe, menu);
        //super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent inte =new Intent(VerInformeTecnicoActivity.this, VerInformeAcivityDosActivity.class);
        startActivity(inte);
        //return super.onOptionsItemSelected(item);
        return true;
    }
}
