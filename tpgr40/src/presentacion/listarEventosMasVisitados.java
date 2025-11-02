package presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import logica.ControladorEvento;
import logica.Evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EventosMasVisitados extends JInternalFrame {
    private JTable tablaEventos;
    private JButton btnActualizar;
    private JButton btnCerrar;
    private JScrollPane scrollPane;
    private JLabel lblTitulo;
    private DefaultTableModel modeloTabla;

    public EventosMasVisitados() {
        initComponents();
    }

    private void initComponents() {
        setVisible(true);
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);

        setTitle("Eventos más visitados");
        setBounds(50, 50, 600, 400);
        getContentPane().setLayout(null);

        lblTitulo = new JLabel("Top 5 - Eventos más visitados");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(100, 20, 400, 30);
        getContentPane().add(lblTitulo);

        // --- Tabla ---
        String[] columnas = {"#", "Nombre del Evento", "Cantidad de Visitas"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloTabla);
        tablaEventos.setEnabled(false); // no editable por el usuario

        scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBounds(50, 70, 500, 200);
        getContentPane().add(scrollPane);

        // --- Botón Actualizar ---
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(150, 300, 120, 30);
        getContentPane().add(btnActualizar);

        // --- Botón Cerrar ---
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(330, 300, 120, 30);
        getContentPane().add(btnCerrar);

        // Acción de cerrar
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        // Acción de actualizar
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarEventosMasVisitados();
            }
        });