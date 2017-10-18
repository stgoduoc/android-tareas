package cl.duoc.android.tareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.duoc.android.tareas.entities.Tarea;

/**
 * Created by zero on 19/09/17.
 */

public class TareasDbHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME          = "tareas";
    public static final String COLUMN_ID           = "id";
    public static final String COLUMN_TAREA        = "tarea";
    public static final String COLUMN_PRIORIDAD    = "prioridad";
    public static final String COLUMN_REALIZADA    = "realizada";
    public static final String COLUMN_IMAGEN       = "imagen";
    public static final String COLUMN_LATITUD      = "latitud";
    public static final String COLUMN_LONGITUD     = "longitud";
    public static final String COLUMN_FECHA_CREACION = "fecha_creacion";

    private static final String DATABASE_NAME = "tareas.db";
    private static final int DATABASE_VERSION = 1;

    public TareasDbHelper(Context context){
        // guarda BD en espacio privado
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //guardado en External Storage
        //super(context, Environment.getExternalStorageDirectory()+ File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean eliminarTarea(Tarea tarea) {
        return eliminarTarea(tarea.getId());
    }

    public boolean eliminarTarea(Long tareaId) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        int eliminados = writableDatabase.delete(TABLE_NAME, "id = ?", new String[]{tareaId + ""});
        return eliminados>0?true:false;
    }

    public void crearTarea(Tarea tarea) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TAREA, tarea.getTarea());
        contentValues.put(COLUMN_PRIORIDAD, tarea.getPrioridad());
        contentValues.put(COLUMN_REALIZADA, tarea.getPrioridad());
        contentValues.put(COLUMN_FECHA_CREACION, tarea.getFechaCreacion().getTimeInMillis());

        // image blob
        if(tarea.getImagen() != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            tarea.getImagen().compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            contentValues.put(COLUMN_IMAGEN, imageBytes);
        }


        // guarda en BD
        writableDatabase.insert(TareasDbHelper.TABLE_NAME, null, contentValues);

    }

    public List<Tarea> getTareas(){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sql = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s"
                                , COLUMN_ID
                                , COLUMN_TAREA
                                , COLUMN_PRIORIDAD
                                , COLUMN_REALIZADA
                                , COLUMN_FECHA_CREACION
                                , COLUMN_IMAGEN
                                , COLUMN_LATITUD
                                , COLUMN_LONGITUD
                                , TABLE_NAME
        );
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        List<Tarea> lista = new ArrayList<>();
        while(cursor.moveToNext()) {
            Calendar fechaCreacion = Calendar.getInstance();
            fechaCreacion.setTimeInMillis(cursor.getLong(3));
            boolean realizada = cursor.getInt(4)>0?true:false;
            Bitmap bitmap = null;
            byte[] imageBlob = cursor.getBlob(5);
            if(imageBlob != null && imageBlob.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(imageBlob, 0, imageBlob.length);
            }
            Tarea tarea = new Tarea(cursor.getLong(0), cursor.getString(1), cursor.getInt(2)
                                , fechaCreacion, realizada, bitmap, null, null);
            lista.add(tarea);
        }
        return lista;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_TAREA + " TEXT NOT NULL , " +
                COLUMN_PRIORIDAD + " INTEGER DEFAULT 1 ," +
                COLUMN_REALIZADA + " BOOLEAN DEFAULT 0 ," +
                COLUMN_LATITUD + " REAL," +
                COLUMN_LONGITUD + " REAL ," +
                COLUMN_IMAGEN + " BLOB ," +
                COLUMN_FECHA_CREACION + " BIGINT UNSIGNED" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
