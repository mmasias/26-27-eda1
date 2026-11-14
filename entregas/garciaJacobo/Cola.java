
public class Cola {
        private PilaBase entrada;
        private PilaBase salida;

    public Cola() {
       entrada=new PilaBase();
       salida=new PilaBase();
    }

    public void encolar(int valor) {
        Nodo nuevo = new Nodo(valor, null);
        if (ultimo == null) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public int desencolar() {
        assert !estaVacia();
        int valor = primero.getValor();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}
