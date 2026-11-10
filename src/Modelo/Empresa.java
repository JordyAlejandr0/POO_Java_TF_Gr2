package Modelo;

/**
 * Cliente de tipo empresa.
 *
 * Es final porque de ella no hereda ninguna otra clase.
 * Contiene un objeto Contacto, que es la persona con la que se
 * establece la comunicación en esa empresa.
 */
public final class Empresa extends Cliente {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    private Contacto suContacto;

    public Empresa(String numeroIdentificacion, String nombre,
            String nombreCiudad, Contacto suContacto) {
        super(numeroIdentificacion, nombre, nombreCiudad);
        setSuContacto(suContacto);
    }

    public Contacto getSuContacto() {
        return suContacto;
    }

    public void setSuContacto(Contacto suContacto) {
        this.suContacto = suContacto;
    }

    @Override
    public String toString() {
        return "CLIENTE EMPRESA\n" + super.toString()
                + "\n" + suContacto;
    }
}
