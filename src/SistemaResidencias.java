import java.util.Random;

public class SistemaResidencias {
    ArbolAVLPrioridad arbolPrioridad;
    ArbolBusquedaID arbolBusqueda;

    public SistemaResidencias() {
        this.arbolPrioridad = new ArbolAVLPrioridad();
        this.arbolBusqueda = new ArbolBusquedaID();
    }

    public void registrarEstudiante(int id, String nombre, double puntaje) {
        arbolPrioridad.insertar(id, nombre, puntaje);
        arbolBusqueda.insertar(id, nombre, puntaje);
    }

    public void registrarConMensaje(int id, String nombre, double puntaje) {
        registrarEstudiante(id, nombre, puntaje);
        System.out.println("Registrado con exito: " + nombre + " (ID: " + id + ")");
    }

    public void consultarPorID(int id) {
        System.out.println("\nBuscando ID " + id + "...");
        NodoBST encontrado = arbolBusqueda.buscar(id);
        if (encontrado != null) {
            System.out.println("Estudiante encontrado: " + encontrado.nombre +
                " | Puntaje: " + encontrado.puntaje);
        } else {
            System.out.println("Error: Estudiante con ID " + id + " no existe.");
        }
    }

    public NodoBST buscarPorID(int id) {
        return arbolBusqueda.buscar(id);
    }

    public void eliminarEstudiante(int id) {
        NodoBST est = arbolBusqueda.buscar(id);
        if (est != null) {
            double puntajeParaBorrar = est.puntaje;
            arbolBusqueda.eliminar(id);
            arbolPrioridad.eliminarPorPuntaje(puntajeParaBorrar, id);
            System.out.println("Estudiante " + est.nombre + " eliminado exitosamente de ambos registros.");
        } else {
            System.out.println("No se pudo eliminar: El ID " + id + " no existe.");
        }
    }

    // Version sin impresion para mediciones de tiempo
    public void eliminarEstudianteSilencioso(int id) {
        NodoBST est = arbolBusqueda.buscar(id);
        if (est != null) {
            double puntajeParaBorrar = est.puntaje;
            arbolBusqueda.eliminar(id);
            arbolPrioridad.eliminarPorPuntaje(puntajeParaBorrar, id);
        }
    }

    public void asignarCupos(int cuposDisponibles) {
        System.out.println("\n============================================");
        System.out.println(" INICIANDO ASIGNACION DE " + cuposDisponibles + " CUPOS");
        System.out.println("============================================");

        ColaEstudiantes asignados = new ColaEstudiantes();
        ColaEstudiantes noAsignados = new ColaEstudiantes();

        for (int i = 0; i < cuposDisponibles; i++) {
            NodoAVL ganador = arbolPrioridad.extraerMinimo();
            if (ganador != null) {
                asignados.encolar(ganador);
            } else {
                break;
            }
        }

        NodoAVL perdedor = arbolPrioridad.extraerMinimo();
        while (perdedor != null) {
            noAsignados.encolar(perdedor);
            perdedor = arbolPrioridad.extraerMinimo();
        }

        System.out.println(" ESTUDIANTES CON CUPO ASIGNADO:");
        asignados.imprimirLista();
        System.out.println(" ESTUDIANTES EN LISTA DE ESPERA (Sin cupo):");
        noAsignados.imprimirLista();
        System.out.println("============================================\n");
    }

    // Version sin impresion para mediciones de tiempo
    public void asignarCuposSilencioso(int cuposDisponibles) {
        ColaEstudiantes asignados = new ColaEstudiantes();
        ColaEstudiantes noAsignados = new ColaEstudiantes();

        for (int i = 0; i < cuposDisponibles; i++) {
            NodoAVL ganador = arbolPrioridad.extraerMinimo();
            if (ganador != null) {
                asignados.encolar(ganador);
            } else {
                break;
            }
        }
        NodoAVL perdedor = arbolPrioridad.extraerMinimo();
        while (perdedor != null) {
            noAsignados.encolar(perdedor);
            perdedor = arbolPrioridad.extraerMinimo();
        }
    }

    public void generarDatosAleatorios(int cantidad) {
        Random rand = new Random();
        String[] nombres = {"Ana", "Luis", "Maria", "Carlos", "Sofia",
                            "Juan", "Laura", "Pedro", "Camila", "Andres"};
        for (int i = 1; i <= cantidad; i++) {
            String nombre = nombres[rand.nextInt(nombres.length)];
            double puntaje = rand.nextDouble() * 100.0;
            registrarEstudiante(i, nombre, puntaje);
        }
        System.out.println("Se generaron " + cantidad + " estudiantes aleatorios.");
    }
}
