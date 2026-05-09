import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Benchmarks {
    public static void main(String[] args) {
        int[] tamanos = {10000, 50000, 100000};
        int repeticiones = 10;
        String[] nombres = {"Ana", "Luis", "Maria", "Carlos", "Sofia",
                            "Juan", "Laura", "Pedro", "Camila", "Andres"};

        System.out.println("=======================================================================");
        System.out.println("         BENCHMARKS DE RENDIMIENTO - SISTEMA DE RESIDENCIAS");
        System.out.println("=======================================================================");
        System.out.println("IDs aleatorios (no secuenciales) para evitar degeneracion del BST.");
        System.out.println("Operaciones rapidas promediadas sobre " + repeticiones + " ejecuciones.\n");

        System.out.printf("%-10s | %-15s | %-18s | %-18s | %-15s%n",
            "N", "Insercion(ms)", "Busqueda(us)", "Eliminacion(us)", "Asignacion(ms)");
        System.out.println("-----------------------------------------------------------------------");

        for (int n : tamanos) {
            Random rand = new Random(42);

            ArrayList<Integer> ids = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) ids.add(i);
            Collections.shuffle(ids, rand);

            SistemaResidencias sistema = new SistemaResidencias();
            long inicio = System.nanoTime();
            for (int i = 0; i < n; i++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                double puntaje = rand.nextDouble() * 5.0;
                sistema.registrarEstudiante(ids.get(i), nombre, puntaje);
            }
            long tiempoInsercion = System.nanoTime() - inicio;

            long sumaBusqueda = 0;
            for (int r = 0; r < repeticiones; r++) {
                int idBuscar = ids.get(rand.nextInt(n));
                inicio = System.nanoTime();
                sistema.buscarPorID(idBuscar);
                sumaBusqueda += System.nanoTime() - inicio;
            }
            double tiempoBusqueda = sumaBusqueda / (double) repeticiones;

            long sumaEliminacion = 0;
            for (int r = 0; r < repeticiones; r++) {
                int idEliminar = ids.get(rand.nextInt(n));
                inicio = System.nanoTime();
                sistema.eliminarEstudianteSilencioso(idEliminar);
                sumaEliminacion += System.nanoTime() - inicio;
            }
            double tiempoEliminacion = sumaEliminacion / (double) repeticiones;

            Random rand2 = new Random(42);
            ArrayList<Integer> ids2 = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) ids2.add(i);
            Collections.shuffle(ids2, rand2);

            SistemaResidencias sistemaAsignacion = new SistemaResidencias();
            for (int i = 0; i < n; i++) {
                String nombre = nombres[rand2.nextInt(nombres.length)];
                double puntaje = rand2.nextDouble() * 5.0;
                sistemaAsignacion.registrarEstudiante(ids2.get(i), nombre, puntaje);
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
        System.out.println("\nComplejidades teoricas (BST balanceado con IDs aleatorios):");
        System.out.println("  Insercion N elementos:   O(N log N) en AVL y BST");
        System.out.println("  Busqueda por ID (BST):   O(log N) caso promedio");
        System.out.println("  Eliminacion (ambos):     O(log N) en AVL y BST");
        System.out.println("  Asignacion K cupos:      O(K log N) via AVL");
        System.out.println("  Encolar (Cola):          O(1)");
    }
}
