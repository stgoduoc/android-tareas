package cl.duoc.android.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import cl.duoc.android.tareas.adapter.CustomAdapter;
import cl.duoc.android.tareas.db.TareasDbHelper;
import cl.duoc.android.tareas.entities.Tarea;

public class MainActivity extends AppCompatActivity {

    List<Tarea> listaTareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cargarTareas();
    }

    private void cargarTareas(){
        TareasDbHelper tareasDbHelper = new TareasDbHelper(this);
        listaTareas = tareasDbHelper.getTareas();
        ListView lvListaTareas = (ListView) findViewById(R.id.lvListaTareas);
        //ArrayAdapter<Tarea> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, listaTareas);
        CustomAdapter adapter = new CustomAdapter(this, listaTareas);
        lvListaTareas.setAdapter(adapter);
    }

    public void irAgregarTarea(View view) {
        startActivity(new Intent(this, CrearTareaActivity.class));
    }

}
