package alber.data;

public class Contacto {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String afición;
    private String pandilla;
    private String vacaciones;

    //Constructor sin id para crear personas en el insert y update
    public Contacto(String nombre, String direccion, String telefono, String afición, String pandilla, String vacaciones) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.afición = afición;
        this.pandilla = pandilla;
        this.vacaciones = vacaciones;
    }

    //constructor para listados con id incluido
    public Contacto(int id, String nombre, String direccion, String telefono, String afición, String pandilla, String vacaciones) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.afición = afición;
        this.pandilla = pandilla;
        this.vacaciones = vacaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAfición() {
        return afición;
    }

    public void setAfición(String afición) {
        this.afición = afición;
    }

    public String getPandilla() {
        return pandilla;
    }

    public void setPandilla(String pandilla) {
        this.pandilla = pandilla;
    }

    public String getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(String vacaciones) {
        this.vacaciones = vacaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID = " + id  +  ", ");
        sb.append("Nombre = " + nombre + ", ");
        sb.append("Direccion = " + direccion + ", ");
        sb.append("Telefono = " + telefono + ", ");
        sb.append("Aficion = " + afición + ", ");
        sb.append("Pandilla = " + pandilla + ", ");
        sb.append("Vacaciones = " + vacaciones + "\n");
        return sb.toString();

    }
}