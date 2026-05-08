class NodoAVL {
    int id;
    String nombre;
    double puntaje;
    int altura;
    NodoAVL izquierdo, derecho;

    public NodoAVL(int id, String nombre, double puntaje) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.altura = 1;
    }
}

public class ArbolAVLPrioridad {
    private NodoAVL raiz;

    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    private int getBalance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.izquierdo) - altura(n.derecho);
    }

    // Rotaciones
    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        return y;
    }

    public void insertar(int id, String nombre, double puntaje) {
        raiz = insertarRecursivo(raiz, id, nombre, puntaje);
    }

    public void eliminarPorPuntaje(double puntaje, int id) {
        raiz = eliminarNodo(raiz, puntaje, id);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, int id, String nombre, double puntaje) {
        if (nodo == null) return new NodoAVL(id, nombre, puntaje);
        if (puntaje < nodo.puntaje) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, id, nombre, puntaje);
        } else {
            nodo.derecho = insertarRecursivo(nodo.derecho, id, nombre, puntaje);
        }
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        int balance = getBalance(nodo);

        // Caso izquierda-izquierda
        if (balance > 1 && puntaje < nodo.izquierdo.puntaje)
            return rotarDerecha(nodo);
        // Caso derecha-derecha
        if (balance < -1 && puntaje > nodo.derecho.puntaje)
            return rotarIzquierda(nodo);
        // Caso izquierda-derecha
        if (balance > 1 && puntaje > nodo.izquierdo.puntaje) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        // Caso derecha-izquierda
        if (balance < -1 && puntaje < nodo.derecho.puntaje) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    public NodoAVL extraerMinimo() {
        if (raiz == null) return null;
        NodoAVL minimo = encontrarMinimo(raiz);
        raiz = eliminarNodo(raiz, minimo.puntaje, minimo.id);
        return minimo;
    }

    private NodoAVL encontrarMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izquierdo != null) actual = actual.izquierdo;
        return actual;
    }

    private NodoAVL eliminarNodo(NodoAVL nodo, double puntaje, int id) {
        if (nodo == null) return nodo;
        if (puntaje < nodo.puntaje) {
            nodo.izquierdo = eliminarNodo(nodo.izquierdo, puntaje, id);
        } else if (puntaje > nodo.puntaje) {
            nodo.derecho = eliminarNodo(nodo.derecho, puntaje, id);
        } else {
            // El puntaje coincide, verificar que sea el estudiante correcto por ID
            if (nodo.id != id) {
                nodo.izquierdo = eliminarNodo(nodo.izquierdo, puntaje, id);
                nodo.derecho = eliminarNodo(nodo.derecho, puntaje, id);
            } else {
                if ((nodo.izquierdo == null) || (nodo.derecho == null)) {
                    NodoAVL temp = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
                    if (temp == null) {
                        nodo = null;
                    } else {
                        nodo = temp;
                    }
                } else {
                    NodoAVL temp = encontrarMinimo(nodo.derecho);
                    nodo.id = temp.id;
                    nodo.nombre = temp.nombre;
                    nodo.puntaje = temp.puntaje;
                    nodo.derecho = eliminarNodo(nodo.derecho, temp.puntaje, temp.id);
                }
            }
        }
        if (nodo == null) return nodo;

        nodo.altura = Math.max(altura(nodo.izquierdo), altura(nodo.derecho)) + 1;
        int balance = getBalance(nodo);

        if (balance > 1 && getBalance(nodo.izquierdo) >= 0)
            return rotarDerecha(nodo);
        if (balance > 1 && getBalance(nodo.izquierdo) < 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && getBalance(nodo.derecho) <= 0)
            return rotarIzquierda(nodo);
        if (balance < -1 && getBalance(nodo.derecho) > 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }
}
