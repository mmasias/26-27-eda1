# Enmienda 
**Nombre** Hugo Astoreca Liaño

## Enmienda 1. Constructor `Pila()`


**Fecha:** 11/11/2026

**Qué falla en mi propuesta inicial:**

Inicialmente había planteado que el constructor recibiría una cola que podía tener elementos o estar vacía. Sin embargo, mi implementación utiliza un constructor sin parámetros.

También había pensado utilizar `null` para indicar que no había un último elemento, pero `ultimo` es de tipo `int` y no puede almacenar `null`.

**Cómo lo compruebo:**

Revisando el constructor puedo comprobar que no recibe ningún parámetro y que crea directamente una cola nueva.

Además, si intento asignar `null` a una variable de tipo `int`, Java produce un error de compilación.

**Qué he cambiado:**

He creado la cola directamente con `new Cola()` y he inicializado `ultimo` y `posicionPersonaEnCola` a `0`.

---

## Enmienda 2. Método `apilar(int valor)`

**Fecha:** 11/11/2026

**Qué falla en mi propuesta inicial:**

Había planteado comprobar si la cola estaba vacía antes de introducir el valor, pero esa comprobación no es necesaria para realizar la operación.

**Cómo lo compruebo:**

Puedo probar a ejecutar `apilar(1)` con una cola vacía y comprobar que el elemento se introduce correctamente.

Después puedo ejecutar `apilar(2)` y `apilar(3)` para comprobar que se conservan los elementos y que `ultimo` se actualiza con el último valor introducido.

**Qué he cambiado:**

He eliminado la comprobación inicial y he utilizado directamente `encolar(valor)`.

He mantenido mi idea de recorrer la cola y actualizar `ultimo`, utilizando un contador y los métodos públicos de `Cola`.

---

## Enmienda 3. Método `desapilar()`

**Fecha:** 12/11/2026

**Qué falla en mi propuesta inicial:**

Mi planteamiento decía que tenía que eliminar el último elemento, pero no había concretado cómo hacerlo teniendo en cuenta que `desencolar()` elimina el primero.

**Cómo lo compruebo:**

Puedo introducir los valores `1`, `2` y `3` y ejecutar `desapilar()`.

El método debe devolver `3`, conservar los valores `1` y `2` en la cola y actualizar `ultimo` a `2`.

También puedo volver a desapilar para comprobar que devuelve `2` y después `1`.

**Qué he cambiado:**

He utilizado un `while` para mover los elementos de la cola hasta colocar el último en la primera posición y poder eliminarlo con `desencolar()`.

Después actualizo `posicionPersonaEnCola` y recorro los elementos restantes para identificar el nuevo `ultimo`.

He mantenido mi idea inicial de comprobar que la pila no esté vacía, eliminar el último valor y devolverlo.

---

## Enmienda 4. Método `estaVacia()`

**Fecha:** 12/11/2026

**Qué falla en mi propuesta inicial:**

Había pensado recorrer la cola y comprobar si `ultimo` era `null`, pero `ultimo` es un `int` y no puede tener ese valor.

Además, no necesito recorrer la cola porque ya dispone de un método que comprueba si está vacía.

**Cómo lo compruebo:**

Puedo crear una pila vacía y ejecutar `estaVacia()`, que debe devolver `true`.

Después puedo introducir un elemento y volver a comprobarlo, obteniendo `false`.

Finalmente, puedo eliminar ese elemento y comprobar que vuelve a devolver `true`.

**Qué he cambiado:**

He utilizado directamente `cola.estaVacia()` para comprobar si la pila contiene elementos.

He mantenido la idea de devolver `true` cuando está vacía y `false` cuando tiene elementos.

