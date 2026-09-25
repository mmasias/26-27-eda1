package Src.Entregas.GarrastazuAsier.Reto001_02;

public class Cliente {
    private Cliente siguiente;
    private int minutoLlegada;

    public Cliente(){
        this.minutoLlegada = minutoLlegada;
        this.siguiente = null;
    }

    private int getMinutoLligada(){
        return minutoLlegada;
    }
    
    public Cliente getSiguiente(){
        return siguiente;
    }
    
    public void setSiguiente(Cliente siguiente){
        this.siguiente = siguiente;
    }

}
