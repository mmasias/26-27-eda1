package entregas.aguilarAdriana;

public class Cola {
    private Pila pila;
    private int primero;
    private int ultimo;
    private int tamaño;
 
    public Cola() {
        pila = new Pila();
        tamaño = 0;
    }
    public void encolar(int valor) {
        pila.apilar(valor);
        if (tamaño == 0) {
            primero = valor;
        }
        ultimo = valor;
        tamaño++;
    }

    public int desencolar() {
        assert !estaVacia() : "Precondición: la cola no puede estar vacía";
 
        Pila reordenada = new Pila();
        for (int i = 0; i < tamaño; i++) {
            reordenada.apilar(pila.desapilar());
        }
 
        int valor = reordenada.desapilar();
        tamaño--;
 
        if (!reordenada.estaVacia()) {
            primero = reordenada.desapilar();
            pila.apilar(primero);
        }
        while (!reordenada.estaVacia()) {
            pila.apilar(reordenada.desapilar());
        }
 
        return valor;
    }
 
    public boolean estaVacia() {
        return tamaño == 0;
    }
}
