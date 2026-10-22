public class Pila {
    private Nodo cima;
    private int tamano;

    public Pila(){
        this.cima = null;
        this.tamano = 0;
    }

    public void apilar(String dato){
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamano++;
    }
    public String desapilar(){
        if (estaVacia())
            return null;
        String dato = cima.getDato();
        cima = cima.getSiguiente();
        tamano--;
        return dato;
    }

    public String verCima(){
        if (estaVacia())
            return null;
        return cima.getDato();
    }

    public int tamano(){
        return tamano;
    }

    private boolean estaVacia(){
        return cima == null;
    }
}
