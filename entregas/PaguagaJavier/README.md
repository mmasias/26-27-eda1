# Reto 002 - Nodo dummy

> Restricciones: Java *plain vanilla*. Parta de la [`ListaEnlazada`](https://github.com/mmasias/eda1/blob/main/src/listas/nodoDummy/ListaEnlazada.java) vista en [Nodo dummy](https://github.com/mmasias/eda1/blob/main/temario/999-otrosTemas/nodoDummy.md): lista simplemente enlazada de `int`, con referencia únicamente a la cabeza.

## Reto base

Implemente en `ListaEnlazada` el método `public void eliminarRepetidos()`.

La lista está ordenada ascendentemente. El método elimina **todos** los nodos cuyo valor aparezca más de una vez: no deja una copia de cada valor, los elimina todos.

<div align=center>

|Entrada|Salida|
|-|-|
|`1 -> 1 -> 2 -> 3 -> 3 -> 4`|`2 -> 4`|
|`1 -> 1 -> 1`|`null`|
|`1 -> 2 -> 2`|`1`|
|`1 -> 2 -> 3`|`1 -> 2 -> 3`|
|`5 -> 5 -> 6 -> 6`|`null`|
|`null`|`null`|

</div>

Implemente dos versiones: `eliminarRepetidos()`, con nodo dummy, y `eliminarRepetidosSinDummy()`, sin él. Ambas deben producir la misma salida en todos los casos de la tabla.

## Retos extendidos

Implemente en `ListaEnlazada` el método `public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b)`.

Dadas dos listas ordenadas ascendentemente, devuelve una lista ordenada con todos sus elementos.

- No se crean nodos nuevos, salvo el dummy: el resultado se construye reenlazando los nodos de `a` y `b`.
- Al terminar, `a` y `b` quedan vacías.

<div align=center>

|`a`|`b`|Resultado|
|-|-|-|
|`1 -> 4 -> 7`|`2 -> 3 -> 8 -> 9`|`1 -> 2 -> 3 -> 4 -> 7 -> 8 -> 9`|
|`null`|`2 -> 3`|`2 -> 3`|
|`null`|`null`|`null`|
|`1 -> 1`|`1`|`1 -> 1 -> 1`|

</div>

Justifique por qué `a` y `b` deben quedar vacías y qué ocurriría si no fuera así.

## Se debe entregar

- El código fuente, obligatorio: `Nodo`, `ListaEnlazada` con los métodos pedidos y una clase `Ejemplo` que ejecute todos los casos de las tablas, mostrando entrada y salida.
- Todos los demás artefactos, optativos.

Prepárese para defender su propuesta: en particular, qué casos obligan a la versión sin dummy a tratar la cabeza por separado.

## Pasos:

1. Se comienza creando las clases ListaEnlazada y Nodo, y se crea el metodo eliminarRepetidos y eliminarRepetidosSinDummy, y la clase Ejemplo se ha creado para confirmar que ambos metodos produzca la misma salida.