# Registro de desviaciones y enmienda — Examen parcial EDA1

## 1. Diseño original de la Parte 1

En la parte escrita propuse representar la cola utilizando una pila que almacenase valores `int`.

La intención era utilizar las operaciones de la pila para implementar `encolar()`, `desencolar()` y `estaVacia()`.

Sin embargo, mi diseño no especificaba correctamente cómo conseguir que los elementos salieran en orden FIFO.

## 2. Problema detectado

Una pila sigue el principio LIFO: el último elemento que entra es el primero que sale.

Una cola debe seguir FIFO: el primer elemento que entra es el primero que sale.

Por ejemplo, si apilamos los valores 1, 2 y 3, al desapilar directamente obtenemos 3, cuando una cola debería devolver 1.

Además, en los diagramas de la Parte 1 indiqué incorrectamente que la cola quedaba vacía después del primer `desencolar()`, aunque todavía quedaban elementos pendientes.

## 3. Cómo se comprobó

Revisé la secuencia exigida en el examen:

`encolar(1)`, `encolar(2)`, `encolar(3)`, `desencolar()`, `encolar(4)`, `desencolar()`, `desencolar()`.

Los valores que deben devolver las tres extracciones son 1, 2 y 3, y al finalizar debe permanecer el valor 4.

Para verificar la solución corregida, compilé `Cola.java` con una implementación auxiliar de `Pila` compatible con la interfaz pública del enunciado.

Después ejecuté `PruebaCola.java` con las aserciones activadas mediante `java -ea PruebaCola`.

Las cinco pruebas finalizaron correctamente. La clase `Pila` auxiliar se utilizó exclusivamente en el entorno local de pruebas y no forma parte de la entrega.

## 4. Enmienda del diseño

He sustituido la propuesta inicial por una implementación que utiliza dos pilas:

- `entrada`: recibe los elementos nuevos mediante `apilar()`.
- `salida`: permite devolver los elementos respetando el orden FIFO.

Cuando se llama a `desencolar()`, si `salida` está vacía, se transfieren todos los elementos de `entrada` a `salida`.

Esta transferencia invierte el orden de los elementos. Después se desapila el elemento situado en el tope de `salida`, que corresponde al primero que entró en la cola.

Si `salida` todavía contiene elementos, no se realiza ninguna transferencia.

## 5. Invariante de la estructura

El orden lógico de la cola corresponde a los elementos de `salida` desde su tope hasta el fondo, seguidos de los elementos de `entrada` desde el fondo hasta el tope.

Esta propiedad permite mantener el comportamiento FIFO después de cada operación.

## 6. Registro de desviaciones

| Diseño de la Parte 1 | Implementación de la Parte 2 | Justificación |
|---|---|---|
| Propuesta basada en una pila | Dos pilas permanentes | Permite invertir el orden y garantizar FIFO |
| No se especificó cómo mantener FIFO | Transferencia de `entrada` a `salida` cuando corresponde | Corrige el problema del orden de extracción |
| Invariante incompleto | Invariante basado en el orden lógico de ambas pilas | Permite justificar la corrección |
| Diagramas con estados incorrectos | Secuencia corregida con el elemento 4 restante | Los estados deben coincidir con las operaciones realizadas |
| Se planteó descartar `estaVacia()` | Se implementa `estaVacia()` comprobando ambas pilas | Es un método obligatorio de la interfaz |
| No se concretó la comprobación de la precondición | `assert !estaVacia()` en `desencolar()` | Respeta el contrato especificado |

## 7. Conclusión

El diseño inicial era incompleto porque no establecía un mecanismo válido para garantizar FIFO.

La nueva implementación corrige ese problema mediante dos pilas, respeta las restricciones del enunciado y ha superado las pruebas locales realizadas.

Declaro expresamente esta enmienda porque el diseño de la Parte 2 difiere del entregado por escrito en la Parte 1.

## 8. Diagramas de memoria de la implementación corregida

La clase `Cola` contiene dos referencias a objetos `Pila`: `entrada` y `salida`.

Los siguientes estados muestran el contenido de cada pila después de cada operación. Los valores se representan desde el fondo hasta el tope.

| Operación | Pila entrada (fondo → tope) | Pila salida (fondo → tope) | Valor devuelto |
|---|---|---|---|
| `Cola()` | Vacía | Vacía | — |
| `encolar(1)` | 1 | Vacía | — |
| `encolar(2)` | 1, 2 | Vacía | — |
| `encolar(3)` | 1, 2, 3 | Vacía | — |
| Transferencia interna | Vacía | 3, 2, 1 | — |
| `desencolar()` | Vacía | 3, 2 | 1 |
| `encolar(4)` | 4 | 3, 2 | — |
| `desencolar()` | 4 | 3 | 2 |
| `desencolar()` | 4 | Vacía | 3 |

Al finalizar la secuencia, el elemento 4 permanece en la pila `entrada`, por lo que `estaVacia()` devuelve `false`.

### Diagrama de referencias

```text
                Cola
             /        \
            v          v
      Pila entrada   Pila salida
           [4]          vacía
```

### Inversión del orden

Antes de la primera extracción:

```text
  ENTRADA          SALIDA

    [3]
    [2]            vacía
    [1]
```

Después de transferir los elementos:

```text
ENTRADA          SALIDA

                  [1] <- Tope
  vacía           [2]
                  [3]
```

Al ejecutar `salida.desapilar()`, se devuelve el 1 y quedan el 2 y el 3.

La transferencia se realiza únicamente cuando `salida` está vacía, manteniendo el orden FIFO.