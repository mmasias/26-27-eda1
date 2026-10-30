
# Simulacro de examen - Estructuras de Datos y Algoritmos I

## 1. Análisis de implementaciones

En este primer ejercicio tenemos dos formas de implementar un nodo de una lista enlazada. La diferencia principal es que la implementación A crea una copia de la persona y la B guarda directamente su referencia.

### A. ¿Qué implementación utiliza mejor la memoria?

Creo que la implementación B es más eficiente en cuanto a memoria, porque no necesita crear otro objeto `Persona` cada vez que insertamos un nodo.

En la implementación A tenemos:

```java
this.persona = new Persona(persona.getNombre(), persona.getDni());
```

Al utilizar `new` estamos creando otro objeto en memoria con los mismos datos.

En cambio, en B:

```java
this.persona = persona;
```

Simplemente guardamos una referencia al objeto que ya existía.

**Diagrama de memoria:**

```text
IMPLEMENTACIÓN A

personaOriginal ──> [Persona 1: Ana]

lista ──> [Nodo] ──> [Persona 2: Ana]

Son dos objetos diferentes.


IMPLEMENTACIÓN B

personaOriginal ─────┐
                     v
                  [Persona 1: Ana]
                     ^
                     |
lista ──> [Nodo] ────┘

Las referencias apuntan al mismo objeto.
```

Por ejemplo, si insertamos 100 personas que ya estaban creadas, con A generaríamos otras 100 personas, mientras que con B reutilizaríamos las existentes.

Por eso elegiría B si lo que buscamos es ahorrar memoria.

### B. Ventajas y problemas de cada implementación

**Implementación A:**

La principal ventaja es que el objeto original y el almacenado en la lista son independientes.

Por ejemplo, si insertamos a Ana y después cambiamos su nombre, la copia que está dentro de la lista seguiría teniendo el nombre anterior.

Esto puede venir bien cuando queremos que la información de la lista no cambie por modificaciones externas.

El problema es que consume más memoria y que, si queremos actualizar una persona, sus copias no se actualizan automáticamente.

**Implementación B:**

Su ventaja es que utiliza menos memoria y permite que diferentes partes del programa trabajen con el mismo objeto.

Sin embargo, esto también puede ser un inconveniente.

Si modificamos una persona desde fuera de la lista, estaremos modificando el mismo objeto que guarda el nodo.

Por ejemplo:

```java
Persona ana = new Persona("Ana", "1234");
lista.insertarPersona(ana);
ana.setNombre("Ana María");
```

Con A, la lista conserva `"Ana"`.

Con B, la lista pasa a tener `"Ana María"`.

Por tanto, ninguna implementación es siempre mejor. Depende de si queremos compartir los objetos o mantener copias independientes.

### C. ¿Cómo afectan al comportamiento de la lista?

Supongamos que insertamos dos veces la misma persona:

```java
Persona ana = new Persona("Ana", "1234");
lista.insertarPersona(ana);
lista.insertarPersona(ana);
```

Con A se crearían dos copias diferentes.

```text
ana ──> [P1]

lista ──> [Nodo 2] ──> [Nodo 1] ──> null
             |             |
             v             v
            [P3]          [P2]
```

Con B, los dos nodos apuntarían a la misma persona.

```text
ana ───────────┐
               v
              [P1]
             ^    ^
             |    |
          Nodo2  Nodo1
```

Esto significa que, con A, cada elemento puede cambiar de manera independiente.

Con B, si modificamos la persona, veremos ese cambio desde los dos nodos.

Los enlaces de la lista funcionan igual en ambos casos. Lo que cambia es cómo se guardan las personas.

---

## 2. Gestión de referencias

### A. ¿Qué nombre tendrá la persona almacenada?

Tenemos el siguiente código:

```java
Lista lista = new Lista();
Persona juan = new Persona("Juan", "1234");

lista.insertarPersona(juan);

Persona encontrada = lista.obtenerPersona("1234");
encontrada.setNombre("Juan Manuel");
```

La persona que está dentro de la lista terminará llamándose **Juan Manuel**, tanto con A como con B.

Esto ocurre porque `obtenerPersona()` devuelve la referencia al objeto almacenado dentro del nodo.

Con A, ese objeto es una copia de Juan.

```text
juan ──> [P1: Juan]

lista ──> [Nodo] ──> [P2: Juan Manuel]
                           ^
                           |
                       encontrada
```

Por tanto, el objeto original sigue llamándose Juan, pero la copia de la lista cambia.

Con B, todas las referencias apuntan al mismo objeto.

```text
juan ──────────────┐
                   v
             [P1: Juan Manuel]
                   ^
                   |
lista ──> [Nodo] ──┤
                   |
               encontrada
```

En este caso también cambia el objeto original.

Lo importante es entender que, aunque A haga una copia al insertar, cuando después la buscamos estamos recibiendo una referencia a esa copia, no una nueva copia.

### B. ¿Qué pasa si modificamos a Juan después de insertarlo?

