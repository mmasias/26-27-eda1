## Pregunta 1: análisis de implementaciones

### a) ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

La **implementación B** utiliza mejor los recursos de memoria.

En la implementación A se crea un nuevo objeto `Persona` cada vez que se crea un nodo:

```java
this.persona = new Persona(persona.getNombre(), persona.getDni());
```

Esto significa que, aunque el objeto `Persona` ya exista, se crea otro objeto con los mismos datos para almacenarlo en el nodo.

En cambio, en la implementación B:

```java
this.persona = persona;
```

no se crea un nuevo objeto `Persona`, sino que se guarda una referencia al objeto que ya existe.

Por lo tanto, la implementación B utiliza menos memoria porque evita crear objetos `Persona` adicionales.

### b) ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

#### Implementación A

La principal ventaja es que la persona almacenada en el nodo es independiente de la persona original.

Por ejemplo:

```java
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);

juan.setNombre("Juan Carlos");
```

Si utilizamos la implementación A, la persona almacenada en la lista seguirá teniendo el nombre `"Juan"`, porque al insertarla se creó una nueva `Persona`.

La desventaja es que utiliza más memoria, ya que se crea una copia de cada persona que se introduce en la lista.

#### Implementación B

La principal ventaja es que utiliza menos memoria, porque no crea una copia de la persona.

Sin embargo, varias referencias pueden apuntar al mismo objeto.

Por ejemplo:

```java
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);

juan.setNombre("Juan Carlos");
```

Con la implementación B, la persona almacenada en la lista también tendrá como nombre `"Juan Carlos"`, porque `juan` y el nodo hacen referencia al mismo objeto `Persona`.

### c) ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

Con la implementación A, la lista almacena copias independientes de las personas. Por lo tanto, modificar el objeto original después de insertarlo no modifica la copia almacenada en la lista.

Con la implementación B, la lista almacena referencias a los objetos originales. Por lo tanto, si una persona se modifica desde fuera de la lista, el cambio también se verá reflejado dentro de la lista.

En resumen, la implementación A ofrece más independencia entre los objetos, mientras que la implementación B utiliza menos memoria pero permite que un mismo objeto pueda modificarse desde diferentes referencias.

---

## Pregunta 2: gestión de referencias

### a)

Tenemos el siguiente código:

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);

Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```

La persona almacenada en la lista tendrá como nombre:

```text
Juan Manuel
```

Esto ocurre porque el método `obtenerPersona()` devuelve la referencia a la `Persona` que está almacenada en el nodo:

```java
return actual.getPersona();
```

Por lo tanto, `personaEncontrada` apunta directamente al objeto `Persona` almacenado en la lista.

Cuando hacemos:

```java
personaEncontrada.setNombre("Juan Manuel");
```

estamos modificando directamente ese objeto.

Esto ocurre tanto con la implementación A como con la B. La diferencia es cómo llegó la persona al nodo, pero una vez que `obtenerPersona()` devuelve la referencia interna del nodo, podemos modificarla desde fuera.

### b)

Si después de insertar a `juan` hacemos:

```java
juan.setNombre("Juan Carlos");
```

el resultado depende de la implementación de `Nodo`.

#### Con la implementación A

La lista seguirá almacenando:

```text
Juan
```

Esto ocurre porque el constructor del nodo creó una nueva `Persona`. Por tanto, `juan` y la persona almacenada en la lista son objetos diferentes.

#### Con la implementación B

La persona almacenada en la lista pasará a llamarse:

```text
Juan Carlos
```

Esto ocurre porque el nodo almacena una referencia al mismo objeto al que apunta `juan`.

Por tanto, al modificar `juan`, estamos modificando también el objeto que está viendo la lista.

---

## Pregunta 3: referencias y constructores

Tenemos:

```java
Persona manuel = new Persona("Manuel", "1234");
Persona copia = new Persona(manuel);
Persona referencia = manuel;

