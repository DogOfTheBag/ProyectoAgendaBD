package alber.GUI;

import alber.data.Contacto;
import alber.data.ContactoDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaApp extends JFrame {

    private ContactoDAO dao;

    private JButton btnAñadir;
    private JButton btnModificar;
    private JButton btnBorrar;
    private JButton btnListarNombre;
    private JButton btnListarTelefono;

    public VentanaApp() {
        dao = new ContactoDAO();

        setTitle("Agenda Proyecto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Agenda Proyecto", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(new EmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel hueco = new JPanel();
        hueco.setOpaque(false);
        add(hueco, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(10, 40, 25, 40));
        panelBotones.setLayout(new GridLayout(5, 1, 0, 12)); // 5 filas, espacio vertical 12

        btnAñadir = new JButton("Añadir");
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        btnListarNombre = new JButton("Listar por nombre");
        btnListarTelefono = new JButton("Listar por teléfono");

        panelBotones.add(btnAñadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnListarNombre);
        panelBotones.add(btnListarTelefono);

        add(panelBotones, BorderLayout.SOUTH);

        //********EVENTOS
        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre de la persona a añadir", "Nombre", JOptionPane.QUESTION_MESSAGE);
                String direccion = JOptionPane.showInputDialog(null, "Introduce la direccion de la persona a añadir", "Direccion", JOptionPane.QUESTION_MESSAGE);
                String telefono = JOptionPane.showInputDialog(null, "Introduce el teléfono de la persona a añadir", "Telefono", JOptionPane.QUESTION_MESSAGE);
                String aficion = JOptionPane.showInputDialog(null, "Introduce la aficion de la persona a añadir", "Aficion", JOptionPane.QUESTION_MESSAGE);
                String pandilla = JOptionPane.showInputDialog(null, "Introduce la pandilla de la persona a añadir", "Pandilla", JOptionPane.QUESTION_MESSAGE);
                String vacaciones = JOptionPane.showInputDialog(null, "Introduce el plan de vacaciones de la persona a añadir", "Vacaciones", JOptionPane.QUESTION_MESSAGE);

                Contacto con = new Contacto(nombre, direccion, telefono, aficion, pandilla, vacaciones);
                dao.Insertar(con);
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String telefonoActual = JOptionPane.showInputDialog(null, "Introduce el teléfono de la persona a modificar", "Telefono", JOptionPane.QUESTION_MESSAGE);


                String nombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre de la persona a añadir", "Nombre", JOptionPane.QUESTION_MESSAGE);
                String direccion = JOptionPane.showInputDialog(null, "Introduce la nueva direccion de la persona a añadir", "Direccion", JOptionPane.QUESTION_MESSAGE);
                String telefono = JOptionPane.showInputDialog(null, "Introduce el nuevo teléfono de la persona a añadir", "Telefono", JOptionPane.QUESTION_MESSAGE);
                String aficion = JOptionPane.showInputDialog(null, "Introduce la nueva aficion de la persona a añadir", "Aficion", JOptionPane.QUESTION_MESSAGE);
                String pandilla = JOptionPane.showInputDialog(null, "Introduce la nueva pandilla de la persona a añadir", "Pandilla", JOptionPane.QUESTION_MESSAGE);
                String vacaciones = JOptionPane.showInputDialog(null, "Introduce el nuevo plan de vacaciones de la persona a añadir", "Vacaciones", JOptionPane.QUESTION_MESSAGE);

                Contacto con = new Contacto(nombre, direccion, telefono, aficion, pandilla, vacaciones);
                dao.Modificar(telefonoActual,con);

            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String telefono = JOptionPane.showInputDialog(null, "Introduce el teléfono de la persona a eliminar", "Telefono", JOptionPane.QUESTION_MESSAGE);
                dao.delete(telefono);
            }
        });

        btnListarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Contacto> listaNombre = new ArrayList<>();
                listaNombre = dao.Listar();
                JOptionPane.showMessageDialog(null, listaNombre);
            }
        });

        btnListarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Contacto> listaTelefono = new ArrayList<>();
                listaTelefono = dao.ListarTelefonos();
                JOptionPane.showMessageDialog(null, listaTelefono);
            }
        });
    }

}
