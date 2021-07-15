package BBDD.Clases;

import java.util.ArrayList;

public class Tareas {

    private int ID;
    private String Nombre;
    private String Minitareas;
    private int IdUsuario;

    public Tareas() {
    }

    public  Tareas(int ID, String nombre, String minitareas, int idUsuario) {
        this.ID = ID;
        Nombre = nombre;
        Minitareas = minitareas;
        IdUsuario = idUsuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMinitareas() {
        return Minitareas;
    }

    public void setMinitareas(String minitareas) {
        Minitareas = minitareas;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
}
