
class PilaBase {
    private Nodo primero;
    private Console console;

    public PilaBase() {
        primero = null;
        console = new Console();

    }

    public void apilar(int valor) {
        Nodo nuevo = new Nodo(valor, primero);
        primero = nuevo;
    }

    public int desapilar() {
        assert !this.estaVacia();
        int valor = primero.getValor();
        primero = primero.getSiguiente();
        return valor;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void mostrar() {
        Nodo actual = primero;
          console.write("pila-> ");
        while (actual!=null) {

            console.write("["+actual.getValor()+"]");
            actual = actual.getSiguiente();
        }
        console.writeln();
    }
}