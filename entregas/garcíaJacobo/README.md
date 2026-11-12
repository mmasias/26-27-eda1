# Registro de enmiendas

## Enmienda 1. Constructor `Pila()`

**Fecha:** 11/11/2026

**Contrato original:**

El constructor recibe una cola, que puede contener elementos o estar vacía.

**Problema detectado:**

Mi planteamiento inicial no cumplía con el enunciado, ya que había indicado que el constructor recibiría una cola como parámetro.

**Cómo comprobé el problema:**

Al revisar el enunciado y compararlo con mi contrato inicial, comprobé que se exigía un constructor `Pila()` sin parámetros.

**Cambio realizado:**

En la implementación, el constructor no recibe ninguna cola como parámetro. En su lugar, crea una cola nueva mediante `new Cola()` e inicializa los atributos:

- `numeroPersonasEnCola = 0`
- `ultimo = 0`
- `tamaño = 0`

**Justificación:**

El enunciado exige que el constructor sea `Pila()` sin parámetros. Por tanto, he tenido que adaptar mi planteamiento inicial para cumplir con esa condición.

---

## Enmienda 2. Variable `ultimo`

**Fecha:** 11/11/2026

**Contrato original:**

La variable `ultimo` podía contener un valor o `null` cuando la cola estaba vacía.

**Problema detectado:**

En mi contrato había planteado utilizar `null` cuando la pila estuviese vacía, pero el atributo `ultimo` es de tipo `int` y no puede almacenar ese valor.

**Cómo comprobé el problema:**

Al implementar la clase `Pila`, comprobé que no podía asignar `null` a la variable `ultimo`, porque Java no permite almacenar ese valor en una variable de tipo `int`.

**Cambio realizado:**

He mantenido `ultimo` como un entero (`int`), pero utilizo el valor `0` cuando la pila está vacía.

**Justificación:**

En Java, una variable de tipo `int` no puede almacenar `null`. Por eso he utilizado `0` como valor inicial y cuando la pila se queda sin elementos.

Hay que tener en cuenta que `ultimo = 0` no significa necesariamente que la pila esté vacía, ya que también puedo apilar el número `0`. Por eso, para comprobar si la pila está vacía, utilizo el método `estaVacia()`.

---

## Enmienda 3. Método `apilar(int valor)`

**Fecha:** 12/11/2026

**Contrato original:**

Mi planteamiento era comprobar primero si la cola estaba vacía mediante un `if`. Después, recorrer los elementos de la cola utilizando `desencolar()`, actualizar `ultimo` y modificar el tamaño según fueran entrando los valores.

**Problema detectado:**

Al implementar el método, me di cuenta de que no necesitaba comprobar por separado si la cola estaba vacía.

Además, si aumentaba el tamaño por cada elemento que trasladaba, estaría contando otra vez elementos que ya formaban parte de la pila.

**Cómo comprobé el problema:**

Al desarrollar el método `apilar(int valor)`, comprobé que, cuando la cola está vacía, el `while` no se ejecuta porque `numeroPersonasEnCola` vale `0`.

También comprobé que los elementos que traslado de una cola a otra ya estaban contabilizados en el tamaño de la pila, por lo que no tenía que volver a sumarlos.

**Cambio realizado:**

En mi implementación no he utilizado el `if (cola.estaVacia())`, porque si la cola está vacía, el `while` directamente no se ejecuta.

Además, actualizo `ultimo` con el valor introducido y aumento `tamaño` una sola vez al finalizar el método.

**Justificación:**

He realizado estos cambios porque no necesito comprobar por separado si la cola está vacía. Además, solamente estoy añadiendo un elemento nuevo, por lo que el tamaño debe aumentar una única vez.
