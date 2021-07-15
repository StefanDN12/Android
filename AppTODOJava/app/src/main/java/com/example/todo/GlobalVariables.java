package com.example.todo;

import java.util.ArrayList;
import java.util.List;

import BBDD.Clases.UserLogin;

public class GlobalVariables {


    private static int IdOpcionespuldado;
    private static int IdBorradopulsado;

    private static String NombreTarea;

    private static String miniTareas;


    private static int IdUsuario;

    public static UserLogin login;

    public static int getIdUsuario() {
        return IdUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }


    public static int getIdOpcionespuldado() {
        return IdOpcionespuldado;
    }

    public static void setIdOpcionespuldado(int idOpcionespuldado) {
        IdOpcionespuldado = idOpcionespuldado;
    }

    public static int getIdBorradopulsado() {
        return IdBorradopulsado;
    }

    public static void setIdBorradopulsado(int setIdBorradopulsado) {
        GlobalVariables.IdBorradopulsado = setIdBorradopulsado;
    }

    public static String getNombreTarea() {
        return NombreTarea;
    }

    public static void setNombreTarea(String nombreTarea) {
        NombreTarea = nombreTarea;
    }

    public static String getMiniTareas() {
        return miniTareas;
    }

    public static void setMiniTareas(String miniTareas) {

        GlobalVariables.miniTareas = miniTareas;
    }
}
