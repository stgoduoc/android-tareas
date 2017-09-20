package cl.duoc.android.tareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String COLUMN_FECHA_CREACION = "fecha_creacion";

    private static final String DATABASE_NAME = "tareas.db";
    private static final int DATABASE_VERSION = 1;

    public TareasDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void crearTarea(String tarea, String stringPrioridad) {
        int prioridad = 0;
        switch (stringPrioridad) {
            case "Alta":
                prioridad = 3;
                break;
            case "Media":
                prioridad = 2;
                break;
            case "Baja":
            default:
                prioridad = 1;
        }

        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TAREA, tarea);
        contentValues.put(COLUMN_PRIORIDAD, prioridad);
        writableDatabase.insert(TareasDbHelper.TABLE_NAME, null, contentValues);

    }

    public List<Tarea> getTareas(){
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String sql = String.format("SELECT %s, %s, %s, %s FROM %s"
                                , COLUMN_ID
                                , COLUMN_TAREA
                                , COLUMN_PRIORIDAD
                                , COLUMN_FECHA_CREACION
                                , TABLE_NAME
        );
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        List<Tarea> lista = new ArrayList<>();
        while(cursor.moveToNext()) {
            Tarea tarea = new Tarea(cursor.getLong(0), cursor.getString(1), cursor.getInt(2)
                                , Calendar.getInstance());
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
                COLUMN_FECHA_CREACION + " DEFAULT CURRENT_TIMESTAMP" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
