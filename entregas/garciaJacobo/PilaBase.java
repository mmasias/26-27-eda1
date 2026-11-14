

class PilaBase{
    private Nodo primero;
    
    public PilaBase(){
        primero=null;
    }

    public void apilar(int valor){
        Nodo nuevo=new Nodo(valor,primero);
        primero=nuevo;
    }

    public int desapilar(){
        assert !this.estaVacia();
        int valor =primero.getValor();
        primero=primero.getSiguiente();
        return valor;
    }

    public boolean estaVacia() {
       return primero==null;
    }
}