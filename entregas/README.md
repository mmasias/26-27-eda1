# Ejercicio 1: Análisis de implementaciones

## a) ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

- La **Implementación B** es la opción más eficiente en cuanto al uso de memoria.
- **Justificación**:
  - En la **Implementación A**, cada vez que se crea un nodo se genera también una nueva instancia de `Persona` mediante `new Persona(...)`. Esto provoca que se almacenen varios objetos con información duplicada.
  - En la **Implementación B**, el nodo guarda directamente la referencia al objeto recibido (`this.persona = persona`), sin necesidad de crear otra instancia.
  - Por este motivo, la implementación B reduce el consumo de memoria y evita crear objetos que no son necesarios.

## b) ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

- **Implementación A**:
  - *Ventaja*: Permite mantener los datos separados. Si modificamos la persona original después de introducirla en la lista, los datos guardados en el nodo permanecerán iguales.
  - *Inconveniente*: Necesita más memoria, ya que crea una nueva persona en cada inserción. Esto puede ser poco eficiente cuando se trabaja con muchos elementos.
  - *Ejemplo*: Si guardamos una persona llamada Juan y después cambiamos el nombre del objeto original a Juan Carlos, el nodo continuará teniendo almacenado el nombre Juan.

- **Implementación B**:
  - *Ventaja*: Aprovecha mejor la memoria porque utiliza el mismo objeto sin generar copias adicionales.
  - *Inconveniente*: Puede producir modificaciones inesperadas, ya que cualquier cambio realizado sobre el objeto original también se verá reflejado en la lista.
  - *Ejemplo*: Si insertamos a Juan y posteriormente ejecutamos `juan.setNombre("Juan Carlos")`, el nombre que contiene el nodo también pasará a ser Juan Carlos.

## c) ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

- **Implementación A**: La lista mantiene sus propios objetos, separados de los que utiliza el resto del programa. Por tanto, las modificaciones realizadas fuera de ella no alteran la información almacenada.
- **Implementación B**: La lista trabaja con los mismos objetos que se utilizan externamente. Esto significa que cualquier modificación realizada sobre ellos también se reflejará dentro de la estructura.

**Conclusión:** La implementación A ofrece una mayor separación y protección de los datos, mientras que la B permite ahorrar memoria, aunque es necesario controlar mejor las modificaciones de los objetos compartidos.

---

# Ejercicio 2: Gestión de referencias

## a) ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?

- La persona almacenada en la lista pasará a llamarse **"Juan Manuel"**.
- **Justificación**:
  - Cuando se ejecuta `lista.obtenerPersona("1234")`, se obtiene la referencia al objeto que está guardado dentro del nodo.
  - La variable `personaEncontrada` apunta directamente a ese objeto, por lo que no se está trabajando con una copia independiente.
  - Al llamar a `personaEncontrada.setNombre("Juan Manuel")`, se modifica el propio objeto almacenado en la lista.
  - Por tanto, el cambio se verá reflejado al consultar posteriormente la persona, independientemente de si el nodo utiliza A o B.

## b) ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos")` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?

- **Implementación A**:
  - La persona que está dentro de la lista conservará el nombre **"Juan"**.
  - Esto se debe a que, al insertar el objeto, el nodo creó una copia con sus propios datos.
  - Por tanto, modificar la variable `juan` no tendrá ningún efecto sobre esa copia.

- **Implementación B**:
  - El nombre de la persona almacenada cambiará a **"Juan Carlos"**.
  - Esto ocurre porque el nodo guarda la misma referencia que utiliza la variable `juan`.
  - Al modificar el objeto desde cualquiera de las dos referencias, el cambio será visible desde ambas.

**Conclusión:** El resultado está condicionado por el constructor del nodo. Si crea una copia, los objetos permanecen separados; si guarda la referencia, ambos trabajan sobre la misma persona.

---

# Ejercicio 3: Referencias y constructores

## a) ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

- Se crean inicialmente **2 objetos `Persona` distintos**.
- **Justificación**:
  1. El primer objeto aparece al ejecutar `new Persona("Manuel", "1234")`, quedando asociado a la variable `manuel`.
  2. El segundo se crea mediante `new Persona(manuel)`, que utiliza el constructor copia para generar otra persona con los mismos datos.
  3. La variable `referencia` no genera una nueva instancia, sino que recibe la misma referencia que contiene `manuel`.
  4. Por tanto, `manuel` y `referencia` representan al mismo objeto, mientras que `copia` apunta a otro diferente.

**Representación de las referencias:**

```text
manuel -----------\
                   \
                    ---> [Persona 1]
                   /      Manuel, 1234
referencia -------/


copia -----------------> [Persona 2]
                          Manuel, 1234
```

- **Consideración sobre los nodos**:
  - Si utilizamos la implementación B, los nodos almacenan las referencias de los dos objetos originales, por lo que siguen existiendo dos instancias de `Persona`.
  - Si utilizamos la implementación A, cada nodo genera una nueva copia al insertarse. En ese caso, se crearían tres objetos adicionales y habría cinco instancias en total.

## b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

- **Con la implementación B**:
  - Cambiará el elemento que se insertó utilizando la variable `manuel`.
  - También cambiará el elemento insertado mediante `referencia`.
  - El elemento correspondiente a `copia` permanecerá igual.
  - La razón es que `manuel` y `referencia` utilizan el mismo objeto, mientras que `copia` representa una instancia diferente.

- **Con la implementación A**:
  - Ninguno de los elementos almacenados en la lista modificará su nombre.
  - Esto sucede porque cada nodo contiene una instancia independiente creada durante la inserción.
  - Los cambios realizados sobre el objeto original no se transmiten a las copias.

