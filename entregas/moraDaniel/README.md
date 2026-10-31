# Simulación ExamenParcial · Respuestas

**Alumno:** Daniel Mora
**Asignatura:** Estructura de Datos y Algoritmos I


> Supuestos: `Persona` tiene los getters y setters que usa el enunciado (`getNombre`, `getDni`, `setNombre`) y `Nodo` tiene `getPersona()` y `getSiguiente()`. Salvo que se diga otra cosa, la `Lista` de las preguntas 2, 3 y 5 usa la **implementación B** del nodo.

---

## Pregunta 1: análisis de implementaciones

La única diferencia entre las dos implementaciones es una línea del constructor:

|       | Qué guarda el nodo                                                                | `new Persona` por inserción |
| ----- | --------------------------------------------------------------------------------- | --------------------------- |
| **A** | Una **copia** de la persona: `new Persona(persona.getNombre(), persona.getDni())` | 1                           |
| **B** | Una **referencia** a la persona original: `this.persona = persona`                | 0                           |

### a) ¿Qué implementación utiliza mejor los recursos de memoria?

**La implementación B.** El nodo solo guarda una referencia a un objeto `Persona` que ya existía, así que insertar no crea ningún objeto `Persona` nuevo. La implementación A ejecuta un `new Persona(...)` en cada inserción: con *n* elementos en la lista hay *n* objetos `Persona` de más, y además se gasta tiempo en crearlos.

El gasto extra de A es algo menor de lo que parece. La copia es **superficial**: la `Persona` nueva recibe `persona.getNombre()` y `persona.getDni()`, que son referencias a los **mismos** objetos `String` del original. Como los `String` son inmutables, compartirlos es seguro. Así que cada nodo de A cuesta un objeto `Persona` más (con dos referencias), no una copia de los textos.

### b) Problemas y ventajas de cada implementación

**Implementación A (copia)**

- ✅ **Independencia:** lo que se haga fuera con el objeto original no afecta a la lista.
  ```java
  Persona juan = new Persona("Juan", "1234");
  lista.insertarPersona(juan);
  juan.setNombre("Juan Carlos");   // la lista sigue guardando "Juan"
  ```
- ✅ **Protege las reglas de la lista:** si la lista no admite DNIs repetidos, nadie puede romperlo desde fuera cambiando el objeto original.
- ❌ **Más memoria y más tiempo:** un objeto nuevo por inserción.
- ❌ **Los cambios legítimos no se ven:** si Juan cambia de nombre de verdad, hay que actualizarlo también dentro de la lista.
- ❌ **Se pierde la identidad:** `lista.obtenerPersona("1234") == juan` da `false`, aunque sea "la misma persona".
- ❌ **Copia frágil:** se copia campo a campo. Si `Persona` gana un atributo (por ejemplo `edad`), A lo pierde sin avisar. Sería mejor un constructor de copia `new Persona(persona)`, que se mantiene dentro de la propia clase.
- ❌ Si se inserta `null`, A lanza `NullPointerException` al llamar a `persona.getNombre()`.

**Implementación B (referencia)**

- ✅ **Eficiente:** no crea objetos, así que la inserción es más rápida y gasta menos memoria.
- ✅ **Datos siempre actualizados:** si el objeto cambia fuera, la lista lo ve. Es útil cuando se *quiere* compartir el objeto.
- ❌ **Aliasing (efectos secundarios):** cualquiera que tenga una referencia al objeto puede cambiar el contenido de la lista sin pasar por ella.
  ```java
  Persona juan = new Persona("Juan", "1234");
  lista.insertarPersona(juan);
  juan.setNombre("Juan Carlos");   // la lista también muestra "Juan Carlos"
  ```
- ❌ **Puede romper las reglas de la lista:** si alguien cambia el DNI de un objeto que ya está dentro, pueden quedar dos personas con el mismo DNI, aunque `contiene` lo impidiera al insertar.
- ❌ El **mismo objeto puede estar varias veces** en la lista (se ve en la pregunta 3).

### c) ¿Cómo afectaría cada implementación al comportamiento de la lista?

