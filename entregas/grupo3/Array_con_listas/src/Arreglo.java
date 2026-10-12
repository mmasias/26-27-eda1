class Arreglo {
    private int longitud;
    private ListaEnlazada lista;

    public Arreglo(int longitud){
        this.longitud = longitud;
        crearArreglo(longitud);
    }

    public void agregarElemento(int posicion, int elemento){
        validarPosicion(posicion);
        lista.insertarEnPosicion(posicion, elemento);
    }

    private void validarPosicion(int posicion){
        assert(posicion >= 0 && posicion < longitud) : "Posición fuera de rango";
    }
    public int longitud(){
        return longitud;
    }

    private void crearArreglo(int tamaño){
        lista = new ListaEnlazada();
        for(int i = 0; i <= tamaño; i++){
            lista.insertarAlPrincipio(0);
        }
    }

    public int obtenerElemento(int posicion){
        validarPosicion(posicion);
        return lista.obtenerElemento(posicion);
    }
}