public class UrnaCola {
    private NodoPapeleta frente;
    private NodoPapeleta fin;

    public UrnaCola() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(String contenido) {
        NodoPapeleta nuevo = new NodoPapeleta(contenido);
        if (fin == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public String desencolar() {
        if (frente == null) {
            return null;
        }
        String dato = frente.contenido;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
