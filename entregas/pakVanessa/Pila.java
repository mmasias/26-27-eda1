public class Pila {

    private ColaInferior elementos;

    public Pila() {
        elementos = new ColaInferior();
    }

    public void apilar(int numero) {
        ColaInferior temporal = new ColaInferior();

        temporal.encolar(numero);

        while (!elementos.estaVacia()) {
            int dato = elementos.desencolar();
            temporal.encolar(dato);
        }

        elementos = temporal;
    }

    public int desapilar() {
        assert !estaVacia() : "No se puede sacar de una pila vacia";

        return elementos.desencolar();
    }

    public boolean estaVacia() {
        return elementos.estaVacia();
    }
}