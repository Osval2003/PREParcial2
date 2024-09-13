package umg.progra2.Model;

public class Modeltb_usuarios {
    private int idusuario;
    private String carne;
    private String nombre;
    private String correo;
    private String seccion;
    private Long telegramid;
    private String activo;

    // Constructor vacío
    public Modeltb_usuarios() {}

    // Constructor con todos los campos
    public Modeltb_usuarios(int idusuario, String carne, String nombre, String correo, String seccion, Long telegramid, String activo) {
        this.idusuario = idusuario;
        this.carne = carne;
        this.nombre = nombre;
        this.correo = correo;
        this.seccion = seccion;
        this.telegramid = telegramid;
        this.activo = activo;
    }

    // Getters y Setters
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Long getTelegramid() {
        return telegramid;
    }

    public void setTelegramid(Long telegramid) {
        this.telegramid = telegramid;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
