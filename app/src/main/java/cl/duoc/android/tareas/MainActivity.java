package cl.duoc.android.tareas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

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
        final TareasDbHelper tareasDbHelper = new TareasDbHelper(this);
        listaTareas = tareasDbHelper.getTareas();
        final ListView lvListaTareas = (ListView) findViewById(R.id.lvListaTareas);
        //ArrayAdapter<Tarea> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, listaTareas);
        final CustomAdapter adapter = new CustomAdapter(this, listaTareas);
        lvListaTareas.setAdapter(adapter);
        lvListaTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, final long id) {
                new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirmación eliminación")
                        .setMessage("Realmente desea eliminar esta tarea?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton("NO", null)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.v("Posicion", position+"");
                                Log.v("ID:", id+"");
                                boolean resultado = tareasDbHelper.eliminarTarea(id);
                                if(resultado) {
                                    listaTareas.remove(position);
                                    lvListaTareas.invalidateViews();
                                    Toast.makeText(MainActivity.this, "Tarea eliminada correctamente", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Hubo problemas al eliminar la Tarea", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    public void irAgregarTarea(View view) {
        startActivity(new Intent(this, CrearTareaActivity.class));
    }

}
