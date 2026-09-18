import java.util.Scanner;

class Cliente{
    private int productos;
    private int minutosEsperando;
    private double intentoAbandono;
    private final double PROBABILIDAD_IRSE = 0.3;

    public Cliente(int productos){
        this.productos = productos;
    }

    public int getProductos(){
        return productos;
    }

    public int getMinutosEsperando(){
        return minutosEsperando;
    }

    public void cobrarProductos(){
        final int PRODUCTOS_POR_SEGUNDO = 5;
        this.productos = productos - PRODUCTOS_POR_SEGUNDO;
    }

    public void espera(){
        this.minutosEsperando = minutosEsperando + 1;
    }

    public boolean abandonarCola(){
        this.intentoAbandono = Math.random();
        return intentoAbandono <= PROBABILIDAD_IRSE;    
    }
}

public class reto001{
    static int ultimoPuesto = 0;
    static Cliente[] listaClientes = new Cliente [30];
    
    public static void main(String[] args){    

        final double PROBABILIDAD_CLIENTE = 0.6;
        double clienteNuevo = 0;
        int cantidadProductos = 0;

        Scanner scanner = new Scanner(System.in);    

        for(int minutos = 0 ; minutos <= 240 ; minutos++){
            
            dibujarCola(minutos);
            generarClientes(clienteNuevo, PROBABILIDAD_CLIENTE, cantidadProductos);  
            aumentarTiempoEspera();
            avanzarCola();

            if(minutos >= 20){
                aburridometro();
            }
            scanner.nextLine();

        }
        scanner.close();
        System.out.println("El supermercado ha cerrado.");
    }

    public static void dibujarCola(int minutos){
        for(int i = 0; i <= listaClientes.length - 1; i++){
            if(listaClientes[i] == null){
            System.out.print("[ ]");

            }else{
                System.out.print("[" + listaClientes[i].getProductos() + ", " + listaClientes[i].getMinutosEsperando() + "]");
            }
        }
        System.out.println("\nMinuto: " + minutos);
    }    
    
    public static void generarClientes(double clienteNuevo, double PROBABILIDAD_CLIENTE, int cantidadProductos){
        if (ultimoPuesto  <= listaClientes.length - 1){
            clienteNuevo = Math.random();

            if (clienteNuevo <= PROBABILIDAD_CLIENTE){
                cantidadProductos = (int) (Math.random() * 20) + 1;
                listaClientes[ultimoPuesto] = new Cliente(cantidadProductos);
                ultimoPuesto = ultimoPuesto + 1;
            }
        }
    }

    public static void avanzarCola(){
        if(listaClientes[0] != null){
            if (listaClientes[0].getProductos() > 0){
                listaClientes[0].cobrarProductos();
            }
            if (listaClientes[0].getProductos() <=0){
                for(int i = 0; i <= listaClientes.length - 1; i++){
                    if(i != listaClientes.length - 1){
                        listaClientes[i] = listaClientes[i + 1];
                    }else{
                        listaClientes[i] = null;
                    }
                }

                ultimoPuesto = ultimoPuesto - 1;
            }                
        }
    }

    public static void aumentarTiempoEspera(){
        for(int i = 0; i <= ultimoPuesto - 1; i++){
            listaClientes[i].espera();
        }
    }

    public static void aburridometro(){
        for(int i = 1; i <= ultimoPuesto - 1; i++){
            if(listaClientes[i].getMinutosEsperando() >= 8){
                if(listaClientes[i].abandonarCola()){
                    System.out.println("El cliente en el puesto " + i + " va a abandonar la cola.");
                    for(int j = i; j <= ultimoPuesto - 1; j++){
                        if (j != ultimoPuesto - 1){
                            listaClientes[j] = listaClientes[j + 1];                    
                        }else{
                            listaClientes[j] = null;
                        }
                    }
                    ultimoPuesto = ultimoPuesto - 1;
                }
            }
        }
    }
}