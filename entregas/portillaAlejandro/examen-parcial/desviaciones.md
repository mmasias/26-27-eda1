## 1. Errores detectados

En mi diseño inicial cometí los siguientes errores:

- Utilicé una única pila para implementar la cola, sin tener en cuenta que una pila funciona con LIFO y una cola con FIFO.
- Al apilar los elementos 1, 2 y 3, el primero en desapilarse sería el 3, cuando debería ser el 1.
- Pensé que utilizando un atributo entero para controlar los elementos podría solucionar el problema, pero este atributo solamente permite contar, no cambiar el orden de salida.

## 2. Correcciones realizadas

Para solucionar estos errores, he realizado los siguientes cambios:

- He sustituido la única pila por dos pilas: `entrada` y `salida`.
- Utilizo `entrada` para almacenar los elementos que encolo.
- Cuando `salida` está vacía, paso todos los elementos de `entrada` a `salida`, invirtiendo así su orden.
- De esta manera, consigo que salga primero el elemento más antiguo y respeto el comportamiento FIFO.
- Mantengo un atributo entero llamado `cantidad` para controlar el número de elementos y limitar la cola a cuatro.

## 3. Comprobaciones

He probado la secuencia del examen en `PruebaCola.java` y he comprobado que las llamadas a `desencolar()` devuelven correctamente 1, 2 y 3. También he comprobado que el último elemento es el 4 y que la cola termina vacía.

## 4. Desviación respecto al diseño inicial

He cambiado la implementación de una pila a dos pilas porque he comprobado que mi diseño inicial no garantizaba el comportamiento FIFO. He mantenido el límite de cuatro elementos y he respetado las restricciones del enunciado.