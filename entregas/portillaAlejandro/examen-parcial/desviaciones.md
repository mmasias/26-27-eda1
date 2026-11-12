
## 1. Errores detectados

En mi diseño inicial planteé implementar la cola utilizando una única pila y un atributo entero.

El principal error fue no tener en cuenta que una pila funciona con LIFO y una cola con FIFO. Por ejemplo, al introducir los elementos 1, 2 y 3 en una pila, saldría primero el 3, cuando debería salir el 1.

También comprobé que utilizar un entero para contar los elementos no solucionaba el problema, ya que no permitía cambiar su orden de salida.

## 2. Correcciones realizadas

Para solucionar estos problemas, he realizado los siguientes cambios:

- He sustituido la única pila por dos pilas: `entrada` y `salida`.
- Utilizo `entrada` para almacenar los elementos que encolo.
- Cuando `salida` está vacía, transfiero los elementos de `entrada` a `salida`, invirtiendo su orden para respetar FIFO.
- He añadido el atributo entero `cantidad` para controlar el número total de elementos.
- He añadido la constante `CAPACIDAD`, con valor 4, para limitar el número máximo de elementos.

## 3. Comprobaciones y restricciones

He añadido las siguientes comprobaciones mediante `assert`:

- Evitar introducir más de cuatro elementos.
- Evitar desencolar cuando la cola está vacía.

He creado `PruebaCola.java` para comprobar la secuencia del examen y verificar que las extracciones devuelven 1, 2 y 3 en el orden correcto. También compruebo que el último elemento es el 4 y que la cola termina vacía.

La implementación no utiliza arrays, nodos directamente ni recursividad, y mantiene los atributos dentro de los tipos permitidos por el enunciado.

## 4. Declaración final

La principal desviación respecto a mi diseño inicial ha sido pasar de una pila a dos pilas, ya que he comprobado que la estrategia original no garantizaba el comportamiento FIFO.

He mantenido la idea de controlar la cantidad de elementos mediante un entero y la capacidad máxima de cuatro elementos.
