import java.util.ArrayList;

public class ArraySimulado {
    private ArrayList<Integer> datos;
    private int capacidad;

    public ArraySimulado(int capacidad, int valorInicial) {
        if (capacidad < 0) {
            throw new IllegalArgumentException("La capacidad no puede ser negativa");
        }
        this.capacidad = capacidad;
        this.datos = new ArrayList<Integer>();
        for (int i = 0; i < capacidad; i++) {
            datos.add(valorInicial);
        }
    }

    public int get(int i) {
        comprobarIndice(i);
        return datos.get(i);
    }

    public void set(int i, int valor) {
        comprobarIndice(i);
        datos.set(i, valor);
    }

    public int length() {
        return capacidad;
    }

    private void comprobarIndice(int i) {
        if (i < 0 || i >= capacidad) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + i);
        }
    }

    public void mostrar() {
        System.out.println(datos);
    }
}