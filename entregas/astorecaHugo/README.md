# Ejercicio 1: Análisis de implementaciones

## a) ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

- La **Implementación B** utiliza mejor los recursos de memoria[cite: 3].
- **Justificación**:
  - La **Implementación A** crea un nuevo objeto `Persona` cada vez que se instancia un nodo (`this.persona = new Persona(...)`), duplicando la información de la persona en la memoria heap[cite: 3].
  - La **Implementación B** simplemente almacena la referencia del objeto existente (`this.persona = persona`), evitando duplicidades innecesarias de objetos idénticos[cite: 3].

## b) ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

- **Implementación A**:
  - _Ventaja_: **Aislamiento y seguridad de datos**. Al crear una copia mediante el constructor, si el objeto original externo se modifica más tarde, el nodo de la lista no se ve alterado[cite: 3].
  - _Inconveniente_: **Alto consumo de memoria y sobrecarga de CPU** si se insertan objetos grandes o masivos repetidamente[cite: 3].
- **Implementación B**:
  - _Ventaja_: **Máxima eficiencia de memoria y velocidad**, ya que solo se asignan referencias de punteros de 64 bits[cite: 3].
  - _Inconveniente_: **Efectos colaterales (Aliasing)**. Si se modifica externamente el objeto original pasado por parámetro, el contenido dentro de la lista cambiará de forma indeseada[cite: 3].

## c) ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

- **Implementación A**: La lista actúa como un contenedor independiente; los cambios externos en las variables de origen no afectan el contenido interno de la estructura de datos[cite: 3].
- **Implementación B**: La lista mantiene una dependencia directa con el exterior. Trabaja estrictamente por referencias compartidas, lo que exige un mayor cuidado con la mutabilidad de los objetos almacenados[cite: 3].

---

# Ejercicio 2: Gestión de referencias

## a) Si tenemos el fragmento de código indicado... ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?

- El nombre almacenado será **"Juan Manuel"**[cite: 2].
- **Justificación**: Al ejecutar `lista.obtenerPersona("1234")`, el método devuelve **la referencia directa** al objeto `Persona` que se encuentra dentro del nodo de la lista[cite: 2]. Al aplicar `personaEncontrada.setName("Juan Manuel")` sobre esa referencia devuelta, se modifica directamente el estado interno del objeto alojado en la lista[cite: 2].

## b) ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos");` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?

- Si la lista utiliza nodos basados en la **Implementación B**, el nombre dentro de la lista cambiará automáticamente a "Juan Carlos"[cite: 2, 3].
- Si utiliza la **Implementación A**, el nombre dentro de la lista **no** se verá afectado (permanecerá como "Juan")[cite: 2, 3].
- **Justificación**: Depende directamente de la implementación del nodo[cite: 2, 3]. La implementación B almacena la referencia compartida al objeto `juan`, mientras que la implementación A almacena una copia independiente creada en el momento de la inserción[cite: 3].

---

# Ejercicio 3: Referencias y constructores

## a) ¿Cuántos objetos Persona diferentes hay en memoria? Justifique su respuesta.

- Hay **2** objetos `Persona` diferentes en memoria[cite: 4, 5].
- **Justificación**:
  1. El primer objeto se crea con `new Persona("Manuel", "1234")` y se asigna a la variable `manuel`[cite: 5].
  2. El segundo objeto se crea con `new Persona(manuel)` (utilizando el constructor de copia) y se asigna a la variable `copia`[cite: 4, 5].
  3. La variable `referencia` **no** crea un nuevo objeto; simplemente apunta (copia la referencia) al mismo objeto que `manuel`[cite: 5].
  4. Al insertar estos en la lista: `manuel` y `referencia` apuntan al primer objeto, mientras que `copia` apunta al segundo[cite: 5]. Por tanto, solo existen 2 instancias de `Persona` en la memoria[cite: 4, 5].

## b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

