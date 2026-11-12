## Registro de enmiendas

### Enmienda 1. Constructor `Pila()`

**Contrato original:**

El constructor recibe una cola, que puede contener elementos o estar vacía.

**Cambio realizado:**

En la implementación, el constructor no recibe ninguna cola como parámetro. En su lugar, crea una cola nueva mediante `new Cola()` e inicializa los atributos:

- `numeroPersonasEnCola = 0`
- `ultimo = 0`
- `tamaño = 0`

**Justificación:**

El enunciado exige que el constructor sea `Pila()` sin parámetros. Por tanto, he tenido que adaptar mi planteamiento inicial para cumplir con esa condición.

### Enmienda 2. Variable `ultimo`

**Contrato original:**

La variable `ultimo` podía contener un valor o `null` cuando la cola estaba vacía.

**Cambio realizado:**

He mantenido `ultimo` como un entero (`int`), pero utilizo el valor `0` para representar que la pila está vacía.

**Justificación:**

En Java, una variable de tipo `int` no puede almacenar `null`. Por eso he utilizado `0` como valor especial cuando no hay elementos.
### Enmienda 3. Método `apilar(int valor)`

**Contrato original:**

Mi planteamiento era comprobar primero si la cola estaba vacía mediante un `if`. Después, recorrer los elementos de la cola utilizando `desencolar()`, actualizar `ultimo` y modificar el tamaño según fueran entrando los valores.

**Cambio realizado:**

En mi implementación no he utilizado el `if (cola.estaVacia())`, porque si la cola está vacía, el `while` directamente no se ejecuta.

Además, actualizo `ultimo` con el valor introducido y aumento `tamaño` una sola vez al finalizar el método.

**Justificación:**

He realizado estos cambios porque no necesito comprobar por separado si la cola está vacía. Además, solamente estoy añadiendo un elemento nuevo, por lo que el tamaño debe aumentar una única vez.
