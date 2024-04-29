package com.jeska.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.jeska.conexion.Conexion.getConexion;

import com.jeska.dominio.Estudiante;

//DAO Data Access Object
public class EstudianteDAO {
	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> estudiantes = new ArrayList<>();
		PreparedStatement ps;
		ResultSet rs;
		Connection con = getConexion();
		String sql = "SELECT * FROM  estudiante ORDER BY id_estudiante";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				var estudiante = new Estudiante();
				estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
				estudiante.setNombre(rs.getString("nombre"));
				estudiante.setApellido(rs.getString("apellido"));
				estudiante.setTelefono(rs.getString("telefono"));
				estudiante.setEmail(rs.getString("email"));
				estudiantes.add(estudiante);
			}
		} catch (Exception e) {
			System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
		} finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
			}
		}
		return estudiantes;
	}
	
	//findById
	public boolean buscarEstudiantePorId(Estudiante estudiante) {
		PreparedStatement ps;
		ResultSet rs;
		Connection con = getConexion();
		String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, estudiante.getIdEstudiante());
			rs = ps.executeQuery();
			if(rs.next()) {
				estudiante.setNombre(rs.getString("nombre"));
				estudiante.setApellido(rs.getString("apellido"));
				estudiante.setTelefono(rs.getString("telefono"));
				estudiante.setEmail(rs.getString("email"));
				return true;
			}
		}catch(Exception e) {
			System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
		}finally {
			try {
				con.close();
			}catch (Exception e) {
				System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
			}
		}
		return false;	
	}
	
	public boolean agregarEstudiante(Estudiante estudiante) {
		PreparedStatement ps;
		Connection con = getConexion();
		String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, estudiante.getNombre());
			ps.setString(2, estudiante.getApellido());
			ps.setString(3, estudiante.getTelefono());
			ps.setString(4, estudiante.getEmail());
			ps.execute(); 
			return true;
		}catch(Exception e) {
			System.out.println("Error al insertar estudiante: " + e.getMessage());
		}finally {
			try {
				con.close();
			}catch (Exception e) {
				System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
			}
		}
		return false;
	}
	
	
	public boolean modificarEstudiante(Estudiante estudiante) {
		PreparedStatement ps;
		Connection con = getConexion();
		String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? "
				+ "WHERE id_estudiante = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, estudiante.getNombre());
			ps.setString(2, estudiante.getApellido());
			ps.setString(3, estudiante.getTelefono());
			ps.setString(4, estudiante.getEmail());
			ps.setInt(5, estudiante.getIdEstudiante());
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("Error al modificar estudiante: " + e.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return false;
	}
	
	public boolean eliminarEstudiante(Estudiante estudiante) {
		PreparedStatement ps;
		Connection con 	= getConexion();
		String sql = "DELETE FROM estudiante WHERE id_estudiante = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, estudiante.getIdEstudiante());
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("Error al intentar eliminar estudiante: " + e.getMessage());
		}finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		var estudianteDAO = new EstudianteDAO();
		
		//-------------------LISTAR LOS ESTUDIANTES------------------------------------------------------
		/*
				System.out.println(" ");
				System.out.println("Listado de estudiantes");
				List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
				estudiantes.forEach(System.out::println);
				*/
	    //-----------------------------------------------------------------------------------------------
		
		//------------------AGREGAR ESTUDIANTE-----------------------------------------------------------
		/*
		var nuevoEstudiante = new Estudiante("Iker", "Casillas", "7671597835", "iker.cas@yahoo.com");
		var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
		if(agregado)
			System.out.println("Estudiante agregado: " + nuevoEstudiante);
		else 
			System.out.println("No se agrego un nuevo estudiante: " + nuevoEstudiante);
			*/
		//-----------------------------------------------------------------------------------------------
		
		//------------------MODIFICAR ESTUDIANTE--------------------------------------------------------
		/*
		var estudianteModificar = new Estudiante(2, "Cristiano Ronaldo", "Dos Santos Aveiro", 
				"7671150714", "cris.ro@yahoo.com");
		var modificado = estudianteDAO.modificarEstudiante(estudianteModificar);
		if(modificado)
			System.out.println("Estudiante modificado: " + estudianteModificar);
		else
			System.out.println("No se modifico el estudiante: " + estudianteModificar);
			*/
		//-----------------------------------------------------------------------------------------------
		
		
		//-------------------BUSCAR ESTUDIANTES POR ID---------------------------------------------------
		/*
		var estudiante1 = new Estudiante(7);
		System.out.println("Estudiante antes de la busqueda: " + estudiante1);
		var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante1);
		if(encontrado)
			System.out.println("Estudiante encontrado: " + estudiante1);
		else 
			System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
		 */
		//-----------------------------------------------------------------------------------------------
				
		//-------------------ELIMINAR ESTUDIANTES--------------------------------------------------------
		/* 
		var estudianteEliminar = new Estudiante(7);
		 var eliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
		 if(eliminado)
			 System.out.println("Estudiante eliminado: " + estudianteEliminar);
		 else 
			 System.out.println("No se elimino el estudiante: " + estudianteEliminar);
			 */
		//-----------------------------------------------------------------------------------------------
				
		 //-------------------LISTAR LOS ESTUDIANTES------------------------------------------------------
		 
		System.out.println(" ");
		System.out.println("Listado de estudiantes");
		List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
		estudiantes.forEach(System.out::println);
		  
		 //-----------------------------------------------------------------------------------------------
		
	}  
} 


