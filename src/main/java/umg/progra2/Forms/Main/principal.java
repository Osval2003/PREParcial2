package umg.progra2.Formularios.Principal;

import umg.progra2.Formularios.Tabla1.ejercicio1;
import umg.progra2.Formularios.Ejercicio2.ejercicio2;
import umg.progra2.Formularios.Ejercicio3.ejercicio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class principal extends JFrame {
    private JPanel jFormPrincipal;
    private JLabel lblTitulo;
    private JButton buttonEjercicio1;
    private JButton buttonEjercicio2;
    private JButton buttonEjercicio3;

    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        JFrame frame = new principal();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Ajustar el tamaño de la ventana
        frame.setVisible(true);
    }

    public principal() {

        // Configurar el contenido del JFrame
        setTitle("Principal");
        setContentPane(jFormPrincipal);
        setLayout(null); // Establecer el layout del panel a null para posicionar manualmente los componentes
        setLocationRelativeTo(null);

        // Cambiar la posición y tamaño del lblTitulo
        lblTitulo.setBounds(160, 20, 200, 50); // (x, y, width, height)
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Ajustar la posición y el tamaño de los botones
        buttonEjercicio1.setBounds(150, 100, 200, 30); // Posición y tamaño
        buttonEjercicio2.setBounds(150, 150, 200, 30);
        buttonEjercicio3.setBounds(150, 200, 200, 30);

        // Acción del botón Ejercicio 1
        buttonEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario del ejercicio 1
                ejercicio1 frm1 = new ejercicio1();
                frm1.setVisible(true);
                dispose();
            }
        });

        // Acción del botón Ejercicio 2
        buttonEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir el formulario del ejercicio 2
                ejercicio2 frm2 = new ejercicio2();
                frm2.setVisible(true);
                dispose();
            }
        });

        //Accion del boton Ejercicio 3
        buttonEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejercicio3 frm3 = new ejercicio3();
                frm3.setVisible(true);
                dispose();
            }
        });
    }
}
