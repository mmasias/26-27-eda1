# Registro de Desviaciones y Enmienda — Examen Parcial (Cola)

## 1. Enmienda al diseño de la Parte 1

Durante la implementación del diseño realizado en la Parte 1 comprobé que la propuesta inicial de utilizar una única `Pila`, haciendo corresponder directamente `encolar()` con `apilar()` y `desencolar()` con `desapilar()`, es inviable para implementar correctamente una cola.

- **Qué falla:** Una pila funciona siguiendo el principio LIFO (*Last In, First Out*), por lo que `desapilar()` devuelve el último elemento introducido. Sin embargo, una cola debe funcionar siguiendo el principio FIFO (*First In, First Out*), devolviendo el primer elemento introducido.

- **Cómo lo comprobé:** Al seguir la secuencia `encolar(1)`, `encolar(2)`, `encolar(3)` y `desencolar()`, una única pila tendría el `3` en la cima. Por tanto, `desencolar()` devolvería `3` en lugar de `1`.

- **Qué cambia:** Se modifica la representación de la cola para utilizar dos pilas: `pEntrada` y `pSalida`. Los elementos se insertan en `pEntrada`. Cuando es necesario desencolar y `pSalida` está vacía, todos los elementos de `pEntrada` se transfieren a `pSalida`. De esta forma, el elemento más antiguo queda en la cima de `pSalida` y puede ser eliminado correctamente.

## 2. Comprobación de la secuencia

La siguiente tabla representa el estado de las dos pilas durante la secuencia de pruebas.

| Cliente | Acción interna | `pEntrada` (fondo → cima) | `pSalida` (fondo → cima) | Devuelve |
|---|---|---|---|---|
| `encolar(1)` | `pEntrada.apilar(1)` | `[1]` | `[]` | — |
| `encolar(2)` | `pEntrada.apilar(2)` | `[1, 2]` | `[]` | — |
| `encolar(3)` | `pEntrada.apilar(3)` | `[1, 2, 3]` | `[]` | — |
| `desencolar()` | Transferencia a `pSalida` y `pSalida.desapilar()` | `[]` | `[3, 2]` | **1** |
| `encolar(4)` | `pEntrada.apilar(4)` | `[4]` | `[3, 2]` | — |
| `desencolar()` | `pSalida.desapilar()` | `[4]` | `[3]` | **2** |
| `desencolar()` | `pSalida.desapilar()` | `[4]` | `[]` | **3** |

Finalmente, al realizar un cuarto `desencolar()`, se devuelve el valor `4` y la cola queda vacía.

## 3. Cumplimiento de restricciones y pruebas

- **`Cola.java`:** Utiliza únicamente dos atributos de tipo `Pila`: `pEntrada` y `pSalida`. No utiliza arrays, nodos, otras estructuras de datos ni recursión.
- **Precondición:** `desencolar()` utiliza `assert !estaVacia()`, tal como establece el enunciado.
- **`PruebasCola.java`:** Reproduce la secuencia indicada y comprueba mediante `assert` que los valores devueltos son `1`, `2`, `3` y `4`.
- **Desviación declarada:** La implementación final utiliza dos pilas en lugar de la única pila propuesta inicialmente, debido a que el diseño original no podía mantener el comportamiento FIFO requerido.