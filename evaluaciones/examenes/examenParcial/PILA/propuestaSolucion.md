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

## Pila con una cola

Notación: la cola se escribe de frente a final y el frente es la cima de la pila.

<div align=center>

|Cliente|Acción sobre la cola|Cola|tamano|
|-|-|-|:-:|
|`apilar(1)`|encolar(1)|[1]|0|
||(rotar 0 veces) tamano++|[1]|1|
|`apilar(2)`|encolar(2)|[1, 2]|1|
||rotar: desencolar 1, encolar 1|[2, 1]|1|
||tamano++|[2, 1]|2|
|`apilar(3)`|encolar(3)|[2, 1, 3]|2|
||rotar: pasa el 2 al final|[1, 3, 2]|2|
||rotar: pasa el 1 al final|[3, 2, 1]|2|
||tamano++|[3, 2, 1]|3|
|`desapilar()`|tamano--, desencolar -> **3**|[2, 1]|2|
|`apilar(4)`|encolar(4)|[2, 1, 4]|2|
||rotar dos veces|[4, 2, 1]|2|
||tamano++|[4, 2, 1]|3|
|`desapilar()`|tamano--, desencolar -> **4**|[2, 1]|2|
|`desapilar()`|tamano--, desencolar -> **2**|[1]|1|

</div>

El cliente recibe 3, 4 y 2. Queda el 1 en la pila.
