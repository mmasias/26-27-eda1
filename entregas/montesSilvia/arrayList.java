import java.util.ArrayList;
import java.util.List;

public class ArraySimulado<T> {

    private final List<T> elementos;
    private final int capacidad;
    public ArraySimulado(int capacidad) {
        if (capacidad < 0) {
            throw new IllegalArgumentException(
                "La capacidad no puede ser negativa."
            );
        }

        this.capacidad = capacidad;
        this.elementos = new ArrayList<>(capacidad);

        
        for (int i = 0; i < capacidad; i++) {
            elementos.add(null);
        }
    }
    public int longitud() {
        return capacidad;
    }
    private void comprobarIndice(int indice) {
        if (indice < 0 || indice >= capacidad) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice
            );
        }
    }
    public void set(int indice, T valor) {
        comprobarIndice(indice);
        elementos.set(indice, valor);
    }

    
    public T get(int indice) {
        comprobarIndice(indice);
        return elementos.get(indice);
    }
    public void mostrar() {
        System.out.println(elementos);
    }
    public void rellenar(T valor) {
        for (int i = 0; i < capacidad; i++) {
            elementos.set(i, valor);
        }
    }

    public static void main(String[] args) {
        ArraySimulado<Integer> numeros =
            new ArraySimulado<>(5);
        numeros.set(0, 10);
        numeros.set(1, 20);
        numeros.set(2, 30);
        numeros.set(3, 40);
        numeros.set(4, 50);

        System.out.println("Array simulado:");
        numeros.mostrar();
        System.out.println("Elemento en la posición 2: "
            + numeros.get(2));
        numeros.set(2, 99);

        System.out.println("Después de modificar:");
        numeros.mostrar();
        System.out.println("Longitud: " + numeros.longitud());
        try {
            numeros.set(5, 60);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



