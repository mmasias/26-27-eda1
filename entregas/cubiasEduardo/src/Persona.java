public class Persona {

    private String nombre;
    private Persona siguiente;
    private Persona anterior;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
        this.anterior = null;
    }

    public void encolar(Persona persona) {
        encolarRecursivo(persona, 1);
    }

    private void encolarRecursivo(Persona persona, int iteracion) {
        if (this.siguiente == null) {
            this.siguiente = persona;
            persona.vaDelante(this);
            System.out.println(saludarEIdentificarmeYAlAnterior());
        } else {
            System.out.println(saludarEIdentificarTodo());
            this.siguiente.encolarRecursivo(persona, iteracion + 1);
        }
        System.out.println("Soy la persona Nº " + iteracion + " en la fila");
    }

    public boolean haySiguiente() {
        return siguiente != null;
    }

    public Persona devolverSiguiente() {
        Persona nuevoPrimero = siguiente;
        siguiente = null;
        if (nuevoPrimero != null) {
            nuevoPrimero.vaDelante(null);
        }
        return nuevoPrimero;
    }

    public void vaDelante(Persona persona) {
        anterior = persona;
    }

    public void salir() {
        if (anterior != null) {
            anterior.siguiente = siguiente;
        }
        if (siguiente != null) {
            siguiente.anterior = anterior;
        }
        anterior = null;
        siguiente = null;
    }

    public String obtenerNombre() {
        return nombre;
    }

    private String saludarEIdentificarmeYAlAnterior() {
        String prev = (anterior != null) ? anterior.obtenerNombre() : "nadie (soy el primero)";
        return "Hola, soy " + nombre + " y estoy después de " + prev;
    }

    private String saludarEIdentificarTodo() {
        String sig = (siguiente != null) ? siguiente.obtenerNombre() : "nadie";
        return saludarEIdentificarmeYAlAnterior() + " y estoy antes de " + sig;
    }


    public void mostrar() {
        System.out.println(saludarEIdentificarTodo());
        if (siguiente != null) {
            siguiente.mostrar();
        }
    }

    public int contar() {
        if (siguiente == null) {
            return 1;
        }
        return 1 + siguiente.contar();
    }

    public void mostrarAlReves() {
        if (siguiente != null) {
            siguiente.mostrarAlReves();
        }
        System.out.println(saludarEIdentificarTodo());
    }

    public Persona buscar(String nombre) {
        if (this.nombre != null && this.nombre.equalsIgnoreCase(nombre)) {
            return this;
        }
        if (siguiente != null) {
            return siguiente.buscar(nombre);
        }
        return null;
    }

    public void colarseDetrasDe(Persona persona) {
        if (persona == null || persona == this) {
            return;
        }
        this.salir();

        Persona siguienteOriginal = persona.siguiente;
        this.anterior = persona;
        this.siguiente = siguienteOriginal;

        persona.siguiente = this;
        if (siguienteOriginal != null) {
            siguienteOriginal.anterior = this;
        }
    }
}