- Se verá afectado el elemento correspondiente a `manuel` (y por ende el de `referencia`), pero **no** el de `copia`[cite: 4, 5].
- **Justificación**:
  - `manuel` y `referencia` comparten exactamente la misma referencia de memoria al primer objeto `Persona`[cite: 4, 5]. Al modificar su atributo mediante `setNombre`, el cambio se refleja inmediatamente a través de cualquiera de las dos variables o desde dentro de la lista[cite: 4, 5].
  - Por el contrario, `copia` se creó utilizando el constructor de copia (`this.nombre = persona.nombre;`), lo que genera un nuevo objeto con sus propios espacios de memoria independientes (clonación superficial de atributos primitivos/cadenas)[cite: 4, 5].

---

# Ejercicio 4: Diseño y optimización

Explicación y Justificación de las Decisiones Tomadas:

- **Detección de duplicados**:
  - Se implementó el método auxiliar `contieneDni` para recorrer la lista y verificar si ya existe un nodo con el mismo identificador antes de proceder a la inserción[cite: 1, 2].
  - Esto cumple estrictamente con el requisito de permitir detectar si se está intentando insertar una referencia o persona que ya existe en la lista, previniendo registros duplicados[cite: 1].

- **Elección entre copia o referencia**:
  - Se añadió un parámetro booleano (`clonar`) en la firma del método de inserción[cite: 1].
  - Si se pasa como `true`, se utiliza el constructor de copia (`new Persona(persona)`), lo que aísla el objeto interno frente a modificaciones externas (equivalente a la Implementación A)[cite: 3, 4, 5].
  - Si se pasa como `false`, se almacena directamente la referencia compartida, priorizando el ahorro absoluto de memoria en el heap (equivalente a la Implementación B)[cite: 3].

- **Eficiencia en el uso de memoria y rendimiento**:
  - Se emplea una estructura de lista enlazada simple donde los nuevos elementos se enlazan directamente en la cabeza (`this.primero = new Nodo(...)`)[cite: 1, 2].
  - Esta elección garantiza una complejidad temporal de $O(1)$ para las inserciones, optimizando al máximo los recursos de procesamiento y memoria sin recorrer toda la estructura innecesariamente[cite: 1, 3].

## Propuesta de implementación de la Lista:

Para cumplir con los requisitos planteados en el enunciado (permitir detectar duplicados, elegir entre insertar copia o mantener referencia, y ser eficiente en memoria), se propone la siguiente implementación en Java:

```java
public class ListaPersonasOptimizada {
    private Nodo primero;

    public void insertarPersona(Persona persona, boolean clonar) {

        if (contieneDni(persona.getDni())) {
            throw new IllegalArgumentException("La persona con DNI " + persona.getDni() + " ya existe en la lista.");
        }


        Persona personaAAlmacenar = clonar ? new Persona(persona) : persona;


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

# Ejercicio 5: Análisis de código

## a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

- **Problema 1 (Mutabilidad y exposición de referencias)**: El método `buscarPersona` delega en `lista.obtenerPersona(dni)`, el cual expone la referencia real del objeto almacenado[cite: 2, 6]. Esto permite que cualquier clase externa modifique el estado interno de la persona en la lista sin control[cite: 6].
- **Problema 2 (Falta de encapsulamiento estricto)**: `agregarPersona` evalúa la existencia mediante `!lista.contiene(persona)`, pero si se pasa un objeto con los mismos datos clave pero distinta identidad de memoria, puede haber comportamientos ambiguos según cómo esté programado el método `contiene`[cite: 2, 6].

## b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

- Modificando los métodos de recuperación para que **devuelvan una copia defensiva** (utilizando el constructor de copia de la clase `Persona`) en lugar de la referencia directa, asegurando que el encapsulamiento de la lista no se rompa desde el exterior[cite: 4, 6].

## c) Proponga una solución que proteja la integridad de los datos.

- Modificar el método de obtención para que retorne una nueva instancia clonada:

```java
public Persona buscarPersona(String dni) {
    Persona p = lista.obtenerPersona(dni);
    if (p != null) {
        return new Persona(p);
    }
    return null;
}

```
