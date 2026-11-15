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

    // ---------- Validaciones de los setters ----------

    @Test(expected = IllegalArgumentException.class)
    public void laIdentificacionVaciaSeRechaza() {
        new Empleado("   ", "Luz Adriana Ramos", "Ingeniera", (byte) 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void elNombreVacioSeRechaza() {
        new Empleado("1144099999", "", "Ingeniera", (byte) 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void elTituloProfesionalVacioSeRechaza() {
        new Empleado("1144099999", "Luz Adriana Ramos", "   ", (byte) 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void losAniosDeExperienciaNegativosSeRechazan() {
        new Empleado("1144099999", "Luz Adriana Ramos", "Ingeniera", (byte) -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void masDeSetentaAniosDeExperienciaSeRechazan() {
        new Empleado("1144099999", "Luz Adriana Ramos", "Ingeniera", (byte) 71);
    }

    @Test
    public void ceroAniosDeExperienciaSeAcepta() {
        Empleado objEmpleado = new Empleado("1144099999", "Luz Adriana Ramos",
                "Ingeniera", (byte) 0);
        assertEquals(0, objEmpleado.getAniosExperiencia());
    }

    @Test
    public void losEspaciosSobrantesSeRecortan() {
        Empleado objEmpleado = new Empleado("  1144099999  ",
                "  Luz Adriana Ramos  ", "  Ingeniera  ", (byte) 8);
        assertEquals("1144099999", objEmpleado.getNumeroIdentificacion());
        assertEquals("Luz Adriana Ramos", objEmpleado.getNombreCompleto());
        assertEquals("Ingeniera", objEmpleado.getTituloProfesional());
    }
}
