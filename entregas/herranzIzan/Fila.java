import java.util.Random;

public class Fila {

    private static final int CAPACIDAD_INICIAL = 16;

    private Persona[] personas = new Persona[CAPACIDAD_INICIAL];
    private int cantidad = 0;

    public int tamano() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public int longitudMetros() {
        return cantidad;
    }

    public Persona get(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        return personas[posicion];
    }

    public void agregarAlFinal(Persona p) {
        insertar(cantidad, p);
    }

    public Persona atenderFrente() {
        if (estaVacia()) {
            return null;
        }
        return eliminar(0);
    }

    private void asegurarCapacidad() {
        if (cantidad == personas.length) {
            Persona[] nuevo = new Persona[personas.length * 2];
            for (int i = 0; i < cantidad; i++) {
                nuevo[i] = personas[i];
            }
            personas = nuevo;
        }
    }

    private void insertar(int posicion, Persona p) {
        asegurarCapacidad();
        for (int i = cantidad; i > posicion; i--) {
            personas[i] = personas[i - 1];
        }
        personas[posicion] = p;
        cantidad++;
    }

    private Persona eliminar(int posicion) {
        Persona eliminada = personas[posicion];
        for (int i = posicion; i < cantidad - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[cantidad - 1] = null;
        cantidad--;
        return eliminada;
    }

    public void agregarPreferente(Persona p) {
        int ultimoPreferente = -1;
        for (int i = 0; i < cantidad; i++) {
            if (personas[i].isPreferente()) {
                ultimoPreferente = i;
            }
        }
        insertar(ultimoPreferente + 1, p);
    }

    public void colarDetras(Persona p, int posicionConocido) {
        insertar(posicionConocido + 1, p);
    }

    public Persona entregarCompras(int posicionDador, int posicionReceptor) {
        Persona dador = personas[posicionDador];
        Persona receptor = personas[posicionReceptor];
        receptor.recibirCompras(dador.getCompras());
        return eliminar(posicionDador);
    }

    public int retirarAburridos(int minutoActual, int umbralMinutos, double prob, Random rnd) {
        int fuera = 0;
        for (int i = cantidad - 1; i >= 0; i--) {
            Persona p = personas[i];
            if (p.minutosEnFila(minutoActual) > umbralMinutos && rnd.nextDouble() < prob) {
                eliminar(i);
                fuera++;
            }
        }
        return fuera;
    }

    @Override
    public String toString() {
        String texto = "[";
        for (int i = 0; i < cantidad; i++) {
            texto += personas[i];
            if (i < cantidad - 1) {
                texto += ", ";
            }
        }
        return texto + "]";
    }

}