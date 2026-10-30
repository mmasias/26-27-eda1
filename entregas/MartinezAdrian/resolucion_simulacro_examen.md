# Resolución del Simulacro de Examen: Gestión de Memoria, Referencias y Listas Enlazadas en Java

Este documento contiene la resolución completa y detallada del simulacro de examen, incluyendo razonamientos sobre el comportamiento real del código y casos de prueba donde el código falla.

## 1. Análisis de implementaciones

### A. ¿Qué implementación considera que utiliza mejor los recursos de memoria? Justifique su respuesta.

* **La Implementación B** es más eficiente en el uso de memoria.

* **Justificación:** En la **Implementación B**, el constructor recibe la referencia `persona` y la asigna directamente a su atributo (`this.persona = persona`). Solo se reserva memoria  para el objeto `Nodo`, reutilizando el objeto `Persona` que ya existía. En cambio, la **Implementación A** ejecuta `new Persona(...)` dentro del constructor, lo que duplica el objeto en memoria por cada inserción. Si insertamos $N$ elementos, la opción A consume memoria para $N$ nodos + $N$ objetos `Persona` adicionales.

### B. ¿Qué problemas o ventajas podría causar cada implementación? Proporcione ejemplos.

* **Implementación A (Creación de copia):**

  * **Ventaja (Encapsulamiento y seguridad):** Aísla los datos de la lista. Si desde fuera del nodo se modifica el objeto original, el nodo no se ve alterado.

  * **Problema 1 (`NullPointerException`):** Como ejecuta `persona.getNombre()`, si se pasa `persona = null` al constructor del nodo, el programa lanzará una excepción inmediatamente.

    * *Caso de prueba:* `new Nodo(null, null);` $\rightarrow$ Falla en A con `NullPointerException`, mientras que funciona en B.

  * **Problema 2 (Pérdida de datos si `Persona` crece):** Si en el futuro la clase `Persona` añade más atributos (ej. `edad`), pero este constructor solo copia `nombre` y `dni`, el objeto guardado en el nodo perderá información respecto al original.

* **Implementación B (Asignación de referencia):**

  * **Ventaja (Rendimiento):** Tiempo de creación constante e instantáneo sin coste extra de memoria para objetos `Persona`.

  * **Problema:** A su vez, el hecho de que tanto el código externo como el nodo apuntan a la misma dirección de memoria puede causar un problema de “modificación colateral”.

    * *Ejemplo:* Insertamos `p1` en un nodo. Más adelante ejecutamos `p1.setNombre("Otro")` fuera de la lista. El nombre cambiará también dentro del nodo sin que la lista lo haya gestionado.

### C. ¿Cómo afectaría cada implementación al comportamiento de una lista que use estos nodos?

* **Con la Implementación A:** La lista se comporta como un contenedor **independiente** (almacenamiento por copia). Sus datos internos solo pueden alterarse mediante métodos de la propia lista. Además, si buscamos un elemento comparando referencias (`==`), nunca lo encontrará porque la referencia guardada dentro del nodo es distinta a la original externa.

* **Con la Implementación B:** La lista almacena **alias** (referencias compartidas). Si la misma referencia `Persona` se inserta en dos listas distintas (o dos veces en la misma lista) y se modifica desde fuera, cambiará simultáneamente en todas las posiciones donde aparezca.

## 2. Gestión de referencias

> **Nota crítica sobre el código de `Lista` (Análisis de fallos reales):**
> Antes de responder a los apartados, existe un error grave en el método `contiene(Persona persona)` entregado por el asistente:
>
> * La línea `actual = actual.getSiguiente();` está **solo dentro del bloque `if`**, justo antes de `return true;`, y **no existe avance fuera del `if`**.
>
> * *Caso de prueba que lo demuestra:* Si insertamos a `"Ana"` (DNI `"1111"`) y llamamos a `lista.contiene(new Persona("Luis", "2222"))`, la condición del `if` es `false` y la variable `actual` jamás avanza al siguiente nodo, provocando un **bucle infinito**.

### a) ¿Qué nombre tendrá la persona almacenada en la lista? ¿Por qué?

* **Respuesta:** Tendrá el nombre **`"Juan Manuel"`** .

