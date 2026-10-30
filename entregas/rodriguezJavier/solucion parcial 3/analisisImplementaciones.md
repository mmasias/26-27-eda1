# 1. Análisis de implementaciones

## A. ¿Qué implementación utiliza mejor los recursos de memoria? Justifique su respuesta.

La implementación B utiliza mejor los recursos de memoria porque guarda directamente la referencia al objeto `Persona`, sin crear un objeto nuevo.

En cambio, la implementación A crea una copia mediante `new Persona(persona.getNombre(), persona.getDni())`, por lo que se crean dos objetos `Persona` diferentes con los mismos datos.


## B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

La implementación A tiene la ventaja de que los cambios realizados en la persona original no afectan a la copia almacenada en el nodo. Por ejemplo, si cambiamos el nombre de la persona original a "Juan Carlos", la copia seguirá llamándose "Juan".

Su inconveniente es que crea objetos adicionales y consume más memoria.

La implementación B evita crear copias y permite compartir el mismo objeto. Sin embargo, si se modifica la persona original, también cambia la información que contiene el nodo. Esto puede provocar modificaciones no deseadas.

## C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

Ambas implementaciones permiten construir una lista enlazada porque las dos guardan una referencia al siguiente nodo.

La diferencia está en cómo almacenan las personas. Con A, cada nodo contiene una copia independiente de la persona recibida. Con B, el nodo comparte el objeto original.

Por tanto, la inserción y el recorrido de la lista pueden funcionar de la misma manera, pero las modificaciones posteriores de las personas pueden producir resultados diferentes. 