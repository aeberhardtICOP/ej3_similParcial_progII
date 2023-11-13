package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Cargos;
import model.Empleado;
import model.Funcionario;
import model.Funciones;
import model.Integrante;
import model.Organismo;

public class IntegrantePersistence {
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public void crearIntegrante(Integrante inte, String circunscripcion) {
    	try {
    		connection = con.conectar();
    		
            String sql = "INSERT INTO integrante (nombre, apellido, nro_legajo, edad, dni, año_nacimiento,"
            		+ " tipo_integrante, es_afiliado, fecha_ingreso, funcion, fecha_posecion, acuerdo_legislativo,"
            		+ " cargo, id_organismo, circunscripcion)"
            		+ "VALUES(?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inte.getNombre());
            preparedStatement.setString(2, inte.getApellido() );
            preparedStatement.setLong(3, inte.getNroLegajo());
            preparedStatement.setInt(4, inte.getEdad());
            preparedStatement.setLong(5, inte.getDni());
            preparedStatement.setInt(6, inte.getAñoNacimiento());
            
            if(inte instanceof Funcionario) {
            	Funcionario fun = (Funcionario)inte;
            	preparedStatement.setString(7, "Funcionario");
            	preparedStatement.setNull(8, java.sql.Types.BOOLEAN);
            	preparedStatement.setDate(9, null);
            	preparedStatement.setString(10, null);
            	
            	preparedStatement.setDate(11, (java.sql.Date) fun.getFechaPosecion());
            	preparedStatement.setBoolean(12, fun.isAcuerdoLegislatico());
            	preparedStatement.setString(13, fun.getCargo().name());
            }else {
            	Empleado emp = (Empleado)inte;
            	preparedStatement.setString(7, "Empleado");
            	preparedStatement.setBoolean(8, emp.isEsAfiliado());
            	preparedStatement.setDate(9, (java.sql.Date) emp.getFechaIngreso());
            	preparedStatement.setString(10, emp.getFuncion().name());
            	
            	preparedStatement.setDate(11, null);
            	preparedStatement.setNull(8, java.sql.Types.BOOLEAN);
            	preparedStatement.setString(13, null);
            }
            
            preparedStatement.setLong(14, inte.getOrganismo().getId());
            preparedStatement.setString(15,circunscripcion);
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
    public HashMap<Long, Integrante> traerIntegrantes() {
    	try {
    		HashMap<Long, Integrante>integrantes=new HashMap();
    		connection = con.conectar();
            String sql = "SELECT * FROM integrante;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	 Long id = resultSet.getLong("id");
                 String nombre = resultSet.getString("nombre");
                 String apellido = resultSet.getString("apellido");
                 int nroLegajo = resultSet.getInt("nro_legajo");
                 int edad = resultSet.getInt("edad");
                 int dni = resultSet.getInt("dni");
                 int anioNacimiento = resultSet.getInt("año_nacimiento");
                 String tipo = resultSet.getString("tipo_integrante");
                 Long id_organismo = resultSet.getLong("id_organismo");
                 if(tipo.equals("Funcionario")) {
                	 Date fechaPosecion=resultSet.getDate("fecha_posecion");
                	 boolean acuerdoLegislativo=resultSet.getBoolean("acuerdo_legislativo");
                	 String cargo=resultSet.getString("cargo");
                	 java.sql.Date sqlDate = new java.sql.Date(fechaPosecion.getTime());
                	 Funcionario fun = new Funcionario(id, nroLegajo, anioNacimiento, nombre, apellido, dni, edad,
                			 buscarOrganismo(id_organismo), acuerdoLegislativo, stringACargos(cargo), sqlDate);
                	 integrantes.put(fun.getId(), fun);
                 }else{
                	 boolean esAfiliado = resultSet.getBoolean("es_afiliado");
                	 Date fechaIngreso = resultSet.getDate("fecha_ingreso");
                	 String funcion = resultSet.getString("funcion");
                	 java.sql.Date sqlDate = new java.sql.Date(fechaIngreso.getTime());
                	 Empleado emp = new Empleado (id, nroLegajo, anioNacimiento,  nombre, apellido, dni, edad,
                				buscarOrganismo(id_organismo), esAfiliado, sqlDate,  stringAFunciones(funcion));
                	 integrantes.put(emp.getId(), emp);
                 }
               
               
            }
            return integrantes;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    /*public void editar(Cliente cliente, int id) {
    	try {
    		connection = con.conectar();
    		
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, mail = ?,"
            		+ " genero = ?, dni = ?, domicilio = ?, telefono = ?, baja = ? "
            		+ "WHERE id_cliente = ?";
;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getMail());
            preparedStatement.setString(4, cliente.getGenero());
            preparedStatement.setInt(5, cliente.getDni());
            preparedStatement.setString(6, cliente.getDomicilio());
            preparedStatement.setInt(7, cliente.getTelefono());
            preparedStatement.setBoolean(8, false);
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    public void baja(int id) {
    	try {
    		connection = con.conectar();
    		
            String sql = "UPDATE cliente SET baja = true WHERE id_cliente = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    */
    public int ultimoId() {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT MAX(id) AS max_id FROM integrante;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            } else { 
                return -1; 
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return -1;
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    	
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
    
    public Organismo buscarOrganismo(Long idOrganismo) {
		try {
			connection = con.conectar();
            String sql = "SELECT * FROM organismo WHERE id= ?;";
            preparedStatement.setLong(1, idOrganismo);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	String nombre = resultSet.getString("nombre");
            	Date fecha_creacion = resultSet.getDate("fecha_creacion");
            	String domicilio = resultSet.getString("domicilio");
            	Organismo org = new Organismo();
            	org.setId(idOrganismo);
            	org.setDomicilio(domicilio);
            	org.setFechaCreaciom(fecha_creacion);
            	org.setNombre(nombre);
            	return org;
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    
    public Cargos stringACargos(String cargo) {
    	if(cargo.equals("INICIAL")) {
    		return Cargos.INICIAL;
    	}else if(cargo.equals("SUPERIOR")) {
    		return Cargos.SUPERIOR;
    	}
    	return null; 
    }
    public Funciones stringAFunciones(String funcion) {
    	if(funcion.equals("ADMINISTRATIVO")) {
    		return Funciones.ADMINISTRATIVO;
    	}else if(funcion.equals("ORDENANZA")) {
    		return Funciones.ORDENANZA;
    	}else if(funcion.equals("ESPECIALIZADO")){
    		return Funciones.ESPECIALIZADO;
    	}else {
    		return null; 
    	}
    }
}
