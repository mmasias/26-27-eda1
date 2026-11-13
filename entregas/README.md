# Cola sobre Pila y Pila sobre Cola

## Integrantes

- Marcos Bolivar
- Adriana Aguilar
- Nicolas Crespo
- Marcos Huidobro

## Qué hace el código

Cada estructura se construye usando solo la vista pública de la anterior:

```
Pila (del profesor)  →  Cola (FIFO sobre Pila)  →  PilaSegunda (LIFO sobre Cola)
```

- **`Cola`:** `encolar` apila el valor. Para `desencolar`, pasa todos los datos a una pila auxiliar (así el primero queda arriba), lo saca y devuelve el resto a la pila original.
- **`PilaSegunda`:** `apilar` encola el valor. Para `desapilar`, rota `tamano - 1` datos del frente al final de la cola, de modo que el último queda al frente, y lo desencola.

| Operación | Coste |
|---|---|
| `Cola.encolar` / `PilaSegunda.apilar` | O(1) |
| `Cola.desencolar` | O(n) |
| `PilaSegunda.desapilar` | O(n²) (n rotaciones, cada una con un `desencolar` O(n)) |


