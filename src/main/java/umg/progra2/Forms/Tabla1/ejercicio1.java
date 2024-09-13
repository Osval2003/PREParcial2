package umg.progra2.Formularios.Tabla1;

import umg.progra2.Formularios.Principal.principal;
import umg.progra2.Model.ModelDatos;
import umg.progra2.Servicios.ServiceDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ejercicio1 extends JFrame {
    private JLabel lblTitulo1;
    private JPanel jFormEjercicio1;
    private JButton buttonCrear1;
    private JButton buttonBuscar1;
    private JButton buttonActualizar1;
    private JButton buttonEliminar1;
    private JButton buttonRegresaralMenu1;
    private JLabel lblCodigo1;
    private JTextField textFieldCodigo1;
    private JLabel lblNombre1;
    private JTextField textFieldNombre1;
    private JLabel lblApellido;
    private JTextField textFieldApellido1;
    private JLabel lblDepartamento1;
    private JComboBox comboBoxDepertamento1;
    private JLabel lblFechaNacimiento1;
    private JTextField textFieldFechaNacimiento1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio1");
        frame.setContentPane(new ejercicio1().jFormEjercicio1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);//tamaño de la ventana
        frame.setVisible(true);
    }

    public ejercicio1() {//contructor para modificar el ejericio 1



        // Cambiar la posición y tamaño del lblTitulo
        lblTitulo1.setBounds(145,20,200,50);

        // Cambiar el tamaño de la fuente del lblTitulo
        lblTitulo1.setFont(new Font("Arial", Font.BOLD, 20));

        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jFormEjercicio1);
        setTitle("Ejercicio 1");
        setSize(600, 500); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana



        // Agregar todos los departamentos de Guatemala al JComboBox
        String[] departamentos = {
                "","Guatemala", "Alta Verapaz", "Baja Verapaz", "Chimaltenango", "Chiquimula",
                "El Progreso", "Escuintla", "Huehuetenango", "Izabal", "Jalapa",
                "Jutiapa", "Petén", "Quetzaltenango", "Quiché", "Retalhuleu",
                "Sacatepéquez", "San Marcos", "Santa Rosa", "Solalá", "Suchitepéquez",
                "Totonicapán", "Zacapa"
        };

        for (String depto : departamentos) {//for each para recorrer cada uno de los departamentos
            comboBoxDepertamento1.addItem(depto);
        }

        //crear un registro
        buttonCrear1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        ModelDatos datos = new ModelDatos();
                datos.setNombre(textFieldNombre1.getText());
                datos.setApellido(textFieldApellido1.getText());
                datos.setDepartamento(comboBoxDepertamento1.getSelectedItem().toString());
                datos.setFechaNacimiento(new Timestamp(System.currentTimeMillis()));

                if (textFieldNombre1.getText().trim().isEmpty() && textFieldApellido1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre y el apellido no deben estar vacíos");
                    return;
                }

                // Convertir el texto de la fecha a Timestamp
                try {
                    Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldFechaNacimiento1.getText());
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    datos.setFechaNacimiento(timestamp);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha no válido. Use yyyy-MM-dd HH:mm:ss");
                    return;
                }

                try {
                    new ServiceDatos().insertar(datos);
                    JOptionPane.showMessageDialog(null, "Efectivamente ha nacido con éxito");

                    // Limpiar los campos después de crear el registro
                    textFieldNombre1.setText("");
                    textFieldApellido1.setText("");
                    comboBoxDepertamento1.setSelectedIndex(0);
                    textFieldFechaNacimiento1.setText("");

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Aún falta un mes para que nazca. " + exception);
                }
            }

        });

        //Buscar en el registro
        buttonBuscar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cod= textFieldCodigo1.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo1.getText());

                if(cod==0){
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un codigo valido");
                    return;
                }

                try{
                    ModelDatos registroencontrado= new ServiceDatos().obtenerPorId(cod);
                    textFieldNombre1.setText(registroencontrado.getNombre());
                    textFieldApellido1.setText(registroencontrado.getApellido());
                    comboBoxDepertamento1.setSelectedItem((registroencontrado.getDepartamento()));
                    textFieldFechaNacimiento1.setText(registroencontrado.getFechaNacimiento().toString());

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Error al buscar el registro"+exception);
                }
            }
        });

        //Actualizar registro
        buttonActualizar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recuperar el código del registro a actualizar
                int cod = textFieldCodigo1.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo1.getText());

                if (cod == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.");
                    return;
                }

                // Confirmación antes de actualizar
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas actualizar este registro?", "Confirmación",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        // Obtener el registro existente por ID
                        ModelDatos registroExistente = new ServiceDatos().obtenerPorId(cod);

                        if (registroExistente == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró un registro con el código proporcionado.");
                            return;
                        }

                        // Actualizar los campos del registro existente con los nuevos datos
                        registroExistente.setNombre(textFieldNombre1.getText());
                        registroExistente.setApellido(textFieldApellido1.getText());
                        registroExistente.setDepartamento(comboBoxDepertamento1.getSelectedItem().toString());

                        // Convertir el texto de la fecha a Timestamp
                        try {
                            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(textFieldFechaNacimiento1.getText());
                            Timestamp timestamp = new Timestamp(parsedDate.getTime());
                            registroExistente.setFechaNacimiento(timestamp);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Formato de fecha no válido. Use yyyy-MM-dd HH:mm:ss");
                            return;
                        }

                        // Actualizar el registro en la base de datos
                        new ServiceDatos().actualizar(registroExistente);
                        JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el registro: " + exception.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Actualización cancelada.");
                }
            }
        });

        //Eliminar registro
        buttonEliminar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Recuperar el código del registro a eliminar
                int cod = textFieldCodigo1.getText().isEmpty() ? 0 : Integer.parseInt(textFieldCodigo1.getText());

                if (cod == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código válido.");
                    return;
                }

                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar este registro?", "Confirmación",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        // Verificar si el registro existe antes de eliminarlo
                        ModelDatos registroExistente = new ServiceDatos().obtenerPorId(cod);

                        if (registroExistente == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró un registro con el código proporcionado.");
                            return;
                        }

                        // Eliminar el registro de la base de datos
                        new ServiceDatos().eliminar(cod);
                        JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");

                        // Limpiar los campos después de la eliminación
                        textFieldCodigo1.setText("");
                        textFieldNombre1.setText("");
                        textFieldApellido1.setText("");
                        comboBoxDepertamento1.setSelectedIndex(0);
                        textFieldFechaNacimiento1.setText("");

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + exception.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                }
            }
        });

        //Para cerrar la ventana
        buttonRegresaralMenu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                principal frm= new principal();
                frm.setVisible(true);
                frm.setSize(500,400);
            }
        });
    }
}