* **Por qué:** El método `obtenerPersona("1234")` devuelve directamente `actual.getPersona()`, que es la **referencia exacta al objeto `Persona` almacenado dentro del nodo**. Al guardarla en `personaEncontrada` y ejecutar `personaEncontrada.setNombre("Juan Manuel")`, estamos siguiendo esa referencia en memoria y mutando directamente el objeto interno del nodo.

### b) ¿Qué sucedería si modificamos `juan.setNombre("Juan Carlos")` después de insertarlo en la lista? ¿Depende de la implementación del Nodo que usemos (A o B de la pregunta anterior)?

* **Respuesta:** **Sí, depende totalmente de la implementación del `Nodo`** que utilice la clase `Lista`:

1. **Si usamos la Implementación A:** El nombre dentro de la lista seguirá siendo **`"Juan"`**. Al hacer `insertarPersona(juan)`, el constructor del Nodo A creó un objeto `Persona` nuevo en otra dirección de memoria. La variable `juan` modifica el objeto externo original, mientras que el nodo apunta a su propia copia.

2. **Si usamos la Implementación B:** El nombre dentro de la lista cambiará a **`"Juan Carlos"`**. El constructor del Nodo B guardó la misma referencia `juan`. Como `juan` y `primero.persona` apuntan al mismo objeto, modificar `juan` altera el contenido visible desde la lista.

## 3. Referencias y constructores

### a) ¿Cuántos objetos `Persona` diferentes hay en memoria? Justifique su respuesta.

El número total de objetos  depende de cómo esté implementado `Nodo` dentro de `insertarPersona`:

* **En el método `main` (antes de insertar en la lista):** Se crean **2 objetos `Persona`**:

  1. `Persona manuel = new Persona("Manuel", "1234");` $\rightarrow$ **Objeto 1** (se usa `new`).

  2. `Persona copia = new Persona(manuel);` $\rightarrow$ **Objeto 2** (se usa un segundo `new` que invoca al constructor de copia).

  3. `Persona referencia = manuel;` $\rightarrow$ **0 objetos nuevos**. Solo declara una variable que apunta al **Objeto 1**.

* **Tras las 3 llamadas a `lista.insertarPersona(...)`:**

  * **Si `Nodo` usa la Implementación B (guarda referencias):** Hay **2 objetos `Persona`** en total en memoria. Los tres nodos apuntan a los objetos ya existentes (dos nodos apuntan al Objeto 1 y un nodo apunta al Objeto 2).

  * **Si `Nodo` usa la Implementación A (hace `new Persona` en cada nodo):** Habrá **5 objetos `Persona`** en total en memoria (los 2 creados en el `main` + 3 copias nuevas creadas dentro de cada uno de los 3 nodos insertados).

### b) Si hacemos `manuel.setNombre("Manuel Antonio")`, ¿qué elementos de la lista se verán afectados? ¿Por qué?

* **Si `Nodo` usa la Implementación B:** Se verán afectados **dos elementos de la lista**: el primer nodo insertado (`manuel`) y el tercer nodo insertado (`referencia`).

  * **Por qué:** Porque tanto `manuel` como `referencia` almacenan la misma dirección de memoria (apuntan al Objeto Persona 1). Al insertar ambos en la lista, dos nodos distintos quedan apuntando a ese mismo objeto. El nodo de `copia` no se ve afectado porque apunta al Objeto Persona 2, creado en otra dirección de memoria.

* **Si `Nodo` usa la Implementación A:** **Ningún elemento** de la lista se verá afectado, porque los tres nodos habrían creado sus propios objetos independientes al insertarse.

## 4. Diseño y optimización

### Decisiones de diseño y justificación:

1. **Un único `Nodo` ligero (Eficiencia en memoria):** Se utiliza la **Implementación B** para la clase `Nodo` (`this.persona = persona`). No se añaden atributos adicionales al nodo; se delega en el método de inserción de `Lista` la decisión de crear o no una copia usando el constructor de copia `new Persona(persona)`.

2. **Detección de la misma referencia con el operador `==`:** Para detectar si se está intentando insertar una referencia que ya existe en la lista, comparamos las direcciones de memoria directamente con `actual.getPersona() == persona`. Es una operación inmediata y comprueba identidad de referencia en lugar de igualdad de contenido.

3. **Elección de copia o referencia mediante parámetro booleano:** Se añade el parámetro `boolean hacerCopia` al método de inserción. Si es `true`, se instancia un duplicado en el *Heap*; si es `false`, se guarda la referencia original ahorrando memoria.

