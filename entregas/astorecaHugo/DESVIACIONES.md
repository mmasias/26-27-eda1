# Registro de Desviaciones respecto al Diseño Inicial

## 1. Concepción general de mi pila

**Mi planteamiento inicial:**

Mi idea era implementar una pila utilizando una cola, de manera que pudiera introducir elementos y eliminar siempre el último que hubiera añadido.

Para conseguirlo, había pensado utilizar las variables `ultimo` y `posicionPersonaEnCola`, que me ayudarían a identificar el último elemento y controlar cuántos había en la cola.

**Mi implementación final:**

He mantenido esta idea utilizando una única cola y las dos variables que había planteado.

Como la cola funciona de manera FIFO y la pila debe funcionar de manera LIFO, he utilizado los métodos `encolar()` y `desencolar()` para recorrer y reorganizar los elementos cuando es necesario.

---

## 2. Constructor `Pila()`

**Mi planteamiento inicial:**

Había pensado que el constructor recibiría una cola que podría estar vacía o contener elementos.

También utilizaría las variables `ultimo` y `posicionPersonaEnCola`.

**Mi implementación final (desviación):**

Finalmente, he creado una cola vacía directamente dentro del constructor mediante `new Cola()`.

He inicializado `ultimo` y `posicionPersonaEnCola` a `0`.

**Justificación:**

De esta manera, mi pila siempre comienza vacía y puedo controlar los elementos que se van introduciendo desde el principio.

---

## 3. Método `apilar(int valor)`

**Mi planteamiento inicial:**

Mi idea era comprobar si la cola estaba vacía, introducir el nuevo elemento y recorrerla para identificar cuál era el último.

**Mi implementación final (desviación):**

He mantenido la idea principal, pero he eliminado la comprobación inicial de si la cola está vacía, porque puedo introducir directamente el elemento.

Después utilizo un contador y un `while` para recorrer los elementos mediante `desencolar()` y `encolar()`.

Durante el recorrido actualizo `ultimo` para guardar el valor del último elemento.

**Justificación:**

No necesito comprobar si la cola está vacía para poder introducir un elemento.

Además, recorriendo la cola puedo actualizar `ultimo` sin acceder directamente a sus nodos ni modificar su implementación.

---

## 4. Método `desapilar()`

**Mi planteamiento inicial:**

Mi idea era comprobar que la pila no estuviera vacía, eliminar el último elemento, actualizar el nuevo último y devolver el valor eliminado.

**Mi implementación final:**

He mantenido mi planteamiento inicial, pero he tenido que concretar cómo eliminar el último elemento.

Para ello, utilizo un `while` que mueve los elementos del principio al final hasta que el último queda en la primera posición.

Después lo elimino con `desencolar()`, reduzco `posicionPersonaEnCola` y recorro los elementos restantes para actualizar `ultimo`.

**Justificación:**

Como `desencolar()` solamente permite eliminar el primer elemento, necesito reorganizar la cola para poder eliminar el último.

Así consigo que la pila respete el comportamiento LIFO utilizando únicamente los métodos públicos de `Cola`.

---

## 5. Método `estaVacia()`

**Mi planteamiento inicial:**

Había pensado recorrer la cola y comprobar si `ultimo` era `null` para saber si la pila estaba vacía.

**Mi implementación final (desviación):**

Finalmente, he utilizado directamente el método `cola.estaVacia()`.

**Justificación:**

La clase `Cola` ya tiene un método que permite comprobar si está vacía, por lo que no necesito recorrer todos sus elementos.

Además, `ultimo` es de tipo `int` y no puede valer `null`.

---

## 6. Conclusión

En este segundo contrato he mantenido prácticamente toda mi idea inicial.

La principal diferencia respecto al primer contrato es que aquí utilizo una única cola y voy moviendo sus elementos para poder eliminar el último cuando ejecuto `desapilar()`.

Las desviaciones más importantes han sido crear la cola directamente en el constructor, eliminar la comprobación inicial de `apilar()` y utilizar `cola.estaVacia()` para comprobar si la pila está vacía.

En el método `desapilar()` he mantenido el comportamiento que había planteado inicialmente, pero he tenido que desarrollar una forma de conseguirlo utilizando solamente las operaciones de la cola.
