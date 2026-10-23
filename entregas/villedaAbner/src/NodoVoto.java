public class NodoVoto {
    public Voto voto;
    public NodoVoto siguiente;

    public NodoVoto(Voto voto) {
        this.voto = voto;
        this.siguiente = null;
    }
}

