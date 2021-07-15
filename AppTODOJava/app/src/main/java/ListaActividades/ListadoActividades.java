package ListaActividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo.GlobalVariables;
import com.example.todo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.TareaAdapter;
import BBDD.Clases.Tareas;
import BBDD.Clases.UserLogin;
import BBDD.Helper.BBDDHelperTarea;
import CreacionTarea.AnadirTarea;

public class ListadoActividades extends AppCompatActivity {


    TareaAdapter adp;
    private Button btnBorrar;
    final BBDDHelperTarea helper = new BBDDHelperTarea(this);
    private GlobalVariables tit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_actividades);
        TextView titulo = findViewById(R.id.titulo);



        adp = new TareaAdapter(new TareaAdapter.tareaDiff());
        RecyclerView rcView = findViewById(R.id.recyclerView);


        rcView.setAdapter(adp);
        rcView.setLayoutManager(new LinearLayoutManager(this));


        TextPaint paint = titulo.getPaint();
        float width = paint.measureText("TO DO");
        Shader shadersito = new LinearGradient(0,0,width,titulo.getLineHeight(),new int[]{

                Color.parseColor("#F57C00"),
                Color.parseColor("#EF6C00"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FFEE58"),

        },null,Shader.TileMode.REPEAT);


        titulo.getPaint().setShader(shadersito);
        addTarea();

        //adp.submitList(listadoObtenido());

        listadoObtenido();
    }



    public void addTarea(){
        Button anad = findViewById(R.id.add);

        anad.setOnClickListener(v->{
            Intent vistaAnadir = new Intent(getApplicationContext(), AnadirTarea.class);

            startActivity(vistaAnadir);
        });

    }



    public ArrayList<Tareas> listadoObtenido(){

        ArrayList<Tareas> tareas2 = new ArrayList<>();
        ArrayList<List<String>> datosTareas = new ArrayList<List<String>>();

        String[] tareas;

        final BBDDHelperTarea helper = new BBDDHelperTarea(this);

        SQLiteDatabase db = helper.getReadableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT ID, Nombre, Minitareas FROM TAREAS WHERE IdUsuario ="+ UserLogin.getID(), null)) {


                while (cursor.moveToNext()) {

                    Tareas tarea = new Tareas();
                    tarea.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                    tarea.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                    tarea.setMinitareas(cursor.getString(cursor.getColumnIndex("Minitareas")));
                    tareas2.add(tarea);

                    tareas = new String[]{cursor.getString(cursor.getColumnIndex("ID")),
                            cursor.getString(cursor.getColumnIndex("Nombre")),
                    };

                    datosTareas.add(Arrays.asList(tareas));

                    /*for (int i = 0;i <= cursor.getColumnCount(); i++) {
                        datosTareas.get(i).add(cursor.getString(cursor.getColumnIndex("ID")));
                        datosTareas.get(i).add(cursor.getString(cursor.getColumnIndex("Nombre")));
                        datosTareas.get(i).add(cursor.getString(cursor.getColumnIndex("Minitareas")));
                        datosTareas.get(i).add(cursor.getString(cursor.getColumnIndex("IdUsuario")));
                    }*/
                }
        }catch (Exception e){
            e.printStackTrace();
        }

        for(Tareas tarea : tareas2) {
            Log.d("ARRAY2", "" + tarea.getID() +", " + tarea.getNombre() + ", " + tarea.getMinitareas());
        }


        adp.submitList(tareas2);
        adp.notifyDataSetChanged();




//        GlobalVariables dtAct = new GlobalVariables();


//        dtAct.setDatosTareas(datosTareas);

        return tareas2;



        //return datosTareas;
    }


}