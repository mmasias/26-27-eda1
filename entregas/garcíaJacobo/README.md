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
