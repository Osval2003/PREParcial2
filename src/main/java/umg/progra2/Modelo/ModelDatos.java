package umg.progra2.Model;

import java.sql.Timestamp;

public class ModelDatos {
    private int codigo;
    private String nombre;
    private String apellido;
    private String departamento;
    private Timestamp fechaNacimiento;

    // Constructor vacío
    public ModelDatos() {}

    // Constructor con parámetros
    public ModelDatos(int codigo, String nombre, String apellido, String departamento, Timestamp fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "ModelDatos{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", departamento='" + departamento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
