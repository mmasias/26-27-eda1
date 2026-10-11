package listas.simulacion;

public class ListaQueSimulaArray {

    private class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private final int tamano;

    public ListaQueSimulaArray(int tamano) {
        this.tamano = tamano;
        this.cabeza = null;

        if (tamano > 0) {
            cabeza = new Nodo(0);
            Nodo actual = cabeza;
            int i = 1;
            while (i < tamano) {
                actual.siguiente = new Nodo(0);
                actual = actual.siguiente;
                i++;
            }
        }
    }

    public int length() {
        return tamano;
    }

    public int get(int indice) {
        if (indice < 0 || indice >= tamano) {
            return -1;
        }

        Nodo actual = cabeza;
        int i = 0;
        while (i < indice) {
            actual = actual.siguiente;
            i++;
        }
        return actual.dato;
    }

    public void set(int indice, int valor) {
        if (indice < 0 || indice >= tamano) {
            return;
        }

        Nodo actual = cabeza;
        int i = 0;
        while (i < indice) {
            actual = actual.siguiente;
            i++;
        }
        actual.dato = valor;
    }

    public void imprimir() {
        System.out.print("[ ");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println("]");
    }
}