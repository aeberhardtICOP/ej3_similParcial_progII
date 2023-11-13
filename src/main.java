import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import persistence.Conexion;

public class main {

	public static void main(String[] args) {
		Conexion con = new Conexion();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
		try {
	    connection=con.conectar();
	    String sql="SELECT * FROM integrante;";
	    preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		System.out.println("Número de columnas: " + columnCount);

		for (int i = 1; i <= columnCount; i++) {
		    System.out.println("Nombre columna " + i + ": " + metaData.getColumnName(i));
		}

	}catch(SQLException e) {
		e.printStackTrace();
		
	}

}
	}
