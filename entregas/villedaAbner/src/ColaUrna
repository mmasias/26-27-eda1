public class ColaUrna {
    private NodoVoto frente; 
    private NodoVoto fin;    

    public ColaUrna() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(Voto nuevoVoto) {
        NodoVoto nuevo = new NodoVoto(nuevoVoto);
        
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo; 
        }
    }

    public Voto desencolar() {
        Voto votoExtraido = null;
        
        if (frente != null) {
            votoExtraido = frente.voto;
            frente = frente.siguiente; 
            
            if (frente == null) {
                fin = null;
            }
        }
        
        return votoExtraido; 
    }
}