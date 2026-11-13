package entregas.PiladeColax2;

public class PilaSegunda {
    private Cola datos;
    private int tamano;

    public PilaSegunda() {
        datos = new Cola();
        tamano = 0;
    }

    public void apilar(int valor) {
        datos.encolar(valor);
        tamano++;
    }

    public int desapilar() {
        assert !estaVacia() : "La pila no puede estar vacia";
        for (int i = 0; i < tamano - 1; i++) {
            datos.encolar(datos.desencolar());
        }
        int cima = datos.desencolar();
        tamano--;
        return cima;
    }

    public boolean estaVacia() {
        return datos.estaVacia();
    }
}
