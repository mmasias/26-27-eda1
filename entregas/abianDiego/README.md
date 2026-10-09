# Reto 003 - Simulación

**Equipo 1:** Diego Abián, Sergio del Rio y Victor Arenas.

Se resuelven los dos casos:

1. [Simular un array utilizando una lista](#caso-1-simular-un-array-utilizando-una-lista): partir de una lista enlazada y **quitarle** lo que un array no permite.
2. [Simular una lista utilizando un array](#caso-2-simular-una-lista-utilizando-un-array): partir de un array y **añadirle** lo que una lista sí permite.

<div align=center>

|Fichero|Contenido|
|-|-|
|[arrayConLista/Nodo.java](src/arrayConLista/Nodo.java)|Nodo con un `int` y la referencia al siguiente.|
|[arrayConLista/ArrayConLista.java](src/arrayConLista/ArrayConLista.java)|`ArrayConLista(longitud)`, `longitud()`, `obtener(posicion)` y `asignar(posicion, valor)`.|
|[arrayConLista/Ejemplo.java](src/arrayConLista/Ejemplo.java)|Hace las mismas operaciones con un `int[]` y con un `ArrayConLista` y muestra que el resultado es el mismo.|
|[listaConArray/ListaConArray.java](src/listaConArray/ListaConArray.java)|`insertarAlFinal(valor)`, `insertar(posicion, valor)`, `eliminar(posicion)`, `obtener(posicion)`, `longitud()` y `estaVacia()`.|
|[listaConArray/Ejemplo.java](src/listaConArray/Ejemplo.java)|Hace las mismas operaciones con un `ArrayList` y con una `ListaConArray` y muestra que el resultado es el mismo.|

</div>

## Ejecución

Desde `src/` (con `-ea` para que se comprueben los `assert`):

```bash
javac arrayConLista/*.java listaConArray/*.java
java -ea arrayConLista.Ejemplo
java -ea listaConArray.Ejemplo
```

# Caso 1: Simular un array utilizando una lista

Se parte de una lista simplemente enlazada de `int` (con referencia a la cabeza) y se le quita todo lo que un array no permite: solo puede crearse con un tamaño, leer una posición y cambiar una posición.

## Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento esperado)|Lista (comportamiento real)|¿Qué habría que imponer o restringir?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable|El constructor recibe la longitud y crea ya todos los nodos (a `0`, como un `int[]`). No hay ningún método que añada o quite nodos.|
|**Tipo de dato**|Homogéneo|Posiblemente heterogéneo|El `dato` del nodo se declara de un tipo concreto (`int`), no `Object`. El compilador impide guardar otra cosa.|
|**Inserción y eliminación**|No permitidas|Permitidas|No se ofrecen. `asignar` cambia el **valor** de un nodo que ya existe, nunca los enlaces.|
|**Acceso**|Por índice directo|Por índice o recorrido|Solo se accede por posición (`obtener` / `asignar`), de `0` a `longitud - 1`, comprobado con `assert`.|
|**Posiciones en memoria**|Contiguas|No necesariamente contiguas|No se puede imponer, solo ocultar: el usuario solo ve posiciones, pero internamente hay que recorrer los nodos hasta llegar a la pedida.|

</div>

```
asignar(1, 9)

antes:    cabeza → [4] → [7] → [2] → null
después:  cabeza → [4] → [9] → [2] → null     mismos nodos y mismos enlaces; solo cambia el dato
```

## Parte 2: Cuestiones para el análisis

**1. Si una lista puede crecer y reducirse, ¿cómo podría fijarse un tamaño constante?**

Creando todos los nodos en el constructor y guardando la longitud. Como la clase no tiene ningún método que añada o quite nodos, ese número ya no puede cambiar. Además, `cabeza` y `longitud` son `final`.

**2. ¿Qué mecanismos conceptuales permitirían "bloquear" la inserción o eliminación de elementos?**

La encapsulación: `cabeza` es `private` y `Nodo` no es pública, así que desde fuera solo se puede usar lo que la clase ofrece (`longitud`, `obtener` y `asignar`). La inserción y la eliminación no se bloquean con un `if`: simplemente no existen.

**3. ¿Cómo se garantizaría la homogeneidad de tipos dentro de la lista?**

Declarando el `dato` con un tipo concreto. Con `int dato`, intentar guardar un `String` no compila: lo garantiza el compilador, no el programa en ejecución.

**4. ¿Qué implicaciones tendría acceder siempre por índice, incluso si la lista internamente no está organizada de forma contigua?**

Que cada `obtener(i)` o `asignar(i, valor)` tiene que empezar en la cabeza y avanzar `i` nodos. En un array el acceso es **O(1)**; aquí es **O(n)**. Recorrer todo el "array" con un `for` y `obtener(i)` pasa a ser **O(n²)**, porque cada vuelta vuelve a empezar desde la cabeza. Por fuera se usa igual que un array, pero es más lento.

**5. ¿Qué se pierde y qué se gana al imponer a una lista el comportamiento de un array?**

<div align=center>

|Se gana|Se pierde|
|-|-|
|Reglas claras: nadie puede cambiar el tamaño ni el tipo.|El acceso directo: de O(1) a O(n).|
|Poder trabajar "como con un array" en un lenguaje que no los tiene.|La flexibilidad de la lista: ya no puede crecer ni reducirse.|
|No hace falta un bloque de memoria contiguo.|Memoria: cada nodo guarda además la referencia `siguiente`.|

</div>

## Parte 3: Reflexión

**1. ¿Hasta qué punto una estructura flexible puede comportarse de manera disciplinada como una estructura rígida?**

Por fuera, del todo: ofrece las mismas operaciones y cumple las mismas reglas que un array. Por dentro sigue siendo una lista, y eso se nota en el coste: imita el comportamiento, pero no el rendimiento.

**2. ¿Qué dice este ejercicio sobre la relación entre naturaleza de una estructura y modo de uso?**

Que quien usa una estructura solo ve lo que se le deja hacer con ella. `ArrayConLista` no *es* un array, pero *actúa como* uno porque cumple sus reglas. La naturaleza interna no decide qué se puede hacer, sino cuánto cuesta hacerlo.

**3. ¿Cuándo podría ser útil una simulación de este tipo en un contexto real de programación o diseño de sistemas?**

- En lenguajes o entornos que solo ofrecen listas.
- Cuando la memoria está fragmentada y no hay un bloque contiguo lo bastante grande.
- Cuando se quiere entregar a otra parte del programa una colección que no pueda cambiar de tamaño. Java lo hace con `Arrays.asList(...)`: devuelve una lista de tamaño fijo, que permite `set` pero lanza una excepción con `add` o `remove`.

# Caso 2: Simular una lista utilizando un array

Se parte de un array de `int` (tamaño fijo) y se le añade lo que una lista sí permite: crecer, insertar y eliminar en cualquier posición. La clave es separar dos números: la **capacidad** (casillas que tiene el array) y la **longitud** (elementos que hay de verdad).

```
capacidad = 4, longitud = 3

elementos → [10][20][30][ 0]
              0   1   2   3
             └── ocupadas ──┘└ libre: existe, pero el usuario no la ve
```

## Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento real)|Lista (comportamiento esperado)|¿Qué habría que simular o emular?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable|Un contador `longitud`, distinto de `elementos.length`. Cuando el array se llena, se crea otro con el doble de casillas y se copian los elementos.|
|**Inserción**|No permitida|Permitida en cualquier posición|Abrir hueco: mover una casilla a la derecha todos los elementos desde la posición hasta el final, escribir el valor y sumar 1 a `longitud`.|
|**Eliminación**|No permitida|Permitida en cualquier posición|Cerrar hueco: mover una casilla a la izquierda todos los elementos que había detrás y restar 1 a `longitud`.|
|**Espacio libre**|No considerado|Puede existir implícitamente|Las casillas desde `longitud` hasta `elementos.length - 1` están libres: se reservan para crecer sin copiar, pero no se muestran.|
|**Acceso**|Directo por índice|Directo o secuencial|Se conserva el acceso directo (O(1)), pero solo a las posiciones ocupadas (de `0` a `longitud - 1`), comprobado con `assert`.|

</div>

```
insertar(1, 15)

antes:          [10][20][30][ 0]   longitud = 3
abrir hueco:    [10][20][20][30]   el 30 y el 20 se mueven a la derecha (empezando por el final)
escribir:       [10][15][20][30]   longitud = 4

eliminar(1)

antes:          [10][15][20][30]   longitud = 4
cerrar hueco:   [10][20][30][30]   el 20 y el 30 se mueven a la izquierda
                                   longitud = 3: el último 30 queda en zona libre y ya no se ve
```

## Parte 2: Cuestiones para el análisis

**1. ¿Cómo podría representarse el número real de elementos de la lista dentro del array?**

Con un atributo `longitud`, separado de la capacidad del array (`elementos.length`). Las posiciones ocupadas son siempre de `0` a `longitud - 1`, seguidas y sin huecos. Además, `longitud` es justo la primera casilla libre, que es donde inserta `insertarAlFinal`.

**2. ¿Qué debería ocurrir cuando el array se llena por completo?**

Como un array no puede crecer, se crea uno nuevo con el **doble** de capacidad, se copian los elementos y la lista pasa a usar el nuevo. Esa inserción cuesta O(n) por la copia, pero al duplicar la capacidad pasa pocas veces, así que insertar al final sigue siendo rápido casi siempre.

**3. ¿Cómo se simularía la inserción de un elemento en una posición intermedia?**

Abriendo un hueco: se mueven una casilla a la derecha todos los elementos desde la posición hasta el final, **empezando por el último**. Si se empezara por la posición, cada elemento pisaría al siguiente antes de moverlo. Después se escribe el valor en el hueco y se suma 1 a `longitud`. Coste: O(n).

**4. ¿Qué operaciones serían necesarias para eliminar un elemento sin dejar "huecos" lógicos?**

Mover una casilla a la izquierda todos los elementos que hay detrás del eliminado (empezando por el más cercano) y restar 1 a `longitud`. La última casilla se queda con un valor repetido, pero ya está fuera de `longitud`, en la zona libre. Coste: O(n).

**5. ¿Qué mecanismos permitirían ocultar estos detalles al usuario, de modo que la estructura parezca verdaderamente dinámica?**

La encapsulación: `elementos` y `longitud` son `private`, y también lo son `abrirHuecoEn`, `cerrarHuecoEn` y `duplicarCapacidad`. Desde fuera solo se ve `insertar`, `eliminar`, `obtener`, `longitud` y `estaVacia`: el usuario nunca sabe cuál es la capacidad ni cuándo se ha copiado el array.

## Parte 3: Reflexión

**1. ¿Qué tipo de "flexibilidad simulada" puede lograrse con una estructura fija?**

Toda la que ve el usuario: la lista crece sin límite (hasta donde llegue la memoria) y permite insertar y eliminar en cualquier posición. Pero es simulada: por debajo sigue habiendo un array fijo que, cuando no da más de sí, se sustituye por otro.

**2. ¿Qué costos en tiempo o memoria implica mantener la ilusión de dinamismo?**

<div align=center>

|Tiempo|Memoria|
|-|-|
|Insertar o eliminar en medio: O(n), por los desplazamientos.|Casillas reservadas sin usar: tras duplicar, hasta la mitad del array puede estar libre.|
|Crecer: O(n), por la copia (aunque pasa pocas veces).|Mientras se copia, conviven el array viejo y el nuevo.|
|Acceder por posición: O(1), igual que un array (esto se gana frente a una lista enlazada).|No hace falta la referencia `siguiente` de cada nodo.|

</div>

**3. ¿Qué enseña este ejercicio sobre la diferencia entre abstracción conceptual y limitación técnica?**

Que una limitación técnica (el array no cambia de tamaño) no impide ofrecer una abstracción distinta (una lista que sí cambia). La abstracción es lo que se promete al usuario a través de los métodos; la limitación solo decide cuánto cuesta cumplir esa promesa.

**4. ¿Existen ejemplos en ingeniería donde se utilicen mecanismos similares para ofrecer una interfaz más flexible sobre una base rígida?**

Sí, y muy habituales:

- `ArrayList` de Java: es exactamente esto, un array que se sustituye por otro mayor (un 50 % más grande) cuando se llena. El `Ejemplo` lo usa para comparar.
- `StringBuilder`: un array de caracteres que crece cuando se le añade texto.
- Las listas de Python y el `vector` de C++ funcionan igual.
