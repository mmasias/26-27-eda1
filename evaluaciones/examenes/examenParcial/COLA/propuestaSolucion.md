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

## Cola con dos pilas

Notación: cada pila se escribe de cima a base. En `principal` la cima es el frente de la cola.

<div align=center>

|Cliente|Acción sobre las pilas|principal|auxiliar|
|-|-|-|-|
|`encolar(1)`|vaciar principal (nada), apilar 1, devolver (nada)|[1]|[]|
|`encolar(2)`|vaciar principal en auxiliar|[]|[1]|
||apilar 2 en principal|[2]|[1]|
||devolver desde auxiliar|[1, 2]|[]|
|`encolar(3)`|vaciar principal en auxiliar|[]|[2, 1]|
||apilar 3 en principal|[3]|[2, 1]|
||devolver desde auxiliar|[1, 2, 3]|[]|
|`desencolar()`|desapilar principal -> **1**|[2, 3]|[]|
|`encolar(4)`|vaciar principal en auxiliar|[]|[3, 2]|
||apilar 4 en principal|[4]|[3, 2]|
||devolver desde auxiliar|[2, 3, 4]|[]|
|`desencolar()`|desapilar principal -> **2**|[3, 4]|[]|
|`desencolar()`|desapilar principal -> **3**|[4]|[]|

</div>

El cliente recibe 1, 2 y 3. Queda el 4 en la cola.

> *NOTA: tras cada operación completa, `auxiliar` está vacía. Solo contiene elementos en medio de `encolar`*