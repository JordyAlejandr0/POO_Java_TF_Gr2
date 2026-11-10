package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Proyecto que desarrolla la organización para un cliente.
 *
 * Es la clase principal del modelo: contiene un cliente, un gerente
 * y un equipo de empleados.
 *
 * Reglas del enunciado:
 * - codigo: cadena de 8 caracteres, el año seguido de un guión y un
 *   consecutivo de 3 dígitos que reinicia en 1 cada año. Ej. 2026-001.
 * - titulo: entre 20 y 100 caracteres.
 * - unidadDuracion: semanas, meses o años.
 * - duracionEstimada: semanas entre 1 y 3; meses entre 1 y 11, 13 y 23,
 *   25 y 35; años entre 1 y 3.
 * - suEquipo: un proyecto se puede registrar sin equipo.
 * - estado: por defecto INICIADO.
 */
public class Proyecto implements Serializable {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    public static final String[] ESTADOS_VALIDOS = {"INICIADO", "EN CURSO",
        "CANCELADO", "FINALIZADO", "SUSPENDIDO", "APLAZADO"};

    public static final String[] UNIDADES_VALIDAS = {"semanas", "meses", "años"};

    private String codigo;
    private String titulo;
    private LocalDate fechaInicio;
    private String unidadDuracion;
    private byte duracionEstimada;
    private Cliente suCliente;
    private Empleado suGerente;
    private ArrayList<Empleado> suEquipo;
    private String estado;

    /**
     * Constructor completo: recibe también el equipo del proyecto.
     */
    public Proyecto(String codigo, String titulo, LocalDate fechaInicio,
            String unidadDuracion, byte duracionEstimada, Cliente suCliente,
            Empleado suGerente, ArrayList<Empleado> suEquipo, String estado) {
        setCodigo(codigo);
        setTitulo(titulo);
        setFechaInicio(fechaInicio);
        // La unidad se asigna antes que la duración porque la validación
        // de la duración depende de cuál sea la unidad.
        setUnidadDuracion(unidadDuracion);
        setDuracionEstimada(duracionEstimada);
        setSuCliente(suCliente);
        setSuGerente(suGerente);
        setSuEquipo(suEquipo);
        setEstado(estado);
    }

    /**
     * Constructor sin equipo. Sobrecarga del anterior.
     * El enunciado permite registrar un proyecto sin equipo y agregarlo
     * después, integrante por integrante o completo.
     * El estado queda en INICIADO, que es el valor por defecto.
     */
    public Proyecto(String codigo, String titulo, LocalDate fechaInicio,
            String unidadDuracion, byte duracionEstimada, Cliente suCliente,
            Empleado suGerente) {
        this(codigo, titulo, fechaInicio, unidadDuracion, duracionEstimada,
                suCliente, suGerente, new ArrayList<Empleado>(), "INICIADO");
    }

    /**
     * Calcula la fecha en que debería terminar el proyecto, sumando a la
     * fecha de inicio la duración estimada en la unidad correspondiente.
     */
    public LocalDate determinarFechaFinalizacion() {
        LocalDate fechaFinalizacion;
        if (unidadDuracion.equalsIgnoreCase("semanas")) {
            fechaFinalizacion = fechaInicio.plusWeeks(duracionEstimada);
        } else {
            if (unidadDuracion.equalsIgnoreCase("meses")) {
                fechaFinalizacion = fechaInicio.plusMonths(duracionEstimada);
            } else {
                fechaFinalizacion = fechaInicio.plusYears(duracionEstimada);
            }
        }
        return fechaFinalizacion;
    }

    /**
     * Agrega un integrante al equipo del proyecto.
     * No agrega dos veces el mismo número de identificación.
     */
    public void agregarIntegrante(Empleado nuevoIntegrante) {
        if (nuevoIntegrante == null) {
            throw new IllegalArgumentException("El integrante no puede ser nulo.");
        }
        for (Empleado integrante : suEquipo) {
            if (integrante.getNumeroIdentificacion()
                    .equals(nuevoIntegrante.getNumeroIdentificacion())) {
                throw new IllegalArgumentException(
                        "El integrante ya pertenece al equipo del proyecto.");
            }
        }
        suEquipo.add(nuevoIntegrante);
    }

