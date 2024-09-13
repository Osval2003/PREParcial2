package umg.progra2.Formularios.Ejercicio2;

import umg.progra2.Formularios.Principal.principal;
import umg.progra2.Model.Modeltb_usuarios;
import umg.progra2.Servicios.Servicetb_usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ejercicio2 extends JFrame {
    private JLabel lblTitulo2;
    private JPanel jFormEjercicio2;
    private JButton buttonCrear2;
    private JButton buttonBuscar2;
    private JButton buttonActualizar2;
    private JButton buttonEliminar2;
    private JLabel lblIdUsuario;
    private JTextField textFieldIdUsuario;
    private JLabel lblCarne;
    private JTextField textFieldCarne;
    private JLabel lblNombre;
    private JTextField textFieldNombre;
    private JLabel lblCorreo;
    private JTextField textFieldCorreo;
    private JLabel lblSeccion;
    private JTextField textFieldSeccion;
    private JLabel lblTelegramId;
    private JTextField textFieldTelegramId;
    private JCheckBox checkBoxActividad;
    private JComboBox comboBoxSeccion;
    private JButton buttonRegresarMenu;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio2");
        frame.setContentPane(new ejercicio2().jFormEjercicio2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }

    public ejercicio2() {//constructor para las modificaciones del ejercicio 2

        // Cambiar la posición y tamaño del lblTitulo
        lblTitulo2.setBounds(145,20,200,50);

        // Cambiar el tamaño de la fuente del lblTitulo
        lblTitulo2.setFont(new Font("Arial", Font.BOLD, 20));

        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jFormEjercicio2);
        setTitle("Ejercicio2");
        setSize(600, 500); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana


        //para las secciones
        comboBoxSeccion.addItem("");
        comboBoxSeccion.addItem("A");
        comboBoxSeccion.addItem("B");
        comboBoxSeccion.addItem("C");

        //Para crear un usuario
        buttonCrear2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Modeltb_usuarios usuario = new Modeltb_usuarios();
                usuario.setNombre(textFieldNombre.getText());
                usuario.setCorreo(textFieldCorreo.getText());
                usuario.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                usuario.setCarne(textFieldCarne.getText()); // No olvides esto

                // Verificar si todos los campos están llenos
                if (textFieldNombre.getText().isEmpty() ||
                        textFieldCorreo.getText().isEmpty() ||
                        textFieldCarne.getText().isEmpty() ||
                        comboBoxSeccion.getSelectedItem().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                    return;
                }

                // Manejo del campo activo
                if (checkBoxActividad.isSelected()) {
                    usuario.setActivo("1");
                } else {
                    usuario.setActivo("0");
                }

                try {
                    new Servicetb_usuarios().guardarOActualizarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");

                    // Limpiar los campos después de la creación
                    textFieldNombre.setText("");
                    textFieldCorreo.setText("");
                    textFieldCarne.setText("");
                    comboBoxSeccion.setSelectedIndex(0);
                    textFieldTelegramId.setText(""); // Si aplica
                    checkBoxActividad.setSelected(false);

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error al crear usuario: " + exception.getMessage());
                }
            }
        });


        //Para buscar
        buttonBuscar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id= textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());

                if(id==0){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una id de usuario valida");
                    return;
                }

                try{
                    Modeltb_usuarios usuarioencontrado = new Servicetb_usuarios().obtenerUsuarioPorId(id);
                    textFieldNombre.setText(usuarioencontrado.getNombre());
                    textFieldCorreo.setText(usuarioencontrado.getCorreo());
                    comboBoxSeccion.setSelectedItem(usuarioencontrado.getSeccion());
                    textFieldCarne.setText(usuarioencontrado.getCarne());
                    textFieldTelegramId.setText(String.valueOf(usuarioencontrado.getTelegramid()));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al buscar al usurio   "+ex);
                }
            }
        });


        //Para actualizar
        buttonActualizar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        // Recuperamos la id del usuario
                int idusuario = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());

                if (idusuario == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una id de usuario válida");
                    return;
                }

                try {
                    // Obtener el usuario por la id
                    Modeltb_usuarios usuarioexistente = new Servicetb_usuarios().obtenerUsuarioPorId(idusuario);

                    if (usuarioexistente == null) {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado con el ID proporcionado");
                        return;
                    }

                    // Actualizar los datos del usuario con los nuevos datos
                    usuarioexistente.setNombre(textFieldNombre.getText());
                    usuarioexistente.setCorreo(textFieldCorreo.getText());
                    usuarioexistente.setSeccion(comboBoxSeccion.getSelectedItem().toString());
                    usuarioexistente.setCarne(textFieldCarne.getText());

                    if (textFieldTelegramId.getText().isEmpty() || textFieldTelegramId.getText().equals("0")) {
                        usuarioexistente.setTelegramid(null);
                    } else {
                        usuarioexistente.setTelegramid(Long.parseLong(textFieldTelegramId.getText()));
                    }

                    // Manejo del campo activo
                    if (checkBoxActividad.isSelected()) {
                        usuarioexistente.setActivo("1");
                    } else {
                        usuarioexistente.setActivo("0");
                    }

                    // Confirmar la actualización con el usuario
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea actualizar este usuario?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Actualizar los datos en la base de datos
                        new Servicetb_usuarios().guardarOActualizarUsuario(usuarioexistente);
                        JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Actualización cancelada.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + ex.getMessage());
                }
            }
        });

        //Para eliminar un usuario
        buttonEliminar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verificamos si el campo de ID de usuario está vacío
                int idusuario = textFieldIdUsuario.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdUsuario.getText());

                if (idusuario == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una ID de usuario válida");
                    return;
                }

                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar este usuario?", "Confirmación",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        // Llamamos al servicio para eliminar el usuario por su ID
                        boolean eliminado = new Servicetb_usuarios().eliminarUsuario(idusuario);

                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");

                            // Limpiar los campos después de la eliminación
                            textFieldIdUsuario.setText("");
                            textFieldNombre.setText("");
                            textFieldCorreo.setText("");
                            comboBoxSeccion.setSelectedItem("");
                            textFieldCarne.setText("");
                            textFieldTelegramId.setText("");
                            checkBoxActividad.setSelected(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró el usuario con la ID proporcionada");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                }
            }
        });

        //Para cerrar la ventana
        buttonRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                principal frm=new principal();
                frm.setVisible(true);
                frm.setSize(500,400);
            }
        });
    }
}