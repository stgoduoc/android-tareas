package cl.duoc.android.tareas.entities;

import java.util.Calendar;

public class Tarea {

    private Long id;
    private String tarea;
    private int prioridad;
    private Calendar fechaCreacion;

    public Tarea() {
    }

    public Tarea(Long id, String tarea, int prioridad, Calendar fechaCreacion) {
        this.id = id;
        this.tarea = tarea;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
    }

    public Tarea(String tarea, int prioridad) {
        this.tarea = tarea;
        this.prioridad = prioridad;
    }

    // métodos

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

    // overwrite
    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", tarea='" + tarea + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}
