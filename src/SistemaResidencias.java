import java.util.Random;

public class SistemaResidencias {
    ArbolAVLPrioridad arbolPrioridad;
    ArbolBusquedaID arbolBusqueda;
    ColaEstudiantes asignados;
    ColaEstudiantes noAsignados;

    public SistemaResidencias() {
        this.arbolPrioridad = new ArbolAVLPrioridad();
        this.arbolBusqueda = new ArbolBusquedaID();
        this.asignados = new ColaEstudiantes();
        this.noAsignados = new ColaEstudiantes();
    }

    public boolean registrarEstudiante(int id, String nombre, double puntaje) {
        if (arbolBusqueda.buscar(id) != null) return false;
        arbolBusqueda.insertar(id, nombre, puntaje);
        arbolPrioridad.insertar(id, nombre, puntaje);
        return true;
    }

    public void registrarConMensaje(int id, String nombre, double puntaje) {
        if (registrarEstudiante(id, nombre, puntaje)) {
            System.out.println("Registrado con exito: " + nombre + " (ID: " + id + ")");
        } else {
            System.out.println("Error: Ya existe un estudiante con ID " + id + ".");
        }
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
            boolean teniaAsignacion = asignados.removerPorId(id);
            noAsignados.removerPorId(id);
            if (teniaAsignacion && !noAsignados.estaVacia()) {
                NodoCola siguiente = noAsignados.desencolar();
                asignados.encolar(siguiente.estudiante);
                System.out.println("Cupo reasignado a: " + siguiente.estudiante.nombre + " (ID: " + siguiente.estudiante.id + ")");
            }
            System.out.println("Estudiante " + est.nombre + " eliminado exitosamente de todos los registros.");
        } else {
            System.out.println("No se pudo eliminar: El ID " + id + " no existe.");
        }
    }

    private void reinyectarAlAVL() {
        NodoCola nodo = asignados.desencolar();
        while (nodo != null) {
            arbolPrioridad.insertar(nodo.estudiante.id, nodo.estudiante.nombre, nodo.estudiante.puntaje);
            nodo = asignados.desencolar();
        }
        nodo = noAsignados.desencolar();
        while (nodo != null) {
            arbolPrioridad.insertar(nodo.estudiante.id, nodo.estudiante.nombre, nodo.estudiante.puntaje);
            nodo = noAsignados.desencolar();
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

        reinyectarAlAVL();
        asignados = new ColaEstudiantes();
        noAsignados = new ColaEstudiantes();

        for (int i = 0; i < cuposDisponibles; i++) {
            NodoAVL ganador = arbolPrioridad.extraerMaximo();
            if (ganador != null) {
                asignados.encolar(ganador);
            } else {
                break;
            }
        }

        NodoAVL perdedor = arbolPrioridad.extraerMaximo();
        while (perdedor != null) {
            noAsignados.encolar(perdedor);
            perdedor = arbolPrioridad.extraerMaximo();
        }

        System.out.println(" ESTUDIANTES CON CUPO ASIGNADO:");
        asignados.imprimirLista();
        System.out.println(" ESTUDIANTES EN LISTA DE ESPERA (Sin cupo):");
        noAsignados.imprimirLista();
        System.out.println("============================================\n");
    }

    public void mostrarEstado() {
        System.out.println("\n============================================");
        System.out.println(" ESTADO ACTUAL DEL SISTEMA");
        System.out.println("============================================");
        System.out.println(" ESTUDIANTES CON CUPO ASIGNADO:");
        if (asignados.estaVacia()) {
            System.out.println("  (ninguno)");
        } else {
            asignados.imprimirLista();
        }
        System.out.println(" ESTUDIANTES EN LISTA DE ESPERA:");
        if (noAsignados.estaVacia()) {
            System.out.println("  (ninguno)");
        } else {
            noAsignados.imprimirLista();
        }
        System.out.println("============================================\n");
    }

    // Version sin impresion para mediciones de tiempo
    public void asignarCuposSilencioso(int cuposDisponibles) {
        ColaEstudiantes asignados = new ColaEstudiantes();
        ColaEstudiantes noAsignados = new ColaEstudiantes();

        for (int i = 0; i < cuposDisponibles; i++) {
            NodoAVL ganador = arbolPrioridad.extraerMaximo();
            if (ganador != null) {
                asignados.encolar(ganador);
            } else {
                break;
            }
        }
        NodoAVL perdedor = arbolPrioridad.extraerMaximo();
        while (perdedor != null) {
            noAsignados.encolar(perdedor);
            perdedor = arbolPrioridad.extraerMaximo();
        }
    }

    public void generarDatosAleatorios(int cantidad) {
        Random rand = new Random();
        String[] nombres = {"Ana", "Luis", "Maria", "Carlos", "Sofia",
                            "Juan", "Laura", "Pedro", "Camila", "Andres"};
        java.util.ArrayList<Integer> ids = new java.util.ArrayList<>(cantidad);
        for (int i = 1; i <= cantidad; i++) ids.add(i);
        java.util.Collections.shuffle(ids, rand);
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[rand.nextInt(nombres.length)];
            double puntaje = rand.nextDouble() * 5.0;
            registrarEstudiante(ids.get(i), nombre, puntaje);
        }
        System.out.println("Se generaron " + cantidad + " estudiantes aleatorios.");
    }
}
