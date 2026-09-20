package huidobroMarcos;

class Main {

    public static void main(String[] args) {

        CentroComercial centroComercial = new CentroComercial();

        centroComercial.simular();

        System.out.println("Personas atendidas: " + centroComercial.getPersonasAtendidas());
        System.out.println("Personas en fila: " + centroComercial.getPersonasEnFila());
    }
}
