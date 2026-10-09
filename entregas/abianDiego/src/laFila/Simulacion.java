package laFila;

class Simulacion {
    public static void main(String[] args) {
        CentroComercial centroComercialDelRetoBase = new CentroComercial();
        centroComercialDelRetoBase.simularRetoBase();

        CentroComercial centroComercialDelEscenarioCompleto = new CentroComercial();
        centroComercialDelEscenarioCompleto.simularEscenarioCompleto();
    }
}
