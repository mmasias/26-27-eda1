public class Persona {
    private int id;
    private int minutoLlegada;
    private boolean esPreferente;

    public Persona(int id, int minutoLlegada, boolean esPreferente) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;
        this.esPreferente = esPreferente;
    }

    public int getId(){ 
        return id; 
    }

    public int getMinutoLlegada(){
         return minutoLlegada;
    }

    public boolean esPreferente(){
         return esPreferente; 
    }
}