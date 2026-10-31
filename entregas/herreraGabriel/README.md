# Resolución de la Simulación de Examen

---

## Análisis de implementaciones

### A. ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

La **Implementación B** utiliza mejor los recursos de memoria.

* **Justificación de por qué la Implementación B es mejor en memoria:** En Java, las variables de objetos almacenan **referencias** (direcciones de memoria). En la Implementación B (`this.persona = persona;`), el atributo interno del nodo simplemente copia la dirección de memoria del objeto `Persona` que ya fue creado previamente fuera del nodo. Solo existe **1 objeto `Persona`** en el *Heap* por cada persona creada, ocupando únicamente el espacio de una referencia adicional dentro del `Nodo`.
* **Justificación de por qué la Implementación A es peor en memoria:** La Implementación A ejecuta `new Persona(persona.getNombre(), persona.getDni())` dentro del constructor. El operador `new` reserva memoria adicional en el *Heap* para instanciar un **segundo objeto `Persona` duplicado** cada vez que se inserta un nodo. Si el objeto original que se pasó por parámetro deja de usarse fuera, se habrá duplicado innecesariamente el número de instancias de `Persona` en memoria (obligando al *Garbage Collector* a trabajar más), y si se mantiene fuera, habrá el doble de objetos `Persona` ocupando espacio en el *Heap*.

**Diagrama de memoria tras ejecutar `Persona p = new Persona("Ana", "111A"); Nodo n = new Nodo(p, null);`:**

```text
IMPLEMENTACIÓN A (2 objetos Persona en el Heap):
 [Stack]                [Heap]
   p -----------------> #P1: Persona { nombre: "Ana", dni: "111A" }
   
   n -----------------> #N1: Nodo
                         ├── siguiente: null
                         └── persona ---> #P2: Persona { nombre: "Ana", dni: "111A" } (Copia nueva)

IMPLEMENTACIÓN B (1 único objeto Persona compartido en el Heap):
 [Stack]                [Heap]
   p ────────────────┐
                     ├──> #P1: Persona { nombre: "Ana", dni: "111A" }
   n ---> #N1: Nodo  │
           ├── persona ┘
           └── siguiente: null
```

---

### B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

**Implementación A (`this.persona = new Persona(...)`)**

* **Ventaja (Aislamiento y encapsulamiento en la entrada):** Protege al nodo frente a modificaciones externas mediante **copia defensiva**. Si el código exterior modifica el objeto original después de crear el nodo, el objeto guardado dentro del nodo no se altera.
* **Problema 1 (Mayor consumo de memoria y tiempo):** Crea un objeto nuevo en cada inserción.
* **Problema 2 (Fallo con referencias nulas - `NullPointerException`):** Si se intenta crear un nodo vacío o un *nodo centinela/dummy* pasando `new Nodo(null, null)`, la llamada `persona.getNombre()` lanzará un `NullPointerException` inmediato porque `persona` es `null`.
* **Problema 3 (Pérdida de datos si `Persona` crece):** Si en el futuro a la clase `Persona` se le añade un atributo (por ejemplo, `edad` o `telefono`) y un constructor más completo, la Implementación A solo copia `nombre` y `dni`, perdiendo el resto del estado del objeto original.

*Ejemplo concreto de fallo en la Implementación A:*

```java
Persona p = null;
Nodo nodoCentinela = new Nodo(p, null);
```

Al ejecutar esta línea con la Implementación A, el programa aborta con `NullPointerException` al evaluar `persona.getNombre()`.

**Implementación B (`this.persona = persona`)**

* **Ventaja 1 (Eficiencia espacial y temporal):** No instancia objetos extra ni invoca getters en la construcción; es inmediata y ahorra memoria.
* **Ventaja 2 (Tolerancia a `null` en construcción):** Permite hacer `new Nodo(null, null)` sin lanzar excepciones en el constructor.
* **Problema (Efectos laterales por *aliasing* / referencias compartidas):** Como el exterior y el nodo apuntan al mismo objeto `#P1`, cualquier cambio hecho desde fuera sobre la variable original altera silenciosamente los datos internos del nodo sin que el nodo ni la lista tengan control sobre ello.

