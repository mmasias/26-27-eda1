public class Parlante {

    private final int umbral;
    private final int periodo;
    private final String mensaje;

    public Parlante(int umbral, int periodo) {
        this.umbral = umbral;
        this.periodo = periodo;
        this.mensaje = "pasen por esta caja en orden de fila";
    }

    public boolean anunciarSiCorresponde(int minuto, int tamanoFila) {
        if (minuto % periodo == 0 && tamanoFila > umbral) {
            System.out.println("[min " + minuto + "] Parlantes: " + mensaje);
            return true;
        }
        return false;
    }
}