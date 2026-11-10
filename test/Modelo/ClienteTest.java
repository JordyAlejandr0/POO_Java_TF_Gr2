package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Pruebas unitarias de la jerarquía Cliente.
 *
 * Verifican la generalización y el polimorfismo: las dos subclases son
 * tratables como Cliente y cada una sobreescribe el toString() del padre.
 */
public class ClienteTest {

    @Test
    public void unaPersonaNaturalEsUnCliente() {
        Cliente objCliente = new PersonaNatural("1144012345", "Andrey Valencia",
                "Cali", "3001234567", "andrey@correo.com");
        assertTrue(objCliente instanceof Cliente);
        assertTrue(objCliente instanceof PersonaNatural);
    }

    @Test
    public void unaEmpresaEsUnCliente() {
        Contacto objContacto = new Contacto("Luz Adriana Ramos", "Gerente",
                "3012223344", "luz@empresa.com");
        Cliente objCliente = new Empresa("900123456", "Vivir la Tecnología",
                "Cali", objContacto);
        assertTrue(objCliente instanceof Cliente);
        assertTrue(objCliente instanceof Empresa);
    }

    @Test
    public void laPersonaNaturalHeredaLosAtributosDelPadre() {
        PersonaNatural objCliente = new PersonaNatural("1144012345",
                "Andrey Valencia", "Cali", "3001234567", "andrey@correo.com");
        assertEquals("1144012345", objCliente.getNumeroIdentificacion());
        assertEquals("Andrey Valencia", objCliente.getNombre());
        assertEquals("Cali", objCliente.getNombreCiudad());
    }

    @Test
    public void laEmpresaGuardaSuContacto() {
        Contacto objContacto = new Contacto("Luz Adriana Ramos", "Gerente",
                "3012223344", "luz@empresa.com");
        Empresa objEmpresa = new Empresa("900123456", "Vivir la Tecnología",
                "Cali", objContacto);
        assertEquals("Luz Adriana Ramos",
                objEmpresa.getSuContacto().getNombreCompleto());
        assertEquals("Gerente", objEmpresa.getSuContacto().getCargo());
    }

    @Test
    public void cadaSubclaseSobreescribeElToString() {
        Cliente personaNatural = new PersonaNatural("1144012345",
                "Andrey Valencia", "Cali", "3001234567", "andrey@correo.com");
        Cliente empresa = new Empresa("900123456", "Vivir la Tecnología", "Cali",
                new Contacto("Luz Adriana Ramos", "Gerente", "3012223344",
                        "luz@empresa.com"));

        assertTrue(personaNatural.toString().startsWith("CLIENTE PERSONA NATURAL"));
        assertTrue(empresa.toString().startsWith("CLIENTE EMPRESA"));
    }
}
