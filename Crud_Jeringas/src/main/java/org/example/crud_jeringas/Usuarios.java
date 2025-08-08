
package org.example.crud;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String numeroTelefonico;
    private String direccion;

    public Usuario(int id, String nombre, String correo, String numeroTelefonico, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.numeroTelefonico = numeroTelefonico;
        this.direccion = direccion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}