- **Con A**, la lista es una **foto**: guarda cómo eran las personas **en el momento de insertarlas**, y sus datos son solo suyos.
- **Con B**, la lista es una **ventana** a objetos que viven fuera: muestra cómo son **ahora**, y comparte los datos con el resto del programa.

Ni siquiera A protege del todo. Si la lista tiene un método que devuelve `actual.getPersona()` (como `obtenerPersona` en la pregunta 2), desde fuera se puede modificar la copia que está dentro. Para proteger de verdad los datos hay que copiar **al entrar y al salir** de la lista.

---

## Pregunta 2: gestión de referencias

### a) ¿Qué nombre tendrá la persona almacenada en la lista?

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```

**"Juan Manuel"**, con cualquiera de las dos implementaciones del nodo.

**Por qué:** `obtenerPersona` no devuelve una copia, sino `actual.getPersona()`, que es la **referencia al objeto guardado dentro del nodo**. Por eso `personaEncontrada` y el nodo apuntan al mismo objeto, y `setNombre` modifica el objeto que está en la lista. La referencia interna "se escapa" por el getter.

La diferencia está en la variable `juan`:
- **B:** `juan`, el nodo y `personaEncontrada` apuntan al mismo objeto, así que `juan` también pasa a llamarse "Juan Manuel".
- **A:** el nodo tiene una copia, así que se modifica esa copia y `juan` sigue llamándose "Juan".

| Nodo | Nombre en la lista | `juan.getNombre()` |
| ---- | ------------------ | ------------------ |
| A    | Juan Manuel        | Juan               |
| B    | Juan Manuel        | Juan Manuel        |

### b) ¿Qué sucede con `juan.setNombre("Juan Carlos")` después de insertarlo? ¿Depende de la implementación?

**Sí, depende de la implementación:**

- **B (referencia):** el nodo apunta al mismo objeto que `juan`, así que la lista muestra **"Juan Carlos"**.
- **A (copia):** el nodo tiene su propia copia, hecha al insertar. Cambiar `juan` no la afecta: la lista conserva el nombre que tenía ("Juan", o "Juan Manuel" si antes se ejecutó el código del apartado a).

**Idea clave:**
- En a) no depende de la implementación, porque se modifica **lo que devuelve la lista** (el objeto de dentro).
- En b) sí depende, porque se modifica **el objeto original de fuera**, y solo B lo comparte con la lista.

---

## Pregunta 3: referencias y constructores

```java
Persona manuel = new Persona("Manuel", "1234");   // new nº 1
Persona copia = new Persona(manuel);              // new nº 2 (constructor de copia)
Persona referencia = manuel;                      // sin new: mismo objeto que manuel
```

```
manuel ─────┐
            ▼
         [Persona "Manuel"]   ← objeto 1
            ▲
referencia ─┘

copia ────► [Persona "Manuel"]   ← objeto 2
```

### a) ¿Cuántos objetos Persona diferentes hay en memoria?

**2 objetos `Persona`** (con la implementación B del nodo):

1. `new Persona("Manuel", "1234")` crea el objeto 1.
2. `new Persona(manuel)` crea el objeto 2: el constructor de copia hace un objeto nuevo con los mismos datos.
3. `referencia = manuel` **no crea nada**: copia la referencia, así que `referencia` apunta al objeto 1.
4. `insertarPersona` crea objetos `Nodo`, pero ningún objeto `Persona`.

Comprobación: `copia == manuel` da `false` (son dos objetos) y `referencia == manuel` da `true` (es el mismo objeto).

Si la lista usara la **implementación A**, cada inserción haría otro `new Persona`: serían 2 + 3 = **5 objetos**.

El constructor de copia accede a `persona.nombre`, que es privado, y es legal: en Java `private` es privado **de la clase**, no de cada objeto.

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se ven afectados?

`insertarPersona` inserta al **principio**, así que la lista queda en orden inverso al de inserción:

```
primero → [referencia] → [copia] → [manuel] → null
            objeto 1      objeto 2    objeto 1
