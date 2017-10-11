package cl.duoc.android.tareas.entities;

import android.graphics.Bitmap;

import java.util.Calendar;

public class Tarea {

    private Long id;
    private String tarea;
    private int prioridad;
    private Calendar fechaCreacion = Calendar.getInstance();
    private Bitmap imagen;
    private Double latitud;
    private Double longitud;

    public Tarea() {
    }

    public Tarea(Long id, String tarea, int prioridad, Calendar fechaCreacion, Bitmap imagen, Double latitud, Double longitud) {
        this.id = id;
        this.tarea = tarea;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Tarea(String tarea, int prioridad) {
        this.tarea = tarea;
        this.prioridad = prioridad;
    }

    // métodos setters y getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", tarea='" + tarea + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}
