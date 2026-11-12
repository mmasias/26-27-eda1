# Parte 2: Implementación de una Cola mediante dos Pilas

> Implementar y comprobar el funcionamiento de una estructura de datos Cola utilizando dos estructuras Pila.

---

## 💡 Planteamiento y revisión del diseño inicial

Después de revisar el planteamiento de la Parte 1, he considerado que la idea de invertir el orden de los elementos podía seguir siendo útil para resolver el ejercicio.

Sin embargo, utilizando una única pila no conseguía garantizar correctamente el comportamiento FIFO de una cola.

Por ello, he decidido modificar el diseño inicial utilizando dos pilas, manteniendo la idea principal de aprovechar la inversión del orden de los elementos, pero separando las operaciones de entrada y salida.

## 🔍 Desarrollo del razonamiento

La nueva propuesta se basa en utilizar dos pilas con funciones diferentes:

| **Pila** | **Función** |
|---|---|
| `entrada` | Almacenar los elementos que se introducen en la cola. |
| `salida` | Permitir extraer los elementos en el orden de llegada. |

La idea es que los elementos se introduzcan inicialmente en la pila `entrada` y, cuando sea necesario extraer uno y la pila `salida` esté vacía, se transfieran todos los elementos de una pila a otra.

De esta forma, se invierte el orden y se consigue que el primer elemento introducido quede disponible para salir primero.

### Representación del funcionamiento

Inicialmente, después de introducir los elementos `1`, `2` y `3`, tendríamos:

```text
      ENTRADA
     ┌─────┐
     │  3  │ ← TOPE
     ├─────┤
     │  2  │
     ├─────┤
     │  1  │
     └─────┘

      SALIDA
     (vacía)
```

Al transferir los elementos a la segunda pila, el orden se invierte:

```text
      ENTRADA
     (vacía)

      SALIDA
     ┌─────┐
     │  1  │ ← TOPE
     ├─────┤
     │  2  │
     ├─────┤
     │  3  │
     └─────┘
```

Así, al realizar `desencolar()`, se obtiene primero el elemento `1`, respetando el comportamiento FIFO.

## ⚙️ Implementación de la propuesta

Para llevar a cabo este diseño, he planteado los siguientes métodos:

| **Método** | **Función** |
|---|---|
| `encolar(int valor)` | Introducir un elemento en la pila de entrada. |
| `desencolar()` | Extraer el primer elemento disponible de la cola. |
| `estaVacia()` | Comprobar si las dos pilas están vacías. |

Además, he mantenido la precondición de `desencolar()`, utilizando `assert` para comprobar que la cola no esté vacía antes de realizar la operación.

## 🧪 Comprobación del funcionamiento

Para verificar la implementación, he utilizado la misma secuencia de operaciones que en la Parte 1:

| **Paso** | **Operación** |
|---|---|
| 1 | `encolar(1)` |
| 2 | `encolar(2)` |
| 3 | `encolar(3)` |
| 4 | `desencolar()` |
| 5 | `encolar(4)` |
| 6 | `desencolar()` |
| 7 | `desencolar()` |

El resultado esperado es:

```text
1
2
3
```

Después de estas operaciones, el elemento `4` permanece en la cola, por lo que `estaVacia()` devuelve `false`.

## 📝 Conclusión

Con esta modificación he conseguido mantener la idea inicial de invertir el orden de los elementos, utilizando dos pilas para garantizar correctamente el comportamiento FIFO de una cola.

De esta manera, he podido adaptar el diseño inicial y llevarlo a una implementación que respeta el funcionamiento esperado de la estructura.

---

<text color="secondary" size="xs" weight="medium">EDA1 · EXAMEN PARCIAL · PARTE 2</text>

**Diseño revisado e implementación documentada.**