# 2. Gestión de referencias

## A. ¿Qué nombre tendrá la persona almacenada en la lista?

La persona almacenada en la lista tendrá el nombre "Juan Manuel", porque el método `obtenerPersona` devuelve la referencia al objeto encontrado y, al ejecutar `personaEncontrada.setNombre("Juan Manuel")`, se modifica ese objeto.

Con la implementación A, el objeto original `juan` seguirá llamándose "Juan", porque el nodo almacena una copia. Con la implementación B, `juan` también pasará a llamarse "Juan Manuel", porque tanto la variable como el nodo apuntan al mismo objeto.

## B. ¿Qué sucede si ejecutamos `juan.setNombre("Juan Carlos")` después de insertarlo?

Con la implementación A, la persona almacenada en la lista seguirá llamándose "Juan", porque el nodo contiene una copia independiente del objeto original.

Con la implementación B, la persona almacenada en la lista pasará a llamarse "Juan Carlos", porque el nodo y la variable `juan` comparten la misma referencia al objeto.