| Elemento | Implementación A | Implementación B |
|---|---|---|
| manuel | Manuel | Manuel Antonio |
| copia | Manuel | Manuel |
| referencia | Manuel | Manuel Antonio |

---

# Ejercicio 4: Diseño y optimización

## Explicación y justificación de las decisiones tomadas

- **Control de elementos duplicados**:
  - Se incorpora un método llamado `contieneDni()` que recorre los nodos para comprobar si ya existe una persona con el mismo DNI.
  - De esta manera, se evita introducir registros que tengan un identificador que ya esté presente en la lista.

- **Posibilidad de elegir entre copia y referencia**:
  - Se incorpora un parámetro booleano llamado `clonar` en el método de inserción.
  - Cuando su valor es `true`, se crea una nueva instancia mediante el constructor copia (`new Persona(persona)`).
  - Cuando su valor es `false`, se conserva el objeto original mediante su referencia, evitando crear una instancia adicional.

- **Optimización de memoria y tiempo de ejecución**:
  - Se utiliza una lista enlazada simple en la que los nuevos nodos se añaden al principio.
  - Esta operación tiene un coste temporal de O(1), porque no es necesario recorrer los elementos para realizar la inserción.
  - La comprobación de DNI requiere recorrer la lista, por lo que su coste es O(n).

## Propuesta de implementación de la Lista

Para cumplir con las condiciones del ejercicio, se propone el siguiente código:

```java
public class ListaPersonasOptimizada {

    private Nodo primero;

    public void insertarPersona(Persona persona, boolean clonar) {

        if (persona == null) {
            throw new IllegalArgumentException(
                "La persona no puede ser nula"
            );
        }

        if (contieneDni(persona.getDni())) {
            throw new IllegalArgumentException(
                "Ya existe una persona con el DNI: " + persona.getDni()
            );
        }

        Persona personaAAlmacenar;

        if (clonar) {
            personaAAlmacenar = new Persona(persona);
        } else {
            personaAAlmacenar = persona;
        }

        this.primero = new Nodo(personaAAlmacenar, this.primero);
    }

    public boolean contieneDni(String dni) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getPersona().getDni().equals(dni)) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }
}
```

**Ejemplo de utilización:**

```java
Persona juan = new Persona("Juan", "1234");

ListaPersonasOptimizada lista = new ListaPersonasOptimizada();

// Inserta una copia independiente de Juan.
lista.insertarPersona(juan, true);

// Inserta una referencia al objeto original.
lista.insertarPersona(
    new Persona("Ana", "5678"),
    false
);
```

**Justificación del funcionamiento:**

- Primero se comprueba que la persona recibida no sea nula.
- Después se verifica si ya existe alguien con ese DNI.
- Si no hay duplicados, se decide cómo almacenar el objeto según el valor de `clonar`.
- Finalmente, se crea un nuevo nodo y se coloca al principio de la lista.

**Nota:** Esta implementación controla personas con el mismo DNI, aunque sean objetos diferentes. No comprueba exclusivamente si se está repitiendo exactamente la misma referencia, ya que ambas condiciones son distintas.

---

# Ejercicio 5: Análisis de código

## a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

- **Problema 1: Posibles modificaciones desde el exterior**
  - Si la lista conserva las referencias originales, cualquier clase que tenga acceso a esos objetos puede modificar sus atributos.
  - Por ejemplo, después de añadir a Juan, si ejecutamos `juan.setNombre("Pedro")`, la información almacenada en la lista también cambiará.

- **Problema 2: Acceso directo a los objetos internos**
  - El método `buscarPersona()` devuelve el resultado de `lista.obtenerPersona(dni)` sin crear una copia.
  - Esto permite que quien recibe la persona pueda modificar directamente el objeto que está guardado en la lista.
  - Como consecuencia, los datos internos pueden cambiar sin pasar por el gestor.

- **Problema 3: Comprobación de duplicados poco definida**
  - El funcionamiento depende de cómo se haya desarrollado el método `contiene()`.
  - Si solamente compara referencias, podría permitir almacenar dos personas distintas que tengan el mismo DNI.
  - Sería necesario comprobar qué criterio de duplicidad debe aplicar el programa.

## b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

- Aplicaría el mecanismo de **copia defensiva** para evitar que se pueda acceder directamente a los objetos internos.
- Al guardar una persona, se crearía una copia mediante el constructor copia.
- Cuando se realiza una búsqueda, también se devolvería una nueva instancia con los mismos datos.
- De esta forma, cualquier modificación realizada sobre los objetos externos no afectaría directamente a la información almacenada en la lista.

## c) Proponga una solución que proteja la integridad de los datos.

- Una posible solución consiste en modificar el método `buscarPersona()` para que cree una nueva instancia antes de devolver el resultado.
- También es recomendable crear una copia al insertar para evitar que el objeto original pueda modificar los datos internos de la lista.

**Código propuesto:**

```java
public class GestorPersonas {

    private Lista lista;

    public GestorPersonas() {
        lista = new Lista();
    }

    public void agregarPersona(Persona persona) {

        if (persona == null) {
            return;
        }

        if (!lista.contiene(persona)) {
            lista.insertarPersona(new Persona(persona));
        }
    }

    public Persona buscarPersona(String dni) {

        Persona personaEncontrada = lista.obtenerPersona(dni);

        if (personaEncontrada != null) {
            return new Persona(personaEncontrada);
        }

        return null;
    }
}
```

**Justificación:**

- Se comprueba que la persona recibida no sea nula.
- Al insertar, se crea una copia de la persona para evitar que las modificaciones externas afecten a la lista.
- Al buscar, se devuelve otra copia, impidiendo que el usuario modifique directamente el objeto interno.
- Se inicializa la lista en el constructor para evitar referencias nulas.
- De esta forma se consigue un mayor aislamiento y protección de los datos almacenados.