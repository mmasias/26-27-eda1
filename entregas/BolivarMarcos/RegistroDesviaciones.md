# Registro de desviaciones y enmienda — Marcos Bolívar Ceballos

## Desviaciones
**D1. Nombres de las operaciones.** En el texto de mi diseño llamé a las operaciones `encolar` y `desencolar`, aunque en los diagramas ya puse `apilar` y `desapilar`. En el código uso `apilar` y `desapilar`, que es lo que pide el enunciado en la vista pública de `Pila`. Solo cambia el nombre; lo que hace cada operación es lo mismo.

Aparte de D1, no hay más desviaciones. Todo lo demás que cambia está explicado en la enmienda E1.

---

## Enmienda E1 — 12/11/2026, Parte 2

### Qué falla en mi diseño
1. **No cumple las restricciones.** Hice la pila con nodos enlazados (`Nodo`, `fin`, `siguiente`) y no usé la clase `Cola`. El enunciado no permite nodos, y los atributos solo pueden ser de tipo `Cola` o `int`.
2. **Tal como está escrito, no compila.** En `desapilar` uso `fin.anterior`, pero mis nodos solo tienen `siguiente`.
3. **`apilar` está al revés.** La línea `Nuevo = fin.siguiente` debería ser `fin.siguiente = Nuevo`. Con la pila vacía, `fin` es `null` y da un NullPointerException desde el primer `apilar(1)`.

**Lo que sí está bien y mantengo:** el comportamiento. Mis diagramas dicen que los `desapilar()` devuelven **3, 4, 2** y que al final queda el **1**, que es lo correcto para una pila: el último que entra es el primero que sale. También mantengo la idea de que el elemento nuevo se pone siempre "al final" y que se saca desde ese final.

### Cómo lo comprobé
Pasé a código mi diseño tal cual estaba y lo compilé y ejecuté:
```
error: cannot find symbol
        fin = fin.anterior;
  symbol:   variable anterior

apilar(1) sobre la pila vacia -> NullPointerException
```

### Qué cambia
He intentado cambiar lo menos posible.

| | Diseño de la Parte 1 | Después de E1 |
|---|---|---|
| Cómo guardo la pila | nodos enlazados con un puntero `fin` | una `Cola datos` (el final de la cola es la cima de la pila) y un `int tamano` |
| `apilar` | enlazo el nodo al final y muevo `fin` | `datos.encolar(valor)` y `tamano++`. **La idea es la misma: el nuevo va al final** |
| `desapilar` | saco el valor de `fin` y muevo `fin` hacia atrás | paso `tamano - 1` elementos del principio al final de la cola (desencolar y volver a encolar); así el último queda delante, lo desencolo y hago `tamano--` |
| `estaVacia` | `fin == null` | `datos.estaVacia()` |
| Precondición | `assert !estaVacia()` | igual |

**Invariante nuevo:** al acabar cada operación, `datos` tiene todos los elementos en el orden en que se apilaron (el más antiguo delante y la cima al final), y `tamano` es cuántos hay.

### Diagramas corregidos (secuencia del punto 3)
Las colas se escriben así: `[principio … final]`. El final es la cima de la pila.

| Cliente | Qué hago con la cola | datos | tamano | Devuelve |
|---|---|---|---|---|
| (inicio) | — | `[ ]` | 0 | |
| apilar(1) | encolar(1) | `[1]` | 1 | |
| apilar(2) | encolar(2) | `[1 2]` | 2 | |
| apilar(3) | encolar(3) | `[1 2 3]` | 3 | |
| desapilar() | encolar(desencolar()) | `[2 3 1]` | 3 | |
| | encolar(desencolar()) | `[3 1 2]` | 3 | |
| | desencolar() | `[1 2]` | 2 | **3** |
| apilar(4) | encolar(4) | `[1 2 4]` | 3 | |
| desapilar() | encolar(desencolar()) | `[2 4 1]` | 3 | |
| | encolar(desencolar()) | `[4 1 2]` | 3 | |
| | desencolar() | `[1 2]` | 2 | **4** |
| desapilar() | encolar(desencolar()) | `[2 1]` | 2 | |
| | desencolar() | `[1]` | 1 | **2** |

Los valores devueltos (3, 4, 2) y el estado final (solo queda el 1) coinciden con mis diagramas de la Parte 1. `PilaTest.java` comprueba justo esto.

### Alternativa que descarto
**Hacer el trabajo al apilar:** cada `apilar` encola el nuevo y luego rota los anteriores detrás de él, para que la cima quede siempre delante. Así `desapilar` es O(1) y `apilar` pasa a ser O(n). La descarto porque en mi diseño `apilar` solo añade al final y todo el trabajo está en `desapilar`. Con esta alternativa tendría que cambiar las dos operaciones, y con la que he elegido `apilar` se queda prácticamente igual.