*Ejemplo concreto de problema en la Implementación B:*

```java
Persona p = new Persona("Ana", "111A");
Nodo n = new Nodo(p, null);
p.setDni("999Z");
```

El nodo `n` ve alterado el DNI de su persona a `"999Z"` sin haber invocado ningún método sobre `n`.

---

### C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

1. **Con la Implementación A (Lista aislada del exterior en la inserción):**
   * La lista actúa como una estructura con **valores independientes de las variables externas** que se usaron para insertar.
   * Si insertamos la misma variable `Persona p` dos veces modificando su nombre entre inserciones (`p.setNombre("Otro")`), la lista tendrá dos nodos con dos personas distintas.
   * **Peligro oculto:** Si la lista devuelve la referencia interna en sus métodos de consulta (como hace `obtenerPersona` en la sección *Gestión de referencias* con `return actual.getPersona();`), la protección de la Implementación A se pierde al consultar, porque el exterior conseguirá igualmente la referencia al objeto `#P2` del nodo.

2. **Con la Implementación B (Lista acoplada a las referencias externas):**
   * La lista almacena **alias** de los objetos del programa principal.
   * Si se modifica un objeto fuera de la lista, su estado cambia dentro de la lista.
   * Si se inserta dos veces la misma referencia `p` en la lista, **dos nodos distintos de la lista apuntarán exactamente al mismo objeto `Persona`**. Modificar la persona de uno de esos nodos modificará automáticamente la persona del otro nodo.
   * Si desde fuera se modifica el `dni` de una persona ya insertada, los métodos `contiene(Persona)` y `obtenerPersona(String dni)` de la lista dejarán de encontrarla por su DNI original o pueden generarse dos nodos con el mismo DNI dentro de la lista.

---

## Gestión de referencias

*(Nota sobre lo que el código no muestra: La clase `Lista` invoca `actual.getPersona()` y `actual.getSiguiente()`, métodos que no aparecen escritos en los fragmentos de `Nodo` de la sección anterior. Se razona asumiendo la implementación estándar `public Persona getPersona() { return this.persona; }`, e indicando también qué ocurriría si `getPersona()` hiciese copia defensiva).*

### a) ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?

Código ejecutado:

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");
lista.insertarPersona(juan);
Persona personaEncontrada = lista.obtenerPersona("1234");
personaEncontrada.setNombre("Juan Manuel");
```

* **Respuesta (asumiendo getter estándar `return this.persona;` en `Nodo`):**
  La persona almacenada en la lista tendrá el nombre **`"Juan Manuel"`**, **tanto si se usa la Implementación A como si se usa la Implementación B de `Nodo`**.
* **Justificación:**
  1. Al hacer `lista.insertarPersona(juan)`, se crea el nodo `primero` cuyo atributo interno `persona` apunta al objeto almacenado en la lista (llamémoslo `#P2` en la Implementación A, o `#P1` en la Implementación B).
  2. Al llamar a `lista.obtenerPersona("1234")`, el bucle encuentra el nodo cuyo DNI coincide con `"1234"` y ejecuta `return actual.getPersona();`.
  3. Ese `return` devuelve **la referencia directa** al objeto `Persona` que está guardado dentro del nodo. Esa referencia se guarda en la variable `personaEncontrada`.
  4. Por tanto, `personaEncontrada` y el atributo `persona` del nodo de la lista apuntan **exactamente al mismo objeto en memoria**. Al ejecutar `personaEncontrada.setNombre("Juan Manuel")`, se muta directamente el objeto interno del nodo.
* *(Posibilidad alternativa si `Nodo.getPersona()` no fuera un getter estándar):* Si `actual.getPersona()` estuviera programado devolviendo una copia nueva (`return new Persona(this.persona);`), entonces `personaEncontrada` apuntaría a un objeto distinto y la persona dentro de la lista conservaría el nombre **`"Juan"`**.

**Diagrama de memoria del apartado a) (con getter estándar):**

