package alber.data;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    //INSERT
    public void Insertar(Contacto contacto){
        String sql = "INSERT INTO contacto (nombre,direccion,telefono,aficion,pandilla,vacaciones) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection c = conexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,contacto.getNombre());
            ps.setString(2,contacto.getDireccion());
            ps.setString(3,contacto.getTelefono());
            ps.setString(4,contacto.getAfición());
            ps.setString(5,contacto.getPandilla());

            if (contacto.getVacaciones() == null || contacto.getVacaciones().isBlank()) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, contacto.getVacaciones());
            }

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al insertar contacto: " + e.getMessage());
        }
    }
    //el listado normal va a estar ordenado por nombres alfabeticamente
    public List<Contacto> Listar(){
        String sql = "SELECT * FROM contacto order by  nombre ASC";
        List<Contacto> lista = new ArrayList<>();

        try(Connection c = conexion();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Contacto con = new Contacto(rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("aficion"),
                    rs.getString("pandilla"),
                    rs.getString("vacaciones"));

                    lista.add(con);
                }
        }catch (SQLException e){
            System.out.println("Error al listar contactos: " + e.getMessage());
        }
        return lista;
    }

    //MODIFICAR (lo voy a poner por introducir el teléfono de la persona que quieras cambiar)
    public void Modificar(String telefonoViejo, Contacto contacto){
        String sql = "UPDATE contacto SET nombre = ?, direccion = ?, telefono=?, aficion = ?, pandilla = ?, vacaciones = ? WHERE telefono=?";

        try (Connection c = conexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,contacto.getNombre());
            ps.setString(2,contacto.getDireccion());
            ps.setString(3,contacto.getTelefono());
            ps.setString(4,contacto.getAfición());
            ps.setString(5,contacto.getPandilla());

            if (contacto.getVacaciones() == null || contacto.getVacaciones().isBlank()) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, contacto.getVacaciones());
            }

            ps.setString(7,telefonoViejo);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al modificar contacto: " + e.getMessage());
        }
    }

    //el de ordenar telefonos es igual por haber puesto que es un varchar y no un long
    public List<Contacto> ListarTelefonos(){
        String sql = "SELECT * FROM contacto order by telefono ASC";
        List<Contacto> lista = new ArrayList<>();

        try(Connection c = conexion();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Contacto con = new Contacto(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("aficion"),
                        rs.getString("pandilla"),
                        rs.getString("vacaciones"));

                lista.add(con);
            }
        }catch (SQLException e){
            System.out.println("Error al listar contactos: " + e.getMessage());
        }
        return lista;
    }

    public void delete(String telefono){
        String sql = "Delete from contacto where telefono=?";

        try (Connection c = conexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,telefono);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error al borrar contacto: " + e.getMessage());
        }
    }

    private java.sql.Connection conexion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_proyecto_UT02","root","2002");
    }
}
