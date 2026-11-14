# Reto 005 - Uroboro vII

Equipo: Diego Abián, Victor Arenas y Sergio del Rio. La Cola y la pila de abajo las hicimos parecidas entre los tres, pero la Pila es la de mi examen.

En el examen parcial hice la Pila con una Cola, y lo que hemos hecho en este reto es que esa Cola que usa mi Pila también esté hecha con pilas.

# 1. Cómo queda

```
Pila -> Cola -> PilaConNodos -> Nodo
```

- Pila: la de mi examen, tal cual, sin cambiar nada. Tiene una Cola y un int tamano.
- Cola: la nueva, la cual tiene dos pilas, entrada y salida.
- PilaConNodos: una pila normal hecha con nodos, como la que vimos en clase.
- Nodo: su valor y el siguiente.

# 2. El problema del uroboro

Si la Cola usara mi misma Pila, al hacer new Pila() se crearía una Cola, esa Cola crearía dos Pila, y cada Pila crearía otra Cola, y así todo el rato sin parar nunca. Lo probé y nada más crear la primera Pila sale StackOverflowError:

```
new Pila() -> new Cola() -> new Pila() -> new Cola() -> ... -> StackOverflowError
```

Por eso la Cola no usa mi Pila sino PilaConNodos, debido a que esa no necesita ninguna otra estructura por debajo y es la que corta el ciclo.

# 3. La Cola con dos pilas

- encolar(v): apilamos v en entrada.
- desencolar(): si salida está vacía, volcamos toda la entrada en salida, es decir, vamos desapilando de una y apilando en la otra, y así los datos se quedan dados la vuelta. Después desapilamos de salida.
- estaVacia(): está vacía cuando las dos pilas están vacías.

Invariante: el primero de la cola es lo de arriba del todo de salida, y si salida está vacía, es lo de abajo del todo de entrada.

Traza con la misma secuencia que la del examen pero de cola (lo de arriba del todo de cada pila es lo de la derecha):

```
encolar(1)    mete 1    entrada [1]        salida []
encolar(2)    mete 2    entrada [1, 2]     salida []
encolar(3)    mete 3    entrada [1, 2, 3]  salida []
desencolar()  vuelca    entrada []         salida [3, 2, 1]
              saca 1    entrada []         salida [3, 2]
encolar(4)    mete 4    entrada [4]        salida [3, 2]
desencolar()  saca 2    entrada [4]        salida [3]
desencolar()  saca 3    entrada [4]        salida []
```

Solo volcamos cuando salida está vacía, porque si volcáramos siempre, el 4 se pondría encima del 2 y saldría antes de lo que le toca.

# 4. Alternativa que descarto

Volcar toda la entrada en salida en cada desencolar() y luego devolverlo todo otra vez a entrada. Funcionaría, pero en cada desencolar() se mueven todos los datos dos veces, y en cambio con mi forma cada dato pasa de entrada a salida como mucho una vez.

# 5. Pruebas

En Pruebas.java están las dos:

- La Cola: hace la traza de arriba y comprueba que salen el 1, el 2 y el 3, que luego sale el 4 y que se queda vacía.
- La Pila: la misma secuencia del examen, que ahora pasa por toda la cadena (Pila -> Cola -> PilaConNodos), sigue sacando el 3, el 4, el 2 y el 1, y se queda vacía.

Las comprobaciones son con if y no con assert, para que funcionen aunque no se ponga -ea.

```
javac -encoding UTF-8 *.java
java Pruebas
```
