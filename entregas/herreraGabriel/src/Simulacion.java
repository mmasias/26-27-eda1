public class Simulacion {
    private int minutosTotales;
    private Fila fila;
    private CajaRegistradora caja;
    private int contadorId;

    public Simulacion(int minutosTotales) {
        this.minutosTotales = minutosTotales;
        this.fila = new Fila();
        this.caja = new CajaRegistradora();
        this.contadorId = 1;
    }

    public void ejecutar() {
        for (int minuto = 1; minuto <= this.minutosTotales; minuto++) {

            this.fila.actualizarTiempos();

            if (Math.random() < 0.6) {
                Persona nuevaPersona;
                if (Math.random() < 0.2) {
                    nuevaPersona = new ClientePreferente(String.valueOf(contadorId++), "Prioridad Legal");
                } else {
                    nuevaPersona = new ClienteNormal(String.valueOf(contadorId++));
                }
                this.fila.formarPersona(nuevaPersona);
            }

            if (Math.random() < 0.4) {
                Persona atendida = this.fila.salirPersona();
                if (atendida != null) {
                    this.caja.atenderPersona();
                }
            }

            if (minuto >= 20) {
                if (minuto % 5 == 0) {
                    this.fila.revisarAburridos();
                }

                if (Math.random() < 0.05) {
                    Persona colado = new ClienteNormal(contadorId++ + "Colado");
                    this.fila.colarConocido(colado);
                }

                if (Math.random() < 0.05) {
                    this.fila.entregarComprasAConocido();
                }
            }

            if (minuto % 15 == 0 && this.fila.getLongitud() > 25) {
                System.out.println("[Minuto " + minuto + "] : Pasen por esta caja en orden de fila.");
            }

            System.out.println("Minuto " + minuto + "  - Longitud de la fila: " + this.fila.getLongitud() + " metros.");
        }
    }

    public void mostrarResultados() {
        System.out.println("Resultados CCCF tras " + this.minutosTotales + " minutos ");
        System.out.println("Personas que fueron atendidas: " + this.caja.getTotalAtendidos());
        System.out.println("Personas que quedaron en la fila: " + this.fila.getLongitud());
    }
}