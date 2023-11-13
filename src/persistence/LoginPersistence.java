package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPersistence {
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean buscarUsuario(String nombreUsuario, String contrasenia) {
        try {
            connection = con.conectar();
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombreUsuario);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String contraseniaAlmacenada = resultSet.getString("contrasenia");
                if (contrasenia.equals(contraseniaAlmacenada)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(connection, preparedStatement, resultSet);
        }

        return false;
    }
    public void crearUsuaio(String usu, String pass) {
    	try {
    		connection = con.conectar();
            String sql = "INSERT INTO usuario (usuario, contrasenia)VALUES(?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usu);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    	
    }
    public void editarContraseña(String usu, String pass) {
    	try {
    		connection = con.conectar();
            String sql = "UPDATE usuario SET contrasenia = ? WHERE usuario = ?;"
            		+ "";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, usu);
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    	
    }
    public boolean existeUsuario(String user) {
    	try {
    		connection = con.conectar();
            String sql = "SELECT usuario FROM usuario WHERE usuario ='"+user+"';";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	return true;
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
		return false;
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
