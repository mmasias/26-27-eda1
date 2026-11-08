### Resolución de la Simulación de Examen

#### Análisis de implementaciones

##### A. ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.
* La implementación B aprovecha mejor los recursos de memoria que la implementación A. La B simplemente guarda la referencia al objeto de tipo `Persona` que se está pasando por parámetro, apuntando al objeto ya existente en memoria. La implementación A, por el contrario, extrae los datos del objeto entregado y fabrica un objeto (instancia) totalmente nuevo mediante `new Persona(...)`. Es decir, con la A ahora hay dos objetos iguales en contenido, pero con distinta identidad en memoria, lo que duplica el consumo de RAM.

##### B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.
* La implementación A tiene como desventaja el uso duplicado de memoria para guardar nuevos objetos. Sin embargo, su gran ventaja es la seguridad (programación defensiva), al crear una "lista espejo" con copias aisladas que protege los datos internos de modificaciones externas no deseadas. Por ejemplo, si afuera se hace `p.setNombre("Otro")`, la lista conserva el nombre original.
* La implementación B tiene la ventaja de no gastar memoria extra al guardar solo la referencia (apunta a la dirección de memoria de la `Persona` original). Su mayor problema es la vulnerabilidad a efectos secundarios (Fuga de Encapsulamiento / Aliasing): si se modifica el objeto original fuera de la lista, el cambio afectará silenciosamente al nodo interno de la lista.

##### C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?
* El comportamiento de la lista cambia radicalmente dependiendo de la implementación elegida, ya que afecta cómo se manejan las referencias.

**Diagrama de Memoria - Implementación A (Lista Desconectada / Inmutable externamente):**
Variable externa juan ---> [Objeto Persona en memoria: "Juan"] 
Nodo de la Lista      ---> [NUEVO Objeto Persona en memoria: "Juan"]

**Diagrama de Memoria - Implementación B (Lista Vinculada / Mutable externamente):**
Variable externa juan ---> [Objeto Persona en memoria: "Juan"] <--- Nodo de la Lista

Como se evidencia en los diagramas, si se usa la implementación A los datos internos quedan aislados y protegidos de cambios externos. Sin embargo, clonar cada objeto no es sostenible en escenarios de alta densidad de datos por el consumo de memoria. La implementación B soluciona el consumo de memoria al aprovechar la misma referencia, permitiendo actualizaciones en tiempo real a costa de perder aislamiento.

#### Gestión de referencias

##### A. ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?
* El nombre de la persona almacenada en la lista será **"Juan Manuel"**.
* **¿Por qué?** Porque el método `obtenerPersona` devuelve la referencia del objeto exacto que vive dentro del nodo actual de la lista. Al aplicar el método `.setNombre("Juan Manuel")` sobre la variable `personaEncontrada`, estamos alterando directamente el objeto en la memoria al que apunta ese nodo (Fuga de Encapsulamiento). Esto ocurre sin importar si se usó la implementación A o B en el nodo, ya que la modificación se hace desde la referencia retornada por la propia lista.

##### B. ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos");` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?
* Sí, en este caso el resultado depende totalmente de la implementación del nodo, ya que la modificación se hace sobre la variable externa original `juan`:
* **Si usamos la implementación A:** La lista no se verá afectada y conservará el nombre anterior ("Juan"), ya que el nodo almacena una copia en una dirección de memoria distinta (lista espejo).
  * *Diagrama de Memoria:* 
    Variable juan ---> [Objeto Persona: "Juan Carlos"] *(modificado)* 
    Nodo en la Lista ---> [Objeto Persona Copia: "Juan"] *(intacto)*
* **Si usamos la implementación B:** La lista sí pasará a llamarse "Juan Carlos", porque el nodo y la variable externa `juan` comparten la misma referencia de memoria; el cambio externo afecta silenciosamente a la estructura interna.
  * *Diagrama de Memoria:* 
    Variable juan ---> [Objeto Persona: "Juan Carlos"] <--- Nodo en la Lista

#### Referencias y constructores

