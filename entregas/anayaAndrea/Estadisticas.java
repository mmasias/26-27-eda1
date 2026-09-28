public class Estadisticas {

    private int llegadas;
    private int preferentes;
    private int colados;
    private int entregas;
    private int atendidas;
    private int aburridas;
    private int desistieron;
    private int avisos;

    private int minutosRegistrados;
    private int sumaLongitudes;
    private int longitudMaxima;

    public Estadisticas() {
        this.llegadas = 0;
        this.preferentes = 0;
        this.colados = 0;
        this.entregas = 0;
        this.atendidas = 0;
        this.aburridas = 0;
        this.desistieron = 0;
        this.avisos = 0;
        this.minutosRegistrados = 0;
        this.sumaLongitudes = 0;
        this.longitudMaxima = 0;
    }

    public void registrarLlegada()     { llegadas++; }
    public void registrarPreferente()  { preferentes++; }
    public void registrarColado()      { colados++; }
    public void registrarEntrega()     { entregas++; }
    public void registrarAtendida()    { atendidas++; }
    public void registrarAburrida()    { aburridas++; }
    public void registrarDesistio()    { desistieron++; }
    public void registrarAviso()       { avisos++; }

    public void registrarLongitud(int longitud) {
        minutosRegistrados++;
        sumaLongitudes += longitud;
        if (longitud > longitudMaxima) {
            longitudMaxima = longitud;
        }
    }

    public void imprimirResumen(boolean extendido, int enFila) {
        System.out.println("--- Resumen ---");
        System.out.println("Llegadas normales: " + llegadas);
        System.out.println("Atendidas:         " + atendidas);
        System.out.println("En fila al cierre: " + enFila);
        if (extendido) {
            System.out.println("Preferentes:       " + preferentes);
            System.out.println("Colados:           " + colados);
            System.out.println("Entregas compras:  " + entregas);
            System.out.println("Se aburrieron:     " + aburridas);
            System.out.println("Desistieron:       " + desistieron);
            System.out.println("Avisos parlantes:  " + avisos);
            System.out.println("Longitud maxima:   " + longitudMaxima + " m");
            System.out.printf("Longitud media:    %.2f m%n",
                    (double) sumaLongitudes / minutosRegistrados);
        }
    }
}