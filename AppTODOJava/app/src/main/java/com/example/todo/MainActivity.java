package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

import BBDD.Clases.UserLogin;
import BBDD.Helper.BBDDHelper;
import ListaActividades.ListadoActividades;

public class MainActivity extends AppCompatActivity {



    Button inciarSesion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titulo = findViewById(R.id.Titulo);

        EditText usuario = findViewById(R.id.User);
        EditText pass = findViewById(R.id.Pass);

        inciarSesion = findViewById(R.id.incSesion);



        usuario.setText("Pepito");
        pass.setText("Pepito");


        TextPaint paint = titulo.getPaint();
        float width = paint.measureText("TO DO");
        Shader shadersito = new LinearGradient(0,0,width,titulo.getLineHeight(),new int[]{

                Color.parseColor("#F57C00"),
                Color.parseColor("#EF6C00"),
                Color.parseColor("#FFEB3B"),
                Color.parseColor("#FFEE58"),

        },null,Shader.TileMode.REPEAT);


        titulo.getPaint().setShader(shadersito);


        creacionCuenta();


        inciarSesion.setOnClickListener(v->{
            Logado(usuario.getText().toString(), pass.getText().toString());
        });


    }


    public void creacionCuenta(){

        Button crearCuenta = findViewById(R.id.crCuenta);
        crearCuenta.setOnClickListener(v->{
            Intent movCreacion = new Intent(this, CreacionUsuario.class);


            startActivity(movCreacion);
        });
    }


    public void Logado(String usu, String pass){
        UserLogin login = null;

        final BBDDHelper helper = new BBDDHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();


            try (Cursor cursor = db.rawQuery("SELECT ID,  Cuenta, Password FROM CUENTAS WHERE Cuenta= '" +  usu  + "' AND Password= '"+ pass+ "'", null)) {
                if(cursor.moveToFirst()){
                    do{
                        login = new UserLogin();
                        login.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                        login.setCuenta(cursor.getString(cursor.getColumnIndex("Cuenta")));
                        login.setPassword(cursor.getString(cursor.getColumnIndex("Password")));

                    }while(cursor.moveToNext());


                }

            }catch (Exception e){
                e.printStackTrace();
            }

//            if(usu.equals(usuario) && pass.equals(password)){
            if (login != null && login.getCuenta().equals(usu) && login.getPassword().equals(pass)) {
                GlobalVariables.login = login;
                Intent listaTareas = new Intent(this, ListadoActividades.class);
                startActivity(listaTareas);
                this.finish();
            }else{
                Toast.makeText(getApplicationContext(), "Usuario no valido", Toast.LENGTH_LONG).show();
            }


            //GlobalVariables guardadoId = new GlobalVariables();

            //guardadoId.setIdUsuario(id);



    }

}