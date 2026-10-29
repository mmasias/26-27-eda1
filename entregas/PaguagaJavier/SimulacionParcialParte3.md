# Examen Parcial: Gestión de Memoria, Referencias y Estructuras de Datos

---

# Análisis de implementaciones

## Código de partida:

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

## Pregunta A: ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

- Respuesta:

La Implementación B utiliza mejor los recursos de memoria.

- Justificación:

1. Reutilización de memoria existente ya que implementación B únicamente almacena una referencia (un puntero de 4 u 8 bytes) al objeto Persona que ya fue instanciado previamente en el Heap.

2. Elimina la redundancia ya que la Implementación A duplica el consumo en memoria dinámica al ejecutar new Persona(...) en cada creación de nodo. Esto reserva espacio adicional en el Heap para la cabecera del nuevo objeto (object header), sus campos y las referencias a los String.

## Pregunta B: ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

- Implementación A

    * Ventaja: Encapsulamiento defensivo (Aislamiento).
El nodo crea su propia copia independiente. Las mutaciones posteriores en el objeto original fuera del nodo no alteran el dato interno. Ejemplo:

```java
Persona p = new Persona("Carlos", "111");
Nodo n = new Nodo(p, null);
p.setNombre("Pedro"); // El nodo sigue teniendo "Carlos"
```
    * Desventajas: Fallo crítico con referencias nulas pues si el argumento persona es null, la llamada persona.getNombre() rompe la ejecución inmediatamente; y pérdida de subtipos / polimorfismo pues si se pasa una subclase (por ejemplo, Empleado con campo sueldo), al reconstruir con new Persona(persona.getNombre(), persona.getDni()) se pierden los datos adicionales y el tipo dinámico del objeto.

- Implementación B

    * Ventaja: Rendimiento y flexibilidad, pues la Operación O(1) en tiempo y memoria (no invoca constructores). Además, admite sin fallos valores null: this.persona = null;.

    * Desventaja: Efectos colaterales por Aliasing (Mutabilidad compartida).
Cualquier agente externo que conserve la referencia original puede mutar el estado del objeto dentro del nodo sin supervisión de la lista. Ejemplo:

```java
Persona p = new Persona("Carlos", "111");
Nodo n = new Nodo(p, null);
p.setNombre("Modificado"); // Modifica de forma invisible el objeto dentro del nodo
```
## Pregunta C: ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

- Si la lista usa la Implementación B (Semántica por Referencia):

1. Si un objeto cambia externamente, el contenido de la lista cambia automáticamente.

2. Dos nodos de la lista pueden referenciar al mismo objeto físico en memoria.

3. Si se muta el DNI desde fuera, métodos como contiene(persona) u obtenerPersona(dni) darán resultados desfasados o impredecibles respecto a cuándo se insertó.

- Si la lista usa la Implementación A (Semántica de Instantánea / Snapshot):

1. La lista se vuelve inmune a cambios externos una vez insertado el elemento.

2. No se puede actualizar el contenido de la lista modificando los objetos originales.

3. No admite la inserción de elementos null sin lanzar excepción.

---

# Gestión de referencias

## Código de partida:

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

