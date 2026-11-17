package Vista;

import Modelo.Cliente;
import Modelo.Contacto;
import Modelo.Empleado;
import Modelo.Empresa;
import Modelo.PersonaNatural;
import Modelo.Proyecto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase vista del aplicativo de gestión de proyectos.
 *
 * Es de tipo JFrame, por lo tanto es una clase instanciable: tiene
 * constructor y sus métodos no llevan static, con excepción del main
 * y de los dos atributos contenedores.
 *
 * La interacción con el usuario se hace con JOptionPane y los resultados
 * se muestran en el área de texto de la ventana.
 */
public class UsaProyecto extends javax.swing.JFrame {
    // Identificador de versión de la clase serializable.
    // Permite que un archivo de objetos generado hoy se pueda leer
    // aunque la clase cambie más adelante.
    private static final long serialVersionUID = 1L;


    private static ArrayList<Proyecto> losProyectos = new ArrayList<Proyecto>();
    private static ArrayList<Cliente> losClientes = new ArrayList<Cliente>();

    private static final String ARCHIVO_PROYECTOS = "datosProyectos";
    private static final String ARCHIVO_CLIENTES = "datosClientes";

    // Componentes graficos. Se nombran con la convencion que usa la docente:
    // jPanel, jLabel, jTextField, jComboBox, jBoton y jTextArea.
    private javax.swing.JPanel jPanelDatosProyecto;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelResultados;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelTituloProyecto;
    private javax.swing.JLabel jLabelFechaInicio;
    private javax.swing.JLabel jLabelUnidadDuracion;
    private javax.swing.JLabel jLabelDuracionEstimada;
    private javax.swing.JLabel jLabelOperacion;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldTituloProyecto;
    private javax.swing.JTextField jTextFieldFechaInicio;
    private javax.swing.JTextField jTextFieldDuracionEstimada;
    private javax.swing.JComboBox<String> jComboBoxUnidadDuracion;
    private javax.swing.JComboBox<String> jComboBoxOperaciones;
    private javax.swing.JButton jBotonRegistrar;
    private javax.swing.JButton jBotonEjecutar;
    private javax.swing.JButton jBotonLimpiar;
    private javax.swing.JButton jBotonSalir;
    private javax.swing.JScrollPane jScrollPaneResultados;
    private javax.swing.JTextArea jTextAreaResultados;

    public UsaProyecto() {
        initComponents();
    }

    /**
     * Construye los componentes graficos de la ventana.
     * Es privado porque solo lo utiliza el constructor de esta clase.
     */
    private void initComponents() {

        jPanelDatosProyecto = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jPanelResultados = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelTituloProyecto = new javax.swing.JLabel();
        jLabelFechaInicio = new javax.swing.JLabel();
        jLabelUnidadDuracion = new javax.swing.JLabel();
        jLabelDuracionEstimada = new javax.swing.JLabel();
        jLabelOperacion = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldTituloProyecto = new javax.swing.JTextField();
        jTextFieldFechaInicio = new javax.swing.JTextField();
        jTextFieldDuracionEstimada = new javax.swing.JTextField();
        jComboBoxUnidadDuracion = new javax.swing.JComboBox<String>();
        jComboBoxOperaciones = new javax.swing.JComboBox<String>();
        jBotonRegistrar = new javax.swing.JButton();
        jBotonEjecutar = new javax.swing.JButton();
        jBotonLimpiar = new javax.swing.JButton();
        jBotonSalir = new javax.swing.JButton();
        jScrollPaneResultados = new javax.swing.JScrollPane();
        jTextAreaResultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de proyectos");
        setSize(960, 680);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout(10, 10));

        javax.swing.JPanel jPanelSuperior = new javax.swing.JPanel();
        jPanelSuperior.setLayout(new java.awt.BorderLayout());

