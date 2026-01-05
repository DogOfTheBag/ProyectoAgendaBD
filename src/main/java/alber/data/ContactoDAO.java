package alber.data;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Con el patrón de diseño DAO, en esta clase basicamente lo que haremos serán todas las operaciones de gestión de
* bases de datos.*/
public class ContactoDAO {

    //INSERT
    public void Insertar(Contacto contacto){
        /*La estructura general de esto será basciamente hacer una función con la operación sql que queramos hacer
        * en la que pongamos la sentencia, conectemos con la base de datos, y usemos un prepareStatement para establecer datos y hacer la operacion*/
        String sql = "INSERT INTO contacto (nombre,direccion,telefono,aficion,pandilla,vacaciones) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection c = conexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            //hago comprobaciones para que los que tienen que tener algo no estén vacios ni null
            ps.setString(1,contacto.getNombre());
            if(contacto.getNombre().isBlank() || contacto.getNombre() == null){
                return;
            }
            ps.setString(2,contacto.getDireccion());
            if(contacto.getDireccion().isBlank() || contacto.getDireccion() == null){
                return;
            }
            ps.setString(3,contacto.getTelefono());
            if(contacto.getTelefono().isBlank() || contacto.getTelefono() == null){
                return;
            }
            ps.setString(4,contacto.getAfición());
            if(contacto.getAfición().isBlank() || contacto.getAfición() == null){
                return;
            }
            ps.setString(5,contacto.getPandilla());
            if(contacto.getPandilla().isBlank() || contacto.getPandilla() == null){
                return;
            }


            //puesto que vacaciones puede ser null, esta forma es mejor para marcar null en sql
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
    /*Como podemos ver, las demás van a ser iguales, la unica complejidad es hacer la sentencia sql deseada, y a partir de ahi
    * darle los parametros que necesite.*/
    //el listado normal va a estar ordenado por nombres alfabeticamente
    public List<Contacto> Listar(){
        String sql = "SELECT * FROM contacto order by  nombre ASC";
        List<Contacto> lista = new ArrayList<>();

        try(Connection c = conexion();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Contacto con = new Contacto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("aficion"),
                    rs.getString("pandilla"),
                    rs.getString("vacaciones")
                    );

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
            if(contacto.getNombre().isBlank() || contacto.getNombre() == null){
                return;
            }
            ps.setString(2,contacto.getDireccion());
            if(contacto.getDireccion().isBlank() || contacto.getDireccion() == null){
                return;
            }
            ps.setString(3,contacto.getTelefono());
            if(contacto.getTelefono().isBlank() || contacto.getTelefono() == null){
                return;
            }
            ps.setString(4,contacto.getAfición());
            if(contacto.getAfición().isBlank() || contacto.getAfición() == null){
                return;
            }
            ps.setString(5,contacto.getPandilla());
            if(contacto.getPandilla().isBlank() || contacto.getPandilla() == null){
                return;
            }

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
    /*lo unico que mysql ordena numeros con order by de forma ascendente , va comparando numero por numero hasta que uno sea menor que otro*/
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

    //ya que estamos haciendo siempre conexiones lo ponemos en una funcion
    private java.sql.Connection conexion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_proyecto_UT02","root","2002");
    }
}