// Caso analizado
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```

## Pregunta A: ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?

- Respuesta:

En ambas implementaciones (A y B) del Nodo, la persona almacenada en la lista tendrá el nombre "Juan Manuel".

- Justificación técnica:

El método obtenerPersona realiza:

```Java
return actual.getPersona();
```

Dado que actual.getPersona() devuelve la referencia directa al objeto que reside en el nodo:

- En Implementación B: El nodo guarda @PersonaOriginal. obtenerPersona retorna @PersonaOriginal. personaEncontrada.setNombre(...) muta ese objeto.

- En Implementación A: Al insertar se creó @PersonaCopiaInterna. obtenerPersona devuelve la referencia directa a @PersonaCopiaInterna. Por lo tanto, personaEncontrada apunta al objeto que está dentro del nodo, y al mutarlo, el objeto de la lista pasa a llamarse "Juan Manuel". (El único objeto que permanece con "Juan" en A es la variable externa juan).

## Pregunta B: ¿Qué sucedería si modificamos juan.setNombre("Juan Carlos"); después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?

- Respuesta:

Sí, depende enteramente de la implementación del Nodo utilizada.

- Si se usa la Implementación B: juan y el campo persona del nodo comparten la misma dirección física de memoria; y al hacer juan.setNombre("Juan Carlos"), la persona dentro de la lista pasa a llamarse "Juan Carlos".

- Si se usa la Implementación A: El constructor del nodo realizó new Persona(...), desacoplando las referencias en memoria; y modificar juan solo altera el objeto externo (@0x01). La persona dentro de la lista (@0x02) permanece intacta con su nombre anterior.

---

# Referencias y constructores

- Código de partida:

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

## Pregunta A: ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

- Respuesta y justificacion:

Debido a que el comportamiento depende de la implementación del nodo con que trabaje la clase Lista, se analizan las dos posibilidades:

1. La Lista utiliza la Implementación B de Nodo (referencia directa):

- Total: 2 objetos Persona diferentes en el Heap.

    * new Persona("Manuel", "1234"): Instancia el primer objeto (@P1). Las variables manuel y referencia apuntan a este objeto.

    * new Persona(manuel): El constructor de copia crea una segunda instancia independiente (@P2), apuntada por copia.

    * Persona referencia = manuel;: No crea ningún objeto, únicamente duplica la referencia hacia @P1.

    * Las tres llamadas a insertarPersona no invocan new Persona.

2. La Lista utiliza la Implementación A de Nodo (copia defensiva en cada nodo):

- Total: 5 objetos Persona diferentes en el Heap.

    * Los 2 objetos creados en el main (@P1 y @P2).

    * 3 objetos nuevos creados en el Heap, uno por cada llamada a insertarPersona, ya que el constructor del Nodo A ejecuta new Persona(...).

## Pregunta B: Si hacemos manuel.setNombre("Manuel Antonio"), ¿qué elementos de la lista se verán afectados? ¿Por qué?

- Respuesta y justificacion:

El método insertarPersona agrega siempre al inicio (primero = nuevo). Por lo tanto, la estructura secuencial de la lista son las siguientes posiciones:

1. (Cabeza): Nodo con referencia (insertado al final).
2. (Medio): Nodo con copia (insertado en segundo lugar).
3. (Cola): Nodo con manuel (insertado al principio).

Análisis según implementación de Nodo:

- Con Implementación B: Se ven afectados el primer elemento y el tercer elemento de la lista porque ambos nodos guardan la referencia @P1. Al ejecutar manuel.setNombre("Manuel Antonio"), cualquier lectura a través del primer o del tercer nodo mostrará el nuevo nombre. El segundo nodo (apuntando a @P2) no se ve afectado.

- Con Implementación A:Ningún elemento de la lista se verá afectado porque cada nodo guarda una instancia aislada en memoria. El cambio sobre @P1 no tiene propagación hacia las copias internas.

---

# Diseño y optimización

## Requisitos:

1. Detectar si se está intentando insertar una referencia a una Persona que ya existe en la lista.

2. Permitir elegir si queremos insertar una copia de la Persona o mantener la referencia.

3. Ser eficiente en el uso de memoria.

## Implementación propuesta:

```java
public class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }

    public Persona getPersona() { return this.persona; }
    public Nodo getSiguiente() { return this.siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}

public class ListaOptimizada {
    private Nodo primero;

    /**
     * Inserta una persona controlando identidad de referencia y política de copia.
     * @param persona Objeto a insertar.
     * @param clonar true para almacenar una copia; false para mantener la referencia directa.
     * @return true si se insertó; false si la referencia exacta ya existía en la lista.
     */
    public boolean insertar(Persona persona, boolean clonar) {
        if (persona == null) {
            throw new IllegalArgumentException("No se permite insertar referencias nulas.");
        }

        // Requisito 1: Detección estricta de la misma referencia en memoria (==)
        if (contieneReferencia(persona)) {
            return false;
        }

        // Requisito 2: Elección de copia vs referencia directa
        Persona aAlmacenar = clonar ? new Persona(persona) : persona;

        // Requisito 3: Eficiencia de memoria y tiempo (inserción O(1) al inicio)
        this.primero = new Nodo(aAlmacenar, this.primero);
        return true;
    }

