class Persona {

    private String nombre;
    private Persona siguiente;
    private Persona anterior;

    public Persona(String nombre) {
        this.nombre = nombre;
        siguiente = null;
        anterior = null;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void encolar(Persona persona) {
        if (siguiente == null) {
            siguiente = persona;
            persona.vaDelante(this);
        } else {
            siguiente.encolar(persona);
        }
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

    public String mostrar(){
        if (siguiente == null) {
        return nombre;
        } else {
            return nombre + " -> " + siguiente.mostrar();
        }
    }

    public int contar() {
        if (siguiente == null) {
            return 1;
        } else {
            return 1 + siguiente.contar();
        }
    }

    public Persona buscar(String nombre) {
        if (this.nombre.equals(nombre)) {
            return this;
        } else if (siguiente != null) {
            return siguiente.buscar(nombre);
        } else {
            return null;
        }
    }

    public void colocarseDetrasDe(Persona persona) {
        if (persona == null || persona == this) {
            return;
        }
        this.salir();
        Persona queEstabaDetras = persona.siguiente;
        persona.siguiente = this;
        this.anterior = persona;
        this.siguiente = queEstabaDetras;
        if (queEstabaDetras != null) {
            queEstabaDetras.anterior = this;
        }
    }


    public String mostrarAlReves() {
        if (siguiente == null) {
            return nombre;
        }
        return siguiente.mostrarAlReves() + " <- " + nombre;
    }

    public Persona getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Persona siguiente) {
        this.siguiente = siguiente;
    }

    public Persona getAnterior() {
        return anterior;
    }

    public void setAnterior(Persona anterior) {
        this.anterior = anterior;
    }



}