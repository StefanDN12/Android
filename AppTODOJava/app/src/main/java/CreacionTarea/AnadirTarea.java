package CreacionTarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo.CreacionUsuario;
import com.example.todo.GlobalVariables;
import com.example.todo.R;

import java.sql.Array;

import BBDD.Clases.UserLogin;
import BBDD.EstructuraBBDDTareas;
import BBDD.Helper.BBDDHelper;
import BBDD.Helper.BBDDHelperTarea;
import ListaActividades.ListadoActividades;

public class AnadirTarea extends AppCompatActivity {

    private int id = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_tarea);
        Button btnAnadirTarea = findViewById(R.id.btnAnadir);


        btnAnadirTarea.setOnClickListener(v-> {

            introducirTareas();

        });

    }


    public boolean comprobarDB(String database_path){
        SQLiteDatabase checkDB = null;

        try {
            checkDB = SQLiteDatabase.openDatabase(database_path, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        }catch (SQLiteException e){
            Log.d("Error", "No exite la bbdd" + e.getMessage());
        }

        return checkDB != null;
    }


    public int comprobarID(){

        boolean existe = comprobarDB("/data/data/com.example.todo/databases/Tareas.db");
        int idSeleccionado = 1;


        if(existe == false){
            return idSeleccionado;
        }else{
            final BBDDHelperTarea helper = new BBDDHelperTarea(this);
            SQLiteDatabase db = helper.getReadableDatabase();


            try (Cursor cursor = db.rawQuery("SELECT ID FROM TAREAS", null)) {
                if(cursor.moveToFirst()){
                    do{
                        idSeleccionado = cursor.getInt(cursor.getColumnIndex("ID"));
                    }while(cursor.moveToNext());
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return idSeleccionado + 1;
        }





    }

    public void introducirTareas(){



        EditText nombreTarea = findViewById(R.id.tituloTarea);

        EditText miniTareas = findViewById(R.id.miniTareas);

        final BBDDHelperTarea helper = new BBDDHelperTarea(this);




        int idAnadido = comprobarID();

        SQLiteDatabase db = helper.getWritableDatabase();



            String textoComa = miniTareas.getText().toString().replace("\n", ",");

            ContentValues values = new ContentValues();

            if(idAnadido == 1){
                values.put(EstructuraBBDDTareas.COLUMNA1, idAnadido);
            }else{
                values.put(EstructuraBBDDTareas.COLUMNA1, idAnadido++);
            }




            if(validacionCuenta(nombreTarea.getText().toString())){
                values.put(EstructuraBBDDTareas.COLUMNA2, nombreTarea.getText().toString());
            }else{
                Toast.makeText(this,"Introduce caracteres validos", Toast.LENGTH_SHORT).show();
            }

            values.put(EstructuraBBDDTareas.COLUMNA3,textoComa);

        UserLogin usu = new UserLogin();

            values.put(EstructuraBBDDTareas.COLUMNA4, usu.getID());

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(EstructuraBBDDTareas.TABLE_NAME, null, values);
            Toast.makeText(getApplicationContext(), "Se guardado la tarea",  Toast.LENGTH_LONG).show();

            nombreTarea.setText("");
            miniTareas.setText("");

        Intent listado = new Intent(this, ListadoActividades.class);


        startActivity(listado);

    }

    public static boolean validacionCuenta (String str){
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }


}