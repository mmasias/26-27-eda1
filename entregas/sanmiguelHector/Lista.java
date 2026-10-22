public class Lista {
    private Nodo primero;
    private int tamano;

    public Lista() {
        this.primero = null;
        this.tamano = 0;
    }

    public void agregar(String dato){
        Nodo nuevoNodo = new Nodo(dato);
        if (primero == null){
            primero = nuevoNodo;
        }
        else{
            Nodo auxiliar = primero;
            while(auxiliar.getSiguiente() != null){
                auxiliar = auxiliar.getSiguiente();
            }
            auxiliar.setSiguiente(nuevoNodo);
        }
        tamano++;
    }
    public boolean eliminar(String dato){
        if (primero == null)
            return false;
        if (primero.getDato().equals(dato)){
            primero = primero.getSiguiente();
            tamano--;
            return true;
        }
        
        Nodo auxiliar = primero;
        while (auxiliar.getSiguiente() != null && 
                !auxiliar.getSiguiente().getDato().equals(dato)) {
            auxiliar = auxiliar.getSiguiente();
        }

        if (auxiliar.getSiguiente() != null) {
            auxiliar.setSiguiente(auxiliar.getSiguiente().getSiguiente());
            tamano--;
            return true;
        }
        return false;
    }

    public boolean contiene(String dato) {
        Nodo auxiliar = primero;
        while (auxiliar != null) {
            if (auxiliar.getDato().equals(dato)) {
                return true;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        return false;
    }
    
    public int getTamanoLista(){
        return tamano;
    }
}