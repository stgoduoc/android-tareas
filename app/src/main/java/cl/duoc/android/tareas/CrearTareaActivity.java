package cl.duoc.android.tareas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cl.duoc.android.tareas.db.TareasDbHelper;

public class CrearTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);
    }

    public void guardarTarea(View view) {
        // recupera componentes
        EditText etTarea    = (EditText) findViewById(R.id.etTarea);
        Spinner spPrioridad = (Spinner) findViewById(R.id.spPrioridad);

        // extrae valores del formulario
        String tarea = etTarea.getText().toString();
        String stringPrioridad = spPrioridad.getSelectedItem().toString();


        // inserta en la BD
        TareasDbHelper tareasDbHelper = new TareasDbHelper(this);
        tareasDbHelper.crearTarea(tarea, stringPrioridad);

        // mostrar mensaje
        Toast.makeText(this, "Tarea guardada", Toast.LENGTH_LONG).show();
    }
}
