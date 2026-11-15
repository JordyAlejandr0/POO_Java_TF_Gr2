package Modelo;

import java.io.Serializable;

/**
 * Empleado de la organización.
 *
 * Un empleado puede cumplir dos papeles dentro de un proyecto:
 * ser el gerente, o ser uno de los integrantes del equipo.
 */
public class Empleado implements Serializable {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    private String numeroIdentificacion;
    private String nombreCompleto;
    private String tituloProfesional;
    private byte aniosExperiencia;

    public Empleado(String numeroIdentificacion, String nombreCompleto,
            String tituloProfesional, byte aniosExperiencia) {
        setNumeroIdentificacion(numeroIdentificacion);
        setNombreCompleto(nombreCompleto);
        setTituloProfesional(tituloProfesional);
        setAniosExperiencia(aniosExperiencia);
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        if (numeroIdentificacion == null
                || numeroIdentificacion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La identificación del empleado es obligatoria.");
        }
        this.numeroIdentificacion = numeroIdentificacion.trim();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del empleado es obligatorio.");
        }
        this.nombreCompleto = nombreCompleto.trim();
    }

    public String getTituloProfesional() {
        return tituloProfesional;
    }

    public void setTituloProfesional(String tituloProfesional) {
        if (tituloProfesional == null || tituloProfesional.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El título profesional del empleado es obligatorio.");
        }
        this.tituloProfesional = tituloProfesional.trim();
    }

    public byte getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(byte aniosExperiencia) {
        if (aniosExperiencia < 0 || aniosExperiencia > 70) {
            throw new IllegalArgumentException(
                    "Los años de experiencia deben estar entre 0 y 70.");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return "Identificación: " + numeroIdentificacion
                + " | Nombre: " + nombreCompleto
                + " | Título: " + tituloProfesional
                + " | Años de experiencia: " + aniosExperiencia;
    }
}
