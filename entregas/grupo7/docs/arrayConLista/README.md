# Simular un array utilizando una lista

## Contexto

Imagine que el lenguaje de programación **no dispone de arrays**, pero sí permite el uso de **listas**.  
Una lista es una estructura dinámica que puede crecer o reducirse, mientras que un array es una estructura de tamaño fijo y posiciones contiguas.

El objetivo es **simular el comportamiento de un array utilizando una lista**, es decir, usar una estructura flexible para comportarse como una rígida.

## Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento esperado)|Lista (comportamiento real)|¿Qué habría que imponer o restringir?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable|Inicializa una cantidad fija en el constructor|
|**Tipo de dato**|Homogéneo|Posiblemente heterogéneo|Para este retro restringir un solo tipo de dato, en este caso se uso el tipo int|
|**Inserción y eliminación**|No permitidas|Permitidas|Restringir esta estructura |
|**Acceso**|Por índice directo|Por índice o recorrido||
|**Posiciones en memoria**|Contiguas|No necesariamente contiguas||

</div>

## Parte 2: Cuestiones para el análisis

1. Si una lista puede crecer y reducirse, ¿cómo podría fijarse un tamaño constante?
- Respuesta despues de debatir: Un limite de **N** elementos.   

2. ¿Qué mecanismos conceptuales permitirían “bloquear” la inserción o eliminación de elementos?
- Respuesta despues de debatir: Usando **POO** ya que no se expone ningun metodo para alterar la cantidad o eliminacion de elementos. 

3. ¿Cómo se garantizaría la homogeneidad de tipos dentro de la lista?
- Respuesta despues de debatir: mediante la declaracion universal de un solo tipo de valor en el nodo

4. ¿Qué implicaciones tendría acceder siempre por índice, incluso si la lista internamente no está organizada de forma contigua?
- Respuesta despues de debatir: Los valores dentro del lista que conforma el array pueden definirse fuera   

5. ¿Qué se pierde y qué se gana al imponer a una lista el comportamiento de un array?
- Respuesta despues de debatir: Se pierde la velocidad, pero se gana el acceso aleatoreo 


## Parte 3: Reflexión

1. ¿Hasta qué punto una estructura flexible puede comportarse de manera disciplinada como una estructura rígida?  
   -Puede comportarse de forma totalmente disciplinada a nivel lógico y funcional, pero nunca a nivel físico o de rendimiento.
2. ¿Qué dice este ejercicio sobre la relación entre **naturaleza de una estructura** y **modo de uso**?  
   -Que el modo de uso se puede aplicar a cualquier ejercicio, pero la natureleza sera afectada al ir en contra de su obejtivo y perdera eficiencia
3. ¿Cuándo podría ser útil una simulación de este tipo en un contexto real de programación o diseño de sistemas?
   -En las APIs restringidas y en los sistemas de memoria fragmentadas