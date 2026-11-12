# Registro de desviaciones

## Diseño congelado (Parte 1)

- Implementar la `Cola` usando una `Pila` como estructura de almacenamiento.
- `encolar`: guardar el nuevo valor (apilarlo).
- Tener cuidado con la cabeza (el frente) de la cola para no perder parte del recorrido.
- Usar un bucle `while` con la condición `estaVacia()` para recorrer la pila.

## Qué se mantiene igual

| Diseño | Código | ¿Coincide? |
|---|---|---|
| La cola se construye sobre `Pila` | Atributos solo de tipo `Pila` | Sí |
| `encolar` guarda el nuevo valor | `entrada.apilar(valor)` | Sí |
| Bucle `while` con `estaVacia()` | `while (!entrada.estaVacia())` en `desencolar` | Sí |
| Cuidar la cabeza para no perder elementos | Invariante: el frente es la cima de `salida` o, si está vacía, el fondo de `entrada` | Sí |
| Precondición con `assert` | `assert !estaVacia()` en `desencolar` | Sí |

## Desviaciones

### D1. Se usan dos pilas como atributos (`entrada` y `salida`)

- **Qué cambia:** el diseño hablaba de usar una pila; el código guarda dos pilas como atributos: `entrada` (donde se encola) y `salida` (donde se desencola).
- **Justificación:** con una sola pila, el frente de la cola queda en el fondo. Para sacarlo hay que volcar todo a otra pila y después devolverlo, porque, si no, se pierde el orden (justo el problema de la cabeza que señalaba el diseño). Guardar la segunda pila como atributo evita devolver los elementos cada vez: solo se vuelca cuando `salida` está vacía.
- **Cómo se comprobó:** con la secuencia del punto 3, los `desencolar()` devuelven 1, 2 y 3 en orden y el 4 se queda en `entrada` (ver `ColaTest`).

### D2. El volcado del `while` solo se hace cuando `salida` está vacía

- **Qué cambia:** el bucle `while (!entrada.estaVacia())` no se ejecuta en cada `desencolar`, solo cuando `salida` está vacía.
- **Justificación:** si se volcara siempre, el 4 encolado después pasaría por encima de 2 y 3 y saldría antes de tiempo, con lo que se rompería el orden FIFO. Con esta condición, el segundo y el tercer `desencolar()` de la secuencia no vuelcan nada y devuelven 2 y 3 correctamente.

## Sin otras desviaciones

Se respetan todas las restricciones: no hay arrays, ni nodos, ni recursión, y la precondición se expresa con `assert`, no con `if` ni `throw`.