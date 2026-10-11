# Reto 003

<div align=center>

|||
|-|-|
En programación, las estructuras de datos no solo se utilizan: también pueden **simularse**.  |Simular una estructura consiste en **reproducir su comportamiento esencial** empleando otra que no posee exactamente las mismas propiedades.

</div>

Este ejercicio propone dos direcciones de simulación opuestas:

1. **Simular un array utilizando una lista.** [🧠](arrayConLista/README.md)
2. **Simular una lista utilizando un array.** [🧠](listaConArray/README.md)

En ambos casos se busca razonar sobre **qué define realmente a cada estructura**, **qué reglas habría que imponer o relajar**, y **qué se gana o se pierde** al hacerlo.

## Conclusión

<div align=center>

|||
|-|-|
Ambas simulaciones —una lista que finge ser un array y un array que finge ser una lista— revelan la tensión entre **estructura y comportamiento**.|El análisis permite observar que lo esencial en una estructura de datos no es solo su implementación, sino **las reglas de uso y las expectativas de quien la emplea**.

</div>

Se recomienda discutir en grupo las siguientes cuestiones:

- ¿Qué diferencia hay entre “ser” una estructura y “actuar como” una estructura?  
- ¿Qué papel juega la abstracción en la evolución de los tipos de datos?  
- ¿Qué enseñanzas de diseño pueden extraerse de estas simulaciones para la ingeniería de software?