---

## Desviaciones respecto al diseño original

Durante el desarrollo de la implementación de `Pila.java` se han detectado algunas diferencias entre el planteamiento inicial de la Parte 1 y el código necesario para conseguir que funcione correctamente en Java.

Estas desviaciones no pretenden cambiar el objetivo del diseño original, sino solucionar las limitaciones técnicas encontradas durante su implementación.

### 1. Representación de la pila vacía

En el diseño inicial se utilizaba `null` para indicar que no había ningún elemento en la pila.

Sin embargo, el atributo `ultimo` es de tipo `int`, por lo que no puede almacenar ese valor.

**Desviación:** Se utiliza el valor `0` como inicialización de `ultimo` y se comprueba el estado de la pila mediante `cola.estaVacia()`.

### 2. Actualización del último elemento

En el planteamiento original se indicaba que el atributo `ultimo` debía actualizarse cuando se introducía o extraía un elemento.

Durante la implementación se comprobó que era necesario reorganizar los elementos de la cola para mantener el comportamiento LIFO de una pila.

**Desviación:** Se utilizan las operaciones de encolar y desencolar para mantener el elemento superior en la posición adecuada.

### 3. Uso del atributo `posicionEnCola`

En el diseño inicial se contemplaba este atributo, pero no se especificaba completamente su funcionamiento.

**Desviación:** Se utiliza como contador del número de elementos almacenados, permitiendo controlar los recorridos y las rotaciones de la cola.

### 4. Comprobación del método `desapilar()`

En el diseño original no quedaba completamente especificado qué debía ocurrir cuando se extraía un elemento y la pila quedaba vacía.

**Desviación:** Se incorpora la comprobación mediante `assert !estaVacia()` y se actualiza `ultimo` cuando se elimina el último elemento.

### Valoración final de las desviaciones

Las modificaciones descritas se consideran ajustes técnicos necesarios para adaptar el diseño inicial a las características de Java y a las operaciones disponibles en la clase `Cola`.

Se mantiene el objetivo original del ejercicio: implementar una pila utilizando exclusivamente una cola, respetando el funcionamiento LIFO y las restricciones establecidas en el examen.