        // ---------- Panel 1: datos del proyecto ----------
        jPanelDatosProyecto.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory
                        .createLineBorder(java.awt.Color.GRAY),
                        "Datos del proyecto"));
        jPanelDatosProyecto.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints posicion = new java.awt.GridBagConstraints();
        posicion.insets = new java.awt.Insets(6, 8, 6, 8);
        posicion.anchor = java.awt.GridBagConstraints.WEST;
        posicion.fill = java.awt.GridBagConstraints.HORIZONTAL;

        jLabelTitulo.setText("APLICATIVO DE GESTIÓN DE PROYECTOS");
        jLabelTitulo.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 16));
        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.gridwidth = 4;
        jPanelDatosProyecto.add(jLabelTitulo, posicion);
        posicion.gridwidth = 1;

        jLabelCodigo.setText("Código:");
        posicion.gridx = 0;
        posicion.gridy = 1;
        jPanelDatosProyecto.add(jLabelCodigo, posicion);

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setColumns(10);
        jTextFieldCodigo.setToolTipText("Lo genera el sistema al registrar");
        posicion.gridx = 1;
        jPanelDatosProyecto.add(jTextFieldCodigo, posicion);

        jLabelFechaInicio.setText("Fecha de inicio (aaaa-mm-dd):");
        posicion.gridx = 2;
        jPanelDatosProyecto.add(jLabelFechaInicio, posicion);

        jTextFieldFechaInicio.setColumns(12);
        posicion.gridx = 3;
        jPanelDatosProyecto.add(jTextFieldFechaInicio, posicion);

        jLabelTituloProyecto.setText("Título (20 a 100 caracteres):");
        posicion.gridx = 0;
        posicion.gridy = 2;
        jPanelDatosProyecto.add(jLabelTituloProyecto, posicion);

        jTextFieldTituloProyecto.setColumns(45);
        posicion.gridx = 1;
        posicion.gridwidth = 3;
        jPanelDatosProyecto.add(jTextFieldTituloProyecto, posicion);
        posicion.gridwidth = 1;

        jLabelUnidadDuracion.setText("Unidad de duración:");
        posicion.gridx = 0;
        posicion.gridy = 3;
        jPanelDatosProyecto.add(jLabelUnidadDuracion, posicion);

        jComboBoxUnidadDuracion.setModel(
                new javax.swing.DefaultComboBoxModel<String>(
                        Proyecto.UNIDADES_VALIDAS));
        posicion.gridx = 1;
        jPanelDatosProyecto.add(jComboBoxUnidadDuracion, posicion);

        jLabelDuracionEstimada.setText("Duración estimada:");
        posicion.gridx = 2;
        jPanelDatosProyecto.add(jLabelDuracionEstimada, posicion);

        jTextFieldDuracionEstimada.setColumns(5);
        posicion.gridx = 3;
        jPanelDatosProyecto.add(jTextFieldDuracionEstimada, posicion);

        jBotonRegistrar.setText("Registrar proyecto");
        posicion.gridx = 0;
        posicion.gridy = 4;
        posicion.gridwidth = 2;
        jPanelDatosProyecto.add(jBotonRegistrar, posicion);

        jBotonLimpiar.setText("Limpiar");
        posicion.gridx = 2;
        posicion.gridwidth = 2;
        jPanelDatosProyecto.add(jBotonLimpiar, posicion);
        posicion.gridwidth = 1;

        // ---------- Panel 2: operaciones ----------
        jPanelOperaciones.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory
                        .createLineBorder(java.awt.Color.GRAY), "Operaciones"));
        jPanelOperaciones.setLayout(new java.awt.FlowLayout(
                java.awt.FlowLayout.LEFT, 10, 10));

        jLabelOperacion.setText("Operación:");
        jPanelOperaciones.add(jLabelOperacion);

        jComboBoxOperaciones.setModel(new javax.swing.DefaultComboBoxModel<String>(
                new String[]{
                    "1. Consultar todos los proyectos",
                    "2. Consultar los proyectos en curso",
                    "3. Consultar el primer proyecto registrado",
                    "4. Consultar el último proyecto registrado",
                    "5. Consultar un proyecto dado su código",
                    "6. Consultar el gerente de un proyecto",
                    "7. Consultar el equipo de un proyecto",
                    "8. Registrar un integrante a un proyecto",
                    "9. Registrar el equipo a un proyecto",
                    "10. Actualizar el estado de un proyecto",
                    "11. Eliminar un integrante de un proyecto",
                    "12. Consultar los proyectos de un gerente",
                    "13. Consultar los proyectos de un cliente",
                    "14. Consultar los proyectos dado un tipo de cliente",
                    "15. Generar archivo de objetos de proyectos",
                    "16. Leer archivo de objetos de proyectos",
                    "17. Generar archivo de objetos de clientes",
                    "18. Leer archivo de objetos de clientes"
                }));
        jPanelOperaciones.add(jComboBoxOperaciones);

        jBotonEjecutar.setText("Ejecutar");
        jPanelOperaciones.add(jBotonEjecutar);

        jBotonSalir.setText("Salir");
        jPanelOperaciones.add(jBotonSalir);

        jPanelSuperior.add(jPanelDatosProyecto, java.awt.BorderLayout.NORTH);
        jPanelSuperior.add(jPanelOperaciones, java.awt.BorderLayout.SOUTH);

        // ---------- Panel 3: resultados ----------
        jPanelResultados.setBorder(javax.swing.BorderFactory
                .createTitledBorder(javax.swing.BorderFactory
                        .createLineBorder(java.awt.Color.GRAY), "Resultados"));
        jPanelResultados.setLayout(new java.awt.BorderLayout(5, 5));

        jTextAreaResultados.setEditable(false);
        jTextAreaResultados.setColumns(20);
        jTextAreaResultados.setRows(5);
        jTextAreaResultados.setFont(
                new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        jScrollPaneResultados.setViewportView(jTextAreaResultados);
        jPanelResultados.add(jScrollPaneResultados, java.awt.BorderLayout.CENTER);

        add(jPanelSuperior, java.awt.BorderLayout.NORTH);
        add(jPanelResultados, java.awt.BorderLayout.CENTER);

        // ---------- Eventos: un metodo privado por cada boton ----------
        jBotonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonRegistrarActionPerformed(evt);
            }
        });

        jBotonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonEjecutarActionPerformed(evt);
            }
        });

        jBotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonLimpiarActionPerformed(evt);
            }
        });

        jBotonSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonSalirActionPerformed(evt);
            }
        });
    }

    /**
     * Evento del boton Registrar proyecto.
     */
    private void jBotonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            losProyectos = registrarProyecto(losProyectos);
        } catch (IllegalArgumentException | DateTimeParseException error) {
            JOptionPane.showMessageDialog(this, error.getMessage(),
                    "Dato no válido", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Evento del boton Limpiar. Deja el formulario y los resultados en blanco.
     */
    private void jBotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldCodigo.setText("");
        jTextFieldTituloProyecto.setText("");
        jTextFieldFechaInicio.setText("");
        jTextFieldDuracionEstimada.setText("");
        jComboBoxUnidadDuracion.setSelectedIndex(0);
        jTextAreaResultados.setText("");
    }

    /**
     * Evento del boton Salir.
     */
    private void jBotonSalirActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "El programa se cerrará. Gracias por utilizar el servicio.");
        System.exit(0);
    }

    /**
     * Evento del boton Ejecutar. Determina cual operacion eligio el
     * usuario en el combo y llama al metodo correspondiente.
     */
    private void jBotonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            switch (jComboBoxOperaciones.getSelectedIndex()) {
                case 0:
                    mostrar(consultarTodosProyectos(losProyectos));
                    break;
                case 1:
                    mostrar(consultarProyectosEnCurso(losProyectos));
                    break;
                case 2:
                    mostrar(consultarPrimerProyecto(losProyectos));
                    break;
                case 3:
                    mostrar(consultarUltimoProyecto(losProyectos));
                    break;
                case 4:
                    mostrar(consultarProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 5:
                    mostrar(consultarGerenteProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 6:
                    mostrar(consultarEquipoProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 7:
                    mostrar(registrarIntegranteProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 8:
                    mostrar(registrarEquipoProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 9:
                    mostrar(actualizarEstadoProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 10:
                    mostrar(eliminarIntegranteProyectoDadoCodigo(losProyectos,
                            pedirTexto("Código del proyecto:")));
                    break;
                case 11:
                    mostrar(consultarProyectosDadoNumIDEGerente(losProyectos,
                            pedirTexto("Identificación del gerente:")));
                    break;
                case 12:
                    mostrar(consultarProyectosDadoNumIDECliente(losProyectos,
                            pedirTexto("Identificación del cliente:")));
                    break;
                case 13:
                    mostrar(consultarProyectosDadoTipoCliente(losProyectos,
                            pedirOpcion("Tipo de cliente:",
                                    new String[]{"Empresa", "PersonaNatural"})));
                    break;
                case 14:
                    generarArchivoDeObjetosProyectos(losProyectos);
                    break;
                case 15:
                    losProyectos = leerArchivoDeObjetosProyectos();
                    mostrar(consultarTodosProyectos(losProyectos));
                    break;
                case 16:
                    generarArchivoDeObjetosClientes(losProyectos);
                    break;
                case 17:
                    losClientes = leerArchivoDeObjetosClientes();
                    mostrarClientes(losClientes);
                    break;
                default:
                    mostrar("Operación no reconocida.");
            }
        } catch (IllegalArgumentException | DateTimeParseException error) {
            JOptionPane.showMessageDialog(this, error.getMessage(),
                    "Dato no válido", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UsaProyecto().setVisible(true);
            }
        });
    }

    // ==================== REGISTRO ====================

    /**
     * Realiza el proceso de registro de un proyecto.
     * Genera el código con el método respectivo, solicita los demás datos,
     * crea el proyecto, lo agrega a la lista y devuelve la lista.
     */
    public ArrayList<Proyecto> registrarProyecto(ArrayList<Proyecto> datos) {

        // Los datos propios del proyecto se toman del formulario.
        String codigo = generarCodigoProyecto(datos);
        String titulo = jTextFieldTituloProyecto.getText().trim();
        String unidadDuracion = jComboBoxUnidadDuracion.getSelectedItem().toString();

        if (jTextFieldFechaInicio.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Escriba la fecha de inicio con el formato aaaa-mm-dd.");
        }
        LocalDate fechaInicio;
        try {
            fechaInicio = LocalDate.parse(jTextFieldFechaInicio.getText().trim());
        } catch (DateTimeParseException error) {
            throw new IllegalArgumentException(
                    "La fecha de inicio debe tener el formato aaaa-mm-dd, "
                    + "con guiones. Ej. 2026-02-15.");
        }

        byte duracionEstimada;
        try {
            duracionEstimada = Byte.parseByte(
                    jTextFieldDuracionEstimada.getText().trim());
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(
                    "La duración estimada debe ser un número entero.");
        }

        // Los datos del cliente, del gerente y del equipo se solicitan con
        // ventanas de dialogo porque son demasiados para un solo formulario.
        Cliente suCliente = leerCliente();
        Empleado suGerente = leerEmpleado("GERENTE DEL PROYECTO");

        Proyecto objProyecto = new Proyecto(codigo, titulo, fechaInicio,
                unidadDuracion, duracionEstimada, suCliente, suGerente);

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea registrar el equipo del proyecto en este momento?",
                "Equipo del proyecto", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            int cantidad = pedirEntero("Cantidad de integrantes del equipo:");
            for (int i = 0; i < cantidad; i++) {
                objProyecto.agregarIntegrante(
                        leerEmpleado("INTEGRANTE " + (i + 1) + " DEL EQUIPO"));
            }
        }

        datos.add(objProyecto);
        jTextFieldCodigo.setText(codigo);
        mostrar("Proyecto registrado con el código " + codigo + "\n" + objProyecto);
        return datos;
    }

    /**
     * Genera el código del proyecto: el año actual, un guión y un
     * consecutivo de tres dígitos que reinicia en 1 cada año.
     */
    public String generarCodigoProyecto(ArrayList<Proyecto> datos) {
        int anioActual = LocalDate.now().getYear();
        int consecutivo = 0;
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().startsWith(anioActual + "-")) {
                consecutivo++;
            }
        }
        consecutivo++;
        return String.format("%d-%03d", anioActual, consecutivo);
    }

    // ==================== CONSULTAS GENERALES ====================

    /**
     * Devuelve la información de cada uno de los proyectos registrados.
     */
    public String consultarTodosProyectos(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        String resultado = "TODOS LOS PROYECTOS REGISTRADOS\n";
        for (Proyecto objProyecto : datos) {
            resultado += objProyecto;
        }
        return resultado;
    }

    /**
     * Devuelve los proyectos que se encuentran en estado EN CURSO.
     */
    public String consultarProyectosEnCurso(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        String resultado = "";
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getEstado().equals("EN CURSO")) {
                resultado += objProyecto;
            }
        }
        if (resultado.isEmpty()) {
            return "No hay proyectos en curso.";
        }
        return "PROYECTOS EN CURSO\n" + resultado;
    }

    /**
     * Devuelve la información del primer proyecto registrado.
     */
    public String consultarPrimerProyecto(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        return "PRIMER PROYECTO REGISTRADO\n" + datos.get(0);
    }

    /**
     * Devuelve la información del último proyecto registrado.
     */
    public String consultarUltimoProyecto(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        return "ÚLTIMO PROYECTO REGISTRADO\n" + datos.get(datos.size() - 1);
    }

    /**
     * Devuelve la información del proyecto que tenga el código indicado.
     */
    public String consultarProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                return "PROYECTO " + codigoProyecto + "\n" + objProyecto;
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    /**
     * Devuelve la información del gerente del proyecto indicado.
     */
    public String consultarGerenteProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                return "GERENTE DEL PROYECTO " + codigoProyecto + "\n"
                        + objProyecto.getSuGerente();
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    /**
     * Devuelve la información de cada integrante del equipo del proyecto.
     */
    public String consultarEquipoProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                if (objProyecto.getSuEquipo().isEmpty()) {
                    return "El proyecto " + codigoProyecto
                            + " aún no tiene equipo asignado.";
                }
                String resultado = "EQUIPO DEL PROYECTO " + codigoProyecto + "\n";
                for (Empleado integrante : objProyecto.getSuEquipo()) {
                    resultado += integrante + "\n";
                }
                return resultado;
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    // ==================== CONSULTAS POR CLIENTE Y GERENTE ====================

    /**
     * Devuelve los proyectos cuyo gerente tenga la identificación indicada.
     */
    public String consultarProyectosDadoNumIDEGerente(ArrayList<Proyecto> datos,
            String numIDEGerente) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        String resultado = "";
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getSuGerente().getNumeroIdentificacion()
                    .equals(numIDEGerente)) {
                resultado += objProyecto;
            }
        }
        if (resultado.isEmpty()) {
            return "No hay proyectos del gerente " + numIDEGerente + ".";
        }
        return "PROYECTOS DEL GERENTE " + numIDEGerente + "\n" + resultado;
    }

    /**
     * Devuelve los proyectos cuyo cliente tenga la identificación indicada.
     */
    public String consultarProyectosDadoNumIDECliente(ArrayList<Proyecto> datos,
            String numIDECliente) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        String resultado = "";
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getSuCliente().getNumeroIdentificacion()
                    .equals(numIDECliente)) {
                resultado += objProyecto;
            }
        }
        if (resultado.isEmpty()) {
            return "No hay proyectos del cliente " + numIDECliente + ".";
        }
        return "PROYECTOS DEL CLIENTE " + numIDECliente + "\n" + resultado;
    }

    /**
     * Devuelve los proyectos cuyo cliente sea del tipo indicado.
     * Se usa instanceof para preguntar por el tipo concreto del cliente,
     * que es posible gracias al polimorfismo.
     */
    public String consultarProyectosDadoTipoCliente(ArrayList<Proyecto> datos,
            String tipoCliente) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        String resultado = "";
        for (Proyecto objProyecto : datos) {
            Cliente objCliente = objProyecto.getSuCliente();
            if ((tipoCliente.equalsIgnoreCase("Empresa")
                    && objCliente instanceof Empresa)
                    || (tipoCliente.equalsIgnoreCase("PersonaNatural")
                    && objCliente instanceof PersonaNatural)) {
                resultado += objProyecto;
            }
        }
        if (resultado.isEmpty()) {
            return "No hay proyectos con clientes del tipo " + tipoCliente + ".";
        }
        return "PROYECTOS CON CLIENTE DEL TIPO " + tipoCliente + "\n" + resultado;
    }

    // ==================== EQUIPO Y ESTADO ====================

    /**
     * Registra un integrante en el equipo del proyecto indicado.
     */
    public String registrarIntegranteProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                objProyecto.agregarIntegrante(
                        leerEmpleado("NUEVO INTEGRANTE DEL EQUIPO"));
                return "Integrante agregado al proyecto " + codigoProyecto + ".\n"
                        + consultarEquipoProyectoDadoCodigo(datos, codigoProyecto);
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    /**
     * Registra todos los integrantes del equipo del proyecto indicado.
     */
    public String registrarEquipoProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                int cantidad = pedirEntero("Cantidad de integrantes a registrar:");
                ArrayList<Empleado> elEquipo = new ArrayList<Empleado>();
                for (int i = 0; i < cantidad; i++) {
                    elEquipo.add(leerEmpleado("INTEGRANTE " + (i + 1) + " DEL EQUIPO"));
                }
                objProyecto.setSuEquipo(elEquipo);
                return "Equipo registrado en el proyecto " + codigoProyecto + ".\n"
                        + consultarEquipoProyectoDadoCodigo(datos, codigoProyecto);
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    /**
     * Retira un integrante del equipo del proyecto indicado.
     */
    public String eliminarIntegranteProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                String numeroIdentificacion
                        = pedirTexto("Identificación del integrante a retirar:");
                Empleado integranteRetirado
                        = objProyecto.eliminarIntegrante(numeroIdentificacion);
                if (integranteRetirado == null) {
                    return "El integrante " + numeroIdentificacion
                            + " no pertenece al equipo del proyecto "
                            + codigoProyecto + ".";
                }
                return "Integrante retirado del proyecto " + codigoProyecto + ":\n"
                        + integranteRetirado;
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    /**
     * Cambia el estado del proyecto indicado.
     */
    public String actualizarEstadoProyectoDadoCodigo(ArrayList<Proyecto> datos,
            String codigoProyecto) {
        if (datos.isEmpty()) {
            return "No hay proyectos registrados.";
        }
        for (Proyecto objProyecto : datos) {
            if (objProyecto.getCodigo().equals(codigoProyecto)) {
                String estadoAnterior = objProyecto.getEstado();
                objProyecto.setEstado(pedirOpcion("Nuevo estado del proyecto:",
                        Proyecto.ESTADOS_VALIDOS));
                return "El proyecto " + codigoProyecto + " pasó del estado "
                        + estadoAnterior + " al estado " + objProyecto.getEstado() + ".";
            }
        }
        return "No existe un proyecto con el código " + codigoProyecto + ".";
    }

    // ==================== ARCHIVOS DE OBJETOS ====================

    /**
     * Guarda todos los proyectos de la lista en el archivo de objetos
     * "datosProyectos".
     */
    public void generarArchivoDeObjetosProyectos(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            mostrar("La lista de proyectos está vacía, no se generó el archivo.");
            return;
        }
        try {
            FileOutputStream archivo = new FileOutputStream(ARCHIVO_PROYECTOS);
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            for (Proyecto objProyecto : datos) {
                salida.writeObject(objProyecto);
            }
            salida.close();
            archivo.close();
            mostrar("Se generó el archivo " + ARCHIVO_PROYECTOS + " con "
                    + datos.size() + " proyecto(s).\nUbicación: "
                    + new File(ARCHIVO_PROYECTOS).getAbsolutePath());
        } catch (java.io.IOException error) {
            mostrar("No se pudo generar el archivo de proyectos: "
                    + error.getMessage());
        }
    }

    /**
     * Lee el archivo de objetos "datosProyectos" y devuelve la lista
     * de proyectos que contiene.
     */
    public ArrayList<Proyecto> leerArchivoDeObjetosProyectos() {
        ArrayList<Proyecto> datos = new ArrayList<Proyecto>();
        File archivoProyectos = new File(ARCHIVO_PROYECTOS);
        if (!archivoProyectos.exists()) {
            mostrar("El archivo " + ARCHIVO_PROYECTOS + " no existe todavía.");
            return losProyectos;
        }
        try {
            FileInputStream archivo = new FileInputStream(archivoProyectos);
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            boolean hayRegistros = true;
            while (hayRegistros) {
                try {
                    datos.add((Proyecto) entrada.readObject());
                } catch (java.io.EOFException fin) {
                    hayRegistros = false;
                }
            }
            entrada.close();
            archivo.close();
        } catch (java.io.IOException | ClassNotFoundException error) {
            mostrar("No se pudo leer el archivo de proyectos: " + error.getMessage());
            return losProyectos;
        }
        return datos;
    }

    /**
     * Guarda en el archivo de objetos "datosClientes" el cliente de cada
     * uno de los proyectos de la lista.
     */
    public void generarArchivoDeObjetosClientes(ArrayList<Proyecto> datos) {
        if (datos.isEmpty()) {
            mostrar("La lista de proyectos está vacía, no se generó el archivo.");
            return;
        }
        try {
            FileOutputStream archivo = new FileOutputStream(ARCHIVO_CLIENTES);
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            for (Proyecto objProyecto : datos) {
                salida.writeObject(objProyecto.getSuCliente());
            }
            salida.close();
            archivo.close();
            mostrar("Se generó el archivo " + ARCHIVO_CLIENTES + " con "
                    + datos.size() + " cliente(s).\nUbicación: "
                    + new File(ARCHIVO_CLIENTES).getAbsolutePath());
        } catch (java.io.IOException error) {
            mostrar("No se pudo generar el archivo de clientes: "
                    + error.getMessage());
        }
    }

    /**
     * Lee el archivo de objetos "datosClientes" y devuelve la lista de
     * clientes, verificando que no se repita ninguno.
     */
    public ArrayList<Cliente> leerArchivoDeObjetosClientes() {
        ArrayList<Cliente> datos = new ArrayList<Cliente>();
        File archivoClientes = new File(ARCHIVO_CLIENTES);
        if (!archivoClientes.exists()) {
            mostrar("El archivo " + ARCHIVO_CLIENTES + " no existe todavía.");
            return losClientes;
        }
        try {
            FileInputStream archivo = new FileInputStream(archivoClientes);
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            boolean hayRegistros = true;
            while (hayRegistros) {
                try {
                    Cliente objCliente = (Cliente) entrada.readObject();
                    // Verifica que el cliente no esté repetido en la lista.
                    boolean estaRepetido = false;
                    for (Cliente clienteGuardado : datos) {
                        if (clienteGuardado.getNumeroIdentificacion()
                                .equals(objCliente.getNumeroIdentificacion())) {
                            estaRepetido = true;
                            break;
                        }
                    }
                    if (!estaRepetido) {
                        datos.add(objCliente);
                    }
                } catch (java.io.EOFException fin) {
                    hayRegistros = false;
                }
            }
            entrada.close();
            archivo.close();
        } catch (java.io.IOException | ClassNotFoundException error) {
            mostrar("No se pudo leer el archivo de clientes: " + error.getMessage());
            return losClientes;
        }
        return datos;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Escribe un texto en el área de resultados de la ventana.
     */
    private void mostrar(String informacion) {
        jTextAreaResultados.setText(informacion);
        jTextAreaResultados.setCaretPosition(0);
    }

    /**
     * Muestra en el área de resultados la lista de clientes leída del archivo.
     */
    private void mostrarClientes(ArrayList<Cliente> datos) {
        if (datos.isEmpty()) {
            mostrar("No hay clientes para mostrar.");
            return;
        }
        String resultado = "CLIENTES LEÍDOS DEL ARCHIVO " + ARCHIVO_CLIENTES + "\n";
        for (Cliente objCliente : datos) {
            resultado += "\n" + objCliente + "\n";
        }
        mostrar(resultado);
    }

    /**
     * Solicita los datos de un cliente y lo crea según el tipo elegido.
     * Si es Empresa, pide además los datos de su contacto.
     */
    private Cliente leerCliente() {
        String tipoCliente = pedirOpcion("Tipo de cliente:",
                new String[]{"Empresa", "PersonaNatural"});
        String numeroIdentificacion = pedirTexto("CLIENTE - Identificación:");
        String nombre = pedirTexto("CLIENTE - Nombre:");
        String nombreCiudad = pedirTexto("CLIENTE - Ciudad:");

        Cliente objCliente;
        if (tipoCliente.equals("Empresa")) {
            Contacto objContacto = new Contacto(
                    pedirTexto("CONTACTO - Nombre completo:"),
                    pedirTexto("CONTACTO - Cargo:"),
                    pedirTexto("CONTACTO - Teléfono:"),
                    pedirTexto("CONTACTO - Correo electrónico:"));
            objCliente = new Empresa(numeroIdentificacion, nombre,
                    nombreCiudad, objContacto);
        } else {
            objCliente = new PersonaNatural(numeroIdentificacion, nombre,
                    nombreCiudad,
                    pedirTexto("CLIENTE - Teléfono:"),
                    pedirTexto("CLIENTE - Correo electrónico:"));
        }
        return objCliente;
    }

    /**
     * Solicita los datos de un empleado y lo crea.
     */
    private Empleado leerEmpleado(String titulo) {
        return new Empleado(
                pedirTexto(titulo + "\nIdentificación:"),
                pedirTexto(titulo + "\nNombre completo:"),
                pedirTexto(titulo + "\nTítulo profesional:"),
                pedirByte(titulo + "\nAños de experiencia:"));
    }

    /**
     * Pide un texto al usuario. No acepta que se deje vacío ni que se cancele.
     */
    private String pedirTexto(String mensaje) {
        String dato = JOptionPane.showInputDialog(this, mensaje);
        while (dato == null || dato.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El dato es obligatorio.");
            dato = JOptionPane.showInputDialog(this, mensaje);
        }
        return dato.trim();
    }

    /**
     * Pide un número entero al usuario y repite hasta que sea válido.
     */
    private int pedirEntero(String mensaje) {
        while (true) {
            try {
                int dato = Integer.parseInt(pedirTexto(mensaje));
                if (dato > 0) {
                    return dato;
                }
                JOptionPane.showMessageDialog(this, "Debe ser mayor que cero.");
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número entero.");
            }
        }
    }

    /**
     * Pide un número de tipo byte al usuario y repite hasta que sea válido.
     */
    private byte pedirByte(String mensaje) {
        while (true) {
            try {
                return Byte.parseByte(pedirTexto(mensaje));
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar un número entero entre -128 y 127.");
            }
        }
    }

    /**
     * Pide una fecha en formato aaaa-mm-dd y repite hasta que sea válida.
     */
    private LocalDate pedirFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(pedirTexto(mensaje));
            } catch (DateTimeParseException error) {
                JOptionPane.showMessageDialog(this,
                        "La fecha debe tener el formato aaaa-mm-dd. Ej. 2026-02-15.");
            }
        }
    }

    /**
     * Muestra una lista de opciones y devuelve la que elija el usuario.
     */
    private String pedirOpcion(String mensaje, String[] opciones) {
        Object seleccion = JOptionPane.showInputDialog(this, mensaje,
                "Seleccione una opción", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        if (seleccion == null) {
            throw new IllegalArgumentException("Operación cancelada por el usuario.");
        }
        return seleccion.toString();
    }
}
