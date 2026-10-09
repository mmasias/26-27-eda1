# Reto 002 - Nodo dummy

## Ejecución

```
javac *.java
java Ejemplo
```

## Reto base

- `eliminarRepetidos()`: el nodo dummy se coloca antes de la cabeza, así eliminar el primer nodo es igual que eliminar cualquier otro. Al final, `cabeza = dummy.siguiente`.
- `eliminarRepetidosSinDummy()`: sin dummy, la cabeza no tiene nodo anterior, así que hay que tratarla aparte con un bucle previo.

Casos que obligan a tratar la cabeza por separado en la versión sin dummy:

- `1 -> 1 -> 2 -> 3 -> 3 -> 4`: la cabeza es repetida y cambia a `2`.
- `1 -> 1 -> 1`: se elimina todo, la cabeza pasa a `null`.
- `5 -> 5 -> 6 -> 6`: tras borrar los `5`, la nueva cabeza `6` también es repetida; por eso el tratamiento de la cabeza es un bucle y no un `if`.
- `null`: lista vacía, hay que comprobarla antes de acceder a `cabeza.siguiente`.

## Reto extendido: `fusionar`

El resultado se construye reenlazando los nodos de `a` y `b` tras un dummy; no se crean nodos nuevos.

### ¿Por qué `a` y `b` deben quedar vacías?

Porque los nodos ya no les pertenecen: ahora forman parte del resultado. Si `a` y `b` conservaran su cabeza, tres listas compartirían los mismos nodos. Entonces:

- Modificar `a` (por ejemplo, `a.eliminarRepetidos()` o `a.agregar(5)`) cambiaría también el resultado sin querer.
- `a` mostraría datos incorrectos: su cabeza apunta a nodos que ahora siguen el orden de la fusión e incluyen elementos de `b`.
- Se rompe la idea de que cada lista es dueña de sus nodos.

Dejar `a.cabeza = null` y `b.cabeza = null` deja claro que los nodos se han transferido al resultado.

## Punto de partida

Se parte de `Nodo` y `ListaEnlazada` del ejemplo [Nodo dummy](https://github.com/mmasias/eda1/tree/main/src/secuencias/listas/nodoDummy) (campo `dato`, métodos `imprimirLista`, `insertarEnPosicion`, `eliminarPorValor` y sus versiones sin dummy). Solo se elimina la línea `package` para compilar en esta carpeta, y se añaden `eliminarRepetidos()`, `eliminarRepetidosSinDummy()` y `fusionar(a, b)`.
