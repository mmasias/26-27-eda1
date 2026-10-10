public class Lista {

    private int[] lista;
    private int longitud;
    private final int CAPACIDAD_INICIAL = 10;

    public Lista(){
        this.lista = new int[CAPACIDAD_INICIAL];
        this.longitud = 0;
    }

    public void insertarInicio(int dato){
        
        if(estaLleno()){
            ampliarArray();
        }

        for (int i = longitud - 1; i >= 0; i--) {
            lista[i]= lista[i+1];
        }

        lista[0] = dato;
        longitud++;
    }

    public void insertarFinal(int dato){
        
        if(estaLleno()){
            ampliarArray();
        }

        lista[longitud] = dato;
        longitud++;
    }

    public void eliminarInicio(){
        assert longitud > 0;

        for (int i = 0; i < longitud - 1; i++) {
            lista[i]=lista[i+1];
        }
        longitud--;
    }

    public void eliminarFinal(){
        assert longitud > 0;

        longitud--;
    }

    public int obtener(int posicion){
        assert posicion>0 && posicion<longitud-1;
        return lista[posicion];
    }

    public int longitud(){
        return longitud;
    }

    public boolean estaVacia(){
        return longitud == 0;
    }
    

    private void ampliarArray(){
        
        int[] auxiliar = new int[lista.length*2];
        
        for(int i = 0; i < longitud; i++){
            auxiliar[i] = lista[i];
        }
        lista = auxiliar;
    }

    private boolean estaLleno(){
        return lista.length == longitud;
    }
}
