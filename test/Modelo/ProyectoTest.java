package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias de la clase Proyecto.
 *
 * Verifican las reglas de negocio que exige el enunciado: formato del
 * código, longitud del título, duración válida según la unidad, estado
 * por defecto, cálculo de la fecha de finalización y manejo del equipo.
 */
public class ProyectoTest {

    private Cliente objCliente;
    private Empleado objGerente;
    private String tituloValido;

    @Before
    public void prepararDatos() {
        objCliente = new PersonaNatural("1144012345", "Andrey Valencia",
                "Cali", "3001234567", "andrey@correo.com");
        objGerente = new Empleado("1144099999", "Luz Adriana Ramos",
                "Ingeniera de Sistemas", (byte) 8);
        tituloValido = "Sistema de gestion documental para la empresa";
    }

    private Proyecto crearProyecto(String unidad, byte duracion) {
        return new Proyecto("2026-001", tituloValido, LocalDate.of(2026, 2, 15),
                unidad, duracion, objCliente, objGerente);
    }

    // ---------- Código ----------

    @Test
    public void elCodigoConFormatoValidoSeAcepta() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        assertEquals("2026-001", objProyecto.getCodigo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void elCodigoSinGuionSeRechaza() {
        crearProyecto("meses", (byte) 6).setCodigo("2026001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void elCodigoConConsecutivoDeDosDigitosSeRechaza() {
        crearProyecto("meses", (byte) 6).setCodigo("2026-01");
    }

    // ---------- Título ----------

    @Test(expected = IllegalArgumentException.class)
    public void elTituloDeMenosDeVeinteCaracteresSeRechaza() {
        crearProyecto("meses", (byte) 6).setTitulo("Proyecto corto");
    }

    @Test
    public void elTituloDeExactamenteVeinteCaracteresSeAcepta() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        objProyecto.setTitulo("12345678901234567890");
        assertEquals(20, objProyecto.getTitulo().length());
    }

    // ---------- Duración según la unidad ----------

    @Test
    public void laDuracionDeTresSemanasSeAcepta() {
        assertEquals(3, crearProyecto("semanas", (byte) 3).getDuracionEstimada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void laDuracionDeCuatroSemanasSeRechaza() {
        crearProyecto("semanas", (byte) 4);
    }

    @Test
    public void laDuracionDeOnceMesesSeAcepta() {
        assertEquals(11, crearProyecto("meses", (byte) 11).getDuracionEstimada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void laDuracionDeDoceMesesSeRechazaPorqueEquivaleAUnAnio() {
        crearProyecto("meses", (byte) 12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void laDuracionDeVeinticuatroMesesSeRechaza() {
        crearProyecto("meses", (byte) 24);
    }

    @Test
    public void laDuracionDeTreintaYCincoMesesSeAcepta() {
        assertEquals(35, crearProyecto("meses", (byte) 35).getDuracionEstimada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void laDuracionDeCuatroAniosSeRechaza() {
        crearProyecto("años", (byte) 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unaUnidadDeDuracionDistintaSeRechaza() {
        crearProyecto("dias", (byte) 2);
    }

    // ---------- Estado ----------

    @Test
    public void elEstadoPorDefectoEsIniciado() {
        assertEquals("INICIADO", crearProyecto("meses", (byte) 6).getEstado());
    }

    @Test
    public void elEstadoSeActualizaAEnCurso() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        objProyecto.setEstado("EN CURSO");
        assertEquals("EN CURSO", objProyecto.getEstado());
    }

    @Test(expected = IllegalArgumentException.class)
    public void unEstadoQueNoEstaEnLaListaSeRechaza() {
        crearProyecto("meses", (byte) 6).setEstado("TERMINADO");
    }

    // ---------- Fecha de finalización ----------

    @Test
    public void laFechaDeFinalizacionEnSemanasSumaSemanas() {
        Proyecto objProyecto = crearProyecto("semanas", (byte) 2);
        assertEquals(LocalDate.of(2026, 3, 1),
                objProyecto.determinarFechaFinalizacion());
    }

    @Test
    public void laFechaDeFinalizacionEnMesesSumaMeses() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        assertEquals(LocalDate.of(2026, 8, 15),
                objProyecto.determinarFechaFinalizacion());
    }

    @Test
    public void laFechaDeFinalizacionEnAniosSumaAnios() {
        Proyecto objProyecto = crearProyecto("años", (byte) 2);
        assertEquals(LocalDate.of(2028, 2, 15),
                objProyecto.determinarFechaFinalizacion());
    }

    // ---------- Equipo ----------

    @Test
    public void elProyectoSePuedeRegistrarSinEquipo() {
        assertTrue(crearProyecto("meses", (byte) 6).getSuEquipo().isEmpty());
    }

    @Test
    public void seAgregaUnIntegranteAlEquipo() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        objProyecto.agregarIntegrante(new Empleado("111", "Samuel Vergara",
                "Ingeniero", (byte) 3));
        assertEquals(1, objProyecto.getSuEquipo().size());
    }

    @Test
    public void noSeAgregaDosVecesElMismoIntegrante() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        objProyecto.agregarIntegrante(new Empleado("111", "Samuel Vergara",
                "Ingeniero", (byte) 3));
        try {
            objProyecto.agregarIntegrante(new Empleado("111", "Samuel Vergara",
                    "Ingeniero", (byte) 3));
            fail("Debió rechazar el integrante repetido.");
        } catch (IllegalArgumentException error) {
            assertEquals(1, objProyecto.getSuEquipo().size());
        }
    }

    @Test
    public void seRetiraUnIntegranteDelEquipo() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        objProyecto.agregarIntegrante(new Empleado("111", "Samuel Vergara",
                "Ingeniero", (byte) 3));
        objProyecto.agregarIntegrante(new Empleado("222", "Joshua Angulo",
                "Ingeniero", (byte) 2));
        Empleado retirado = objProyecto.eliminarIntegrante("111");
        assertNotNull(retirado);
        assertEquals("Samuel Vergara", retirado.getNombreCompleto());
        assertEquals(1, objProyecto.getSuEquipo().size());
    }

    @Test
    public void retirarUnIntegranteQueNoExisteDevuelveNulo() {
        Proyecto objProyecto = crearProyecto("meses", (byte) 6);
        assertNull(objProyecto.eliminarIntegrante("999"));
    }

    @Test
    public void elConstructorCompletoRecibeElEquipo() {
        ArrayList<Empleado> elEquipo = new ArrayList<Empleado>();
        elEquipo.add(new Empleado("111", "Samuel Vergara", "Ingeniero", (byte) 3));
        Proyecto objProyecto = new Proyecto("2026-002", tituloValido,
                LocalDate.of(2026, 2, 15), "meses", (byte) 6, objCliente,
                objGerente, elEquipo, "EN CURSO");
        assertEquals(1, objProyecto.getSuEquipo().size());
        assertEquals("EN CURSO", objProyecto.getEstado());
    }
}