    /**
     * Retira del equipo al integrante con el número de identificación dado.
     * Devuelve el empleado retirado, o null si no estaba en el equipo.
     */
    public Empleado eliminarIntegrante(String numeroIdentificacion) {
        Empleado integranteRetirado = null;
        for (int i = 0; i < suEquipo.size(); i++) {
            if (suEquipo.get(i).getNumeroIdentificacion()
                    .equals(numeroIdentificacion)) {
                integranteRetirado = suEquipo.remove(i);
                break;
            }
        }
        return integranteRetirado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || !codigo.matches("\\d{4}-\\d{3}")) {
            throw new IllegalArgumentException(
                    "El código debe tener el formato aaaa-nnn. Ej. 2026-001.");
        }
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 20
                || titulo.trim().length() > 100) {
            throw new IllegalArgumentException(
                    "El título debe tener entre 20 y 100 caracteres.");
        }
        this.titulo = titulo.trim();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria.");
        }
        this.fechaInicio = fechaInicio;
    }

    public String getUnidadDuracion() {
        return unidadDuracion;
    }

    public void setUnidadDuracion(String unidadDuracion) {
        boolean esValida = false;
        if (unidadDuracion != null) {
            for (String unidad : UNIDADES_VALIDAS) {
                if (unidad.equalsIgnoreCase(unidadDuracion.trim())) {
                    esValida = true;
                    break;
                }
            }
        }
        if (!esValida) {
            throw new IllegalArgumentException(
                    "La unidad de duración debe ser semanas, meses o años.");
        }
        this.unidadDuracion = unidadDuracion.trim().toLowerCase();
    }

    public byte getDuracionEstimada() {
        return duracionEstimada;
    }

    /**
     * Valida la duración según la unidad, tal como lo exige el enunciado:
     * semanas entre 1 y 3; meses entre 1 y 11, 13 y 23, 25 y 35
     * (se excluyen 12, 24 y 36 porque esos valores corresponden a años);
     * años entre 1 y 3.
     */
    public void setDuracionEstimada(byte duracionEstimada) {
        boolean esValida;
        if (unidadDuracion.equals("semanas") || unidadDuracion.equals("años")) {
            esValida = duracionEstimada >= 1 && duracionEstimada <= 3;
        } else {
            esValida = (duracionEstimada >= 1 && duracionEstimada <= 11)
                    || (duracionEstimada >= 13 && duracionEstimada <= 23)
                    || (duracionEstimada >= 25 && duracionEstimada <= 35);
        }
        if (!esValida) {
            throw new IllegalArgumentException(
                    "La duración no es válida para la unidad " + unidadDuracion + ".");
        }
        this.duracionEstimada = duracionEstimada;
    }

    public Cliente getSuCliente() {
        return suCliente;
    }

    public void setSuCliente(Cliente suCliente) {
        if (suCliente == null) {
            throw new IllegalArgumentException("El proyecto requiere un cliente.");
        }
        this.suCliente = suCliente;
    }

    public Empleado getSuGerente() {
        return suGerente;
    }

    public void setSuGerente(Empleado suGerente) {
        if (suGerente == null) {
            throw new IllegalArgumentException("El proyecto requiere un gerente.");
        }
        this.suGerente = suGerente;
    }

    public ArrayList<Empleado> getSuEquipo() {
        return suEquipo;
    }

    public void setSuEquipo(ArrayList<Empleado> suEquipo) {
        if (suEquipo == null) {
            this.suEquipo = new ArrayList<Empleado>();
        } else {
            this.suEquipo = suEquipo;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        boolean esValido = false;
        if (estado != null) {
            for (String estadoValido : ESTADOS_VALIDOS) {
                if (estadoValido.equalsIgnoreCase(estado.trim())) {
                    esValido = true;
                    break;
                }
            }
        }
        if (!esValido) {
            throw new IllegalArgumentException(
                    "El estado debe ser INICIADO, EN CURSO, CANCELADO, "
                    + "FINALIZADO, SUSPENDIDO o APLAZADO.");
        }
        this.estado = estado.trim().toUpperCase();
    }

    @Override
    public String toString() {
        String informacion = "\n===== PROYECTO " + codigo + " =====";
        informacion += "\nTítulo: " + titulo;
        informacion += "\nFecha de inicio: " + fechaInicio;
        informacion += "\nDuración estimada: " + duracionEstimada + " " + unidadDuracion;
        informacion += "\nFecha de finalización: " + determinarFechaFinalizacion();
        informacion += "\nEstado: " + estado;
        informacion += "\n--- Cliente ---\n" + suCliente;
        informacion += "\n--- Gerente ---\n" + suGerente;
        informacion += "\n--- Equipo (" + suEquipo.size() + " integrantes) ---";
        if (suEquipo.isEmpty()) {
            informacion += "\nEl proyecto aún no tiene equipo asignado.";
        } else {
            for (Empleado integrante : suEquipo) {
                informacion += "\n" + integrante;
            }
        }
        return informacion + "\n";
    }
}
