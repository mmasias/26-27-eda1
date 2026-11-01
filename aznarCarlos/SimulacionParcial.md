## 1. Análisis de implementaciones

### Código de referencia

```java
// Implementación A
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = new Persona(persona.getNombre(), persona.getDni());
        this.siguiente = siguiente;
    }
}

// Implementación B
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }
}

```

---

### A. ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

La **Implementación B** utiliza mejor los recursos de memoria.

**Justificación:**

* **Implementación A:** Realiza una *copia defensiva* (`new Persona(...)`) cada vez que se crea un nuevo nodo. Esto implica instanciar un objeto totalmente nuevo en la memoria heap. Si se insertan muchos elementos repetidos o la lista maneja grandes cantidades de datos, duplica innecesariamente el consumo de memoria.


* **Implementación B:** Guarda directamente la *referencia* al objeto `Persona` existente (`this.persona = persona`). Al no instanciar un nuevo objeto, reduce notablemente la sobrecarga del recolector de basura (*Garbage Collector*) y ahorra memoria.



---

### B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

#### Implementación A (Copia en profundidad)

* **Ventajas:**
* **Encapsulación e Independencia:** Al trabajar con copias, los cambios externos realizados al objeto `Persona` original no afectan al elemento almacenado en la lista.




* **Desventajas:**
* **Mayor consumo de memoria y CPU:** Crear una nueva instancia por cada nodo incrementa el uso del montón (*heap*) y ralentiza el proceso.


* **Pérdida de identidad:** Si se modifica el objeto original fuera de la lista, la lista no reflejará dichos cambios, lo que puede causar inconsistencias si se esperaba sincronización.



#### Implementación B (Por referencia)

* **Ventajas:**
* **Eficiencia:** Rapidez en tiempo de ejecución y uso óptimo de memoria al compartir la misma dirección de memoria del objeto.


* **Sincronización:** Modificar la instancia original actualiza automáticamente la información visible en la lista.


* **Desventajas:**
* **Efectos secundarios (Aliasing / Mutabilidad compartida):** Si desde fuera del método/lista se modifica la `Persona` mediante un *setter* (ej. `persona.setNombre(...)`), el estado del nodo dentro de la lista también cambia. Esto puede provocar fallos inesperados de integridad si la lista requería inmutabilidad.





---

### C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

* **Con Implementación A:** La lista mantiene una versión totalmente independiente de cada `Persona`. Si el programa principal modifica una instancia de `Persona` que fue añadida a la lista, el dato dentro de la lista permanecerá inalterado.


* **Con Implementación B:** La lista comparte referencias a los objetos con el resto del programa. Cualquier cambio realizado en el objeto `Persona` fuera de la lista afectará inmediatamente al elemento que la lista almacena y retorna.



---

## 2. Gestión de referencias

### Código de referencia

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
            if (actual.getPersona().getDni().equals(persona.getDni())) {
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

---

### a) Si tenemos:

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");

```

**¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?**

Tendrá el nombre **"Juan Manuel"**.

**Justificación:**
`obtenerPersona("1234")` retorna la referencia directa al objeto `Persona` almacenado en el nodo. Al ejecutar `personaEncontrada.setNombre("Juan Manuel")`, se está modificando exactamente el mismo objeto en memoria al que apunta la lista. *(Asumiendo la Implementación B de `Nodo`, donde el nodo guarda la referencia original)*.

---

### b) ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos")` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B)?

* **Si usamos la Implementación B:** El nombre en la lista **sí cambia** a **"Juan Carlos"**. Como el nodo guardó la referencia directa a `juan`, cualquier modificación mediante la variable `juan` afecta directamente al objeto en la lista.


* **Si usamos la Implementación A:** El nombre en la lista **NO cambia** (permanecerá como estaba al insertarlo). Esto se debe a que la Implementación A creó un nuevo objeto `Persona` con los mismos datos mediante un constructor de copia al momento de la inserción.



---

## 3. Referencias y constructores

### Código de referencia

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

---

### a) ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

Hay **2 objetos `Persona` diferentes** en memoria.

**Justificación:**

1. `new Persona("Manuel", "1234")`: Se crea el **objeto 1** en el montón.


2. `new Persona(manuel)`: Se invoca `new`, por lo que se crea el **objeto 2** (una copia con el mismo contenido).


3. `Persona referencia = manuel`: **No** crea un objeto nuevo. Simplemente asigna a la variable `referencia` el mismo puntero que apunta al **objeto 1**.



Por lo tanto, la palabra clave `new` aplicada a la clase `Persona` solo se invoca 2 veces.

---

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

Se verán afectados el **primer** y el **tercer** elemento insertados en la lista (correspondientes a `manuel` y `referencia`).

**Justificación:**
Tanto `manuel` como `referencia` apuntan exactamente al mismo objeto en la memoria heap. Al modificar el nombre de `manuel`, el cambio se refleja en todas las referencias que apunten a ese mismo espacio de memoria. El segundo elemento (`copia`) es un objeto independiente y mantendrá su estado intacto.

---

## 4. Diseño y optimización

### Requisitos solicitados

Diseñar una implementación de una clase `Lista` que:

1. Permita detectar si se está intentando insertar una referencia a una `Persona` que ya existe en la lista.


2. Permita elegir si queremos insertar una copia de la `Persona` o mantener la referencia.


3. Sea eficiente en el uso de memoria.



---

### Propuesta de diseño (Java)
``` java
public class ListaOptimizada {

    private static class Nodo {
        private Persona persona;
        private Nodo siguiente;

        public Nodo(Persona persona, Nodo siguiente) {
            this.persona = persona;
            this.siguiente = siguiente;
        }

        public Persona getPersona() {
            return persona;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }
    }

    private Nodo primero;

    public ListaOptimizada() {
        this.primero = null;
    }

    public boolean insertar(Persona persona, boolean hacerCopia) {
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula.");
        }

        Nodo actual = primero;
        while (actual != null) {
          
            if (actual.getPersona() == persona) {
                System.out.println("Aviso: Ya existe una referencia a esta misma Persona en la lista.");
                return false; // Se rechaza la inserción por duplicidad de referencia
            }
            actual = actual.getSiguiente();
        }

     
        Persona personaAInsertar = hacerCopia ? new Persona(persona) : persona;

        
        primero = new Nodo(personaAInsertar, primero);
        return true;
    }
}
````


---

### Justificación de las decisiones tomadas

* **Control de Copia vs. Referencia:**
Se agregó el parámetro booleano `hacerCopia` al método de inserción. Si el usuario necesita aislamiento de mutaciones, pasa `true` (invocando el constructor de copia); si busca eficiencia máxima de memoria, pasa `false`.


* **Eficiencia de memoria:**
Al no forzar copias por defecto, se evita duplicar objetos innecesariamente. Además, la estructura de lista enlazada permite crecimiento dinámico sin desperdicio de capacidad.