```java
juan.setNombre("Juan Carlos");
```

Si hacemos esta modificación justo después de insertar, sin realizar antes el apartado A:

- Con A, la lista sigue teniendo `"Juan"`.
- Con B, la lista pasa a tener `"Juan Carlos"`.

Esto se debe a que A tiene un objeto independiente, mientras que B comparte el original.

Si hacemos esta modificación después de ejecutar también el apartado A, con A la lista conservaría `"Juan Manuel"` y con B pasaría a tener `"Juan Carlos"`.

---

## 3. Referencias y constructores

Tenemos:

```java
Persona manuel = new Persona("Manuel", "1234");
Persona copia = new Persona(manuel);
Persona referencia = manuel;

Lista lista = new Lista();

lista.insertarPersona(manuel);
lista.insertarPersona(copia);
lista.insertarPersona(referencia);
```

### A. ¿Cuántos objetos Persona diferentes hay?

Primero tenemos que distinguir las tres variables.

```java
Persona manuel = new Persona("Manuel", "1234");
```

Aquí creamos un objeto.

```java
Persona copia = new Persona(manuel);
```

Aquí creamos otro objeto utilizando el constructor de copia.

```java
Persona referencia = manuel;
```

Aquí no se crea ningún objeto nuevo. Simplemente tenemos otra referencia al mismo objeto.

Antes de insertar tenemos esta situación:

```text
manuel ─────┐
            v
        [P1: Manuel]
            ^
referencia ─┘

copia ──────> [P2: Manuel]
```

Por tanto, antes de insertar existen dos objetos `Persona`.

**Con implementación A:**

Cada inserción crea otra persona.

Como insertamos tres veces, se crean tres copias adicionales.

En total tenemos **5 objetos Persona**.

```text
manuel ──> [P1] <── referencia
copia ───> [P2]

lista ──> Nodo3 ──> Nodo2 ──> Nodo1
            |          |         |
            v          v         v
           [P5]       [P4]      [P3]
```

**Con implementación B:**

No se crean nuevas personas durante las inserciones.

Los nodos simplemente guardan las referencias existentes.

Por tanto, tenemos **2 objetos Persona**.

```text
lista ──> Nodo3 ──> Nodo2 ──> Nodo1
            |          |         |
            v          v         |
           [P1]       [P2]       |
            ^                    |
            └────────────────────┘
```

### B. ¿Qué elementos cambian si modificamos Manuel?

```java
manuel.setNombre("Manuel Antonio");
```

Con A no cambia ninguno de los tres elementos de la lista, porque todos tienen sus propias copias.

Con B cambian el primer y el tercer nodo que insertamos, porque los dos apuntan al objeto `manuel`.

El segundo nodo no cambia, ya que contiene el objeto `copia`, que es independiente.

En resumen, con A cambian **0 elementos** y con B cambian **2 elementos**.

---

## 4. Diseño y optimización

En este apartado tenemos que proponer una lista que permita detectar referencias repetidas, elegir entre copia o referencia y utilizar la memoria de forma razonable.

### Mi propuesta

Mantendría una lista simplemente enlazada, porque para este ejercicio no necesitamos una estructura más compleja.

Para elegir si queremos copiar o compartir una persona, utilizaría un parámetro booleano llamado `copiar`.

También guardaría en cada nodo la referencia original, para saber si ya hemos insertado ese mismo objeto.

Esto es importante porque, si guardamos una copia, la referencia almacenada es distinta de la original.

### Código Java

```java
class Persona {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Persona(Persona otra) {
        this.nombre = otra.nombre;
        this.dni = otra.dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

class Nodo {
    Persona original;
    Persona persona;
    Nodo siguiente;

    public Nodo(Persona original, Persona persona, Nodo siguiente) {
        this.original = original;
        this.persona = persona;
        this.siguiente = siguiente;
    }
}

class Lista {
    private Nodo primero;

    public boolean contieneReferencia(Persona persona) {
        Nodo actual = primero;

        while (actual != null) {
            if (actual.original == persona
                    || actual.persona == persona) {
                return true;
            }

            actual = actual.siguiente;
        }

        return false;
    }

    public boolean insertarPersona(Persona persona, boolean copiar) {
        if (persona == null) {
            return false;
        }

        if (contieneReferencia(persona)) {
            return false;
        }

        Persona guardada;

        if (copiar) {
            guardada = new Persona(persona);
        } else {
            guardada = persona;
        }

        primero = new Nodo(persona, guardada, primero);
        return true;
    }

    public Persona obtenerPersona(String dni) {
        Nodo actual = primero;

        while (actual != null) {
            if (actual.persona.getDni().equals(dni)) {
                return new Persona(actual.persona);
            }

            actual = actual.siguiente;
        }

        return null;
    }
}
```

### ¿Cómo funciona?

Si insertamos una persona con `copiar = true`, se crea un nuevo objeto.

Si utilizamos `copiar = false`, se guarda la misma referencia.

Por ejemplo:

