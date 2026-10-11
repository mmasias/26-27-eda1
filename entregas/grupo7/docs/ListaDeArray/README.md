# Simular una lista utilizando un array

## Contexto

Imagine que el lenguaje de programación **solo ofrece arrays**, es decir:  

- Estructuras de **tamaño fijo**.  
- Elementos del **mismo tipo**.  
- Acceso a través de **índices numéricos**.

El objetivo es **simular el comportamiento de una lista** utilizando un array: usar una estructura rígida para representar algo flexible.

## Parte 1: Identificación de diferencias

<div align=center>
|Característica|Array (comportamiento real)|Lista (comportamiento esperado)|¿Qué habría que simular o emular?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable|Habria que simular un array variable, es decir dinamico|
|**Inserción**|No permitida|Permitida en cualquier posición|simular un metodo para insertar en la posicion deseada|
|**Eliminación**|No permitida|Permitida en cualquier posición|simular un metodo para eliminar en la posicion deseada|
|**Espacio libre**|No considerado|Puede existir implícitamente|simular la lista para que no consuma espacio, sin embargo los espacios restantes del array quedaran vacios|
|**Acceso**|Directo por índice|Directo o secuencial|simular un metodo para acceder al elemento esperado|

</div>

## Parte 2: Cuestiones para el análisis

1. ¿Cómo podría representarse el número real de elementos de la lista dentro del array?  
   -Mediante la declaracion de una variable
2. ¿Qué debería ocurrir cuando el array se llena por completo?  
   -Al array estar en su maxima capacidad el programa debe crear un nuevo array expandido y trasladar los datos antiguos al nuevo array
3. ¿Cómo se simularía la inserción de un elemento en una posición intermedia?  
   -Mediante un metodo el cual pide la posicion solicitada, el metodo en este caso busca la posicion para colocar el elemento 
4. ¿Qué operaciones serían necesarias para eliminar un elemento sin dejar “huecos” lógicos?  
   -El uso del for y la cantidad total de elementos ayudan a esto sin problemas
5. ¿Qué mecanismos permitirían ocultar estos detalles al usuario, de modo que la estructura parezca verdaderamente dinámica?
   -Mediante el encapsulamiento de los metodos criticos

## Parte 3: Reflexión

1. ¿Qué tipo de “flexibilidad simulada” puede lograrse con una estructura fija? 
   -Se logra la ilusión de un tamaño elástico e infinito junto con la capacidad de realizar inserciones y eliminaciones arbitrarias sin que queden huecos
2. ¿Qué costos en tiempo o memoria implica mantener la ilusión de dinamismo?
   -Implica asumir un compromiso directo entre rendimiento y consumo de recursos
3. ¿Qué enseña este ejercicio sobre la diferencia entre **abstracción conceptual** y **limitación técnica**?  
   -Enseña que la abstracción conceptual define el qué, mientras que la limitación técnica define el cómo 
4. ¿Existen ejemplos en ingeniería donde se utilicen mecanismos similares para ofrecer una interfaz más flexible sobre una base rígida?
   -Si, el ejemplo mas común es la librería ArrayList
