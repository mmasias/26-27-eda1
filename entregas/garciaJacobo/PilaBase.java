

class PilaBase{
    private Nodo primero;
    
    public PilaBase(){
        primero=null;
    }

    public void apilar(int valor){
        Nodo nuevo=new Nodo(valor,primero);
        primero=nuevo;
    }
}