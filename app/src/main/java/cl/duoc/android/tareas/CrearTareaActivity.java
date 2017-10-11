package cl.duoc.android.tareas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import cl.duoc.android.tareas.db.TareasDbHelper;
import cl.duoc.android.tareas.entities.Tarea;

public class CrearTareaActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private Bitmap bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);
        imageView = (ImageView) findViewById(R.id.fotoCamara);
    }

    public void guardarTarea(View view) {
        // recupera componentes
        EditText etTarea = (EditText) findViewById(R.id.etTarea);
        Spinner spPrioridad = (Spinner) findViewById(R.id.spPrioridad);

        // extrae valores del formulario
        String tarea = etTarea.getText().toString();
        String stringPrioridad = spPrioridad.getSelectedItem().toString();
        int prioridad = 0;
        switch (stringPrioridad.toUpperCase()) {
            case "ALTA":
                prioridad = 3;
                break;
            case "MEDIA":
                prioridad = 2;
                break;
            case "BAJA":
            default:
                prioridad = 1;
        }

        // inserta en la BD
        TareasDbHelper tareasDbHelper = new TareasDbHelper(this);
        Calendar calendar = Calendar.getInstance();
        tareasDbHelper.crearTarea(new Tarea(null, tarea, prioridad, calendar, bitmap, null, null));

        // mostrar mensaje
        Toast.makeText(this, "Tarea guardada", Toast.LENGTH_LONG).show();
    }

    public void capturarFotografia(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

}
