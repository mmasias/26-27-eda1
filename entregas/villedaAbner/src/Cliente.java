public  class Cliente {
    private boolean prioridad;
    private int minutosEsperando;
    private int numeroObjetos;

    public Cliente(int minutosEsperando, boolean prioridad, int numeroObjetos) {
        this.minutosEsperando = minutosEsperando;
        this.prioridad = prioridad;
        this.numeroObjetos = numeroObjetos;
    }

    
    public  boolean esPreferente(){
        return prioridad;
    }

    public int getMinutoLlegada(){
        return minutosEsperando;
    }

    public int getNumeroObjetos(){
        return numeroObjetos;
    }


    public void aumentarMinuto() {
        minutosEsperando++;
    }


}
