class Nodo {
    String nombre;
    int votos;
    Nodo siguiente;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
        this.siguiente = null;
    }
}