```text
CON IMPLEMENTACIÓN A:
 [Stack]                   [Heap]
  juan -------------------> #P1: Persona { nombre: "Juan", dni: "1234" } (Intacto)

  lista --> #L1: Lista
             └── primero -> #N1: Nodo
                             ├── siguiente: null
                             └── persona ───┐
                                            ├──> #P2: Persona { nombre: "Juan Manuel", dni: "1234" }
  personaEncontrada ────────────────────────┘

CON IMPLEMENTACIÓN B:
 [Stack]                   [Heap]
  juan ─────────────────────────────────────┐
  lista --> #L1: Lista                      │
             └── primero -> #N1: Nodo       ├──> #P1: Persona { nombre: "Juan Manuel", dni: "1234" }
                             ├── persona ───┤
                             └── sig: null  │
  personaEncontrada ────────────────────────┘
```

---

### b) ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos");` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?

**Sí, depende completamente de la implementación del `Nodo` (A o B)**:

1. **Si usamos la Implementación A de `Nodo`:**
   * **Qué sucede:** El nombre de la persona dentro de la lista **NO cambia** (sigue siendo `"Juan"`, o `"Juan Manuel"` si se ejecutó tras el bloque del apartado a). Solo cambia el objeto externo apuntado por `juan`.
   * **Por qué:** Porque al insertar a `juan`, el constructor de la Implementación A hizo `this.persona = new Persona(...)`, creando un objeto `#P2` distinto en memoria para el nodo. La variable `juan` sigue apuntando al objeto original `#P1`. Modificar `juan` modifica `#P1`, pero el nodo de la lista apunta a `#P2`.

2. **Si usamos la Implementación B de `Nodo`:**
   * **Qué sucede:** El nombre de la persona almacenada dentro de la lista **SÍ cambia a `"Juan Carlos"`**.
   * **Por qué:** Porque el constructor de la Implementación B hizo `this.persona = persona;`, guardando en el nodo la misma dirección de memoria que tiene la variable `juan` (`#P1`). Al hacer `juan.setNombre("Juan Carlos")`, se modifica el único objeto `#P1` compartido por `juan` y por el nodo de la lista.

**Diagrama de memoria del apartado b) (`juan.setNombre("Juan Carlos")` tras insertar):**

```text
IMPLEMENTACIÓN A (Independientes):
 [Stack]              [Heap]
  juan --------------> #P1: Persona { nombre: "Juan Carlos", dni: "1234" }
  lista -> primero --> #N1: Nodo ---> #P2: Persona { nombre: "Juan", dni: "1234" } (No afectado)

IMPLEMENTACIÓN B (Referencias compartidas / Aliasing):
 [Stack]              [Heap]
  juan ─────────────────────────────┐
                                    ├──> #P1: Persona { nombre: "Juan Carlos", dni: "1234" } (Afectado)
  lista -> primero --> #N1: Nodo ───┘
```

---

## Referencias y constructores

### a) ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

En Java, un nuevo objeto `Persona` solo se instancia en el *Heap* cuando se ejecuta la palabra reservada **`new Persona(...)`**. Analizando el código paso a paso:

1. **En el método `main` (antes de insertar en la lista):**
   * `Persona manuel = new Persona("Manuel", "1234");` $\rightarrow$ Usa `new`. Crea el **Objeto `#P1`** en el *Heap*.
   * `Persona copia = new Persona(manuel);` $\rightarrow$ Usa `new` (invoca al constructor de copia). Crea el **Objeto `#P2`** en el *Heap*, distinto de `#P1` aunque con los mismos datos.
   * `Persona referencia = manuel;` $\rightarrow$ **No usa `new`**. Es una asignación de referencias: la variable `referencia` copia la dirección de memoria de `manuel` y apunta al mismo **Objeto `#P1`**.

