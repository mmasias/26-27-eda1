import java.util.Scanner;

class Nodo {
    int dato;
    int posicion;
    Nodo siguiente;

    public Nodo(){
        this.dato = dato;
        this.posicion = posicion;
        this.siguiente = null;
    }

    public void cambiarValor(int valorNuevo){
        this.dato = valorNuevo;
    }
}

class Lista {
    private Nodo cabeza;

    public Lista(){
        this.cabeza = null;
    }

    public void crearArray(int tamañoArray){
        for(int i = 0; i<=tamañoArray; i++){
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.posicion = i;
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }

    public Nodo obtenerNodo(int posicionBuscada){
        Nodo actual = cabeza;

        while (actual != null){
            if (actual.posicion == posicionBuscada){
                return actual;
            }
            
            actual = actual.siguiente;
        }

        return null;
    }

    public void imprimirArray(int tamañoArray){
        for(int i = 0; i <= tamañoArray; i++){
            System.out.print("[" + obtenerNodo(i).dato + "]");
        }
        System.err.println("");
    }
}

public class reto003{
    public static void main(String[] args) {
        Lista lista = new Lista();
        boolean encendido = true;    
        Scanner scanner = new Scanner(System.in);
        int posicionBuscada = 0;
        int valorNuevo = 0;

        System.out.println("Elige el tamaño del array: ");
        int tamañoArray = scanner.nextInt();
        System.out.println("");
        lista.crearArray(tamañoArray);

        while(encendido){
            lista.imprimirArray(tamañoArray);
            System.out.println("Que espacio quieres modificar: ");
            posicionBuscada = scanner.nextInt();
            System.out.println("Que valor le quieres dar: ");
            valorNuevo = scanner.nextInt();

            lista.obtenerNodo(posicionBuscada).cambiarValor(valorNuevo);
        }
    }
}