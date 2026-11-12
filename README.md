En esta segunda parte he revisado el diseño inicial de la cola presentado en la Parte 1.

He comprobado que utilizar una única pila no permite garantizar correctamente el comportamiento FIFO de una cola.

Por este motivo, he modificado la implementación utilizando dos pilas:

- `entrada`: almacena los elementos que se introducen.
- `salida`: permite extraerlos respetando el orden de llegada.

Además, he incorporado una clase `Pila.java` para implementar la estructura auxiliar necesaria.

Con este cambio consigo que la cola funcione correctamente siguiendo el comportamiento FIFO.

