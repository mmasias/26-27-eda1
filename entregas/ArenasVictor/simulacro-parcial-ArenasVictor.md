# 1. Análisis de implementaciones

### A. 

La B. Ya que solo referencia a una persona que ya esta creado, mientras que la A siempre crea una persona nueva.

### B. 

En el caso de la implementación A.
  -Ventajas: Permite modificar el objeto despues de insertarlo en la lista sin cambiar la lista.
  -Desventajas: Es menos eficiente y en caso de que persona tenga mas atributos no los copia.

En el caso de la implementación B.
  -Ventajas: Es más eficiente.
  -Desventajas: En caso de querer modificar una persona, solo será posible modificar el nombre interno (el atributo), la lista seguira mostrando el nombre original del objeto persona.

### C. 

En el caso de implementar la opción A, la lista almacenaría sus propias copias y no es afectada por cambios externos.
En el caso de implementar la opción B, la lista comparte las personas con el resto del programa, por lo tanto los cambios externos afectan a la lista.

# 2. Gestión de referencias.

### A. 

El nombre será Juan Manuel, ya que asi lo indica el set.Nombre que modifica el atributo Nombre.

### B.

Depende del nodo, ya que con la implementación B la lista cambia a Juan Carlos ya qeu el nodo apunta al mismo objeto qeu juan y con la implementación A no cambia ya que el nodo apunta a una copia.

# 3. Referencias y constructores.

### A. 

2 Objetos persona, ya que solo hay dos new, referencia no crea un nuevo objeto sino que referencia a manuel.

### B.

Se ven afectados manuel y referencia, porque ambos apuntan al mismo objeto, copia no cambia porque es un objeto distinto.

# 4. Diseño y optimización.

Para comprobar si una referencia existe en la lista, se utiliza == para comprobar si es el mismo objeto.
Para que te permita elegir si insertar una copia o mantener la referencia, se utilizan dos métodos separados, uno que inserta la referencia y otro que inserta la copia. Si la persona ya está o es null, no se inserta.

public class Nodo {

    private Persona persona;
    private Nodo siguiente; 

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    public Persona getPersona() {
        return persona;
    }

}

public class Lista {

    private Nodo primero;

    public Lista() {
        primero = null;
    }

    public boolean contieneReferencia(Persona persona) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getPersona() == persona) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    public boolean insertarCopia(Persona persona) {
        if (persona == null || contieneReferencia(persona)) {
            return false;
        }
        primero = new Nodo(new Persona(persona), primero);
        return true;
    }

    public boolean insertarReferencia(Persona persona) {
        if (persona == null || contieneReferencia(persona)) {
            return false;
        }
        primero = new Nodo(persona, primero);
        return true;
    }

}

# 5. Análisis de Código.

### A.

La variable lista nunca se crea, por lo tanto agregarPersona no funciona.
buscarPersona es inseguro, ya que permite modificar  objetos en la lista.

### B. 

Para evitar modificaciones no deseadas se debería guardar una copia al agregar y devolver otra al buscar, de esta manera nadie tendrá acceso al objeto real desde afuera.

### C.

Para proteger la integridad de los datos DNI debe ser final para que no se pueda modificar y el nombre solo se pueda cambiar a través del gestor.

