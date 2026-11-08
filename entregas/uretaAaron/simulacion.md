# Ejercicios: referencias, copias y listas enlazadas

## Pregunta 1: Implementaciones de `Nodo`

**Implementación A**

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

**Implementación B**

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

### a) ¿Qué implementación usa mejor la memoria?

La **B**. Un objeto guarda una dirección (una referencia), y la B solo guarda esa dirección. La A, al usar `new`, reserva memoria nueva para cada persona.

### b) Problemas y ventajas de cada una

**Implementación A**
- **Ventaja:** nadie puede modificar la persona desde fuera de la lista.
- **Problema:** si se insertan 100 personas, se crean 100 nodos y 100 personas nuevas, con el consumo de memoria que eso supone.

**Implementación B**
- **Ventaja:** es rápida y casi no gasta memoria, porque solo guarda la referencia.
- **Problema:** si se cambia la persona desde fuera, también cambia la lista.

### c) Efecto sobre el comportamiento de la lista

- **A:** la lista está blindada, independientemente de lo que pase fuera.
- **B:** la lista está conectada al exterior, y cualquier cambio hecho fuera se refleja dentro.

---

## Pregunta 2: Gestión de referencias

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

### a)

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```

**¿Qué nombre tendrá la persona almacenada en la lista?**

**"Juan Manuel"**. `lista.obtenerPersona("1234")` devuelve la dirección del objeto real en memoria, no una copia. Al cambiar el nombre a través de `personaEncontrada`, se modifica el objeto original.

### b)

```java
juan.setNombre("Juan Carlos");
```

**¿Depende de la implementación del Nodo?**

Sí.

- **Con B:** el nombre pasa a ser "Juan Carlos", porque `juan` y el nodo apuntan a la misma persona en memoria.
- **Con A:** se queda como "Juan", porque la lista guardó una copia independiente.

---

## Pregunta 3: Referencias y constructores

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

### a) ¿Cuántos objetos `Persona` diferentes hay en memoria?

Hay **2**:

- `manuel` es el objeto 1, creado con `new`.
- `copia` es el objeto 2, creado con el constructor de copia.
- `referencia` no crea nada nuevo, es solo un puntero al objeto 1.

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se ven afectados?

Cambian el **1.º y el 3.º**, es decir, `manuel` y `referencia`.

Esos dos no son realmente dos cosas distintas. Son como dos chats que apuntan a la misma persona: si a esa persona le cambias el nombre, da igual por cuál de los dos le hables, ya se llama "Manuel Antonio".

`copia`, en cambio, es otra persona distinta que simplemente se llamaba igual. Si le cambias el nombre al original, ella ni se entera, así que sigue siendo "Manuel".

---

## Pregunta 4: Diseño y optimización

### Diagrama UML

```
┌──────────────────────────────────────┐
│            ListaPersonas             │
├──────────────────────────────────────┤
│ - elementos: List<Persona>           │
│ - refs: Set<Persona>  (IdentityHash) │
├──────────────────────────────────────┤
│ + insertar(p: Persona,               │
│            copiar: boolean): boolean │
│ + contieneReferencia(p: Persona):    │
│                         boolean      │
└───────────────┬──────────────────────┘
                │ 0..*
                │ contiene
                ▼
┌──────────────────────────────────────┐
│               Persona                │
├──────────────────────────────────────┤
│ - nombre: String                     │
├──────────────────────────────────────┤
│ + Persona(nombre: String)            │
│ + Persona(otra: Persona)  «copia»    │
│ + setNombre(n: String): void         │
└──────────────────────────────────────┘
```

### Código

```java
public class ListaPersonas {
    private final List<Persona> elementos = new ArrayList<>();
    private final Set<Persona> refs =
            Collections.newSetFromMap(new IdentityHashMap<>());

    public boolean contieneReferencia(Persona p) {
        return refs.contains(p);
    }

    public boolean insertar(Persona p, boolean copiar) {
        Persona x = copiar ? new Persona(p) : p;
        if (!refs.add(x)) return false;
        return elementos.add(x);
    }
}
```

### Justificación

- **Detección de referencias repetidas:** `IdentityHashMap` compara por `==`, así que detecta si es el mismo objeto en O(1), sin recorrer la lista.
- **Copia o referencia:** el parámetro `copiar` deja elegir al que llama.
- **Memoria:** por defecto se guarda la referencia, y solo se copia cuando se pide explícitamente, así no se duplican objetos.

---

## Pregunta 5: Análisis de código

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

### a) Problemas potenciales

- **Fuga de datos:** `buscarPersona` devuelve el objeto directo de la lista, así que quien lo recibe puede modificarlo.
- **Pérdida de integridad:** si la persona que se inserta viene de fuera y luego cambia, se rompe el contenido de la lista.

### b) Cómo mejorarlo

Aplicando **copia defensiva**: al entrar se guarda una copia, y al salir se devuelve otra copia. Así la lista nunca comparte sus objetos con el exterior.

### c) Solución

```java
public class GestorPersonas {
    private Lista lista;

    public GestorPersonas() {
        this.lista = new Lista();
    }

    public void agregarPersona(Persona persona) {
        if (persona != null && !lista.contiene(persona)) {
            lista.insertarPersona(persona, true);
        }
    }

    public Persona buscarPersona(String dni) {
        Persona original = lista.obtenerPersona(dni);
        if (original == null) {
            return null;
        }
        return new Persona(original);
    }
}
```