    /**
     * Comprueba si la referencia física (dirección de memoria) ya existe en algún nodo.
     */
    public boolean contieneReferencia(Persona persona) {
        Nodo actual = this.primero;
        while (actual != null) {
            if (actual.getPersona() == persona) { // Identidad por puntero
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
}
```

- Justificacion:

1. Detección por identidad física (==): El requerimiento exige detectar la inserción de una referencia que ya existe. En Java, la igualdad de referencias se verifica con el operador ==. No se utiliza .equals() porque dos objetos distintos pueden tener los mismos atributos sin ser la misma referencia.

2. Parámetro explícito boolean clonar: Otorga control al usuario del componente: permite modo de bajo consumo de memoria (referencia directa) o modo de alta seguridad (copia defensiva mediante constructor de copia).

3. Eficiencia en memoria: La clase Nodo es puramente referencial (Implementación B) y si la inserción es rechazada por referencia duplicada o valor nulo, no se instancia ningún objeto en el Heap (cero costo de recolección de basura).

---

# Análisis de código

## Código de partida:

```Java
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

## Pregunta A: ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

- Respuesta y justificacion:

1. Fuga de referencias internas en la consulta (Escape por retorno directo): buscarPersona retorna la referencia directa al objeto dentro de la lista. Quien llame a este método puede mutar el objeto (persona.setNombre(...)), modificando el estado interno del gestor sin su consentimiento ni validación.

2. Inyección de mutabilidad externa en el ingreso: agregarPersona no realiza una copia defensiva. Si la lista subyacente guarda la referencia recibida, el código cliente retiene el puntero original y puede alterar los datos en cualquier instante.

3. Ruptura de la integridad de los datos / Inconsistencia de unicidad: si un cliente modifica el DNI de un objeto tras insertarlo o consultarlo, la comprobación !lista.contiene(persona) se corrompe: podrían insertarse personas duplicadas o fallar las búsquedas por DNI.

4. Fallo por desreferencia de nulos (NullPointerException): el campo lista no está inicializado (es null por omisión); y si el parámetro persona en agregarPersona es null, la llamada lista.contiene(persona) arroja NullPointerException al invocar persona.getDni().

## Pregunta B: ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

- Respuesta y justificacion:

1. Uso de Copias Defensivas (Defensive Copies):

    * Al agregar una persona: almacenar un clon (new Persona(persona)).

    * Al retornar una persona en la búsqueda: devolver un clon (new Persona(encontrada)).

2. Garantía de Inmutabilidad (Solución de diseño estructural):

    * Diseñar la clase Persona como inmutable (campos final, inicializados por constructor, sin setters). Al no existir operaciones de mutación sobre la instancia, compartir referencias directas resulta 100% seguro y ahorra consumo de memoria Heap.

## Pregunta C: Proponga una solución que proteja la integridad de los datos.

- Respuesta y justificacion:

Asumiendo que Persona cuenta con métodos modificadores (setters), se implementa una solución blindada con copias defensivas bidireccionales y control de nulos:

```Java
public class GestorPersonasProtegido {
    private final Lista lista;

    public GestorPersonasProtegido() {
        this.lista = new Lista(); // Inicialización garantizada
    }

    public void agregarPersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("No se puede agregar una referencia nula.");
        }

        // Se comprueba existencia sobre los datos actuales
        if (!lista.contiene(persona)) {
            // COPIA DEFENSIVA DE ENTRADA:
            // Desacopla la lista interna de cualquier mutación externa posterior
            Persona copiaEntrada = new Persona(persona.getNombre(), persona.getDni());
            lista.insertarPersona(copiaEntrada);
        }
    }

    public Persona buscarPersona(String dni) {
        if (dni == null) {
            return null;
        }

        Persona interna = lista.obtenerPersona(dni);
        if (interna == null) {
            return null;
        }

        // COPIA DEFENSIVA DE SALIDA:
        // Se entrega una copia al exterior para evitar mutaciones directas sobre la lista
        return new Persona(interna.getNombre(), interna.getDni());
    }
}
```

