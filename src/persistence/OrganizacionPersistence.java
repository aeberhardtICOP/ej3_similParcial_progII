package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Organismo;

public class OrganizacionPersistence {
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public void crearOrganismo(Organismo org) {
    	try {
    		connection = con.conectar();
    		
            String sql = "INSERT INTO organismo (nombre, fecha_creacion, domicilio) "
            		+ "VALUES(?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, org.getNombre());
            preparedStatement.setDate(2, new java.sql.Date(org.getFechaCreaciom().getTime()));
            preparedStatement.setString(3, org.getDomicilio());
            
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
    public HashMap<Long, Organismo> traerOrganismos() {
    	try {
    		HashMap<Long, Organismo>organismos=new HashMap();
    		connection = con.conectar();
            String sql = "SELECT * FROM organismo;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	 Long idOrganism = resultSet.getLong("id_organismo");
                 String nombre = resultSet.getString("nombre");
                 Date fechaCreacion = resultSet.getDate("fecha_creacion");
                 java.sql.Date sqlDate = new java.sql.Date(fechaCreacion.getTime());
                 String domicilio = resultSet.getString("domicilio");
                 
               Organismo org = new Organismo();
               org.setId(idOrganism);
               org.setNombre(nombre);
               org.setFechaCreaciom(sqlDate);
               org.setDomicilio(domicilio);
               organismos.put(idOrganism, org);
               
            }
            return organismos;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
   
    
    public Long ultimoId() {
    	try {
    		connection = con.conectar();
            String sql = "SELECT MAX(id_organismo) AS max_id FROM organismo;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("max_id");
            } else { 
                return -1L; 
            }
    	}catch(SQLException e) {
    		
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
		return -1L;
    }
    
    private void cerrarRecursos(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
