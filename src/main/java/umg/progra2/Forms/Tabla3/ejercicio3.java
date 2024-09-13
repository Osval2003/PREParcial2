package umg.progra2.Formularios.Ejercicio3;

import umg.progra2.Formularios.Principal.principal;
import umg.progra2.Model.ModelChampions;
import umg.progra2.Servicios.ServiceChampions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class ejercicio3 extends JFrame {
    private JLabel lblTitulo3;
    private JPanel jFormEjercicio3;
    private JButton buttonCrear3;
    private JButton buttonBuscar3;
    private JButton buttonActualizar3;
    private JButton buttonEliminar3;
    private JLabel lblIdEquipo;
    private JTextField textFieldIdEquipo;
    private JTextField textFieldNombre;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblCiudad;
    private JLabel lblEstadio;
    private JLabel lblAñoFundacion;
    private JTextField textFieldFechaFundacion;
    private JLabel lblEntrenador;
    private JTextField textFieldEntrenador;
    private JLabel lblWebOficial;
    private JTextField textFieldWebOficial;
    private JLabel lblFacebook;
    private JTextField textFieldFacebook;
    private JLabel lblTwitter;
    private JTextField textFieldTwitter;
    private JLabel lblInstagram;
    private JTextField textFieldInstagram;
    private JLabel lblPatrocinador;
    private JTextField textFieldPatrocinador;
    private JLabel lblFechaCreacion;
    private JTextField textFieldCreadoEn;
    private JTextField textFieldCiudad;
    private JTextField textFieldEstadio;
    private JTextField textFieldPais;
    private JButton buttonRegresarMenu;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ejercicio3");
        frame.setContentPane(new ejercicio3().jFormEjercicio3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,600);
        frame.setVisible(true);
    }

    public ejercicio3() {//constructor para modificar el Form del ejercicio 3

        // Cambiar la posición y tamaño del lblTitulo
        lblTitulo3.setBounds(145,20,200,50);

        // Cambiar el tamaño de la fuente del lblTitulo
        lblTitulo3.setFont(new Font("Arial", Font.BOLD, 20));

        // Configuramos el contenido del JFrame con el panel jFormEjercicio1
        setContentPane(jFormEjercicio3);
        setTitle("Ejercicio3");
        setSize(700, 600); // Establecer tamaño
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar sólo esta ventana
        setLocationRelativeTo(null); // Centrar la ventana

        //para crear un equipo
        buttonCrear3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    String nombre = textFieldNombre.getText();
                    String pais = textFieldPais.getText();
                    String ciudad = textFieldCiudad.getText();
                    String estadio = textFieldEstadio.getText();
                    String fechaFundacionText = textFieldFechaFundacion.getText();
                    int fundacion = fechaFundacionText.isEmpty() ? 0 : Integer.parseInt(fechaFundacionText);
                    String entrenador = textFieldEntrenador.getText();
                    String webOficial = textFieldWebOficial.getText();
                    String facebook = textFieldFacebook.getText();
                    String twitter = textFieldTwitter.getText();
                    String instagram = textFieldInstagram.getText();
                    String patrocinadorPrincipal = textFieldPatrocinador.getText();

                    // Verificar que todos los campos necesarios estén llenos
                    if (nombre.trim().isEmpty() || pais.trim().isEmpty() || ciudad.trim().isEmpty() ||
                            estadio.trim().isEmpty() || entrenador.trim().isEmpty() || webOficial.trim().isEmpty() ||
                            facebook.trim().isEmpty() || twitter.trim().isEmpty() || instagram.trim().isEmpty() ||
                            patrocinadorPrincipal.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
                        return;
                    }

                    // Crear una instancia del modelo con los datos del formulario
                    ModelChampions nuevoEquipo = new ModelChampions(
                            0, // id_equipo es autogenerado, así que se pone 0
                            nombre,
                            pais,
                            ciudad,
                            estadio,
                            fundacion,
                            entrenador,
                            webOficial,
                            facebook,
                            twitter,
                            instagram,
                            patrocinadorPrincipal,
                            null // creado_en se establece automáticamente por la base de datos
                    );

                    // Crear instancia del servicio
                    ServiceChampions service = new ServiceChampions();

                    // Agregar el nuevo equipo
                    boolean creado = service.agregarEquipo(nuevoEquipo);

                    // Mostrar mensaje de éxito o error
                    if (creado) {
                        JOptionPane.showMessageDialog(null, "Equipo creado exitosamente.");
                        // Limpiar los campos después de crear
                        textFieldNombre.setText("");
                        textFieldPais.setText("");
                        textFieldCiudad.setText("");
                        textFieldEstadio.setText("");
                        textFieldFechaFundacion.setText("");
                        textFieldEntrenador.setText("");
                        textFieldWebOficial.setText("");
                        textFieldFacebook.setText("");
                        textFieldTwitter.setText("");
                        textFieldInstagram.setText("");
                        textFieldPatrocinador.setText("");
                        textFieldCreadoEn.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo crear el equipo.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un año de fundación válido.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Para buscar
        buttonBuscar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Obtener el valor del campo de texto
                    int idEquipo= textFieldIdEquipo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipo.getText());

                    // Crear instancia del servicio
                    ServiceChampions service = new ServiceChampions();

                    // Obtener el equipo por el ID
                    ModelChampions equipoBuscado = service.obtenerEquipoPorId(idEquipo);

                    // Si se encuentra el equipo, llenamos los campos con su información
                    if (equipoBuscado != null) {
                        textFieldNombre.setText(equipoBuscado.getNombre());
                        textFieldPais.setText(equipoBuscado.getPais());
                        textFieldCiudad.setText(equipoBuscado.getCiudad());
                        textFieldEstadio.setText(equipoBuscado.getEstadio());
                        textFieldFechaFundacion.setText(String.valueOf(equipoBuscado.getFundacion()));
                        textFieldEntrenador.setText(equipoBuscado.getEntrenador());
                        textFieldWebOficial.setText(equipoBuscado.getWebOficial());
                        textFieldFacebook.setText(equipoBuscado.getFacebook());
                        textFieldTwitter.setText(equipoBuscado.getTwitter());
                        textFieldInstagram.setText(equipoBuscado.getInstagram());
                        textFieldPatrocinador.setText(equipoBuscado.getPatrocinadorPrincipal());
                        textFieldCreadoEn.setText(String.valueOf(equipoBuscado.getCreadoEn()));
                    } else {
                        // Si no se encuentra el equipo, mostramos un mensaje
                        JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Para actualizar un equipo
        buttonActualizar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el valor del campo de texto
                    int idEquipo = textFieldIdEquipo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipo.getText());

                    if (idEquipo == 0) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese una ID de equipo válida");
                        return;
                    }

                    // Obtener los valores de los campos de texto
                    String nombre = textFieldNombre.getText();
                    String pais = textFieldPais.getText();
                    String ciudad = textFieldCiudad.getText();
                    String estadio = textFieldEstadio.getText();
                    int fundacion = textFieldFechaFundacion.getText().isEmpty() ? 0 : Integer.parseInt(textFieldFechaFundacion.getText());
                    String entrenador = textFieldEntrenador.getText();
                    String webOficial = textFieldWebOficial.getText();
                    String facebook = textFieldFacebook.getText();
                    String twitter = textFieldTwitter.getText();
                    String instagram = textFieldInstagram.getText();
                    String patrocinadorPrincipal = textFieldPatrocinador.getText();
                    // La fecha de creación no se actualiza
                    Timestamp creadoEn = textFieldCreadoEn.getText().isEmpty() ? null : Timestamp.valueOf(textFieldCreadoEn.getText());

                    // Crear una instancia del modelo con los datos del formulario
                    ModelChampions equipoActualizado = new ModelChampions(
                            idEquipo,
                            nombre,
                            pais,
                            ciudad,
                            estadio,
                            fundacion,
                            entrenador,
                            webOficial,
                            facebook,
                            twitter,
                            instagram,
                            patrocinadorPrincipal,
                            creadoEn
                    );

                    // Confirmación antes de actualizar
                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Estás seguro de que deseas actualizar este equipo?", "Confirmación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Crear instancia del servicio
                        ServiceChampions service = new ServiceChampions();

                        // Actualizar el equipo
                        boolean actualizado = service.actualizarEquipo(equipoActualizado);

                        // Mostrar mensaje de éxito o error
                        if (actualizado) {
                            JOptionPane.showMessageDialog(null, "Equipo actualizado exitosamente.");
                            // Limpiar los campos después de actualizar
                            textFieldIdEquipo.setText("");
                            textFieldNombre.setText("");
                            textFieldPais.setText("");
                            textFieldCiudad.setText("");
                            textFieldEstadio.setText("");
                            textFieldFechaFundacion.setText("");
                            textFieldEntrenador.setText("");
                            textFieldWebOficial.setText("");
                            textFieldFacebook.setText("");
                            textFieldTwitter.setText("");
                            textFieldInstagram.setText("");
                            textFieldPatrocinador.setText("");
                            textFieldCreadoEn.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar el equipo.");
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un año de fundación válido.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //Para Eliminar
        buttonEliminar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor del campo de texto
                int idEquipo = textFieldIdEquipo.getText().isEmpty() ? 0 : Integer.parseInt(textFieldIdEquipo.getText());

                if (idEquipo == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una ID de equipo válida");
                    return;
                }
                // Confirmación antes de eliminar
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar este equipo?", "Confirmación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try{
                        // Llamamos al servicio para eliminar el equipo por su ID
                        boolean eliminado = new ServiceChampions().eliminarEquipo(idEquipo);

                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Equipo eliminado exitosamente.");

                            // Limpiar los campos después de eliminar
                            textFieldIdEquipo.setText("");
                            textFieldNombre.setText("");
                            textFieldPais.setText("");
                            textFieldCiudad.setText("");
                            textFieldEstadio.setText("");
                            textFieldFechaFundacion.setText("");
                            textFieldEntrenador.setText("");
                            textFieldWebOficial.setText("");
                            textFieldFacebook.setText("");
                            textFieldTwitter.setText("");
                            textFieldInstagram.setText("");
                            textFieldPatrocinador.setText("");
                            textFieldCreadoEn.setText("");

                        }else{
                            JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                        }

                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Id del equipo no valida"+ex);
                    }
                }

            }
        });
        buttonRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                principal frm= new principal();
                frm.setVisible(true);
                frm.setLocationRelativeTo(null);
                frm.setSize(500,400);

            }
        });
    }
}

