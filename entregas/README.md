# EXAMEN COMPLETO RESUELTO

# 1. Análisis de implementaciones

Observa los siguientes fragmentos de código que implementan un nodo para una lista enlazada.

## Implementación A

```java
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = new Persona(persona.getNombre(), persona.getDni());
        this.siguiente = siguiente;
    }
}
```

## Implementación B

```java
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }
}
```

## A. ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

**Respuesta: Implementación B.**

La implementación B utiliza mejor los recursos de memoria porque almacena directamente la referencia al objeto Persona original, sin crear una copia.

En cambio, la implementación A crea un objeto Persona nuevo cada vez que se inserta un nodo, por lo que consume más memoria.

## B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

**Implementación A:**

Ventajas:
- Cada nodo tiene su propia copia de Persona.
- Modificar la Persona original no afecta a la almacenada en la lista.
- Los objetos son independientes.

Inconvenientes:
- Consume más memoria.
- Necesita crear objetos adicionales.

Ejemplo:

```java
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);

juan.setNombre("Carlos");
```

En la implementación A, la persona almacenada en la lista seguirá llamándose Juan.

**Implementación B:**

Ventajas:
- Consume menos memoria.
- No necesita crear copias.
- La inserción es más sencilla.

Inconvenientes:
- Si modificamos el objeto original, también cambia el objeto almacenado en la lista.
- Diferentes partes del programa pueden compartir y modificar el mismo objeto.

En el ejemplo anterior, en B la persona de la lista pasará a llamarse Carlos.

## C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

| Característica | Implementación A | Implementación B |
|---|---|---|
| Memoria | Mayor consumo | Menor consumo |
| Inserción | Crea una copia | Guarda referencia |
| Modificar original | No afecta a la lista | Afecta a la lista |
| Objetos compartidos | No | Sí |
| Independencia | Mayor | Menor |

**Conclusión:** La implementación A ofrece mayor independencia entre objetos, mientras que la B es más eficiente en memoria, pero permite que los cambios se propaguen entre referencias compartidas.

---

# 2. Gestión de referencias

Consideramos el siguiente código:

```java
class Lista {
    private Nodo primero;

    public void insertarPersona(Persona persona) {
        Nodo nuevo = new Nodo(persona, primero);
        primero = nuevo;
    }

    public boolean contiene(Persona persona) {
        Nodo actual = primero;

        while (actual != null) {
            if (actual.getPersona().getDni()
                .equals(persona.getDni())) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public Persona obtenerPersona(String dni) {
        Nodo actual = primero;

        while (actual != null) {
            if (actual.getPersona().getDni().equals(dni)) {
                return actual.getPersona();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }
}
```

## A. Si tenemos:

```java
Lista lista = new Lista();

Persona juan = new Persona("Juan", "1234");

lista.insertarPersona(juan);

Persona personaEncontrada =
    lista.obtenerPersona("1234");

personaEncontrada.setNombre("Juan Manuel");
```

**¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?**

**Respuesta: Juan Manuel.**

Explicación:

1. Creamos el objeto Persona Juan.
2. Insertamos Juan en la lista.
3. Buscamos la persona cuyo DNI es 1234.
4. `obtenerPersona()` devuelve la referencia al objeto almacenado en el nodo.
5. Modificamos su nombre mediante `setNombre()`.

Por tanto, estamos modificando el mismo objeto que está almacenado en la lista.

La clave está en esta línea:

```java
return actual.getPersona();
```

No devuelve una copia, sino la referencia al objeto almacenado.

Esto sucede tanto en la implementación A como en la B. Aunque A haya creado una copia al insertar, `obtenerPersona()` devuelve la referencia a esa copia almacenada.

## B. ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos")` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B)?

**Respuesta: Sí, depende de la implementación.**

- **Implementación A:** el nodo guarda una copia de Juan. Por tanto, modificar el objeto original no afecta a la lista. Seguirá llamándose Juan.
- **Implementación B:** el nodo guarda la referencia original. Por tanto, al modificar Juan, también cambiará el nombre almacenado en la lista a Juan Carlos.

Esto sucede porque en A existen dos objetos Persona diferentes, mientras que en B ambos utilizan el mismo objeto.

---

# 3. Referencias y constructores

Analizamos el siguiente código:

```java
public class Persona {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Persona(Persona persona) {
        this.nombre = persona.nombre;
        this.dni = persona.dni;
    }
}
```

Y en la clase Principal:

```java
public class Principal {
    public static void main(String[] args) {

        Persona manuel = new Persona("Manuel", "1234");

        Persona copia = new Persona(manuel);

        Persona referencia = manuel;

        Lista lista = new Lista();

        lista.insertarPersona(manuel);
        lista.insertarPersona(copia);
        lista.insertarPersona(referencia);
    }
}
```

## A. ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

**Respuesta: 2 objetos Persona.**

Primero analizamos las tres variables:

```java
Persona manuel = new Persona("Manuel", "1234");
```

Se crea el primer objeto.

```java
Persona copia = new Persona(manuel);
```

Se crea un segundo objeto independiente, copiando los atributos del primero.

```java
Persona referencia = manuel;
```

Aquí NO se crea ningún objeto nuevo. Simplemente se copia la referencia de Manuel.

Por tanto, tenemos dos objetos Persona:

- Objeto 1: compartido por `manuel` y `referencia`.
- Objeto 2: al que apunta `copia`.

Las llamadas a `insertarPersona()` no crean objetos Persona por sí mismas. El número de objetos dependerá de cómo esté implementado el constructor de Nodo:

- Con la implementación A, se crearían tres copias adicionales al insertar: 5 objetos en total.
- Con la implementación B, se conservarían los dos objetos originales: 2 objetos en total.

## B. Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

**Implementación A:**

Ningún elemento de la lista cambiará, porque cada nodo contiene una copia independiente de Persona.

**Implementación B:**

Se verán afectados el primer y el tercer nodo, porque ambos apuntan al mismo objeto que `manuel`.

El segundo nodo no cambiará porque apunta al objeto independiente creado mediante el constructor de copia.

Esto se debe a que:

```java
Persona referencia = manuel;
```

No crea un objeto nuevo, sino que comparte la referencia.

---

# 4. Diseño y optimización

Se pide proponer una implementación de una lista que:

- Permita detectar si se está intentando insertar una referencia a una Persona que ya existe en la lista.
- Permita elegir si queremos insertar una copia de la Persona o mantener la referencia.
- Sea eficiente en el uso de memoria.

## Solución en Java

### Clase Persona

```java
class Persona {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Constructor de copia
    public Persona(Persona persona) {
        this.nombre = persona.nombre;
        this.dni = persona.dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
```

### Clase Lista

```java
class Lista {

    private class Nodo {
        Persona persona;
        Nodo siguiente;

        public Nodo(Persona persona, Nodo siguiente) {
            this.persona = persona;
            this.siguiente = siguiente;
        }
    }

    private Nodo primero;

    public boolean insertarPersona(Persona persona,
                                   boolean copiar) {

        // Comprobar si ya existe una persona con ese DNI
        Nodo actual = primero;

        while (actual != null) {

            if (actual.persona.getDni()
                .equals(persona.getDni())) {
                return false;
            }

            actual = actual.siguiente;
        }

        // Elegir entre copia o referencia
        Persona nuevaPersona;

        if (copiar) {
            nuevaPersona = new Persona(persona);
        } else {
            nuevaPersona = persona;
        }

        // Insertar al principio
        Nodo nuevo = new Nodo(nuevaPersona, primero);
        primero = nuevo;

        return true;
    }
}
```

## Explicación del funcionamiento

### 1. Comprobamos si la persona ya existe

```java
if (actual.persona.getDni()
    .equals(persona.getDni())) {
    return false;
}
```

Comparamos los DNI porque dos personas con el mismo DNI se consideran la misma persona, aunque tengan nombres diferentes.

### 2. Elegimos cómo insertar

```java
if (copiar) {
    nuevaPersona = new Persona(persona);
} else {
    nuevaPersona = persona;
}
```

- Si `copiar` es `true`, creamos un objeto nuevo.
- Si `copiar` es `false`, guardamos la referencia original.

### 3. Insertamos el nodo al principio

```java
Nodo nuevo = new Nodo(nuevaPersona, primero);
primero = nuevo;
```

### Ejemplo de utilización

