package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Pruebas unitarias de la clase Empleado.
 */
public class EmpleadoTest {

    @Test
    public void elEmpleadoGuardaSusCuatroDatos() {
        Empleado objEmpleado = new Empleado("1144099999", "Luz Adriana Ramos",
                "Ingeniera de Sistemas", (byte) 8);
        assertEquals("1144099999", objEmpleado.getNumeroIdentificacion());
        assertEquals("Luz Adriana Ramos", objEmpleado.getNombreCompleto());
        assertEquals("Ingeniera de Sistemas", objEmpleado.getTituloProfesional());
        assertEquals(8, objEmpleado.getAniosExperiencia());
    }

    @Test
    public void elToStringMuestraLaInformacionDelEmpleado() {
        Empleado objEmpleado = new Empleado("1144099999", "Luz Adriana Ramos",
                "Ingeniera de Sistemas", (byte) 8);
        assertTrue(objEmpleado.toString().contains("Luz Adriana Ramos"));
        assertTrue(objEmpleado.toString().contains("Ingeniera de Sistemas"));
    }
}