Lista lista = new Lista();
lista.insertarPersona(manuel);
lista.insertarPersona(copia);
lista.insertarPersona(referencia);
```

### a) ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

Antes de realizar las inserciones en la lista existen **2 objetos `Persona` diferentes**.

El primero se crea aquí:

```java
Persona manuel = new Persona("Manuel", "1234");
```

El segundo se crea aquí:

```java
Persona copia = new Persona(manuel);
```

Aunque `copia` tiene los mismos datos que `manuel`, es un objeto diferente porque se ha utilizado `new Persona(...)`.

En cambio:

```java
Persona referencia = manuel;
```

no crea ningún objeto nuevo. Simplemente hace que `referencia` apunte al mismo objeto que `manuel`.

Podemos representarlo de esta forma:

```text
manuel --------> Persona 1
                    ^
                    |
referencia ---------+

copia ---------> Persona 2
```

Después se insertan las tres referencias en la lista.

Aquí el número final de objetos `Persona` depende de la implementación del nodo.

#### Si utilizamos la implementación B

El nodo solamente guarda las referencias recibidas:

```java
this.persona = persona;
```

Por lo tanto, no se crean nuevas personas y siguen existiendo **2 objetos `Persona` diferentes**.

Dos nodos apuntarán al mismo objeto (`manuel` y `referencia`) y otro nodo apuntará al objeto `copia`.

#### Si utilizamos la implementación A

Cada vez que se crea un nodo también se crea una nueva `Persona`:

```java
this.persona = new Persona(persona.getNombre(), persona.getDni());
```

Como realizamos tres inserciones, se crean tres objetos `Persona` adicionales.

Por tanto, tendremos:

- 2 objetos creados inicialmente (`manuel` y `copia`).
- 3 objetos creados por los tres nodos.

En total habrá **5 objetos `Persona` diferentes en memoria**.

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

El resultado depende de la implementación de `Nodo`.

#### Con la implementación B

`manuel` y `referencia` apuntan al mismo objeto.

Por tanto, los nodos creados al insertar:

```java
lista.insertarPersona(manuel);
lista.insertarPersona(referencia);
```

también apuntan al mismo objeto.

Si hacemos:

```java
manuel.setNombre("Manuel Antonio");
```

los **dos elementos de la lista correspondientes a `manuel` y `referencia`** mostrarán `"Manuel Antonio"`.

El elemento correspondiente a `copia` seguirá mostrando `"Manuel"`, porque `copia` es otro objeto diferente.

#### Con la implementación A

Ningún elemento de la lista se verá afectado.

Esto ocurre porque cada nodo creó su propia copia de `Persona` al realizar la inserción.

Por tanto, modificar posteriormente el objeto `manuel` no modifica las copias almacenadas dentro de los nodos.

---

## Pregunta 4: diseño y optimización

Se puede diseñar una lista que permita comprobar si una referencia ya está almacenada y que, además, permita elegir entre guardar la referencia original o crear una copia.

Una posible implementación sería:

```java
public class Lista {

    private Nodo primero;

    public boolean contieneReferencia(Persona persona) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getPersona() == persona) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public void insertarPersona(Persona persona, boolean copiar) {

        if (!contieneReferencia(persona)) {

            Persona personaInsertar;

            if (copiar) {
                personaInsertar = new Persona(persona);
            } else {
                personaInsertar = persona;
            }

            Nodo nuevo = new Nodo(personaInsertar, primero);
            primero = nuevo;
        }
    }
}
```

Para detectar si estamos intentando insertar exactamente el mismo objeto utilizamos:

```java
actual.getPersona() == persona
```

Utilizamos `==` porque queremos comparar las referencias, es decir, comprobar si las dos variables apuntan exactamente al mismo objeto `Persona`.

El parámetro:

```java
boolean copiar
```

permite decidir cómo queremos realizar la inserción.

Por ejemplo:

```java
lista.insertarPersona(juan, false);
```

almacena la referencia original.

En cambio:

```java
lista.insertarPersona(juan, true);
```

crea una copia antes de almacenarla.

Para que este diseño funcione correctamente, el `Nodo` debe limitarse a guardar la `Persona` que recibe:

```java
public Nodo(Persona persona, Nodo siguiente) {
    this.persona = persona;
    this.siguiente = siguiente;
}
```

De esta manera evitamos que el nodo vuelva a crear otra copia cuando ya hemos decidido en `insertarPersona()` si queremos copiarla o no.

Si mantenemos la referencia original, utilizamos menos memoria, pero cualquier modificación externa del objeto también afectará a la lista.

Si almacenamos una copia, utilizamos algo más de memoria, pero conseguimos que el objeto almacenado sea independiente del original.

De esta forma podemos elegir entre eficiencia de memoria o mayor protección de los datos dependiendo de lo que necesitemos.

---

## Pregunta 5: análisis de código

Tenemos:

```java
public class GestorPersonas {

