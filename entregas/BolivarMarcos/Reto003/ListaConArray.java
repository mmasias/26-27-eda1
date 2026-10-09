package entregas.BolivarMarcos.Reto003;

class ListaConArray{
    private int[] datos;
    private int cantidad;

    public ListaConArray() {
        datos= new int[4];
        cantidad = 0;
    }

    public void agregar(int dato){
        if(cantidad==datos.length){
            ampliar();
        }
        datos[cantidad]= dato;
        cantidad++;
    }

    private void ampliar(){
        int[] nuevo= new int[datos.length*2];
        for(int i=0;i<cantidad; i++) {
            nuevo[i]= datos[i];
        }
        datos= nuevo;
    }


    public void imprimir(){
        System.out.print("[");
        for (int i=0; i< cantidad; i++){
            System.out.print(datos[i]);
            if (i<cantidad-1){
                System.out.print(",");
            }
        }
        System.out.println("] cantidad="+ cantidad+ " capacidad ="+datos.length);
    }   
}