##### A. ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.
* La cantidad total depende enteramente de la implementación del Nodo que estemos utilizando, ya que debemos contar cada vez que se ejecuta la palabra reservada `new` o se invoca un constructor:
  * **Si usamos la Implementación B (Lista Vinculada):** Hay exactamente **2 objetos Persona** en memoria. Las variables `manuel` y `copia` crean 2 objetos con `new`. La variable `referencia` solo es un puntero al Objeto 1. Al insertarlos, los nodos solo guardan estas referencias sin crear objetos nuevos.
    * *Diagrama de Memoria (Implementación B - 2 Objetos):* 
      Variables externas: 
      manuel     ---> [Objeto 1: "Manuel", "1234"] <--- Nodo 3 de la Lista
      referencia ---> (Apunta al Objeto 1)         <--- Nodo 1 de la Lista
      copia      ---> [Objeto 2: "Manuel", "1234"] <--- Nodo 2 de la Lista
  * **Si usamos la Implementación A (Lista Desconectada / Snapshot):** Hay **5 objetos Persona** distintos en memoria. Las variables externas apuntan a 2 objetos, pero cada inserción en la lista ejecuta un `new Persona(...)` interno, creando 3 objetos adicionales.
    * *Diagrama de Memoria (Implementación A - 5 Objetos):* 
      Variables externas: 
      manuel     ---> [Objeto 1 original: "Manuel", "1234"] 
      referencia ---> (Apunta al Objeto 1) 
      copia      ---> [Objeto 2 copia manuel: "Manuel", "1234"] 
      Nodos de la Lista (espejo): 
      Nodo 1 (al insertar manuel)     ---> [Objeto 3 interno: "Manuel", "1234"] 
      Nodo 2 (al insertar copia)      ---> [Objeto 4 interno: "Manuel", "1234"] 
      Nodo 3 (al insertar referencia) ---> [Objeto 5 interno: "Manuel", "1234"]

##### B. Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?
* El impacto de esta modificación externa también depende de la implementación elegida para el nodo de la lista:
  * **Si usamos la Implementación B:** Se verán afectados el primer elemento insertado (`manuel`) y el tercer elemento insertado (`referencia`). Como ambos nodos apuntan a la misma dirección de memoria que la variable externa `manuel`, el cambio se refleja en la lista. El segundo elemento insertado (`copia`) no se verá afectado porque es un objeto distinto.
  * **Si usamos la Implementación A:** Ningún elemento de la lista se verá afectado. Como la lista creó su propia "lista espejo" clonando cada objeto durante las inserciones, los datos internos están encapsulados y protegidos de cualquier modificación externa que sufra el objeto `manuel` original.

#### Diseño y optimización

##### Propuesta de implementación
Para cumplir con los requisitos, se propone basar la lista en la Implementación B (guardar por referencia) por defecto para optimizar la memoria, pero modificando el método de inserción en la clase `Lista` de la siguiente manera:

```java
public boolean insertarPersona(Persona nuevaPersona, boolean comoCopia) {
    Nodo actual = primero;
    while (actual != null) {
        if (actual.getPersona() == nuevaPersona) {
            System.out.println("Error: Esta misma referencia ya existe en la lista.");
            return false;
        }
        actual = actual.getSiguiente();
    }

    Persona personaAInsertar;
    if (comoCopia) {
        personaAInsertar = new Persona(nuevaPersona); 
    } else {
        personaAInsertar = nuevaPersona;
    }

    primero = new Nodo(personaAInsertar, primero);
    return true;
}
```

##### Justificación de las decisiones tomadas:
* **Detección de referencia:** Se recorre la lista utilizando el comparador `==` en lugar de comparar el DNI. El `==` verifica si ambas variables apuntan a la misma dirección de memoria física, cumpliendo con detectar la inserción de una referencia duplicada.
* **Elección de comportamiento:** Se añade el parámetro booleano `comoCopia` en el método. Esto delega la decisión al programador que usa la lista, permitiéndole elegir el comportamiento caso por caso.
* **Eficiencia de memoria:** El diseño es híbrido. Evita el problema de la Implementación A (que clonaba todo incondicionalmente) asegurando que el operador `new` solo se ejecute cuando el parámetro `comoCopia` sea `true`. Si es `false`, se comporta como la Implementación B, maximizando la eficiencia de los recursos.

#### Análisis de código

##### A. ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?
* El principal problema es que el método `buscarPersona(String dni)` devuelve la referencia directa al objeto `Persona` almacenado en la lista privada del `GestorPersonas`. Dado que los objetos `Persona` son mutables (tienen métodos como `setNombre`), cualquier clase externa que llame a este método obtiene el "control remoto" del objeto y puede modificar sus datos internos sin pasar por las validaciones del Gestor. Adicionalmente, al agregar personas, el método `contiene` delega en una búsqueda por igualdad de datos (DNI), lo que podría mezclar referencias no deseadas si no se controla bien la inserción.

##### B. ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?
* Para evitar los efectos secundarios de entregar la referencia original, el `GestorPersonas` no debe exponer sus objetos internos. La mejora consiste en aplicar una copia. En lugar de devolver el acceso al objeto real, el gestor debe construir y devolver un objeto nuevo (un clon) que contenga los mismos datos. Así, si el código externo modifica el objeto recibido, solo estará alterando su propia copia temporal y la lista original permanecerá intacta.

##### C. Proponga una solución que proteja la integridad de los datos.
* Aprovechando que la clase `Persona` cuenta con un constructor de copia, modificamos el método `buscarPersona` para proteger la integridad de los datos de la siguiente manera:

```java
public Persona buscarPersona(String dni) {
    Persona personaEncontrada = lista.obtenerPersona(dni);
    if (personaEncontrada != null) {
        return new Persona(personaEncontrada); 
    }
    return null;
}
```