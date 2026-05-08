import java.util.Random;

public class Benchmarks {
    public static void main(String[] args) {
        int[] tamanos = {10000, 50000, 100000};
        int repeticiones = 10;
        String[] nombres = {"Ana", "Luis", "Maria", "Carlos", "Sofia",
                            "Juan", "Laura", "Pedro", "Camila", "Andres"};

        System.out.println("=======================================================================");
        System.out.println("         BENCHMARKS DE RENDIMIENTO - SISTEMA DE RESIDENCIAS");
        System.out.println("=======================================================================");
        System.out.println("Operaciones rapidas promediadas sobre " + repeticiones + " ejecuciones.\n");

        System.out.printf("%-10s | %-15s | %-18s | %-18s | %-15s%n",
            "N", "Insercion(ms)", "Busqueda(us)", "Eliminacion(us)", "Asignacion(ms)");
        System.out.println("-----------------------------------------------------------------------");

        for (int n : tamanos) {
            SistemaResidencias sistema = new SistemaResidencias();
            Random rand = new Random(42);

            long inicio = System.nanoTime();
            for (int i = 1; i <= n; i++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                double puntaje = rand.nextDouble() * 100.0;
                sistema.registrarEstudiante(i, nombre, puntaje);
            }
            long tiempoInsercion = System.nanoTime() - inicio;

            long sumaBusqueda = 0;
            for (int r = 0; r < repeticiones; r++) {
                int idBuscar = rand.nextInt(n) + 1;
                inicio = System.nanoTime();
                sistema.buscarPorID(idBuscar);
                sumaBusqueda += System.nanoTime() - inicio;
            }
            double tiempoBusqueda = sumaBusqueda / (double) repeticiones;

            long sumaEliminacion = 0;
            for (int r = 0; r < repeticiones; r++) {
                int idEliminar = n - r;
                inicio = System.nanoTime();
                sistema.eliminarEstudianteSilencioso(idEliminar);
                sumaEliminacion += System.nanoTime() - inicio;
            }
            double tiempoEliminacion = sumaEliminacion / (double) repeticiones;

            SistemaResidencias sistemaAsignacion = new SistemaResidencias();
            Random rand2 = new Random(42);
            for (int i = 1; i <= n; i++) {
                String nombre = nombres[rand2.nextInt(nombres.length)];
                double puntaje = rand2.nextDouble() * 100.0;
                sistemaAsignacion.registrarEstudiante(i, nombre, puntaje);
            }
            inicio = System.nanoTime();
            sistemaAsignacion.asignarCuposSilencioso(n / 2);
            long tiempoAsignacion = System.nanoTime() - inicio;

            System.out.printf("%-10d | %-15.2f | %-18.2f | %-18.2f | %-15.2f%n",
                n,
                tiempoInsercion / 1_000_000.0,
                tiempoBusqueda / 1_000.0,
                tiempoEliminacion / 1_000.0,
                tiempoAsignacion / 1_000_000.0);
        }

        System.out.println("=======================================================================");
        System.out.println("us = microsegundos | ms = milisegundos");
        System.out.println("\nComplejidades teoricas:");
        System.out.println("  Insercion N elementos:   O(N log N) en AVL, O(N^2) peor caso BST");
        System.out.println("  Busqueda por ID (BST):   O(N) peor caso (arbol degenerado)");
        System.out.println("  Eliminacion (ambos):     O(log N) en AVL, O(N) peor caso BST");
        System.out.println("  Asignacion K cupos:      O(K log N) via AVL");
        System.out.println("  Encolar (Cola):          O(1)");
    }
}