    private Lista lista;

    public void agregarPersona(Persona persona) {
        if (!lista.contiene(persona)) {
            lista.insertarPersona(persona);
        }
    }

    public Persona buscarPersona(String dni) {
        return lista.obtenerPersona(dni);
    }
}
```

### a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

Uno de los principales problemas está en el método:

```java
public Persona buscarPersona(String dni) {
    return lista.obtenerPersona(dni);
}
```

El método `obtenerPersona()` devuelve directamente la referencia a la persona almacenada dentro de la lista.

Esto significa que desde fuera del `GestorPersonas` podemos modificar directamente los datos internos.

Por ejemplo:

```java
Persona persona = gestor.buscarPersona("1234");
persona.setNombre("Pedro");
```

Si `persona` apunta al mismo objeto almacenado en la lista, el nombre de la persona de la lista también cambiará a `"Pedro"`.

También puede existir un problema al insertar una persona si la lista guarda directamente la referencia recibida.

Por ejemplo:

```java
Persona juan = new Persona("Juan", "1234");
gestor.agregarPersona(juan);

juan.setNombre("Juan Carlos");
```

Si se mantiene la referencia original, el cambio también se verá reflejado dentro de la lista.

### b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

Una solución sería devolver una copia de la persona en lugar de devolver directamente la referencia almacenada.

Por ejemplo:

```java
public Persona buscarPersona(String dni) {

    Persona persona = lista.obtenerPersona(dni);

    if (persona != null) {
        return new Persona(persona);
    }

    return null;
}
```

De esta forma, si hacemos:

```java
Persona persona = gestor.buscarPersona("1234");
persona.setNombre("Pedro");
```

solo modificaremos la copia obtenida y no la persona que se encuentra almacenada dentro de la lista.

### c) Proponga una solución que proteja la integridad de los datos.

Una solución más completa sería realizar una copia tanto al insertar una persona como al devolverla.

Por ejemplo:

```java
public class GestorPersonas {

    private Lista lista;

    public GestorPersonas() {
        lista = new Lista();
    }

    public void agregarPersona(Persona persona) {

        if (!lista.contiene(persona)) {
            Persona copia = new Persona(persona);
            lista.insertarPersona(copia);
        }
    }

    public Persona buscarPersona(String dni) {

        Persona persona = lista.obtenerPersona(dni);

        if (persona != null) {
            return new Persona(persona);
        }

        return null;
    }
}
```

Al insertar hacemos:

```java
Persona copia = new Persona(persona);
```

Así, la lista no depende del objeto original recibido desde fuera.

Después, al buscar hacemos:

```java
return new Persona(persona);
```

De esta forma tampoco entregamos la referencia interna almacenada en la lista.

Por ejemplo:

```java
Persona juan = new Persona("Juan", "1234");
gestor.agregarPersona(juan);

juan.setNombre("Juan Carlos");
```

La persona almacenada en el gestor seguirá llamándose `"Juan"`.

Y si posteriormente hacemos:

```java
Persona encontrada = gestor.buscarPersona("1234");
encontrada.setNombre("Pedro");
```

la persona almacenada seguirá llamándose `"Juan"` porque `encontrada` es otra copia.

Por tanto, esta solución protege mejor la integridad de los datos, ya que los objetos almacenados dentro de la lista no pueden ser modificados directamente desde fuera.

La desventaja es que se utiliza algo más de memoria debido a la creación de copias, pero a cambio se consigue un mayor control y protección de los datos.
