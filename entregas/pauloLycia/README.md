# Examen parcial - Una cola con pilas

**Alumna:** Lycia Paulo

| Archivo | Contenido |
|-|-|
| `Cola.java` | Implementación del diseño congelado |
| `PruebasCola.java` | Pruebas de la secuencia del punto 3 de la Parte 1 |
| `Pila.java`, `Nodo.java` | Material provisto (ver nota en el registro) |

Ejecución:

```bash
javac *.java
java -ea PruebasCola
```

`-ea` activa los `assert`, necesario para comprobar la precondición de `desencolar()`.

## Diseño congelado (Parte 1, transcripción)

### 1. Estrategia central

La cola se representa con **dos pilas**: `entrada`, donde se apilan los elementos nuevos, y `salida`, de donde se desapilan.

- `encolar(valor)`: apila `valor` en `entrada`.
- `desencolar()`: si `salida` está vacía, vuelca todos los elementos de `entrada` a `salida` (invierte el orden y el más antiguo queda en la cima). Después desapila de `salida`.
- `estaVacia()`: `true` si las dos pilas están vacías.

### 2. Invariante

El frente de la cola es siempre la cima de `salida` si no está vacía y, si lo está, el fondo de `entrada`; el orden de la cola es `salida` (de cima a fondo) seguida de `entrada` (de fondo a cima).

### 3. Diagramas

No se entregaron en la Parte 1.

### 4. Alternativa descartada

Una pila principal más una auxiliar temporal, volcando todos los elementos en **cada** `encolar` para que la cima sea siempre el frente. Se descarta porque cada `encolar` cuesta siempre O(n); con dos pilas y volcado solo cuando `salida` está vacía, cada elemento se mueve una sola vez y el coste amortizado es O(1).

## Registro de desviaciones

**No hay desviaciones** entre `Cola.java` y el diseño congelado:

- Mismos atributos (`entrada` y `salida`, ambos `Pila`), mismas operaciones y mismo criterio de volcado (solo cuando `salida` está vacía).
- La precondición de `desencolar()` se expresa con `assert !estaVacia()`, sin `if` ni `throw`. El único `if` decide el volcado, como indica el diseño.
- Las pruebas reproducen la secuencia del punto 3 y verifican que cada `desencolar()` devuelve 1, 2 y 3, en orden FIFO, como se deriva de la estrategia y del invariante.

**Nota:** el diseño congelado no incluye los diagramas del punto 3 porque no los completé durante la Parte 1. No los añado ahora para no alterar el documento congelado.

**Nota sobre el material provisto (12/11/2026):** `Pila.java` accede a `cima.valor` y `cima.siguiente`, pero en el `Nodo.java` provisto esos atributos son `private`, por lo que no compila (`error: valor has private access in Nodo`). Para poder ejecutar las pruebas **no se modificó `Pila`**: se quitó el modificador `private` de los dos atributos de `Nodo`. No afecta a `Cola` ni al diseño.
