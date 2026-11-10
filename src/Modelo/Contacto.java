package Modelo;

import java.io.Serializable;

/**
 * Persona de contacto de un cliente de tipo Empresa.
 *
 * Implementa Serializable porque los objetos de esta clase viajan dentro
 * de un objeto Empresa cuando se genera el archivo de objetos de clientes.
 */
public class Contacto implements Serializable {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    private String nombreCompleto;
    private String cargo;
    private String numeroTelefono;
    private String correoElectronico;

    public Contacto(String nombreCompleto, String cargo,
            String numeroTelefono, String correoElectronico) {
        setNombreCompleto(nombreCompleto);
        setCargo(cargo);
        setNumeroTelefono(numeroTelefono);
        setCorreoElectronico(correoElectronico);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Contacto: " + nombreCompleto
                + " | Cargo: " + cargo
                + " | Teléfono: " + numeroTelefono
                + " | Correo: " + correoElectronico;
    }
}
