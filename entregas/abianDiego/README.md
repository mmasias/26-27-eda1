# Simulación parcial - Parte 3

Nota: el codigo del enunciado usa getNombre, getDni, setNombre, getPersona y getSiguiente pero no aparecen en las clases, supongo que existen.

En los diagramas si dos variables apuntan al mismo numero (Persona#1) es el mismo objeto.

# 1. Análisis de implementaciones

### A.

La B, porque solo guarda la referencia a la persona que ya existe. La A hace un new en cada nodo, asi que hay dos personas por cada una que metes.

```
A:
juan         -> Persona#1 ("Juan", "1234")
nodo.persona -> Persona#2 ("Juan", "1234")

B:
juan         -> Persona#1 ("Juan", "1234")
nodo.persona -> Persona#1
```

### B.

Implementación A:
- Ventaja: si cambias la persona desde fuera la lista no cambia.
- Desventajas: gasta mas memoria, y si Persona tiene mas atributos no los copia porque copia solo nombre y dni. Ademas si persona es null da NullPointerException.

Implementación B:
- Ventaja: gasta menos memoria.
- Desventaja: cualquiera que tenga la variable puede cambiar la persona que esta dentro de la lista.

### C.

Con la A la lista tiene sus propias copias y no le afectan los cambios de fuera. Con la B la lista comparte las personas con el resto del programa y los cambios de fuera se ven en la lista. Para buscar da igual porque se busca por dni.

# 2. Gestión de referencias

### A.

Juan Manuel, con los dos nodos. obtenerPersona devuelve la referencia a la persona que esta dentro del nodo, no una copia, asi que el setNombre cambia la de la lista.

```
B:
juan              -> Persona#1 ("Juan Manuel", "1234")
nodo.persona      -> Persona#1
personaEncontrada -> Persona#1

A:
juan              -> Persona#1 ("Juan", "1234")
nodo.persona      -> Persona#2 ("Juan Manuel", "1234")
personaEncontrada -> Persona#2
```

### B.

Si depende. Con la B la lista cambia a Juan Carlos porque el nodo apunta al mismo objeto que juan. Con la A sigue siendo Juan porque el nodo tiene una copia.

```
B:
juan         -> Persona#1 ("Juan Carlos", "1234")
nodo.persona -> Persona#1

A:
juan         -> Persona#1 ("Juan Carlos", "1234")
nodo.persona -> Persona#2 ("Juan", "1234")
```

# 3. Referencias y constructores

### A.

En el main hay 2, porque solo hay dos new. referencia no crea otro objeto, apunta al de manuel.

```
manuel     -> Persona#1 ("Manuel", "1234")
referencia -> Persona#1
copia      -> Persona#2 ("Manuel", "1234")
```

Si la lista usa el nodo B siguen siendo 2:

```
primero -> [Nodo] -> [Nodo] -> [Nodo] -> null
            #1        #2        #1
       (referencia) (copia)  (manuel)
```

Si usa el nodo A son 5, los 2 del main y una copia por cada insertar.

### B.

Con el nodo B cambian el primero y el tercero (referencia y manuel) porque son el mismo objeto, copia no cambia porque es otro objeto. Con el nodo A no cambia ninguno porque la lista tiene copias.

```
B: [Manuel Antonio] -> [Manuel] -> [Manuel Antonio] -> null
A: [Manuel] -> [Manuel] -> [Manuel] -> null
```

# 4. Diseño y optimización

Uso el nodo B para no gastar memoria de mas. Para saber si la referencia ya esta uso == porque compara si es el mismo objeto, no si tiene los mismos datos. Para elegir copia o referencia hago dos metodos, y la copia la hace el constructor de copia de Persona.

```java
class Lista {
    private Nodo primero;

    public boolean contieneReferencia(Persona persona) {
        Nodo actual = primero;
        boolean encontrada = false;
        while (actual != null && !encontrada) {
            encontrada = actual.getPersona() == persona;
            actual = actual.getSiguiente();
        }
        return encontrada;
    }

    public void insertarReferencia(Persona persona) {
        assert !contieneReferencia(persona);
        primero = new Nodo(persona, primero);
    }

    public void insertarCopia(Persona persona) {
        primero = new Nodo(new Persona(persona), primero);
    }
}
```

Si hago insertarReferencia(manuel) y luego insertarCopia(manuel):

```
manuel  -> Persona#1
primero -> [Nodo] -> [Nodo] -> null
            #2        #1
```

# 5. Análisis de código

### A.

- lista nunca se crea porque no hay constructor, asi que el primer agregarPersona da NullPointerException (gestor.lista -> null).
- buscarPersona devuelve la persona que esta dentro de la lista, y quien la recibe la puede cambiar sin pasar por el gestor.
- Con el nodo B agregarPersona guarda la referencia de fuera, asi que se puede seguir cambiando despues.
- Si no encuentra a nadie devuelve null, y si la persona ya estaba no avisa.

### B.

Crear la lista en el constructor, guardar una copia al agregar y devolver una copia al buscar. Si hay que cambiar algo, que sea con un metodo del gestor.

### C.

```java
public class GestorPersonas {
    private Lista lista;

    public GestorPersonas() {
        lista = new Lista();
    }

    public boolean agregarPersona(Persona persona) {
        boolean esNueva = !lista.contiene(persona);
        if (esNueva) {
            lista.insertarPersona(new Persona(persona));
        }
        return esNueva;
    }

    public boolean existePersona(String dni) {
        return lista.obtenerPersona(dni) != null;
    }

    public Persona buscarPersona(String dni) {
        assert existePersona(dni);
        return new Persona(lista.obtenerPersona(dni));
    }

    public void cambiarNombre(String dni, String nombre) {
        assert existePersona(dni);
        lista.obtenerPersona(dni).setNombre(nombre);
    }
}
```

```
juan                  -> Persona#1 (fuera)
lista.primero.persona -> Persona#2 (dentro)
p = buscarPersona()   -> Persona#3 (copia)
```

Asi cambiar juan o p no cambia la lista. Gasta mas memoria pero los datos estan protegidos.
