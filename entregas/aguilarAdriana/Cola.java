package entregas.aguilarAdriana;

public class Cola {
    private Pila pila;
    private int primero;
    private int ultimo;
    private int tamaño;
 
    public Cola() {
        pila = new Pila();
        tamaño = 0;
    }
    public void encolar(int valor) {
        pila.apilar(valor);
        if (tamaño == 0) {
            primero = valor;
        }
        ultimo = valor;
        tamaño++;
    }
    
    public int desencolar() 
    public boolean estaVacia()
}
