# PILA con COLA: Propuesta de solución

Una sola cola con rotación en apilar, más un int que cuenta los elementos.

Estrategia: el frente de la cola es siempre la cima de la pila.

- apilar(v): se encola v (queda al final) y se rotan los tamaño elementos previos (desencolar + encolar cada uno), con lo que v pasa al frente.
- desapilar(): se desencola directamente, porque el frente ya es la cima.
- estaVacia(): delega en la cola.

Invariante: la cola contiene los elementos de la pila ordenados de cima a base, y tamaño es su cantidad.

Por qué el int es imprescindible: Cola no expone su tamaño y la rotación necesita saber cuántos elementos mover.

```java
public class Pila {
    private Cola cola;
    private int tamano;

    public Pila() {
        cola = new Cola();
        tamano = 0;
    }

    public void apilar(int valor) {
        cola.encolar(valor);
        for (int i = 0; i < tamano; i++) {
            cola.encolar(cola.desencolar());
        }
        tamano++;
    }

    public int desapilar() {
        assert !estaVacia();
        tamano--;
        return cola.desencolar();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}
```