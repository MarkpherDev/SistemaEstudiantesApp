package mp.presentation;

import java.util.List;
import java.util.Scanner;
import mp.data.EstudianteDAO;
import mp.domain.Estudiante;

public class SistemaEstudianteApp {

  public static void main(String[] args) {
    EstudianteDAO estudianteDAO = new EstudianteDAO();
    Scanner consola = new Scanner(System.in);
    boolean salir = false;
    while (!salir) {
      try {
        int opcion = mostrarMenu(consola);
        salir = ejecutarOpciones(opcion, consola, estudianteDAO);
      } catch (Exception e) {
        System.out.println("Ocurrio un error: " + e.getMessage());
      } finally {
        System.out.println();
      }
    }
  }

  private static int mostrarMenu(Scanner consola) {
    System.out.print("""
        *** Sistema de Estudiantes ***
        1. Listar Estudiantes
        2. Buscar Estudiante
        3. Agregar Estudiante
        4. Modificar Estudiante
        5. Eliminar Estudiante
        6. Salir
        Elige una opcion:\s""");

    return Integer.parseInt(consola.nextLine());
  }

  private static boolean ejecutarOpciones(int opcion, Scanner consola,
      EstudianteDAO estudianteDAO) {
    boolean salir = false;

    switch (opcion) {
      case 1 -> {
        System.out.println("*** Listar Estudiantes ***");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);
      }
      case 2 -> {
        System.out.println("*** Buscar Estudiante ***");
        System.out.print("Ingrese el id a buscar: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudianteBuscar = new Estudiante(idEstudiante);
        boolean encontrado = estudianteDAO.buscarEstudiantePorId(estudianteBuscar);
        if (encontrado) {
          System.out.println("Estudiante encontrado: " + estudianteBuscar);
        } else {
          System.out.println("No se encontro el estudiante: " + estudianteBuscar);
        }
      }
      case 3 -> {
        System.out.println("*** Nuevo Estudiante ***");
        System.out.print("Ingrese el nombre: ");
        String nombre = consola.nextLine();
        System.out.print("Ingrese el Apellido: ");
        String apellido = consola.nextLine();
        System.out.print("Ingrese el Telefono: ");
        String telefono = consola.nextLine();
        System.out.print("Ingrese el Email: ");
        String email = consola.nextLine();
        Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, telefono, email);
        boolean agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
        if (agregado) {
          System.out.println("Estudiante agregado: " + nuevoEstudiante);
        } else {
          System.out.println("No se pudo agregar el estudiante: " + nuevoEstudiante);
        }
      }
      case 4 -> {
        System.out.println("*** Modificar Estudiante ***");
        System.out.print("Ingrese el id del estudiante: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        System.out.print("Ingrese el Nombre: ");
        String nombre = consola.nextLine();
        System.out.print("Ingrese el Apellido: ");
        String apellido = consola.nextLine();
        System.out.print("Ingrese el Telefono: ");
        String telefono = consola.nextLine();
        System.out.print("Ingrese el Email: ");
        String email = consola.nextLine();
        Estudiante estudianteModificar = new Estudiante(idEstudiante, nombre, apellido, telefono,
            email);
        boolean modificado = estudianteDAO.modificar(estudianteModificar);
        if (modificado) {
          System.out.println("Estudiante modificado: " + estudianteModificar);
        } else {
          System.out.println("No se pudo modificar el estudiante: " + estudianteModificar);
        }
      }
      case 5 -> {
        System.out.println("*** Eliminar Estudiante ***");
        System.out.print("Ingrese el id del estudiante: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudianteEliminar = new Estudiante(idEstudiante);
        boolean eliminado = estudianteDAO.eliminar(estudianteEliminar);
        if (eliminado) {
          System.out.println("Estudiante eliminado: " + estudianteEliminar);
        } else {
          System.out.println("No se pudo eliminar el estudiante: " + estudianteEliminar);
        }
      }
      case 6 -> {
        System.out.println("*** Saliendo del Sistema ***");
        System.out.println("Hasta Pronto!!!");
        salir = true;
      }
      default -> System.out.println("Opcion erronea: " + opcion);
    }

    return salir;
  }
}