public class Tiempo {
    private int minutoActual;
    private final int duracionTotal;

    public Tiempo(int duracionTotal) {
        this.minutoActual = 0;
        this.duracionTotal = duracionTotal;
    }

    public void avanzarMinuto() {
        minutoActual++;
    }

    public int mostrarMinutoActual(){
        return minutoActual;
    }

    public boolean trabajando() {
        return minutoActual < duracionTotal;
    }   

    public boolean esTiempoDeParlante(){
        return minutoActual > 0 && minutoActual % 15 == 0;
    }

    public boolean reglasNuevasActivas(){
        return minutoActual >= 20;
    }


}