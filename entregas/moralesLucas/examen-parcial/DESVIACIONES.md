# Registro de desviaciones

## 1. Errores detectados

En mi diseño inicial planteé utilizar una única pila y un atributo entero para controlar el número de elementos, estableciendo un límite de 4.

El principal error fue no tener en cuenta que una pila funciona con LIFO y una cola con FIFO. Al apilar 1, 2 y 3, saldría primero el 3 en lugar del 1. Además, el atributo entero solo permite controlar la cantidad, pero no cambiar el orden de salida.

## 2. Correcciones realizadas

Para solucionar este problema, he cambiado mi estrategia y he utilizado dos pilas: una de entrada y otra de salida.

La pila de entrada almacena los nuevos elementos. Cuando la pila de salida está vacía, paso todos los elementos de entrada a salida, invirtiendo su orden. Así consigo que salga primero el elemento más antiguo.

También he mantenido el límite de 4 elementos mediante una constante y un atributo entero llamado cantidad.

## 3. Invariante

El invariante de mi implementación es que la variable cantidad representa el número total de elementos almacenados entre las dos pilas y nunca puede superar los 4 elementos.

## 4. Pruebas realizadas

He creado PruebaCola.java para reproducir la secuencia del examen:

encolar(1), encolar(2), encolar(3), desencolar(), encolar(4), desencolar(), desencolar().

He comprobado que las tres llamadas a desencolar() devuelven 1, 2 y 3 respectivamente. Además, he comprobado que el siguiente elemento es el 4 y que la cola termina vacía.