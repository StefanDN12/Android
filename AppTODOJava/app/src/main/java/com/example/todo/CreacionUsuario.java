package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

import BBDD.EstructuraBBDD;
import BBDD.Helper.BBDDHelper;

import static java.security.AccessController.getContext;

public class CreacionUsuario extends AppCompatActivity {

    private int idSeleccionado = 0;
    private int comprobadorDeId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_usuario);

        EditText idUsuario = findViewById(R.id.idPersona);

        EditText usuPers = findViewById(R.id.usPersona);

        EditText pass = findViewById(R.id.passPersona);

        Button creacion = (Button) findViewById(R.id.creacionCuenta);

        final BBDDHelper helper = new BBDDHelper(this);

        TextView ultimoId = (TextView)findViewById(R.id.ultimoId);


        SQLiteDatabase db = helper.getReadableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT ID FROM CUENTAS", null)) {
            if(cursor.moveToFirst()){
                do{
                    idSeleccionado = cursor.getInt(cursor.getColumnIndex("ID"));
                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ultimoId.setText("Ultimo id asignado: " + String.valueOf(idSeleccionado));


        creacion.setOnClickListener(v ->{
            String usuario = "";
            String password = "";
            // Gets the data repository in write mode

                // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(EstructuraBBDD.COLUMNA1, idUsuario.getText().toString());
            values.put(EstructuraBBDD.COLUMNA2, usuPers.getText().toString());
            values.put(EstructuraBBDD.COLUMNA3, pass.getText().toString());

            // Insert the new row, returning the primary key value of the new row


            try (Cursor cursor = db.rawQuery("SELECT ID FROM CUENTAS WHERE ID ='"+idUsuario.getText().toString()+"'", null)) {
                if(cursor.moveToFirst()){
                    do{
                        comprobadorDeId = cursor.getInt(cursor.getColumnIndex("ID"));
                    }while(cursor.moveToNext());
                }

                Log.d("resultado", String.valueOf(comprobadorDeId));
            }catch (Exception e){
                e.printStackTrace();
            }

            if (String.valueOf(comprobadorDeId).equals(idUsuario.getText().toString())){
                Toast.makeText(getApplicationContext(), "El usuario con ese id ya existe", Toast.LENGTH_LONG).show();
            }else{

                try (Cursor cursor = db.rawQuery("SELECT Cuenta, Password FROM CUENTAS WHERE Cuenta= '" +  usuPers.getText().toString()  + "' AND Password= '"+ pass.getText().toString()+ "'", null)) {
                    if(cursor.moveToFirst()){
                        do{
                            usuario = cursor.getString(cursor.getColumnIndex("Cuenta"));
                            password = cursor.getString(cursor.getColumnIndex("Password"));
                        }while(cursor.moveToNext());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                if(usuPers.getText().toString().equals(usuario) && pass.getText().toString().equals(password)){
                    Toast.makeText(getApplicationContext(), "El usuario con ese nombre y pass ya exita", Toast.LENGTH_LONG).show();
                }else {

                    if(validacionCuenta(usuPers.getText().toString()) && validacionPass(pass.getText().toString())){
                        long newRowId = db.insert(EstructuraBBDD.TABLE_NAME, null, values);
                        Toast.makeText(getApplicationContext(), "Se guardó el registro con clave: " + newRowId, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Introduce caracteres validos", Toast.LENGTH_LONG).show();
                    }
                }
            }
            idUsuario.setText("");
            usuPers.setText("");
            pass.setText("");
            try (Cursor cursor = db.rawQuery("SELECT ID FROM CUENTAS", null)) {
                if(cursor.moveToFirst()){
                    do{
                        idSeleccionado = cursor.getInt(cursor.getColumnIndex("ID"));
                    }while(cursor.moveToNext());
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        });
        volver();
    }


    public static boolean validacionCuenta (String str){
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }


    public static boolean validacionPass(String pass){
        String expression = "^[a-zA-Z\\s]+";
        return pass.matches(expression);
    }

    public void volver(){
        Button btnVolver = findViewById(R.id.bntVolver);

        btnVolver.setOnClickListener(v->{
                onBackPressed();
        });
    }




}