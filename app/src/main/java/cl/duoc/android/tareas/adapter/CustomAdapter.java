package cl.duoc.android.tareas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cl.duoc.android.tareas.R;
import cl.duoc.android.tareas.entities.Tarea;

/**
 * Created by zero on 10/10/17.
 */

public class CustomAdapter extends BaseAdapter {

    private List<Tarea> listaTareas;
    private Context context;

    public CustomAdapter(Context context, List<Tarea> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @Override
    public int getCount() {
        return listaTareas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaTareas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_item, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(R.id.texto1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.texto2);
        TextView textView3 = (TextView) convertView.findViewById(R.id.texto3);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagen);

        Tarea tarea = (Tarea) getItem(position);

        textView1.setText(tarea.getTarea());
        String prioridad = "";
        switch (tarea.getPrioridad()) {
            case 2:
                prioridad = "MEDIA";
                break;
            case 3:
                prioridad = "ALTA";
                break;
            default:
                prioridad = "BAJA";
        }
        textView2.setText(prioridad);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        textView3.setText(simpleDateFormat.format(tarea.getFechaCreacion().getTime()));

        imageView.setImageBitmap(tarea.getImagen());
        return convertView;
    }
}
