class NodoCola {
    NodoAVL estudiante;
    NodoCola siguiente;

    public NodoCola(NodoAVL estudiante) {
        this.estudiante = estudiante;
        this.siguiente = null;
    }
}

public class ColaEstudiantes {
    private NodoCola frente;
    private NodoCola fin;

    public void encolar(NodoAVL estudiante) {
        NodoCola nuevo = new NodoCola(estudiante);
        if (fin == null) {
            frente = fin = nuevo;
            return;
        }
        fin.siguiente = nuevo;
        fin = nuevo;
    }

    public NodoCola desencolar() {
        if (frente == null) return null;
        NodoCola removido = frente;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        removido.siguiente = null;
        return removido;
    }

    public void imprimirLista() {
        NodoCola actual = frente;
        if (actual == null) {
            System.out.println("  (Ningun estudiante en esta lista)");
        }
        while (actual != null) {
            System.out.println("  - ID: " + actual.estudiante.id +
                " | Nombre: " + actual.estudiante.nombre +
                " | Puntaje: " + actual.estudiante.puntaje);
            actual = actual.siguiente;
        }
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
