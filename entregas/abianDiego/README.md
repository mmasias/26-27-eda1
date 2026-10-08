# Reto 002 - Nodo dummy

Solución partiendo de la [`ListaEnlazada`](https://github.com/mmasias/eda1/blob/main/src/listas/nodoDummy/ListaEnlazada.java) vista en [Nodo dummy](https://github.com/mmasias/eda1/blob/main/temario/999-otrosTemas/nodoDummy.md): lista simplemente enlazada de `int`, con referencia únicamente a la cabeza.

<div align=center>

|Fichero|Contenido|
|-|-|
|[Nodo.java](src/listas/nodoDummy/Nodo.java)|Nodo con un `int` y la referencia al siguiente (sin cambios).|
|[ListaEnlazada.java](src/listas/nodoDummy/ListaEnlazada.java)|Métodos originales + `eliminarRepetidos()`, `eliminarRepetidosSinDummy()` y `fusionar(a, b)`.|
|[Ejemplo.java](src/listas/nodoDummy/Ejemplo.java)|Ejecuta todos los casos de las tablas del enunciado, mostrando entrada y salida.|

</div>

## Ejecución

Desde `src/`:

```bash
javac listas/nodoDummy/*.java
java listas.nodoDummy.Ejemplo
```

## Reto base: `eliminarRepetidos()`

La lista está ordenada, así que los valores repetidos aparecen **consecutivos**. El puntero `actual` se sitúa siempre en el **predecesor** del bloque que se evalúa:

- Si `actual.siguiente` y `actual.siguiente.siguiente` tienen el mismo valor, ese valor está repetido: se guarda en `repetido` y se saltan **todos** los nodos con ese valor (`actual.siguiente = actual.siguiente.siguiente`). `actual` no avanza, porque el siguiente bloque también podría estar repetido.
- Si no, el nodo es único y `actual` avanza.

|Con dummy|Sin dummy|
|-|-|
|`actual` arranca en el dummy, así que la cabeza tiene predecesor como cualquier otro nodo. Un único bucle y, al final, `cabeza = dummy.siguiente`.|La cabeza no tiene predecesor: hace falta un bucle previo que la trate por separado, un `return` si la lista se queda vacía y, después, el mismo bucle general.|

### Qué casos obligan a la versión sin dummy a tratar la cabeza por separado

<div align=center>

|Entrada|¿Por qué?|
|-|-|
|`1 -> 1 -> 2 -> 3 -> 3 -> 4`|El primer bloque repetido está en la cabeza: no hay nodo anterior cuyo `siguiente` modificar, hay que mover `cabeza`.|
|`1 -> 1 -> 1`|La lista entera desaparece: `cabeza` acaba en `null` y, sin el `return`, `actual = cabeza` sería `null` y `actual.siguiente` lanzaría `NullPointerException`.|
|`5 -> 5 -> 6 -> 6`|Tras borrar el bloque de `5`, la **nueva** cabeza (`6`) también está repetida. Por eso el tratamiento de la cabeza es un `while` y no un `if`.|
|`null`|Lista vacía: la guarda `cabeza != null` y el `return` evitan acceder a `cabeza.siguiente`.|

</div>

En `1 -> 2 -> 2` y `1 -> 2 -> 3` la cabeza no cambia, pero el código sin dummy igualmente tiene que comprobarla aparte antes de poder usarla como predecesor. Con dummy, todos estos casos los resuelve el mismo bucle.

## Reto extendido: `fusionar(a, b)`

- Se usa un dummy como inicio de la lista resultado y un puntero `ultimo` al final de lo ya fusionado.
- Mientras quedan nodos en ambas listas, se **reenlaza** el menor (`ultimo.siguiente = actualX`) y se avanza. Con `<=`, ante empate va primero el de `a` (fusión estable).
- Cuando una se agota, se engancha de golpe el resto de la otra: ya está ordenado.
- No se crea ningún nodo salvo el dummy: el resultado está hecho con los mismos nodos de `a` y `b`.
- Gracias al dummy no hace falta un caso especial para decidir quién es la cabeza del resultado: `resultado.cabeza = dummy.siguiente`.

### Por qué `a` y `b` deben quedar vacías

Porque los nodos **no se copian, se mueven**. Después de fusionar, cada nodo de `a` y de `b` forma parte del resultado y su campo `siguiente` ha sido reescrito. Si `a.cabeza` y `b.cabeza` siguieran apuntando a sus antiguos primeros nodos, tres listas compartirían los mismos nodos:

Ejecutando `fusionar` **sin** vaciar `a` y `b` con `a = 1 -> 4 -> 7` y `b = 2 -> 3 -> 8 -> 9`:

```
resultado: 1 -> 2 -> 3 -> 4 -> 7 -> 8 -> 9 -> null
a:         1 -> 2 -> 3 -> 4 -> 7 -> 8 -> 9 -> null
b:         2 -> 3 -> 4 -> 7 -> 8 -> 9 -> null
a.eliminarPorValor(8)
resultado: 1 -> 2 -> 3 -> 4 -> 7 -> 9 -> null
b:         2 -> 3 -> 4 -> 7 -> 9 -> null
```

- `a` y `b` ya no contienen lo que contenían: `a` "tiene" los elementos de `b` y viceversa, porque los enlaces se han reescrito.
- Cualquier modificación sobre una de ellas afecta silenciosamente a las demás: borrar el `8` en `a` lo borra también del resultado y de `b`.
- Se rompe la idea de que cada lista es dueña de sus nodos; son errores difíciles de detectar porque no hay excepción, solo datos incorrectos.

Dejar `a` y `b` vacías hace explícito que sus nodos han pasado a pertenecer al resultado: la operación **transfiere** los nodos, no los comparte.
