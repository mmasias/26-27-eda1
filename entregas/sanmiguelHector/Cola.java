public class Cola {
    private Nodo primero;
    private Nodo ultimo;
    private int tamano;

    public Cola(){
        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;
    }
    
    public void encolar(String dato){
        Nodo nuevoNodo = new Nodo(dato);
        if (estaVacia()){
            primero = nuevoNodo;
        }
        else{
            ultimo.setSiguiente(nuevoNodo);
        }
        ultimo = nuevoNodo;
        tamano++;
    }

    public String desencolar(){
        if (estaVacia())
            return null;
        String dato = primero.getDato();
        primero = primero.getSiguiente();
        if (estaVacia())
            ultimo = null;
        tamano--;
        return dato;
    }

    public int getTamanoCola(){
        return tamano;
    }

    public boolean estaVacia(){
        return primero == null;
    }
}