# Resolución y Auditoría Técnica: Simulacro de Examen EDA I
**Asignatura:** Estructura de Datos y Algoritmos I  
**Rol:** Senior Software Engineer / Lead Technical Reviewer  
**Formato de salida:** Repositorio Markdown para GitHub  

---

## Índice
1. [Análisis de Implementaciones del Nodo (A vs. B)](#1-análisis-de-implementaciones-del-nodo-a-vs-b)
2. [Gestión de Referencias y Estado de la Lista](#2-gestión-de-referencias-y-estado-de-la-lista)
3. [Referencias, Constructores y Conteo de Objetos](#3-referencias-constructores-y-conteo-de-objetos)
4. [Diseño y Optimización de la Estructura `Lista`](#4-diseño-y-optimización-de-la-estructura-lista)
5. [Análisis de Código y Seguridad en `GestorPersonas`](#5-análisis-de-código-y-seguridad-en-gestorpersonas)

---

## 1. Análisis de Implementaciones del Nodo (A vs. B)

### Código Evaluado
```java
// Implementación A (Copia Defensiva)
class Nodo {
    private Persona persona;
    private Nodo siguiente;
    
    public Nodo (Persona persona, Nodo siguiente) {
        // Asumiendo corrección sintáctica: this.persona = new Persona(...)
        this.persona = new Persona (persona.getNombre(), persona.getDni());
        this.siguiente = siguiente;
    }
}

// Implementación B (Paso por Referencia)
class Nodo {
    private Persona persona;
    private Nodo siguiente;
    
    public Nodo (Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }
}
```

---

### A. ¿Qué implementación utiliza mejor los recursos de memoria?

**Respuesta:** La **Implementación B** utiliza mejor los recursos de memoria.

#### Justificación
* **Implementación B ($O(1)$ de memoria extra por inserción):** Almacena directamente la referencia del objeto `Persona` existente en el *Heap*. El sobrecoste de memoria por cada nodo se limita a las referencias internas del nodo (la referencia a `persona` y a `siguiente`, equivalentes a $4$ u $8$ bytes cada una según la arquitectura de $32$ o $64$ bits con *Compressed OOPs* en la JVM).
* **Implementación A ($O(N)$ duplicación de objetos):** Realiza una **copia defensiva** en cada instanciación del nodo. Por cada nodo añadido, la JVM debe reservar memoria para:
  1. La cabecera del objeto `Persona` en el Heap (12-16 bytes).
  2. Las referencias internas de `nombre` y `dni`.
  3. Nuevas instancias o referencias de los `String` asignados (si no provienen de la *String Pool*).

Esto duplica el consumo de Heap por cada nodo y genera mayor presión sobre el Recolector de Basura (*Garbage Collector*).

---

### B. Ventajas y problemas de cada implementación

| Criterio | Implementación A (Copia Defensiva) | Implementación B (Paso por Referencia) |
| :--- | :--- | :--- |
| **Ventajas** | **Encapsulamiento e Inmutabilidad Externa:** El estado interno de la lista está protegido contra cambios en el exterior. | **Rendimiento y Eficiencia Espacial:** Inserción rápida $O(1)$ sin instanciar objetos adicionales. Ahorro de memoria Heap. |
| **Problemas** | **Sobrecostes y Pérdida de Identidad:** Mayor huella de memoria. La comparación por identidad (`==`) falla entre el objeto original y el guardado. | **Aliasing (Efectos Secundarios Imprevistos):** Si el objeto `Persona` es mutable y se modifica fuera, la lista cambia silenciosamente. |

#### Ejemplo de problema en Implementación B (Aliasing)
```java
Persona p = new Persona("Ana", "111A");
lista.insertarPersona(p); // Nodo B almacena la referencia p

p.setDni("999Z"); // Se altera el DNI fuera de la lista

// Si la lista dependía del DNI para búsquedas o mantenía un orden por DNI,
// la estructura de datos ha quedado CORRUPTA e inconsistente en memoria.
```

#### Ejemplo de problema en Implementación A (Pérdida de identidad)
```java
Persona p = new Persona("Ana", "111A");
lista.insertarPersona(p); // Nodo A crea una nueva Persona("Ana", "111A") en el Heap

// Falla si buscamos por identidad de objeto
boolean existe = (lista.getPrimero().getPersona() == p); // Evaluará FALSE
```

---

### C. Impacto en el comportamiento de una Lista

1. **Si la lista usa Nodo A:**
   * La lista opera como un contenedor **aislado**.
   * Cualquier modificación hecha sobre el objeto `Persona` externo tras la inserción **no afecta** al contenido de la lista.
   * Garantiza la integridad de sus elementos frente a código externo cliente, a costa de un mayor consumo de memoria.

2. **Si la lista usa Nodo B:**
   * La lista opera mediante **referencias compartidas**.
   * Modificar cualquier propiedad de un objeto `Persona` fuera de la lista **se refleja inmediatamente** dentro de la lista.
   * La lista es vulnerable a mutaciones no controladas si los objetos `Persona` no son inmutables.

---

### Diagramas de Memoria (Nodo A vs. Nodo B)

#### Estado de Memoria para Nodo A (Copia Defensiva)
```text
STACK (Variables locales)             HEAP (Memoria de Objetos)
+-----------------------+             +-----------------------------------------+
| persona (ref) --------+-----------> | Persona Obj #1 (Original)               |
|                       |             |  - nombre: "Juan"                       |
+-----------------------+             |  - dni:    "1234"                       |
| nodo (ref) -----------+--+          +-----------------------------------------+
+-----------------------+  |
                           |          +-----------------------------------------+
                           +--------> | Nodo Obj                                |
                                      |  - persona (ref) --------------------+  |
                                      |  - siguiente: null                   |  |
                                      +--------------------------------------|--+
                                                                             |
                                      +--------------------------------------v--+
                                      | Persona Obj #2 (Copia creada por A)     |
                                      |  - nombre: "Juan"                       |
                                      |  - dni:    "1234"                       |
                                      +-----------------------------------------+
```

#### Estado de Memoria para Nodo B (Paso por Referencia)
```text
STACK (Variables locales)             HEAP (Memoria de Objetos)
+-----------------------+             +-----------------------------------------+
| persona (ref) --------+-----------> | Persona Obj #1                          |
|                       |     +-----> |  - nombre: "Juan"                       |
+-----------------------+     |       |  - dni:    "1234"                       |
| nodo (ref) -----------+-----+       +-----------------------------------------+
+-----------------------+     |
                              |       +-----------------------------------------+
                              +-----> | Nodo Obj                                |
                                      |  - persona (ref) -----------------------+ (Apunta al MISMO objeto)
                                      |  - siguiente: null                      |
                                      +-----------------------------------------+
```

---

## 2. Gestión de Referencias y Estado de la Lista

### Auditoría previa de código: Bugs detectados
Al examinar el método `contiene` proporcionado en el examen:
```java
public boolean contiene (Persona persona) {
    Nodo actual = primero;
    while (actual != null) {
        if (actual.getPersona().getDni().equals(persona.getDni())) {
            actual = actual.getSiguiente(); // Bug
            return true;
        }
        // FALLO CRÍTICO: Si el primer nodo no coincide, 'actual' NUNCA avanza.
        // Provoca un BUCLE INFINITO (Infinite Loop).
    }
    return false;
}
```
*Atención:* En un entorno de producción, `contiene` colgaría la ejecución si el elemento buscado no es el primero. Para el análisis semántico de las preguntas a) y b) asumiremos el comportamiento previsto de la gestión de referencias.

---

### Pregunta a)
**Código:**
```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```
**¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?**

#### Respuesta:
Tendrá el nombre **"Juan Manuel"** tanto con Nodo A como con Nodo B (salvo si `obtenerPersona` devolviera una copia, lo cual el código provisto **no hace**).

#### Explicación:
* `obtenerPersona("1234")` ejecuta: `return actual.getPersona();`. Retorna la referencia al objeto `Persona` almacenado dentro del nodo.
* La variable `personaEncontrada` recibe esa referencia directa al objeto del Heap.
* Al invocar `personaEncontrada.setNombre("Juan Manuel")`, se modifica el objeto apuntado dentro de la lista.

---

### Pregunta b)
**¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos")` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos?**

#### Respuesta:
**Sí, depende absolutamente de la implementación del Nodo utilizada.**

1. **Si se usa la Implementación B:**
   * La persona en la lista **cambiará a "Juan Carlos"**.
   * **Razón:** `juan` y el nodo de la lista apuntan exactamente a la **misma instancia física** en el Heap.

2. **Si se usa la Implementación A:**
   * La persona en la lista **mantendrá su nombre original** (o "Juan Manuel" si se aplicó el apartado anterior).
   * **Razón:** El Nodo A creó una instancia totalmente independiente en el Heap al ejecutar `new Persona(...)` durante la inserción. Modificar la referencia externa `juan` no tiene efecto sobre el objeto copia alojado en el nodo.

---

### Diagrama de Memoria (Caso Pregunta b con Nodo B)

```text
STACK                                 HEAP
+-----------------------+             +-----------------------------------------+
| juan (ref) -----------+-----------> | Persona Obj #1                          |
|                       |     +-----> |  - nombre: "Juan Carlos" <--- [Mutado]  |
+-----------------------+     |       |  - dni:    "1234"                       |
| lista (ref) ----------+-+   |       +-----------------------------------------+
+-----------------------+ |   |
                          v   |
                       +------+-----------------+
                       | Lista Obj              |
                       |  - primero (ref) ----+ |
                       +----------------------|-+
                                              v
                                   +--------------------+
                                   | Nodo Obj           |
                                   |  - persona (ref)---+
                                   |  - siguiente: null |
                                   +--------------------+
```

---

## 3. Referencias, Constructores y Conteo de Objetos

### Código Evaluado
```java
Persona manuel = new Persona ("Manuel", "1234");
Persona copia = new Persona (manuel);
Persona referencia = manuel;

Lista lista = new Lista();
lista.insertarPersona (manuel);
lista.insertarPersona (copia);
lista.insertarPersona (referencia);
```

---

### a) ¿Cuántos objetos `Persona` diferentes hay en memoria?

La respuesta varía según la implementación de `Nodo` que esté utilizando la `Lista`:

#### Caso 1: Utilizando Nodo B (Sin copia defensiva en el nodo)
**Hay exactamente 2 objetos `Persona` en memoria Heap.**

1. **Objeto 1:** Creado por `new Persona("Manuel", "1234")` y asignado a `manuel`.
2. **Objeto 2:** Creado por `new Persona(manuel)` (constructor de copia) y asignado a `copia`.
3. `referencia` **no crea un objeto**; únicamente copia la dirección de memoria de `manuel` en el Stack.
4. Las llamadas a `lista.insertarPersona()` guardan las referencias existentes sin instanciar nuevos objetos `Persona`.

#### Caso 2: Utilizando Nodo A (Con copia defensiva en el nodo)
**Hay exactamente 5 objetos `Persona` en memoria Heap.**

1. **Objeto 1:** Instancia original de `manuel`.
2. **Objeto 2:** Instancia de `copia`.
3. **Objeto 3:** Copia creada dentro del Nodo 1 al insertar `manuel`.
4. **Objeto 4:** Copia creada dentro del Nodo 2 al insertar `copia`.
5. **Objeto 5:** Copia creada dentro del Nodo 3 al insertar `referencia`.

---

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados?

#### Con Nodo B:
Se verán afectados el **primer y el tercer elemento insertado** (los nodos correspondientes a `manuel` y `referencia`).

* **Justificación:** La lista insertó en el nodo 1 el objeto 1 (`manuel`) y en el nodo 3 la referencia `referencia` (que apunta también al objeto 1). Como ambos nodos comparten la misma dirección de memoria, modificar `manuel` muta ambos elementos dentro de la lista. El segundo nodo (`copia`, objeto 2) permanece intacto.

#### Con Nodo A:
**Ningún elemento de la lista se verá afectado.**

* **Justificación:** Los nodos de la lista almacenan copias totalmente independientes de las instancias originales. Modificar `manuel` solo afecta al objeto alojado fuera de la lista.

---

### Diagrama de Memoria Completo (`main` con Nodo B)

```text
STACK                                 HEAP
+-----------------------+             +-----------------------------------------+
| manuel ---------------+-----------> | Persona Obj #1 ("Manuel Antonio")       |
| copia ----------------+-------+     |  - dni: "1234"                          |
| referencia -----------+-----+ |     +-----------------------------------------+
| lista ----------------+---+ | |                                  ^
+-----------------------+   | | |                                  |
                            v | |     +----------------------------------+
                       +----+-+-+---+ |                                  |
                       | Lista Obj  | |                                  |
                       | - primero -+-+--+                               |
                       +------------+    |                               |
                                         v                               |
                              +--------------------+                     |
                              | Nodo Obj #3        |                     |
                              |  - persona (ref)---+---------------------+
                              |  - siguiente ------+---+
                              +--------------------+   |
                                                       v
                                            +--------------------+       +-----------------------------------------+
                                            | Nodo Obj #2        | ----> | Persona Obj #2 ("Manuel")               |
                                            |  - persona (ref)---+-----> |  - dni: "1234"                          |
                                            |  - siguiente ------+--+    +-----------------------------------------+
                                            +--------------------+  |
                                                                    v
                                                         +--------------------+
                                                         | Nodo Obj #1        |
                                                         |  - persona (ref)---+
                                                         |  - siguiente: null |
                                                         +--------------------+
```

---

## 4. Diseño y Optimización de la Estructura `Lista`

### Requisitos del Negocio
1. Detectar si se intenta insertar una referencia a una `Persona` que ya existe en la lista (evitar alias o duplicados no deseados).
2. Permitir elegir mediante parámetro si se desea insertar una **copia** de la `Persona` o **mantener la referencia**.
3. Ser eficiente en el uso de memoria.

---

### Propuesta de Arquitectura y Diseño

#### Decisiones de Diseño:
* **Estrategia de Clonación:** Se usa un `enum ModoInsercion { REFERENCIA, COPIA }` para hacer explícita la intención en la API pública, evitando valores booleanos oscuros (`true`/`false`).
* **Verificación de Existencia de Referencia:** Para cumplir con la detección estricta de referencias ya existentes en memoria, se utiliza la comparación por identidad (`==`). Para evitar duplicidad semántica de datos, se complementa opcionalmente con `.equals()`.
* **Eficiencia Espacial:** El nodo solo reserva memoria para una copia si el usuario lo solicita explícitamente (`ModoInsercion.COPIA`). En caso contrario, reutiliza la referencia enviada.

---

### Implementación Código Java Optimizado

```java
package estructura;

import java.util.Objects;

/**
 * Modo de inserción para controlar el aliasing y el aislamiento de memoria.
 */
enum ModoInsercion {
    REFERENCIA,
    COPIA
}

public class ListaOptimizada {

    private static class Nodo {
        private final Persona persona;
        private Nodo siguiente;

        public Nodo(Persona persona, Nodo siguiente) {
            this.persona = persona;
            this.siguiente = siguiente;
        }
    }

    private Nodo primero;
    private int tamanio;

    public ListaOptimizada() {
        this.primero = null;
        this.tamanio = 0;
    }

    /**
     * Comprueba si la MISMA REFERENCIA física de objeto ya está dentro de la lista.
     * Complejidad Espacial: O(1)
     * Complejidad Temporal: O(N)
     */
    public boolean existeReferencia(Persona persona) {
        if (persona == null) return false;
        
        Nodo actual = primero;
        while (actual != null) {
            if (actual.persona == persona) { // Comparación por identidad de memoria (==)
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Inserta una persona permitiendo elegir la política de copia/referencia.
     * @return true si se pudo insertar, false si la referencia ya existía.
     */
    public boolean insertarPersona(Persona persona, ModoInsercion modo) {
        Objects.requireNonNull(persona, "La persona a insertar no puede ser nula");

        // Requisito 1: Detectar si la referencia exacta ya está presente
        if (existeReferencia(persona)) {
            System.err.println("Advertencia: Intento de reinsertar la misma referencia de memoria.");
            return false; 
        }

        // Requisito 2: Decidir si mantener la referencia o crear copia
        Persona personaAInsertar;
        if (modo == ModoInsercion.COPIA) {
            personaAInsertar = new Persona(persona); // Copia defensiva
        } else {
            personaAInsertar = persona; // Reutiliza referencia existente (Eficiencia)
        }

        // Inserción al inicio O(1)
        this.primero = new Nodo(personaAInsertar, primero);
        this.tamanio++;
        return true;
    }

    public int getTamanio() {
        return tamanio;
    }
}
```

---

## 5. Análisis de Código y Seguridad en `GestorPersonas`

### Código Evaluado
```java
public class GestorPersonas {
    private Lista lista;
    
    public void agregarPersona (Persona persona) {
        if (!lista.contiene (persona)) {
            lista.insertarPersona (persona);
        }
    }
    
    public Persona buscarPersona (String dni) {
        return lista.obtenerPersona (dni);
    }
}
```

---

### a) Problemas potenciales respecto al manejo de referencias

1. **Fuga de Encapsulamiento (Escritura Externa Uncontrolled):**
   `buscarPersona(dni)` retorna directamente la referencia interna del objeto `Persona`. Un cliente externo puede mutar la persona devuelta (`persona.setDni("0000")`), modificando la base de datos interna de la lista sin el consentimiento ni validación de `GestorPersonas`.

2. **Aliasing en la entrada (`agregarPersona`):**
   Si la lista almacena por referencia (Nodo B), el invocador mantiene el control del objeto `persona` pasado por argumento y puede modificarlo en cualquier momento posterior.

3. **Propagación del Bug del Método `contiene`:**
   Si la clase `Lista` subyacente es la proporcionada en el examen, `lista.contiene(persona)` provocará un bucle infinito en `agregarPersona` si hay más de un elemento y el DNI no coincide a la primera.

4. **Incompatibilidad NullPointer:**
   La variable `lista` no está inicializada en la declaración ni en un constructor, por lo que cualquier llamada lanzará `NullPointerException`.

---

### b) Estrategias de Mejora e Inmutabilidad

1. **Inmutabilidad del Modelo de Datos (La mejor solución técnica):**
   Si la clase `Persona` se diseña como **inmutable** (campos `final`, sin métodos de modificación/setters), el manejo por referencias (Nodo B) es $100\%$ seguro, elimina el riesgo de aliasing y ofrece el máximo rendimiento en memoria.

2. **Copias Defensivas en los Métodos de Entrada/Salida:**
   Si `Persona` debe ser obligatoriamente mutable, el gestor debe retornar una copia al buscar y guardar una copia al agregar.

---

### c) Solución Propuesta (Refactorización de Producción)

A continuación se presenta la arquitectura recomendada implementando el patrón **Inmutabilidad + Copia Defensiva**:

```java
package gestor;

import java.util.Objects;

/**
 * Rediseño del Modelo Persona como clase Inmutable (o Java Record).
 * Elimina completamente los problemas de Aliasing sin costo innecesario de memoria.
 */
public final class Persona {
    private final String nombre;
    private final String dni;

    public Persona(String nombre, String dni) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.dni = Objects.requireNonNull(dni, "El DNI no puede ser nulo");
    }

    // Constructor de copia
    public Persona(Persona otra) {
        this(otra.nombre, otra.dni);
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}

/**
 * Gestor Refactorizado con seguridad de tipos, inicialización defensiva
 * e integridad de datos garantizada.
 */
public class GestorPersonasRefactorizado {
    
    private final ListaOptimizada lista;

    public GestorPersonasRefactorizado() {
        this.lista = new ListaOptimizada();
    }

    public boolean agregarPersona(Persona persona) {
        if (persona == null) return false;
        
        // Dado que Persona es inmutable, usar ModoInsercion.REFERENCIA es totalmente seguro
        // y óptimo en memoria.
        return lista.insertarPersona(persona, ModoInsercion.REFERENCIA);
    }

    public Persona buscarPersona(String dni) {
        if (dni == null || dni.isBlank()) return null;
        
        // Si obtenerPersona devolviera un objeto mutable, retornaríamos new Persona(p);
        // Al ser inmutable, devolver la referencia es seguro.
        return lista.obtenerPersona(dni);
    }
}
```