# 3. Referencias y constructores

## A. ¿Cuántos objetos Persona diferentes hay en memoria?

Al ejecutar las instrucciones:

```java
Persona manuel = new Persona("Manuel", "1234");
Persona copia = new Persona(manuel);
Persona referencia = manuel;
```

Se crean inicialmente dos objetos Persona diferentes.

- `manuel` apunta al primer objeto.
- `copia` apunta a un segundo objeto creado mediante el constructor de copia.
- `referencia` apunta al mismo objeto que `manuel`, por lo que no crea un objeto nuevo.

Después de insertar las tres variables en la lista, el número de objetos depende de la implementación del nodo.

**Con Nodo B:** hay dos objetos Persona, porque los nodos guardan las referencias recibidas.

**Con Nodo A:** hay cinco objetos Persona, porque cada una de las tres inserciones crea una copia adicional.

## B. Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se ven afectados?

**Con Nodo B**, cambia el elemento insertado mediante `manuel` y también el insertado mediante `referencia`, porque ambos apuntan al mismo objeto original. El elemento insertado mediante `copia` no cambia, ya que es un objeto diferente.

**Con Nodo A**, ninguno de los tres elementos de la lista cambia, porque cada nodo contiene una copia independiente. Solo cambia el objeto original al que apuntan `manuel` y `referencia`.