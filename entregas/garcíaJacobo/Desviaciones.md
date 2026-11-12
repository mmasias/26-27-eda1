# Registro de desviaciones respecto al diseño inicial

## 1. Constructor `Pila()`

**Mi planteamiento inicial:**

En mi contrato había planteado que el constructor recibiese una cola, que podía contener elementos o estar vacía.

**Implementación final:**

El constructor no recibe una cola como parámetro. En su lugar, crea una nueva cola mediante `new Cola()` e inicializa los atributos a cero.

**Motivo de la desviación:**

El enunciado exigía un constructor sin parámetros (`Pila()`), por lo que tuve que adaptar mi planteamiento inicial.

## 2. Variable `ultimo`

**Mi planteamiento inicial:**

Había indicado que la variable `ultimo` podía contener un valor o `null` cuando la pila estuviese vacía.

**Implementación final:**

He mantenido `ultimo` como un atributo de tipo `int`, utilizando el valor `0` cuando la pila está vacía.

**Motivo de la desviación:**

Una variable de tipo `int` no puede almacenar `null`. Por eso he utilizado `0` como valor inicial y cuando la pila se queda sin elementos. Para comprobar si está vacía utilizo el método `estaVacia()`.

## 3. Método `apilar(int valor)`

**Mi planteamiento inicial:**

Había planteado comprobar si la cola estaba vacía mediante un `if` y, si contenía elementos, recorrerla utilizando `desencolar()` para ir pasando sus valores a mi pila.

También había indicado que actualizaría el tamaño según fueran entrando los valores.

**Implementación final:**

He sustituido la comprobación inicial mediante `if` por un funcionamiento en el que el `while` no se ejecuta cuando la cola está vacía.

Además, actualizo el tamaño una sola vez al finalizar el método.

**Motivo de la desviación:**

He simplificado la comprobación de la cola vacía y he evitado incrementar el tamaño por cada elemento que traslado, porque esos elementos ya formaban parte de la pila. Únicamente tengo que contabilizar el nuevo elemento que estoy apilando.