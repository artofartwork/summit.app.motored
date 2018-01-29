package com.movil.summmit.motorresapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.movil.summmit.motorresapp.Adapters.AnalisisCausaFallaAdapter;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntosDetalle;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoAdjuntosDetalleRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoConclusionesRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoFallaRepository;
import com.movil.summmit.motorresapp.Storage.db.repository.InformeTecnicoRecomendacionesRepository;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeController;
import com.movil.summmit.motorresapp.controllerSwipe.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

public class DetailTwoActivity extends AppCompatActivity {

    ListView lsvAdjuntos, lsvConclusiones, lsvRecomendaciones;
    AnalisisCausaFallaAdapter mAdapter;
   /* InformeTecnicoFallaRepository informeTecnicoFallaRepository;
    InformeTecnicoAdjuntosDetalleRepository informeTecnicoAdjuntosDetalleRepository;
    InformeTecnicoConclusionesRepository informeTecnicoConclusionesRepository;
    InformeTecnicoRecomendacionesRepository informeTecnicoRecomendacionesRepository;*/
   Repository repository;
    SwipeController swipeController = null;
    int IdInformeTecnico = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_two);
        Intent myIntent = getIntent(); // gets the previously created intent
        IdInformeTecnico = myIntent.getIntExtra("IdInformeTecnico", 0);
        repository = new Repository(this);
        /*informeTecnicoFallaRepository = new InformeTecnicoFallaRepository(this);
        informeTecnicoAdjuntosDetalleRepository = new InformeTecnicoAdjuntosDetalleRepository(this);
        informeTecnicoConclusionesRepository = new InformeTecnicoConclusionesRepository(this);
        informeTecnicoRecomendacionesRepository = new InformeTecnicoRecomendacionesRepository(this);*/

        setPlayersDataAdapter();
        setupRecyclerView();
        initComponentsTabs();

        lsvAdjuntos =(ListView)findViewById(R.id.lsvAdjuntos);
        lsvConclusiones =(ListView)findViewById(R.id.lsvConclusiones);
        lsvRecomendaciones =(ListView)findViewById(R.id.lsvRecomendaciones);
        initListaAdjjuntos();
        initConclusiones();
        initRecomendaciones();


    }
    private void initRecomendaciones()
    {
        List<InformeTecnicoRecomendaciones> lista = repository.informeTecnicoRecomendacionesRepository().findAllxInforme(IdInformeTecnico);
        List<String> datos = new ArrayList<>();
        for (InformeTecnicoRecomendaciones obj : lista)
        {
            datos.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        lsvRecomendaciones.setAdapter(adapter);
    }
    private void initConclusiones()
    {
        List<InformeTecnicoConclusiones> lista = repository.informeTecnicoConclusionesRepository().findAllxInforme(IdInformeTecnico);
        List<String> datos = new ArrayList<>();
        for (InformeTecnicoConclusiones obj : lista)
        {
            datos.add(obj.getDescripcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        lsvConclusiones.setAdapter(adapter);
    }
    private void initListaAdjjuntos()
    {
        List<InformeTecnicoAdjuntosDetalle> lista = repository.informeTecnicoAdjuntosDetalleRepository().findAllxInforme(IdInformeTecnico);
        List<String> adjuntosname = new ArrayList<>();
        for (InformeTecnicoAdjuntosDetalle obj : lista)
        {
            adjuntosname.add(obj.getArchivoNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, adjuntosname);
        lsvAdjuntos.setAdapter(adapter);
    }
    private void setPlayersDataAdapter() {
        List<InformeTecnicoFalla> players = new ArrayList<>();
        players = repository.informeTecnicoFallaRepository().findAllxInforme(IdInformeTecnico);

        mAdapter = new AnalisisCausaFallaAdapter(players);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listaanalisiscausa);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {

                //aqui poner la funcion para mostrar el fragment con detalles.
            }

            @Override
            public void onLeftClicked(int position) {
               // Toast.makeText(getBaseContext(), "hola mundo" + position, Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void initComponentsTabs()
    {
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Adjuntos");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Conclusiones");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Recomendaciones");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Detalles Informe");
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        //super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_home:
                Intent inte =new Intent(DetailTwoActivity.this, ListaInformesActivity.class);
                startActivity(inte);
                break;
        }

        //return super.onOptionsItemSelected(item);
        //inte.putExtra("IdInformeTecnico", IdInformeTecnico);
        return true;
    }


}
