# Examen: listas enlazadas y referencias en Java — Resolución

---

## 1. Análisis de implementaciones (Nodo A vs B)

### A. Memoria

B usa mejor la memoria. Solo guarda la referencia que recibe, así que no crea ningún objeto. A crea un `Persona` nuevo en cada inserción, con su cabecera y dos referencias.

Aun así, la copia de A es superficial: los `String` de nombre y DNI se comparten, porque `getNombre()` devuelve la referencia y los String son inmutables. Lo único que se duplica es el envoltorio `Persona`.

```
Nodo A                      Nodo B
nodo.persona ─► P' (nueva)  nodo.persona ─┐
juan ─────────► P           juan ─────────┴► P
P y P' comparten los String
```

### B. Problemas y ventajas

- **A, ventaja:** aísla la lista. Si alguien hace `juan.setNombre(...)` desde fuera, la lista no cambia.
- **A, problemas:**
  - La protección es solo de entrada. `obtenerPersona()` devuelve la copia interna, así que se puede modificar por la salida.
  - Copia campo a campo con `(nombre, dni)` en lugar de usar `new Persona(persona)`. Si mañana `Persona` tiene un campo más, la copia lo pierde sin avisar.
  - Lanza NPE si recibe `null`.
- **B, ventaja:** no ocupa memoria extra y mantiene la identidad del objeto.
- **B, problemas:**
  - Aliasing: cualquier modificación externa altera la lista, incluido un cambio de DNI, lo que rompe la unicidad que intenta garantizar `contiene`.
  - Acepta `null` sin quejarse, y el fallo aparece más tarde, en `contiene`.

### C. Comportamiento de la lista

`contiene` y `obtenerPersona` funcionan igual en ambas, porque comparan por DNI. Lo que cambia es la identidad del objeto devuelto y la propagación de cambios.

Caso concreto para comprobarlo:

```java
lista.insertarPersona(juan);
System.out.println(lista.obtenerPersona("1234") == juan);
```

---

## 2. Gestión de referencias

### a)

El nombre será **"Juan Manuel" con A y con B**. `obtenerPersona` devuelve la referencia guardada en el nodo, y `setNombre` modifica ese mismo objeto.

- **Con A:** el objeto modificado es la copia. La variable `juan` sigue valiendo "Juan".
- **Con B:** es el mismo objeto, así que `juan` también pasa a valer "Juan Manuel".
- Si `getPersona()` devolviera una copia, la lista seguiría con "Juan".

### b)

El resultado **sí depende** del nodo:

- **A:** la lista no se entera, porque guarda otro objeto.
- **B:** la lista pasa a mostrar "Juan Carlos", porque `juan` y el nodo apuntan al mismo objeto.

---

## 3. Referencias y constructores

### a)

`main` crea **2 objetos Persona**:

- `manuel` es el primero.
- `copia` es el segundo, creado con `new` y el constructor de copia.
- `referencia` no crea nada: es un alias de `manuel`.

La cuenta total depende del nodo, que el enunciado no fija:

- **Con B:** 2 en total.
- **Con A:** cada inserción crea uno más, así que son **5**.

En ningún caso se crean Strings nuevos: los dos objetos comparten "Manuel" y "1234".

```
PILA              MONTÍCULO (Nodo B)
manuel     ─┐
referencia ─┴──► P1
copia      ─────► P2

lista.primero ─► N3 ─► N2 ─► N1 ─► null
                 │     │     │
                 P1    P2    P1
```

La lista queda en orden inverso porque se inserta por la cabeza. Con A, cada nodo apuntaría a su propia copia: P3, P4 y P5.

### b)

De nuevo depende del nodo:

- **Con B:** cambian **2 de los 3 nodos**, los que se insertaron con `manuel` y con `referencia`, porque son el mismo P1. El de `copia` no cambia.
- **Con A:** no cambia **ninguno**.

Detalle extra: la lista acaba con tres nodos con DNI "1234", porque `insertarPersona` no comprueba duplicados.

---

## 4. Diseño y optimización

```java
class Lista {
    public enum Modo { REFERENCIA, COPIA }
    private Nodo primero;  // Nodo versión B

    public boolean contieneReferencia(Persona p) {
        for (Nodo a = primero; a != null; a = a.getSiguiente())
            if (a.getPersona() == p) return true;
        return false;
    }

    public boolean insertarPersona(Persona p, Modo modo) {
        if (p == null) throw new IllegalArgumentException();
        if (contieneReferencia(p)) return false;
        Persona g = (modo == Modo.COPIA) ? new Persona(p) : p;
        primero = new Nodo(g, primero);
        return true;
    }
}
```

**Justificación:**

- **Detección con `==` y no con `equals`.** El enunciado pide detectar la misma *referencia*, no la misma persona. Si también se quieren impedir DNIs duplicados, eso es otro requisito y conviene separarlo en otro método.
- **La decisión de copiar la toma la Lista, no el Nodo.** Por eso el nodo es el B: así no se copia dos veces.
- **Eficiencia de memoria:**
  - El modo por defecto es REFERENCIA.
  - La copia reutiliza los String.
  - La búsqueda recorre la lista sin estructuras auxiliares.
  - Contrapartida: la búsqueda es O(n). Un `IdentityHashMap` la haría O(1) a cambio de más memoria.

**Limitación:** si se inserta en modo COPIA, el original no queda en la lista, así que `contieneReferencia(original)` devolverá `false` después.

---

## 5. Análisis de código (GestorPersonas)

### a) Problemas

1. **`lista` nunca se inicializa** y no se ve ningún constructor. La primera llamada a `agregarPersona` lanza NPE. Es el error más grave, y se comprueba con `new GestorPersonas().agregarPersona(juan)`.
2. **`buscarPersona` expone el objeto interno.** El llamante puede cambiarlo, incluido el DNI si existe un `setDni`, y entonces `contiene` deja de proteger contra duplicados.
3. **Con el Nodo B, `agregarPersona` guarda el alias del llamante.** El llamante sigue pudiendo modificarlo.
4. **`agregarPersona` es `void`.** Si rechaza a la persona por estar ya en la lista, falla en silencio. Además, pasarle `null` provoca NPE.

### b) Mejora

- Inicializar la lista.
- Hacer copias defensivas a la entrada y a la salida (`new Persona(p)`).
- Que los cambios pasen por métodos del gestor, por ejemplo `cambiarNombre(dni, nuevo)`.

### c) Solución robusta: `Persona` inmutable

Si nadie puede modificar una Persona, ya no hacen falta copias. Es seguro y además eficiente en memoria, así que enlaza con la pregunta 4.

```java
public final class Persona {
    private final String nombre, dni;

    public Persona(String nombre, String dni) {
        this.nombre = Objects.requireNonNull(nombre);
        this.dni = Objects.requireNonNull(dni);
    }

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }

    public Persona conNombre(String n) {
        return new Persona(n, dni);
    }
}

public class GestorPersonas {
    private final Lista lista = new Lista();

    public boolean agregarPersona(Persona p) {
        if (p == null || lista.contiene(p)) return false;
        lista.insertarPersona(p);
        return true;
    }

    public Persona buscarPersona(String dni) {
        return lista.obtenerPersona(dni);
    }
}
```

---