```java
Persona ana = new Persona("Ana", "1234");

Lista lista = new Lista();

lista.insertarPersona(ana, true);
lista.insertarPersona(ana, false);
```

La primera inserción se realiza correctamente.

La segunda se rechaza porque la referencia original ya estaba registrada.

Para comprobar si es el mismo objeto utilizo `==`.

No utilizo `equals()` porque aquí nos interesa saber si dos referencias apuntan al mismo objeto, no si tienen los mismos datos.

### Diagrama de memoria

```text
ana ───────────────> [P1: Ana]
                       ^
                       |
                    original
                       |
lista ──> [Nodo] ───────┤
             |
             | persona
             v
          [P2: Ana]
```

El nodo conserva la referencia original y la copia que realmente guarda.

### Eficiencia

La inserción al principio, si no comprobáramos duplicados, sería O(1).

Como primero tenemos que recorrer la lista para comprobar si la referencia existe, la inserción completa es O(n) en el peor caso.

La búsqueda también es O(n), porque puede ser necesario recorrer todos los nodos.

En cuanto a memoria, la lista necesita O(n) espacio para almacenar sus nodos.

### Otras opciones que he descartado

Podríamos guardar siempre referencias, pero entonces no cumpliríamos el requisito de poder crear copias.

También podríamos copiar siempre, pero perderíamos la posibilidad de compartir los objetos.

Otra opción sería comparar por DNI, pero no sería lo mismo que comprobar si estamos insertando la misma referencia.

He preferido guardar la referencia original en cada nodo porque es fácil de entender y de implementar.

El inconveniente es que utilizamos una referencia adicional por nodo. Además, si insertamos una copia, el nodo mantiene referenciado también el objeto original.

---

## 5. Análisis del código GestorPersonas

Tenemos este código:

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

### A. ¿Qué problemas podemos encontrar?

El primer problema que veo es que se declara la lista, pero no se inicializa.

```java
private Lista lista;
```

Si intentamos utilizarla sin crear antes el objeto, tendremos una `NullPointerException`.

Otro problema es que, si utilizamos la implementación B, guardamos referencias a los objetos originales.

Eso significa que alguien podría modificar una persona desde fuera del gestor y cambiar también los datos almacenados.

Además, el método `buscarPersona()` devuelve directamente la persona de la lista.

Por tanto, quien reciba esa referencia podría modificarla sin pasar por ningún método del gestor.

### B. ¿Cómo lo mejoraría?

Lo primero sería inicializar la lista.

Después utilizaría copias defensivas para evitar que se puedan modificar los objetos internos desde fuera.

Por ejemplo, suponiendo que utilizamos la lista original del enunciado con nodos B:

```java
public class GestorPersonas {
    private final Lista lista = new Lista();

    public void agregarPersona(Persona persona) {
        if (persona == null) {
            return;
        }

        Persona copia = new Persona(persona);

        if (!lista.contiene(copia)) {
            lista.insertarPersona(copia);
        }
    }

    public Persona buscarPersona(String dni) {
        Persona encontrada = lista.obtenerPersona(dni);

        if (encontrada == null) {
            return null;
        }

        return new Persona(encontrada);
    }
}
```

De esta forma, cuando insertamos una persona, el gestor guarda una copia.

Y cuando buscamos una persona, devuelve otra copia en lugar de proporcionar directamente el objeto interno.

Así las modificaciones externas no afectan a la información almacenada.

### C. ¿Cómo protegería la integridad de los datos?

Además de utilizar copias defensivas, haría algunas mejoras:

- Comprobaría que las personas recibidas no fueran `null`.
- Evitaría que se insertasen dos personas con el mismo DNI si el sistema exige que sea único.
- No permitiría modificar directamente el DNI una vez creada la persona.
- Haría que las modificaciones se realizasen mediante métodos del gestor.

Por ejemplo, podríamos tener un método `cambiarNombre(dni, nuevoNombre)` para modificar una persona de manera controlada.

También podríamos hacer que `Persona` fuera inmutable, pero eso implicaría eliminar los setters y cambiar el diseño original.

Para este ejercicio me parece más sencillo utilizar copias defensivas.

---

## 6. Pruebas que realizaría

Antes de utilizar este código en un proyecto, comprobaría algunos casos concretos.

| Prueba | Resultado esperado |
|---|---|
| Insertar con A | Se crea otra Persona |
| Insertar con B | Se comparte la referencia |
| Modificar el objeto original con A | La copia no cambia |
| Modificar el objeto original con B | Cambia la persona del nodo |
| Crear una persona mediante constructor de copia | Hay dos objetos |
| Asignar una referencia a otra variable | Sigue habiendo un objeto |
| Tres inserciones del ejercicio con A | 5 objetos Persona |
| Tres inserciones del ejercicio con B | 2 objetos Persona |
| Insertar dos veces la misma referencia | Se rechaza la segunda |
| Modificar una copia obtenida por búsqueda | La lista no cambia |
| Insertar una persona nula | Se rechaza |
