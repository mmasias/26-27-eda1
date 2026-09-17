import java.util.Scanner;

class Cliente{
    private int productos;

    public Cliente(int productos){
        this.productos = productos;
    }

    public int getProductos(){
        return productos;
    }

    public void cobrarProductos(){
        final int PRODUCTOS_POR_SEGUNDO = 5;
        this.productos = productos - PRODUCTOS_POR_SEGUNDO;
    }
}

public class reto001{
    static int ultimoPuesto = 0;
    static Cliente[] listaClientes = new Cliente [30];
    static int clientesAtendidos = 0;
    
    public static void main(String[] args){    

        final double PROBABILIDAD_CLIENTE = 0.6;
        double clienteNuevo = 0;
        int cantidadProductos = 0;

        Scanner scanner = new Scanner(System.in);    

        for(int minutos = 0 ; minutos <= 240 ; minutos++){

            generarClientes(clienteNuevo, PROBABILIDAD_CLIENTE, cantidadProductos);  
            avanzarCola();
            dibujarCola(minutos);

            scanner.nextLine();
        }

        scanner.close();
        System.out.println("El supermercado ha cerrado.");
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
                    if(i != 29){
                        listaClientes[i] = listaClientes[i + 1];

                    }else{
                        listaClientes[i] = null;
                    }
                }

                ultimoPuesto = ultimoPuesto - 1;
                clientesAtendidos = clientesAtendidos + 1;
            }                
        }
    }

    public static void dibujarCola(int minutos){
        for(int x = 0; x <= listaClientes.length - 1; x++){
            if(listaClientes[x] == null){
            System.out.print("[ ]");
            }else{
                System.out.print("[" + listaClientes[x].getProductos() + "]");
            }
        }
        System.out.println("\nMinuto: " + minutos + "\nClientes atendidos: " + clientesAtendidos);
    }
}