
interface Pila {

    void apilar(int valor);

    int desapilar();

    int cima();

    boolean estaVacia();

    int cantidad();
}

interface Cola {

    void encolar(int valor);

    int desencolar();

    int frente();

    boolean estaVacia();

    int cantidad();
}

class Nodo {

    int valor;
    Nodo siguiente;

    Nodo(int valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}


class PilaEnlazada implements Pila {

    private Nodo tope;
    private int cantidadElementos;

    public void apilar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        cantidadElementos++;
    }

    public int desapilar() {
        comprobarQueNoEstaVacia();

        int valorDelTope = tope.valor;
        tope = tope.siguiente;
        cantidadElementos--;

        return valorDelTope;
    }

    public int cima() {
        comprobarQueNoEstaVacia();
        return tope.valor;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int cantidad() {
        return cantidadElementos;
    }

    private void comprobarQueNoEstaVacia() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
    }
}

class ColaEnlazada implements Cola {

    private Nodo primero;
    private Nodo ultimo;
    private int cantidadElementos;

    public void encolar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);

        if (estaVacia()) {
            primero = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
        }

        ultimo = nuevoNodo;
        cantidadElementos++;
    }

    public int desencolar() {
        comprobarQueNoEstaVacia();

        int valorDelFrente = primero.valor;
        primero = primero.siguiente;

        if (primero == null) {
            ultimo = null;
        }

        cantidadElementos--;

        return valorDelFrente;
    }

    public int frente() {
        comprobarQueNoEstaVacia();
        return primero.valor;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int cantidad() {
        return cantidadElementos;
    }

    private void comprobarQueNoEstaVacia() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
    }
}


class PilaConCola implements Pila {

    private final Cola colaInterna;

    PilaConCola(Cola colaInterna) {
        this.colaInterna = colaInterna;
    }

    public void apilar(int valor) {
        colaInterna.encolar(valor);

        int rotacionesNecesarias = colaInterna.cantidad() - 1;

        for (int rotacion = 0; rotacion < rotacionesNecesarias; rotacion++) {
            colaInterna.encolar(colaInterna.desencolar());
        }
    }

    public int desapilar() {
        return colaInterna.desencolar();
    }

    public int cima() {
        return colaInterna.frente();
    }

    public boolean estaVacia() {
        return colaInterna.estaVacia();
    }

    public int cantidad() {
        return colaInterna.cantidad();
    }
}


class ColaConPilas implements Cola {

    private final Pila pilaEntrada;
    private final Pila pilaSalida;

    ColaConPilas(Pila pilaEntrada, Pila pilaSalida) {
        this.pilaEntrada = pilaEntrada;
        this.pilaSalida = pilaSalida;
    }

    public void encolar(int valor) {
        pilaEntrada.apilar(valor);
    }

    public int desencolar() {
        volcarEntradaSiSalidaVacia();
        return pilaSalida.desapilar();
    }

    public int frente() {
        volcarEntradaSiSalidaVacia();
        return pilaSalida.cima();
    }

    public boolean estaVacia() {
        return pilaEntrada.estaVacia() && pilaSalida.estaVacia();
    }

    public int cantidad() {
        return pilaEntrada.cantidad() + pilaSalida.cantidad();
    }

    private void volcarEntradaSiSalidaVacia() {
        if (pilaSalida.estaVacia()) {
            while (!pilaEntrada.estaVacia()) {
                pilaSalida.apilar(pilaEntrada.desapilar());
            }
        }
    }
}


public class Reto {

    public static void main(String[] argumentos) {

        System.out.println("=== Caso A: Pila con Cola, y esa Cola hecha con Pilas ===");

        Pila pilaCasoA = new PilaConCola(
            new ColaConPilas(
                new PilaEnlazada(),
                new PilaEnlazada()
            )
        );

        probarPila(pilaCasoA);
    }

    static void probarPila(Pila pila) {

        for (int valor = 1; valor <= 5; valor++) {
            pila.apilar(valor);
        }

        System.out.println("Cima tras apilar 1..5: "
            + pila.cima() + " (esperado: 5)");

        System.out.println("Desapilo dos: "
            + pila.desapilar() + " "
            + pila.desapilar() + " (esperado: 5 4)");

        pila.apilar(6);
        pila.apilar(7);

        System.out.print("Apilo 6 y 7 y vacío la pila:");

        while (!pila.estaVacia()) {
            System.out.print(" " + pila.desapilar());
        }

        System.out.println(" (esperado: 7 6 3 2 1)");
    }
}
