fusionar no copia nodos: los reenlaza. Los nodos del resultado son los
mismos objetos que antes pertenecían a a y b.

Si a y b conservaran su cabeza, seguirían apuntando a nodos que ahora
están dentro del resultado. Esto provoca varios problemas:

1. Listas que comparten nodos. Una lista que debería estar vacía
   (estaVacia() devolvería false) estaría enganchada a la mitad del
   resultado.

2. Contenido incorrecto. Con a = 1 -> 4 -> 7 y b = 2 -> 3 -> 8 -> 9,
   el resultado es 1 -> 2 -> 3 -> 4 -> 7 -> 8 -> 9. Si a siguiera
   apuntando al nodo 1, al imprimirla mostraría el resultado entero, y
   b (apuntando al nodo 2) mostraría 2 -> 3 -> 4 -> 7 -> 8 -> 9. Ninguna
   de las dos mostraría sus elementos originales.

3. Corrupción por efecto lateral. Si se modifica a (agregarAlFinal,
   eliminarRepetidos...), se altera también el resultado, porque son los
   mismos nodos.

4. Riesgo de ciclos. Si se reutilizara a o b en otra fusión, se
   reenlazarían nodos que ya forman parte de otra cadena, y podrían
   quedar nodos apuntándose entre sí, formando un bucle infinito.

Al poner a.cabeza = null y b.cabeza = null, cada nodo pertenece a una
sola lista (el resultado) y no queda estado compartido.