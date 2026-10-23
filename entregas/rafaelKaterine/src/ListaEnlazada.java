class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregar(String nombre) {
        Nodo nuevo = new Nodo(nombre);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public boolean sumarVoto(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.nombre.equalsIgnoreCase(nombre)) {
                temp.votos++;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    public boolean restarVoto(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.nombre.equalsIgnoreCase(nombre)) {
                if (temp.votos > 0) temp.votos--;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    public void mostrarCandidato(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.nombre.equalsIgnoreCase(nombre)) {
                System.out.println("-> Candidato/Opción: " + temp.nombre + " | Votos acumulados: " + temp.votos);
                return;
            }
            temp = temp.siguiente;
        }
        System.out.println("-> El candidato u opción '" + nombre + "' no existe en la lista.");
    }

    public void mostrarResultados() {
        Nodo temp = cabeza;
        int totalGeneral = 0;
        System.out.println("==========================================");
        System.out.println("        DESGLOSE GENERAL DE VOTOS         ");
        System.out.println("==========================================");
        while (temp != null) {
            System.out.println("- " + temp.nombre + ": " + temp.votos + " voto(s)");
            totalGeneral += temp.votos;
            temp = temp.siguiente;
        }
        System.out.println("------------------------------------------");
        System.out.println("TOTAL GENERAL DE REGISTROS: " + totalGeneral);
        System.out.println("==========================================\n");
    }

    public boolean eliminar(String nombre) {
        if (cabeza == null) return false;

        if (cabeza.nombre.equalsIgnoreCase(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.nombre.equalsIgnoreCase(nombre)) {
            temp = temp.siguiente;
        }

        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
            return true;
        }
        return false;
    }

    public boolean contiene(String nombre) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.nombre.equalsIgnoreCase(nombre)) return true;
            temp = temp.siguiente;
        }
        return false;
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}