2. **Al ejecutar las 3 llamadas a `lista.insertarPersona(...)`:**
   Cada llamada ejecuta `new Nodo(persona, primero)`. Como el código de esta sección no especifica si `Nodo` usa la Implementación A o la Implementación B de *Análisis de implementaciones*, se razonan ambas posibilidades:

   * **Caso 1: Si `Nodo` usa la Implementación B (`this.persona = persona`):**
     El constructor de `Nodo` no ejecuta `new Persona(...)`, sino que reutiliza las referencias recibidas.
     Por tanto, hay exactamente **2 objetos `Persona` diferentes en memoria** (`#P1` y `#P2`) y 3 objetos `Nodo`.

   * **Caso 2: Si `Nodo` usa la Implementación A (`this.persona = new Persona(...)`):**
     Cada una de las 3 llamadas a `insertarPersona` crea un `Nodo` que ejecuta internamente un `new Persona(...)` adicional, instanciando 3 objetos más (`#P3`, `#P4` y `#P5`).
     Por tanto, hay **5 objetos `Persona` diferentes en memoria** (los 2 del `main`: `#P1`, `#P2` + los 3 creados dentro de los nodos: `#P3`, `#P4`, `#P5`) y 3 objetos `Nodo`.

**Diagrama de memoria al finalizar `main`:**

```text
CASO 1: Con Nodo Implementación B (2 objetos Persona en total)
 [Stack (main)]          [Heap]
  manuel ───────────────┐
  referencia ───────────┼──> #P1: Persona { nombre: "Manuel", dni: "1234" } <──┐
                        │                                                      │
  copia ────────────────┼──> #P2: Persona { nombre: "Manuel", dni: "1234" } <┐ │
                        │                                                    │ │
  lista --> #L1: Lista  │    (3º insertado: referencia)                      │ │
             └── primero ──> #N3: Nodo { sig: #N2, persona } ────────────────┼─┘
                                               │                             │
                             (2º insertado: copia)                           │
                             #N2: Nodo { sig: #N1, persona } ────────────────┘
                                               │
                             (1º insertado: manuel)
                             #N1: Nodo { sig: null, persona } ───────────────> (#P1)

CASO 2: Con Nodo Implementación A (5 objetos Persona en total)
 [Stack (main)]          [Heap]
  manuel, referencia ──────> #P1: Persona { "Manuel", "1234" }
  copia ───────────────────> #P2: Persona { "Manuel", "1234" }
  lista -> primero ────────> #N3 (sig: #N2) ──> #P5: Persona { "Manuel", "1234" }
                             #N2 (sig: #N1) ──> #P4: Persona { "Manuel", "1234" }
                             #N1 (sig: null) ─> #P3: Persona { "Manuel", "1234" }
```

---

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

*(Nota: Como `insertarPersona` inserta siempre por la cabeza haciendo `Nodo nuevo = new Nodo(persona, primero); primero = nuevo;`, el orden de los nodos en la lista desde `primero` es: **1º nodo (`#N3`)** el insertado con `referencia`, **2º nodo (`#N2`)** el insertado con `copia`, y **3º nodo (`#N1`)** el insertado con `manuel`).*

* **Si `Nodo` usa la Implementación B:**
  * **Elementos afectados:** Se verán afectados **2 elementos de la lista**: el **primer nodo de la lista (`#N3`, insertado con `referencia`)** y el **tercer/último nodo de la lista (`#N1`, insertado con `manuel`)**. El segundo nodo (`#N2`, insertado con `copia`) **no** se verá afectado.
  * **Por qué:** Porque tanto `manuel` como `referencia` guardan la dirección del mismo objeto **`#P1`**, y ambos nodos (`#N1` y `#N3`) almacenaron esa misma referencia `#P1`. Al cambiar el nombre de `#P1` a `"Manuel Antonio"`, ambos nodos leen el nuevo estado de `#P1`. En cambio, `copia` fue creado con `new Persona(manuel)`, por lo que el nodo `#N2` apunta al objeto independiente **`#P2`**, que mantiene `"Manuel"`.

* **Si `Nodo` usa la Implementación A:**
  * **Elementos afectados:** **Ningún elemento de la lista se verá afectado**.
  * **Por qué:** Porque al insertar cada elemento, el constructor de `Nodo` creó copias nuevas e independientes (`#P3`, `#P4` y `#P5`). La instrucción `manuel.setNombre("Manuel Antonio")` modifica únicamente `#P1` (afectando a `manuel` y a `referencia` en el `main`), pero los nodos `#N1`, `#N2` y `#N3` apuntan a `#P3`, `#P4` y `#P5` respectivamente.

---

## Diseño y optimización

