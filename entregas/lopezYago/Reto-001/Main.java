package entregas.lopezYago.Reto-001;

public class Main {
    public static void main(String[] args) {
        CentroComercial centroComercialBasico = new CentroComercial(240, false);
        centroComercialBasico.simular(240);

        CentroComercial centroComercialExtendido = new CentroComercial(120, true);
        centroComercialExtendido.simular(120);
    }
}