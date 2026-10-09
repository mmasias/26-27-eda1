public class ListaEnlazada {
public static void main(String[] args) {
    
}
    public Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }


    public void eliminarRepetidos() {
        if (cabeza == null) return;

        Nodo dummy = new Nodo(0);
        dummy.siguiente = cabeza;
        Nodo anterior = dummy;

        while (anterior.siguiente != null) {
            
            if (anterior.siguiente.siguiente != null && anterior.siguiente.dato == anterior.siguiente.siguiente.dato) {
                int valorRepetido = anterior.siguiente.dato;

                
                while (anterior.siguiente != null && anterior.siguiente.dato == valorRepetido) {
                    anterior.siguiente = anterior.siguiente.siguiente;
                }
            } else {
               
                anterior = anterior.siguiente;
            }
        }

        cabeza = dummy.siguiente;
    }

  
    public void eliminarRepetidosSinDummy() {
       
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valorRepetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == valorRepetido) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) return;

       
        Nodo anterior = cabeza;
        while (anterior.siguiente != null && anterior.siguiente.siguiente != null) {
            if (anterior.siguiente.dato == anterior.siguiente.siguiente.dato) {
                int valorRepetido = anterior.siguiente.dato;

                
                while (anterior.siguiente != null && anterior.siguiente.dato == valorRepetido) {
                    anterior.siguiente = anterior.siguiente.siguiente;
                }
            } else {
                anterior = anterior.siguiente;
            }
        }
    }
}