class ListaVotantes {
    
    private class NodoVotante {
        String nombre;
        boolean haVotado;
        NodoVotante siguiente;
        
        NodoVotante(String nombre) {
            this.nombre = nombre;
            this.haVotado = false;
            this.siguiente = null;
        }
    }

    private NodoVotante cabeza;

    public void agregar(String nombre) {
        NodoVotante nuevo = new NodoVotante(nombre);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void eliminar(String nombre) {
        NodoVotante dummy = new NodoVotante("");
        dummy.siguiente = cabeza;
        
        for (NodoVotante previo = dummy, actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.nombre.equals(nombre)) {
                previo.siguiente = actual.siguiente;
            } else {
                previo = actual;
            }
        }
        cabeza = dummy.siguiente;
    }

    public boolean estaPresente(String nombre) {
        for (NodoVotante actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.nombre.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean intentarVotar(String nombre) {
        for (NodoVotante actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.nombre.equals(nombre)) {
                boolean puedeVotar = !actual.haVotado;
                actual.haVotado = true;
                return puedeVotar;
            }
        }
        return false;
    }

    public int contarPresentes() {
        int contador = 0;
        for (NodoVotante actual = cabeza; actual != null; actual = actual.siguiente) {
            contador++;
        }
        return contador;
    }
}