```java
Lista lista = new Lista();

Persona juan = new Persona("Juan", "1234");
Persona ana = new Persona("Ana", "5678");

// Insertamos una copia de Juan
lista.insertarPersona(juan, true);

// Insertamos la referencia original de Ana
lista.insertarPersona(ana, false);

// Intentamos insertar otro Juan con el mismo DNI
Persona otroJuan = new Persona("Juan Carlos", "1234");

lista.insertarPersona(otroJuan, false);
```

La última inserción será rechazada porque ya existe una persona con el DNI 1234.

## Justificación del diseño

| Requisito | Cómo lo conseguimos |
|---|---|
| Detectar duplicados | Comparamos los DNI |
| Elegir copia o referencia | Parámetro boolean `copiar` |
| Ahorrar memoria | Solo copiamos cuando es necesario |
| Inserción | Añadimos el nodo al principio |

La búsqueda de duplicados tiene un coste de `O(n)`, porque debemos recorrer la lista.

La inserción, una vez comprobados los duplicados, tiene un coste de `O(1)`.

---

# 5. Resumen de conceptos importantes para el examen

| Código | ¿Qué sucede? |
|---|---|
| `Persona p2 = p1;` | Comparte referencia |
| `Persona p2 = new Persona(p1);` | Crea una copia |
| `p1.setNombre("Carlos");` | Modifica el objeto original |
| `return actual.getPersona();` | Devuelve la referencia almacenada |
| `new Nodo(persona, primero);` | Depende del constructor de Nodo |
| `persona1 == persona2` | Comprueba si son el mismo objeto |
| `persona1.equals(persona2)` | Comprueba igualdad según `equals()` |
| `persona1.getDni().equals(persona2.getDni())` | Compara los DNI |

## Reglas fundamentales

- `=` entre objetos copia referencias, no objetos.
- `new` crea un objeto nuevo.
- Un constructor de copia crea otro objeto independiente.
- Guardar una referencia en un nodo permite compartir el objeto.
- Modificar un objeto afecta a todas las variables que apuntan a él.
- `==` compara referencias.
- `.equals()` compara el contenido según cómo esté implementado el método.

# EJERCICIO: MANEJO DE REFERENCIAS EN JAVA

## a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

El problema principal es que el código almacena y devuelve referencias directas a los objetos `Persona`, en lugar de trabajar con copias.

En el método `agregarPersona()`, se introduce en la lista la referencia del objeto original. Esto significa que, si se modifica el objeto desde otra parte del programa, también cambiarán los datos almacenados en la lista.

En el método `buscarPersona()`, se devuelve directamente la referencia al objeto guardado. Por tanto, quien recibe esa referencia puede modificar sus atributos sin que el gestor tenga control sobre ello.

Esto puede provocar:
- Referencias compartidas entre diferentes partes del programa.
- Modificaciones no deseadas de los objetos.
- Pérdida de integridad de los datos.

## b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

Utilizaría copias defensivas para evitar compartir referencias entre los objetos originales y los almacenados en la lista.

Para ello, realizaría dos modificaciones:

1. Al agregar una persona, guardaría una copia del objeto original en lugar de su referencia.
2. Al buscar una persona, devolvería una copia del objeto almacenado en lugar de devolver directamente su referencia.

Además, declararía los atributos de la clase `Persona` como privados para controlar el acceso y evitar modificaciones externas.

## c) Proponga una solución que proteja la integridad de los datos.

La solución sería modificar los métodos `agregarPersona()` y `buscarPersona()` para utilizar copias defensivas.

**Código corregido:**

    public class GestorPersonas {
        private Lista lista;

        public void agregarPersona(Persona persona) {
            if (!lista.contiene(persona)) {
                lista.insertarPersona(new Persona(persona));
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

También añadiría un constructor de copia en la clase `Persona`:

    public Persona(Persona otra) {
        this.dni = otra.dni;
        this.nombre = otra.nombre;
    }

Este constructor crea un objeto nuevo con los mismos datos que el original, pero con una referencia independiente.

## CONCLUSIÓN

El problema del código original es que permite compartir referencias a los mismos objetos, lo que puede provocar modificaciones no controladas.

Para solucionarlo, utilizaría copias defensivas y atributos privados. De esta manera, se protege la información almacenada, se evitan modificaciones externas y se garantiza la integridad de los datos.