# Parte 1 – Diseño inicial de una Cola mediante una Pila

## Objetivo

Implementar una estructura de datos Cola utilizando las operaciones disponibles de una estructura Pila.

## Diseño inicial

La propuesta inicial consistía en utilizar una pila para almacenar los elementos de la cola.

Se planteaba utilizar las siguientes operaciones:

- `apilar()`: introducir un elemento.
- `desapilar()`: extraer el elemento situado en la parte superior.
- `estaVacia()`: comprobar si la estructura está vacía.

La idea era aprovechar el comportamiento LIFO de la pila para invertir el orden de los elementos y conseguir el comportamiento FIFO de una cola.

## Secuencia de prueba planteada

La secuencia del ejercicio era:

1. Encolar 1.
2. Encolar 2.
3. Encolar 3.
4. Desencolar.
5. Encolar 4.
6. Desencolar.
7. Desencolar.

El comportamiento esperado de una cola sería devolver los elementos en este orden:

```text
1
2
3
```

## Limitaciones detectadas

Al revisar el diseño, se observa que utilizar una única pila no permite garantizar correctamente el comportamiento FIFO de una cola.

Una pila sigue el principio LIFO (Last In, First Out), mientras que una cola sigue el principio FIFO (First In, First Out).

Por tanto, el diseño inicial necesita una modificación para garantizar el orden correcto de extracción.

## Conclusión

El diseño inicial permitió identificar cómo aprovechar las operaciones de una pila para invertir el orden de los elementos, aunque no era suficiente para implementar correctamente una cola.

Esta limitación se corrige en la Parte 2 mediante una enmienda al diseño original.