### Propuesta de implementación (`Nodo` y `Lista`)

Temas de los repositorios usados: **PRG2** (constructores de copia, sobrecarga de métodos, vistas y encapsulamiento en `02-vistas`) y **EDA1** (`001-intro/pasoDeParametros.md` y `002-00-listas`).

```java
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public Nodo getSiguiente() {
        return this.siguiente;
    }
}

class Lista {
    private Nodo primero;

    public boolean contieneReferencia(Persona persona) {
        if (persona == null) {
            return false;
        }
        Nodo actual = this.primero;
        while (actual != null) {
            if (actual.getPersona() == persona) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean contieneDni(Persona persona) {
        if (persona == null || persona.getDni() == null) {
            return false;
        }
        Nodo actual = this.primero;
        while (actual != null) {
            if (actual.getPersona().getDni().equals(persona.getDni())) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean insertarPersona(Persona persona, boolean hacerCopia) {
        if (persona == null || this.contieneReferencia(persona)) {
            return false;
        }
        Persona personaAGuardar = persona;
        if (hacerCopia) {
            personaAGuardar = new Persona(persona);
        }
        this.primero = new Nodo(personaAGuardar, this.primero);
        return true;
    }

    public boolean insertarPersona(Persona persona) {
        return this.insertarPersona(persona, false);
    }
}
```

### Explicación del diseño y justificación de las decisiones tomadas

1. **Detección de referencia existente (`contieneReferencia` con `==` frente a `contieneDni` con `.equals()`):**
   * El enunciado pide específicamente *"detectar si se está intentando insertar una referencia a una Persona que ya existe en la lista"*.
   * Para comparar si dos referencias apuntan exactamente a la misma dirección de memoria en el *Heap*, utilizamos el operador de identidad **`==`** (`actual.getPersona() == persona`) en `contieneReferencia`.
   * Esto evita que dos nodos distintos apunten al mismo objeto físico en memoria (evitando el problema visto en *Referencias y constructores* con `manuel` y `referencia`). Adicionalmente, se incluye `contieneDni` con `.equals()` por si se requiere comprobar igualdad lógica por clave identificativa.

2. **Elección entre insertar copia o mantener referencia (`boolean hacerCopia` + `Nodo` tipo B):**
   * **Decisión clave en `Nodo`:** Se ha elegido la **Implementación B** para `Nodo` (`this.persona = persona;`).
   * **Justificación:** Si hubiésemos puesto `new Persona(...)` dentro del constructor de `Nodo` (Implementación A), sería **imposible** mantener la referencia original cuando el usuario lo pida. Al dejar que `Nodo` solo guarde la referencia que recibe, trasladamos la decisión al método `insertarPersona(Persona persona, boolean hacerCopia)` de `Lista`:
     * Si `hacerCopia` es `false`: pasa la referencia `persona` directamente al `Nodo` (0 objetos `Persona` adicionales creados).
     * Si `hacerCopia` es `true`: invoca al constructor de copia `new Persona(persona)` visto en *Referencias y constructores* y le pasa la nueva instancia al `Nodo` (exactamente 1 copia creada).

3. **Eficiencia en el uso de memoria:**
   * **Sin duplicación innecesaria:** Solo se reserva memoria para un nuevo objeto `Persona` cuando `hacerCopia` es `true`.
   * **Rechazo previo de duplicados:** La comprobación `this.contieneReferencia(persona)` se ejecuta **antes** de hacer `new Persona(persona)` y **antes** de hacer `new Nodo(...)`, ahorrando la creación tanto del nodo como de la copia si la referencia ya estaba en la lista.
   * **Reutilización de `String` inmutables:** El constructor de copia `Persona(Persona persona)` copia las referencias de `nombre` y `dni` (`String`). Como `String` es inmutable en Java (visto en `eda1/temario/001-intro/stringStringBuilder.md`), compartir la referencia a los `String` entre el original y la copia es completamente seguro y no duplica arrays de caracteres en memoria.

**Diagrama de memoria del diseño propuesto:**

