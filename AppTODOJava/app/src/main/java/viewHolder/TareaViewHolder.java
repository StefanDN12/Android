package viewHolder;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.GlobalVariables;
import com.example.todo.InfoTarea;
import com.example.todo.R;

import BBDD.Clases.Tareas;
import BBDD.Helper.BBDDHelperTarea;
import Interfaces.BotonesInterfaz;
import ListaActividades.ListadoActividades;


public class TareaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, BotonesInterfaz {

    private TextView txtNombre;
    private Button buttonOpciones;
    private Button buttonBorrar;
    GlobalVariables btnRaya = new GlobalVariables();
    private Tareas tarea;
    private Intent intensito;
    private GlobalVariables tit;

    public TareaViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombre           =   itemView.findViewById(R.id.tareita);
            buttonOpciones      =   itemView.findViewById(R.id.opcionesActividad);
            buttonBorrar        =   itemView.findViewById(R.id.Brr);

        }

        public void bind(Tareas tareas){
            Context context = itemView.getContext();
            tarea = tareas;
            txtNombre.setText(tareas.getNombre());

            buttonOpciones.setOnClickListener(this);
            buttonBorrar.setOnClickListener(this);
        }

        public static TareaViewHolder create(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycledviewbtn, parent,false);
            return new TareaViewHolder(view);
        }

    @Override
    public void onClick(View v) {

       if(v.getId() == buttonOpciones.getId()){
            botonRayas(tarea.getID());

           Log.d("TODO",String.valueOf(tarea.getID()) + tarea.getNombre() + tarea.getMinitareas() );

           btnRaya.setNombreTarea(tarea.getNombre());
           btnRaya.setMiniTareas(tarea.getMinitareas());


            intensito = new Intent(v.getContext(), InfoTarea.class);

           v.getContext().startActivities(new Intent[]{intensito});

        }else{
            botonCruz(tarea.getID());
           final BBDDHelperTarea helper = new BBDDHelperTarea(v.getContext());
           SQLiteDatabase db = helper.getReadableDatabase();

           db.execSQL("DELETE FROM TAREAS WHERE ID = "+tarea.getID());
           intensito = new Intent(v.getContext(), ListadoActividades.class);

           v.getContext().startActivities(new Intent[]{intensito});

        }
    }

    @Override
    public void botonRayas(int id) {
        btnRaya.setIdOpcionespuldado(id);
    }

    @Override
    public void botonCruz(int id) {

        btnRaya.setIdBorradopulsado(id);

    }
}
