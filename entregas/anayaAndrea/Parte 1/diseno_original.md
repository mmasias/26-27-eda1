## Parte 1: Diseño de una Cola mediante una Pila

>Implementar una estructura de datos Cola utilizando las operaciones disponibles de una Pila.

---

## 💡 Planteamiento inicial

Para abordar el ejercicio, he pensado en aprovechar el comportamiento de una pila para conseguir invertir el orden de los elementos y así aproximarme al funcionamiento de una cola.

Mi idea inicial ha sido utilizar las operaciones de apilar y desapilar para reorganizar los elementos, buscando que el primero en introducirse sea también el primero en salir.

A partir de este planteamiento, he desarrollado el razonamiento teniendo en cuenta las características y operaciones de una pila:

| Operación | Función |
|:---|:---|
| `apilar()` | Introducir un elemento |
| `desapilar()` | Extraer el elemento superior |
| `estaVacia()` | Comprobar si la pila está vacía |

Mi planteamiento se ha basado principalmente en aprovechar el comportamiento de la pila para invertir el orden de los elementos.

## 🔍 Desarrollo del razonamiento

Una pila sigue el principio **LIFO (Last In, First Out)**, es decir, el último elemento en entrar es el primero en salir.

Por ejemplo, al introducir los elementos `1`, `2` y `3`, la estructura quedaría de la siguiente manera:

```text
       TOPE
     ┌─────┐
     │  3  │
     ├─────┤
     │  2  │
     ├─────┤
     │  1  │
     └─────┘
```

Al extraer los elementos, el orden obtenido sería:

```text
3 → 2 → 1
```

A partir de este comportamiento, he planteado aprovechar la inversión del orden para conseguir el funcionamiento **FIFO (First In, First Out)**, característico de una cola.

## ⚙️ Desarrollo de la propuesta

Mi idea ha sido utilizar las operaciones de apilar y desapilar para reorganizar los elementos y conseguir que el primero introducido sea también el primero en salir.

Para comprobar el comportamiento de la estructura, he tomado como referencia la siguiente secuencia de operaciones:

| Paso | Operación |
|:---:|---|
| 1 | `encolar(1)` |
| 2 | `encolar(2)` |
| 3 | `encolar(3)` |
| 4 | `desencolar()` |
| 5 | `encolar(4)` |
| 6 | `desencolar()` |
| 7 | `desencolar()` |

El orden de extracción esperado para esta secuencia es:

```text
1 → 2 → 3
```
