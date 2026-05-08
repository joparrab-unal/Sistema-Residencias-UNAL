class NodoBST {
    int id;
    String nombre;
    double puntaje;
    NodoBST izquierdo, derecho;

    public NodoBST(int id, String nombre, double puntaje) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolBusquedaID {
    private NodoBST raiz;

    public ArbolBusquedaID() {
        this.raiz = null;
    }

    public void insertar(int id, String nombre, double puntaje) {
        NodoBST nuevo = new NodoBST(id, nombre, puntaje);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        NodoBST actual = raiz;
        while (true) {
            if (id < actual.id) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = nuevo;
                    return;
                }
                actual = actual.izquierdo;
            } else if (id > actual.id) {
                if (actual.derecho == null) {
                    actual.derecho = nuevo;
                    return;
                }
                actual = actual.derecho;
            } else {
                return;
            }
        }
    }

    public NodoBST buscar(int id) {
        NodoBST actual = raiz;
        while (actual != null) {
            if (id == actual.id) {
                return actual;
            } else if (id < actual.id) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return null;
    }

    public void eliminar(int id) {
        NodoBST padre = null;
        NodoBST actual = raiz;
        boolean esHijoIzquierdo = false;

        while (actual != null && actual.id != id) {
            padre = actual;
            if (id < actual.id) {
                actual = actual.izquierdo;
                esHijoIzquierdo = true;
            } else {
                actual = actual.derecho;
                esHijoIzquierdo = false;
            }
        }
        if (actual == null) return;

        if (actual.izquierdo == null && actual.derecho == null) {
            if (actual == raiz) {
                raiz = null;
            } else if (esHijoIzquierdo) {
                padre.izquierdo = null;
            } else {
                padre.derecho = null;
            }
        } else if (actual.derecho == null) {
            if (actual == raiz) {
                raiz = actual.izquierdo;
            } else if (esHijoIzquierdo) {
                padre.izquierdo = actual.izquierdo;
            } else {
                padre.derecho = actual.izquierdo;
            }
        } else if (actual.izquierdo == null) {
            if (actual == raiz) {
                raiz = actual.derecho;
            } else if (esHijoIzquierdo) {
                padre.izquierdo = actual.derecho;
            } else {
                padre.derecho = actual.derecho;
            }
        } else {
            NodoBST padreSucesor = actual;
            NodoBST sucesor = actual.derecho;
            while (sucesor.izquierdo != null) {
                padreSucesor = sucesor;
                sucesor = sucesor.izquierdo;
            }
            actual.id = sucesor.id;
            actual.nombre = sucesor.nombre;
            actual.puntaje = sucesor.puntaje;
            if (padreSucesor == actual) {
                padreSucesor.derecho = sucesor.derecho;
            } else {
                padreSucesor.izquierdo = sucesor.derecho;
            }
        }
    }
}
