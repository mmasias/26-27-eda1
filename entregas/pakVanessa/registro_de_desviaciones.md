# Registro de Desviaciones

## 1. Idea inicial

En el diseño teórico tuve la idea de implementar listas para organizar los elementos de la cola, ya que permiten almacenar y recorrer datos de forma ordenada.

## 2. Idea descartada

Decidí descartar el uso de listas porque el ejercicio establece que únicamente se pueden utilizar pilas como estructura de datos.

Además, comprobé que una cola se puede implementar utilizando pilas, sin necesidad de añadir listas, nodos u otras estructuras.

## 3. Elementos que mantuve del diseño teórico

### 3.1. Uso de pilas

Mantuve la idea de utilizar pilas para implementar la cola, aprovechando las operaciones de apilar y desapilar elementos.

### 3.2. Uso de repetidores

En el examen teórico propuse utilizar repetidores para recorrer, agregar y quitar elementos.

Esta idea se mantuvo en la implementación práctica mediante bucles `while`, que permiten trasladar y reorganizar los elementos entre las pilas.

De esta manera, se consigue mantener el orden de la cola sin utilizar otras estructuras de datos.

### 3.3. Funcionamiento de la cola

También mantuve la idea de implementar una cola siguiendo el principio **FIFO (First In, First Out)**, donde el primer elemento introducido es el primero en salir.

## 4. Desviaciones respecto al diseño teórico

### 4.1. Uso de listas

Inicialmente consideré utilizar listas, pero descarté esta idea porque no cumplía las restricciones del ejercicio.

### 4.2. Pila auxiliar

En el diseño teórico no especifiqué claramente el uso de una pila auxiliar.

Durante la implementación decidí utilizarla para reorganizar los elementos y mantener el orden FIFO de la cola.

### 4.3. Contador de elementos

Se añadió un contador de tipo `int` para controlar la cantidad de elementos almacenados y comprobar si la cola está vacía.

Este detalle no quedó especificado en el diseño teórico.

## 5. Justificación de las decisiones

La implementación mantiene las ideas principales que planteé en el examen teórico, especialmente el uso de pilas, las operaciones de apilar y desapilar y los repetidores.

La principal idea descartada fue utilizar listas, ya que la cola podía implementarse utilizando únicamente pilas.

Las decisiones adicionales permiten mantener el funcionamiento FIFO sin utilizar arrays, nodos ni recursión.

## 6. Conclusión

En la implementación práctica mantuve el uso de pilas y repetidores que había planteado en el diseño teórico.

Descarté la idea de utilizar listas porque no eran necesarias y no estaban permitidas por el enunciado.

Finalmente, utilicé las operaciones de las pilas y los bucles `while` para implementar el funcionamiento de una cola, dejando documentadas las diferencias respecto al diseño inicial.