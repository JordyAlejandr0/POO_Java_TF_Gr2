package Modelo;

/**
 * Cliente de tipo persona natural.
 *
 * Es final porque de ella no hereda ninguna otra clase.
 * Hereda de Cliente el número de identificación, el nombre y la ciudad,
 * y agrega sus datos de contacto propios.
 */
public final class PersonaNatural extends Cliente {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    private String numeroTelefono;
    private String correoElectronico;

    public PersonaNatural(String numeroIdentificacion, String nombre,
            String nombreCiudad, String numeroTelefono,
            String correoElectronico) {
        super(numeroIdentificacion, nombre, nombreCiudad);
        setNumeroTelefono(numeroTelefono);
        setCorreoElectronico(correoElectronico);
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
        return "CLIENTE PERSONA NATURAL\n" + super.toString()
                + "\nTeléfono: " + numeroTelefono
                + "\nCorreo: " + correoElectronico;
    }
}
