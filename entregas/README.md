# Reto 003: Simular estructuras de datos

Práctica de Estructuras de Datos (EDA 1).

Consiste en simular una estructura de datos usando otra que no tiene exactamente las mismas propiedades:

1. Simular un **array** usando una **lista**.
2. Simular una **lista** usando un **array**.

## Archivos

| Archivo | Descripción |
|---|---|
| `ArraySimulado.java` | Array de tamaño fijo construido sobre un `ArrayList` |
| `ListaSimulada.java` | Lista dinámica construida sobre un `int[]` |
| `Main.java` | Programa de prueba de ambas clases |

## Compilar y ejecutar

```bash
javac *.java
java Main
```

## 1. Array simulado con una lista

Un array tiene tamaño fijo y solo permite acceder por índice. Para simularlo con una lista:

- La lista es `private`, así que el usuario no puede acceder a ella directamente.
- Se rellena con todas las posiciones al crear el objeto.
- Solo se ofrecen `get`, `set` y `length`. No existen `add` ni `remove`, por lo que el tamaño no puede cambiar.
- Se comprueba que el índice esté entre `0` y `capacidad - 1`.

| Se consigue | Se pierde |
|---|---|
| Tamaño fijo | La memoria no está garantizada como contigua |
| Acceso solo por índice | Hay validaciones extra en cada acceso |
| Errores controlados | Con una lista enlazada, acceder a un índice sería O(n) |

## 2. Lista simulada con un array

Un array no puede crecer ni insertar elementos. Para simular una lista:

- Se usa una variable `n` con el número de elementos que hay realmente. Es distinta de la capacidad del array.
- Si el array se llena, se crea uno con el doble de capacidad y se copian los elementos.
- **Insertar** en la posición `pos`: se desplazan los elementos a la derecha (de atrás hacia delante) y se coloca el valor.
- **Eliminar** en la posición `pos`: se desplazan los elementos a la izquierda y se reduce `n`.
- Las posiciones desde `n` hasta el final son espacio reservado que el usuario no puede ver.

## Complejidad

| Operación | Array simulado | Lista simulada |
|---|---|---|
| `get` / `set` | O(1) | O(1) |
| `agregar` al final | No existe | O(1) amortizado |
| `insertar` en medio | No existe | O(n) |
| `eliminar` en medio | No existe | O(n) |

## Conclusión

Una estructura de datos se define por las **reglas que impone**, no solo por cómo está guardada en memoria.

- Una lista puede comportarse como un array si se encapsula y se limitan sus operaciones, pero no se puede garantizar la contigüidad en memoria.
- Un array puede comportarse como una lista si se añaden un contador, un redimensionado y desplazamientos, a costa de que insertar y eliminar en medio sean O(n) y de gastar memoria extra.