# Parte 2 - Cola con pilas

Daniel Mora Núñez - 12/11/2026

Entrego `Cola.java`, `PruebaCola.java` y este documento.

## Mi diseño de la Parte 1

En la Parte 1 puse que la cola tiene **un atributo de tipo Pila** (y un `int valor`), pero no llegué a explicar qué hace cada operación.

## Enmienda (12/11/2026)

**Qué falla:** con una sola pila, si en `desencolar()` hago `desapilar()` directamente me sale el último que entró, no el primero.

**Cómo lo comprobé:** encolando 1, 2, 3 y desapilando:

```
| 3 |  <- sale este (mal)
| 2 |
| 1 |  <- tendría que salir este
+---+
```

**Qué cambio:** sigo con una sola pila de atributo, pero en `desencolar()` uso una pila auxiliar **local** (`pilaDePaso`):
1. Paso todo de `pila` a `pilaDePaso` (queda al revés, el 1 arriba).
2. Desapilo el de arriba, que es el primero.
3. Devuelvo el resto a `pila`.

`encolar` solo hace `apilar` y `estaVacia` mira si la pila está vacía.

**Invariante:** al acabar cada operación todo está en `pila`, con el primero que entró abajo del todo.

## Secuencia

(pila de abajo a arriba)

| Operación | pila | pilaDePaso | devuelve |
|-|-|-|-|
| encolar(1) | 1 | | |
| encolar(2) | 1 2 | | |
| encolar(3) | 1 2 3 | | |
| desencolar() | vacía | 3 2 1 | |
| | 2 3 | vacía | 1 |
| encolar(4) | 2 3 4 | | |
| desencolar() | vacía | 4 3 2 | |
| | 3 4 | vacía | 2 |
| desencolar() | vacía | 4 3 | |
| | 4 | vacía | 3 |

Al final:

```
cola --> [ pila ] --> | 4 |
                      +---+
```

## Pruebas

`PruebaCola` hace esta misma secuencia y comprueba con `assert` que los `desencolar()` devuelven 1, 2 y 3 y que al final la cola no está vacía (queda el 4). Se ejecuta con `java -ea PruebaCola`. Salen todas bien.

## Desviaciones

1. En el papel llamé a la pila `pila` y luego `this.cola`, y la creaba dos veces. En el código hay una sola, `pila`, creada en el constructor. Es el mismo diseño, era un error al escribirlo.
2. He quitado `int valor` porque no lo uso para nada.
3. Arreglé la sintaxis (`New` -> `new`, los `;`).

No hay más cambios.

## Comentario

`desencolar()` es O(n) porque mueve todos los elementos dos veces. Con dos pilas de atributo sería más rápido, pero mi diseño era de una sola pila y no quería cambiarlo.
