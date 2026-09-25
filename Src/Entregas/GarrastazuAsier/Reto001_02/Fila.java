package Src.Entregas.GarrastazuAsier.Reto001_02;


public class Fila {
    
    private Cliente primero;
    private Cliente ultimo;
    private int cantidad;

    public Fila(){
        primero = null;
        ultimo = null;
        cantidad = 0;
    }
    
    public void añadirCliente(int minutoLlegada){

        Cliente nuevo = new Cliente(minutoLlegada);
        
        if (cantidad == 0){
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;

        }

        cantidad++;
    }

    public void atenderCliente(){

        if (cantidad > 0){
            primero = primero.getSiguiente();
            cantidad--;
        } else {
            System.out.println("No hay clientes en la fila.");
        }
    }
    public void abandonarAburridos(int minutoActual) {

    Cliente anterior = null;
    Cliente actual = primero;

    while (actual != null) {

        if (minutoActual - actual.getMinutoLlegada() > 8) {

            if (Math.random() < 0.3) {

                
                if (anterior == null) {
                    primero = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }

                
                if (actual == ultimo) {
                    ultimo = anterior;
                }

                cantidad--;

                actual = actual.getSiguiente();

            } else {
                anterior = actual;
                actual = actual.getSiguiente();
            }

        } else {
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }
}
}
