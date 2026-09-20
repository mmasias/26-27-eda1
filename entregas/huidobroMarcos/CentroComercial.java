package huidobroMarcos;

class CentroComercial {

    private Fila fila;
    private Caja caja;
    private int personasAtendidas;

    public CentroComercial() {
        fila = new Fila(240);
        caja = new Caja();
        personasAtendidas = 0;
    }

    public void simular() {
        for (int minuto = 0; minuto < 240; minuto++) {

            if (Math.random() < 0.6) {
                fila.entrar(new Persona());
            }

            if (Math.random() < 0.4 && fila.longitud() > 0) {
                caja.atender(fila);
                personasAtendidas++;
            }
        }
    }

    public int getPersonasAtendidas() {
        return personasAtendidas;
    }

    public int getPersonasEnFila() {
        return fila.longitud();
    }
}