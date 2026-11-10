package Modelo;

import java.io.Serializable;

/**
 * Clase abstracta que representa al cliente de un proyecto.
 *
 * Es abstracta porque no existe un cliente genérico: todo cliente es
 * una PersonaNatural o una Empresa.
 *
 * Los atributos son protected (# en el diagrama) para que las subclases
 * puedan utilizarlos directamente.
 */
public abstract class Cliente implements Serializable {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    protected String numeroIdentificacion;
    protected String nombre;
    protected String nombreCiudad;

    public Cliente(String numeroIdentificacion, String nombre,
            String nombreCiudad) {
        setNumeroIdentificacion(numeroIdentificacion);
        setNombre(nombre);
        setNombreCiudad(nombreCiudad);
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @Override
    public String toString() {
        return "Identificación: " + numeroIdentificacion
                + "\nNombre: " + nombre
                + "\nCiudad: " + nombreCiudad;
    }
}
