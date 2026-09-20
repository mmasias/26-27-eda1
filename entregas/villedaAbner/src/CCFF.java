public class CCFF {

    private Caja[] cajas;
    private Fila fila;
    private Cliente[] filaClientes;
    final private int TIEMPO_TOTAL = 240;
    private int totalPersonasAtendidas;
    final private double PROBABILIDAD_DE_SALIDA = 0.4;
    int totalVips = 0;
    int totalColados = 0;
    int totalAburridos = 0;

    public CCFF() {
        cajas = new Caja[4];
        for (int i = 0; i < 4; i++) {
            cajas[i] = new Caja();
        }
        fila = new Fila();

    }

    public void simular() {
        Mapa mapa = new Mapa();

        for (int tiempoTranscurrido = 0; tiempoTranscurrido < TIEMPO_TOTAL; tiempoTranscurrido++) {

            mapa.proyectar(tiempoTranscurrido, fila, cajas);
            evento(tiempoTranscurrido, mapa);

            if (tiempoTranscurrido % 5 == 0 && tiempoTranscurrido >= 20) {
                mapa.imprimirEvento("¡Un cliente se aburrió de esperar y se fue!");
                for (int i = 0; i < fila.getNumeroPersonas(); i++) {
                    if (fila.estaAburrido(i)) {
                        fila.clienteSaleFila(i);
                        totalAburridos++;
                        i--;
                    }
                }
            }

            if (fila.llegaCliente()) {
                boolean esVip = tiempoTranscurrido >= 20 ? RNG() < 0.1 : false;
                int objetos = (int) (RNG() * 20) + 1;
                Cliente nuevoCliente = new Cliente(tiempoTranscurrido, esVip, objetos);
                if (nuevoCliente.esPreferente()) {totalVips++;}
                fila.recibePersona(nuevoCliente);

            }
            if (RNG() < PROBABILIDAD_DE_SALIDA) {
                for (int i = 0; i < cajas.length; i++) {
                    if (!cajas[i].estaVacia()) {
                        cajas[i].vaciarCaja();
                        break;
                    }
                }

            }
            for (int i = 0; i < cajas.length; i++) {
                if (cajas[i].estaVacia() && fila.getNumeroPersonas() > 0) {
                    fila.eliminaPersona(1);
                    cajas[i].recibePersona();
                }
            }

            for (int i = 0; i < fila.getNumeroPersonas(); i++) {
                fila.getCliente(i).aumentarMinuto();
            }

        }
        for (int i = 0; i < cajas.length; i++) {
            totalPersonasAtendidas += cajas[i].getPersonasAtendidas();
        }
mapa.pantallaFinal(totalPersonasAtendidas, fila.getNumeroPersonas(), totalVips, totalColados, totalAburridos);
    }

    private void evento(int tiempoTranscurrido, Mapa mapa) {
        if (RNG() < 0.1 && tiempoTranscurrido >= 20) {
            mapa.imprimirEvento("¡Alguien se acaba de colar en la fila!");
            boolean esVip = false;
            int objetos = (int) (RNG() * 20) + 1;
            Cliente nuevoCliente = new Cliente(tiempoTranscurrido, esVip, objetos);
            fila.colarCliente(nuevoCliente);
            totalColados++;

        }
    }

    private double RNG() {
        return Math.random();
    }
}
