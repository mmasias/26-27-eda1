## Examen Parcial
#### Colas con pilas

El codigo entregado en el archivo ExamenParcial.java es el mismo que se plantea en el diseño congelado, sin embargo este código, a pesar de funcionar correctamente, tiene un problema que hace que no cumpla con su proposito. Este problema se debe a que, al usar el metodo encolar(valor), el valor pasa de la pila intermedia directamente a la pila final, lo que hace que la pila intermedia no cumpla con la funcion que se le planteaba dar. Este enfoque podria funcionar si entrasen todos los valores de la cola a la vez y no entrase otra serie de valores hasta no haber sacado todos los valores metidos en primer lugar, sin embargo este no es el resultado que se planteaba tener, ya que no funciona como una cola.

Una propuesta para resolver este problema se plantea en Correccion.java, esta solucion consiste en:

1. El metodo encolar(valor) solo manda los valores a la pilaIntermedia y no pasan a la pilaFinal como si que ocurría antes,

2. El metodo desencolar() ahora:
   1. Pasa todos los objetos almacenados en la pilaIntermedia a la pilaFinal.
   2. Usa el metodo pilaFinal.desapilar().
   3. Pasa todos los objetos almacenados en la pilaFinal a la pilaIntermedia.

Al hacer todos estos cambios, la cola intermedia recupera el proposito que le queriamos dar y la interaccion entre ambas pilas consigue el objetivo que buscabamos, comportandose como si fuesen una cola.