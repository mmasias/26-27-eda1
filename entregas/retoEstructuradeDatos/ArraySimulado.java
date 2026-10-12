package entregas.retoEstructuradeDatos;

public class ArraySimulado {

    private Nodo primero;
    private  final int TAMANO;

    private class Nodo {
        String valor;
        Nodo siguiente;
    }

    public ArraySimulado(int TAMANO) {
        this.TAMANO = TAMANO;
        primero = null;
        for (int i = 0; i < TAMANO; i++) {
            Nodo nuevo = new Nodo();
            nuevo.siguiente = primero;
            primero = nuevo;
        }
    }

    public void guardar(int indice, String valor) {
        if (indiceValido(indice)) {
            Nodo nodo = buscarNodo(indice);
            nodo.valor = valor;
        } else {
            System.out.println("Indice fuera de rango: " + indice);
        }
    }

    public String obtener(int indice) {
        if (indiceValido(indice)) {
            Nodo nodo = buscarNodo(indice);
            return nodo.valor;
        } else {
            System.out.println("Valores fuera de rango: " + indice);
            return null;
        }
    }

    public void mostrar() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.print(actual.valor + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    private Nodo buscarNodo(int indice) {
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    private boolean indiceValido(int indice) {
        return indice >= 0 && indice < TAMANO;
    }
}