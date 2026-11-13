# 📚 Implementación de Pilas y Colas

Implementación de estructuras de datos utilizando otras estructuras, aplicando los principios **FIFO** y **LIFO**, nodos enlazados y estructuras auxiliares.

## 1. Objetivo

El proyecto consiste en implementar dos estructuras de datos:

- **Cola:** utiliza `PilaBase` para mantener el comportamiento FIFO.
- **Pila:** utiliza `Cola` para mantener el comportamiento LIFO.

Además, se implementa `PilaBase` mediante nodos enlazados.

## 2. Clases del proyecto

| Clase | Responsabilidad |
|---|---|
| `Main` | Ejecutar y mostrar las pruebas. |
| `Cola` | Implementar una cola utilizando una pila. |
| `Pila` | Implementar una pila utilizando una cola. |
| `PilaBase` | Implementar una pila mediante nodos enlazados. |
| `Node` | Almacenar valores y referencias al siguiente nodo. |

## 3. Diagrama general de clases

```mermaid
classDiagram
    class Main {
        +main(String[] args)
        -probarCola()
        -probarPila()
    }

    class Cola {
        -PilaBase pila
        +encolar(int valor)
        +desencolar() int
        +estaVacia() boolean
    }

    class Pila {
        -Cola cola
        +apilar(int valor)
        +desapilar() int
        +estaVacia() boolean
    }

    class PilaBase {
        -Node top
        -int size
        +apilar(int value)
        +desapilar() int
        +estaVacia() boolean
    }

    class Node {
        -int value
        -Node next
    }

    Main ..> Cola : utiliza
    Main ..> Pila : utiliza
    Cola --> PilaBase : utiliza
    Pila --> Cola : utiliza
    PilaBase --> Node : enlaza
```

## 4. Funcionamiento de las estructuras

### Cola utilizando una pila

La cola aplica el principio **FIFO (First In, First Out)**: el primer elemento en entrar es el primero en salir.

Para desencolar, se crea una pila auxiliar temporal, se transfieren los elementos, se extrae el más antiguo y se restauran los elementos restantes.

```mermaid
flowchart TD
    A(["desencolar()"]) --> B{"¿Está vacía?"}
    B -->|Sí| C["Error de precondición"]
    B -->|No| D["Crear pila auxiliar"]
    D --> E["Transferir elementos"]
    E --> F["Extraer el elemento más antiguo"]
    F --> G["Restaurar los elementos restantes"]
    G --> H["Devolver el elemento extraído"]
    H --> I(["Fin"])
```

### Pila utilizando una cola

La pila aplica el principio **LIFO (Last In, First Out)**: el último elemento en entrar es el primero en salir.

Para desapilar, se crea una cola auxiliar temporal, se transfieren todos los elementos excepto el último, se conserva ese último como resultado y se restauran los demás.

```mermaid
flowchart TD
    A(["desapilar()"]) --> B{"¿Está vacía?"}
    B -->|Sí| C["Error de precondición"]
    B -->|No| D["Crear cola auxiliar"]
    D --> E["Extraer elementos de la cola"]
    E --> F{"¿Quedan elementos?"}
    F -->|Sí| G["Guardar en cola auxiliar"]
    G --> E
    F -->|No| H["Conservar el último elemento"]
    H --> I["Restaurar los elementos restantes"]
    I --> J["Devolver el elemento extraído"]
    J --> K(["Fin"])
```