```

- **1.er nodo** (`referencia`): apunta al objeto 1, así que **cambia**.
- **2.º nodo** (`copia`): apunta al objeto 2, así que **no cambia**.
- **3.er nodo** (`manuel`): apunta al objeto 1, así que **cambia**.

**Por qué:** `manuel` y `referencia` son el **mismo objeto metido dos veces** en la lista, y `copia` es un objeto independiente. Resultado: `[Manuel Antonio] → [Manuel] → [Manuel Antonio]`.

Con la implementación A **no cambiaría ninguno**, porque cada nodo guarda su propia copia.

**Problema añadido:** la lista contiene el mismo objeto dos veces y tres entradas con el mismo DNI, porque `insertarPersona` no comprueba duplicados. La pregunta 4 lo resuelve.

---

## Pregunta 4: diseño y optimización

### Diseño

```java
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;          // el nodo no decide: guarda lo que le dan
        this.siguiente = siguiente;
    }

    public Persona getPersona() { return persona; }
    public Nodo getSiguiente() { return siguiente; }
}

public class ListaPersonas {
    private Nodo primero;

    public boolean insertar(Persona persona, boolean guardarCopia) {
        if (persona == null || contieneReferencia(persona) || contieneDni(persona.getDni())) {
            return false;                // no se inserta: es null o ya está
        }
        Persona personaAGuardar = guardarCopia ? new Persona(persona) : persona;
        primero = new Nodo(personaAGuardar, primero);
        return true;
    }

