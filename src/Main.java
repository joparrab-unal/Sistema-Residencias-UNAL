import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaResidencias sistema = new SistemaResidencias();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========= SISTEMA DE RESIDENCIAS =========");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Consultar por ID");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Asignar cupos");
            System.out.println("5. Generar datos aleatorios");
            System.out.println("0. Salir");
            System.out.println("==========================================");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese puntaje: ");
                    double puntaje = sc.nextDouble();
                    sistema.registrarConMensaje(id, nombre, puntaje);
                    break;
                case 2:
                    System.out.print("Ingrese ID a buscar: ");
                    int idBuscar = sc.nextInt();
                    sistema.consultarPorID(idBuscar);
                    break;
                case 3:
                    System.out.print("Ingrese ID a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sistema.eliminarEstudiante(idEliminar);
                    break;
                case 4:
                    System.out.print("Cuantos cupos disponibles? ");
                    int cupos = sc.nextInt();
                    sistema.asignarCupos(cupos);
                    break;
                case 5:
                    System.out.print("Cuantos estudiantes generar? ");
                    int cantidad = sc.nextInt();
                    sistema.generarDatosAleatorios(cantidad);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
