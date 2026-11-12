# Registro de desviaciones

**Desviaciones respecto al diseño congelado: ninguna.**

El código sigue lo escrito a mano en la Parte 1: dos pilas privadas, una de entrada y otra de salida, y cuando la salida está vacía se redirigen hacia ella los elementos de la entrada.

## Decisiones de implementación (no son desviaciones)

- `estaVacia()` no figuraba en el diseño. Se implementó como "ambas pilas vacías" por ser la única condición coherente con él.
- El diseño dice que se redirigen los elementos a la salida, pero no cómo. Se pasan todos, uno a uno, con un bucle `while`, y solo cuando la salida está vacía.
- La precondición de `desencolar` se expresa con `assert !estaVacia();`, como exige el enunciado.
- No se usan arrays, nodos ni recursión. Los atributos son solo de tipo `Pila`.