    public boolean contieneReferencia(Persona persona) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getPersona() == persona) {          // == → el MISMO objeto
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean contieneDni(String dni) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getPersona().getDni().equals(dni)) { // equals → el mismo contenido
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

Ejemplo de uso:

```java
ListaPersonas lista = new ListaPersonas();
Persona ana = new Persona("Ana", "1");
lista.insertar(ana, false);                  // true: se guarda la referencia
lista.insertar(ana, true);                   // false: esa referencia ya está
lista.insertar(new Persona(ana), false);     // false: otro objeto, pero el mismo DNI
lista.insertar(new Persona("Luis", "2"), true); // true: se guarda una copia
```

### Justificación de las decisiones

1. **Detectar una referencia repetida con `==`.** El enunciado pide saber si se inserta *una referencia a una Persona que ya existe*, es decir, el **mismo objeto**. `==` compara identidad (si dos variables apuntan al mismo objeto), que es justo lo que se busca.
2. **Detectar también el mismo DNI con `equals`.** Si antes se guardó una copia, el objeto original ya no está en la lista con `==`, pero es la misma persona. Comprobar el DNI evita duplicados lógicos.
3. **Elegir copia o referencia con un parámetro (`boolean guardarCopia`).** Quien inserta sabe si necesita aislar los datos (copia) o compartirlos (referencia). La decisión se toma **en la lista**, no en el nodo. Así hay un único tipo de nodo, que se limita a enlazar.
4. **Constructor de copia `new Persona(persona)`** en vez de copiar campo a campo: si `Persona` gana atributos, solo hay que cambiar la clase `Persona`.
5. **Eficiencia en memoria:**
   - Solo se hace `new Persona` cuando se pide una copia. Por defecto se comparte la referencia.
   - Nunca se guardan duplicados, así que no se gastan nodos repetidos.
   - El nodo solo guarda lo imprescindible (persona y siguiente).
   - La lista enlazada crece de uno en uno, sin reservar huecos vacíos como un array.
   - Insertar al principio cuesta O(1) y no desplaza nada.
6. **Coste asumido:** comprobar duplicados recorre la lista (O(n)). Es el mismo coste que ya tiene `contiene` en el código original, y a cambio la lista nunca tiene datos repetidos.

---

## Pregunta 5: análisis de código

### a) Problemas potenciales respecto al manejo de referencias

1. **`lista` nunca se inicializa.** No hay constructor ni `= new Lista()`, así que vale `null` y la primera llamada a `agregarPersona` lanza `NullPointerException`.
2. **Entrada sin copia:** `agregarPersona` guarda la referencia que recibe. Quien llamó al método **sigue teniendo esa referencia** y puede modificar el objeto que está dentro de la lista cuando quiera.
   ```java
   Persona eva = new Persona("Eva", "3");
   gestor.agregarPersona(eva);
   eva.setNombre("Otra");          // cambia la persona guardada en el gestor
   ```
3. **Salida sin copia:** `buscarPersona` devuelve la referencia interna, así que quien la recibe puede modificar los datos de la lista **sin pasar por el gestor**.
   ```java
   gestor.buscarPersona("3").setNombre("Otra");   // modifica el dato interno
   ```
4. **Se puede romper la regla de "sin DNIs repetidos":** `agregarPersona` comprueba el DNI al insertar, pero después alguien con una referencia podría cambiar el DNI (si existiera `setDni`) y dejar dos personas con el mismo DNI.
5. **`null` no se controla:** `agregarPersona(null)` provoca `NullPointerException` dentro de `contiene`, al llamar a `persona.getDni()`.
6. **`agregarPersona` es `void`:** quien llama no sabe si la persona se insertó o se descartó por estar repetida.

### b) ¿Cómo mejorarlo para evitar modificaciones no deseadas?

Con una **copia defensiva en las dos fronteras** de la clase (al entrar y al salir), además de inicializar la lista:

```java
public class GestorPersonas {
    private ListaPersonas lista;

    public GestorPersonas() {
        lista = new ListaPersonas();                  // evita el NullPointerException
    }

    public boolean agregarPersona(Persona persona) {
        return lista.insertar(persona, true);         // ENTRADA: se guarda una copia
    }

    public Persona buscarPersona(String dni) {
        Persona encontrada = lista.obtenerPersona(dni);
        if (encontrada == null) {
            return null;
        }
        return new Persona(encontrada);               // SALIDA: se devuelve una copia
    }
}
```

- **Al entrar** se guarda una copia, así que la referencia de quien llama ya no apunta a nada que esté dentro del gestor.
- **Al salir** se devuelve una copia, así que modificar el resultado de `buscarPersona` no afecta a los datos internos.
- `agregarPersona` devuelve `boolean`, así que quien llama sabe si se insertó.
- `insertar` (pregunta 4) ya rechaza `null` y los duplicados.

Comprobación:

```java
GestorPersonas gestor = new GestorPersonas();
Persona eva = new Persona("Eva", "3");
gestor.agregarPersona(eva);
eva.setNombre("Hackeada");
gestor.buscarPersona("3").setNombre("Hackeada2");
gestor.buscarPersona("3").getNombre();   // sigue devolviendo "Eva"
```

### c) Solución que proteja la integridad de los datos

Hay dos formas. La segunda es la más robusta.

**1. Copias defensivas (apartado b).** Funciona con la clase `Persona` actual. Su coste es crear un objeto en cada inserción y en cada búsqueda.

**2. Hacer `Persona` inmutable:**

```java
public final class Persona {
    private final String nombre;
    private final String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Persona(Persona persona) {
        this.nombre = persona.nombre;
        this.dni = persona.dni;
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    // sin setters
}
```

Si nadie puede modificar una `Persona` después de crearla, **compartir referencias deja de ser peligroso**. La lista puede guardar y devolver referencias (tan eficiente como la implementación B) con la seguridad de la implementación A, y sin hacer copias. Es la misma razón por la que en Java es seguro compartir objetos `String`.

Si hace falta cambiar un dato, el cambio pasa por el gestor, que puede validarlo:

```java
public boolean cambiarNombre(String dni, String nuevoNombre)
// busca la persona, la sustituye por new Persona(nuevoNombre, dni) y devuelve si lo ha hecho
```

**Conclusión:** la integridad de los datos se protege **controlando las fronteras** de la clase. Se puede copiar al entrar y al salir, o hacer `Persona` inmutable para que compartir referencias sea seguro. En los dos casos, cualquier modificación pasa por métodos del gestor que comprueban las reglas (por ejemplo, que no se repita el DNI).
