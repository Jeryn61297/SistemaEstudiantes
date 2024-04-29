package com.jeska.presentacion;

import java.util.Scanner;
import com.jeska.datos.EstudianteDAO;
import com.jeska.dominio.Estudiante;

public class SistemaEstudiantesApp {
	public static void main(String[] args) {
		var salir = false;
		var consola = new Scanner(System.in);

		// se crea una instancia de clase servicio
		var estudianteDAO = new EstudianteDAO();
		while (!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOperaciones(consola, estudianteDAO);
			} catch (Exception e) {
				System.out.println("Ocurrio un error al ejecutar operacion: " + e.getMessage());
			}
			System.out.println(" ");
		} // fin while
	}

	// METODO 1
	private static void mostrarMenu() {
		System.out.println("-------SISTEMA DE ESTUDIANTES-------");
		System.out.println("1.- Listar estudiantes \n2.- Buscar estudiante \n3.- Agregar estudiante"
				+ "\n4.- Modificar estudiante \n5.- Eliminar estudiante \n6.- Salir \n--Elige una opcion");
	}

	// METODO 2
	private static boolean ejecutarOperaciones(Scanner consola, EstudianteDAO estudianteDAO) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion) {
		// LISTAR ESTUDIANTES
		case 1: {
			System.out.println("Listado de estudiantes");
			var estudiantes = estudianteDAO.listarEstudiantes();
			estudiantes.forEach(System.out::println);
			break;
		}
		// BUSCAR ESTUDIANTE
		case 2: {
			System.out.println("Introduce el id_estudiante a buscar: ");
			var id_estudiante = Integer.parseInt(consola.nextLine());
			// idEStudiante
			var estudiante = new Estudiante(id_estudiante);
			var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
			if (encontrado)
				System.out.println("Estudiante encontrado: " + estudiante);
			else
				System.out.println("Estudiante no encontrado: " + estudiante);
			break;
		}
		// AGREGAR ESTUDIANTE
		case 3: {
			System.out.println("Agregar estudiante");
			System.out.print("Nombre: ");
			var nombre = consola.nextLine();
			System.out.print("Apellido: ");
			var apellido = consola.nextLine();
			System.out.print("Telefono: ");
			var telefono = consola.nextLine();
			System.out.print("email: ");
			var email = consola.nextLine();
			// CREAR EL OBJETO ESTUDIANTE (SIN EL ID)
			var estudiante = new Estudiante(nombre, apellido, telefono, email);
			var agregado = estudianteDAO.agregarEstudiante(estudiante);
			if (agregado)
				System.out.println("Estudiante agregado: " + estudiante);
			else
				System.out.println("Estudiante no agregado: " + estudiante);
			break;

		}
		// MODIFICAR ESTUDIANTE
		case 4: {
			System.out.println("Modificar estudiante");
			System.out.println("Id estudiante: ");
			var idEstudiante = Integer.parseInt(consola.nextLine());
			System.out.print("Nombre: ");
			var nombre = consola.nextLine();
			System.out.print("Apellido: ");
			var apellido = consola.nextLine();
			System.out.print("Telefono: ");
			var telefono = consola.nextLine();
			System.out.print("Email: ");
			var email = consola.nextLine();

			// CREAR OBJETO ESTUDIANTE A MODIFICAR
			var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
			var modificado = estudianteDAO.modificarEstudiante(estudiante);
			if (modificado)
				System.out.println("El estudiante fue modificado: " + estudiante);
			else
				System.out.println("el estudiante no fue modificado: " + estudiante);
			break;
		}
		// ELIMINAR ESTUDIANTE
		case 5: {
			System.out.println("Eliminar estudiante");
			System.out.println("Id estudiante");
			var idEstudiante = Integer.parseInt(consola.nextLine());
			var estudiante = new Estudiante(idEstudiante);
			var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
			if (eliminado)
				System.out.println("estudiante eliminado: " + estudiante);
			else
				System.out.println("El estudiante no fue eliminado: " + estudiante);
			break;
		}
		// SALIR
		case 6: {
			System.out.println("Hasta pronto");
			salir = true;
			break;
		}
		default:
			System.out.println("Opcion no reconocida");
		}
		return salir;
	}
}