```text
Llamada 1: lista.insertarPersona(p1, false); (hacerCopia = false -> mantiene referencia)
Llamada 2: lista.insertarPersona(p2, true);  (hacerCopia = true  -> crea copia independiente)

 [Stack]                [Heap]
  p1 ─────────────────────────────────────────────┐
                                                  ▼
  lista -> primero -> #N2: Nodo               #P1: Persona ("Ana", "111A")
                       ├── sig: #N1: Nodo ────────┘
                       └── persona
                             │
                             ▼
                          #P2_Copia: Persona ("Luis", "222B")   <-- Creado porque hacerCopia = true
  
  p2 ───────────────────> #P2_Orig:  Persona ("Luis", "222B")   <-- Independiente de #P2_Copia
```

---

## Análisis de código

### a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

Analizando lo que el código **hace realmente**, existen **4 problemas críticos**:

1. **Referencia `lista` nula por falta de constructor (`NullPointerException` garantizado):**
   * El atributo `private Lista lista;` está declarado pero no se inicializa ni en su declaración ni en un constructor. Su valor por defecto en memoria es `null`.
   * En cuanto se llame a `agregarPersona` o `buscarPersona`, al evaluar `lista.contiene(...)` o `lista.obtenerPersona(...)` sobre `lista == null`, el programa fallará con **`NullPointerException`**.
   * *Caso concreto de prueba:*

```java
GestorPersonas gestor = new GestorPersonas();
gestor.agregarPersona(new Persona("Eva", "1111"));
```

Al invocar `agregarPersona`, se produce inmediatamente un `NullPointerException` porque `this.lista` es `null`.

2. **Fuga de referencia interna en `buscarPersona(String dni)` (Vulneración del encapsulamiento en la salida):**
   * `buscarPersona` hace `return lista.obtenerPersona(dni);`, lo cual devuelve la **referencia directa** al objeto `Persona` almacenado dentro del `Nodo` de la lista.
   * Cualquier clase externa que llame a `buscarPersona` obtiene acceso directo al objeto interno y puede mutarlo sin control del gestor (por ejemplo, cambiar su nombre o cambiar su DNI con `setDni`).
   * *Caso concreto de prueba (Corrupción de la integridad y duplicación de DNI):*

```java
gestor.agregarPersona(new Persona("Ana", "1111"));
gestor.agregarPersona(new Persona("Luis", "2222"));
Persona p = gestor.buscarPersona("2222");
p.setDni("1111");
```

Tras ejecutar `p.setDni("1111")`, la lista interna pasa a tener dos personas con el mismo DNI `"1111"`, rompiendo la restricción de unicidad que intentaba proteger `agregarPersona`.

3. **Aliasing externo en `agregarPersona(Persona persona)` (Vulneración en la entrada):**
   * Si `Nodo` utiliza la Implementación B, `lista.insertarPersona(persona)` guarda la misma referencia externa que pasó el cliente.
   * Tras superar el control `if (!lista.contiene(persona))`, el cliente conserva la variable externa y puede modificar el objeto (incluso su DNI) desde fuera del `GestorPersonas`.
   * *Caso concreto de prueba:*

```java
Persona ext = new Persona("Ana", "1111");
gestor.agregarPersona(ext);
ext.setNombre("Nombre Modificado");
```

Esto modifica el interior de `GestorPersonas` sin haber invocado ningún método del gestor.

4. **Desprotección ante parámetros `null`:**
   * Si se invoca `gestor.agregarPersona(null)`, el método `lista.contiene(null)` intentará ejecutar `persona.getDni()` sobre `null`, lanzando un `NullPointerException` (o si la lista está vacía, insertará un `null` en el nodo que hará fallar las siguientes búsquedas).

**Diagrama de memoria de los problemas 2 y 3 en `GestorPersonas`:**

```text
 [ Variables Externas ]             [ Interior de GestorPersonas ]
   ext (en main) ─────────────────────────┐
                                          ▼
                                    #N1: Nodo
                                     └── persona ──> #P1: Persona { "Ana", "1111" }
                                                          ▲
   p (devuelta por buscarPersona) ────────────────────────┘
   (Tanto 'ext' como 'p' apuntan al objeto privado #P1 dentro de la lista del Gestor)
```

---

### b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

