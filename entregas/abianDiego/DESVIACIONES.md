# Pila con una cola - desviaciones y enmiendas

Diego Abián González - Parte 2

El código sigue mi diseño de la Parte 1 menos en tres cosas que me encontré al repasarlo, y que pongo abajo como enmiendas. Aparte de estas tres no hay más diferencias entre el código y mi diseño.

## E1 - "Invertir la cola" choca con mi invariante (09/11/2026)

Qué falla: en el punto 1 puse que la estrategia era invertir la cola, pero en el punto 2 puse que no va a variar el primer elemento de la cola, y si invertimos la cola de verdad, es decir, ponemos lo de arriba de la pila al principio de la cola, las dos cosas no pueden pasar a la vez.

Cómo lo comprobé: hice la traza de los dos primeros apilar. Con la cola invertida, apilar(1) deja [1] y apilar(2) deja [2, 1], así que el primer elemento ya cambia de 1 a 2 en el segundo apilar y se rompe el invariante. En cambio con la cola en el orden en el que van entrando los datos queda [1] y luego [1, 2], y el primero sigue siendo el 1. Además en PruebasPila, cuando acaba la secuencia lo único que nos queda es el 1, que es el primero que metimos, es decir, el principio de la cola sigue siendo el mismo que al empezar.

Qué cambia: le hago caso al invariante. La cola no se invierte nunca y los datos se quedan en el orden en el que entran, lo que va al revés es el orden en el que salen, y eso pasa dentro de desapilar(), que es justo donde lo pone mi punto 1 ("para ello cuando pase por public int desapilar()..."). Así se sigue cumpliendo lo importante del punto 1, que el último que entra es el primero que sale.

Lo de FIFO del invariante lo digo por la cola de dentro ("al ser una cola..."), que es FIFO siempre, y la pila vista desde fuera es LIFO.

## E2 - Puse "el primer valor" y es el último (09/11/2026)

Qué falla: en el punto 1 puse que desapilar() "tendrá que darnos el primer valor que hemos metido", y eso es lo que haría una cola, no una pila.

Aquí me lié al escribirlo en el examen, quería poner justo lo contrario. Me di cuenta al releerlo y con las comprobaciones de abajo ya terminé de darme la razón.

Cómo lo comprobé: en mi propio punto 3 el primer desapilar saca el 3 ("el último número que añadimos se nos va") y no el 1, que fue el primero que metimos. Y en PruebasPila el primer desapilar() devuelve 3.

Qué cambia: desapilar() saca el último que hemos metido, que es lo que cuadra con la frase de antes en el mismo punto ("el último dato que metamos será el primero en la pila") y con todo el punto 3.

## E3 - No dije cómo llega desapilar al último, añado int tamano (09/11/2026)

Qué falla: en mi diseño digo qué tiene que sacar desapilar() pero no cómo llega al último con una sola cola.

Cómo lo comprobé: con esta traza, dándole vueltas a la cola solo con desencolar(), encolar() y estaVacia():

```
cola [1, 2, 3], quiero sacar el 3
saco 1 -> quedan [2, 3]  vacia? no -> lo meto al final: [2, 3, 1]
saco 2 -> quedan [3, 1]  vacia? no -> lo meto al final: [3, 1, 2]
saco 3 -> quedan [1, 2]  vacia? no -> es el ultimo que entro? no hay forma de saberlo
```

Si hay más de un dato, al darle vueltas la cola nunca se queda vacía, así que estaVacia() no nos dice cuándo parar. Por lo tanto hace falta saber cuántos datos hay.

Qué cambia: añado el atributo int tamano (los atributos int están permitidos). Para ello desapilar() pasa al final los tamano - 1 de delante y saca el siguiente, el cual sería el último que entró. Al acabar, el principio de la cola vuelve a ser el mismo, así que el invariante de E1 se sigue cumpliendo.

Con esto el invariante queda así: la cola tiene los datos en el orden en el que entraron (el principio es lo de abajo del todo de la pila y el final lo de arriba del todo) y tamano es cuántos hay. Al final de apilar() y desapilar() hay un assert que comprueba la parte que se puede mirar sin tocar la cola, que es que tamano es 0 si y solo si la cola está vacía.

## Pruebas

PruebasPila hace la secuencia de mi punto 3 y comprueba lo que digo en cada paso. Se ejecuta con java -ea PruebasPila y salen todas OK.

Lo que digo en mi punto 3 y la comprobación que lo demuestra:

- El 2 va encima del 1 y el 3 "arriba de todo": sale primero el 3, y más tarde el 2 antes que el 1
- "el último número que añadimos se nos va": el 1er desapilar() devuelve 3
- Después de apilar(4), "el primer número abajo del todo el segundo en la mitad y el cuarto arriba del todo": los desapilar siguientes sacan 4, 2 y 1, en ese orden
- "en la primera se quite el cuarto número": el 2º desapilar() devuelve 4
- "la segunda vez que desapilas se iría ese segundo número": el 3er desapilar() devuelve 2
- "el tercer número no aparece porque se fue cuando desapilamos": el 3 no vuelve a salir, después solo salen 4, 2 y 1 y la pila se queda vacía
- "y solo nos quedaría el primer número": la pila no está vacía, sale el 1 y después ya sí está vacía

Esto es lo que pasa dentro de la cola con el código en esa secuencia. No cambia mi punto 3, es lo que van recorriendo las pruebas:

| Cliente | Acción | Estado |
|-|-|-|
| apilar(1) | encolar(1) | [1], tamano 1 |
| apilar(2) | encolar(2) | [1, 2], tamano 2 |
| apilar(3) | encolar(3) | [1, 2, 3], tamano 3 |
| desapilar() | paso el 1 y el 2 al final y desencolo | [1, 2], tamano 2, devuelve 3 |
| apilar(4) | encolar(4) | [1, 2, 4], tamano 3 |
| desapilar() | paso el 1 y el 2 al final y desencolo | [1, 2], tamano 2, devuelve 4 |
| desapilar() | paso el 1 al final y desencolo | [1], tamano 1, devuelve 2 |

Cuando acaba cada operación, si leemos la cola de principio a final es la pila de abajo arriba. Por ejemplo, después de apilar(4) queda [1, 2, 4], que es lo que puse: "el primer número abajo del todo el segundo en la mitad y el cuarto arriba del todo". Y al final queda [1], es decir, "solo nos quedaría el primer número".

Así queda en memoria después de apilar(4), con el 1 abajo del todo de la pila y el 4 arriba del todo:

```
Pila
  cola: [1, 2, 4]
  tamano: 3
```

## De mi diseño al código

Lo que escribí en la Parte 1 y dónde está cada cosa en el código:

- "invertir la cola que tenemos" (punto 1): una sola cola, Cola cola (ver E1)
- "todos los datos tienen que entrar por el final" (punto 2): apilar(v) hace cola.encolar(v)
- "tampoco va a variar el primer elemento de la cola" (punto 2): el principio de la cola es lo de abajo del todo de la pila y desapilar() lo deja como estaba
- "el último dato que metamos será el primero en la pila" (punto 1): desapilar() saca el último que hemos metido
- "tendrá que darnos el primer valor que hemos metido" (punto 1): saca el último (ver E2)
- No dije cómo llega desapilar() al último: int tamano (ver E3)
- Precondición de desapilar() (la pide el enunciado): assert !estaVacia()