### Implementación en Java:

```
class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona; // Almacena la referencia recibida
        this.siguiente = siguiente;
    }

    public Persona getPersona() { return persona; }
    public Nodo getSiguiente() { return siguiente; }
}

class Lista {
    private Nodo primero;

    // 1. Detecta si la MISMA referencia de memoria ya está en la lista
    public boolean contieneReferencia(Persona persona) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getPersona() == persona) { // Comparación de direcciones de memoria
                return true;
            }
            actual = actual.getSiguiente(); // Avance fuera del if
        }
        return false;
    }

    // 2. Permite elegir si insertar copia o mantener referencia
    public boolean insertarPersona(Persona persona, boolean hacerCopia) {
        if (persona == null) {
            return false;
        }
        // Si ya existe exactamente esa referencia en la lista, no se inserta
        if (contieneReferencia(persona)) {
            return false;
        }

        Persona personaAGuardar;
        if (hacerCopia) {
            personaAGuardar = new Persona(persona); // Crea copia independiente en el Heap
        } else {
            personaAGuardar = persona; // Reutiliza la misma referencia (ahorra memoria)
        }

        primero = new Nodo(personaAGuardar, primero);
        return true;
    }
}


```

## 5. Análisis de código (`GestorPersonas`)

### a) ¿Qué problemas potenciales ve en este código respecto al manejo de referencias?

1. **Atributo `lista` sin inicializar (`NullPointerException`):**

   * En `GestorPersonas`, se declara `private Lista lista;` pero **no hay constructor** que instancie `this.lista = new Lista();`. Por defecto, `lista` apunta a `null`.

   * *Caso de prueba:* Llamar a `gestor.agregarPersona(p)` tras instanciar `GestorPersonas` lanzará `NullPointerException` al evaluar `lista.contiene(persona)`.

2. **Fuga de referencias en `buscarPersona(String dni)` (Ruptura de encapsulamiento):**

   * El método devuelve directamente la referencia interna obtenida de `lista.obtenerPersona(dni)`. El código externo obtiene acceso directo al objeto almacenado dentro del nodo.

   * *Caso de prueba:* `Persona p = gestor.buscarPersona("1234"); p.setDni("9999");` modifica el DNI dentro de la lista saltándose las validaciones de `GestorPersonas`.

3. **Captura de referencia externa en `agregarPersona(Persona persona)` (si `Nodo` es tipo B):**

   * Si se inserta la referencia `persona` directamente y posteriormente el código externo modifica esa variable, cambiarán los datos internos del gestor.

4. **Fallo heredado de `lista.contiene(persona)`:**

   * Si `persona` es `null` producirá excepción, además del bucle infinito mencionado en la sección 2 si el primer elemento de la lista no coincide con el buscado.

### b) ¿Cómo lo mejoraría para evitar modificaciones no deseadas de los objetos?

Aplicando **copias defensivas** (mediante el constructor de copia `new Persona(persona)`) en los puntos de entrada y salida de `GestorPersonas`:

* **En la entrada (`agregarPersona`):** Antes de insertar en la lista, crear una copia del objeto recibido para desvincular la lista de la referencia externa original.

* **En la salida (`buscarPersona`):** Al localizar el objeto en la lista, devolver un `new Persona(encontrada)` en lugar de la referencia original guardada en el nodo. Así, cualquier modificación posterior del usuario afectará únicamente a su copia temporal.

### c) Proponga una solución que proteja la integridad de los datos.

```
public class GestorPersonas {
    private Lista lista;

    // 1. Inicializar la lista en el constructor para evitar NullPointerException
    public GestorPersonas() {
        this.lista = new Lista();
    }

    public void agregarPersona(Persona persona) {
        // 2. Comprobar que la referencia no sea nula antes de operar
        if (persona != null && !lista.contiene(persona)) {
            // 3. Copia defensiva de ENTRADA: se guarda un objeto nuevo independiente
            Persona copiaInterna = new Persona(persona);
            lista.insertarPersona(copiaInterna);
        }
    }

    public Persona buscarPersona(String dni) {
        if (dni == null) {
            return null;
        }
        Persona encontrada = lista.obtenerPersona(dni);
        
        // 4. Copia defensiva de SALIDA: si existe, se devuelve un duplicado, no la referencia interna
        if (encontrada != null) {
            return new Persona(encontrada);
        }
        return null;
    }
}

```