Aplicando el principio de **copia defensiva** (visto en `eda1/src/zzzotros/cccf/vPRG2_02_safe` y en el tema de vistas/encapsulamiento de **PRG2**):

1. **Inicializar la lista en el constructor de `GestorPersonas`:** Crear la instancia `this.lista = new Lista();` para que la referencia `lista` nunca sea `null`.
2. **Copia defensiva en la entrada (`agregarPersona`):** Al insertar una persona válida, pasar una **copia nueva** (`new Persona(persona)`) a la lista en lugar de la referencia original externa. Así cortamos el vínculo con la variable del cliente.
3. **Copia defensiva en la salida (`buscarPersona`):** Cuando `lista.obtenerPersona(dni)` devuelva la referencia al objeto `Persona` del nodo, **no devolver esa referencia al exterior**, sino devolver un clon (`return new Persona(encontrada);`). Si no existe (`encontrada == null`), devolver `null`.
4. **Inmutabilidad del identificador en `Persona` y validación de nulos:** Asegurar que `Persona` no tenga `setDni` (o que sus atributos solo se modifiquen de forma controlada) y comprobar `persona != null` y `dni != null` antes de operar.

**Diagrama de memoria con las mejoras aplicadas (Aislamiento total del Gestor):**

```text
 [ Exterior (main) ]                [ Interior de GestorPersonas (Encapsulado) ]
   ext ─────────> #P1_Ext             #N1: Nodo
                 ("Ana","1111")        └── persona ──> #P2_Interno ("Ana","1111")
                                                        (Inaccesible desde fuera)
   pBuscada ────> #P3_CopiaSalida
                 ("Ana","1111")
```

---

### c) Proponga una solución que proteja la integridad de los datos.

Temas de los repositorios usados: **PRG2** (`02-vistas`, constructores de copia, ocultación de información) y **EDA1** (`001-intro/pasoDeParametros.md`, `002-00-listas` y `cccf/vPRG2_02_safe`).

```java
public class GestorPersonas {
    private Lista lista;

    public GestorPersonas() {
        this.lista = new Lista();
    }

    public boolean agregarPersona(Persona persona) {
        if (persona == null || persona.getDni() == null) {
            return false;
        }
        if (!this.lista.contiene(persona)) {
            Persona copiaEntrada = new Persona(persona);
            this.lista.insertarPersona(copiaEntrada);
            return true;
        }
        return false;
    }

    public Persona buscarPersona(String dni) {
        if (dni == null) {
            return null;
        }
        Persona encontrada = this.lista.obtenerPersona(dni);
        if (encontrada == null) {
            return null;
        }
        return new Persona(encontrada);
    }
}
```

#### Cómo funciona y caso concreto para probar la integridad

* **Constructor:** Inicializa `this.lista = new Lista()`, evitando el `NullPointerException`.
* **`agregarPersona`:** Verifica que ni `persona` ni su `dni` sean `null`. Si el DNI no está registrado en la lista, crea `copiaEntrada = new Persona(persona)` usando el constructor de copia de `Persona` e inserta esa copia.
* **`buscarPersona`:** Busca el nodo en la lista. Si lo encuentra, en lugar de entregar la referencia `encontrada` que apunta al interior del nodo, construye y devuelve `new Persona(encontrada)`.

**Programa de prueba (`main`) que demuestra que los fallos quedan bloqueados:**

```java
public class PruebaIntegridad {
    public static void main(String[] args) {
        GestorPersonas gestor = new GestorPersonas();

        Persona original = new Persona("Juan", "1234");
        gestor.agregarPersona(original);

        original.setNombre("Juan Modificado Fuera");
        System.out.println(gestor.buscarPersona("1234").getNombre());

        Persona consultada = gestor.buscarPersona("1234");
        consultada.setNombre("Juan Modificado En Busqueda");
        System.out.println(gestor.buscarPersona("1234").getNombre());
    }
}
```

**Salida por consola:**

```text
Juan
Juan
```

Tanto al mutar la variable original tras la inserción como al mutar el objeto devuelto por `buscarPersona`, la persona almacenada dentro de `GestorPersonas` mantiene intacto su nombre `"Juan"`, garantizando la integridad de los datos.