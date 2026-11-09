# Examen parcial - Una cola

Está usted en un lenguaje de programación que solo permite pilas. Implemente una cola con esa estructura de datos.

## Material provisto

La clase `Pila` viene compilada. No puede modificarla ni conocer su implementación, solo su vista pública:

```java
public class Pila {
    public Pila()
    public void apilar(int valor)
    public int desapilar()
    public boolean estaVacia()
}
```

* La precondición de `desapilar` es `!estaVacia()`.

## Lo que debe construir

```java
public class Cola {
    public Cola()
    public void encolar(int valor)
    public int desencolar()
    public boolean estaVacia()
}
```

La precondición de `desencolar` es `!estaVacia()` y es responsabilidad del cliente: se expresa con `assert`, no con `if` ni `throw`.

### Restricciones

* Los atributos de `Cola` solo pueden ser de tipo `Pila` o `int`.
* No se permiten arrays, nodos ni ninguna otra estructura de datos.
* No se permite recursión.

## Parte 1 (60 minutos, sin ordenador)

Produzca el diseño de su `Cola`. Este documento queda congelado: se entrega en papel al terminar la hora y es el contrato de la Parte 2. Debe contener:

1. La estrategia central: cómo representa la cola con la pila y qué hace cada operación.
2. El invariante de su estructura, en una frase.
3. Diagramas de memoria del estado de su `Cola` y de sus `Pila` tras cada operación de esta secuencia: `encolar(1)`, `encolar(2)`, `encolar(3)`, `desencolar()`, `encolar(4)`, `desencolar()`, `desencolar()`.
4. Una alternativa de diseño que descarta y la razón.

## Parte 2 (60 minutos, un ordenador)

Implemente su diseño. Puede apoyarse en un asistente de IA, pero lo que se evalúa es la fidelidad a su diseño de la Parte 1, no que el código funcione. Entregue:

1. `Cola.java`.
2. Pruebas propias que reproduzcan la secuencia del punto 3 de la Parte 1 y verifiquen el valor que devuelve cada `desencolar()`.
3. Registro de desviaciones: cada diferencia entre el código entregado y el diseño congelado, con su justificación. Si no hay ninguna, indíquelo expresamente.

Si descubre que su diseño es inviable, no lo reescriba. Declare una enmienda fechada: qué falla, cómo lo comprobó y qué cambia. Una enmienda declarada penaliza menos que una desviación no declarada.

## Se valora

* Coherencia entre el diseño congelado y el código entregado.
* Pruebas que demuestren lo que sus diagramas afirman.
* Dos o tres razonamientos sólidos antes que una enumeración extensa.
* Diagramas por sobre el texto escrito.

## Ayuda para los diagramas

Para construir los diagramas del punto 3 de la Parte 1 puede ayudarse de una tabla como esta: una fila por cada acción que su diseño realiza sobre la estructura provista, con el estado resultante. Añada las filas que necesite.

| Cliente | Acción | Estado |
|-|-|-|
| `encolar(1)` | | |
| `encolar(2)` | | |
| `encolar(3)` | | |
| `desencolar()` | | |
| `encolar(4)` | | |
| `desencolar()` | | |
| `desencolar()` | | |