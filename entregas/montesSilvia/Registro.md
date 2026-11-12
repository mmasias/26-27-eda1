No se han realizado desviaciones respecto al diseño congelado en la parte 1.
La implementación utiliza dos pilas (principal y auxiliar ) , tal y como se estableció en el diseño original.

encolar(int valor): introduce directamente el elemento en la pila principal

desencolar (): transfiere todos los elementos a la pila auxiliar , extrae el elemento más antiguo y devuelve los restantes a la pila principal.

estaVacia(): comprueba si la pila principal está vacía.

Se respetan todas las restricciones del enunciado: no se utilizan arrays, nodos , estructuras de datos adicionales ni recursividad. 


Razonamientos sólidos:
1. Conservación del orden de los elementos: después de extraer el primer elemento, los restantes se devuelven a la pila principal. Esto permite conservar el orden FIFO de la cola para las siguientes operaciones.
2. Uso de la pila auxiliar: la pila auxiliar queda vacía al finalizar cada operación pública. Así se mantiene el invariante de la estructura y se puede reutilizar en el siguiente desencolado.
3. Correcto funcionamiento FIFO: aunque las pilas funcionan con el criterio LIFO , al transferir los elementos de la pila principal a la auxiliar se invierte su orden. De esta manera, el elemento que lleva más tiempo en la cola queda en la cima de la pila auxiliar y es el primero en desencolarse. 
