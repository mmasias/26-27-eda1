# COLA con PILA: Propuesta de solución

Dos pilas (principal y auxiliar), con ida y vuelta de todos los elementos en encolar.

Estrategia: la cima de principal es siempre el frente de la cola.

- encolar(v): se vacía principal en auxiliar (invierte el orden), se apila v en principal (ahora vacía) y se devuelve todo desde auxiliar a principal (restablece el orden). v queda en la base y el más antiguo sigue en la cima.
- desencolar(): se desapila de principal directamente, porque la cima ya es el frente.
- estaVacia(): delega en principal.

Invariante: principal contiene los elementos de la cola ordenados de frente a final (cima a base) y auxiliar está vacía entre operaciones.

Por qué dos pilas: con una sola no se puede llegar al elemento de la base sin recursión, y Pila no permite recorrerse. La auxiliar es el único lugar donde dejar los elementos mientras se coloca el nuevo.

Por qué ningún int: los bucles terminan con estaVacia() de la pila que se vacía, sin necesidad de contar.

```java
public class Cola {
    private Pila principal;
    private Pila auxiliar;

    public Cola() {
        principal = new Pila();
        auxiliar = new Pila();
    }

    public void encolar(int valor) {
        while (!principal.estaVacia()) {
            auxiliar.apilar(principal.desapilar());
        }
        principal.apilar(valor);
        while (!auxiliar.estaVacia()) {
            principal.apilar(auxiliar.desapilar());
        }
    }

    public int desencolar() {
        assert !estaVacia();
        return principal.desapilar();
    }

    public boolean estaVacia() {
        return principal.estaVacia();
    }
}
```